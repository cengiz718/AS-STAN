package com.example.data.curriculum

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ==========================================
// MODELS FOR TURKCEN.NET TUTANAK OLUŞTURUCU
// ==========================================

enum class TutanakCategory(val title: String, val description: String) {
    ZUMRE("Zümre Öğretmenler Kurulu", "Sene başı, 1. dönem sonu, 2. dönem ve sene sonu zümre tutanakları ile onay dilekçeleri"),
    SOK("Şube Öğretmenler Kurulu (ŞÖK)", "5, 6, 7 ve 8. sınıflar için öğrenci değerlendirme tablosu ve kurul tutanağı"),
    VELI("Veli Toplantısı & Görüşme", "Sınıf veli toplantı tutanağı, koparmalı veli davetiyesi ve veli katılım imza listesi"),
    OGRETMENLER_KURULU("Öğretmenler Kurulu", "Genel kurul toplantı tutanağı ve karar defteri"),
    KULUP_VE_BEP("BEP, Kulüp & Olay Tutanakları", "Kütüphanecilik kulübü, BEP birimi, sınav kağıtları teslim ve olay tutanakları")
}

data class GundemItem(
    val no: Int,
    val title: String,
    val discussion: String,
    val decision: String,
    var isSelected: Boolean = true
)

data class SokStudentEvaluation(
    val studentNo: String,
    val fullName: String,
    val attendanceStatus: String, // Devamsızlık durumu
    val academicStatus: String,   // Akademik başarı
    val behaviorStatus: String,   // Davranış ve rehberlik
    val actionDecision: String    // Alınan karar
)

data class VeliAttendanceItem(
    val rowNo: Int,
    val studentNo: String,
    val studentName: String,
    val parentName: String,
    val phone: String = ""
)

object TutanakGeneratorRepository {

    // ----------------------------------------------------
    // 1. ZÜMRE TOPLANTI TUTANAĞI VERİ VE ŞABLONLARI
    // ----------------------------------------------------

