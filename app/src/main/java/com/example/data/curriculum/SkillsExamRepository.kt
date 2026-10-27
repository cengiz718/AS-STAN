package com.example.data.curriculum

data class ListeningExamItem(
    val id: String,
    val grade: String,
    val title: String,
    val passageToRead: String, // Öğretmenin sınıfta okuyacağı veya ses kaydından dinletilecek metin
    val questions: List<ListeningQuestion>,
    val rubricNotes: String
)

data class ListeningQuestion(
    val number: Int,
    val questionText: String,
    val expectedAnswer: String,
    val points: Int
)

data class SpeakingExamTopic(
    val id: String,
    val grade: String,
    val topicTitle: String,
    val guidancePrompt: String,
    val preparationTimeMinutes: Int = 2,
    val speechDurationMinutes: Int = 3
)

data class SpeakingRubricCriteria(
    val criteriaName: String,
    val description: String,
    val maxPoints: Int
)

data class IokbsQuestion(
    val number: Int,
    val grade: String,
    val subject: String,
    val questionPrompt: String,
    val options: List<String>,
    val correctKey: String,
    val explanation: String
)

object SkillsExamRepository {

    // =========================================================================
    // 1. MEB DİNLEME BECERİSİ SINAVI (Yönetmelik Gereği %25 Ağırlık)
    // =========================================================================
    val listeningExams: List<ListeningExamItem> = listOf(
        ListeningExamItem(
            id = "LISTEN_5",
            grade = "5. Sınıf",
            title = "5. Sınıf Dinleme Becerisi Sınavı: Yunus ile Kuşun Dostluğu",
            passageToRead = """
                (Öğretmen Yönergesi: Metin öğrencilere iki kez sakin ve anlaşılır bir tonla okunur. İlk okumada not almaları istenmez, ikinci okumada önemli bilgileri not almalarına izin verilir.)
                
                Uzak bir bozkır köyünde yaşayan Yunus, bir kış sabahı penceresinin kenarında kanadı yaralı küçük bir saka kuşu buldu. Soğuktan donmak üzere olan bu kuşu içeri alıp avuçlarının sıcaklığıyla ısıttı. Kırık kanadını tahta parçalarıyla sararak ona günlerce buğday taneleri ve ılık su verdi. Kuş iyileştikçe odanın içinde neşeyle cıvıldamaya, Yunus'un omzuna konmaya başladı. 
                Bahar gelip doğa uyanınca Yunus, onu bir kafese hapsetmek yerine açık pencerenin önüne bıraktı. Kuş önce havalandı, gökyüzünde bir sevinç çemberi çizdikten sonra Yunus'un omzuna son kez konup teşekkür edercesine öttü ve mavi gökyüzüne kanat çırptı. Yunus anlamıştı ki gerçek sevgi, tutsak etmek değil; özgürce uçmasına izin vermekti.
            """.trimIndent(),
            questions = listOf(
                ListeningQuestion(
                    number = 1,
                    questionText = "Yunus yaralı saka kuşunu nerede ve ne zaman bulmuştur?",
                    expectedAnswer = "Bir kış sabahı, penceresinin kenarında bulmuştur.",
                    points = 20
                ),
                ListeningQuestion(
                    number = 2,
                    questionText = "Yunus kuşu iyileştirmek için hangi fedakârlıkları yapmıştır?",
                    expectedAnswer = "Avuçlarında ısıtmış, kanadını sarmış, günlerce buğday taneleri ve ılık su vermiştir.",
                    points = 25
                ),
                ListeningQuestion(
                    number = 3,
                    questionText = "Bahar gelince Yunus neden kuşu kafese kapatmamış, pencereden salmıştır?",
                    expectedAnswer = "Çünkü gerçek sevginin canlıları tutsak etmek değil, özgürce uçmalarına izin vermek olduğunu bilmektedir.",
                    points = 30
                ),
                ListeningQuestion(
                    number = 4,
                    questionText = "Kuş gökyüzüne uçmadan önce Yunus'a nasıl bir teşekkür davranışı sergilemiştir?",
                    expectedAnswer = "Gökyüzünde bir sevinç çemberi çizip omzuna son kez konarak ötmüştür.",
                    points = 25
                )
            ),
            rubricNotes = "MEB Ölçme ve Değerlendirme Yönetmeliği gereği dinleme sınavı 100 tam puan üzerinden değerlendirilir ve Türkçe dersi dönem notuna %25 oranında etki eder."
        ),
        ListeningExamItem(
            id = "LISTEN_8",
            grade = "8. Sınıf",
            title = "8. Sınıf Dinleme Becerisi Sınavı: Yapay Zekâ ve İnsan Zihni",
            passageToRead = """
                (Öğretmen Yönergesi: Metin iki kez dinletilir.)
                
                Yirmi birinci yüzyılda teknolojinin ulaştığı nokta, insan zihninin sınırlarını yeniden sorgulamamıza yol açmaktadır. Yapay zekâ algoritmaları milyonlarca veriyi saniyeler içinde işleyebilmekte, satranç ve Go gibi karmaşık strateji oyunlarında dünya şampiyonlarını geride bırakabilmektedir. 
                Ne var ki bir makinenin bilgi işlemesi ile bir insanın 'anlamlandırması' ve 'hissetmesi' arasında aşılmaz bir uçurum vardır. Bir yapay zekâ hüzünlü bir şiirin hece ölçüsünü kusursuz taklit edebilir ancak sevdiğini kaybeden bir insanın yüreğindeki sızıyı asla duyamaz. Merhamet, adalet, vicdan ve estetik beğeni yalnızca insana özgü tinsel erdemlerdir. Bu nedenle geleceğin dünyasında makineleri yönetenler, kod yazanlar kadar insan kalabilenler olacaktır.
            """.trimIndent(),
            questions = listOf(
                ListeningQuestion(
                    number = 1,
                    questionText = "Metne göre yapay zekânın insan şampiyonları geride bıraktığı alanlar nelerdir?",
                    expectedAnswer = "Milyonlarca veriyi işleme hızı ile Satranç ve Go gibi karmaşık strateji oyunlarıdır.",
                    points = 20
                ),
                ListeningQuestion(
                    number = 2,
                    questionText = "Bir makinenin bilgi işlemesi ile insan zihni arasındaki temel fark nedir?",
                    expectedAnswer = "İnsanın anlamlandırması, hissetmesi, merhamet ve vicdan gibi tinsel erdemlere sahip olmasıdır.",
                    points = 30
                ),
                ListeningQuestion(
                    number = 3,
                    questionText = "Yapay zekâ hüzünlü bir şiir yazabildiği halde neden insan olamaz?",
                    expectedAnswer = "Şiirin biçimini ve ölçüsünü taklit edebilir ama o hüznü ve acıyı yüreğinde duyamaz, yaşayamaz.",
                    points = 25
                ),
                ListeningQuestion(
                    number = 4,
                    questionText = "Yazar metnin sonunda geleceğin dünyasını yönetecek kişiler hakkında nasıl bir çıkarımda bulunmuştur?",
                    expectedAnswer = "Makineleri yönetenlerin sadece kod yazanlar değil, aynı zamanda insan kalabilenler olacağını belirtmiştir.",
                    points = 25
                )
            ),
            rubricNotes = "Öğrencinin dinlediğini anlama, ana fikir ve yardımcı fikirleri tespit etme kabiliyeti ölçülür."
        )
    )

