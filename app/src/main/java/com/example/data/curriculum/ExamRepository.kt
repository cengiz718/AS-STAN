package com.example.data.curriculum

import com.example.data.model.QuestionEntity

data class ExamConfig(
    val districtName: String = "Çankaya İlçe Millî Eğitim Müdürlüğü",
    val schoolName: String = "Atatürk Ortaokulu",
    val academicYear: String = "2026-2027 Eğitim Öğretim Yılı",
    val semesterExam: String = "1. Dönem 1. Ortak Yazılı Sınavı",
    val gradeLevel: String = "8. Sınıf",
    val courseName: String = "TÜRKÇE DERSİ",
    val bookletGroup: String = "A", // "A" veya "B"
    val durationMinutes: Int = 40,
    val teacherSignatures: String = "Türkçe Zümresi: Ahmet Yılmaz, Elif Kaya",
    val instructions: String = "1. Sınav süresi 40 dakikadır. 2. Cevaplarınızı okunaklı bir yazıyla ilgili alanlara yazınız. 3. Yazım ve noktalama kurallarına riayet ediniz. Başarılar dileriz.",
    val layoutMode: String = "TWO_COLUMN", // "TWO_COLUMN" (İki Sütunlu MEB Mizanpajı) veya "SINGLE_COLUMN"
    val includeAnswerKey: Boolean = true,
    val includeScoreTable: Boolean = true, // Soru puan barem çizelgesi (1-10)
    val includeOpticalSheet: Boolean = false // Mini optik kodlama kutusu
)

data class ExamTemplate(
    val id: String,
    val title: String,
    val description: String,
    val targetGrade: String,
    val questionIds: List<Long>,
    val customPoints: Map<Long, Int>
)

object ExamRepository {

