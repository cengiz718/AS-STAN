package com.example.data.model

data class CurrencyItem(
    val code: String,          // USD, EUR, XAU, QGOLD
    val name: String,          // Dolar, Euro, Gram Altın, Çeyrek Altın
    val buyingPrice: Double,   // Alış
    val sellingPrice: Double,  // Satış
    val changeRate: Double,    // Değişim yüzdesi (+0.35, -0.12 vb.)
    val symbol: String = "₺",
    val lastUpdate: String = "Canlı"
)

data class AdiyamanPrayerTimes(
    val city: String = "Adıyaman",
    val district: String = "Merkez",
    val dateGregorian: String,
    val dateHijri: String,
    val imsak: String,     // İmsak
    val gunes: String,     // Güneş
    val ogle: String,      // Öğle
    val ikindi: String,    // İkindi
    val aksam: String,     // Akşam
    val yatsi: String,     // Yatsı
    val currentPrayerName: String,
    val nextPrayerName: String,
    val remainingTimeText: String
)

data class TeacherNewsItem(
    val id: String,
    val title: String,
    val summary: String,
    val content: String,
    val category: String, // Son Dakika MEB, Türkçe Dersi & Maarif Modeli, Özlük Hakları & Maaş, LGS & Sınavlar, Atama & Tayin
    val source: String,   // MEB.gov.tr, turkcen.net, Resmi Gazete, Memurlar.net
    val publishDate: String,
    val isUrgent: Boolean = false,
    val tags: List<String> = emptyList()
)
