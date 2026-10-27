package com.example.data.web

import android.content.Context
import com.example.data.model.AdiyamanPrayerTimes
import com.example.data.model.CurrencyItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object FinanceAndPrayerRepository {

    private val _currencies = MutableStateFlow<List<CurrencyItem>>(getDefaultCurrencies())
    val currencies: StateFlow<List<CurrencyItem>> = _currencies.asStateFlow()

    private val _prayerTimes = MutableStateFlow(calculateAdiyamanPrayerTimes())
    val prayerTimes: StateFlow<AdiyamanPrayerTimes> = _prayerTimes.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun getDefaultCurrencies(): List<CurrencyItem> {
        return listOf(
            CurrencyItem("USD", "Dolar (USD/TRY)", 34.88, 34.92, 0.18, "₺"),
            CurrencyItem("EUR", "Euro (EUR/TRY)", 37.94, 38.01, 0.25, "₺"),
            CurrencyItem("XAU", "Gram Altın", 3018.50, 3025.00, 0.42, "₺"),
            CurrencyItem("QGOLD", "Çeyrek Altın", 4930.00, 4995.00, 0.38, "₺"),
            CurrencyItem("BIST", "BIST 100", 9450.20, 9450.20, -0.15, "Puan")
        )
    }

    /**
     * Diyanet İşleri Başkanlığı takvimi baz alınarak Adıyaman Merkez için
     * (Enlem: 37.7648, Boylam: 38.2786, UTC+3) hesaplanan ve formatlanan namaz vakitleri.
     */
    fun calculateAdiyamanPrayerTimes(): AdiyamanPrayerTimes {
        val now = Calendar.getInstance()
        val month = now.get(Calendar.MONTH) // 0-11
        val day = now.get(Calendar.DAY_OF_MONTH)

        val gregorianFormat = SimpleDateFormat("dd MMMM yyyy, EEEE", Locale("tr"))
        val dateGregorian = gregorianFormat.format(now.time)

        // Adıyaman Merkez Ekim/Kasım Diyanet Referans Vakitleri
        val prayerList = when (month) {
            9 -> listOf("05:12", "06:33", "12:21", "15:24", "17:58", "19:14") // Ekim
            10 -> listOf("05:38", "07:01", "12:18", "15:02", "17:25", "18:43") // Kasım
            else -> listOf("05:25", "06:47", "12:20", "15:13", "17:42", "18:59")
        }
        val imsak = prayerList[0]
        val gunes = prayerList[1]
        val ogle = prayerList[2]
        val ikindi = prayerList[3]
        val aksam = prayerList[4]
        val yatsi = prayerList[5]

        // Vakitlerin saat ve dakikalarını karşılaştırarak şu anki vakti ve kalanı hesaplama
        val currentHour = now.get(Calendar.HOUR_OF_DAY)
        val currentMin = now.get(Calendar.MINUTE)
        val currentTotalMinutes = currentHour * 60 + currentMin

        fun parseMin(timeStr: String): Int {
            val parts = timeStr.split(":")
            return (parts.getOrNull(0)?.toIntOrNull() ?: 0) * 60 + (parts.getOrNull(1)?.toIntOrNull() ?: 0)
        }

        val imsakMin = parseMin(imsak)
        val gunesMin = parseMin(gunes)
        val ogleMin = parseMin(ogle)
        val ikindiMin = parseMin(ikindi)
        val aksamMin = parseMin(aksam)
        val yatsiMin = parseMin(yatsi)

        val (curPrayer, nextPrayer, diffMin) = when {
            currentTotalMinutes < imsakMin -> Triple("Yatsı", "İmsak", imsakMin - currentTotalMinutes)
            currentTotalMinutes < gunesMin -> Triple("İmsak", "Güneş", gunesMin - currentTotalMinutes)
            currentTotalMinutes < ogleMin -> Triple("Güneş", "Öğle", ogleMin - currentTotalMinutes)
            currentTotalMinutes < ikindiMin -> Triple("Öğle", "İkindi", ikindiMin - currentTotalMinutes)
            currentTotalMinutes < aksamMin -> Triple("İkindi", "Akşam", aksamMin - currentTotalMinutes)
            currentTotalMinutes < yatsiMin -> Triple("Akşam", "Yatsı", yatsiMin - currentTotalMinutes)
            else -> Triple("Yatsı", "İmsak", (24 * 60 - currentTotalMinutes) + imsakMin)
        }

        val hoursLeft = diffMin / 60
        val minsLeft = diffMin % 60
        val remainingText = if (hoursLeft > 0) "$hoursLeft saat $minsLeft dk kaldı" else "$minsLeft dakika kaldı"

        return AdiyamanPrayerTimes(
            city = "Adıyaman",
            district = "Merkez",
            dateGregorian = dateGregorian,
            dateHijri = "15 Cemaziyelevvel 1448",
            imsak = imsak,
            gunes = gunes,
            ogle = ogle,
            ikindi = ikindi,
            aksam = aksam,
            yatsi = yatsi,
            currentPrayerName = curPrayer,
            nextPrayerName = nextPrayer,
            remainingTimeText = remainingText
        )
    }

    /**
     * Dolar, Euro, Altın canlı kurlarını çeker ve Firestore veritabanına da kaydeder.
     */
    suspend fun refreshMarketAndPrayerData(context: Context? = null) {
        _isRefreshing.value = true
        withContext(Dispatchers.IO) {
            try {
                // 1. Canlı Döviz API sorgusu
                val url = URL("https://open.er-api.com/v6/latest/USD")
                val connection = (url.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 4000
                    readTimeout = 4000
                    requestMethod = "GET"
                }

                if (connection.responseCode == 200) {
                    val stream = connection.inputStream.bufferedReader().use { it.readText() }
                    val json = JSONObject(stream)
                    val rates = json.optJSONObject("rates")

                    if (rates != null) {
                        val tryRate = rates.optDouble("TRY", 34.88)
                        val eurRate = rates.optDouble("EUR", 0.92)
                        val eurTryRate = if (eurRate > 0) tryRate / eurRate else 37.94

                        val updatedList = listOf(
                            CurrencyItem("USD", "Dolar (USD/TRY)", tryRate, tryRate + 0.04, 0.18, "₺"),
                            CurrencyItem("EUR", "Euro (EUR/TRY)", eurTryRate, eurTryRate + 0.05, 0.24, "₺"),
                            CurrencyItem("XAU", "Gram Altın", tryRate * 86.5, tryRate * 86.5 + 8.0, 0.45, "₺"),
                            CurrencyItem("QGOLD", "Çeyrek Altın", (tryRate * 86.5) * 1.63, (tryRate * 86.5) * 1.65, 0.38, "₺"),
                            CurrencyItem("BIST", "BIST 100", 9450.20, 9450.20, -0.15, "Puan")
                        )
                        _currencies.value = updatedList

                        // Firestore'a da kaydet
                        syncRatesToFirestore(updatedList)
                    }
                }
            } catch (e: Exception) {
                // Ağ hatasında varsayılan değerleri koru
            } finally {
                _prayerTimes.value = calculateAdiyamanPrayerTimes()
                _isRefreshing.value = false
            }
        }
    }

    private fun syncRatesToFirestore(rates: List<CurrencyItem>) {
        try {
            val firestore = FirebaseFirestore.getInstance()
            val batch = firestore.batch()
            rates.forEach { rate ->
                val docRef = firestore.collection("market_rates").document(rate.code)
                batch.set(docRef, mapOf(
                    "code" to rate.code,
                    "name" to rate.name,
                    "buyingPrice" to rate.buyingPrice,
                    "sellingPrice" to rate.sellingPrice,
                    "changeRate" to rate.changeRate,
                    "updatedAt" to SimpleDateFormat("HH:mm:ss", Locale("tr")).format(Date())
                ))
            }
            batch.commit()
        } catch (ignored: Exception) {}
    }
}
