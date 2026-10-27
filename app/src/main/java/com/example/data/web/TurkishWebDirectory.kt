package com.example.data.web

data class WebResource(
    val id: String,
    val name: String,
    val category: String, // TDK & Sözlükler, MEB & Sınavlar, Edebiyat & Şiir, Ders Materyali, Bilim & Kültür
    val url: String,
    val description: String,
    val tags: List<String>,
    val highlights: String,
    val officialBadge: String = "Resmî Kaynak",
    val samplePassage: String? = null
)

object TurkishWebDirectory {

    val resources: List<WebResource> = listOf(
        // --- 1. TDK VE SÖZLÜKLER ---
        WebResource(
            id = "tdk_sozluk",
            name = "TDK Güncel Türkçe Sözlük",
            category = "TDK & Sözlükler",
            url = "https://sozluk.gov.tr",
            description = "Türk Dil Kurumu'nun resmî sözlüğü; kelime anlamları, köken bilgileri, doğru telaffuz ve atasözü-deyim sorgulaması.",
            tags = listOf("TDK", "sözlük", "kelime", "anlam", "atasözü", "deyim", "köken"),
            highlights = "100.000+ kelime, sesli telaffuz ve anlık TDK arama motoru",
            officialBadge = "T.C. Resmî",
            samplePassage = "Türk Dil Kurumu Güncel Türkçe Sözlük verilerine göre Türkçede kökeni, birleşik yapısı ve mecaz anlamlarıyla zengin bir söz varlığı bulunmaktadır. Sözlükte her kelimenin cümle içi örnekleri ve türleri belirtilmektedir."
        ),
        WebResource(
            id = "tdk_yazim",
            name = "TDK Yazım Kuralları ve İmla Kılavuzu",
            category = "TDK & Sözlükler",
            url = "https://tdk.gov.tr/icerik/yazim-kurallari",
            description = "Büyük harflerin yazımı, birleşik kelimeler, de/da, ki ve mi'nin yazımı, noktalama işaretleri resmî kılavuzu.",
            tags = listOf("imla", "yazım", "noktalama", "büyük harf", "birleşik sözcükler", "TDK"),
            highlights = "En güncel TDK yazım kararları ve noktalama kuralları",
            officialBadge = "T.C. Resmî"
        ),
        WebResource(
            id = "kubbealti_lugat",
            name = "Kubbealtı Lugatı (Aslî ve Edebî Türkçe)",
            category = "TDK & Sözlükler",
            url = "https://lugatim.com",
            description = "İlhan Ayverdi'nin hazırladığı, klasik ve çağdaş edebiyat metinlerinden örnek cümlelerle Türkçenin en zengin edebiyat sözlüğü.",
            tags = listOf("edebiyat sözlüğü", "köken", "osmanlıca", "klasik türkçe", "kubbealtı"),
            highlights = "13 asırlık Türk edebiyatından zengin edebi alıntılar",
            officialBadge = "Edebî Kaynak"
        ),
        WebResource(
            id = "nisanyan_etimoloji",
            name = "Nişanyan Etimoloji Sözlüğü",
            category = "TDK & Sözlükler",
            url = "https://nisanyansozluk.com",
            description = "Çağdaş Türkçenin etimolojik kökenlerini, tarihsel ilk yazılı kayıtlarını ve diller arası geçişlerini inceleyen sözlük.",
            tags = listOf("etimoloji", "köken", "tarihsel türkçe", "dilbilim"),
            highlights = "Kelimelerin Türkçedeki ilk yazılı kayıtları ve kök tahlilleri",
            officialBadge = "Akademik"
        ),

        // --- 2. MEB VE ORTAOKUL PORTALLARI ---
        WebResource(
            id = "turkceciyiz_com",
            name = "Türkçeciyiz Portalı (turkceciyiz.com)",
            category = "Ders Materyali",
            url = "https://www.turkceciyiz.com",
            description = "Türkiye'nin en köklü Türkçe öğretmen paylaşım platformu. 5, 6, 7 ve 8. sınıf yaprak testleri, LGS denemeleri, MEB dinleme ve konuşma sınavı materyalleri, tiyatro piyesleri ve zümre evrakları.",
            tags = listOf("türkçeciyiz", "5. sınıf", "6. sınıf", "7. sınıf", "8. sınıf", "LGS", "dinleme sınavı", "konuşma sınavı", "yaprak test", "bursluluk"),
            highlights = "On binlerce Türkçe öğretmeni tarafından paylaşılan zengin arşiv ve 4 becerili sınav evrakları",
            officialBadge = "Öğretmen Topluluğu",
            samplePassage = "Türkçeciyiz portalı; 5, 6, 7 ve 8. sınıf düzeylerinde dilbilgisi etkinlikleri, MEB'in yeni ölçme yönetmeliğine uygun dinleme ve konuşma sınavı yönergeleri ile LGS soru havuzunu tek çatı altında toplamaktadır."
        ),
        WebResource(
            id = "turkceailesi_net",
            name = "Türkçe Ailesi (turkceailesi.net)",
            category = "Ders Materyali",
            url = "https://turkceailesi.net",
            description = "Maarif Modeli uyumlu fasiküller, Dilimizin Zenginlikleri Projesi evrakları, Resfebe ve Sözcük Avı eğitsel oyunları, seçmeli ders föyleri (Masal ve Destanlarımız, Kültür ve Medeniyetimiz).",
            tags = listOf("türkçe ailesi", "dilimizin zenginlikleri", "resfebe", "eğitsel oyunlar", "seçmeli ders", "maarif modeli", "fasikül"),
            highlights = "Dilimizin Zenginlikleri aylık eylem planları ve akıllı tahta uyumlu interaktif oyunlar",
            officialBadge = "Eğitim Ağı",
            samplePassage = "Türkçe Ailesi; öğrencilerin dil sevgisini geliştirmek amacıyla Resfebe oyunları, deyim canlandırma etkinlikleri, Masal ve Destanlarımız seçmeli ders föyleri ve MEB Dilimizin Zenginlikleri Projesi eylem planlarını sunmaktadır."
        ),
        WebResource(
            id = "kazanimcepte_com",
            name = "Kazanım Cepte (kazanimcepte.com)",
            category = "MEB & Sınavlar",
            url = "https://kazanimcepte.com",
            description = "Haftalık kazanım kavrama ve takip çizelgeleri, BEP (Bireyselleştirilmiş Eğitim Planı) Türkçe uyarlama araçları, ŞÖK raporları ve zümre evrakları.",
            tags = listOf("kazanım cepte", "kazanım takip", "BEP planı", "yıllık plan", "öğrenci takip", "ölçme değerlendirme"),
            highlights = "Kazanım bazlı haftalık gelişim çizelgeleri ve kaynaştırma öğrencileri için BEP formları",
            officialBadge = "Öğretmen Asistanı",
            samplePassage = "Kazanım Cepte platformu, Türkçe öğretmenlerinin sınıflarında hangi kazanımın ne düzeyde kavrandığını haftalık olarak takip etmesini, BEP'li öğrenciler için sınav tedbirleri geliştirmesini sağlayan pratik bir sistemdir."
        ),
        WebResource(
            id = "mesuthayat_com",
            name = "Mesut Hayat Türkçe Portalı (mesuthayat.com)",
            category = "Ders Materyali",
            url = "https://www.mesuthayat.com",
            description = "5, 6, 7 ve 8. sınıf Türkçe dersi yıllık planları, 40 dakikalık günlük ders planları, MEB açık uçlu ortak yazılı sınav senaryoları, LGS denemeleri ve çalışma kağıtları.",
            tags = listOf("mesut hayat", "yıllık plan", "günlük plan", "5. sınıf", "6. sınıf", "7. sınıf", "8. sınıf", "LGS", "yazılı sınav", "çalışma kağıdı"),
            highlights = "Ortaokul Türkçe öğretmenlerinin en çok kullandığı güncel plan ve sınav arşivi",
            officialBadge = "Öğretmen Arşivi",
            samplePassage = "Mesut Hayat portalında yer alan 5, 6, 7 ve 8. sınıf Türkçe dersi yıllık planları; MEB çalışma takvimine, Maarif Modeli çerçevesine ve temalara uygun olarak haftalık 5-6 saatlik bloklar halinde hazırlanmıştır. Her ünitede okuma, dinleme, konuşma ve yazma etkinlikleri ile dilbilgisi kazanımları birbiriyle bütünleşik olarak ele alınmaktadır."
        ),
        WebResource(
            id = "meb_odsgm",
            name = "MEB Ölçme, Değerlendirme ve Sınav Hizmetleri (ÖDSGM)",
            category = "MEB & Sınavlar",
            url = "https://odsgm.meb.gov.tr",
            description = "MEB Ortak Yazılı Sınav örnek soruları, LGS çıkmış soruları, beceri temelli sorular ve kazanım kavrama testleri.",
            tags = listOf("MEB", "LGS", "ortak sınav", "açık uçlu sorular", "örnek sorular", "kazanım testleri"),
            highlights = "Yeni müfredat ortak sınav soru şablonları ve LGS soru havuzu",
            officialBadge = "MEB Resmî",
            samplePassage = "ÖDSGM tarafından yayımlanan beceri temelli Türkçe soruları; okuduğunu anlama, görsel ve grafik okuma, metinler arası karşılaştırma ve analitik çıkarım becerilerini merkeze almaktadır."
        ),
        WebResource(
            id = "meb_eba",
            name = "EBA - Eğitim Bilişim Ağı Türkçe Portalı",
            category = "MEB & Sınavlar",
            url = "https://eba.gov.tr",
            description = "MEB'in dijital eğitim platformu; 5-8. sınıf ve lise Türkçe ders videoları, etkileşimli içerikler, çalışma yaprakları.",
            tags = listOf("EBA", "ders videosu", "etkileşimli içerik", "ödev", "çalışma föyü"),
            highlights = "Tüm sınıf düzeylerinde binlerce dijital ders materyali",
            officialBadge = "MEB Resmî"
        ),
        WebResource(
            id = "meb_ogm_materyal",
            name = "MEB OGM Materyal Soru Bankası ve Etkileşimli Kitaplar",
            category = "MEB & Sınavlar",
            url = "https://ogmmateryal.eba.gov.tr",
            description = "Ortaöğretim ve ortaokul kademelerine yönelik interaktif soru bankaları, e-kitaplar ve 3D etkinlikler.",
            tags = listOf("OGM", "soru bankası", "e-kitap", "konu özeti", "deneme sınavı"),
            highlights = "Kazanım bazlı konu özetleri ve PDF ders kitapları",
            officialBadge = "MEB Resmî"
        ),
        WebResource(
            id = "meb_mufredat",
            name = "MEB Talim ve Terbiye Kurulu Müfredat Portalı",
            category = "MEB & Sınavlar",
            url = "https://mufredat.meb.gov.tr",
            description = "Türkiye Yüzyılı Maarif Modeli ve güncel Türkçe Dersi Öğretim Programı, ünite kazanımları ve haftalık dağılımlar.",
            tags = listOf("müfredat", "kazanım", "öğretim programı", "maarif modeli", "talim terbiye"),
            highlights = "Resmî yıllık plan kazanım kodları ve yönergeler",
            officialBadge = "MEB Resmî"
        ),

        // --- 3. EDEBİYAT, ŞİİR VE METİN ARŞİVLERİ ---
        WebResource(
            id = "teis_yesevi",
            name = "Türk Edebiyatı İsimler Sözlüğü (TEİS)",
            category = "Edebiyat & Şiir",
            url = "https://teis.yesevi.edu.tr",
            description = "Ahmet Yesevi Üniversitesi tarafından hazırlanan, başlangıçtan günümüze tüm Türk şair, yazar ve edebiyatçıların ansiklopedisi.",
            tags = listOf("şairler", "yazarlar", "biyografi", "edebiyat tarihi", "ahmet yesevi"),
            highlights = "15.000+ Türk şair ve yazarının ayrıntılı hayatı ve eserleri",
            officialBadge = "Üniversite"
        ),
        WebResource(
            id = "kultur_portali",
            name = "T.C. Kültür Portalı (Halk Edebiyatı ve Masallar)",
            category = "Edebiyat & Şiir",
            url = "https://kulturportali.gov.tr",
            description = "Kültür ve Turizm Bakanlığı arşivinden Türk masalları, destanları, efsaneleri, halk hikâyeleri ve geleneksel tiyatro.",
            tags = listOf("masal", "destan", "halk edebiyatı", "efsane", "karagöz", "ortaoyunu"),
            highlights = "Derslerde okutulabilecek özgün Türk masalları ve halk hikâyeleri",
            officialBadge = "Bakanlık"
        ),
        WebResource(
            id = "antoloji_siir",
            name = "Antoloji - Türk Şiir ve Edebiyat Arşivi",
            category = "Edebiyat & Şiir",
            url = "https://antoloji.com",
            description = "Yunus Emre'den Mehmet Akif'e, Yahya Kemal'den günümüz şairlerine on binlerce Türkçe şiir ve şair biyografileri.",
            tags = listOf("şiir", "şairler", "antoloji", "güzel okuma", "dizeler"),
            highlights = "Belirli gün ve haftalarda okunacak seçme şiir koleksiyonu",
            officialBadge = "Kültür Sanat"
        ),
        WebResource(
            id = "dil_dernegi",
            name = "Dil Derneği Portalı",
            category = "Edebiyat & Şiir",
            url = "https://dildernegi.org.tr",
            description = "Türkçenin korunması, zenginleştirilmesi, yabancı sözcüklerin Türkçe karşılıkları ve dil devrimi makaleleri.",
            tags = listOf("dil devrimi", "türkçe karşılıklar", "dil bilinci", "yazım"),
            highlights = "Yabancı kökenli sözcüklere önerilen Türkçe karşılıklar",
            officialBadge = "Sivil Toplum"
        ),

        // --- 4. BİLİM VE OKUMA KÜLTÜRÜ ---
        WebResource(
            id = "tubitak_bilim_genc",
            name = "TÜBİTAK Bilim Genç (Paragraf ve Okuma Metinleri)",
            category = "Bilim & Kültür",
            url = "https://bilimgenc.tubitak.gov.tr",
            description = "TÜBİTAK'ın gençler için hazırladığı popüler bilim, teknoloji, doğa ve uzay makaleleri. LGS paragraf soruları için mükemmel kaynak.",
            tags = listOf("TÜBİTAK", "popüler bilim", "paragraf metinleri", "okuma", "makale"),
            highlights = "LGS sınavlarında sıklıkla tercih edilen bilimsel metin türleri",
            officialBadge = "TÜBİTAK",
            samplePassage = "Uzay araştırmaları ve biyoteknolojideki son gelişmeler, insanın doğayı anlama arzusunu daha da güçlendirmiştir. Bilim insanları Mars yüzeyindeki organik molekülleri incelerken Dünya'daki yaşamın kökenine dair yeni ipuçları aramaktadır."
        ),
        WebResource(
            id = "milli_kutuphane",
            name = "T.C. Millî Kütüphane Dijital Koleksiyonları",
            category = "Bilim & Kültür",
            url = "https://mkutup.gov.tr",
            description = "Türkiye'nin hafızası olan tarihi süreli yayınlar, el yazmaları, ilk Türkçe gazeteler ve edebiyat dergileri arşivi.",
            tags = listOf("milli kütüphane", "arşiv", "tarihi gazeteler", "dergiler", "el yazması"),
            highlights = "Tercüman-ı Ahval'den Servet-i Fünun'a tarihi edebiyat dergileri",
            officialBadge = "T.C. Resmî"
        ),

        // --- 5. DERS MATERYALİ VE ÇALIŞMA YAPRAKLARI ---
        WebResource(
            id = "turkcedersi_net",
            name = "Türkçe Dersi Net (Öğretmen Paylaşım Portalı)",
            category = "Ders Materyali",
            url = "https://turkcedersi.net",
            description = "Türkçe öğretmenlerinin hazırladığı haftalık çalışma yaprakları, deneme sınavları, etkinlik föyleri ve zümre evrakları.",
            tags = listOf("çalışma yaprağı", "etkinlik", "deneme sınavı", "öğretmen materyali", "föy"),
            highlights = "Sınıf içi kullanıma hazır yüzlerce Türkçe çalışma kağıdı",
            officialBadge = "Öğretmen Portalı"
        ),
        WebResource(
            id = "edebiyat_ogretmeni",
            name = "Edebiyat Öğretmeni Portalı",
            category = "Ders Materyali",
            url = "https://edebiyatogretmeni.org",
            description = "Konu anlatımları, interaktif testler, edebiyat dönemleri, nazım biçimleri ve söz sanatları ders notları.",
            tags = listOf("ders notları", "konu anlatımı", "söz sanatları", "edebiyat dönemleri"),
            highlights = "Lise ve ortaokul için ayrıntılı Türkçe-Edebiyat ders notları",
            officialBadge = "Eğitim Portalı"
        )
    )

    fun searchResources(query: String, category: String? = null): List<WebResource> {
        val q = query.trim().lowercase()
        return resources.filter { res ->
            val matchesCategory = category == null || category == "Tümü" || res.category == category
            val matchesQuery = q.isEmpty() ||
                res.name.lowercase().contains(q) ||
                res.description.lowercase().contains(q) ||
                res.highlights.lowercase().contains(q) ||
                res.tags.any { it.lowercase().contains(q) } ||
                res.url.lowercase().contains(q)
            matchesCategory && matchesQuery
        }
    }
}