    // =========================================================================
    // 2. MEB KONUŞMA BECERİSİ SINAVI (Yönetmelik Gereği %25 Ağırlık)
    // =========================================================================
    val speakingTopics: List<SpeakingExamTopic> = listOf(
        SpeakingExamTopic(
            id = "SPEAK_1",
            grade = "5 ve 6. Sınıf",
            topicTitle = "Geleneksel Çocuk Oyunları vs Dijital Oyunlar",
            guidancePrompt = "Sokakta saklambaç, yakartop, körebe oynamak ile bilgisayar veya tablet başında oyun oynamak arasındaki farkları arkadaşlık, sağlık ve paylaşım açısından karşılaştırınız.",
            preparationTimeMinutes = 2,
            speechDurationMinutes = 3
        ),
        SpeakingExamTopic(
            id = "SPEAK_2",
            grade = "5 ve 6. Sınıf",
            topicTitle = "Doğayı Korumak İçin Bireysel Sorumluluklarımız",
            guidancePrompt = "Çevremizi temiz tutmak, su tasarrufu yapmak ve sokak hayvanlarına sahip çıkmak için okulunuzda başlatabileceğiniz bir projeyi anlatınız.",
            preparationTimeMinutes = 2,
            speechDurationMinutes = 3
        ),
        SpeakingExamTopic(
            id = "SPEAK_3",
            grade = "7 ve 8. Sınıf",
            topicTitle = "Ana Dilimizin Önemi ve Yabancı Kelime İstilası",
            guidancePrompt = "Günlük konuşmalarımızda ve tabelalarda sıkça kullanılan yabancı kelimelerin (ör. exit, check-in, star, full) Türkçemize etkilerini ve bir milletin bağımsızlığında dilin rolünü değerlendiriniz.",
            preparationTimeMinutes = 2,
            speechDurationMinutes = 3
        ),
        SpeakingExamTopic(
            id = "SPEAK_4",
            grade = "7 ve 8. Sınıf",
            topicTitle = "Başarıya Giden Yolda Azim, Sabır ve Kitap Okuma",
            guidancePrompt = "Hayatta karşılaştığınız zorluklar karşısında pes etmemenin önemini ve düzenli kitap okumanın hayal gücünüze kattıklarını bir örnek olayla açıklayınız.",
            preparationTimeMinutes = 2,
            speechDurationMinutes = 3
        )
    )

