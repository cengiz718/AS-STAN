package com.example.data.curriculum

data class SchoolDocumentItem(
    val id: String,
    val title: String,
    val category: String, // Zümre Tutanakları, Sınav Analizleri, Kulüp Evrakları, Ölçek ve Çizelgeler, Dilekçeler
    val description: String,
    val documentContent: String,
    val targetTerm: String = "1. Dönem",
    val keywords: List<String> = emptyList()
)

object SchoolDocumentsRepository {

    val documents: List<SchoolDocumentItem> = listOf(
        // 1. Sene Başı Türkçe Zümre Öğretmenler Kurulu Toplantı Tutanağı
        SchoolDocumentItem(
            id = "zumre_sene_basi",
            title = "Sene Başı Türkçe Zümre Öğretmenler Kurulu Tutanağı",
            category = "Zümre Tutanakları",
            description = "MEB Ortaöğretim ve İlköğretim Kurumları Yönetmeliğine uygun, gündem maddeleri ve kararları içeren resmi zümre tutanağı",
            targetTerm = "Sene Başı",
            keywords = listOf("zümre", "tutanak", "sene başı", "toplantı", "kararlar"),
            documentContent = """
GÜNDEM MADDELERİ:
1. Açılış ve yoklama.
2. Bir önceki eğitim ve öğretim yılı zümre kararlarının değerlendirilmesi ve LGS/okul başarı analizleri.
3. MEB Türkçe Dersi Öğretim Programı'nın incelenmesi, dersin amaç ve kazanımlarının gözden geçirilmesi.
4. Atatürkçülükle ilgili konuların (2104 ve 2488 sayılı TD) ders planlarına aktarılması.
5. Yeni ölçme ve değerlendirme yönetmeliğine göre açık uçlu ortak yazılı sınav esaslarının belirlenmesi.
6. Ders araç-gereçleri, EBA ve dijital eğitim materyallerinin planlanması.
7. Öğrencilerin kitap okuma alışkanlığının geliştirilmesi ve 100 Temel Eser projeleri.
8. Özel eğitim ihtiyacı olan (BEP'li) öğrencilerin durumları ve bireyselleştirilmiş planlar.
9. Dilek ve temenniler.

GÖRÜŞMELER VE ALINAN KARARLAR:
Madde 1: Zümre başkanı toplantıyı iyi dileklerle açtı. Tüm Türkçe öğretmenlerinin eksiksiz katıldığı görüldü.
Madde 2: Geçtiğimiz yılın LGS Türkçe net ortalamaları incelendi; paragrafta anlam ve muhakeme sorularında başarının arttığı, ancak dil bilgisi (fiilimsiler ve cümle ögeleri) konularında pekiştirme ihtiyacı olduğu tespit edildi.
Madde 3: 5, 6, 7 ve 8. sınıf Türkçe kazanımları incelendi. Dinleme, konuşma, okuma ve yazma becerilerinin dengeli biçimde işlenmesine karar verildi.
Madde 4: 29 Ekim, 10 Kasım, 12 Mart ve 18 Mart gibi milli günlerin ilgili haftalarda ders içi metinlerle işlenmesi kararlaştırıldı.
Madde 5: MEB Ölçme ve Değerlendirme Yönetmeliği uyarınca her dönemde 2 yazılı sınav yapılması, sınavların çoktan seçmeli yerine süreç odaklı açık uçlu ve kısa cevaplı sorulardan oluşması kararlaştırıldı.
Madde 6: Sınıflarda her hafta en az 1 ders saatinin "Serbest Okuma ve Kitap Tahlili Saati" olarak uygulanması kararlaştırıldı.
Madde 7: BEP'li öğrencilerin seviyelerine uygun basitleştirilmiş metinler ve değerlendirme ölçekleri hazırlanması kabul edildi.
            """.trimIndent()
        ),

        // 2. Yazılı Sınav Analiz Raporu ve Eylem Planı
        SchoolDocumentItem(
            id = "sinav_analiz_raporu",
            title = "Türkçe Dersi Yazılı Sınav Analiz Raporu",
            category = "Sınav Analizleri",
            description = "Sınav sonuçlarının soru ve kazanım bazında analizi, sınıf başarı yüzdeleri ve telafi eylem planı",
            targetTerm = "1. ve 2. Dönem",
            keywords = listOf("sınav analizi", "kazanım", "başarı yüzdesi", "eylem planı"),
            documentContent = """
TÜRKÇE DERSİ YAZILI SINAV ANALİZİ VE DEĞERLENDİRME RAPORU

1. SINAV BİLGİLERİ:
• Sınav Türü: 1. Dönem 1. Ortak Yazılı Sınavı
• Sınıf ve Şubeler: 8/A - 8/B - 8/C
• Sınava Giren Öğrenci Sayısı: 78
• Sınav Ortalaması: 76.45 Puan
• En Yüksek Not: 100 | En Düşük Not: 42

2. KAZANIM BAZLI BAŞARI ANALİZİ:
• T.8.3.14 (Metinle ilgili soruları cevaplama / Paragraf): %88 Başarı (Yüksek)
• T.8.3.6 (Deyim ve atasözlerinin metne katkısı): %82 Başarı (Yeterli)
• T.8.3.9 (Fiilimsilerin işlevlerini ayırt etme): %61 Başarı (Geliştirilmeli)
• T.8.3.17 (Cümlenin ögelerini bulma): %54 Başarı (Kritik / Pekiştirilmeli)
• T.8.4.16 (Yazım ve noktalama kuralları): %72 Başarı (Orta)

3. TESPİT EDİLEN ÖĞRENME EKSİKLİKLERİ:
Öğrencilerin fiilimsi ekleriyle kalıcı adları ayırt etmekte ve devrik cümlelerde cümle ögelerini (özellikle özne ve nesne ayrımında) tespit etmekte güçlük çektikleri gözlemlenmiştir.

4. TELAFİ EYLEM PLANI (İYİLEŞTİRME ÇALIŞMALARI):
1. Cümle ögeleri konusunda akıllı tahta üzerinden interaktif cümle çözümleme atölyesi düzenlenecektir.
2. Fiilimsiler için 'MAYIŞMAK' ve 'ANASIMEZARDİKECEKMİŞ' hafıza kodlamaları ve çalışma yaprakları dağıtılacaktır.
3. Başarısı 50 puanın altında kalan öğrencilere yönelik destekleme ve yetiştirme kurslarında (DYK) özel etüt yapılacaktır.
            """.trimIndent()
        ),

        // 3. Şube Öğretmenler Kurulu (ŞÖK) Türkçe Dersi Raporu
        SchoolDocumentItem(
            id = "sok_raporu",
            title = "Şube Öğretmenler Kurulu (ŞÖK) Türkçe Dersi Raporu",
            category = "Zümre Tutanakları",
            description = "Şube bazında öğrencilerin akademik, sosyal, kitap okuma ve devamsızlık durumlarını içeren kurul raporu",
            targetTerm = "1. Dönem / 2. Dönem",
            keywords = listOf("şök", "şube öğretmenler kurulu", "sınıf raporu", "rehberlik"),
            documentContent = """
ŞUBE ÖĞRETMENLER KURULU (ŞÖK) TÜRKÇE DERSİ DEĞERLENDİRME RAPORU

Sınıf / Şube: 8/A
Ders Öğretmeni: Türkçe Dersi Zümresi

1. GENEL AKADEMİK DURUM VE BAŞARI DÜZEYİ:
Sınıfın Türkçe dersine karşı ilgi ve motivasyonu yüksektir. Ders içi katılım oranı %85 civarındadır. Öğrenciler özellikle metin tahlili ve münazara etkinliklerinde oldukça başarılıdır.

2. DİKKAT ÇEKEN VE DESTEKLENMESİ GEREKEN ÖĞRENCİLER:
• Başarılı ve İleri Düzey Öğrenciler: Zeynep K., Kerem A. (İlçe şiir ve kompozisyon yarışmalarına yönlendirildi.)
• Desteklenmesi Gereken Öğrenciler: Murat T., Berke S. (Okuma akıcılığı ve yazılı anlatımda eksikleri mevcut, haftalık bireysel okuma ödevi verildi.)
• Rehberlik Servisiyle İş Birliği: Sınav kaygısı yaşayan 3 öğrenci okul rehberlik servisine yönlendirilmiştir.

3. KİTAP OKUMA ALIŞKANLIĞI:
Sınıf kitaplığında 120 adet eser bulunmaktadır. Dönem başından itibaren öğrenci başına okunan ortalama kitap sayısı 4 adettir. Kitap okuma kulübü çalışmaları başarıyla yürütülmektedir.

4. VELİ İŞ BİRLİĞİ VE TAVSİYELER:
Öğrencilerin evde her gün en az 30 dakika televizyon ve telefon ekranından uzak şekilde sessiz kitap okumaları yönünde velilere bilgilendirme notu iletilmiştir.
            """.trimIndent()
        ),

        // 4. Kütüphanecilik ve Edebiyat Kulübü Yıllık Çalışma Planı
        SchoolDocumentItem(
            id = "kulup_yillik_plani",
            title = "Kütüphanecilik ve Edebiyat Kulübü Yıllık Çalışma Planı",
            category = "Kulüp Evrakları",
            description = "Sosyal etkinlikler kapsamında Edebiyat ve Kütüphanecilik Kulübünün aylık faaliyet planı",
            targetTerm = "Tüm Yıl",
            keywords = listOf("kulüp", "kütüphanecilik", "edebiyat", "sosyal etkinlik"),
            documentContent = """
KÜTÜPHANECİLİK VE EDEBİYAT KULÜBÜ YILLIK ÇALIŞMA PLANI

EKİM AYI:
• Kulüp yönetim kurulunun ve denetleme kurulunun seçilmesi.
• Okul kütüphanesinin tanzimi, kitapların tasnifi ve dijital kayıt sisteminin güncellenmesi.
• 29 Ekim Cumhuriyet Bayramı okul panosunun hazırlanması.

KASIM AYI:
• 10 Kasım Atatürk Haftası için "Atatürk ve Kitap Sevgisi" sergisi.
• 24 Kasım Öğretmenler Günü için "Öğretmenime Mektup" yarışması düzenlenmesi.

ARALIK AYI:
• "Ayın Kitap Kurdu" projesinin başlatılması ve en çok kitap okuyan öğrencilerin ödüllendirilmesi.
• Edebiyat söyleşisi: Yerel bir şair veya yazarın okula davet edilmesi.

OCAK AYI:
• 1. Dönem kulüp faaliyet raporunun hazırlanması ve okul idaresine sunulması.
• Yarıyıl tatili için "Tatil Okuma Listesi" tavsiye bülteni hazırlanması.

ŞUBAT AYI:
• 2. Dönem hedeflerinin belirlenmesi.
• Kütüphaneye yeni bağışlanan kitapların etiketlenmesi.

MART AYI:
• 12 Mart İstiklal Marşı'nın Kabulü ve Mehmet Akif Ersoy'u Anma Günü programı.
• 18 Mart Çanakkale Zaferi şiir dinletisi.
• Kütüphaneler Haftası etkinlikleri (Kitap takas şenliği).

NİSAN - MAYIS AYI:
• 23 Nisan Çocuk Bayramı panosu ve çocuk edebiyatı köşesi.
• Okul edebiyat dergisinin (e-dergi) hazırlanıp yayımlanması.

HAZİRAN AYI:
• Yıl sonu faaliyet raporunun düzenlenmesi ve kütüphane sayımının tamamlanması.
            """.trimIndent()
        ),

        // 5. Türkçe Dersi Proje ve Performans Görevi Değerlendirme Ölçeği
        SchoolDocumentItem(
            id = "proje_degerlendirme_olcegi",
            title = "Proje ve Performans Görevi Değerlendirme Ölçeği",
            category = "Ölçek ve Çizelgeler",
            description = "Öğrenci proje ödevleri için 5 boyutlu resmî MEB puanlama baremi ve kriter listesi",
            targetTerm = "Tüm Yıl",
            keywords = listOf("proje", "performans", "ölçek", "rubrik", "puanlama"),
            documentContent = """
TÜRKÇE DERSİ PROJE VE PERFORMANS GÖREVİ DEĞERLENDİRME BAREMİ

ÖĞRENCİ BİLGİLERİ:
Adı Soyadı: ....................................... Sınıfı/No: ............ / ............
Proje Konusu: "Deyimlerimizin Hikâyeleri ve Karikatür Albümü Hazırlama"
Teslim Tarihi: ..... / ..... / 2026

DEĞERLENDİRME KRİTERLERİ VE PUAN DAĞILIMI:

1. DÜZEN VE PLANLAMA (15 PUAN):
• Zaman planlamasına uyma ve taslak hazırlama: 5 Puan
• Temizlik, sayfa düzeni ve estetik sunum: 10 Puan

2. İÇERİK VE ARAŞTIRMA DERİNLİĞİ (35 PUAN):
• Konuya uygun güvenilir kaynaklardan yararlanma: 10 Puan
• Deyimlerin kökenini ve hikâyelerini doğru yansıtma: 15 Puan
• Özgün fikirler ve yaratıcı görsel tasarım: 10 Puan

3. TÜRKÇEYİ DOĞRU KULLANMA VE İMLA (25 PUAN):
• TDK İmla Kılavuzu kurallarına uygunluk: 10 Puan
• Noktalama işaretlerinin yerli yerinde kullanımı: 10 Puan
• Duru, anlaşılır ve akıcı cümle yapıları: 5 Puan

4. SUNUM VE SAVUNMA (15 PUAN):
• Sınıf önünde projeyi sözlü olarak etkili sunma: 10 Puan
• Sorulan sorulara hakimiyetle yanıt verme: 5 Puan

5. KAYNAKÇA VE ETİK KURALLAR (10 PUAN):
• Yararlanılan eserleri bilimsel formata uygun belirtme: 10 Puan

TOPLAM PUAN: 100 PUAN
Öğretmen Değerlendirme Notu: ..............................................................
            """.trimIndent()
        ),

        // 6. Türkçe Dersi Veli Bilgilendirme ve İş Birliği Mektubu
        SchoolDocumentItem(
            id = "veli_bilgilendirme_mektubu",
            title = "Türkçe Dersi Veli Bilgilendirme & Okuma İş Birliği Mektubu",
            category = "Dilekçeler",
            description = "Velilerle iş birliğini güçlendiren, kitap okuma saati ve LGS hazırlık tavsiyelerini içeren resmi bilgilendirme yazısı",
            targetTerm = "Sene Başı & Dönem Başı",
            keywords = listOf("veli", "mektup", "bilgilendirme", "kitap okuma"),
            documentContent = """
SAYIN VELİMİZ,

Çocuğumuzun eğitim yolculuğunda Türkçe dersi, tüm derslerin ve zihinsel gelişimin temel anahtarıdır. Okuduğunu doğru anlayan, kelime hazinesi zengin ve analitik düşünebilen öğrencilerimiz yalnızca Türkçede değil, matematik, fen ve sosyal bilimlerde de üstün başarı göstermektedir.

Bu doğrultuda evdeki çalışma ortamında sizlerden şu hususlarda destek beklemekteyiz:

1. EVDE GÜNLÜK KİTAP OKUMA SAATİ:
Her akşam en az 30 dakika televizyon, tablet ve akıllı telefonların kapatıldığı "Ailece Kitap Okuma Saati" uygulayınız. Çocuklar nasihatten çok anne ve babalarının davranışlarını örnek alırlar.

2. KELİME DAĞARCIĞINI GELİŞTİRME:
Öğrencimizin bilmediği sözcükleri bir sözlükten araştırmasını teşvik ediniz. Günlük sohbetlerinizde atasözü ve deyimlerimize yer vererek dil bilincini pekiştiriniz.

3. DÜZENLİ ÖDEV VE SORU TAKİBİ:
Haftalık verilen okuma parçası ve yazma çalışmalarını kontrol ederek ders defterini düzenli tutmasını sağlayınız.

Evlatlarımızı aklın, bilimin ve güzel Türkçemizin ışığında geleceğe hep birlikte hazırlayacağız. İş birliğiniz için şimdiden teşekkür ederiz.

Türkçe Dersi Zümre Öğretmenleri
            """.trimIndent()
        ),

        // 7. Mazeret Sınavı Tutanağı ve Üst Yazı
        SchoolDocumentItem(
            id = "mazeret_sinavi_tutanagi",
            title = "Mazeret Sınavı Hazırlama Tutanağı ve Üst Yazı",
            category = "Dilekçeler",
            description = "Raporlu veya mazereti idarece kabul edilen öğrencilere uygulanacak mazeret sınavı bildirim tutanağı",
            targetTerm = "Tüm Dönemler",
            keywords = listOf("mazeret", "sağlık raporu", "telafi sınavı", "tutanak"),
            documentContent = """
.................................................................... MÜDÜRLÜĞÜNE

Ders Adı: Türkçe
Sınav Dönemi: 1. Dönem 1. Ortak Yazılı Sınavı
Sınav Tarihi: ..... / ..... / 2026

Okulumuz 8/... sınıfı ........... numaralı öğrencisi ......................................................, ..... / ..... / 2026 tarihinde yapılan Türkçe dersi 1. Dönem 1. Yazılı Sınavına resmî sağlık raporu / izinli olması sebebiyle katılamamıştır.

Milli Eğitim Bakanlığı Ölçme ve Değerlendirme Yönetmeliği gereğince, adı geçen öğrenci için eşdeğer kazanımları kapsayan "Mazeret Sınavı Soruları" hazırlanmış olup, sınav ..... / ..... / 2026 tarihinde saat .......:.......'da uygulanacaktır.

Gereğini bilgilerinize arz ederim.

Türkçe Dersi Öğretmeni: ....................................................
İmza: ............................................
            """.trimIndent()
        )
    )