    fun getStandardZumreGundemler(term: String): List<GundemItem> {
        return when (term) {
            "2. Dönem Başı" -> listOf(
                GundemItem(
                    1, "Açılış ve yoklama",
                    "Toplantı Zümre Başkanı tarafından açıldı. Tüm Türkçe öğretmenlerinin eksiksiz katıldığı görüldü.",
                    "Toplantı gündemi oy birliği ile kabul edildi."
                ),
                GundemItem(
                    2, "1. Dönem zümre kararlarının ve LGS/okul başarı analizlerinin değerlendirilmesi",
                    "1. Dönemde yapılan 2 ortak yazılı sınav sonuçları ile deneme sınavı netleri incelendi. Paragrafta anlam kazanımlarında başarının %84 olduğu, ancak dil bilgisi (fiilimsiler, cümlenin ögeleri) konularında ek pekiştirmeye ihtiyaç duyulduğu belirtildi.",
                    "1. Dönem kararlarının büyük oranda uygulandığı görüldü. 2. Dönemde dil bilgisi pekiştirme çalışmaları için haftada 1 ders saatinin soru çözümüne ayrılmasına karar verildi."
                ),
                GundemItem(
                    3, "2. Dönem ders planlarının ve Türkiye Yüzyılı Maarif Modeli kazanımlarının gözden geçirilmesi",
                    "MEB Türkçe Dersi Öğretim Programı ve Maarif Modeli çerçevesinde dinleme, konuşma, okuma ve yazma 4 temel becerisinin dengeli biçimde işlenmesi gerektiği vurgulandı.",
                    "Ders planlarında dinleme ve konuşma etkinliklerine ayrılan sürenin aksatılmadan sürdürülmesine karar verildi."
                ),
                GundemItem(
                    4, "2. Dönem ortak yazılı sınav tarihleri ve açık uçlu sınav senaryolarının belirlenmesi",
                    "MEB Ölçme ve Değerlendirme Yönetmeliği doğrultusunda sınavların Bakanlık veya İl Zümresi tarafından belirlenen ortak senaryolara göre açık uçlu ve kısa cevaplı olarak uygulanması konusu görüşüldü.",
                    "1. Sınavın Nisan ayının ilk haftasında, 2. sınavın ise Mayıs ayının son haftasında yapılması ve cevap anahtarlarının rubrikli hazırlanması kararlaştırıldı."
                ),
                GundemItem(
                    5, "Öğrencilerin kitap okuma alışkanlıkları ve 'Dilimizin Zenginlikleri' projesinin yürütülmesi",
                    "Öğrencilerin dönem boyunca okudukları kitap sayıları değerlendirildi. 'Dilimizin Zenginlikleri Projesi' kapsamında Mart ayında 'Deyimler ve Atasözleri Okumaları', Nisan ayında 'Kutadgu Bilig ve Yunus Emre Etkinlikleri' yapılacağı hatırlatıldı.",
                    "Her şubede haftada 1 ders saatinin kitap okuma ve tahlil saati olarak sürdürülmesine ve pano çalışmalarının zenginleştirilmesine karar verildi."
                ),
                GundemItem(
                    6, "Özel eğitim ihtiyacı olan (BEP'li) öğrencilerin bireyselleştirilmiş eğitim planları",
                    "BEP'li öğrencilerin 1. dönem performansları gözden geçirildi. Sınavlarda soru sayılarının azaltılması ve seviyelerine uygun metinler verilmesinin olumlu sonuç verdiği belirtildi.",
                    "2. Dönem sınavlarında da basitleştirilmiş açık uçlu sorular ve çoktan seçmeli destekleyici ölçekler kullanılmasına devam edilmesine karar verildi."
                ),
                GundemItem(
                    7, "Dilek ve temenniler",
                    "Zümre öğretmenleri 2026-2027 Eğitim Öğretim Yılı 2. Döneminin başarılı ve verimli geçmesi temennisinde bulundu.",
                    "Toplantı Zümre Başkanının teşekkür konuşmasıyla sona erdi."
                )
            )

            "Sene Sonu (Yıl Sonu)" -> listOf(
                GundemItem(
                    1, "Açılış, yoklama ve toplantı divanının teşkili",
                    "Toplantı Zümre Başkanı başkanlığında açıldı. İlgili tüm branş öğretmenleri hazır bulundu.",
                    "Gündem maddeleri üzerinde ekleme yapılmadan kabul edildi."
                ),
                GundemItem(
                    2, "Yıllık planların ve müfredat kazanımlarının tamamlanma durumu",
                    "Türkçe dersi 5, 6, 7 ve 8. sınıf yıllık çalışma planlarının incelenmesi neticesinde MEB müfredatında yer alan tüm kazanımların zamanında ve eksiksiz işlendiği tespit edildi.",
                    "Yıllık planların başarıyla tamamlandığı ve herhangi bir kazanım açığı kalmadığı tutanak altına alındı."
                ),
                GundemItem(
                    3, "LGS Türkçe sonuçları ve yıl sonu akademik başarı analizi",
                    "8. Sınıf LGS deneme ve merkezi sınav sonuçları ile 5, 6, 7. sınıfların yıl sonu ders notu ortalamaları karşılaştırıldı. Türkçe dersi okul genel ortalamasının %81.2 olduğu saptandı.",
                    "Önümüzdeki eğitim yılında DYK kurslarının daha erken başlatılması ve 7. sınıflara yönelik LGS hazırlık seminerlerinin artırılması kararlaştırıldı."
                ),
                GundemItem(
                    4, "Yıl boyu uygulanan 'Dilimizin Zenginlikleri' ve kitap okuma projelerinin değerlendirilmesi",
                    "Yıl boyunca öğrencilerin ortalama 14 adet kitap okuduğu, düzenlenen afiş, kompozisyon ve şiir yarışmalarında ilçe genelinde dereceler alındığı ifade edildi.",
                    "Okul kütüphanesinin güncel eserlerle donatılması hususunun okul idaresine bildirilmesine karar verildi."
                ),
                GundemItem(
                    5, "Gelecek eğitim ve öğretim yılına yönelik öneriler ve kapanış",
                    "Önümüzdeki sene akıllı tahta içeriklerinin zenginleştirilmesi ve zümre içi materyal havuzunun dijital ortamda ortak kullanıma açılması önerildi.",
                    "Başarılı geçen eğitim öğretim yılı için tüm öğretmenlere teşekkür edilerek toplantı kapatıldı."
                )
            )

            else -> listOf( // Sene Başı (Varsayılan)
                GundemItem(
                    1, "Açılış ve yoklama",
                    "2026-2027 Eğitim-Öğretim yılı Sene Başı Türkçe Zümre Öğretmenler Kurulu toplantısı Zümre Başkanı tarafından iyi dileklerle açıldı. Yapılan yoklamada tüm Türkçe öğretmenlerinin hazır bulunduğu görüldü.",
                    "Gündem maddeleri tek tek okunarak toplantıya geçildi."
                ),
                GundemItem(
                    2, "Bir önceki eğitim ve öğretim yılının değerlendirilmesi ve LGS başarı analizi",
                    "Geçtiğimiz yılın LGS Türkçe netleri ve ortak sınav sonuçları analiz edildi. Görsel okuma, tablo-grafik yorumlama ve sözel mantık sorularında öğrencilerin yüksek başarı gösterdiği; ancak uzun paragraf analizi ve dil bilgisi sorularında odaklanma güçlüğü yaşandığı tespit edildi.",
                    "Derslerde sözel mantık ve eleştirel düşünme sorularına ağırlık verilmesi, LGS denemelerinin periyodik olarak yapılması kararlaştırıldı."
                ),
                GundemItem(
                    3, "MEB Türkçe Dersi Öğretim Programı ve Türkiye Yüzyılı Maarif Modeli'nin incelenmesi",
                    "Bakanlığımızca güncellenen öğretim programı incelendi. Dinleme, konuşma, okuma ve yazma olmak üzere 4 temel dil becerisinin süreç odaklı olarak derslere dağıtılması gerektiği vurgulandı.",
                    "Haftalık ders saatlerinde her beceriye uygun öğrenme etkinlikleri planlanması ve öğrencilerin aktif konuşma yapabilecekleri drama/münazara etkinliklerine yer verilmesi kararlaştırıldı."
                ),
                GundemItem(
                    4, "Atatürkçülükle ilgili konuların (2104 ve 2488 sayılı Tebliğler Dergisi) yıllık planlara aktarılması",
                    "29 Ekim Cumhuriyet Bayramı, 10 Kasım Atatürk'ü Anma, 12 Mart İstiklal Marşı'nın Kabulü ve 18 Mart Çanakkale Zaferi gibi milli günlerin ilgili haftalarda metin seçimleriyle zenginleştirilmesi görüşüldü.",
                    "Atatürkçülük konularının yıllık planlarda ilgili kazanımlarla ilişkilendirilerek eksiksiz yer alması kararlaştırıldı."
                ),
                GundemItem(
                    5, "MEB Ölçme ve Değerlendirme Yönetmeliği uyarınca açık uçlu ortak sınavların planlanması",
                    "Yeni yönetmelik doğrultusunda her dönem 2 adet ortak yazılı sınav yapılması, sınavların süreç odaklı açık uçlu sorulardan ve MEB örnek soru senaryolarından oluşması hususu değerlendirildi.",
                    "Sınavlarda çoktan seçmeli soru kullanılmaması, ayrıntılı puanlama anahtarı (rubrik) hazırlanması ve dinleme/konuşma becerilerinin %50 oranında not baremine dahil edilmesi kararlaştırıldı."
                ),
                GundemItem(
                    6, "Öğrencilerin kitap okuma alışkanlığının geliştirilmesi ve Dilimizin Zenginlikleri Projesi",
                    "Öğrencilere kitap sevgisinin aşılanması için sınıf kitaplıklarının oluşturulması ve MEB 'Dilimizin Zenginlikleri' projesi takviminin uygulanması görüşüldü.",
                    "Her şubede haftalık 1 ders saatinin serbest okuma ve kitap tahlili olarak ayrılması, Türk Klasikleri ve 100 Temel Eserden seçmelerin tavsiye edilmesi kararlaştırıldı."
                ),
                GundemItem(
                    7, "Özel eğitim ihtiyacı olan (BEP'li) öğrencilerin durumları",
                    "Rehberlik servisinden temin edilen BEP'li öğrencilerin engel durumları ve eğitsel tanıları incelendi.",
                    "Bu öğrenciler için Bireyselleştirilmiş Eğitim Planlarının hazırlanması ve sınavlarda seviyelerine uygun özel sorular düzenlenmesi kararlaştırıldı."
                ),
                GundemItem(
                    8, "Dilek ve temenniler",
                    "Yeni eğitim ve öğretim yılının okulumuz ve milletimiz adına hayırlı olması dileklerinde bulunuldu.",
                    "Zümre başkanı katılan tüm öğretmenlere teşekkür ederek toplantıyı sonlandırdı."
                )
            )
        }
    }

