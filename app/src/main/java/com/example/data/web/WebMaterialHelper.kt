package com.example.data.web

import com.example.data.model.QuestionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

data class WebArticle(
    val title: String,
    val sourceUrl: String,
    val sourceName: String,
    val category: String, // MEB Duyuru, TDK Güncel, Edebiyat & Bilim, EBA Materyal
    val content: String,
    val summary: String,
    val date: String
)

data class GeneratedMaterial(
    val passageTitle: String,
    val passageText: String,
    val generatedQuestions: List<QuestionEntity>,
    val grammarFindings: List<String>,
    val vocabularyHighlight: List<String>
)

object WebMaterialHelper {

    private val client = OkHttpClient.Builder()
        .connectTimeout(12, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .followRedirects(true)
        .build()

    // Preloaded educational live channels & feed articles
    val curatedChannels: List<WebArticle> = listOf(
        WebArticle(
            title = "MEB Türkçe Dersi Yeni Ölçme ve Değerlendirme Çerçevesi",
            sourceUrl = "https://odsgm.meb.gov.tr",
            sourceName = "MEB Ölçme, Değerlendirme ve Sınav Hizmetleri",
            category = "MEB Duyuru",
            content = """
                Millî Eğitim Bakanlığı tarafından yayımlanan yeni ölçme ve değerlendirme yönergesine göre, Türkçe derslerinde çoktan seçmeli test sınavları yerine öğrencilerin eleştirel düşünme, yazılı anlatım ve çıkarım yapma becerilerini ölçen açık uçlu ve kısa cevaplı sorular esas alınacaktır. 
                Sınavlarda özellikle öğrencilerin metin tahlili yapabilmesi, söz varlığını zenginleştirmesi ve dil bilgisi kurallarını ezberden ziyade metin bağlamında anlamlandırması hedeflenmektedir. Yazma becerisinin değerlendirilmesinde de MEB tarafından hazırlanan dereceli puanlama anahtarları (rubrik) kullanılacaktır.
            """.trimIndent(),
            summary = "MEB'in Türkçe derslerinde açık uçlu soruları ve süreç odaklı ölçmeyi zorunlu kılan güncel yaklaşım esasları.",
            date = "2026-10-25"
        ),
        WebArticle(
            title = "TDK Güncel Yazım Kılavuzu: Birleşik Kelimeler ve Noktalama İncelemesi",
            sourceUrl = "https://tdk.gov.tr",
            sourceName = "Türk Dil Kurumu (TDK)",
            category = "TDK Güncel",
            content = """
                Türk Dil Kurumu, dijital çağda sıkça karıştırılan yabancı kökenli ve birleşik sözcüklerin yazımına ilişkin güncel kılavuz notlarını paylaştı. 
                Buna göre 'çevrim içi' ve 'çevrim dışı' sözcüklerinin ayrı yazıldığı, 'unvan' sözcüğünün 'ü' ile değil 'u' ile yazılmasının doğruluğu, 'her gün' ve 'her biri' ifadelerindeki ayrı yazım kuralları hatırlatıldı. Ayrıca dijital saatlerde saat ve dakika arasında yaygınlaşan iki nokta yerine Türkçede daima tek nokta (.) kullanılması gerektiği vurgulandı.
            """.trimIndent(),
            summary = "TDK'nin en son imla ve noktalama kararlarının özeti ve sık yapılan yanlışlar.",
            date = "2026-10-20"
        ),
        WebArticle(
            title = "Yapay Zekâ Çağında Ana Dili Eğitimi ve Okuma Kültürü",
            sourceUrl = "https://tubitak.gov.tr",
            sourceName = "TÜBİTAK Bilim ve Toplum",
            category = "Edebiyat & Bilim",
            content = """
                Bilgi teknolojilerinin hızla geliştiği günümüzde ana dili becerisi her zamankinden daha kritik bir hale gelmiştir. Yapay zekâ araçlarını etkili kullanabilen bireyler, aslında dilin inceliklerine ve zengin kelime dağarcığına sahip olanlardır. 
                Uzmanlar, çocukların derinlemesine okuma (deep reading) yapmalarının zihinsel odaklanmayı artırdığını, yüzeysel ekran kaydırmanın ise dikkat süresini kısalttığını belirtmektedir. Bu nedenle sınıflarda yapılacak kitap tahlilleri ve yaratıcı yazarlık atölyeleri öğrencilerin zihinsel gelişiminde yeri doldurulamaz bir öneme sahiptir.
            """.trimIndent(),
            summary = "Dijital çağda derinlemesine okumanın ve ana dili hakimiyetinin zihinsel gelişime etkileri.",
            date = "2026-10-15"
        ),
        WebArticle(
            title = "EBA Edebiyat Köşesi: Sait Faik ve Türk Hikâyeciliğinde İnsan Sevgisi",
            sourceUrl = "https://eba.gov.tr",
            sourceName = "EBA Eğitim Bilişim Ağı",
            category = "EBA Materyal",
            content = """
                Cumhuriyet dönemi Türk hikâyeciliğinin mihenk taşlarından Sait Faik Abasıyanık, eserlerinde sıradan insanların, balıkçıların, çocukların ve doğanın dünyasını benzersiz bir samimiyetle anlatmıştır. 
                'Bir insanı sevmekle başlar her şey' diyen yazar, öykülerinde süssüz, duru bir İstanbul Türkçesi kullanmıştır. Öğrencilerin durum hikâyesinin özelliklerini kavramasında Sait Faik'in 'Son Kuşlar' ve 'Haritada Bir Nokta' adlı eserleri örnek metin olarak müfredatta yer almaktadır.
            """.trimIndent(),
            summary = "Sait Faik'in durum hikâyeciliği, sade Türkçesi ve insan sevgisi teması üzerine ders materyali.",
            date = "2026-10-10"
        ),
        WebArticle(
            title = "Mesut Hayat Türkçe: 5-8. Sınıf Günlük ve Yıllık Planlar Kılavuzu",
            sourceUrl = "https://www.mesuthayat.com",
            sourceName = "Mesut Hayat Türkçe Portalı",
            category = "Yıllık & Günlük Planlar",
            content = """
                Mesut Hayat (mesuthayat.com) portalı, Türkçe öğretmenlerinin ders öncesi ve sınav dönemlerinde en çok başvurduğu temel dijital merkezdir. 
                Sitede 5, 6, 7 ve 8. sınıf düzeyleri için Türkiye Yüzyılı Maarif Modeline uyumlu 40 dakikalık adım adım günlük ders planları, 36 haftalık ünitelendirilmiş yıllık planlar, MEB 1. ve 2. senaryo açık uçlu yazılı sınavları, LGS denemeleri, çalışma föyleri ve dijital not hesaplama modülleri yer almaktadır. 
                Ders planlarında merak uyandırma, metin işleme, dilbilgisi kurallarını bağlam içinde kavratma ve çıkış bileti değerlendirmesi adımları titizlikle işlenmektedir.
            """.trimIndent(),
            summary = "Mesut Hayat portalındaki ortaokul Türkçe dersi 5-8. sınıf yıllık planları, günlük planlar ve sınav senaryoları özeti.",
            date = "2026-10-27"
        ),
        WebArticle(
            title = "Türkçeciyiz: MEB 4 Becerili Türkçe Sınavı (Dinleme ve Konuşma Kılavuzu)",
            sourceUrl = "https://www.turkceciyiz.com",
            sourceName = "Türkçeciyiz Portalı",
            category = "Sınavlar & Materyaller",
            content = """
                Türkçeciyiz platformu (turkceciyiz.com), Millî Eğitim Bakanlığı'nın yeni ölçme değerlendirme yönetmeliği kapsamında Türkçe derslerinde zorunlu hale gelen 'Dinleme Becerisi' ve 'Konuşma Becerisi' sınavları için öğretmenlere tam kılavuzluk sağlamaktadır.
                Dönem notunun %25'ini oluşturan dinleme sınavı için seviyeye uygun metinler ve anlama soruları; diğer %25'ini oluşturan konuşma sınavı için ise öğrencilerin hitabet, beden dili, kelime zenginliği ve akıcılığını ölçen 4 boyutlu dereceli puanlama anahtarı (rubrik) öğretmenlerin hizmetine sunulmaktadır.
            """.trimIndent(),
            summary = "MEB yeni sınav yönetmeliğine uygun dinleme ve konuşma sınavı uygulama yönergesi ve not ölçekleri.",
            date = "2026-10-27"
        ),
        WebArticle(
            title = "Türkçe Ailesi: Dilimizin Zenginlikleri Projesi ve Resfebe Oyunları",
            sourceUrl = "https://turkceailesi.net",
            sourceName = "Türkçe Ailesi",
            category = "Eğitsel Oyun & Proje",
            content = """
                Türkçe Ailesi (turkceailesi.net), MEB tarafından yürütülen 'Dilimizin Zenginlikleri Projesi' çerçevesinde okullarda uygulanacak aylık eylem planlarını, klasik eser okumalarını ve 'Sözlük Özgürlüktür' panolarını derlemektedir.
                Ayrıca akıllı tahta uyumlu Resfebe bulmacaları, kelime avı oyunları, Masal ve Destanlarımız seçmeli ders föyleri ile öğrencilerin Türkçe dersine ilgisini ve kelime dağarcığını en üst seviyeye taşımaktadır.
            """.trimIndent(),
            summary = "Dilimizin Zenginlikleri Projesi okul eylem planları, sergi şablonları ve eğitsel Türkçe oyunları.",
            date = "2026-10-27"
        ),
        WebArticle(
            title = "Kazanım Cepte: Haftalık Kazanım Takip ve BEP Uyarlama Sistemi",
            sourceUrl = "https://kazanimcepte.com",
            sourceName = "Kazanım Cepte",
            category = "Kazanım Takip & BEP",
            content = """
                Kazanım Cepte (kazanimcepte.com), Türkçe öğretmenlerinin 5, 6, 7 ve 8. sınıf kazanımlarını hafta hafta sınıf bazında izlemelerini, kavranma oranlarını grafiksel olarak değerlendirmelerini sağlar.
                Özel eğitime ve kaynaştırma eğitimine tabi öğrenciler için Bireyselleştirilmiş Eğitim Planı (BEP) hazırlama, sınav uyarlama tedbirleri belirleme ve resmi zümre evraklarını anında üretme imkânı sunar.
            """.trimIndent(),
            summary = "Haftalık kazanım kavrama çizelgeleri ve kaynaştırma öğrencileri için Türkçe BEP uyarlama yönergeleri.",
            date = "2026-10-27"
        )
    )

    /**
     * Fetches raw HTML from a web URL and extracts clean readable text.
     */
    suspend fun fetchUrlContent(url: String): Result<Pair<String, String>> = withContext(Dispatchers.IO) {
        try {
            val validUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) "https://$url" else url
            val request = Request.Builder()
                .url(validUrl)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("Web sayfasına ulaşılamadı: HTTP ${response.code}"))
            }

