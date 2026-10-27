package com.example.data.curriculum

data class DilimizinZenginlikleriActivity(
    val month: String,
    val theme: String,
    val gradeLevel: String,
    val activityTitle: String,
    val description: String,
    val steps: List<String>,
    val outputProduct: String, // "Okul Panosu", "Afiş", "Sözlük Tasarımı", "Hikaye Yazımı", "Sergi"
    val outcomeRelation: String
)

data class ResfebePuzzle(
    val id: String,
    val clueVisual: String, // Görsel / metin simgesi (örn: "K1R", "EV + LER", "AY + DEDE")
    val hint: String,
    val answer: String,
    val category: String, // "Deyim", "Atasözü", "Edebi Kavram", "Kelime"
    val explanation: String
)

data class ElectiveCourseModule(
    val courseName: String, // "Masal ve Destanlarımız", "Kültür ve Medeniyetimize Yön Verenler", "Yazarlık ve Yazma Becerileri"
    val grade: String,
    val weeklyHours: Int = 2,
    val courseObjective: String,
    val units: List<String>,
    val sampleActivity: String
)

data class BepStudentAssessment(
    val studentName: String,
    val gradeSection: String,
    val diagnosis: String, // "Özgül Öğrenme Güçlüğü", "Hafif Düzey Zihinsel", "Dikkat Eksikliği"
    val simplifiedOutcomes: List<String>,
    val accommodations: List<String>, // "Sınav süresine ek 15 dakika", "Büyük punto metinler", "Çoktan seçmeli 3 seçenek"
    val evaluationMethod: String
)

data class KazanimProgressTracker(
    val grade: String,
    val weekNumber: Int,
    val topicTitle: String,
    val outcomeCode: String,
    val studentPerformanceCriteria: List<String>
)

object EducationalGamesAndProjectsRepository {

