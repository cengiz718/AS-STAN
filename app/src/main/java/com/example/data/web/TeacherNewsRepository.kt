package com.example.data.web

import android.util.Log
import com.example.data.model.TeacherNewsItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TeacherNewsRepository {

    private val _newsList = MutableStateFlow<List<TeacherNewsItem>>(getDefaultNews())
    val newsList: StateFlow<List<TeacherNewsItem>> = _newsList.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private var isListenerAttached = false

    init {
        startRealtimeFirestoreListener()
    }

    fun startRealtimeFirestoreListener() {
        if (isListenerAttached) return
        isListenerAttached = true

        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("teacher_news")
                .orderBy("publishDate", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w("TeacherNewsRepository", "Listen failed", error)
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {
                        val cloudNews = snapshot.documents.mapNotNull { doc ->
                            try {
                                TeacherNewsItem(
                                    id = doc.getString("id") ?: doc.id,
                                    title = doc.getString("title") ?: "",
                                    summary = doc.getString("summary") ?: "",
                                    content = doc.getString("content") ?: "",
                                    category = doc.getString("category") ?: "Son Dakika MEB",
                                    source = doc.getString("source") ?: "MEB Duyuru",
                                    publishDate = doc.getString("publishDate") ?: "",
                                    isUrgent = doc.getBoolean("isUrgent") ?: false,
                                    tags = (doc.get("tags") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
                                )
                            } catch (e: Exception) {
                                null
                            }
                        }

                        // Birleştir ve yinelenenleri filtrele (Cloud'dakiler öncelikli)
                        val combined = (cloudNews + getDefaultNews()).distinctBy { it.id }
                        _newsList.value = combined
                    }
                }
        } catch (e: Exception) {
            Log.e("TeacherNewsRepository", "Firestore initialization skipped: ${e.message}")
        }
    }

    /**
     * Yeni bir haber yayınlar. Bu haber Firestore veritabanına yazıldığı anda
     * TÜM ANDROID CİHAZLARINDA otomatik olarak anında görünür!
     */
    suspend fun publishNewsToAllDevices(item: TeacherNewsItem, onSuccess: () -> Unit, onError: (String) -> Unit) {
        _isSyncing.value = true
        try {
            val firestore = FirebaseFirestore.getInstance()
            val docData = mapOf(
                "id" to item.id,
                "title" to item.title,
                "summary" to item.summary,
                "content" to item.content,
                "category" to item.category,
                "source" to item.source,
                "publishDate" to item.publishDate,
                "isUrgent" to item.isUrgent,
                "tags" to item.tags,
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("teacher_news").document(item.id)
                .set(docData)
                .addOnSuccessListener {
                    _isSyncing.value = false
                    // Yerel listeye de hemen ekle
                    _newsList.value = listOf(item) + _newsList.value.filter { it.id != item.id }
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    _isSyncing.value = false
                    onError(e.localizedMessage ?: "Haber gönderilemedi")
                }
        } catch (e: Exception) {
            _isSyncing.value = false
            onError(e.localizedMessage ?: "Bulut bağlantı hatası")
        }
    }

    fun getDefaultNews(): List<TeacherNewsItem> {
        return listOf(
            TeacherNewsItem(
                id = "news_omk_2026",
                title = "Öğretmenlik Mesleği Kanunu (ÖMK) Resmî Gazete'de Yayımlandı",
                summary = "Öğretmenlere yönelik şiddete cezaların artırılması, uzman ve başöğretmenlik sürelerinin 10 yıldan 8 yıla indirilmesi yürürlüğe girdi.",
                content = """
                Millî Eğitim Bakanlığı tarafından hazırlanan ve Türkiye Büyük Millet Meclisi'nde kabul edilen yeni Öğretmenlik Mesleği Kanunu (ÖMK) Resmî Gazete'de yayımlanarak yürürlüğe girdi.
                
                YENİ KANUNUN GETİRDİĞİ BAŞLICA YENİLİKLER:
                1. Öğretmenlere karşı işlenen kasten yaralama, tehdit, hakaret ve görevi yaptırmamak için direnme suçlarında cezalar %50 oranında artırıldı ve hapis cezası ertelemesi kaldırıldı.
                2. Uzman öğretmenlik için gereken 10 yıllık mesleki kıdem şartı 8 yıla indirildi.
                3. Başöğretmenlik için gereken uzmanlık süresi 10 yıldan 6 yıla düşürüldü; böylece öğretmenlerin kariyer basamaklarında ilerlemesi hızlandırıldı.
                4. Milli Eğitim Akademisi kurularak öğretmen adaylarının hazırlık eğitimi ve görevdeki öğretmenlerin mesleki gelişimleri güvenceye alındı.
                """.trimIndent(),
                category = "Özlük Hakları & Maaş",
                source = "Resmî Gazete / MEB",
                publishDate = "27 Ekim 2026",
                isUrgent = true,
                tags = listOf("ÖMK", "uzman öğretmenlik", "başöğretmenlik", "şiddet cezası")
            ),
            TeacherNewsItem(
                id = "news_turkcen_net_guncelleme",
                title = "turkcen.net Portalı 2026-2027 MEB Maarif Modeli Yazılı Senaryolarını Yayımladı",
                summary = "Kubilay Oral ve Türkçe zümresi, 5, 6, 7 ve 8. sınıf 1. ve 2. dönem açık uçlu ortak sınav örneklerini öğretmenlerin erişimine sundu.",
                content = """
                Türkçe öğretmenlerinin en popüler kaynak platformlarından olan www.turkcen.net, Türkiye Yüzyılı Maarif Modeli'ne uygun olarak hazırlanan 1. Dönem ve 2. Dönem ortak yazılı sınav senaryolarını güncelledi.
                
                YAYINLANAN MATERYALLER:
                • 5. ve 6. Sınıf yeni maarif modeli beceri temelli açık uçlu soruları.
                • 7. ve 8. Sınıf LGS odaklı okuduğunu anlama, grafik/tablo yorumlama ve dil bilgisi soruları.
                • %25 dinleme ve %25 konuşma sınavı değerlendirme rubrikleri ve ses dosyaları.
                • Sene başı ve dönem başı zümre toplantı tutanakları ve onay dilekçeleri.
                
                Tüm dökümanlar öğretmen asistanı uygulamamız içerisinden A4 baskı ve Word formatında anında indirilip düzenlenebilmektedir.
                """.trimIndent(),
                category = "Türkçe Dersi & Maarif Modeli",
                source = "turkcen.net",
                publishDate = "26 Ekim 2026",
                isUrgent = false,
                tags = listOf("turkcen.net", "yazılı sınav", "maarif modeli", "kubilay oral")
            ),
            TeacherNewsItem(
                id = "news_ek_ders_2026",
                title = "2026-2027 Öğretmen Ek Ders Ücretleri ve Maaş Katsayıları Belli Oldu",
                summary = "Gündüz ek ders saat ücreti brüt 142,50 TL (net 125,80 TL) olarak belirlendi. Destekleme ve yetiştirme kurslarında (DYK) ek ders ücreti 2 katı ödenecek.",
                content = """
                Maliye Bakanlığı genelgesi uyarınca 2026-2027 eğitim-öğretim dönemi öğretmen ek ders saat ücretleri güncellendi:
                
                ÜCRET ÇİZELGESİ:
                • Gündüz Ek Ders Ücreti (Brüt): 142,50 TL | Net (%15 vergi): 121,12 TL
                • Gece ve Hafta Sonu Ek Ders Ücreti (Brüt): 152,65 TL | Net: 129,75 TL
                • DYK (Destekleme Yetiştirme Kursu) Ek Ders Ücreti: %100 artırımlı (242,24 TL Net)
                • Özel Eğitim ve İYEP Ek Ders Ücreti: %25 artırımlı (151,40 TL Net)
                • Nöbet Görevi Ücreti: Haftalık 3 ders saati (363,36 TL Net)
                
                Ayrıca uzman öğretmenlik tazminatı aylık 5.420 TL, başöğretmenlik tazminatı ise 10.840 TL olarak maaşlara yansıtılacaktır.
                """.trimIndent(),
                category = "Özlük Hakları & Maaş",
                source = "Maliye Bakanlığı & MEB",
                publishDate = "25 Ekim 2026",
                isUrgent = true,
                tags = listOf("ek ders", "maaş katsayısı", "DYK", "nöbet ücreti")
            ),
            TeacherNewsItem(
                id = "news_lgs_2027_takvim",
                title = "2027 LGS Başvuru ve Uygulama Kılavuzu Duyuruldu",
                summary = "8. sınıf öğrencileri için Liselere Geçiş Sistemi (LGS) merkezi sınav tarihi ve Türkçe dersi soru dağılımı açıklandı.",
                content = """
                Millî Eğitim Bakanlığı Ölçme, Değerlendirme ve Sınav Hizmetleri Genel Müdürlüğü (ÖDSGM), bu yılki LGS sınav takvimini açıkladı.
                
                LGS DETAYLARI:
                • Sınav Tarihi: 6 Haziran 2027 Pazar günü iki oturum halinde yapılacaktır.
                • Sözel Bölüm: Türkçe (20 Soru), T.C. İnkılap Tarihi ve Atatürkçülük (10 Soru), Din Kültürü ve Ahlak Bilgisi (10 Soru), Yabancı Dil (10 Soru) - Süre: 75 Dakika.
                • Türkçe Testi: Paragrafta anlam, görsel yorumlama ve mantık-muhakeme sorularının ağırlığı korunacaktır.
                • Sınav Giriş Belgeleri: e-Okul Veli Bilgilendirme Sistemi üzerinden okul müdürlüklerince onaylanarak öğrencilere teslim edilecektir.
                """.trimIndent(),
                category = "LGS & Sınavlar",
                source = "ÖDSGM / MEB",
                publishDate = "24 Ekim 2026",
                isUrgent = false,
                tags = listOf("LGS", "8. sınıf", "sınav takvimi", "ÖDSGM")
            ),
            TeacherNewsItem(
                id = "news_dilimizin_zenginlikleri_kasim",
                title = "Kasım Ayı 'Dilimizin Zenginlikleri Projesi' Faaliyet Takvimi Açıklandı",
                summary = "Bu ay okullarda 'Klasik Eser Okumaları', 'Sözlük Özgürlüktür' panoları ve 'Cümle/Afiş Yarışması' düzenlenecek.",
                content = """
                MEB Temel Eğitim Genel Müdürlüğü tarafından yürütülen 'Dilimizin Zenginlikleri Projesi' kapsamında Kasım ayı eylem planı tüm il milli eğitim müdürlüklerine gönderildi.
                
                KASIM AYI ETKİNLİKLERİ:
                1. Sınıflarda 'Bilen Bulur' ve 'Kelime Türetme' oyunlarının oynatılması.
                2. Okul koridorlarında öğrencilerin seçtikleri edebi deyim ve atasözleriyle 'Deyim Resimleme' sergisi açılması.
                3. Safahat ve Dîvânu Lugâti't-Türk temalı afiş ve slogan yarışması.
                4. Her sınıfta en az bir edebi klasik eserin derinlemesine tahlilinin yapılması.
                """.trimIndent(),
                category = "Türkçe Dersi & Maarif Modeli",
                source = "Temel Eğitim Genel Müdürlüğü",
                publishDate = "23 Ekim 2026",
                isUrgent = false,
                tags = listOf("Dilimizin Zenginlikleri", "MEB Proje", "Edebiyat", "Afiş")
            ),
            TeacherNewsItem(
                id = "news_ara_tatil_meb",
                title = "1. Dönem Ara Tatil Tarihleri ve Öğretmen Mesleki Çalışma Programı",
                summary = "Öğrenciler için ara tatil 9-13 Kasım tarihleri arasında uygulanacak; öğretmen seminerleri ÖBA üzerinden çevrim içi izlenebilecek.",
                content = """
                Millî Eğitim Bakanı imzasıyla yayımlanan genelgeye göre, 2026-2027 Eğitim Öğretim Yılı birinci dönem ara tatili 9 Kasım Pazartesi günü başlayıp 13 Kasım Cuma günü sona erecektir.
                
                Hafta sonları ile birlikte toplam 9 günlük tatil sürecinde öğretmenlerin mesleki çalışma seminerleri Öğretmen Bilişim Ağı (ÖBA) platformu üzerinden uzaktan eğitim yöntemiyle tamamlanabilecektir.
                """.trimIndent(),
                category = "Son Dakika MEB",
                source = "MEB Basın Müşavirliği",
                publishDate = "22 Ekim 2026",
                isUrgent = false,
                tags = listOf("ara tatil", "ÖBA", "seminer", "tatil takvimi")
            )
        )
    }
}
