package com.example.data.curriculum

data class WeeklyPlanRow(
    val weekNumber: Int,
    val dateRange: String,
    val themeName: String,
    val lessonHours: Int = 6,
    val learningOutcomes: String,
    val grammarTopic: String,
    val textOrActivity: String,
    val methodsTechniques: String,
    val toolsEquipment: String,
    val evaluationMethod: String,
    val specialDayOrNote: String = ""
)

data class AnnualPlan(
    val id: String,
    val grade: String, // "5. Sınıf", "6. Sınıf", "7. Sınıf", "8. Sınıf", "8. Sınıf DYK", "Seçmeli Yazarlık"
    val title: String,
    val academicYear: String = "2024-2025 / 2025-2026",
    val weeklyHours: Int = 6,
    val totalHours: Int = 216,
    val generalObjectives: String,
    val weeks: List<WeeklyPlanRow>
)

data class DailyLessonPlan(
    val id: String,
    val grade: String,
    val title: String,
    val themeName: String,
    val textTitle: String,
    val durationMinutes: Int = 40,
    val outcomes: List<String>,
    val targetSkills: List<String>, // Maarif Modeli becerileri: Eleştirel düşünme, çıkarım yapma, söz varlığı
    val materialsUsed: List<String>,
    val step1Introduction: String, // Merak uyandırma / Giriş (5-8 dk)
    val step2Development: String, // Keşfetme / Metin İnceleme / Uygulama (20-25 dk)
    val step3Deepening: String, // Dilbilgisi ve Derinleştirme (10 dk)
    val step4Assessment: String, // Değerlendirme / Çıkış Bileti (5 dk)
    val differentiationBep: String, // Bireyselleştirilmiş eğitim / BEP uyarlaması
    val homeworkExtension: String
)

data class MesutHayatCategoryItem(
    val id: String,
    val title: String,
    val gradeOrType: String,
    val url: String,
    val description: String,
    val keyFeatures: List<String>
)

object LessonPlansRepository {