    // =========================================================================
    // 1. DİLİMİZİN ZENGİNLİKLERİ PROJESİ (MEB Resmî Yıllık Eylem Planı)
    // =========================================================================
    val dilimizinZenginlikleriActivities: List<DilimizinZenginlikleriActivity> = listOf(
        DilimizinZenginlikleriActivity(
            month = "Ekim",
            theme = "Klasik Eser Okumaları ve Bilen Bulur",
            gradeLevel = "5-8. Sınıf",
            activityTitle = "Sözlük Özgürlüktür: Deyimler ve Atasözleri Sergisi",
            description = "Öğrencilerin seçtikleri bir atasözü veya deyimi somutlaştırarak üç boyutlu maket, tuval veya dijital afiş haline getirmesi ve okul fuayesinde sergilenmesi.",
            steps = listOf(
                "1. Öğrencilere TDK Atasözleri ve Deyimler Sözlüğü'nden en az 3 özgün deyim seçtirilir.",
                "2. 'Etekleri zil çalmak', 'Göz boyamak', 'Ağzıyla kuş tutmak' gibi soyut deyimler resmedilir veya maketi yapılır.",
                "3. Eserlerin altına deyimin doğru anlamı ve ortaya çıkış hikayesi (menşei) yazılır.",
                "4. Okul koridorunda 'Dilimizin Zenginlikleri Sokağı' oluşturularak tüm okula sergilenir."
            ),
            outputProduct = "Üç Boyutlu Sergi & Pano",
            outcomeRelation = "T.5.3.6 & T.8.3.6. Deyim ve atasözlerinin metne katkısını belirler."
        ),
        DilimizinZenginlikleriActivity(
            month = "Kasım",
            theme = "Dede Korkut Hikayeleri Okumaları",
            gradeLevel = "6 ve 7. Sınıf",
            activityTitle = "Dede Korkut Tarzı Özgün Boy Boylama & Hikaye Yazma",
            description = "Dede Korkut'un 'Boğaç Han', 'Deli Dumrul' veya 'Bamsı Beyrek' boylarının tahlil edilerek öğrencilerin günümüz diliyle yeni bir boy kaleme alması.",
            steps = listOf(
                "1. Sınıfta Dede Korkut'un anlatım tarzı (nazım-nesir karışık yapısı, aliterasyonlar) incelenir.",
                "2. 'Dedem Korkut geldi, boy boyladı, soy soyladı...' tekerlemesiyle öğrencilerin kendi kahramanlarını anlatan 2 sayfalık hikaye yazması sağlanır.",
                "3. En başarılı hikayeler 'Okulumuzun Dede Korkut Kitabı' adıyla ciltlenerek okul kütüphanesine hediye edilir."
            ),
            outputProduct = "Sınıf Hikaye Kitabı",
            outcomeRelation = "T.6.3.16 & T.7.4.2. Metin türlerini ayırt eder, hikâye edici metin yazar."
        ),
        DilimizinZenginlikleriActivity(
            month = "Aralık",
            theme = "Safahat Sözlüğü ve Mehmet Akif",
            gradeLevel = "7 ve 8. Sınıf",
            activityTitle = "Safahat'tan Seçmeler: Cümle / Afiş Yarışması",
            description = "İstiklal Marşı şairimiz Mehmet Akif Ersoy'un Safahat adlı eserinde geçen ve bugün unutulmaya yüz tutmuş kelimeleri gün ışığına çıkarma etkinliği.",
            steps = listOf(
                "1. Safahat'tan belirlenen 20 kelime (azim, ceht, hamiyet, istikbal, irfan, fazilet vb.) tahtaya yazılır.",
                "2. Öğrenciler bu kelimelerden en az ikisini içeren derinlikli slogan ve afişler tasarlar.",
                "3. Okul panosunda 'Safahat Sözlüğü' köşesi açılır."
            ),
            outputProduct = "Tematik Sözlük Panosu & Afiş",
            outcomeRelation = "T.8.3.5. Bağlamdan yararlanarak kelime hazinesini geliştirir."
        ),
        DilimizinZenginlikleriActivity(
            month = "Mart",
            theme = "Divanu Lugati't-Türk ve Türk Dünyası",
            gradeLevel = "5-8. Sınıf",
            activityTitle = "Kaşgarlı Mahmud'un İzinde: Öz Türkçe Sözlük Ağacı",
            description = "İlk Türkçe sözlük olan Divanu Lugati't-Türk'ün 950. yılı anısına okul bahçesinde veya koridorunda 'Türkçe Sözcük Ağacı' kurulması.",
            steps = listOf(
                "1. Öğrenciler yabancı dillerden dilimize giren kelimelerin (ör. selfi, link, star) yerine Türkçe karşılıklarını (özçekim, bağ, yıldız) yaprak şeklinde kartonlara yazar.",
                "2. Kartonlar okul girişindeki ahşap ağaç maketine asılır.",
                "3. En yaratıcı Türkçe karşılığı üreten öğrenciye kitap ödülü verilir."
            ),
            outputProduct = "Öz Türkçe Sözlük Ağacı",
            outcomeRelation = "T.5.3.3 & T.7.3.5. Türkçenin zenginliklerini fark eder ve korur."
        )
    )