    // ----------------------------------------------------
    // 2. ŞÖK (ŞUBE ÖĞRETMENLER KURULU) HAZIR VERİLERİ
    // ----------------------------------------------------

    fun getSampleSokStudents(): List<SokStudentEvaluation> {
        return listOf(
            SokStudentEvaluation(
                studentNo = "142",
                fullName = "Ahmet Yılmaz",
                attendanceStatus = "Özürsüz 4 gün devamsızlığı var. Takip ediliyor.",
                academicStatus = "Türkçe ve Sosyal derslerinde başarılı, Matematik ve Fen derslerinde ek çalışma gerektiriyor.",
                behaviorStatus = "Sınıf içi kurallara uyumlu, derse katılımı orta düzey.",
                actionDecision = "Hafta sonu Matematik DYK kursuna yönlendirildi, velisi bilgilendirildi."
            ),
            SokStudentEvaluation(
                studentNo = "215",
                fullName = "Zeynep Demir",
                attendanceStatus = "Devamsızlığı yok, derslere düzenli katılıyor.",
                academicStatus = "Tüm branşlarda ders başarısı çok yüksek (%94 ortalama).",
                behaviorStatus = "Örnek tutum sergiliyor, sınıf başkan yardımcısı.",
                actionDecision = "İlçe çapındaki şiir ve LGS deneme yarışmalarına okul adına aday gösterildi."
            ),
            SokStudentEvaluation(
                studentNo = "388",
                fullName = "Mustafa Kaya",
                attendanceStatus = "Özürsüz 8 gün devamsızlık. Veliye mektup gönderildi.",
                academicStatus = "Ders notları zayıf. Okuma hızı ve ödev tesliminde eksiklikler mevcut.",
                behaviorStatus = "Dikkat eksikliği ve odaklanma problemi yaşıyor.",
                actionDecision = "Okul Rehberlik ve Psikolojik Danışma servisine sevk edildi. Birebir veli görüşmesi kararlaştırıldı."
            ),
            SokStudentEvaluation(
                studentNo = "402",
                fullName = "Elif Şahin",
                attendanceStatus = "Raporlu 3 gün devamsızlık.",
                academicStatus = "Sözel derslerde başarılı, fen ve teknoloji dersinde kavramları pekiştirmesi gerekiyor.",
                behaviorStatus = "Saygılı, sessiz ve arkadaşlarıyla iletişimi kuvvetli.",
                actionDecision = "Fen Bilimleri dersinde akran eşleşmesi çalışması yaptırılmasına karar verildi."
            )
        )
    }

    // ----------------------------------------------------
    // 3. VELİ TOPLANTISI & DAVETİYE HAZIR VERİLERİ
    // ----------------------------------------------------

    fun getStandardVeliGundemler(): List<String> {
        return listOf(
            "1. Açılış ve velilerle tanışma.",
            "2. Okul ve sınıf kurallarının (ders başlama saatleri, kılık-kıyafet yönetmeliği) hatırlatılması.",
            "3. Öğrenci devam-devamsızlık durumları ve MEB e-Okul veli bilgilendirme sisteminin takibi.",
            "4. Verimli ders çalışma teknikleri, evde ders çalışma ortamının hazırlanması ve uyku düzeni.",
            "5. Teknolojik aletlerin (akıllı telefon, tablet, bilgisayar oyunu) bilinçli kullanımı ve dijital bağımlılığın önlenmesi.",
            "6. Kitap okuma alışkanlığının aile içinde desteklenmesi ve kitap tahlil saatleri.",
            "7. LGS sınav süreci, deneme sınavları ve soru çözme hedeflerinin belirlenmesi (8. Sınıflar için).",
            "8. Sınıf Veli Temsilcisi seçimi.",
            "9. Dilek ve temenniler, velilerin sorularının yanıtlanması ve kapanış."
        )
    }

    fun getSampleVeliAttendanceList(): List<VeliAttendanceItem> {
        return listOf(
            VeliAttendanceItem(1, "102", "Ali Emre Çetin", "Murat Çetin (Babası)", "0532 111 22 33"),
            VeliAttendanceItem(2, "145", "Ayşe Melek Koç", "Fatma Koç (Annesi)", "0543 222 33 44"),
            VeliAttendanceItem(3, "210", "Burak Özkan", "Hakan Özkan (Babası)", "0505 333 44 55"),
            VeliAttendanceItem(4, "304", "Ceren Güneş", "Emine Güneş (Annesi)", "0555 444 55 66"),
            VeliAttendanceItem(5, "412", "Deniz Karaca", "Selim Karaca (Babası)", "0536 555 66 77")
        )
    }

    // ----------------------------------------------------
    // 4. HTML ÜRETİM MOTORLARI (RESMÎ MEB & TURKCEN.NET FORMATI)
    // ----------------------------------------------------

