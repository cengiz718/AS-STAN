package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.GundemItem
import com.example.data.curriculum.SokStudentEvaluation
import com.example.data.curriculum.TutanakGeneratorRepository
import com.example.data.curriculum.VeliAttendanceItem
import com.example.data.web.CloudTutanakRepository
import com.example.util.PrintUtils

enum class TutanakToolTab(val title: String, val subtitle: String) {
    ZUMRE("Zümre Tutanağı & Dilekçe", "Sene başı, 2. dönem ve sene sonu zümresi"),
    SOK("ŞÖK (Şube Kurulu)", "5-8. sınıflar öğrenci değerlendirme ve kararlar"),
    VELI("Veli Toplantısı & Davetiye", "Toplantı tutanağı, kuponlu davetiye ve imza listesi"),
    KURUL_VE_BEP("Kurul & BEP", "Öğretmenler kurulu ve BEP birimi tutanağı"),
    KULUP_VE_OLAY("Kulüp & Teslim", "Kütüphane kulübü, sınav teslim ve olay tutanağı")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutanakBuilderScreen() {
    val context = LocalContext.current
    var activeTab by remember { mutableStateOf(TutanakToolTab.ZUMRE) }

    // Genel Okul ve Kurul Parametreleri - Başöğretmen Zeynep Akdağ / Emniyet Ortaokulu
    var okulAdi by remember { mutableStateOf("Emniyet Ortaokulu") }
    var ilIlce by remember { mutableStateOf("Adıyaman / Merkez") }
    var egitimYili by remember { mutableStateOf("2026-2027") }
    var tarih by remember { mutableStateOf("15.09.2026") }
    var saat by remember { mutableStateOf("15:30") }
    var yer by remember { mutableStateOf("Öğretmenler Odası") }
    var zümreBaskani by remember { mutableStateOf("Başöğretmen Zeynep Akdağ (Zümre Başkanı)") }
    var katilimciOgretmenlerText by remember { mutableStateOf("Ahmet Yılmaz, Fatma Şahin, Mehmet Çelik") }

    // 1. Zümre Sekmesi Durumları
    var zumreDonem by remember { mutableStateOf("Sene Başı") }
    var zumreSubDoc by remember { mutableStateOf("Tutanak") } // "Tutanak" veya "Dilekçe"
    var zumreGundemler by remember {
        mutableStateOf(TutanakGeneratorRepository.getStandardZumreGundemler("Sene Başı"))
    }
    var showAddGundemDialog by remember { mutableStateOf(false) }
    var newGundemTitle by remember { mutableStateOf("") }
    var newGundemDiscussion by remember { mutableStateOf("") }
    var newGundemDecision by remember { mutableStateOf("") }

    // 2. ŞÖK Sekmesi Durumları
    var sokKademe by remember { mutableStateOf("8. Sınıf") }
    var sokSube by remember { mutableStateOf("8/A") }
    var sokDonem by remember { mutableStateOf("1. Dönem") }
    var sokSinifRehberi by remember { mutableStateOf("Başöğretmen Zeynep Akdağ (Türkçe Öğretmeni)") }
    var sokMudurYardimcisi by remember { mutableStateOf("Kemal Yıldız (Müdür Yardımcısı)") }
    var sokStudents by remember {
        mutableStateOf(TutanakGeneratorRepository.getSampleSokStudents())
    }
    var showAddStudentDialog by remember { mutableStateOf(false) }
    var newStudentNo by remember { mutableStateOf("") }
    var newStudentName by remember { mutableStateOf("") }
    var newStudentAttendance by remember { mutableStateOf("Devamsızlığı yok") }
    var newStudentAcademic by remember { mutableStateOf("Türkçe dersi iyi, matematikten desteklenmeli") }
    var newStudentBehavior by remember { mutableStateOf("Uyumlu ve saygılı") }
    var newStudentDecision by remember { mutableStateOf("DYK kursuna yönlendirildi") }

    // 3. Veli Toplantısı Sekmesi Durumları
    var veliSubDoc by remember { mutableStateOf("Toplantı Tutanağı") } // "Toplantı Tutanağı", "Davetiye Kuponu", "İmza Listesi", "Bireysel Görüşme"
    var veliSube by remember { mutableStateOf("8/A") }
    var veliDonem by remember { mutableStateOf("1. Dönem") }
    var veliDerslik by remember { mutableStateOf("8/A Dersliği") }
    var veliOgretmen by remember { mutableStateOf("Başöğretmen Zeynep Akdağ (Sınıf Rehber Öğretmeni)") }
    var veliGundemler by remember {
        mutableStateOf(TutanakGeneratorRepository.getStandardVeliGundemler())
    }
    var veliGorusmelerText by remember {
        mutableStateOf(
            """
            1. Velilerle tanışıldı, yeni eğitim-öğretim yılının hayırlı olması temenni edildi.
            2. Okul giriş-çıkış saatleri ve kılık-kıyafet kurallarına uyulmasının önemine değinildi.
            3. E-Okul sisteminin velilerce haftalık takip edilmesi ve devamsızlık durumunun anlık izlenmesi rica edildi.
            4. Evde sabit ve sessiz bir ders çalışma ortamının oluşturulması, günlük en az 30 dakika kitap okuma saati yapılması kararlaştırıldı.
            5. Akıllı telefon ve sosyal medya kullanımının sınırlandırılması, gece geç saatlerde ekran kullanımının engellenmesi tavsiye edildi.
            6. 8. Sınıf LGS hazırlık sürecinde deneme sınavlarının analizi ve moral desteğinin önemi vurgulandı.
            """.trimIndent()
        )
    }
    var veliKararlarText by remember {
        mutableStateOf(
            """
            1. Öğrencilerin derslere hazırlıklı gelmesi için veliler tarafından ödev ve çanta kontrolü yapılmasına,
            2. Her gün akşam saat 20:30 - 21:15 arası ailece 'Televizyon ve Telefonu Kapat, Kitap Oku' saati uygulanmasına,
            3. Sınıf veli temsilciliğine oy birliğiyle Murat Çetin'in seçilmesine,
            4. Şüpheli devamsızlık veya ders düşüşü durumunda sınıf öğretmeniyle vakit kaybetmeden iletişime geçilmesine oy birliğiyle karar verilmiştir.
            """.trimIndent()
        )
    }
    var veliAttendanceList by remember {
        mutableStateOf(TutanakGeneratorRepository.getSampleVeliAttendanceList())
    }

    // Bireysel Veli Görüşme Alanları
    var bireyselOgrNo by remember { mutableStateOf("142") }
    var bireyselOgrAd by remember { mutableStateOf("Ahmet Yılmaz") }
    var bireyselVeliAd by remember { mutableStateOf("Hasan Yılmaz") }
    var bireyselYakinlik by remember { mutableStateOf("Babası") }
    var bireyselNeden by remember { mutableStateOf("Ders İçi Dikkat Dağınıklığı ve Ödev Takibi") }
    var bireyselGorusulen by remember {
        mutableStateOf("Öğrencinin son iki haftadır ders sırasında odaklanmakta zorlandığı, ödevlerini eksik getirdiği veliye aktarıldı. Evdeki teknoloji kullanımı soruldu.")
    }
    var bireyselKarar by remember {
        mutableStateOf("Veli, evde tablet ve telefon kullanımını günde 45 dakika ile sınırlandıracağını taahhüt etti. Rehberlik servisinden haftalık çalışma programı alınmasına karar verildi.")
    }

    // 4 & 5. Hızlı Tutanaklar
    var hizliTutanakType by remember { mutableStateOf("BEP Birimi Toplantısı") }

    // Helpers
    val katilimciList = remember(katilimciOgretmenlerText) {
        katilimciOgretmenlerText.split(",").map { it.trim() }.filter { it.isNotBlank() }
    }

    // Compute Live Generated HTML
    val currentHtml: String = remember(
        activeTab, okulAdi, ilIlce, egitimYili, tarih, saat, yer, zümreBaskani, katilimciList,
        zumreDonem, zumreSubDoc, zumreGundemler,
        sokKademe, sokSube, sokDonem, sokSinifRehberi, sokMudurYardimcisi, sokStudents,
        veliSubDoc, veliSube, veliDonem, veliDerslik, veliOgretmen, veliGundemler, veliGorusmelerText, veliKararlarText, veliAttendanceList,
        bireyselOgrNo, bireyselOgrAd, bireyselVeliAd, bireyselYakinlik, bireyselNeden, bireyselGorusulen, bireyselKarar,
        hizliTutanakType
    ) {
        when (activeTab) {
            TutanakToolTab.ZUMRE -> {
                if (zumreSubDoc == "Dilekçe") {
                    TutanakGeneratorRepository.generateZumreDilekceHtml(
                        okulAdi = okulAdi,
                        ilIlce = ilIlce,
                        egitimYili = egitimYili,
                        donem = zumreDonem,
                        tarih = tarih,
                        saat = saat,
                        yer = yer,
                        baskan = zümreBaskani,
                        gundemler = zumreGundemler
                    )
                } else {
                    TutanakGeneratorRepository.generateZumreTutanakHtml(
                        okulAdi = okulAdi,
                        ilIlce = ilIlce,
                        egitimYili = egitimYili,
                        donem = zumreDonem,
                        toplantiNo = "1",
                        tarih = tarih,
                        saat = saat,
                        yer = yer,
                        baskan = zümreBaskani,
                        katilimcilar = katilimciList,
                        gundemler = zumreGundemler
                    )
                }
            }
            TutanakToolTab.SOK -> {
                TutanakGeneratorRepository.generateSokTutanakHtml(
                    okulAdi = okulAdi,
                    ilIlce = ilIlce,
                    egitimYili = egitimYili,
                    kademe = sokKademe,
                    sube = sokSube,
                    donem = sokDonem,
                    tarih = tarih,
                    saat = saat,
                    yer = yer,
                    sinifRehberi = sokSinifRehberi,
                    mudurYardimcisi = sokMudurYardimcisi,
                    bransOgretmenleri = katilimciList,
                    ogrenciListesi = sokStudents,
                    alinanGenelKararlar = listOf(
                        "Ders başarısı düşük öğrencilerin velileri ile birebir görüşme yapılmasına,",
                        "Öğrencilerin okul kütüphanesinden daha etkin yararlanması için kitap okuma saatlerinin aksatılmamasına,",
                        "Sürekli devamsızlık riski taşıyan öğrencilerin ailelerine resmî mektup gönderilmesine,",
                        "8. Sınıf öğrencilerine yönelik LGS motivasyon ve test çözme teknikleri semineri düzenlenmesine oy birliğiyle karar verilmiştir."
                    )
                )
            }
            TutanakToolTab.VELI -> {
                when (veliSubDoc) {
                    "Davetiye Kuponu" -> {
                        TutanakGeneratorRepository.generateVeliDavetiyeHtml(
                            okulAdi = okulAdi,
                            sube = veliSube,
                            donem = veliDonem,
                            tarih = tarih,
                            saat = saat,
                            derslik = veliDerslik,
                            ogretmen = veliOgretmen,
                            gundemler = veliGundemler
                        )
                    }
                    "İmza Listesi" -> {
                        TutanakGeneratorRepository.generateVeliImzaListesiHtml(
                            okulAdi = okulAdi,
                            sube = veliSube,
                            donem = veliDonem,
                            tarih = tarih,
                            ogretmen = veliOgretmen,
                            veliListesi = veliAttendanceList
                        )
                    }
                    "Bireysel Görüşme" -> {
                        TutanakGeneratorRepository.generateVeliGorusmeHtml(
                            okulAdi = okulAdi,
                            sube = veliSube,
                            ogrenciNo = bireyselOgrNo,
                            ogrenciAdSoyad = bireyselOgrAd,
                            veliAdSoyad = bireyselVeliAd,
                            yakinlik = bireyselYakinlik,
                            tarih = tarih,
                            saat = saat,
                            gorusmeNedeni = bireyselNeden,
                            gorusulenKonular = bireyselGorusulen,
                            alinanKararlar = bireyselKarar,
                            ogretmen = veliOgretmen
                        )
                    }
                    else -> {
                        TutanakGeneratorRepository.generateVeliToplantiTutanakHtml(
                            okulAdi = okulAdi,
                            ilIlce = ilIlce,
                            egitimYili = egitimYili,
                            sube = veliSube,
                            donem = veliDonem,
                            tarih = tarih,
                            saat = saat,
                            derslik = veliDerslik,
                            ogretmen = veliOgretmen,
                            gundemler = veliGundemler,
                            gorusmeler = veliGorusmelerText,
                            kararlar = veliKararlarText
                        )
                    }
                }
            }
            TutanakToolTab.KURUL_VE_BEP -> {
                if (hizliTutanakType.contains("BEP")) {
                    TutanakGeneratorRepository.generateQuickDocumentHtml(
                        title = "BİREYSELLEŞTİRİLMİŞ EĞİTİM PROGRAMI (BEP) GELİŞTİRME BİRİMİ TOPLANTI TUTANAĞI",
                        okulAdi = okulAdi,
                        content = """
                        Toplantı Tarihi: $tarih | Saat: $saat | Yer: Rehberlik Servisi
                        
                        1. Özel eğitim ihtiyacı olan kaynaştırma öğrencilerinin eğitsel değerlendirme ve BEP planları incelendi.
                        2. Türkçe dersi kazanımlarının öğrencinin öğrenme hızına göre sadeleştirilmesine karar verildi.
                        3. Sınavlarda ek süre verilmesi, çoktan seçmeli veya kısa cevaplı sorular tercih edilmesi kararlaştırıldı.
                        4. Öğrencinin sosyal kabulünü ve motivasyonunu artıracak sınıf içi görevler verilmesi uygun görüldü.
                        """.trimIndent(),
                        signers = listOf(
                            "Kemal Yıldız" to "Birim Başkanı / Md. Yrd.",
                            zümreBaskani to "Branş Öğretmeni",
                            "Selin Can" to "Rehber Öğretmen",
                            "Ahmet Yılmaz Velisi" to "Öğrenci Velisi"
                        )
                    )
                } else {
                    TutanakGeneratorRepository.generateQuickDocumentHtml(
                        title = "2026-2027 EĞİTİM ÖĞRETİM YILI ÖĞRETMENLER KURULU TOPLANTI TUTANAĞI",
                        okulAdi = okulAdi,
                        content = """
                        Toplantı Tarihi: $tarih | Saat: $saat | Yer: Konferans Salonu
                        
                        GÜNDEM VE ALINAN GENEL KARARLAR:
                        1. Saygı duruşu ve İstiklal Marşı'nın ardından toplantı Okul Müdürü tarafından açıldı.
                        2. MEB Türkiye Yüzyılı Maarif Modeli çerçevesinde tüm derslerin erdem-değer-eylem ekseninde işlenmesine karar verildi.
                        3. Öğrenci devam takibi, nöbet hizmetleri ve okul güvenliği konularında azami hassasiyet gösterilmesi kararlaştırıldı.
                        4. Okul-Aile iş birliğinin güçlendirilmesi ve veli toplantılarının belirtilen takvimde yapılması uygun görüldü.
                        """.trimIndent(),
                        signers = listOf(
                            "Okul Müdürü" to "Kurul Başkanı",
                            "Yazman Öğretmen" to "Kurul Yazmanı",
                            zümreBaskani to "Zümre Başkanı"
                        )
                    )
                }
            }
            TutanakToolTab.KULUP_VE_OLAY -> {
                when {
                    hizliTutanakType.contains("Sınav Teslim") -> {
                        TutanakGeneratorRepository.generateQuickDocumentHtml(
                            title = "TÜRKÇE DERSİ ORTAK YAZILI SINAV EVRAKI TESLİM TUTANAĞI",
                            okulAdi = okulAdi,
                            content = """
                            Teslim Tarihi: $tarih
                            
                            $egitimYili Eğitim Öğretim Yılı $sokSube sınıfı Türkçe Dersi Ortak Yazılı Sınavına ait;
                            • Sınav Soru ve Cevap Anahtarları (1 Nüsha),
                            • Puanlama Rubriği / Not Baremi (1 Nüsha),
                            • Öğrenci Sınav Cevap Kağıtları (Toplam: 28 Adet),
                            • Sınav Analiz ve Kazanım Başarı Raporu (1 Nüsha)
                            
                            eksiksiz ve imzalı olarak okul idaresine teslim edilmiştir.
                            """.trimIndent(),
                            signers = listOf(
                                zümreBaskani to "Ders Öğretmeni (Teslim Eden)",
                                "Müdür Yardımcısı" to "Okul İdaresi (Teslim Alan)"
                            )
                        )
                    }
                    hizliTutanakType.contains("Olay") -> {
                        TutanakGeneratorRepository.generateQuickDocumentHtml(
                            title = "OKUL / DERS İÇİ OLAY VE TESPİT TUTANAĞI",
                            okulAdi = okulAdi,
                            content = """
                            Olay Tarihi ve Saati: $tarih - $saat
                            Olay Yeri: $yer
                            
                            Yukarıda belirtilen yer ve saatte, ders esnasında gerçekleşen durum aşağıda tarafsızca tutanak altına alınmıştır:
                            Öğrenciler arasında ders düzenini bozan sözlü tartışma yaşanmış, ders öğretmeni tarafından anında müdahale edilerek taraflar dinlenmiştir. Olayın mahiyeti nöbetçi müdür yardımcısına ve okul rehberlik servisine bildirilmiştir.
                            
                            İşbu tutanak mahallinde tanzim edilerek müştereken imza altına alınmıştır.
                            """.trimIndent(),
                            signers = listOf(
                                "Nöbetçi Öğretmen" to "Görgü Tanığı",
                                zümreBaskani to "Ders Öğretmeni",
                                "Kemal Yıldız" to "Müdür Yardımcısı"
                            )
                        )
                    }
                    else -> {
                        TutanakGeneratorRepository.generateQuickDocumentHtml(
                            title = "KÜTÜPHANECİLİK VE KÜLTÜR-EDEBİYAT KULÜBÜ TOPLANTI TUTANAĞI",
                            okulAdi = okulAdi,
                            content = """
                            Toplantı Tarihi: $tarih | Saat: $saat | Yer: Okul Kütüphanesi
                            
                            GÜNDEM VE ALINAN KARARLAR:
                            1. Kulüp yönetim ve denetim kurullarına asil ve yedek öğrenci seçimleri yapıldı.
                            2. Kütüphanedeki kitapların tasnifi, barkodlanması ve raf düzeni için öğrenci nöbet çizelgesi hazırlandı.
                            3. 'Kütüphaneler Haftası' kutlama programı ve 'Ayın En Çok Okuyan Kitap Kurdu' ödüllendirmesi planlandı.
                            4. Okul genelinde 'Bir Kitap da Sen Bağışla' kampanyası başlatılması kararlaştırıldı.
                            """.trimIndent(),
                            signers = listOf(
                                zümreBaskani to "Kulüp Danışman Öğretmeni",
                                "Zeynep Demir (8/A)" to "Kulüp Başkanı Öğrenci",
                                "Okul Müdürü" to "Onay"
                            )
                        )
                    }
                }
            }
        }
    }

    var showFormSettings by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("tutanak_builder_screen")
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Hero Header
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "turkcen.net MEB ARACI",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        // Fast Print Button
                        FilledTonalButton(
                            onClick = {
                                PrintUtils.printHtml(
                                    context = context,
                                    htmlContent = currentHtml,
                                    jobName = "Tutanak_${activeTab.name}"
                                )
                            },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("A4 Yazdır / PDF", fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Resmî Tutanak Oluşturma Araçları",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Zümre, ŞÖK, Veli Toplantısı, Kesilebilir Davetiye, BEP ve Dilekçeleri anında doldurun, antetli A4 formatında indirin.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { showFormSettings = !showFormSettings },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                if (showFormSettings) Icons.Default.ExpandLess else Icons.Default.Tune,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (showFormSettings) "Gizle" else "Ayarlar")
                        }

                        Button(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Tutanak Metni", currentHtml.replace(Regex("<[^>]*>"), " ").trim())
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Tutanak metni panoya kopyalandı!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Kopyala")
                        }

                        FilledTonalButton(
                            onClick = {
                                CloudTutanakRepository.saveAndBroadcastTutanak(
                                    id = "tutanak_${activeTab.name.lowercase()}_${System.currentTimeMillis()}",
                                    title = when (activeTab) {
                                        TutanakToolTab.ZUMRE -> "$egitimYili $zumreDonem Türkçe Zümre Tutanağı"
                                        TutanakToolTab.SOK -> "$egitimYili $sokSube ŞÖK Değerlendirme Tutanağı"
                                        TutanakToolTab.VELI -> "$egitimYili $veliSube Veli Toplantısı - $veliSubDoc"
                                        else -> "$egitimYili $hizliTutanakType"
                                    },
                                    type = activeTab.title,
                                    schoolName = okulAdi,
                                    authorName = zümreBaskani,
                                    term = egitimYili,
                                    date = tarih,
                                    htmlContent = currentHtml,
                                    onSuccess = {
                                        Toast.makeText(context, "Tutanak buluta kaydedildi ve TÜM cihazlarda güncellendi!", Toast.LENGTH_LONG).show()
                                    },
                                    onError = { err ->
                                        Toast.makeText(context, "Kayıt hatası: $err", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            },
                            modifier = Modifier.weight(1.3f)
                        ) {
                            Icon(Icons.Default.CloudUpload, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Buluta Dağıt", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Expandable Global Form Settings
        item {
            AnimatedVisibility(visible = showFormSettings) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Kurum ve Toplantı Bilgileri",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = okulAdi,
                                onValueChange = { okulAdi = it },
                                label = { Text("Okul Adı") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = ilIlce,
                                onValueChange = { ilIlce = it },
                                label = { Text("İl / İlçe") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = egitimYili,
                                onValueChange = { egitimYili = it },
                                label = { Text("Eğitim Yılı") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = tarih,
                                onValueChange = { tarih = it },
                                label = { Text("Tarih") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = saat,
                                onValueChange = { saat = it },
                                label = { Text("Saat") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        OutlinedTextField(
                            value = yer,
                            onValueChange = { yer = it },
                            label = { Text("Toplantı Yeri / Derslik") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = zümreBaskani,
                            onValueChange = { zümreBaskani = it },
                            label = { Text("Zümre Başkanı / Rehber Öğretmen") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = katilimciOgretmenlerText,
                            onValueChange = { katilimciOgretmenlerText = it },
                            label = { Text("Katılan Öğretmenler (Virgülle ayırın)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Tab Selector Row
        item {
            ScrollableTabRow(
                selectedTabIndex = activeTab.ordinal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                edgePadding = 16.dp
            ) {
                TutanakToolTab.values().forEach { tab ->
                    Tab(
                        selected = activeTab == tab,
                        onClick = { activeTab = tab },
                        text = {
                            Text(
                                text = tab.title,
                                fontWeight = if (activeTab == tab) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }
        }

        // Tab-Specific Sub-Controls & Interactive Builders
        when (activeTab) {
            TutanakToolTab.ZUMRE -> {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Sub Document Type & Term Selector
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Dönem Seçimi:",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                listOf("Sene Başı", "2. Dönem Başı", "Sene Sonu (Yıl Sonu)").forEach { term ->
                                    FilterChip(
                                        selected = zumreDonem == term,
                                        onClick = {
                                            zumreDonem = term
                                            zumreGundemler = TutanakGeneratorRepository.getStandardZumreGundemler(term)
                                        },
                                        label = { Text(term, style = MaterialTheme.typography.labelSmall) }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Sub-doc selector (Tutanak vs Dilekçe)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Tutanak" to "Zümre Toplantı Tutanağı", "Dilekçe" to "İdare Onay Dilekçesi").forEach { (type, label) ->
                                InputChip(
                                    selected = zumreSubDoc == type,
                                    onClick = { zumreSubDoc = type },
                                    label = { Text(label) },
                                    leadingIcon = {
                                        Icon(
                                            if (type == "Tutanak") Icons.Default.Description else Icons.Default.Mail,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Gündem Maddeleri (${zumreGundemler.count { it.isSelected }}/${zumreGundemler.size} Seçili)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            FilledTonalButton(
                                onClick = { showAddGundemDialog = true },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Gündem Ekle", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }

                items(zumreGundemler) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (item.isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        ),
                        border = if (item.isSelected) CardDefaults.outlinedCardBorder() else null
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = item.isSelected,
                                    onCheckedChange = { checked ->
                                        zumreGundemler = zumreGundemler.map {
                                            if (it.no == item.no) it.copy(isSelected = checked) else it
                                        }
                                    }
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${item.no}. ${item.title}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (item.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            if (item.isSelected) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Görüşme: ${item.discussion}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Karar: ${item.decision}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }

            TutanakToolTab.SOK -> {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = sokSube,
                                onValueChange = { sokSube = it },
                                label = { Text("Şube") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = sokSinifRehberi,
                                onValueChange = { sokSinifRehberi = it },
                                label = { Text("Şube Rehber Öğretmeni") },
                                modifier = Modifier.weight(2f),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ŞÖK Öğrenci Değerlendirme Tablosu (${sokStudents.size} Öğrenci)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Button(
                                onClick = { showAddStudentDialog = true },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Öğrenci Ekle", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }

                items(sokStudents) { st ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Surface(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "No: ${st.studentNo}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = st.fullName,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        sokStudents = sokStudents.filter { it.studentNo != st.studentNo }
                                    },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = "Sil", tint = MaterialTheme.colorScheme.error)
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text("• Devamsızlık: ${st.attendanceStatus}", style = MaterialTheme.typography.bodySmall)
                            Text("• Akademik Durum: ${st.academicStatus}", style = MaterialTheme.typography.bodySmall)
                            Text("• Davranış / Rehberlik: ${st.behaviorStatus}", style = MaterialTheme.typography.bodySmall)
                            Text(
                                text = "• Alınan Karar: ${st.actionDecision}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            TutanakToolTab.VELI -> {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Veli Belgesi Türü:",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(listOf(
                                "Toplantı Tutanağı" to Icons.Default.Description,
                                "Davetiye Kuponu" to Icons.Default.MarkunreadMailbox,
                                "İmza Listesi" to Icons.Default.ListAlt,
                                "Bireysel Görüşme" to Icons.Default.RecordVoiceOver
                            )) { (type, icon) ->
                                FilterChip(
                                    selected = veliSubDoc == type,
                                    onClick = { veliSubDoc = type },
                                    label = { Text(type) },
                                    leadingIcon = { Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp)) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        if (veliSubDoc == "Bireysel Görüşme") {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            ) {
                                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text("Bireysel Görüşme Bilgileri", fontWeight = FontWeight.Bold)
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedTextField(
                                            value = bireyselOgrNo,
                                            onValueChange = { bireyselOgrNo = it },
                                            label = { Text("Öğr. No") },
                                            modifier = Modifier.weight(1f)
                                        )
                                        OutlinedTextField(
                                            value = bireyselOgrAd,
                                            onValueChange = { bireyselOgrAd = it },
                                            label = { Text("Öğrenci Adı") },
                                            modifier = Modifier.weight(2f)
                                        )
                                    }
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedTextField(
                                            value = bireyselVeliAd,
                                            onValueChange = { bireyselVeliAd = it },
                                            label = { Text("Veli Adı Soyadı") },
                                            modifier = Modifier.weight(2f)
                                        )
                                        OutlinedTextField(
                                            value = bireyselYakinlik,
                                            onValueChange = { bireyselYakinlik = it },
                                            label = { Text("Yakınlığı") },
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                    OutlinedTextField(
                                        value = bireyselNeden,
                                        onValueChange = { bireyselNeden = it },
                                        label = { Text("Görüşme Nedeni") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    OutlinedTextField(
                                        value = bireyselGorusulen,
                                        onValueChange = { bireyselGorusulen = it },
                                        label = { Text("Görüşülen Konular") },
                                        modifier = Modifier.fillMaxWidth(),
                                        minLines = 2
                                    )
                                    OutlinedTextField(
                                        value = bireyselKarar,
                                        onValueChange = { bireyselKarar = it },
                                        label = { Text("Alınan Kararlar") },
                                        modifier = Modifier.fillMaxWidth(),
                                        minLines = 2
                                    )
                                }
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedTextField(
                                    value = veliSube,
                                    onValueChange = { veliSube = it },
                                    label = { Text("Şube") },
                                    modifier = Modifier.weight(1f)
                                )
                                OutlinedTextField(
                                    value = veliDerslik,
                                    onValueChange = { veliDerslik = it },
                                    label = { Text("Derslik") },
                                    modifier = Modifier.weight(1.5f)
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(
                                value = veliGorusmelerText,
                                onValueChange = { veliGorusmelerText = it },
                                label = { Text("Toplantı Görüşmeleri (Otomatik Şablon)") },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 4
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = veliKararlarText,
                                onValueChange = { veliKararlarText = it },
                                label = { Text("Alınan Kararlar (Otomatik Şablon)") },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 3
                            )
                        }
                    }
                }
            }

            TutanakToolTab.KURUL_VE_BEP, TutanakToolTab.KULUP_VE_OLAY -> {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Belge Türünü Seçiniz:",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        val options = if (activeTab == TutanakToolTab.KURUL_VE_BEP) {
                            listOf("Öğretmenler Kurulu Toplantı Tutanağı", "BEP Birimi Toplantısı")
                        } else {
                            listOf("Kütüphanecilik Kulübü Tutanağı", "Sınav Teslim Tutanağı", "Olay ve Tespit Tutanağı")
                        }

                        options.forEach { opt ->
                            FilterChip(
                                selected = hizliTutanakType == opt,
                                onClick = { hizliTutanakType = opt },
                                label = { Text(opt) },
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }

        // Live Document Preview
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Preview,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "A4 Belge Canlı Önizlemesi",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = "A4 Resmî Format",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Document Preview Container mimicking printed page
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 220.dp, max = 380.dp)
                            .background(Color(0xFFFCFCFC))
                            .border(1.dp, Color(0xFFDDDDDD), RoundedCornerShape(6.dp))
                            .padding(14.dp)
                    ) {
                        LazyColumn {
                            item {
                                Text(
                                    text = "T.C.\n${ilIlce.uppercase()}\n${okulAdi.uppercase()}",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 16.sp
                                    ),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = when (activeTab) {
                                        TutanakToolTab.ZUMRE -> "$egitimYili $zumreDonem TÜRKÇE DERSİ ZÜMRE ÖĞRETMENLER KURULU TUTANAĞI"
                                        TutanakToolTab.SOK -> "$egitimYili $sokSube ŞÖK DEĞERLENDİRME TUTANAĞI"
                                        TutanakToolTab.VELI -> "$egitimYili $veliSube VELİ TOPLANTISI - $veliSubDoc"
                                        else -> hizliTutanakType.uppercase()
                                    },
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    ),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                Divider()
                                Spacer(modifier = Modifier.height(10.dp))

                                // Sample preview text extracted from HTML
                                val cleanSnippet = currentHtml
                                    .replace(Regex("<style[^>]*>.*?</style>", RegexOption.DOT_MATCHES_ALL), "")
                                    .replace(Regex("<[^>]*>"), " ")
                                    .replace("&amp;", "&")
                                    .replace("&#39;", "'")
                                    .replace(Regex("\\s+"), " ")
                                    .take(800)

                                Text(
                                    text = cleanSnippet,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontFamily = FontFamily.Serif,
                                        lineHeight = 18.sp
                                    ),
                                    color = Color(0xFF222222)
                                )

                                Spacer(modifier = Modifier.height(14.dp))
                                Text(
                                    text = "[ ... Belgenin tamamı A4 Yazdır butonuna basıldığında resmî antet, tablolar ve imza sirküsüyle birlikte PDF olarak üretilir ... ]",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            PrintUtils.printHtml(
                                context = context,
                                htmlContent = currentHtml,
                                jobName = "Tutanak_${activeTab.name}"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Icon(Icons.Default.Print, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("A4 Resmî Formatında Yazdır / PDF İndir", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // Dialog for Adding Custom Agenda
    if (showAddGundemDialog) {
        AlertDialog(
            onDismissRequest = { showAddGundemDialog = false },
            title = { Text("Yeni Gündem Maddesi Ekle") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = newGundemTitle,
                        onValueChange = { newGundemTitle = it },
                        label = { Text("Gündem Başlığı") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newGundemDiscussion,
                        onValueChange = { newGundemDiscussion = it },
                        label = { Text("Görüşme Metni") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )
                    OutlinedTextField(
                        value = newGundemDecision,
                        onValueChange = { newGundemDecision = it },
                        label = { Text("Alınan Karar") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newGundemTitle.isNotBlank()) {
                            val nextNo = (zumreGundemler.maxOfOrNull { it.no } ?: 0) + 1
                            zumreGundemler = zumreGundemler + GundemItem(
                                no = nextNo,
                                title = newGundemTitle,
                                discussion = newGundemDiscussion.ifBlank { "Gündem maddesi üzerinde görüşüldü." },
                                decision = newGundemDecision.ifBlank { "Oy birliğiyle kabul edildi." },
                                isSelected = true
                            )
                            newGundemTitle = ""
                            newGundemDiscussion = ""
                            newGundemDecision = ""
                            showAddGundemDialog = false
                            Toast.makeText(context, "Gündem maddesi eklendi", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Ekle")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddGundemDialog = false }) {
                    Text("İptal")
                }
            }
        )
    }

    // Dialog for Adding Student to SOK
    if (showAddStudentDialog) {
        AlertDialog(
            onDismissRequest = { showAddStudentDialog = false },
            title = { Text("ŞÖK Öğrenci Değerlendirmesi Ekle") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = newStudentNo,
                            onValueChange = { newStudentNo = it },
                            label = { Text("Öğr. No") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = newStudentName,
                            onValueChange = { newStudentName = it },
                            label = { Text("Adı Soyadı") },
                            modifier = Modifier.weight(2f)
                        )
                    }
                    OutlinedTextField(
                        value = newStudentAttendance,
                        onValueChange = { newStudentAttendance = it },
                        label = { Text("Devamsızlık Durumu") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newStudentAcademic,
                        onValueChange = { newStudentAcademic = it },
                        label = { Text("Akademik Başarı / Notlar") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newStudentBehavior,
                        onValueChange = { newStudentBehavior = it },
                        label = { Text("Davranış & Rehberlik") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newStudentDecision,
                        onValueChange = { newStudentDecision = it },
                        label = { Text("Alınan Karar / Tedbir") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newStudentName.isNotBlank()) {
                            sokStudents = sokStudents + SokStudentEvaluation(
                                studentNo = newStudentNo.ifBlank { "${sokStudents.size + 100}" },
                                fullName = newStudentName,
                                attendanceStatus = newStudentAttendance,
                                academicStatus = newStudentAcademic,
                                behaviorStatus = newStudentBehavior,
                                actionDecision = newStudentDecision
                            )
                            newStudentNo = ""
                            newStudentName = ""
                            showAddStudentDialog = false
                            Toast.makeText(context, "Öğrenci ŞÖK tablosuna eklendi", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddStudentDialog = false }) {
                    Text("İptal")
                }
            }
        )
    }
}