    // =========================================================================
    // 2. RESFEBE VE EĞİTSEL TÜRKÇE OYUNLARI (turkceailesi.net uyumlu)
    // =========================================================================
    val resfebePuzzles: List<ResfebePuzzle> = listOf(
        ResfebePuzzle(
            id = "RES_1",
            clueVisual = "K 1 R",
            hint = "Bir sayı ve harf yan yana (K + 1 + R)",
            answer = "KIKIRDAK / KAHIR / KEBİR",
            category = "Kelime",
            explanation = "K + bir (1) + R -> 'K-BİR' (Kibir) veya K-1-R (Kır)."
        ),
        ResfebePuzzle(
            id = "RES_2",
            clueVisual = "👁️ + 🎨 (Boya) + 🧼",
            hint = "Görsel semboller: Bir göz, boya kutusu ve fırça",
            answer = "GÖZ BOYAMAK",
            category = "Deyim",
            explanation = "Birini yanıltmak, gösterişle kandırmak anlamına gelen 'Göz boyamak' deyimidir."
        ),
        ResfebePuzzle(
            id = "RES_3",
            clueVisual = "C1 (Ters C) + LİK",
            hint = "C harfinin içi boş ve ters çevrilmiş, yanında 'LİK'",
            answer = "İYİLİK / TERSLİK",
            category = "Kelime",
            explanation = "Ters C ve lik eki -> 'Terslik'."
        ),
        ResfebePuzzle(
            id = "RES_4",
            clueVisual = "🔥 + 🧱 (Duvar) + 🌾",
            hint = "Ateş resmi, duvar ve ekin",
            answer = "ATEŞLE OYNAMAK / ATEŞ DÜŞTÜĞÜ YERİ YAKAR",
            category = "Atasözü",
            explanation = "Acının sadece o acıyı çekenin yüreğini yaktığını anlatan derin atasözü."
        ),
        ResfebePuzzle(
            id = "RES_5",
            clueVisual = "MAYIŞMAK",
            hint = "Fiilimsilerin isim-fiil eklerini hatırlatan ünlü akrostiş",
            answer = "-ma, -ış, -mak (İSİM-FİİL EKLERİ)",
            category = "Edebi Kavram",
            explanation = "İsim-fiil eklerinin hafızada kalması için geliştirilmiş en köklü Türkçe kodlamasıdır."
        )
    )

    // =========================================================================
    // 3. SEÇMELİ DERSLER (turkceailesi.net & Maarif Modeli Müfredatı)
    // =========================================================================
    val electiveCourses: List<ElectiveCourseModule> = listOf(
        ElectiveCourseModule(
            courseName = "Masal ve Destanlarımız",
            grade = "5 ve 6. Sınıf",
            weeklyHours = 2,
            courseObjective = "Türk masal ve destan geleneğini tanıtmak, kültürel kodlarımızı aktarmak ve öğrencilerin hayal gücünü geliştirmek.",
            units = listOf(
                "1. Ünite: Masal Dünyasına Yolculuk (Keloğlan, Kırk Haramiler, Billur Köşk)",
                "2. Ünite: Türk Destanlarının Kahramanları (Oğuz Kağan, Ergenekon, Türeyiş, Manas)",
                "3. Ünite: Masal ve Destan Anlatıcılığı Atölyesi (Meddahlık ve Canlandırma)"
            ),
            sampleActivity = "Her öğrenci bir masal kahramanının kostümünü tasarlar ve masalın düğüm noktasını kendi hayal gücüyle yeniden kurgular."
        ),
        ElectiveCourseModule(
            courseName = "Kültür ve Medeniyetimize Yön Verenler",
            grade = "7 ve 8. Sınıf",
            weeklyHours = 2,
            courseObjective = "Bilim, sanat, edebiyat ve düşünce dünyamızın öncülerinin hayatlarını, eserlerini ve insanlığa katkılarını kavratmak.",
            units = listOf(
                "1. Ünite: Gönül Erleri ve Dil Mimarları (Yunus Emre, Mevlana, Hacı Bektaş Veli, Ahmed Yesevi)",
                "2. Ünite: Bilim ve Felsefenin Öncüleri (İbn-i Sina, Biruni, Ali Kuşçu, Cezeri, Uluğ Bey)",
                "3. Ünite: Sanat ve Mimari Dehaları (Mimar Sinan, Itri, Dede Efendi, Levni)"
            ),
            sampleActivity = "Öğrenciler 'Tarihe Yön Veren Bir Bilge ile Röportaj' videosu veya canlandırması hazırlar."
        ),
        ElectiveCourseModule(
            courseName = "Yazarlık ve Yazma Becerileri",
            grade = "6, 7 ve 8. Sınıf",
            weeklyHours = 2,
            courseObjective = "Öğrencilerin duygularını, düşüncelerini ve hayallerini özgün, tutarlı ve etkili bir dille yazılı olarak ifade edebilmesini sağlamak.",
            units = listOf(
                "1. Ünite: Yazının Büyülü Dünyası ve Gözlem Yapma",
                "2. Ünite: Kurgu Unsurları ve Karakter Yaratma",
                "3. Ünite: Şiir, Deneme ve Kısa Hikaye Atölyesi",
                "4. Ünite: Metin Redaksiyonu ve Yayına Hazırlama"
            ),
            sampleActivity = "Öğrenciler 500 kelimelik bir 'Mikro Öykü' kaleme alır ve akran değerlendirmesiyle düzeltir."
        )
    )