    val speakingRubric: List<SpeakingRubricCriteria> = listOf(
        SpeakingRubricCriteria(
            criteriaName = "1. Konuya Hakimiyet ve Düşünce Tutarlılığı",
            description = "Giriş, gelişme ve sonuç akışına uyma, konudan sapmama ve mantıklı savunma.",
            maxPoints = 25
        ),
        SpeakingRubricCriteria(
            criteriaName = "2. Kelime Hazinesi ve Türkçenin Doğru Kullanımı",
            description = "Zengin kelime dağarcığı, deyim ve atasözlerinden yararlanma, gereksiz yabancı kelime kullanmama.",
            maxPoints = 25
        ),
        SpeakingRubricCriteria(
            criteriaName = "3. Telaffuz, Diksiyon ve Akıcılık",
            description = "Sözcükleri doğru boğumlama, 'eee, ııı' gibi duraklamalardan kaçınma, işitilebilir ses tonu.",
            maxPoints = 25
        ),
        SpeakingRubricCriteria(
            criteriaName = "4. Beden Dili ve İletişim",
            description = "Dinleyicilerle göz teması kurma, uygun jest ve mimikler kullanma, özgüvenli duruş.",
            maxPoints = 25
        )
    )

    // =========================================================================
    // 3. BURSLULUK (İOKBS) SINAVI SORU BANKASI
    // =========================================================================
    val iokbsQuestions: List<IokbsQuestion> = listOf(
        IokbsQuestion(
            number = 1,
            grade = "5. Sınıf",
            subject = "Sözcükte Anlam (İOKBS Çıkmış Tarzı)",
            questionPrompt = "Aşağıdaki cümlelerin hangisinde 'kuru' sözcüğü mecaz anlamda kullanılmıştır?",
            options = listOf(
                "A) Güneşte bekleyen çamaşırlar bir saatte kurudu.",
                "B) Bizi kapıda oldukça kuru ve soğuk bir ifadeyle karşıladı.",
                "C) Fırından taze ve kuru odunlar getirip sobayı yaktı.",
                "D) Kuru yapraklar rüzgârın önünde savruluyordu."
            ),
            correctKey = "B",
            explanation = "B şıkkında 'kuru ifade' samimiyetten yoksun, sevgisiz ve duygusuz anlamında mecazen kullanılmıştır."
        ),
        IokbsQuestion(
            number = 2,
            grade = "6. Sınıf",
            subject = "İsim Çekim Ekleri (İOKBS Çıkmış Tarzı)",
            questionPrompt = "'Kitabını masanın üzerinde unutan çocuk telaşla geri döndü.'\nBu cümledeki 'kitabını' sözcüğünün aldığı ekler aşağıdakilerden hangisinde sırasıyla doğru verilmiştir?",
            options = listOf(
                "A) Çokluk eki - Ayrılma durumu eki",
                "B) İyelik eki (3. tekil) - Belirtme durumu eki (-ı)",
                "C) İlgi eki - Yönelme durumu eki",
                "D) Yapım eki - İyelik eki"
            ),
            correctKey = "B",
            explanation = "Kitap-ı (onun kitabı -> 3. tekil iyelik eki) -n- (kaynaştırma) -ı (belirtme hal eki)."
        ),
        IokbsQuestion(
            number = 3,
            grade = "7. Sınıf",
            subject = "Fiillerde Kip ve Kişi (İOKBS Çıkmış Tarzı)",
            questionPrompt = "'Bu akşam mutlaka ödevlerimizi bitirmeliyiz.' cümlesindeki fiilin kipi aşağıdakilerden hangisidir?",
            options = listOf(
                "A) Şimdiki Zaman Kipi",
                "B) Şart Kipi",
                "C) Gereklilik Kipi",
                "D) İstek Kipi"
            ),
            correctKey = "C",
            explanation = "Bitir-meli-yiz -> -meli/-malı gereklilik kipidir."
        ),
        IokbsQuestion(
            number = 4,
            grade = "8. Sınıf",
            subject = "Cümlenin Ögeleri (İOKBS & LGS Çıkmış Tarzı)",
            questionPrompt = "'Yağmurdan sonra açan rengârenk gökkuşağı, çocukların neşeli bakışları altında şehri süslüyordu.'\nBu cümlenin öznesi aşağıdakilerden hangisidir?",
            options = listOf(
                "A) Şehri",
                "B) Yağmurdan sonra",
                "C) Yağmurdan sonra açan rengârenk gökkuşağı",
                "D) Çocukların neşeli bakışları altında"
            ),
            correctKey = "C",
            explanation = "Süsleyen ne? 'Yağmurdan sonra açan rengârenk gökkuşağı' bir sıfat tamlaması grubudur ve cümlenin öznesidir."
        )
    )