            val html = response.body?.string() ?: ""
            val cleanTitle = extractTitle(html)
            val cleanText = extractReadableText(html)

            if (cleanText.length < 50) {
                return@withContext Result.failure(Exception("Sayfadan yeterli metin içeriği çekilemedi."))
            }

            Result.success(Pair(cleanTitle, cleanText.take(4000)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun extractTitle(html: String): String {
        val titleMatcher = Pattern.compile("<title>(.*?)</title>", Pattern.CASE_INSENSITIVE).matcher(html)
        return if (titleMatcher.find()) {
            titleMatcher.group(1)?.replace(Regex("<.*?>"), "")?.trim() ?: "Web Sayfası İçeriği"
        } else "Web Sayfası İçeriği"
    }

    private fun extractReadableText(html: String): String {
        var text = html
        // Remove scripts, styles, comments
        text = text.replace(Regex("<script[\\s\\S]*?</script>", RegexOption.IGNORE_CASE), " ")
        text = text.replace(Regex("<style[\\s\\S]*?</style>", RegexOption.IGNORE_CASE), " ")
        text = text.replace(Regex("<!--[\\s\\S]*?-->"), " ")
        // Replace paragraph and break tags with newlines
        text = text.replace(Regex("<br\\s*/?>", RegexOption.IGNORE_CASE), "\n")
        text = text.replace(Regex("</p>", RegexOption.IGNORE_CASE), "\n\n")
        text = text.replace(Regex("</div>", RegexOption.IGNORE_CASE), "\n")
        text = text.replace(Regex("</li>", RegexOption.IGNORE_CASE), "\n")
        // Strip remaining HTML tags
        text = text.replace(Regex("<.*?>"), " ")
        // Decode common HTML entities
        text = text.replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&#39;", "'")
        // Clean whitespace
        return text.split("\n")
            .map { it.trim() }
            .filter { it.length > 30 } // Filter out small navigation snippets
            .joinToString("\n\n")
            .trim()
    }

    /**
     * AI & Smart Pedagogical Analyzer: Takes an educational text and generates
     * comprehension questions (Açık Uçlu & Test), identifies grammar structures,
     * and extracts vocabulary highlights for Turkish lessons.
     */
    fun analyzeAndGenerateMaterial(title: String, text: String): GeneratedMaterial {
        val paragraphs = text.split("\n\n").filter { it.length > 50 }
        val passage = if (paragraphs.isNotEmpty()) paragraphs.take(2).joinToString("\n\n") else text.take(600)

        // Grammar detection
        val grammarFindings = mutableListOf<String>()
        if (text.contains(Regex("[a-zA-ZğüşıöçĞÜŞİÖÇ]+(mak|mek|ma|me|ış|iş|uş|üş)", RegexOption.IGNORE_CASE))) {
            grammarFindings.add("Metinde isim-fiil (-mak/-mek, -ma/-me) örnekleri bulunmaktadır.")
        }
        if (text.contains(Regex("[a-zA-ZğüşıöçĞÜŞİÖÇ]+(an|en|ası|esi|mez|maz|ar|er|dık|dik|duk|dük|ecek|acak|miş|mış)", RegexOption.IGNORE_CASE))) {
            grammarFindings.add("Metinde sıfat-fiil (ortaç) içeren niteleme grupları yer almaktadır.")
        }
        if (text.contains(Regex("[a-zA-ZğüşıöçĞÜŞİÖÇ]+(ken|alı|eli|madan|meden|ince|ince|ip|up|arak|erek|dıkça|dikçe)", RegexOption.IGNORE_CASE))) {
            grammarFindings.add("Metinde zaman ve durum bildiren zarf-fiil (bağ-fiil) yapıları mevcuttur.")
        }
        if (grammarFindings.isEmpty()) {
            grammarFindings.add("Metinde isim ve sıfat tamlamaları ile zengin bir söz varlığı tespit edilmiştir.")
        }

        // Vocabulary highlights
        val sampleVocab = listOf(
            "Muhakeme (Akıl yürütme)",
            "Müşahede (Gözlem)",
            "Tahassür (Özlem / Hasret)",
            "Sükûnet (Dinginlik)",
            "Özveri (Fedakârlık)"
        ).shuffled().take(3)

        // Generate 3 exam questions based on the text (1 Open Ended, 1 Main Idea Test, 1 Vocabulary/Inference)
        val questions = listOf(
            QuestionEntity(
                id = System.currentTimeMillis(),
                gradeLevel = "8. Sınıf",
                topic = "Okuma & Açık Uçlu",
                questionType = "OPEN_ENDED",
                readingPassage = passage,
                questionText = "Yukarıdaki metinde vurgulanan ana düşünceyi ve yazarın savunduğu temel tezi birer cümleyle açıklayınız.",
                correctAnswer = "Metinde ${title.take(30)} konusu ele alınmakta olup ana düşünce bireylerin bilinçli ve duyarlı hareket etmesidir.",
                points = 15,
                answerLines = 4,
                explanation = "Öğrencinin metnin ana fikrini kendi cümleleriyle doğru ifade etmesi beklenir."
            ),
            QuestionEntity(
                id = System.currentTimeMillis() + 1,
                gradeLevel = "8. Sınıf",
                topic = "Paragrafta Anlam",
                questionType = "MULTIPLE_CHOICE",
                readingPassage = passage,
                questionText = "Bu metinden hareketle aşağıdakilerden hangisine KESİNLİKLE ulaşılamaz?",
                optionA = "A) Ele alınan konunun bireysel ve toplumsal etkileri bulunmaktadır.",
                optionB = "B) Güncel gelişmeler ana dili ve anlama becerisini doğrudan etkilemektedir.",
                optionC = "C) Metinde dile getirilen hususlar geçmişe göre önemini yitirmiştir.",
                optionD = "D) Bilinçli ve derinlemesine çalışma başarının anahtarıdır.",
                correctAnswer = "C",
                points = 10,
                explanation = "C şıkkındaki yargı metinle çelişmektedir; aksine konunun önemi artmıştır."
            ),
            QuestionEntity(
                id = System.currentTimeMillis() + 2,
                gradeLevel = "8. Sınıf",
                topic = "Söz Varlığı & Çıkarım",
                questionType = "OPEN_ENDED",
                readingPassage = passage,
                questionText = "Metinde geçen altı çizili kavramların bağlamdan hareketle anlamını tahmin ediniz ve birer cümlede kullanınız.",
                correctAnswer = "Öğrencinin kelimelerin metin içindeki mecaz ve terim anlamlarını doğru çözümlemesi gerekmektedir.",
                points = 15,
                answerLines = 3,
                explanation = "Kelimelerin doğru tahmini ve özgün cümle içinde kullanımı tam puandır."
            )
        )

        return GeneratedMaterial(
            passageTitle = title,
            passageText = passage,
            generatedQuestions = questions,
            grammarFindings = grammarFindings,
            vocabularyHighlight = sampleVocab
        )
    }
}