    // =========================================================================
    // 4. KAZANIM TAKİP & BEP FORMU (kazanimcepte.com standartları)
    // =========================================================================
    val sampleBepProfile = BepStudentAssessment(
        studentName = "Ali Can Yılmaz",
        gradeSection = "6/B",
        diagnosis = "Özgül Öğrenme Güçlüğü (Disleksi)",
        simplifiedOutcomes = listOf(
            "T.6.3.1. Noktalama işaretlerine dikkat ederek sesli okur (Hız değil doğruluk esas alınır).",
            "T.6.3.3. Kısa metinlerin ana fikrini söyler.",
            "T.6.3.8. İsim ve sıfat kavramını somut örneklerle ayırt eder."
        ),
        accommodations = listOf(
            "Yazılı sınavlarda 15 dakika ek süre verilir.",
            "Soru kağıtları 14 punto büyüklüğünde ve satır aralıklı hazırlanır.",
            "Açık uçlu uzun paragraf yerine eşleştirmeli ve boşluk doldurmalı sorular ağırlıkta tutulur.",
            "Sözlü değerlendirmede kendini rahat ifade etmesi için teşvik edilir."
        ),
        evaluationMethod = "Bireyselleştirilmiş rubrik ve süreç değerlendirme ölçeği."
    )

    // =========================================================================
    // HTML PRINT GENERATORS (A4 Resmî Çıktılar)
    // =========================================================================