    // =========================================================================
    // 1. MESUT HAYAT (mesuthayat.com) TÜRKÇE PORTALI KATALOĞU
    // =========================================================================
    val mesutHayatCategories: List<MesutHayatCategoryItem> = listOf(
        MesutHayatCategoryItem(
            id = "mh_5_plan",
            title = "5. Sınıf Türkçe Günlük ve Yıllık Planları",
            gradeOrType = "5. Sınıf",
            url = "https://www.mesuthayat.com/5-sinif-turkce-gunluk-planlari",
            description = "MEB yeni müfredatına ve Türkiye Yüzyılı Maarif Modeline uygun 5. sınıf Türkçe dersi hafta hafta ünitelendirilmiş yıllık planları ve tema bazlı 40 dakikalık günlük planları.",
            keyFeatures = listOf("Maarif Modeli Öğrenme Çıktıları", "Tema 1-8 Metin Planları", "Okuma-Yazma Atölyeleri", "Ölçme Değerlendirme Formları")
        ),
        MesutHayatCategoryItem(
            id = "mh_6_plan",
            title = "6. Sınıf Türkçe Günlük ve Yıllık Planları",
            gradeOrType = "6. Sınıf",
            url = "https://www.mesuthayat.com/6-sinif-turkce-gunluk-planlari",
            description = "6. sınıf MEB Türkçe ders kitabı temalarına göre hazırlanmış, tamlamalar, zamirler ve edat-bağlaç konularını aşamalı işleyen günlük ve yıllık ders planı arşivi.",
            keyFeatures = listOf("Haftalık 6 Saatlik Plan", "İsim & Sıfat Tamlaması Föyleri", "Dinleme Metni Kılavuzları", "Zümre Şablonları")
        ),
        MesutHayatCategoryItem(
            id = "mh_7_plan",
            title = "7. Sınıf Türkçe Günlük ve Yıllık Planları",
            gradeOrType = "7. Sınıf",
            url = "https://www.mesuthayat.com/7-sinif-turkce-gunluk-planlari",
            description = "Fiillerin anlam özellikleri, kipler, ek fiil ve zarflar konusunu detaylandıran, haftalık ders işleniş süreçlerini adım adım veren 7. sınıf plan koleksiyonu.",
            keyFeatures = listOf("Fiillerde Anlam Kayması", "Ek Fiil Uygulama Planı", "Anlatım Biçimleri Analizi", "Süreç Odaklı Rubrikler")
        ),
        MesutHayatCategoryItem(
            id = "mh_8_plan",
            title = "8. Sınıf & LGS Türkçe Günlük ve Yıllık Planları",
            gradeOrType = "8. Sınıf (LGS)",
            url = "https://www.mesuthayat.com/8-sinif-turkce-gunluk-planlari",
            description = "LGS sınav hazırlığıyla senkronize ilerleyen 8. sınıf Türkçe yıllık planı, fiilimsiler, cümlenin ögeleri, metin türleri ve sözel mantık strateji ders planları.",
            keyFeatures = listOf("LGS Soru Çözüm Saatleri", "Sözel Mantık Taktikleri", "Yeni Nesil Beceri Temelli Plan", "DYK Kurs Planı Entegrasyonu")
        ),
        MesutHayatCategoryItem(
            id = "mh_yillik_tum",
            title = "Ünitelendirilmiş Yıllık Planlar (Tüm Kademeler)",
            gradeOrType = "Yıllık Plan",
            url = "https://www.mesuthayat.com/turkce-yillik-planlar",
            description = "Ortaokul 5, 6, 7 ve 8. sınıflar için MEB çalışma takvimine göre hazırlanmış, resmî okul müdürü onayı için imzaya hazır Türkçe yıllık çerçeve planları.",
            keyFeatures = listOf("36 Haftalık MEB Takvimi", "Kazanım-Tema Eşleştirmesi", "Yöntem & Teknikler Tablosu", "A4 Resmî Çıktı Formatı")
        ),
        MesutHayatCategoryItem(
            id = "mh_yazili_sinav",
            title = "MEB Ortak Yazılı Sınav Senaryoları & Kağıtları",
            gradeOrType = "Sınav Arşivi",
            url = "https://www.mesuthayat.com/turkce-yazili-sorulari",
            description = "1. Dönem 1. ve 2. Yazılı, 2. Dönem 1. ve 2. Yazılı MEB il/ilçe ortak açık uçlu sınav senaryolarına uygun, cevap anahtarlı ve not baremli yazılı sınav kağıtları.",
            keyFeatures = listOf("MEB 1. ve 2. Senaryo", "Açık Uçlu Soru Kalıpları", "Puan Dağılım Tablosu", "Cevap Anahtarı ve Rubrik")
        ),
        MesutHayatCategoryItem(
            id = "mh_calisma_kagit",
            title = "Türkçe Konu Anlatımı ve Çalışma Kağıtları",
            gradeOrType = "Çalışma Föyü",
            url = "https://www.mesuthayat.com/turkce-calisma-kagitlari",
            description = "Öğrencilerin derste ve evde çözebileceği, dilbilgisi kurallarını bulmaca, tablo ve eşleştirme teknikleriyle sevdiren zengin çalışma yaprakları.",
            keyFeatures = listOf("Kök-Ek Alıştırmaları", "Cümle Ögeleri Şeması", "Noktalama & İmla Kartları", "Paragraf Taktikleri")
        ),
        MesutHayatCategoryItem(
            id = "mh_puan_hesaplama",
            title = "Online LGS ve Yazılı Sınav Puan Hesaplama",
            gradeOrType = "Dijital Araç",
            url = "https://www.mesuthayat.com/lgs-puan-hesaplama",
            description = "Öğretmen ve öğrencilerin yazılı notlarını ve LGS net/yüzdelik dilim puanlarını MEB katsayılarıyla anlık hesaplayan pratik hesaplama modülü.",
            keyFeatures = listOf("MEB Güncel Katsayıları", "Standart Sapma Simülasyonu", "Türkçe Net Hesabı", "Yazılı Barem Toplamı")
        )
    )

