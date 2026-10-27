package com.example.data.curriculum

import com.example.data.model.LessonPlanEntity
import com.example.data.model.ReadingRecordEntity

data class CurriculumOutcome(
    val code: String, // örn: T.8.3.1
    val grade: String, // 5. Sınıf, 6. Sınıf, 7. Sınıf, 8. Sınıf
    val domain: String, // Okuma, Yazma, Dinleme, Konuşma
    val description: String
)

data class BookItem(
    val title: String,
    val author: String,
    val recommendedGrade: String,
    val pages: Int
)

object MebCurriculumRepository {

    val outcomes: List<CurriculumOutcome> = listOf(
        // 8. Sınıf (LGS Odaklı)
        CurriculumOutcome("T.8.3.1", "8. Sınıf", "Okuma", "Noktalama işaretlerine dikkat ederek sesli ve sessiz okur."),
        CurriculumOutcome("T.8.3.6", "8. Sınıf", "Okuma", "Deyim, atasözü ve özdeyişlerin metne katkısını belirler."),
        CurriculumOutcome("T.8.3.9", "8. Sınıf", "Fiilimsi", "Fiilimsilerin cümledeki işlevlerini fark eder ve türlerini ayırt eder."),
        CurriculumOutcome("T.8.3.14", "8. Sınıf", "Okuma", "Metinle ilgili soruları cevaplar ve metindeki ana fikri tespit eder."),
        CurriculumOutcome("T.8.3.17", "8. Sınıf", "Cümle Ögeleri", "Cümlenin ögelerini ayırt eder ve vurgulanan ögeyi bulur."),
        CurriculumOutcome("T.8.3.18", "8. Sınıf", "Okuma", "Cümle türlerini (basit, birleşik, sıralı, bağlı) tanır."),
        CurriculumOutcome("T.8.3.26", "8. Sınıf", "Söz Sanatları", "Metindeki söz sanatlarını (benzetme, kişileştirme, tezat, abartma) tespit eder."),
        CurriculumOutcome("T.8.4.16", "8. Sınıf", "Yazma", "Yazdıklarını imla ve noktalama kuralları bakımından kontrol eder ve düzeltir."),

        // 7. Sınıf
        CurriculumOutcome("T.7.3.5", "7. Sınıf", "Okuma", "Bağlamdan yararlanarak bilmediği kelime ve kelime gruplarının anlamını tahmin eder."),
        CurriculumOutcome("T.7.3.10", "7. Sınıf", "Fiiller", "Fiillerin anlam özelliklerini (iş, oluş, durum) kavrar."),
        CurriculumOutcome("T.7.3.11", "7. Sınıf", "Fiiller", "Fiillerin kiplerini (haber ve dilek kipleri) ve şahıs eklerini açıklar."),
        CurriculumOutcome("T.7.3.12", "7. Sınıf", "Ek Fiil", "Ek fiili işlevlerine uygun şekilde cümle içinde kullanır."),
        CurriculumOutcome("T.7.3.13", "7. Sınıf", "Zarflar", "Zarfların cümledeki işlevlerini ve türlerini ayırt eder."),
        CurriculumOutcome("T.7.4.2", "7. Sınıf", "Yazma", "Bilgilendirici ve hikâye edici metinler yazar."),

        // 6. Sınıf
        CurriculumOutcome("T.6.3.8", "6. Sınıf", "İsim & Sıfat", "İsim ve sıfat tamlamalarının metnin anlamına katkısını açıklar."),
        CurriculumOutcome("T.6.3.9", "6. Sınıf", "Zamirler", "Zamirlerin cümledeki işlevlerini belirler ve çeşitlerini tanır."),
        CurriculumOutcome("T.6.3.10", "6. Sınıf", "Edat & Bağlaç", "Edat, bağlaç ve ünlemlerin metne kattığı anlamları değerlendirir."),
        CurriculumOutcome("T.6.3.16", "6. Sınıf", "Metin Türleri", "Metin türlerini (anı, mektup, tiyatro, fabl) ayırt eder."),

        // 5. Sınıf
        CurriculumOutcome("T.5.3.3", "5. Sınıf", "Kelime Bilgisi", "Kelimelerin eş, zıt anlamlılarını ve sesteşlerini bulur."),
        CurriculumOutcome("T.5.3.4", "5. Sınıf", "Kelime Bilgisi", "Kelimelerin gerçek, mecaz ve terim anlamlarını ayırt eder."),
        CurriculumOutcome("T.5.3.11", "5. Sınıf", "Ses Olayları", "Büyük ünlü uyumu ve ünsüz benzeşmesi/yumuşaması kurallarını kavrar."),
        CurriculumOutcome("T.5.4.5", "5. Sınıf", "Noktalama", "Büyük harfleri ve noktalama işaretlerini uygun yerlerde kullanır.")
    )