    /**
     * Dilimizin Zenginlikleri Projesi Okul Eylem Planı A4 HTML çıktısı üretir.
     */
    fun generateDilimizinZenginlikleriHtml(
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        val rowsHtml = dilimizinZenginlikleriActivities.joinToString("\n") { act ->
            val stepsHtml = act.steps.joinToString("<br>")
            """
            <tr>
                <td style="text-align:center; font-weight:bold;">${act.month}</td>
                <td><b>${act.theme}</b></td>
                <td><b>${act.activityTitle}</b><br><small>${act.description}</small></td>
                <td style="font-size:8pt;">$stepsHtml</td>
                <td style="text-align:center; font-weight:bold; color:#2c5282;">${act.outputProduct}</td>
                <td style="font-size:8pt;">${act.outcomeRelation}</td>
            </tr>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>Dilimizin Zenginlikleri Projesi Eylem Planı</title>
            <style>
                @page { size: A4 landscape; margin: 10mm; }
                body { font-family: 'Times New Roman', serif; font-size: 8.5pt; color: #111; line-height: 1.3; }
                .header { text-align: center; border-bottom: 2px solid #805ad5; padding-bottom: 4px; margin-bottom: 8px; }
                .header h1 { font-size: 11.5pt; margin: 0; color: #6b46c1; text-transform: uppercase; }
                .header h2 { font-size: 10pt; margin: 2px 0; }
                table { width: 100%; border-collapse: collapse; font-size: 8pt; }
                th, td { border: 1px solid #718096; padding: 4px; vertical-align: top; }
                th { background: #faf5ff; color: #553c9a; text-align: center; font-weight: bold; }
                .footer { margin-top: 15px; display: flex; justify-content: space-between; font-size: 8.5pt; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>DİLİMİZİN ZENGİNLİKLERİ PROJESİ TÜRKÇE DERSİ OKUL EYLEM PLANI</h2>
                <div style="font-size: 8.5pt;">Milli Eğitim Bakanlığı 'Sözlük Özgürlüktür' Vizyonuna Uygundur</div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th style="width:7%;">Ay</th>
                        <th style="width:16%;">Ana Tema</th>
                        <th style="width:23%;">Faaliyet / Etkinlik Adı</th>
                        <th style="width:28%;">Uygulama Basamakları</th>
                        <th style="width:13%;">Somut Çıktı / Ürün</th>
                        <th style="width:13%;">İlgili Kazanım</th>
                    </tr>
                </thead>
                <tbody>
                    $rowsHtml
                </tbody>
            </table>

            <div class="footer">
                <div style="text-align:center;">
                    <div>Proje Sorumlusu Öğretmen</div>
                    <div style="font-weight:bold; margin-top:4px;">$teacherName</div>
                    <div style="margin-top:20px;">İmza</div>
                </div>
                <div style="text-align:center;">
                    <div>UYGUNDUR</div>
                    <div style="font-weight:bold; margin-top:4px;">Okul Müdürü</div>
                    <div style="margin-top:20px;">Mühür / İmza</div>
                </div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    /**
     * BEP Türkçe Dersi Gelişim ve Sınav Uyarlama Formu A4 Çıktısı.
     */
    fun generateBepAssessmentHtml(
        bep: BepStudentAssessment,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${bep.studentName} - BEP Değerlendirme Formu</title>
            <style>
                @page { size: A4 portrait; margin: 15mm; }
                body { font-family: 'Times New Roman', serif; font-size: 10pt; color: #111; line-height: 1.4; }
                .header { text-align: center; border-bottom: 2px solid #2b6cb0; padding-bottom: 6px; margin-bottom: 12px; }
                .header h1 { font-size: 12pt; margin: 0; text-transform: uppercase; }
                .header h2 { font-size: 11pt; margin: 3px 0; color: #2b6cb0; }
                .info-table { width: 100%; border-collapse: collapse; margin-bottom: 10px; font-size: 9.5pt; }
                .info-table td { border: 1px solid #cbd5e0; padding: 4px 6px; }
                .box { border: 1px solid #718096; padding: 8px 10px; margin-bottom: 10px; border-radius: 4px; }
                .box-title { font-weight: bold; color: #2b6cb0; margin-bottom: 4px; font-size: 10pt; }
                .footer { margin-top: 25px; border-top: 1px solid #718096; padding-top: 6px; font-size: 9pt; display: flex; justify-content: space-between; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>BİREYSELLEŞTİRİLMİŞ EĞİTİM PLANI (BEP) TÜRKÇE DERSİ UYARLAMA FORMU</h2>
                <div style="font-size: 9pt; color: #555;">Kazanım Takip ve Sınav Tedbirleri Çizelgesi</div>
            </div>

            <table class="info-table">
                <tr>
                    <td style="width: 35%;"><b>Öğrenci Adı Soyadı:</b> ${bep.studentName}</td>
                    <td style="width: 25%;"><b>Sınıf/Şube:</b> ${bep.gradeSection}</td>
                    <td style="width: 40%;"><b>Tanı / Durum:</b> ${bep.diagnosis}</td>
                </tr>
            </table>

            <div class="box">
                <div class="box-title">1. Sadeleştirilmiş ve Öncelikli Türkçe Kazanımları:</div>
                <ul style="margin: 4px 0; padding-left: 20px; font-size: 9pt;">
                    ${bep.simplifiedOutcomes.joinToString("") { "<li>$it</li>" }}
                </ul>
            </div>

            <div class="box">
                <div class="box-title">2. Ders İçi ve Sınav Uyarlama Tedbirleri:</div>
                <ul style="margin: 4px 0; padding-left: 20px; font-size: 9pt;">
                    ${bep.accommodations.joinToString("") { "<li>$it</li>" }}
                </ul>
            </div>

            <div class="box">
                <div class="box-title">3. Ölçme ve Değerlendirme Yaklaşımı:</div>
                <div style="font-size: 9pt;">${bep.evaluationMethod}</div>
            </div>

            <div class="footer">
                <div>Türkçe Dersi Öğretmeni: <b>$teacherName</b><br>İmza: ...................</div>
                <div>Okul Rehber Öğretmeni: ...................<br>İmza: ...................</div>
                <div>Okul Müdürü: ...................<br>Mühür/İmza</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