    // =========================================================================
    // 2. ÜNİTELENDİRİLMİŞ YILLIK DERS PLANLARI (5, 6, 7, 8. SINIF)
    // =========================================================================
    val annualPlans: List<AnnualPlan> = listOf(
        // 5. SINIF YILLIK PLAN
        AnnualPlan(
            id = "PLAN_YILLIK_5",
            grade = "5. Sınıf",
            title = "5. Sınıf Türkçe Dersi Ünitelendirilmiş Yıllık Planı",
            weeklyHours = 6,
            totalHours = 216,
            generalObjectives = "Öğrencilerin dinleme, konuşma, okuma ve yazma becerilerini geliştirmek; kelime dağarcığını zenginleştirmek; dil bilgisi kurallarını metin bağlamında anlamlandırmak ve millî, manevi, insani değerleri benimsetmek.",
            weeks = listOf(
                WeeklyPlanRow(
                    weekNumber = 1,
                    dateRange = "09 - 13 Eylül",
                    themeName = "1. Tema: Birey ve Toplum",
                    learningOutcomes = "T.5.1.1. Dinlediklerinde/izlediklerinde geçen bilmediği kelimelerin anlamını tahmin eder. T.5.3.3. Kelimelerin eş anlamlılarını bulur.",
                    grammarTopic = "Sözcükte Anlam (Gerçek Anlam)",
                    textOrActivity = "Metin: Memleket İsterim (Cahit Sıtkı Tarancı) - Şiir tahlili ve sesli okuma",
                    methodsTechniques = "Sesli okuma, soru-cevap, beyin fırtınası, kelime defteri tutma",
                    toolsEquipment = "Ders kitabı, EBA, sözlük, mesuthayat.com kelime çalışma kağıdı",
                    evaluationMethod = "Ders içi gözlem, kelime tahmin kartları değerlendirmesi",
                    specialDayOrNote = "İlköğretim Haftası"
                ),
                WeeklyPlanRow(
                    weekNumber = 2,
                    dateRange = "16 - 20 Eylül",
                    themeName = "1. Tema: Birey ve Toplum",
                    learningOutcomes = "T.5.3.4. Kelimelerin mecaz ve terim anlamlarını ayırt eder. T.5.4.5. Büyük harfleri ve noktalama işaretlerini uygun yerlerde kullanır.",
                    grammarTopic = "Mecaz Anlam & Terim Anlam, Nokta ve Virgül",
                    textOrActivity = "Metin: Lokumcular (Fakir Baykurt) - Paylaşma ve yardımlaşma konusu",
                    methodsTechniques = "Güdümlü okuma, canlandırma, eşleştirme etkinliği",
                    toolsEquipment = "Ders kitabı, akıllı tahta, noktalama kartları",
                    evaluationMethod = "Kısa cevaplı değerlendirme testi",
                    specialDayOrNote = ""
                ),
                WeeklyPlanRow(
                    weekNumber = 3,
                    dateRange = "23 - 27 Eylül",
                    themeName = "1. Tema: Birey ve Toplum",
                    learningOutcomes = "T.5.1.8. Dinledikleri/izlediklerine yönelik sorulara cevap verir. T.5.3.10. Kök ve ekleri ayırt eder.",
                    grammarTopic = "Kök ve Ek Kavramı (İsim Kökü / Fiil Kökü)",
                    textOrActivity = "Metin: Gülümsemenin Gücü (Doğan Cüceloğlu) & Dinleme Metni: Konuşan Ağaç",
                    methodsTechniques = "Not alarak dinleme, kök-ek ayırma bulmacası",
                    toolsEquipment = "Ses kaydı, EBA dinleme platformu, çalışma föyü",
                    evaluationMethod = "Dinleme anlama rubriği",
                    specialDayOrNote = "26 Eylül Dil Bayramı Etkinliği"
                ),
                WeeklyPlanRow(
                    weekNumber = 4,
                    dateRange = "30 Eylül - 04 Ekim",
                    themeName = "1. Tema: Birey ve Toplum",
                    learningOutcomes = "T.5.4.1. Şiir yazar veya bilgilendirici metin yazar. T.5.4.16. Yazdıklarını imla ve noktalama bakımından kontrol eder.",
                    grammarTopic = "Yapım Ekleri (-lik, -ci, -li, -siz) ve Türemiş Kelimeler",
                    textOrActivity = "Tema Sonu Değerlendirme & 'Dostluk ve Paylaşım' konulu kompozisyon çalışması",
                    methodsTechniques = "Süreç temelli yazma modeli, akran değerlendirmesi",
                    toolsEquipment = "Ders kitabı tema sonu değerlendirme formu",
                    evaluationMethod = "MEB 5 Kriterli Kompozisyon Puanlama Baremi",
                    specialDayOrNote = "1. Tema Sonu Değerlendirmesi"
                )
            )
        ),

        // 6. SINIF YILLIK PLAN
        AnnualPlan(
            id = "PLAN_YILLIK_6",
            grade = "6. Sınıf",
            title = "6. Sınıf Türkçe Dersi Ünitelendirilmiş Yıllık Planı",
            weeklyHours = 6,
            totalHours = 216,
            generalObjectives = "Öğrencilerin okuma zevk ve alışkanlığını geliştirmek, Türkçenin dilbilgisel yapısını (tamlamalar, zamirler, ekler) sezgisel ve uygulamalı kavramak, düşüncelerini sözlü ve yazılı olarak tutarlı ifade edebilmesini sağlamak.",
            weeks = listOf(
                WeeklyPlanRow(
                    weekNumber = 1,
                    dateRange = "09 - 13 Eylül",
                    themeName = "1. Tema: Duygular ve Paylaşım",
                    learningOutcomes = "T.6.3.5. Bağlamdan yararlanarak bilmediği kelimelerin anlamını tahmin eder. T.6.3.8. İsim tamlamalarının metnin anlamına katkısını açıklar.",
                    grammarTopic = "İsimler (Adlar) ve Belirtili / Belirtisiz İsim Tamlamaları",
                    textOrActivity = "Metin: Kaşağı (Ömer Seyfettin) - Vicdan ve kardeş sevgisi tahlili",
                    methodsTechniques = "Olay örgüsü analizi, rol oynama, tamlama avcısı oyunu",
                    toolsEquipment = "Ders kitabı, sözlük, mesuthayat.com tamlamalar föyü",
                    evaluationMethod = "Çalışma yaprağı kontrolü ve sınıf içi soru-cevap",
                    specialDayOrNote = "İlköğretim Haftası"
                ),
                WeeklyPlanRow(
                    weekNumber = 2,
                    dateRange = "16 - 20 Eylül",
                    themeName = "1. Tema: Duygular ve Paylaşım",
                    learningOutcomes = "T.6.3.14. Hikâye edici metinlerin unsurlarını (olay, kişi, yer, zaman) belirler. T.6.3.8. Zincirleme isim tamlamalarını tanır.",
                    grammarTopic = "Zincirleme İsim Tamlaması & İsim Çekim Ekleri",
                    textOrActivity = "Metin: Bir Fotoğrafa Bakarken (Sunay Akın) & Hikâye Haritası Çıkarma",
                    methodsTechniques = "Grafik düzenleyici kullanımı, grup çalışması",
                    toolsEquipment = "Hikaye haritası şablonu, etkileşimli tahta",
                    evaluationMethod = "Hikaye unsurları rubriği",
                    specialDayOrNote = ""
                )
            )
        ),

        // 7. SINIF YILLIK PLAN
        AnnualPlan(
            id = "PLAN_YILLIK_7",
            grade = "7. Sınıf",
            title = "7. Sınıf Türkçe Dersi Ünitelendirilmiş Yıllık Planı",
            weeklyHours = 5,
            totalHours = 180,
            generalObjectives = "Öğrencilerin fiillerin anlam ve çekim özelliklerini kavramalarını, ek fiil ve zarfların cümledeki işlevini fark etmelerini, okuduğu metinlerdeki örtük anlamları ve düşünceyi geliştirme yollarını çözümlemelerini sağlamak.",
            weeks = listOf(
                WeeklyPlanRow(
                    weekNumber = 1,
                    dateRange = "09 - 13 Eylül",
                    themeName = "1. Tema: Duygular ve İnsan",
                    learningOutcomes = "T.7.3.10. Fiillerin anlam özelliklerini (iş, oluş, durum) kavrar. T.7.3.11. Fiillerin haber ve dilek kiplerini açıklar.",
                    grammarTopic = "Fiillerin Anlam Özellikleri (İş / Oluş / Durum) & Kip Ekleri",
                    textOrActivity = "Metin: Eskici (Refik Halid Karay) - Ana dili özlemi ve insan sevgisi",
                    methodsTechniques = "Metin inceleme, dramatizasyon, karşılaştırma tablosu",
                    toolsEquipment = "Ders kitabı, EBA videoları, fiil çekim tablosu",
                    evaluationMethod = "Kazanım değerlendirme testi",
                    specialDayOrNote = "İlköğretim Haftası"
                ),
                WeeklyPlanRow(
                    weekNumber = 2,
                    dateRange = "16 - 20 Eylül",
                    themeName = "1. Tema: Duygular ve İnsan",
                    learningOutcomes = "T.7.3.12. Ek fiili işlevlerine uygun şekilde kullanır. T.7.3.13. Zarfların cümledeki işlevini belirler.",
                    grammarTopic = "Ek Fiilin Görevleri (İsmi yüklem yapma & Birleşik zaman kurma)",
                    textOrActivity = "Metin: Yalnızlık Paylaşılmaz & Ek fiil cümle analiz alıştırmaları",
                    methodsTechniques = "İstasyon tekniği, cümle tahlili",
                    toolsEquipment = "Çalışma kağıdı, mesuthayat.com ek fiil özeti",
                    evaluationMethod = "Bireysel alıştırma puanlaması",
                    specialDayOrNote = ""
                )
            )
        ),

        // 8. SINIF (LGS) YILLIK PLAN
        AnnualPlan(
            id = "PLAN_YILLIK_8",
            grade = "8. Sınıf",
            title = "8. Sınıf LGS Odaklı Türkçe Ünitelendirilmiş Yıllık Planı",
            weeklyHours = 5,
            totalHours = 180,
            generalObjectives = "Öğrencileri LGS sınavına hazırlamak; fiilimsiler, cümlenin ögeleri, cümle türleri, yazım ve noktalama konularında tam yetkinlik sağlamak; yeni nesil sözel mantık ve tablo/grafik yorumlama becerilerini en üst seviyeye ulaştırmak.",
            weeks = listOf(
                WeeklyPlanRow(
                    weekNumber = 1,
                    dateRange = "09 - 13 Eylül",
                    themeName = "1. Tema: Erdemler ve Hayatın Anlamı",
                    learningOutcomes = "T.8.3.9. Fiilimsilerin cümledeki işlevlerini fark eder, türlerini ayırt eder. T.8.3.14. Metindeki ana fikri ve yardımcı fikirleri belirler.",
                    grammarTopic = "Fiilimsiler (İsim-Fiil: -ma, -ış, -mak & Kalıcı İsim Ayrımı)",
                    textOrActivity = "Metin: Görünmez Adamın Aynası (Ataol Behramoğlu) & LGS Çıkmış Soru Analizi",
                    methodsTechniques = "Örnek olay, ipucu temelli analiz, kodlama (MAYIŞMAK)",
                    toolsEquipment = "Ders kitabı, ÖDSGM LGS soru havuzu, mesuthayat.com LGS föyü",
                    evaluationMethod = "10 soruluk LGS fiilimsi mini denemesi",
                    specialDayOrNote = "LGS Hazırlık Başlangıcı"
                ),
                WeeklyPlanRow(
                    weekNumber = 2,
                    dateRange = "16 - 20 Eylül",
                    themeName = "1. Tema: Erdemler ve Hayatın Anlamı",
                    learningOutcomes = "T.8.3.9. Sıfat-fiil ve zarf-fiil eklerini kavrar. T.8.3.26. Söz sanatlarını (teşbih, teşhis, tezat) tespit eder.",
                    grammarTopic = "Sıfat-Fiil (ANASIMEZARDİKECEKMİŞ) vs Çekimli Fiil Ayrımı & Zarf-Fiiller",
                    textOrActivity = "Metin: Diyet (Ömer Seyfettin) - Metin tahlili ve fiilimsi tarama",
                    methodsTechniques = "Grup yarışması, akıllı tahta etkileşimli test",
                    toolsEquipment = "MEB Kazanım Testi 1, formül kartları",
                    evaluationMethod = "Kazanım Kavrama Testi 1",
                    specialDayOrNote = ""
                ),
                WeeklyPlanRow(
                    weekNumber = 3,
                    dateRange = "23 - 27 Eylül",
                    themeName = "1. Tema: Erdemler ve Hayatın Anlamı",
                    learningOutcomes = "T.8.3.17. Cümlenin ögelerini ayırt eder. T.8.3.15. Sözel mantık ve muhakeme sorularını çözer.",
                    grammarTopic = "Cümlenin Temel Ögeleri (Yüklem ve Özne) & Y-Ö-N-T Kuralı",
                    textOrActivity = "LGS Sözel Mantık Tablo Kurma Atölyesi (mesuthayat.com stratejileri)",
                    methodsTechniques = "Modelleme, tablo oluşturma, adım adım tümdengelim",
                    toolsEquipment = "Sözel mantık soru fasikülü",
                    evaluationMethod = "Sözel Mantık Tarama Testi",
                    specialDayOrNote = "26 Eylül Dil Bayramı"
                )
            )
        )
    )