    val defaultQuestions: List<QuestionEntity> = listOf(
        // Soru 1 - Metne Bağlı Açık Uçlu (MEB Yeni Ortak Sınav Formatı)
        QuestionEntity(
            id = 1,
            gradeLevel = "8. Sınıf",
            topic = "Okuma & Açık Uçlu",
            questionType = "OPEN_ENDED",
            readingPassage = "Güz ayları gelip çatınca kuşlar güneye doğru kanat çırparlar. Çocukluğumda İstanbul adalarında binlerce kuş sürüsü gökyüzünü bir bulut gibi kaplardı. Şimdi ise gökyüzü ıssız, ağaçlar sessiz. Kuşları yok ettik; çünkü açgözlülüğümüzle yeşili katlettik, beton kuleler diktik. Unutuyoruz: Kuşları ve doğayı yok eden insan, aslında kendi geleceğini yok etmektedir. Dünya yalnız bizim değil; ağaçların, kuşların, karıncaların da evidir.",
            questionText = "Yukarıdaki metne göre yazarın doğadaki kuşların azalması karşısında hissettiği temel kaygı nedir ve bunun sebebini neye bağlamaktadır? Metinden hareketle kendi cümlelerinizle açıklayınız.",
            correctAnswer = "Yazar, kuşların ve yeşilin yok edilmesinin insanın kendi geleceğini tehdit ettiği kaygısını taşımaktadır. Bunun sebebini ise insanın açgözlülüğü, yeşilin yok edilmesi ve betonlaşmaya bağlamaktadır.",
            points = 15,
            answerLines = 4,
            explanation = "Öğrencinin ana düşünceyi (doğanın yok oluşu ve geleceğin tehlikeye girmesi) ve sebebi (betonlaşma/açgözlülük) kendi cümleleriyle ifade etmesi beklenir. Her iki unsur tam ise 15, tek unsur ise 8 puan."
        ),

        // Soru 2 - Metne Bağlı Açık Uçlu Soru 2
        QuestionEntity(
            id = 2,
            gradeLevel = "8. Sınıf",
            topic = "Okuma & Çıkarım",
            questionType = "OPEN_ENDED",
            questionText = "Metinde geçen “Dünya yalnız bizim değil; ağaçların, kuşların, karıncaların da evidir.” cümlesiyle anlatılmak istenen ana mesajı bir cümleyle yazınız.",
            correctAnswer = "İnsanların dünyayı diğer tüm canlılarla saygı ve uyum içinde paylaşması gerektiği, doğanın sadece insana ait olmadığı anlatılmaktadır.",
            points = 10,
            answerLines = 3,
            explanation = "Canlıların ortak yaşam hakkı ve doğaya saygı vurgusu içeren doğru açıklamalar tam puan alır (10 puan)."
        ),

        // Soru 3 - Fiilimsiler (Açık Uçlu Tablo)
        QuestionEntity(
            id = 3,
            gradeLevel = "8. Sınıf",
            topic = "Fiilimsiler",
            questionType = "OPEN_ENDED",
            questionText = "“Güneş doğarken yola çıkıp sararmış yaprakları çiğneyerek yürümek ona huzur veriyordu.” cümlesinde geçen fiilimsileri bularak türlerini (İsim-fiil, Sıfat-fiil, Zarf-fiil) karşılarına yazınız.",
            correctAnswer = "• doğarken: Zarf-fiil (-ken)\n• çıkıp: Zarf-fiil (-ıp)\n• sararmış: Sıfat-fiil (-miş)\n• çiğneyerek: Zarf-fiil (-erek)\n• yürümek: İsim-fiil (-mek)",
            points = 15,
            answerLines = 5,
            explanation = "Cümlede 5 adet fiilimsi bulunmaktadır. Her doğru tespit ve tür eşleştirmesi 3 puandır (Toplam 15 puan)."
        ),

        // Soru 4 - Cümle Ögeleri (Açık Uçlu)
        QuestionEntity(
            id = 4,
            gradeLevel = "8. Sınıf",
            topic = "Cümle Ögeleri",
            questionType = "OPEN_ENDED",
            questionText = "“Yıllardır görmediği eski öğretmenini vapur iskelesinde büyük bir sevinçle karşıladı.” cümlesinin ögelerini sırasıyla yazınız.",
            correctAnswer = "Ögeler sırasıyla: Belirtili Nesne (Yıllardır görmediği eski öğretmenini) - Yer Tamlayıcısı (vapur iskelesinde) - Zarf Tamlayıcısı (büyük bir sevinçle) - Yüklem (karşıladı). [Gizli Özne: O]",
            points = 15,
            answerLines = 3,
            explanation = "Yüklem, belirtili nesne, yer tamlayıcısı ve zarf tamlayıcısı ögelerinin doğru ve eksiksiz sıralanması 15 puandır."
        ),

        // Soru 5 - Yazım Kuralları (Düzeltme & Açık Uçlu)
        QuestionEntity(
            id = 5,
            gradeLevel = "8. Sınıf",
            topic = "Yazım Kuralları",
            questionType = "OPEN_ENDED",
            questionText = "“Dün akşam ki toplantıya Ahmet'de katıldı; ancak rapordaki herbir maddeyi inceleyemedi.” cümlesinde geçen 3 adet yazım yanlışını bularak doğrularını yazınız.",
            correctAnswer = "1. 'akşam ki' değil 'akşamki' (sıfat yapan -ki bitişik yazılır)\n2. 'Ahmet'de' değil 'Ahmet de' (bağlaç olan de ayrı yazılır ve te/ta olmaz)\n3. 'herbir' değil 'her biri' (ayrı yazılır)",
            points = 15,
            answerLines = 4,
            explanation = "Her doğru tespit ve düzeltme 5 puandır (Toplam 15 puan)."
        ),

        // Soru 6 - Noktalama İşaretleri (Test / LGS Formatı)
        QuestionEntity(
            id = 6,
            gradeLevel = "8. Sınıf",
            topic = "Noktalama İşaretleri",
            questionType = "MULTIPLE_CHOICE",
            questionText = "Sevinçten, heyecandan içim içime sığmıyor ( ) bağırmak, kahkahalar atmak, ağlamak istiyorum ( ) Ne muazzam bir mutluluk ( )\n\nYukarıdaki parçada yay ayraçla ( ) belirtilen yerlere sırasıyla hangi noktalama işaretleri getirilmelidir?",
            optionA = "A) ( ; ) ( . ) ( ! )",
            optionB = "B) ( , ) ( . ) ( ? )",
            optionC = "C) ( : ) ( ; ) ( ! )",
            optionD = "D) ( ; ) ( ... ) ( . )",
            correctAnswer = "A",
            points = 10,
            explanation = "Birinci boşluğa ögeleri arasında virgül bulunan sıralı cümleyi ayırmak için noktalı virgül (;), ikinciye bitmiş cümle için nokta (.), üçüncüye duygu bildirdiği için ünlem (!) gelir."
        ),

        // Soru 7 - Söz Sanatları (Test)
        QuestionEntity(
            id = 7,
            gradeLevel = "8. Sınıf",
            topic = "Söz Sanatları",
            questionType = "MULTIPLE_CHOICE",
            questionText = "“Güneş altın sarısı saçlarını denizin üzerine serpiştirirken yorgun dağlar derin bir uykuya dalmıştı.”\n\nBu cümlede kullanılan söz sanatı aşağıdakilerin hangisinde doğru verilmiştir?",
            optionA = "A) Teşhis (Kişileştirme)",
            optionB = "B) Tezat (Karşıtlık)",
            optionC = "C) İntak (Konuşturma)",
            optionD = "D) Mübalağa (Abartma)",
            correctAnswer = "A",
            points = 10,
            explanation = "Güneşin saçlarını serpiştirmesi ve dağların uykuya dalması insana ait özelliklerin doğaya aktarılmasıdır (Kişileştirme)."
        ),

        // Soru 8 - Yazma Becerisi / Kompozisyon (20 Puan)
        QuestionEntity(
            id = 8,
            gradeLevel = "8. Sınıf",
            topic = "Yazma Becerisi",
            questionType = "ESSAY",
            questionText = "“Kitaplar, insanın ruhunu aydınlatan ve onu hiç gitmediği diyarlara götüren en sadık dostlardır.” sözünden hareketle okuma sevgisinin insan hayatındaki önemini anlatan üç paragraflık (giriş, gelişme, sonuç) bir kompozisyon yazınız.\n\n(Değerlendirme Ölçütleri: Başlık 3p, Sayfa Düzeni ve Paragraf 6p, Anlatım ve Fikir Zenginliği 6p, İmla ve Noktalama 5p = Toplam 20 Puan)",
            correctAnswer = "Öğrencinin belirlenen temaya uygun özgün bir başlık koyması, giriş-gelişme-sonuç planına uyması ve imla kurallarına riayet etmesi beklenir.",
            points = 20,
            answerLines = 10,
            explanation = "Başlık: 3 Puan | Paragraf Bütünlüğü: 6 Puan | Düşünceyi Geliştirme: 6 Puan | İmla ve Noktalama: 5 Puan (Toplam 20 Puan)."
        ),

        // Soru 9 - Sözcükte Anlam & Deyimler (Test)
        QuestionEntity(
            id = 9,
            gradeLevel = "7. Sınıf",
            topic = "Deyimler ve Atasözleri",
            questionType = "MULTIPLE_CHOICE",
            questionText = "Aşağıdaki cümlelerin hangisinde 'çok sevinmek, aşırı derecede mutlu olmak' anlamında bir deyim kullanılmıştır?",
            optionA = "A) Sınav sonuçlarını öğrenince etekleri zil çaldı.",
            optionB = "B) Yaptığı hatalar yüzünden gözden düştü.",
            optionC = "C) İşlerin yoğunluğundan başını kaşıyacak vakti yoktu.",
            optionD = "D) Verdiği sözü tutabilmek için kılı kırk yardı.",
            correctAnswer = "A",
            points = 10,
            explanation = "'Etekleri zil çalmak' deyimi çok sevinmek, büyük bir sevinç duymak anlamına gelir."
        ),

        // Soru 10 - Doğru / Yanlış Sorusu
        QuestionEntity(
            id = 10,
            gradeLevel = "8. Sınıf",
            topic = "Dil Bilgisi Karma",
            questionType = "TRUE_FALSE",
            questionText = "Aşağıdaki yargılardan doğru olanların başına (D), yanlış olanların başına (Y) yazınız:\n• (  ) İsim-fiil ekleri 'MAYIŞMAK' kodlamasıyla hatırlanır.\n• (  ) Cümlede yüklem her zaman cümlenin en sonunda yer almak zorundadır.\n• (  ) Özel adlara gelen yapım ekleri kesme işaretiyle ayrılmaz.\n• (  ) Saat ile dakika arasına Türkçede iki nokta (:) konur.",
            correctAnswer = "1. ( D )\n2. ( Y ) [Devrik cümlelerde başta veya ortada olabilir]\n3. ( D ) [Türkler, İstanbullu ayrılmaz]\n4. ( Y ) [Yalnızca tek nokta konur: 14.30]",
            points = 10,
            answerLines = 4,
            explanation = "Her doğru D/Y tespiti 2.5 puandır (Toplam 10 puan)."
        )
    )