    fun getDocumentById(id: String): SchoolDocumentItem? = documents.find { it.id == id }

    /**
     * Generates a fully formatted, print-ready A4 HTML document
     * with official MEB letterhead, border, content, and signature lines.
     */
    fun generatePrintableDocumentHtml(
        doc: SchoolDocumentItem,
        schoolName: String = "Atatürk Ortaokulu",
        academicYear: String = "2026-2027 Eğitim Öğretim Yılı"
    ): String {
        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${doc.title} - $schoolName</title>
            <style>
                @page {
                    size: A4;
                    margin: 15mm 18mm 18mm 18mm;
                }
                body {
                    font-family: 'Times New Roman', Times, serif, Arial;
                    color: #111;
                    font-size: 11pt;
                    line-height: 1.45;
                    margin: 0;
                    padding: 0;
                    background: #fff;
                }
                .doc-header {
                    text-align: center;
                    border-bottom: 2px solid #1E3A5F;
                    padding-bottom: 10px;
                    margin-bottom: 16px;
                }
                .ministry-title {
                    font-size: 11pt;
                    font-weight: bold;
                    letter-spacing: 1px;
                    color: #333;
                }
                .school-title {
                    font-size: 14pt;
                    font-weight: bold;
                    color: #1E3A5F;
                    margin: 4px 0;
                    text-transform: uppercase;
                }
                .document-title {
                    font-size: 13pt;
                    font-weight: bold;
                    color: #8B263E;
                    margin-top: 6px;
                    text-transform: uppercase;
                }
                .meta-badge {
                    display: inline-block;
                    font-size: 9.5pt;
                    color: #555;
                    margin-top: 4px;
                    background: #f0f4f8;
                    padding: 2px 10px;
                    border-radius: 4px;
                }
                .content-box {
                    text-align: justify;
                    white-space: pre-wrap;
                    line-height: 1.5;
                    font-size: 10.5pt;
                }
                .signatures-block {
                    margin-top: 45px;
                    display: flex;
                    justify-content: space-around;
                    text-align: center;
                    page-break-inside: avoid;
                    font-size: 10.5pt;
                }
                .sig-person {
                    width: 200px;
                }
                .sig-line {
                    margin-top: 35px;
                    border-top: 1px dashed #666;
                    padding-top: 4px;
                    font-size: 9.5pt;
                    color: #444;
                }
                .footer-meta {
                    margin-top: 40px;
                    border-top: 1px solid #ddd;
                    padding-top: 6px;
                    display: flex;
                    justify-content: space-between;
                    font-size: 8.5pt;
                    color: #777;
                }
            </style>
        </head>
        <body>
            <div class="doc-header">
                <div class="ministry-title">T.C. MİLLÎ EĞİTİM BAKANLIĞI</div>
                <div class="school-title">$schoolName</div>
                <div class="document-title">${doc.title}</div>
                <div class="meta-badge">$academicYear • ${doc.category} • ${doc.targetTerm}</div>
            </div>

            <div class="content-box">
${doc.documentContent}
            </div>

            <div class="signatures-block">
                <div class="sig-person">
                    <b>Zümre Başkanı / Öğretmen</b>
                    <div class="sig-line">İmza</div>
                </div>
                <div class="sig-person">
                    <b>Türkçe Öğretmeni</b>
                    <div class="sig-line">İmza</div>
                </div>
                <div class="sig-person">
                    <b>Okul Müdürü</b>
                    <div class="sig-line">Uygundur / Mühür</div>
                </div>
            </div>

            <div class="footer-meta">
                <div>Türkçe Dersi Resmî Evrak Dosyası • Türkçe Asistanı</div>
                <div>Düzenlenme Tarihi: 2026-10-27</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