    val sampleLessonPlans: List<LessonPlanEntity> = listOf(
        LessonPlanEntity(
            id = 1,
            title = "Fiilimsiler ve Cümlede Anlam",
            gradeLevel = "8. Sınıf",
            subjectArea = "Dil Bilgisi / Okuma",
            learningOutcomes = "T.8.3.9. Fiilimsilerin cümledeki işlevlerini fark eder. T.8.4.16. İmla ve noktalama kontrolü yapar.",
            durationMinutes = 40,
            activities = "1. Giriş (10 dk): Tahtaya 'MAYIŞMAK' ve 'ANASIMEZARDİKECEKMİŞ' kodlamaları yazılarak öğrencilerin merakı uyandırılır.\n2. Keşif (15 dk): Dağıtılan paragraftaki fiilimsilerin altı öğrenciler tarafından çizilir, kalıcı ad olan 'dondurma, ekmek' gibi tuzaklar tartışılır.\n3. Pekiştirme (15 dk): Akıllı tahta üzerinden 5 soruluk mini yarışma.",
            homework = "Ders kitabı sayfa 45-48 arasındaki Fiilimsiler testini çözünüz ve 5 adet sıfat-fiil içeren kompozisyon taslağı oluşturunuz.",
            notes = "8/A sınıfı isim-fiil ve sıfat-fiil ayrımında başarılı; zarf-fiil ekleri haftaya tekrar pekiştirilecek.",
            createdAt = System.currentTimeMillis() - 86400000L
        ),
        LessonPlanEntity(
            id = 2,
            title = "Mecaz Anlam ve Deyimlerin Gücü",
            gradeLevel = "6. Sınıf",
            subjectArea = "Sözcükte Anlam",
            learningOutcomes = "T.6.3.6. Deyim ve atasözlerinin metne katkısını belirler. T.6.3.8. Sıfat tamlamalarını kullanır.",
            durationMinutes = 40,
            activities = "1. Isınma: 'Etekleri zil çalmak' ve 'Burnu havada olmak' deyimleri pandomim (sessiz sinema) ile canlandırılır.\n2. Uygulama: Öğrencilere deyim kartları dağıtılır ve eş anlamları bulunur.\n3. Yazma: Seçilen üç deyimi içeren kısa bir fıkra yazılır.",
            homework = "Evde aile büyüklerinden en az 3 yöresel deyim öğrenip deftere anlamıyla kaydediniz.",
            notes = "Deyim canlandırma drama etkinliği çok ilgi gördü, 6/B sınıfında da uygulanacak.",
            createdAt = System.currentTimeMillis() - 172800000L
        )
    )

    val sampleReadingRecords: List<ReadingRecordEntity> = listOf(
        ReadingRecordEntity(
            id = 1,
            studentName = "Zeynep Kaya",
            gradeSection = "8/A",
            bookTitle = "Küçük Prens",
            author = "Antoine de Saint-Exupéry",
            pageCount = 112,
            status = "Özet Değerlendirildi",
            teacherScore = 95,
            teacherComment = "Harika bir kitap tahlili hazırlamış. Sevgi ve sorumluluk temalarını çok olgun bir dille ele almış.",
            dateAdded = System.currentTimeMillis() - 259200000L
        ),
        ReadingRecordEntity(
            id = 2,
            studentName = "Emirhan Demir",
            gradeSection = "8/A",
            bookTitle = "Çalıkuşu",
            author = "Reşat Nuri Güntekin",
            pageCount = 408,
            status = "Okunuyor",
            teacherScore = 85,
            teacherComment = "Feride karakterinin fedakarlığını ve dönemin Anadolu şartlarını çok iyi takip ediyor. 250. sayfada.",
            dateAdded = System.currentTimeMillis() - 86400000L
        ),
        ReadingRecordEntity(
            id = 3,
            studentName = "Elif Sena Yıldız",
            gradeSection = "7/B",
            bookTitle = "Yalnız Efe",
            author = "Ömer Seyfettin",
            pageCount = 120,
            status = "Tamamlandı",
            teacherScore = 90,
            teacherComment = "Ömer Seyfettin'in sade ve akıcı Türkçesini çok beğenmiş. Sınıf önünde 5 dakikalık sunum yaptı.",
            dateAdded = System.currentTimeMillis() - 400000000L
        )
    )

    val recommendedBooks: List<BookItem> = listOf(
        BookItem("Küçük Prens", "Antoine de Saint-Exupéry", "5-8. Sınıf", 112),
        BookItem("Çalıkuşu", "Reşat Nuri Güntekin", "7-8. Sınıf", 408),
        BookItem("Yalnız Efe", "Ömer Seyfettin", "6-8. Sınıf", 120),
        BookItem("Kutadgu Bilig (Seçmeler)", "Yusuf Has Hacib", "8. Sınıf / Lise", 180),
        BookItem("Dede Korkut Hikâyeleri", "Anonim", "5-7. Sınıf", 160),
        BookItem("Gulliver'in Gezileri", "Jonathan Swift", "5-6. Sınıf", 140),
        BookItem("Beyaz Diş", "Jack London", "6-8. Sınıf", 240),
        BookItem("Şeker Portakalı", "José Mauro de Vasconcelos", "7-8. Sınıf", 184),
        BookItem("Nutuk (Gençler İçin)", "Mustafa Kemal Atatürk", "8. Sınıf / Lise", 320),
        BookItem("Memleket Hikâyeleri", "Refik Halit Karay", "8. Sınıf / Lise", 210)
    )
}