    val templates: List<ExamTemplate> = listOf(
        ExamTemplate(
            id = "meb_ortak",
            title = "MEB Yeni Format Açık Uçlu Ortak Yazılı Sınavı",
            description = "MEB Ölçme ve Değerlendirme Yönetmeliğine tam uyumlu, açık uçlu ve metne bağlı 6 klasik soru (Toplam 100 Puan)",
            targetGrade = "8. Sınıf",
            questionIds = listOf(1, 2, 3, 4, 5, 8),
            customPoints = mapOf(1L to 15, 2L to 10, 3L to 15, 4L to 15, 5L to 15, 8L to 20)
        ),
        ExamTemplate(
            id = "karma_yazili",
            title = "Karma Yazılı Sınavı (Açık Uçlu + Test + Kompozisyon)",
            description = "2 Açık Uçlu + 4 Test + 1 D/Y + 1 Kompozisyon sorusundan oluşan dengeli okul sınavı (Toplam 100 Puan)",
            targetGrade = "8. Sınıf",
            questionIds = listOf(1, 3, 6, 7, 9, 10, 8),
            customPoints = mapOf(1L to 15, 3L to 15, 6L to 10, 7L to 10, 9L to 10, 10L to 10, 8L to 20)
        ),
        ExamTemplate(
            id = "lgs_test",
            title = "LGS Tarama ve Test Sınavı",
            description = "Paragraf, söz sanatları, fiilimsi ve noktalama çoktan seçmeli deneme formatı",
            targetGrade = "8. Sınıf",
            questionIds = listOf(6, 7, 9),
            customPoints = mapOf(6L to 30, 7L to 35, 9L to 35)
        )
    )