    // =========================================================================
    // 3. GÜNLÜK DERS PLANLARI (40 DAKİKALIK ADIM ADIM MEB PLANI)
    // =========================================================================
    val dailyPlans: List<DailyLessonPlan> = listOf(
        DailyLessonPlan(
            id = "GUNLUK_5_1",
            grade = "5. Sınıf",
            title = "Kök, Ek ve Yapım Ekleri ile Kelime Türetme",
            themeName = "1. Tema: Birey ve Toplum",
            textTitle = "Memleket İsterim Şiiri",
            durationMinutes = 40,
            outcomes = listOf(
                "T.5.3.10. Kök ve ekleri ayırt eder, kelimelerin yapısını kavrar.",
                "T.5.3.3. Kelimelerin eş anlamlılarını metin bağlamında tespit eder."
            ),
            targetSkills = listOf("Çıkarım yapma", "Kelime hazinesini genişletme", "Analitik düşünme"),
            materialsUsed = listOf("Türkçe Ders Kitabı", "mesuthayat.com Kök-Ek Çalışma Föyü", "Renkli tahta kalemleri"),
            step1Introduction = "Öğretmen sınıfa girer, tahtaya 'GÖZ' kelimesini yazar. Ardından yanına sırasıyla '-lük', '-cü', '-süz' eklerini takarak anlamın nasıl bir mercekten bir mesleğe dönüştüğünü dramatize ederek sorar: 'Kelimenin kökü ile yeni anlamı arasında bir bağ var mıdır?' (6 dk)",
            step2Development = "Öğrenciler ders kitabındaki şiiri sesli okur. Şiirdeki 'kardeşlik', 'yaşamak', 'özlem' kelimeleri tahtaya yazılır. '-mek/-mak' testiyle kelimelerin isim kökü mü fiil kökü mü olduğu öğrencilerle birlikte keşfedilir. Kökün anlamlı en küçük parça olduğu vurgulanır (Balık-balıkçı ilişkisi incelenirken balta-balık tuzağı açıklanır). (20 dk)",
            step3Deepening = "Dağıtılan çalışma kağıdındaki 5 kelimelik 'Kök ve Ek Dedektifi' alıştırması öğrenciler tarafından ikili eşler halinde (akran dayanışması) çözülür. Öğretmen sıralar arasında dolaşarak geri bildirim verir. (10 dk)",
            step4Assessment = "Çıkış Bileti: Her öğrenci küçük bir kağıda bir isim kökü ve bir yapım eki kullanarak yeni bir kelime türetir ve kapıdan çıkarken öğretmene teslim eder. (4 dk)",
            differentiationBep = "Öğrenme güçlüğü çeken öğrenciler için kök ve ekleri farklı renkte yazılmış hazır kelime kartları verilir; sadece kökü daire içine almaları istenir.",
            homeworkExtension = "Evdeki nesnelerden 3 tanesini seçip kök ve eklerini deftere yazınız (Örn: Tuz-luk, askı-lık)."
        ),
        DailyLessonPlan(
            id = "GUNLUK_8_1",
            grade = "8. Sınıf",
            title = "LGS Fiilimsiler: İsim-Fiil ve Kalıcı İsim Ayrımı",
            themeName = "1. Tema: Erdemler",
            textTitle = "Görünmez Adamın Aynası & LGS Paragrafı",
            durationMinutes = 40,
            outcomes = listOf(
                "T.8.3.9. Fiilimsilerin cümledeki işlevlerini fark eder ve türlerini ayırt eder.",
                "T.8.3.14. LGS tarzı metin içi fiilimsi bulma sorularını çözer."
            ),
            targetSkills = listOf("Eleştirel düşünme", "Kural transferi", "Sınav stratejisi geliştirme"),
            materialsUsed = listOf("Akıllı Tahta", "mesuthayat.com 8. Sınıf Fiilimsiler Formül Föyü", "LGS Çıkmış Soru Bankası"),
            step1Introduction = "Tahtaya 'Annem çok lezzetli bir sarma yaptı.' ve 'Kâğıtları rulo yapıp sarma işini bana bıraktı.' cümleleri alt alta yazılır. 'Hangi sarma bir yiyecektir, hangisi eylemin adıdır?' sorusuyla tartışma başlatılır. (5 dk)",
            step2Development = "Fiilimsilerin özellikleri incelenir: 1) Fiilden türer, 2) Fiil anlamını tamamen kaybetmez, 3) Cümlede isim, sıfat veya zarf görevinde kullanılır, 4) Çekimli fiiller gibi şahıs ve kip eki alamaz. 'MAYIŞMAK' kodlaması tanıtılır. Kalıcı isim tuzakları (ekmek, çakmak, dolma, kavurma, giriş, çıkış) listelenir. (20 dk)",
            step3Deepening = "Son 3 yılın LGS çıkmış fiilimsi soruları akıllı tahtaya yansıtılır. Şıklardaki çeldiriciler (çekimli fiil vs fiilimsi) öğrencilerle analiz edilir. (10 dk)",
            step4Assessment = "3 soruluk mini tarama testi uygulanır; anında doğru yanıtlar tahtada gerekçeleriyle açıklanır. (5 dk)",
            differentiationBep = "Hedefe ulaşmakta zorlanan öğrencilere fiilimsi eklerinin yer aldığı formül kartı masa üzerinde serbest bırakılır.",
            homeworkExtension = "MEB 8. Sınıf Kazanım Kavrama Testi 1'in çözülmesi ve çözülemeyen soruların soru kutusuna atılması."
        )
    )