    // =========================================================================
    // HTML / PDF YAZDIRMA MOTORU (A4 Resmî Çıktılar)
    // =========================================================================

    /**
     * MEB 4 Becerili Türkçe Sınavı: Dinleme ve Konuşma Değerlendirme Ölçeği A4 Çıktısı.
     */
    fun generateListeningSpeakingExamHtml(
        grade: String,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        val listening = listeningExams.find { it.grade.startsWith(grade.take(1)) } ?: listeningExams.first()
        val speaking = speakingTopics.find { it.grade.contains(grade.take(1)) } ?: speakingTopics.first()

        val qHtml = listening.questions.joinToString("\n") { q ->
            """
            <div style="margin-bottom: 8px;">
                <b>Soru ${q.number} (${q.points} Puan):</b> ${q.questionText}
                <div style="border-bottom: 1px dotted #888; height: 26px; margin-top: 3px;"></div>
                <div style="font-size: 8pt; color: #276749;"><i>Beklenen Cevap: ${q.expectedAnswer}</i></div>
            </div>
            """.trimIndent()
        }

        val rubricsHtml = speakingRubric.joinToString("\n") { r ->
            """
            <tr>
                <td><b>${r.criteriaName}</b><br><small>${r.description}</small></td>
                <td style="text-align:center; font-weight:bold;">${r.maxPoints}</td>
                <td style="text-align:center; width:60px;"></td>
            </tr>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${grade} Dinleme ve Konuşma Becerileri Sınavı</title>
            <style>
                @page { size: A4 portrait; margin: 12mm; }
                body { font-family: 'Times New Roman', serif; font-size: 9.5pt; color: #111; line-height: 1.35; margin: 0; padding: 0; }
                .header { text-align: center; border-bottom: 2px solid #234e52; padding-bottom: 4px; margin-bottom: 10px; }
                .header h1 { font-size: 12pt; margin: 0; color: #234e52; text-transform: uppercase; }
                .header h2 { font-size: 10.5pt; margin: 2px 0; }
                .box { border: 1px solid #cbd5e0; padding: 8px 10px; margin-bottom: 10px; border-radius: 4px; break-inside: avoid; }
                .box-title { font-weight: bold; color: #234e52; margin-bottom: 4px; border-bottom: 1px solid #e2e8f0; padding-bottom: 2px; }
                .meta-table { width: 100%; border-collapse: collapse; margin-bottom: 8px; font-size: 9pt; }
                .meta-table td { border: 1px solid #a0aec0; padding: 3px 5px; }
                table.rubric-table { width: 100%; border-collapse: collapse; font-size: 8.5pt; margin-top: 4px; }
                table.rubric-table th, table.rubric-table td { border: 1px solid #cbd5e0; padding: 4px 6px; }
                table.rubric-table th { background: #f7fafc; }
                .footer { margin-top: 15px; border-top: 1px solid #718096; padding-top: 4px; font-size: 8.5pt; display: flex; justify-content: space-between; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>${grade.uppercase()} TÜRKÇE DERSİ DİNLEME VE KONUŞMA BECERİLERİ SINAVI</h2>
                <div style="font-size: 8.5pt; color: #4a5568;">MEB Ölçme ve Değerlendirme Yönetmeliği (%25 Dinleme + %25 Konuşma)</div>
            </div>

            <table class="meta-table">
                <tr>
                    <td style="width:50%;"><b>Öğrenci Adı Soyadı:</b> ...........................................................</td>
                    <td style="width:25%;"><b>Sınıf/No:</b> ........ / ........</td>
                    <td style="width:25%;"><b>Tarih:</b> ...../...../202...</td>
                </tr>
            </table>

            <!-- BÖLÜM 1: DİNLEME BECERİSİ SINAVI (%25) -->
            <div class="box">
                <div class="box-title">BÖLÜM I: DİNLEME BECERİSİ SINAVI (100 PUAN -> %25 Ağırlık)</div>
                <div style="font-size: 8.5pt; color: #4a5568; margin-bottom: 6px;">
                    <b>Metin Başlığı:</b> ${listening.title}<br>
                    <i>(Metin öğretmen tarafından iki kez okunmuştur.)</i>
                </div>
                $qHtml
            </div>

            <!-- BÖLÜM 2: KONUŞMA BECERİSİ SINAVI (%25) -->
            <div class="box">
                <div class="box-title">BÖLÜM II: KONUŞMA BECERİSİ SINAVI (100 PUAN -> %25 Ağırlık)</div>
                <div style="font-size: 8.5pt; margin-bottom: 6px;">
                    <b>Öğrencinin Konusu:</b> ${speaking.topicTitle}<br>
                    <b>Yönerge:</b> ${speaking.guidancePrompt} (${speaking.speechDurationMinutes} Dakika)
                </div>

                <table class="rubric-table">
                    <thead>
                        <tr>
                            <th>Değerlendirme Kriteri (MEB Konuşma Barem Ölçeği)</th>
                            <th style="width:50px;">Tam Puan</th>
                            <th style="width:60px;">Alınan Puan</th>
                        </tr>
                    </thead>
                    <tbody>
                        $rubricsHtml
                        <tr style="background:#edf2f7; font-weight:bold;">
                            <td>KONUŞMA BECERİSİ TOPLAM PUANI</td>
                            <td style="text-align:center;">100</td>
                            <td style="text-align:center;"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="footer">
                <div>Türkçe Dersi Öğretmeni: <b>$teacherName</b></div>
                <div>Yazılı Notu: ....... + Dinleme: ....... + Konuşma: ....... = Dönem Puanı</div>
                <div>İmza: .............................</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