    /**
     * Generates an official, teacher-grade A4 HTML exam paper
     * featuring dual column or single column MEB layout, teacher gradebook scoring table,
     * booklet groups (A/B), and answer key rubrics.
     */
    fun generatePrintableExamHtml(
        config: ExamConfig,
        questions: List<QuestionEntity>,
        pointsMap: Map<Long, Int> = emptyMap()
    ): String {
        // Prepare questions (if booklet B, reverse order or shuffle)
        val orderedQuestions = if (config.bookletGroup == "B") questions.reversed() else questions

        // Header Score Table (S1 | S2 | S3 ... | Toplam)
        val scoreTableHeaders = orderedQuestions.mapIndexed { i, _ -> "<th>S${i + 1}</th>" }.joinToString("")
        val scoreTablePoints = orderedQuestions.mapIndexed { _, q ->
            val pts = pointsMap[q.id] ?: q.points
            "<td><b>$pts</b></td>"
        }.joinToString("")
        val scoreTableEmpty = orderedQuestions.mapIndexed { _, _ -> "<td>&nbsp;</td>" }.joinToString("")
        val totalCalculated = orderedQuestions.sumOf { pointsMap[it.id] ?: it.points }

        val scoreTableHtml = if (config.includeScoreTable && orderedQuestions.isNotEmpty()) {
            """
            <table class="rubric-table">
                <thead>
                    <tr>
                        <th style="width:70px; background:#f0f4f8;">Soru No</th>
                        $scoreTableHeaders
                        <th style="width:70px; background:#e8edf5;">TOPLAM</th>
                    </tr>
                </thead>
                <tbody>
                    <tr style="background:#fcfcfc;">
                        <td style="font-size:8.5pt;">Puan Değeri</td>
                        $scoreTablePoints
                        <td style="font-weight:bold; color:#1E3A5F;">$totalCalculated P</td>
                    </tr>
                    <tr style="height:26px;">
                        <td style="font-size:8.5pt;">Aldığı Puan</td>
                        $scoreTableEmpty
                        <td style="font-weight:bold; font-size:12pt;">&nbsp;</td>
                    </tr>
                </tbody>
            </table>
            """.trimIndent()
        } else ""

        // Render questions
        var lastPassage = ""
        val questionsHtml = orderedQuestions.mapIndexed { index, q ->
            val pts = pointsMap[q.id] ?: q.points
            val questionNum = index + 1

            // Check if passage should be displayed
            val passageHtml = if (q.readingPassage.isNotBlank() && q.readingPassage != lastPassage) {
                lastPassage = q.readingPassage
                """
                <div class="reading-passage-box">
                    <div class="passage-header">📖 OKUMA PARÇASI</div>
                    <div class="passage-text">${q.readingPassage.replace("\n", "<br>")}</div>
                </div>
                """.trimIndent()
            } else ""

            // Render based on question type
            val bodyHtml = when (q.questionType) {
                "OPEN_ENDED" -> {
                    val lines = (1..q.answerLines).joinToString("") {
                        "<div class=\"answer-line\"></div>"
                    }
                    """
                    <div class="open-ended-answer">
                        <div class="answer-prompt"><b>Cevabınız:</b></div>
                        $lines
                    </div>
                    """.trimIndent()
                }
                "ESSAY" -> {
                    val lines = (1..q.answerLines).joinToString("") {
                        "<div class=\"answer-line\"></div>"
                    }
                    """
                    <div class="essay-box">
                        <div style="text-align:center; font-style:italic; font-size:9pt; margin-bottom:6px; color:#555;">[ Yazınız için uygun bir başlık belirleyip aşağıya yazınız ]</div>
                        $lines
                    </div>
                    """.trimIndent()
                }
                "TRUE_FALSE", "FILL_BLANK" -> {
                    val lines = (1..q.answerLines.coerceAtLeast(3)).joinToString("") {
                        "<div class=\"answer-line\"></div>"
                    }
                    """
                    <div class="open-ended-answer">
                        $lines
                    </div>
                    """.trimIndent()
                }
                else -> { // MULTIPLE_CHOICE
                    val optEHtml = if (q.optionE.isNotBlank()) "<div class=\"option-cell\">${q.optionE}</div>" else ""
                    """
                    <div class="options-container">
                        <div class="option-cell">${q.optionA}</div>
                        <div class="option-cell">${q.optionB}</div>
                        <div class="option-cell">${q.optionC}</div>
                        <div class="option-cell">${q.optionD}</div>
                        $optEHtml
                    </div>
                    """.trimIndent()
                }
            }

            """
            $passageHtml
            <div class="question-block">
                <div class="question-title-row">
                    <span class="q-num">$questionNum.</span>
                    <span class="q-points">($pts Puan)</span>
                    <span class="q-topic">[${q.topic}]</span>
                </div>
                <div class="q-text">${q.questionText.replace("\n", "<br>")}</div>
                $bodyHtml
            </div>
            """.trimIndent()
        }.joinToString("\n")

        // Answer Key Section
        val answerKeyRows = orderedQuestions.mapIndexed { index, q ->
            val pts = pointsMap[q.id] ?: q.points
            val ans = if (q.questionType == "MULTIPLE_CHOICE") "<b>${q.correctAnswer}</b>" else q.correctAnswer.replace("\n", "<br>")
            """
            <tr>
                <td style="text-align:center; font-weight:bold; width:35px;">${index + 1}</td>
                <td style="text-align:center; width:50px; font-weight:bold; color:#1E3A5F;">$pts P</td>
                <td style="width:110px;">${q.topic}</td>
                <td style="width:180px;">$ans</td>
                <td><small>${q.explanation.replace("\n", "<br>")}</small></td>
            </tr>
            """.trimIndent()
        }.joinToString("\n")

        val answerKeyPage = if (config.includeAnswerKey) {
            """
            <div style="page-break-before: always; margin-top: 24px;">
                <div class="exam-header" style="border-color:#1E3A5F;">
                    <div class="ministry">T.C. MİLLÎ EĞİTİM BAKANLIĞI</div>
                    <div class="school-title">${config.schoolName.uppercase()}</div>
                    <div class="exam-title">CEVAP ANAHTARI VE PUANLAMA BAREMİ (${config.bookletGroup} KİTAPÇIĞI)</div>
                    <div style="font-size:9.5pt; color:#666; margin-top:3px;">${config.academicYear} • ${config.gradeLevel} ${config.courseName} • Toplam Puan: $totalCalculated</div>
                </div>

                <table class="key-table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Puan</th>
                            <th>Konu & Kazanım</th>
                            <th>Doğru Yanıt / Beklenen Cevap</th>
                            <th>Öğretmen Değerlendirme Baremi</th>
                        </tr>
                    </thead>
                    <tbody>
                        $answerKeyRows
                    </tbody>
                </table>

                <div class="signatures-row">
                    <div><b>Zümre Başkanı</b><br><br>İmza</div>
                    <div><b>Ders Öğretmeni</b><br><br>İmza</div>
                    <div><b>Okul Müdürü</b><br><br>Uygundur / Mühür</div>
                </div>
            </div>
            """.trimIndent()
        } else ""

        // Layout CSS styling (Two-column or single column)
        val columnCss = if (config.layoutMode == "TWO_COLUMN") {
            """
            .questions-wrapper {
                column-count: 2;
                column-gap: 20px;
                column-rule: 1px dashed #d0d7de;
            }
            .question-block {
                break-inside: avoid;
                page-break-inside: avoid;
                margin-bottom: 16px;
            }
            .reading-passage-box {
                column-span: all;
                break-inside: avoid;
            }
            """.trimIndent()
        } else {
            """
            .questions-wrapper {
                column-count: 1;
            }
            .question-block {
                margin-bottom: 18px;
                page-break-inside: avoid;
            }
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${config.schoolName} - ${config.semesterExam}</title>
            <style>
                @page {
                    size: A4;
                    margin: 10mm 12mm 12mm 12mm;
                }
                body {
                    font-family: 'Times New Roman', Times, serif, Arial;
                    color: #1a1a1a;
                    font-size: 10pt;
                    line-height: 1.35;
                    margin: 0;
                    padding: 0;
                    background: #fff;
                }
                .exam-header {
                    text-align: center;
                    border: 2px solid #1E3A5F;
                    border-radius: 6px;
                    padding: 6px 10px;
                    margin-bottom: 8px;
                    background: #fafcff;
                    position: relative;
                }
                .booklet-badge {
                    position: absolute;
                    right: 10px;
                    top: 10px;
                    background: #1E3A5F;
                    color: #fff;
                    font-weight: bold;
                    font-size: 14pt;
                    width: 38px;
                    height: 38px;
                    line-height: 38px;
                    border-radius: 50%;
                    border: 2px solid #B57D1E;
                    text-align: center;
                }
                .ministry {
                    font-size: 9.5pt;
                    font-weight: bold;
                    letter-spacing: 0.8px;
                    color: #333;
                }
                .school-title {
                    font-size: 13pt;
                    font-weight: bold;
                    color: #1E3A5F;
                    margin: 2px 0;
                    text-transform: uppercase;
                }
                .exam-title {
                    font-size: 11pt;
                    font-weight: bold;
                    color: #8B263E;
                }
                .meta-row {
                    font-size: 9pt;
                    color: #555;
                    margin-top: 2px;
                }
                .info-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 8px;
                    font-size: 9.5pt;
                }
                .info-table td, .info-table th {
                    border: 1px solid #444;
                    padding: 4px 6px;
                }
                .info-table th {
                    background: #f4f6f9;
                    text-align: left;
                    font-weight: bold;
                }
                .rubric-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 10px;
                    font-size: 8.5pt;
                    text-align: center;
                }
                .rubric-table th, .rubric-table td {
                    border: 1px solid #777;
                    padding: 3px 2px;
                }
                .instructions-box {
                    font-size: 8.5pt;
                    background: #fdfbf7;
                    border: 1px dashed #B57D1E;
                    border-radius: 4px;
                    padding: 4px 8px;
                    margin-bottom: 10px;
                    color: #444;
                }
                .reading-passage-box {
                    background: #fbfbfd;
                    border: 1px solid #c9d2de;
                    border-left: 4px solid #1E3A5F;
                    border-radius: 4px;
                    padding: 8px 10px;
                    margin-bottom: 12px;
                }
                .passage-header {
                    font-weight: bold;
                    color: #1E3A5F;
                    font-size: 9.5pt;
                    margin-bottom: 4px;
                }
                .passage-text {
                    font-size: 9.5pt;
                    text-align: justify;
                    line-height: 1.4;
                    font-style: italic;
                }
                $columnCss
                .question-title-row {
                    font-weight: bold;
                    margin-bottom: 3px;
                }
                .q-num {
                    color: #1E3A5F;
                    font-size: 11pt;
                }
                .q-points {
                    color: #8B263E;
                    font-size: 8.5pt;
                    margin-left: 3px;
                }
                .q-topic {
                    color: #666;
                    font-size: 8pt;
                    font-weight: normal;
                    margin-left: 4px;
                }
                .q-text {
                    margin-bottom: 6px;
                    text-align: justify;
                    font-size: 9.5pt;
                }
                .options-container {
                    padding-left: 8px;
                    display: flex;
                    flex-direction: column;
                    gap: 3px;
                }
                .option-cell {
                    font-size: 9pt;
                }
                .open-ended-answer {
                    margin-top: 6px;
                }
                .answer-prompt {
                    font-size: 8.5pt;
                    color: #555;
                    margin-bottom: 2px;
                }
                .answer-line {
                    border-bottom: 1px dotted #888;
                    height: 18px;
                    margin-bottom: 3px;
                }
                .essay-box {
                    margin-top: 6px;
                }
                .key-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 10px;
                    font-size: 9pt;
                }
                .key-table th, .key-table td {
                    border: 1px solid #777;
                    padding: 5px 6px;
                }
                .key-table th {
                    background: #f0f4f8;
                    color: #1E3A5F;
                    text-align: left;
                }
                .signatures-row {
                    display: flex;
                    justify-content: space-around;
                    margin-top: 35px;
                    text-align: center;
                    font-size: 9.5pt;
                }
                .footer-text {
                    text-align: center;
                    font-size: 8.5pt;
                    color: #666;
                    margin-top: 14px;
                    border-top: 1px solid #eee;
                    padding-top: 4px;
                }
            </style>
        </head>
        <body>
            <div class="exam-header">
                <div class="booklet-badge">${config.bookletGroup}</div>
                <div class="ministry">T.C. ${config.districtName.uppercase()}</div>
                <div class="school-title">${config.schoolName}</div>
                <div class="exam-title">${config.academicYear} ${config.gradeLevel} ${config.courseName}</div>
                <div class="meta-row"><b>${config.semesterExam.uppercase()}</b> (${config.bookletGroup} KİTAPÇIĞI)</div>
            </div>

            <table class="info-table">
                <tr>
                    <th style="width:16%;">Adı ve Soyadı:</th>
                    <td style="width:40%;"></td>
                    <th style="width:16%;">Sınav Tarihi:</th>
                    <td style="width:28%;">____ / ____ / 2026</td>
                </tr>
                <tr>
                    <th>Sınıfı / Şubesi:</th>
                    <td>${config.gradeLevel} / ____</td>
                    <th>Sınav Süresi:</th>
                    <td>${config.durationMinutes} Dakika</td>
                </tr>
                <tr>
                    <th>Okul Numarası:</th>
                    <td></td>
                    <th>ALDIĞI NOT:</th>
                    <td style="font-size:12pt; font-weight:bold; color:#1E3A5F;">&nbsp;</td>
                </tr>
            </table>

            $scoreTableHtml

            <div class="instructions-box">
                <b>YÖNERGE:</b> ${config.instructions}
            </div>

            <div class="questions-wrapper">
                $questionsHtml
            </div>

            <div class="footer-text">
                • Sınavınız bitmiştir. Lütfen cevaplarınızı kontrol ediniz. | ${config.teacherSignatures} •
            </div>

            $answerKeyPage
        </body>
        </html>
        """.trimIndent()
    }
}