    // =========================================================================
    // HTML / PDF YAZDIRMA MOTORU (RESMÎ MEB YILLIK VE GÜNLÜK PLAN ŞABLONLARI)
    // =========================================================================

    /**
     * Resmî MEB Formatında Ünitelendirilmiş Yıllık Plan A4 HTML çıktısı üretir.
     */
    fun generateAnnualPlanHtml(
        plan: AnnualPlan,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni",
        principalName: String = "Okul Müdürü"
    ): String {
        val rowsHtml = plan.weeks.joinToString("\n") { w ->
            """
            <tr>
                <td style="text-align:center; font-weight:bold;">${w.weekNumber}</td>
                <td style="text-align:center;">${w.dateRange}</td>
                <td><b>${w.themeName}</b><br><small>${w.textOrActivity}</small></td>
                <td style="text-align:center;">${w.lessonHours}</td>
                <td style="font-size:8pt;">${w.learningOutcomes}</td>
                <td><b>${w.grammarTopic}</b></td>
                <td style="font-size:8pt;">${w.methodsTechniques}</td>
                <td style="font-size:8pt;">${w.toolsEquipment}</td>
                <td style="font-size:8pt;">${w.evaluationMethod}</td>
                <td style="font-size:8pt; color:#c53030;">${w.specialDayOrNote}</td>
            </tr>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${plan.title}</title>
            <style>
                @page { size: A4 landscape; margin: 10mm; }
                body { font-family: 'Times New Roman', serif; font-size: 8.5pt; color: #111; margin: 0; padding: 0; }
                .header { text-align: center; border-bottom: 2px solid #1a365d; padding-bottom: 4px; margin-bottom: 8px; }
                .header h1 { font-size: 11pt; margin: 0; text-transform: uppercase; color: #1a365d; }
                .header h2 { font-size: 10pt; margin: 2px 0; color: #222; }
                .meta-table { width: 100%; border-collapse: collapse; margin-bottom: 8px; font-size: 8pt; }
                .meta-table td { border: 1px solid #999; padding: 3px 5px; }
                .plan-table { width: 100%; border-collapse: collapse; font-size: 8pt; }
                .plan-table th, .plan-table td { border: 1px solid #666; padding: 3px 4px; vertical-align: top; }
                .plan-table th { background: #edf2f7; text-align: center; font-weight: bold; }
                .signature-box { margin-top: 15px; display: flex; justify-content: space-between; font-size: 8.5pt; }
                .sig-item { text-align: center; width: 220px; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>${plan.academicYear} EĞİTİM ÖĞRETİM YILI ${plan.grade.uppercase()} TÜRKÇE DERSİ</h2>
                <div style="font-size: 8.5pt;">ÜNİTELENDİRİLMİŞ YILLIK DERS PLANI</div>
            </div>

            <table class="meta-table">
                <tr>
                    <td style="width:25%;"><b>Dersin Adı:</b> Türkçe</td>
                    <td style="width:25%;"><b>Sınıf Düzeyi:</b> ${plan.grade}</td>
                    <td style="width:25%;"><b>Haftalık Ders Saati:</b> ${plan.weeklyHours} Saat</td>
                    <td style="width:25%;"><b>Toplam Ders Saati:</b> ${plan.totalHours} Saat</td>
                </tr>
                <tr>
                    <td colspan="4"><b>Dersin Genel Amaçları:</b> ${plan.generalObjectives}</td>
                </tr>
            </table>

            <table class="plan-table">
                <thead>
                    <tr>
                        <th style="width:3%;">Hf.</th>
                        <th style="width:7%;">Tarih</th>
                        <th style="width:16%;">Tema & Metin</th>
                        <th style="width:3%;">Saat</th>
                        <th style="width:22%;">Öğrenme Çıktıları & Kazanımlar</th>
                        <th style="width:12%;">Dilbilgisi / Konu</th>
                        <th style="width:12%;">Yöntem & Teknikler</th>
                        <th style="width:10%;">Araç & Gereç</th>
                        <th style="width:9%;">Ölçme & Değ.</th>
                        <th style="width:6%;">Belirli Gün</th>
                    </tr>
                </thead>
                <tbody>
                    $rowsHtml
                </tbody>
            </table>

            <div class="signature-box">
                <div class="sig-item">
                    <div>....../....../202...</div>
                    <div style="font-weight:bold; margin-top:4px;">$teacherName</div>
                    <div>Türkçe Dersi Öğretmeni</div>
                    <div style="margin-top:20px;">İmza</div>
                </div>
                <div class="sig-item">
                    <div><b>UYGUNDUR</b></div>
                    <div>....../....../202...</div>
                    <div style="font-weight:bold; margin-top:4px;">$principalName</div>
                    <div>Okul Müdürü</div>
                    <div style="margin-top:20px;">Mühür / İmza</div>
                </div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    /**
     * Resmî MEB Formatında 40 Dakikalık Günlük Ders Planı A4 HTML çıktısı üretir.
     */
    fun generateDailyPlanHtml(
        plan: DailyLessonPlan,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${plan.title} - Günlük Plan</title>
            <style>
                @page { size: A4 portrait; margin: 15mm; }
                body { font-family: 'Times New Roman', serif; font-size: 10pt; color: #111; line-height: 1.35; margin: 0; padding: 0; }
                .header { text-align: center; border-bottom: 2px solid #2b6cb0; padding-bottom: 6px; margin-bottom: 12px; }
                .header h1 { font-size: 12.5pt; margin: 0; text-transform: uppercase; color: #2b6cb0; }
                .header h2 { font-size: 11pt; margin: 3px 0; }
                .section-title { background: #ebf8ff; border-left: 4px solid #3182ce; padding: 4px 8px; font-weight: bold; font-size: 10pt; margin-top: 10px; margin-bottom: 6px; color: #2c5282; }
                .info-table { width: 100%; border-collapse: collapse; font-size: 9pt; margin-bottom: 8px; }
                .info-table td { border: 1px solid #cbd5e0; padding: 4px 6px; }
                .step-block { margin-bottom: 8px; padding-left: 6px; }
                .step-title { font-weight: bold; color: #2d3748; }
                .footer { margin-top: 25px; border-top: 1px solid #718096; padding-top: 6px; font-size: 8.5pt; display: flex; justify-content: space-between; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>TÜRKÇE DERSİ GÜNLÜK DERS PLANI</h2>
                <div style="font-size: 9pt; color: #4a5568;">Süre: ${plan.durationMinutes} Dakika (1 Ders Saati) • ${plan.grade}</div>
            </div>

            <div class="section-title">BÖLÜM I: DERS VE KONU BİLGİLERİ</div>
            <table class="info-table">
                <tr>
                    <td><b>Ders:</b> Türkçe</td>
                    <td><b>Sınıf:</b> ${plan.grade}</td>
                    <td><b>Süre:</b> ${plan.durationMinutes} Dakika</td>
                </tr>
                <tr>
                    <td><b>Tema:</b> ${plan.themeName}</td>
                    <td colspan="2"><b>İşlenen Metin / Konu:</b> ${plan.textTitle}</td>
                </tr>
                <tr>
                    <td colspan="3"><b>Öğretmen:</b> $teacherName</td>
                </tr>
            </table>

            <div class="section-title">BÖLÜM II: ÖĞRENME ÇIKTILARI, BECERİLER VE MATERYALLER</div>
            <div style="font-size: 9pt; margin-bottom: 6px;">
                <b>Kazanımlar / Öğrenme Çıktıları:</b>
                <ul>
                    ${plan.outcomes.joinToString("") { "<li>$it</li>" }}
                </ul>
                <b>Hedef Beceriler (Maarif Modeli):</b> ${plan.targetSkills.joinToString(", ")}<br>
                <b>Kullanılan Araç ve Materyaller:</b> ${plan.materialsUsed.joinToString(", ")}
            </div>

            <div class="section-title">BÖLÜM III: ÖĞRENME-ÖĞRETME SÜRECİ (40 DAKİKA)</div>
            <div class="step-block">
                <div class="step-title">1. Dikkat Çekme ve Merak Uyandırma (Giriş: ~5-8 dk):</div>
                <div style="font-size: 9pt;">${plan.step1Introduction}</div>
            </div>

            <div class="step-block">
                <div class="step-title">2. Keşfetme ve Metin/Konu İncelemesi (Gelişme: ~20-25 dk):</div>
                <div style="font-size: 9pt;">${plan.step2Development}</div>
            </div>

            <div class="step-block">
                <div class="step-title">3. Derinleştirme ve Uygulama (~10 dk):</div>
                <div style="font-size: 9pt;">${plan.step3Deepening}</div>
            </div>

            <div class="step-block">
                <div class="step-title">4. Özet, Değerlendirme ve Çıkış Bileti (~5 dk):</div>
                <div style="font-size: 9pt;">${plan.step4Assessment}</div>
            </div>

            <div class="section-title">BÖLÜM IV: FARKLILAŞTIRMA VE ÖDEV</div>
            <div style="font-size: 9pt;">
                <b>Bireyselleştirilmiş Eğitim (BEP) Uyarlaması:</b> ${plan.differentiationBep}<br>
                <b>Ödev ve Pekiştirme:</b> ${plan.homeworkExtension}
            </div>

            <div class="footer">
                <div>Türkçe Öğretmeni: <b>$teacherName</b></div>
                <div>Mesut Hayat & MEB Çerçeve Standartlarına Uygundur</div>
                <div>İmza: .............................</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