    fun generateZumreTutanakHtml(
        okulAdi: String,
        ilIlce: String,
        egitimYili: String,
        donem: String,
        toplantiNo: String,
        tarih: String,
        saat: String,
        yer: String,
        baskan: String,
        katilimcilar: List<String>,
        gundemler: List<GundemItem>
    ): String {
        val selectedGundem = gundemler.filter { it.isSelected }

        val gundemListHtml = selectedGundem.mapIndexed { idx, item ->
            "<li><strong>Madde ${idx + 1}:</strong> ${escape(item.title)}</li>"
        }.joinToString("\n")

        val gorusmeVeKararlarHtml = selectedGundem.mapIndexed { idx, item ->
            """
            <div style="margin-bottom: 14px; page-break-inside: avoid;">
                <p style="margin: 4px 0; font-weight: bold; color: #1a237e;">
                    Madde ${idx + 1}: ${escape(item.title)}
                </p>
                <p style="margin: 4px 0 6px 0; text-align: justify; line-height: 1.45;">
                    <strong>Görüşmeler:</strong> ${escape(item.discussion)}
                </p>
                <p style="margin: 4px 0 6px 0; text-align: justify; line-height: 1.45; background-color: #f8f9fa; padding: 6px; border-left: 3px solid #1a237e;">
                    <strong>Alınan Karar:</strong> ${escape(item.decision)}
                </p>
            </div>
            """.trimIndent()
        }.joinToString("\n")

        val allTeachers = listOf(baskan to "Zümre Başkanı") + katilimcilar.filter { it.isNotBlank() }.map { it to "Türkçe Öğretmeni" }
        val signaturesHtml = allTeachers.chunked(3).joinToString("\n") { rowTeachers ->
            """
            <div style="display: flex; justify-content: space-around; margin-top: 24px; text-align: center; page-break-inside: avoid;">
                ${rowTeachers.joinToString("\n") { (name, role) ->
                """
                    <div style="width: 30%;">
                        <div style="font-weight: bold;">${escape(name)}</div>
                        <div style="font-size: 11pt; color: #555;">${escape(role)}</div>
                        <div style="margin-top: 35px; border-bottom: 1px dotted #888; width: 80%; margin-left: auto; margin-right: auto;"></div>
                        <div style="font-size: 10pt; color: #777; margin-top: 4px;">İmza</div>
                    </div>
                    """.trimIndent()
            }}
            </div>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Zümre Öğretmenler Kurulu Tutanağı</title>
            <style>
                @page { size: A4; margin: 18mm 15mm 18mm 15mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 12pt;
                    color: #000;
                    line-height: 1.4;
                    margin: 0;
                    padding: 0;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 15px;
                    line-height: 1.3;
                }
                .doc-title {
                    font-size: 13pt;
                    text-decoration: underline;
                    margin-top: 6px;
                }
                table.info-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 15px;
                }
                table.info-table td {
                    border: 1px solid #333;
                    padding: 6px 8px;
                    font-size: 11pt;
                }
                table.info-table td.label {
                    font-weight: bold;
                    background-color: #f1f3f4;
                    width: 25%;
                }
                h3 {
                    font-size: 12pt;
                    border-bottom: 1.5px solid #1a237e;
                    padding-bottom: 3px;
                    margin-top: 16px;
                    margin-bottom: 8px;
                    color: #1a237e;
                }
                ol { margin-top: 4px; padding-left: 20px; }
                li { margin-bottom: 4px; }
                .approval-box {
                    margin-top: 30px;
                    text-align: center;
                    page-break-inside: avoid;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <div>T.C.</div>
                <div>${escape(ilIlce.uppercase(Locale("tr")))} MİLLÎ EĞİTİM MÜDÜRLÜĞÜ</div>
                <div>${escape(okulAdi.uppercase(Locale("tr")))}</div>
                <div class="doc-title">${escape(egitimYili.uppercase(Locale("tr")))} ${escape(donem.uppercase(Locale("tr")))} TÜRKÇE DERSİ ZÜMRE ÖĞRETMENLER KURULU TOPLANTI TUTANAĞI</div>
            </div>

            <table class="info-table">
                <tr>
                    <td class="label">Toplantı No / Dönem</td>
                    <td>${escape(toplantiNo)} / ${escape(donem)}</td>
                    <td class="label">Toplantı Tarihi & Saati</td>
                    <td>${escape(tarih)} - ${escape(saat)}</td>
                </tr>
                <tr>
                    <td class="label">Toplantı Yeri</td>
                    <td>${escape(yer)}</td>
                    <td class="label">Zümre Başkanı</td>
                    <td>${escape(baskan)}</td>
                </tr>
                <tr>
                    <td class="label">Katılımcı Öğretmenler</td>
                    <td colspan="3">${escape(allTeachers.joinToString(", ") { it.first })}</td>
                </tr>
            </table>

            <h3>GÜNDEM MADDELERİ</h3>
            <ol>
                $gundemListHtml
            </ol>

            <h3>GÖRÜŞMELER VE ALINAN KARARLAR</h3>
            $gorusmeVeKararlarHtml

            <div style="margin-top: 25px;">
                <p style="text-align: justify;">
                    İşbu toplantı tutanağı, Millî Eğitim Bakanlığı İlköğretim Kurumları Yönetmeliği hükümleri uyarınca 
                    tarafımızca tanzim edilerek müştereken imza altına alınmıştır.
                </p>
            </div>

            $signaturesHtml

            <div class="approval-box">
                <div>UYGUNDUR</div>
                <div style="margin-top: 4px;">... / ... / 202...</div>
                <div style="margin-top: 15px; font-weight: bold;">Okul Müdürü</div>
                <div style="font-size: 10pt; color: #555;">Mühür / İmza</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    fun generateZumreDilekceHtml(
        okulAdi: String,
        ilIlce: String,
        egitimYili: String,
        donem: String,
        tarih: String,
        saat: String,
        yer: String,
        baskan: String,
        gundemler: List<GundemItem>
    ): String {
        val selectedGundem = gundemler.filter { it.isSelected }
        val gundemListHtml = selectedGundem.mapIndexed { idx, item ->
            "<li>${escape(item.title)}</li>"
        }.joinToString("\n")

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Zümre Toplantı Onay Dilekçesi</title>
            <style>
                @page { size: A4; margin: 25mm 20mm 20mm 20mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 12pt;
                    color: #000;
                    line-height: 1.5;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 30px;
                }
                .date {
                    text-align: right;
                    margin-bottom: 25px;
                }
                .content {
                    text-align: justify;
                    text-indent: 28px;
                    margin-bottom: 20px;
                }
                ol { padding-left: 30px; margin-bottom: 25px; }
                li { margin-bottom: 6px; }
                .signature-box {
                    float: right;
                    width: 250px;
                    text-align: center;
                    margin-top: 30px;
                }
                .approval-box {
                    clear: both;
                    margin-top: 80px;
                    border-top: 1px solid #333;
                    padding-top: 15px;
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <div class="date">${escape(tarih)}</div>
            <div class="header">
                <div>${escape(okulAdi.uppercase(Locale("tr")))} MÜDÜRLÜĞÜNE</div>
                <div>${escape(ilIlce.uppercase(Locale("tr")))}</div>
            </div>

            <div class="content">
                ${escape(egitimYili)} Eğitim-Öğretim Yılı <strong>${escape(donem)}</strong> Türkçe Dersi Zümre Öğretmenler Kurulu toplantısının, 
                aşağıda belirtilen gündem maddelerini görüşmek üzere <strong>${escape(tarih)}</strong> tarihinde, 
                saat <strong>${escape(saat)}</strong>'de okulumuz <strong>${escape(yer)}</strong>'nde yapılması hususunu;
            </div>
            <div class="content" style="text-indent: 0; text-align: center; font-weight: bold;">
                Bilgilerinize ve olurlarınıza saygılarımla arz ederim.
            </div>

            <div class="signature-box">
                <div style="font-weight: bold;">${escape(baskan)}</div>
                <div>Türkçe Zümre Başkanı</div>
                <div style="margin-top: 40px;">İmza</div>
            </div>

            <div style="clear: both; margin-top: 25px;">
                <h4 style="margin-bottom: 8px;">TOPLANTI GÜNDEM MADDELERİ:</h4>
                <ol>
                    $gundemListHtml
                </ol>
            </div>

            <div class="approval-box">
                <div style="font-weight: bold;">O L U R</div>
                <div>... / ... / 202...</div>
                <div style="margin-top: 20px; font-weight: bold;">Okul Müdürü</div>
                <div style="font-size: 10pt; color: #555;">İmza / Mühür</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    // ----------------------------------------------------
    // 5. ŞÖK TUTANAĞI HTML ÜRETİCİSİ (TURKCEN.NET MODELİ)
    // ----------------------------------------------------

    fun generateSokTutanakHtml(
        okulAdi: String,
        ilIlce: String,
        egitimYili: String,
        kademe: String,
        sube: String,
        donem: String,
        tarih: String,
        saat: String,
        yer: String,
        sinifRehberi: String,
        mudurYardimcisi: String,
        bransOgretmenleri: List<String>,
        ogrenciListesi: List<SokStudentEvaluation>,
        alinanGenelKararlar: List<String>
    ): String {
        val ogrenciRowsHtml = ogrenciListesi.mapIndexed { idx, st ->
            """
            <tr>
                <td style="text-align: center; font-weight: bold;">${idx + 1}</td>
                <td style="text-align: center;">${escape(st.studentNo)}</td>
                <td style="font-weight: bold;">${escape(st.fullName)}</td>
                <td>${escape(st.attendanceStatus)}</td>
                <td>${escape(st.academicStatus)}</td>
                <td>${escape(st.behaviorStatus)}</td>
                <td style="background-color: #f9fbfd;"><strong>${escape(st.actionDecision)}</strong></td>
            </tr>
            """.trimIndent()
        }.joinToString("\n")

        val kararlarListHtml = alinanGenelKararlar.mapIndexed { idx, k ->
            "<li>${escape(k)}</li>"
        }.joinToString("\n")

        val allSigners = listOf(
            sinifRehberi to "Sınıf Rehber Öğretmeni",
            mudurYardimcisi to "Müdür Yardımcısı (Kurul Başkanı)"
        ) + bransOgretmenleri.filter { it.isNotBlank() }.map { it to "Branş Öğretmeni" }

        val signaturesHtml = allSigners.chunked(3).joinToString("\n") { chunk ->
            """
            <div style="display: flex; justify-content: space-around; margin-top: 20px; text-align: center; page-break-inside: avoid;">
                ${chunk.joinToString("\n") { (name, role) ->
                """
                    <div style="width: 30%;">
                        <div style="font-weight: bold; font-size: 11pt;">${escape(name)}</div>
                        <div style="font-size: 10pt; color: #555;">${escape(role)}</div>
                        <div style="margin-top: 30px; border-bottom: 1px dotted #888; width: 80%; margin-left: auto; margin-right: auto;"></div>
                        <div style="font-size: 9pt; color: #888; margin-top: 2px;">İmza</div>
                    </div>
                    """.trimIndent()
            }}
            </div>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Şube Öğretmenler Kurulu (ŞÖK) Tutanağı</title>
            <style>
                @page { size: A4; margin: 15mm 12mm 15mm 12mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 11pt;
                    color: #000;
                    line-height: 1.35;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 12px;
                }
                table.styled-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 8px;
                    margin-bottom: 12px;
                }
                table.styled-table th, table.styled-table td {
                    border: 1px solid #333;
                    padding: 5px 6px;
                    font-size: 10pt;
                }
                table.styled-table th {
                    background-color: #f1f3f4;
                    font-weight: bold;
                    text-align: center;
                }
                h3 {
                    font-size: 11.5pt;
                    margin-top: 14px;
                    margin-bottom: 6px;
                    color: #0d47a1;
                    border-bottom: 1px solid #0d47a1;
                    padding-bottom: 2px;
                }
                ol { padding-left: 20px; margin-top: 4px; }
                li { margin-bottom: 3px; }
            </style>
        </head>
        <body>
            <div class="header">
                <div>T.C.</div>
                <div>${escape(ilIlce.uppercase(Locale("tr")))} MİLLÎ EĞİTİM MÜDÜRLÜĞÜ</div>
                <div>${escape(okulAdi.uppercase(Locale("tr")))}</div>
                <div style="font-size: 12pt; text-decoration: underline; margin-top: 4px;">
                    ${escape(egitimYili)} EĞİTİM ÖĞRETİM YILI ${escape(donem.uppercase(Locale("tr")))}
                    <br>${escape(kademe.uppercase(Locale("tr")))} / ${escape(sube)} ŞUBESİ ŞUBE ÖĞRETMENLER KURULU (ŞÖK) TOPLANTI TUTANAĞI
                </div>
            </div>

            <table style="width: 100%; border-collapse: collapse; margin-bottom: 10px; font-size: 10.5pt;">
                <tr>
                    <td style="width: 20%; font-weight: bold;">Toplantı Tarihi/Saati:</td>
                    <td style="width: 30%;">${escape(tarih)} - ${escape(saat)}</td>
                    <td style="width: 20%; font-weight: bold;">Toplantı Yeri:</td>
                    <td style="width: 30%;">${escape(yer)}</td>
                </tr>
                <tr>
                    <td style="font-weight: bold;">Sınıf / Şube:</td>
                    <td>${escape(sube)}</td>
                    <td style="font-weight: bold;">Sınıf Rehber Öğretmeni:</td>
                    <td>${escape(sinifRehberi)}</td>
                </tr>
            </table>

            <h3>1. ÖĞRENCİ BAŞARI, DEVAMSIZLIK VE DAVRANIŞ DEĞERLENDİRME TABLOSU</h3>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th style="width: 4%;">Sıra</th>
                        <th style="width: 8%;">No</th>
                        <th style="width: 18%;">Adı Soyadı</th>
                        <th style="width: 16%;">Devamsızlık Durumu</th>
                        <th style="width: 18%;">Akademik Başarı / Dersler</th>
                        <th style="width: 18%;">Davranış / Rehberlik</th>
                        <th style="width: 18%;">Alınan Karar / Tedbir</th>
                    </tr>
                </thead>
                <tbody>
                    $ogrenciRowsHtml
                </tbody>
            </table>

            <h3>2. ŞÖK GENEL KURULUNDA ALINAN ORTAK KARARLAR</h3>
            <ol>
                $kararlarListHtml
            </ol>

            <div style="margin-top: 15px;">
                $signaturesHtml
            </div>

            <div style="margin-top: 25px; text-align: center; page-break-inside: avoid;">
                <div>UYGUNDUR</div>
                <div style="margin-top: 4px;">... / ... / 202...</div>
                <div style="margin-top: 15px; font-weight: bold;">Okul Müdürü</div>
                <div style="font-size: 9pt; color: #555;">Mühür / İmza</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    // ----------------------------------------------------
    // 6. VELİ TOPLANTISI, DAVETİYESİ VE İMZA SİRKÜSÜ MOTORU
    // ----------------------------------------------------

    fun generateVeliToplantiTutanakHtml(
        okulAdi: String,
        ilIlce: String,
        egitimYili: String,
        sube: String,
        donem: String,
        tarih: String,
        saat: String,
        derslik: String,
        ogretmen: String,
        gundemler: List<String>,
        gorusmeler: String,
        kararlar: String
    ): String {
        val gundemListHtml = gundemler.map { "<li>${escape(it)}</li>" }.joinToString("\n")

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Sınıf Veli Toplantısı Tutanağı</title>
            <style>
                @page { size: A4; margin: 18mm 15mm 18mm 15mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 11.5pt;
                    color: #000;
                    line-height: 1.4;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 15px;
                }
                .title {
                    font-size: 12.5pt;
                    text-decoration: underline;
                    margin-top: 5px;
                }
                h3 {
                    font-size: 12pt;
                    color: #880e4f;
                    border-bottom: 1px solid #880e4f;
                    margin-top: 16px;
                    margin-bottom: 6px;
                    padding-bottom: 2px;
                }
                ol { padding-left: 22px; margin-top: 4px; }
                li { margin-bottom: 4px; }
                .content-box {
                    text-align: justify;
                    white-space: pre-line;
                    line-height: 1.45;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <div>T.C.</div>
                <div>${escape(ilIlce.uppercase(Locale("tr")))} MİLLÎ EĞİTİM MÜDÜRLÜĞÜ</div>
                <div>${escape(okulAdi.uppercase(Locale("tr")))}</div>
                <div class="title">
                    ${escape(egitimYili)} EĞİTİM ÖĞRETİM YILI ${escape(donem.uppercase(Locale("tr")))}
                    <br>${escape(sube)} SINIFI VELİ TOPLANTISI TUTANAĞI
                </div>
            </div>

            <table style="width: 100%; border: 1px solid #555; border-collapse: collapse; margin-bottom: 12px; font-size: 11pt;">
                <tr>
                    <td style="border: 1px solid #555; padding: 6px; font-weight: bold; width: 25%; background: #fdf2f8;">Toplantı Tarihi / Saati:</td>
                    <td style="border: 1px solid #555; padding: 6px; width: 25%;">${escape(tarih)} - ${escape(saat)}</td>
                    <td style="border: 1px solid #555; padding: 6px; font-weight: bold; width: 25%; background: #fdf2f8;">Toplantı Yeri / Derslik:</td>
                    <td style="border: 1px solid #555; padding: 6px; width: 25%;">${escape(derslik)}</td>
                </tr>
                <tr>
                    <td style="border: 1px solid #555; padding: 6px; font-weight: bold; background: #fdf2f8;">Sınıf Rehber Öğretmeni:</td>
                    <td style="border: 1px solid #555; padding: 6px;">${escape(ogretmen)}</td>
                    <td style="border: 1px solid #555; padding: 6px; font-weight: bold; background: #fdf2f8;">Sınıf / Şube:</td>
                    <td style="border: 1px solid #555; padding: 6px;">${escape(sube)}</td>
                </tr>
            </table>

            <h3>GÜNDEM MADDELERİ</h3>
            <ol>
                $gundemListHtml
            </ol>

            <h3>GÜNDEM MADDELERİNİN GÖRÜŞÜLMESİ</h3>
            <div class="content-box">
                ${escape(gorusmeler)}
            </div>

            <h3>ALINAN ORTAK KARARLAR</h3>
            <div class="content-box">
                ${escape(kararlar)}
            </div>

            <div style="display: flex; justify-content: space-between; margin-top: 35px; text-align: center; page-break-inside: avoid;">
                <div style="width: 45%;">
                    <div style="font-weight: bold;">Seçilen Sınıf Veli Temsilcisi</div>
                    <div style="margin-top: 30px;">İmza</div>
                </div>
                <div style="width: 45%;">
                    <div style="font-weight: bold;">${escape(ogretmen)}</div>
                    <div>Sınıf Rehber Öğretmeni</div>
                    <div style="margin-top: 30px;">İmza</div>
                </div>
            </div>

            <div style="margin-top: 30px; text-align: center; page-break-inside: avoid;">
                <div>UYGUNDUR</div>
                <div style="margin-top: 4px;">... / ... / 202...</div>
                <div style="margin-top: 15px; font-weight: bold;">Okul Müdürü</div>
                <div style="font-size: 10pt; color: #555;">Mühür / İmza</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    fun generateVeliDavetiyeHtml(
        okulAdi: String,
        sube: String,
        donem: String,
        tarih: String,
        saat: String,
        derslik: String,
        ogretmen: String,
        gundemler: List<String>
    ): String {
        val gundemListHtml = gundemler.take(5).map { "<li>${escape(it)}</li>" }.joinToString("\n")

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Veli Toplantısı Davetiyesi</title>
            <style>
                @page { size: A4; margin: 15mm 15mm 15mm 15mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 11pt;
                    color: #000;
                    line-height: 1.4;
                }
                .ticket-card {
                    border: 2px solid #880e4f;
                    padding: 16px;
                    border-radius: 8px;
                    background-color: #fffafc;
                }
                .cut-line {
                    border-top: 2px dashed #888;
                    margin: 25px 0 20px 0;
                    text-align: center;
                    position: relative;
                }
                .cut-badge {
                    position: relative;
                    top: -12px;
                    background: #fff;
                    padding: 0 12px;
                    font-size: 10pt;
                    color: #666;
                    font-style: italic;
                }
            </style>
        </head>
        <body>
            <div class="ticket-card">
                <div style="text-align: center; font-weight: bold; margin-bottom: 8px;">
                    <div>${escape(okulAdi.uppercase(Locale("tr")))}</div>
                    <div style="font-size: 12pt; color: #880e4f; margin-top: 3px;">
                        ${escape(sube)} SINIFI ${escape(donem.uppercase(Locale("tr")))} VELİ TOPLANTISI DAVETİYESİ
                    </div>
                </div>

                <p style="text-align: justify; text-indent: 20px; margin: 10px 0;">
                    <strong>Sayın Velimiz,</strong><br>
                    Geleceğimizin teminatı olan çocuklarımızın eğitim-öğretim durumlarını değerlendirmek, 
                    akademik ve sosyal gelişimlerini güçlendirmek amacıyla okulumuzda sınıf veli toplantısı yapılacaktır. 
                    Toplantıya katılımınız öğrencimizin başarısı açısından büyük önem taşımaktadır.
                </p>

                <div style="background: #fff; border: 1px solid #ddd; padding: 10px; margin: 10px 0; border-radius: 4px;">
                    <div style="display: flex; justify-content: space-between;">
                        <div><strong>Tarih:</strong> ${escape(tarih)}</div>
                        <div><strong>Saat:</strong> ${escape(saat)}</div>
                        <div><strong>Yer:</strong> ${escape(derslik)}</div>
                    </div>
                </div>

                <div style="margin-top: 8px;">
                    <strong>Başlıca Gündem Maddeleri:</strong>
                    <ul style="margin: 4px 0; padding-left: 20px;">
                        $gundemListHtml
                    </ul>
                </div>

                <div style="display: flex; justify-content: space-between; margin-top: 20px; text-align: center;">
                    <div style="width: 45%;">
                        <div style="font-weight: bold;">${escape(ogretmen)}</div>
                        <div style="font-size: 10pt;">Sınıf Rehber Öğretmeni</div>
                    </div>
                    <div style="width: 45%;">
                        <div style="font-weight: bold;">Okul Müdürü</div>
                        <div style="font-size: 10pt;">İmza / Mühür</div>
                    </div>
                </div>
            </div>

            <!-- KESME ÇİZGİSİ (KOPARMA KUPONU) -->
            <div class="cut-line">
                <span class="cut-badge">✂ Buradan kesip imzalı olarak sınıf öğretmenine geri gönderiniz ✂</span>
            </div>

            <div style="border: 1px solid #333; padding: 14px; border-radius: 6px; background: #fdfdfd;">
                <div style="text-align: center; font-weight: bold; margin-bottom: 8px;">
                    ${escape(okulAdi.uppercase(Locale("tr")))} ${escape(sube)} SINIFI VELİ GERİ BİLDİRİM KUPONU
                </div>
                <p style="margin: 6px 0;">
                    ${escape(tarih)} tarihinde yapılacak olan sınıf veli toplantısı davetiyesini aldım.
                </p>
                <div style="margin: 10px 0;">
                    <label>[ &nbsp; ] Toplantıya <strong>KATILACAĞIM</strong>.</label><br>
                    <label>[ &nbsp; ] Mazeretim nedeniyle <strong>KATILAMAYACAĞIM</strong>. Mazeretim: .....................................................</label>
                </div>
                <div style="display: flex; justify-content: space-between; margin-top: 15px;">
                    <div><strong>Öğrencinin Adı Soyadı / No:</strong> .....................................................</div>
                    <div><strong>Velinin Adı Soyadı:</strong> ........................................ <strong>İmza:</strong> ................</div>
                </div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    fun generateVeliImzaListesiHtml(
        okulAdi: String,
        sube: String,
        donem: String,
        tarih: String,
        ogretmen: String,
        veliListesi: List<VeliAttendanceItem>
    ): String {
        // En az 25 satırlı resmi çizelge
        val totalRows = maxOf(veliListesi.size, 25)
        val rowsHtml = (0 until totalRows).map { idx ->
            val item = veliListesi.getOrNull(idx)
            """
            <tr>
                <td style="text-align: center; height: 26px;">${idx + 1}</td>
                <td style="text-align: center;">${escape(item?.studentNo ?: "")}</td>
                <td style="font-weight: 500;">${escape(item?.studentName ?: "")}</td>
                <td>${escape(item?.parentName ?: "")}</td>
                <td style="text-align: center;">${escape(item?.phone ?: "")}</td>
                <td style="text-align: center;"></td>
            </tr>
            """.trimIndent()
        }.joinToString("\n")

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Veli Toplantısı Katılım ve İmza Listesi</title>
            <style>
                @page { size: A4; margin: 15mm 12mm 15mm 12mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 11pt;
                    color: #000;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 12px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                }
                th, td {
                    border: 1px solid #333;
                    padding: 4px 6px;
                    font-size: 10pt;
                }
                th {
                    background-color: #f1f3f4;
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <div>${escape(okulAdi.uppercase(Locale("tr")))}</div>
                <div style="font-size: 12pt; text-decoration: underline; margin-top: 4px;">
                    ${escape(sube)} SINIFI ${escape(donem.uppercase(Locale("tr")))} VELİ TOPLANTISI KATILIM VE İMZA LİSTESİ
                </div>
                <div style="font-size: 10pt; font-weight: normal; margin-top: 4px;">
                    Toplantı Tarihi: ${escape(tarih)} | Sınıf Rehber Öğretmeni: ${escape(ogretmen)}
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th style="width: 6%;">Sıra</th>
                        <th style="width: 10%;">Öğr. No</th>
                        <th style="width: 28%;">Öğrencinin Adı Soyadı</th>
                        <th style="width: 28%;">Toplantıya Katılan Veli (Adı Soyadı & Yakınlığı)</th>
                        <th style="width: 16%;">İletişim (Telefon)</th>
                        <th style="width: 12%;">İmza</th>
                    </tr>
                </thead>
                <tbody>
                    $rowsHtml
                </tbody>
            </table>

            <div style="margin-top: 20px; float: right; width: 250px; text-align: center;">
                <div style="font-weight: bold;">${escape(ogretmen)}</div>
                <div>Sınıf Rehber Öğretmeni</div>
                <div style="margin-top: 30px;">İmza</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    // ----------------------------------------------------
    // 7. BİREYSEL VELİ GÖRÜŞME TUTANAĞI HTML MOTORU
    // ----------------------------------------------------

    fun generateVeliGorusmeHtml(
        okulAdi: String,
        sube: String,
        ogrenciNo: String,
        ogrenciAdSoyad: String,
        veliAdSoyad: String,
        yakinlik: String,
        tarih: String,
        saat: String,
        gorusmeNedeni: String,
        gorusulenKonular: String,
        alinanKararlar: String,
        ogretmen: String
    ): String {
        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Bireysel Veli Görüşme Tutanağı</title>
            <style>
                @page { size: A4; margin: 20mm 18mm 20mm 18mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 11.5pt;
                    line-height: 1.45;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 20px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 16px;
                }
                td {
                    border: 1px solid #444;
                    padding: 7px 10px;
                }
                td.label {
                    font-weight: bold;
                    background-color: #f8f9fa;
                    width: 30%;
                }
                .section {
                    margin-top: 14px;
                }
                .section-title {
                    font-weight: bold;
                    color: #1a237e;
                    border-bottom: 1px solid #1a237e;
                    margin-bottom: 6px;
                    padding-bottom: 2px;
                }
                .box {
                    border: 1px solid #888;
                    padding: 10px;
                    min-height: 80px;
                    border-radius: 4px;
                    white-space: pre-line;
                    text-align: justify;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <div>${escape(okulAdi.uppercase(Locale("tr")))} MÜDÜRLÜĞÜ</div>
                <div style="font-size: 12.5pt; text-decoration: underline; margin-top: 6px;">
                    BİREYSEL VELİ GÖRÜŞME KAYIT TUTANAĞI
                </div>
            </div>

            <table>
                <tr>
                    <td class="label">Öğrencinin Adı Soyadı / No:</td>
                    <td>${escape(ogrenciAdSoyad)} (${escape(ogrenciNo)}) - ${escape(sube)}</td>
                </tr>
                <tr>
                    <td class="label">Görüşülen Velinin Adı Soyadı:</td>
                    <td>${escape(veliAdSoyad)} (${escape(yakinlik)})</td>
                </tr>
                <tr>
                    <td class="label">Görüşme Tarihi ve Saati:</td>
                    <td>${escape(tarih)} - ${escape(saat)}</td>
                </tr>
                <tr>
                    <td class="label">Görüşmeyi Yapan Öğretmen:</td>
                    <td>${escape(ogretmen)}</td>
                </tr>
                <tr>
                    <td class="label">Görüşme Nedeni:</td>
                    <td><strong>${escape(gorusmeNedeni)}</strong></td>
                </tr>
            </table>

            <div class="section">
                <div class="section-title">1. VELİ İLE GÖRÜŞÜLEN HUSUSLAR VE TESPİTLER:</div>
                <div class="box">${escape(gorusulenKonular)}</div>
            </div>

            <div class="section">
                <div class="section-title">2. ALINAN KARARLAR VE EYLEM PLANI:</div>
                <div class="box">${escape(alinanKararlar)}</div>
            </div>

            <div style="display: flex; justify-content: space-between; margin-top: 50px; text-align: center; page-break-inside: avoid;">
                <div style="width: 40%;">
                    <div style="font-weight: bold;">${escape(veliAdSoyad)}</div>
                    <div style="font-size: 10.5pt; color: #555;">Öğrenci Velisi</div>
                    <div style="margin-top: 40px;">İmza</div>
                </div>
                <div style="width: 40%;">
                    <div style="font-weight: bold;">${escape(ogretmen)}</div>
                    <div style="font-size: 10.5pt; color: #555;">Türkçe / Sınıf Öğretmeni</div>
                    <div style="margin-top: 40px;">İmza</div>
                </div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    // ----------------------------------------------------
    // 8. DİĞER RESMÎ TUTANAKLAR (BEP, KULÜP, SINAV TESLİM VB.)
    // ----------------------------------------------------

    fun generateQuickDocumentHtml(
        title: String,
        okulAdi: String,
        content: String,
        signers: List<Pair<String, String>>
    ): String {
        val signaturesHtml = signers.chunked(3).joinToString("\n") { chunk ->
            """
            <div style="display: flex; justify-content: space-around; margin-top: 30px; text-align: center; page-break-inside: avoid;">
                ${chunk.joinToString("\n") { (name, role) ->
                """
                    <div style="width: 30%;">
                        <div style="font-weight: bold;">${escape(name)}</div>
                        <div style="font-size: 10.5pt; color: #555;">${escape(role)}</div>
                        <div style="margin-top: 35px; border-bottom: 1px dotted #888; width: 75%; margin-left: auto; margin-right: auto;"></div>
                        <div style="font-size: 9.5pt; color: #777; margin-top: 3px;">İmza</div>
                    </div>
                    """.trimIndent()
            }}
            </div>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${escape(title)}</title>
            <style>
                @page { size: A4; margin: 20mm 15mm 20mm 15mm; }
                body {
                    font-family: 'Times New Roman', Times, serif;
                    font-size: 12pt;
                    color: #000;
                    line-height: 1.5;
                }
                .header {
                    text-align: center;
                    font-weight: bold;
                    margin-bottom: 25px;
                }
                .title {
                    font-size: 13pt;
                    text-decoration: underline;
                    margin-top: 6px;
                }
                .content {
                    text-align: justify;
                    white-space: pre-line;
                    line-height: 1.5;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <div>T.C.</div>
                <div>${escape(okulAdi.uppercase(Locale("tr")))} MÜDÜRLÜĞÜ</div>
                <div class="title">${escape(title.uppercase(Locale("tr")))}</div>
            </div>

            <div class="content">
                ${escape(content)}
            </div>

            $signaturesHtml
        </body>
        </html>
        """.trimIndent()
    }

    private fun escape(text: String): String {
        return text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;")
    }
}
