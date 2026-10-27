package com.example.data.curriculum

data class GradeExamQuestion(
    val id: String,
    val grade: String, // "5. Sınıf", "6. Sınıf", "7. Sınıf", "8. Sınıf"
    val type: String, // "Açık Uçlu", "Çoktan Seçmeli", "Sözel Mantık", "Görsel Yorumlama"
    val outcomeCode: String,
    val title: String,
    val readingPassage: String? = null,
    val questionText: String,
    val options: List<String>? = null,
    val correctAnswer: String,
    val rubric: String,
    val points: Int = 10,
    val mebScenario: String // "MEB 1. Senaryo (Ortak Sınav)", "MEB 2. Senaryo", "LGS Yeni Nesil"
)

data class WorksheetExercise(
    val instruction: String,
    val items: List<String>,
    val answerKey: String
)

data class GradeLessonMaterial(
    val id: String,
    val grade: String,
    val title: String,
    val category: String, // "Çalışma Kağıdı", "Etkinlik Yaprağı", "Grafik Düzenleyici", "Özet Formül"
    val objective: String,
    val summary: String,
    val instructions: String,
    val exercises: List<WorksheetExercise>
)

data class ThemeTextItem(
    val title: String,
    val author: String,
    val textType: String // "Hikâye Edici Metin", "Bilgilendirici Metin", "Şiir", "Dinleme/İzleme Metni", "Serbest Okuma"
)

data class TextbookTheme(
    val themeNumber: Int,
    val themeName: String,
    val keyConcepts: List<String>,
    val texts: List<ThemeTextItem>,
    val grammarFocus: String,
    val listeningSummary: String,
    val assessmentNotes: String
)

data class GradeTextbookGuide(
    val grade: String,
    val publisher: String,
    val totalThemes: Int = 8,
    val themes: List<TextbookTheme>
)

data class KazanimTestQuestion(
    val number: Int,
    val passage: String? = null,
    val prompt: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctKey: String, // "A", "B", "C", "D"
    val explanation: String
)

data class KazanimTesti(
    val id: String,
    val grade: String,
    val testTitle: String,
    val topicName: String,
    val outcomeCode: String,
    val questions: List<KazanimTestQuestion>
)

object GradeLevelsRepository {

    val grades = listOf("5. Sınıf", "6. Sınıf", "7. Sınıf", "8. Sınıf")

    // =========================================================================
    // 1. SINAV SORULARI (5, 6, 7 MEB Açık Uçlu Ortak Sınav & 8 LGS Yeni Nesil)
    // =========================================================================
    val examQuestions: List<GradeExamQuestion> = listOf(
        // ------------------ 5. SINIF SORULARI ------------------
        GradeExamQuestion(
            id = "Q_5_1",
            grade = "5. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.5.3.4",
            title = "Gerçek ve Mecaz Anlam Tespiti",
            readingPassage = "Köyümüzün üzerini akşamüstü kalın bir sis perdesi kapladı. Yaşlı ninem, yaktığı sobanın başında bize tatlı anılarını anlatırken bakışlarındaki sıcaklık odanın soğuğunu unutturmuştu.",
            questionText = "Yukarıdaki metinde geçen 'perde' ve 'sıcaklık' sözcüklerinin metindeki anlam özelliklerini (gerçek veya mecaz) belirleyiniz ve gerekçenizi birer cümleyle yazınız.",
            correctAnswer = "1. 'Perde' sözcüğü metinde mecaz anlamda kullanılmıştır (görüşü engelleyen yoğun sis tabakası anlamındadır).\n2. 'Sıcaklık' sözcüğü mecaz anlamdadır (ısı anlamında değil, sevgi ve samimiyet anlamında kullanılmıştır).",
            rubric = "Her doğru tespit ve gerekçe 5 puandır. Toplam: 10 Puan.",
            points = 10,
            mebScenario = "MEB 1. Senaryo (Ortak Sınav)"
        ),
        GradeExamQuestion(
            id = "Q_5_2",
            grade = "5. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.5.3.11",
            title = "Ses Olayları (Ünsüz Benzeşmesi ve Yumuşaması)",
            readingPassage = null,
            questionText = "Aşağıdaki cümlelerde altı çizili sözcüklerde meydana gelen ses olayını (ünsüz yumuşaması / ünsüz benzeşmesi) karşılarına yazınız:\na) Bahçedeki yaprağı eline aldı.\nb) Sınıftan çıkan çocuk hızla koştu.",
            correctAnswer = "a) Yaprağı: Ünsüz Yumuşaması (k -> ğ)\nb) Sınıftan: Ünsüz Benzeşmesi / Sertleşmesi (d -> t)",
            rubric = "a şıkkı doğru ise 5 puan, b şıkkı doğru ise 5 puan.",
            points = 10,
            mebScenario = "MEB 1. Senaryo (Ortak Sınav)"
        ),
        GradeExamQuestion(
            id = "Q_5_3",
            grade = "5. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.5.3.16",
            title = "Metin Ana Fikri ve Başlık Belirleme",
            readingPassage = "Dostluk bir tohum gibidir; emekle, samimiyetle ve dürüstlükle sulanmazsa kurur gider. İnsan hayatta binlerce kişiyle tanışabilir ancak zor gününde omzunu dayayabileceği gerçek dostların sayısı bir elin parmaklarını geçmez.",
            questionText = "Metne uygun yaratıcı bir başlık belirleyiniz ve metnin ana fikrini tek bir cümle ile açıklayınız.",
            correctAnswer = "Başlık: Gerçek Dostluğun Değeri / Dostluk Ağacı\nAna Fikir: Gerçek dostluklar özen, samimiyet ve fedakârlıkla ayakta kalır.",
            rubric = "Başlık 3 puan, amaca uygun ana fikir cümlesi 7 puan.",
            points = 10,
            mebScenario = "MEB 2. Senaryo"
        ),

        // ------------------ 6. SINIF SORULARI ------------------
        GradeExamQuestion(
            id = "Q_6_1",
            grade = "6. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.6.3.8",
            title = "İsim Tamlamaları Tür Analizi",
            readingPassage = "Ahşap evin çatısı fırtınada uçtu. Mahalle sakinleri demir kapının önüne toplanarak sokak lambasının cılız ışığında ne yapacaklarını konuştular.",
            questionText = "Metinde geçen altı çizili isim tamlamalarını bularak türlerini (Belirtili, Belirtisiz, Zincirleme İsim Tamlaması) yazınız.",
            correctAnswer = "1. 'evin çatısı' -> Belirtili İsim Tamlaması (Tamlayan ve tamlanan ek almıştır)\n2. 'demir kapı' -> Takısız/Sıfat kabul edilmezse 'sokak lambasının cılız ışığı' -> Zincirleme İsim Tamlaması\n3. 'mahalle sakinleri' -> Belirtisiz İsim Tamlaması",
            rubric = "3 tamlama doğru bulunup türü doğru yazılmışsa her biri 4/3 puandan toplam 10 puan.",
            points = 10,
            mebScenario = "MEB 1. Senaryo (Ortak Sınav)"
        ),
        GradeExamQuestion(
            id = "Q_6_2",
            grade = "6. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.6.3.9",
            title = "Zamirlerin Cümledeki İşlevleri",
            readingPassage = "Herkes kendi görevini eksiksiz yapmalıydı. Masadaki kitapları gören çocuk, 'Bunları kim buraya bıraktı?' diye sordu.",
            questionText = "Metinde geçen zamirleri (adılları) bularak türleriyle eşleştiriniz:\n- Belgisiz Zamir:\n- İşaret Zamiri:\n- Soru Zamiri:",
            correctAnswer = "- Belgisiz Zamir: Herkes\n- İşaret Zamiri: Bunları, buraya\n- Soru Zamiri: Kim",
            rubric = "Her doğru zamir eşleştirmesi 3.3 puan. Toplam 10 puan.",
            points = 10,
            mebScenario = "MEB 2. Senaryo"
        ),

        // ------------------ 7. SINIF SORULARI ------------------
        GradeExamQuestion(
            id = "Q_7_1",
            grade = "7. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.7.3.10",
            title = "Fiillerin Anlam Özellikleri (İş, Oluş, Durum)",
            readingPassage = "Sonbahar gelince bahçedeki elmalar sarardı. Ali heyecanla son romanını okuyor, dedesi ise minderde dinleniyordu.",
            questionText = "Metindeki koyu yazılan fiilleri anlam özelliklerine göre (İş / Oluş / Durum) gruplandırınız:\n1. sarardı:\n2. okuyor:\n3. dinleniyordu:",
            correctAnswer = "1. sarardı: Oluş Fiili (Zamanla kendiliğinden gerçekleşir, nesne almaz)\n2. okuyor: İş (Kılış) Fiili (Neyi okuyor? romanını -> nesne alır)\n3. dinleniyordu: Durum Fiili (Öznenin iradesiyle içinde bulunduğu durumu anlatır, nesne almaz)",
            rubric = "Her fiil ve açıklaması 3.3 puan. Toplam 10 puan.",
            points = 10,
            mebScenario = "MEB 1. Senaryo (Ortak Sınav)"
        ),
        GradeExamQuestion(
            id = "Q_7_2",
            grade = "7. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.7.3.12",
            title = "Ek Fiilin Görevleri ve Cümle Analizi",
            readingPassage = "Hava dün oldukça soğuktu. Eğer vaktinde gelseydin treni kaçırmazdın.",
            questionText = "Verilen iki cümledeki ek fiilleri (ek eylemleri) tespit ediniz ve ek fiilin üstlendiği iki temel görevi (İsmi yüklem yapma / Basit zamanlı fiili birleşik zamanlı yapma) hangi cümlede üstlendiğini belirtiniz.",
            correctAnswer = "1. Cümle: 'soğuk-tu' -> 'soğuk' ismi ek fiilin görülen geçmiş zamanını alarak yüklem olmuştur (İsmi yüklem yapma görevi).\n2. Cümle: 'gel-se-y-di-n' (şartın hikayesi) veya 'kaçır-maz-dı-n' (geniş zamanın olumsuzunun hikayesi) -> Basit zamanlı fiili birleşik zamanlı fiil yapmıştır.",
            rubric = "Ek fiillerin tespiti 4 puan, görev açıklamaları 6 puan. Toplam 10 puan.",
            points = 10,
            mebScenario = "MEB 2. Senaryo"
        ),

        // ------------------ 8. SINIF (LGS) SORULARI ------------------
        GradeExamQuestion(
            id = "Q_8_1",
            grade = "8. Sınıf",
            type = "Sözel Mantık",
            outcomeCode = "T.8.3.14",
            title = "LGS Sözel Mantık ve Muhakeme Tablosu",
            readingPassage = "Ahmet, Burak, Ceyda, Deniz ve Elif isimli beş öğrenci bir kütüphaneden Türk Edebiyatı, Dünya Klasikleri ve Bilim Kurgu türlerinde kitaplar ödünç almıştır. Kişiler ve aldıkları kitap türleriyle ilgili bilinenler şunlardır:\n• Herkes sadece bir kitap almıştır.\n• Türk Edebiyatı kitabını iki kişi almıştır.\n• Burak ve Elif aynı türden kitap almamıştır.\n• Ahmet Bilim Kurgu kitabı almıştır.\n• Ceyda Türk Edebiyatı kitabı almıştır.",
            questionText = "Bu bilgilere göre aşağıdakilerden hangisi KESİNLİKLE YANLIŞTIR?\nA) Deniz Türk Edebiyatı kitabı almıştır.\nB) Burak Dünya Klasikleri kitabı almıştır.\nC) Elif Bilim Kurgu kitabı almıştır.\nD) Ceyda ve Deniz farklı türde kitap almıştır.",
            options = listOf(
                "A) Deniz Türk Edebiyatı kitabı almıştır.",
                "B) Burak Dünya Klasikleri kitabı almıştır.",
                "C) Elif Bilim Kurgu kitabı almıştır.",
                "D) Ceyda ve Deniz farklı türde kitap almıştır."
            ),
            correctAnswer = "C) Elif Bilim Kurgu kitabı almıştır.",
            rubric = "Ahmet Bilim Kurgu almıştır ve Bilim Kurgu türünden başka bahsedilmediği ya da kontenjan dağılımında Elif'in Bilim Kurgu alamayacağı netleşir. Detaylı çözüm analizi 10 puan.",
            points = 10,
            mebScenario = "LGS Yeni Nesil"
        ),
        GradeExamQuestion(
            id = "Q_8_2",
            grade = "8. Sınıf",
            type = "Çoktan Seçmeli",
            outcomeCode = "T.8.3.9",
            title = "Fiilimsilerin Cümledeki Görevi (LGS Tarzı)",
            readingPassage = "Güneşin kavurduğu (I) topraklarda yürüyüşe çıkan (II) gezginler, suyun şırıltısını duyunca (III) adımlarını sıklaştırarak çağlayana doğru yöneldiler (IV).",
            questionText = "Bu cümledeki numaralanmış sözcüklerden hangisi fiilimsi DEĞİLDİR?\nA) I\nB) II\nC) III\nD) IV",
            options = listOf("A) I", "B) II", "C) III", "D) IV"),
            correctAnswer = "D) IV (yöneldiler)",
            rubric = "I: Sıfat-fiil (-dık), II: Sıfat-fiil (-an), III: Zarf-fiil (-ınca), IV: Çekimli fiildir (yönelmek fiili görülen geçmiş zaman ve şahıs eki almıştır). Doğru cevap D şıkkıdır.",
            points = 10,
            mebScenario = "LGS Yeni Nesil"
        ),
        GradeExamQuestion(
            id = "Q_8_3",
            grade = "8. Sınıf",
            type = "Açık Uçlu",
            outcomeCode = "T.8.3.17",
            title = "Cümlenin Ögeleri Ayırma ve Vurgu Analizi",
            readingPassage = null,
            questionText = "'Cumhuriyetimizin kurucusu Gazi Mustafa Kemal Atatürk, milletine olan sarsılmaz inancını Gençliğe Hitabe ile ebedîleştirmiştir.'\nYukarıdaki cümlenin ögelerini sırasıyla yazınız ve cümlede vurgulanan ögenin adını belirtiniz.",
            correctAnswer = "Öge Dizilişi:\n- Özne: Cumhuriyetimizin kurucusu Gazi Mustafa Kemal Atatürk\n- Belirtili Nesne: milletine olan sarsılmaz inancını\n- Zarf Tamlayıcısı: Gençliğe Hitabe ile\n- Yüklem: ebedîleştirmiştir.\nVurgulanan Öge: Fiil cümlelerinde vurgu yüklemden bir önceki ögededir, dolayısıyla 'Gençliğe Hitabe ile' (Zarf Tamlayıcısı) vurgulanmıştır.",
            rubric = "Ögelerin doğru tespiti 7 puan, vurgulanan ögenin kuralıyla belirtilmesi 3 puan. Toplam 10 puan.",
            points = 10,
            mebScenario = "MEB 1. Senaryo (Ortak Sınav)"
        )
    )

    // =========================================================================
    // 2. DERS MATERYALLERİ (Çalışma Kağıtları, Grafik Düzenleyiciler, Etkinlikler)
    // =========================================================================
    val lessonMaterials: List<GradeLessonMaterial> = listOf(
        GradeLessonMaterial(
            id = "MAT_5_1",
            grade = "5. Sınıf",
            title = "Kök ve Ek Dedektifi Çalışma Kağıdı",
            category = "Çalışma Kağıdı",
            objective = "T.5.3.10. Kök ve ekleri ayırt eder, yapım eki ile çekim eki arasındaki farkı kavrar.",
            summary = "Öğrencilerin kelimelerin anlamlı en küçük parçası olan kökü bulmalarını ve kökün isim kök mü fiil kök mü olduğunu '-mek / -mak' ekiyle test etmelerini sağlayan kılavuz etkinlik.",
            instructions = "Aşağıdaki kelimelerin kökünü bulunuz, kökün türünü (İsim Kök / Fiil Kök) işaretleyiniz ve aldığı ekin türünü yazınız.",
            exercises = listOf(
                WorksheetExercise(
                    instruction = "Kelimeleri kök ve eklerine ayırınız:",
                    items = listOf(
                        "1. Balıkçı -> Kök: [.......] Tür: [İsim/Fiil] Ek: [.......]",
                        "2. Sevgi -> Kök: [.......] Tür: [İsim/Fiil] Ek: [.......]",
                        "3. Evlerimiz -> Kök: [.......] Tür: [İsim/Fiil] Ek: [.......]",
                        "4. Gözlükçülük -> Kök: [.......] Tür: [İsim/Fiil] Ek: [.......]",
                        "5. Koşucu -> Kök: [.......] Tür: [İsim/Fiil] Ek: [.......]"
                    ),
                    answerKey = "1. balık (İsim), -çı (Yapım eki) | 2. sev- (Fiil), -gi (Yapım eki) | 3. ev (İsim), -ler, -imiz (Çekim eki) | 4. göz (İsim), -lük, -çü, -lük (Yapım ekleri) | 5. koş- (Fiil), -ucu (Yapım eki)"
                )
            )
        ),
        GradeLessonMaterial(
            id = "MAT_6_1",
            grade = "6. Sınıf",
            title = "Hikâye Haritası ve Unsurları Analiz Şablonu",
            category = "Grafik Düzenleyici",
            objective = "T.6.3.14. Hikâye edici metinlerin unsurlarını (olay, kişi, zaman, mekân, anlatıcı) belirler.",
            summary = "Ömer Seyfettin'in 'Kaşağı' veya Refik Halid Karay'ın 'Eskici' hikâyelerinin 5 ana unsurunu sistematik şekilde görselleştiren ve öğrenciyi çözümlemeye yönlendiren analitik şablon.",
            instructions = "Okunan hikâyeye göre aşağıdaki hikâye haritası kutucuklarını eksiksiz doldurunuz.",
            exercises = listOf(
                WorksheetExercise(
                    instruction = "Hikâye unsurlarını çıkarınız:",
                    items = listOf(
                        "[OLAY ÖRGÜSÜ]: Başlangıç, düğüm ve çözüm noktalarını 3 maddeyle yazınız.",
                        "[ŞAHIS KADROSU]: Başkahraman ve yardımcı karakterlerin kişilik özellikleri.",
                        "[ZAMAN]: Olayın geçtiği mevsim, günün vakti veya tarihi dönem.",
                        "[MEKÂN (YER)]: Olayın cereyan ettiği çevre ve betimleyici ayrıntılar.",
                        "[ANLATICI]: 1. tekil kişi (Ben) mi, 3. tekil kişi (O - İlahi/Gözlemci) mi?"
                    ),
                    answerKey = "Hikâye unsurları metnin kurgusuna göre dereceli puanlama anahtarıyla (rubrik) değerlendirilir."
                )
            )
        ),
        GradeLessonMaterial(
            id = "MAT_7_1",
            grade = "7. Sınıf",
            title = "Fiil Kipleri ve Anlam Kayması Çarkı",
            category = "Etkinlik Yaprağı",
            objective = "T.7.3.11. Fiillerin kiplerini ve anlam (zaman) kaymasını kavrar.",
            summary = "Haber (bildirme) ve dilek (tasarlama) kiplerinin karşılaştırılması ile bir kipin başka bir kip yerine kullanıldığı anlam kayması durumlarını tespit ettiren zengin içerikli alıştırma kağıdı.",
            instructions = "Cümlelerde kullanılan kip ile kastedilen kipi belirleyerek anlam kaymasını açıklayınız.",
            exercises = listOf(
                WorksheetExercise(
                    instruction = "Aşağıdaki cümlelerdeki anlam kaymalarını tespit ediniz:",
                    items = listOf(
                        "1. Fatih Sultan Mehmet, 1453'te İstanbul'u fetheder. (Kullanılan: Geniş Zaman -> Kastedilen: [.........])",
                        "2. Yarın sabah erkenden yola çıkıyoruz. (Kullanılan: Şimdiki Zaman -> Kastedilen: [.........])",
                        "3. Nasreddin Hoca bir gün göle maya çalar. (Kullanılan: Geniş Zaman -> Kastedilen: [.........])",
                        "4. Derslerinize her gün düzenli çalışacaksınız! (Kullanılan: Gelecek Zaman -> Kastedilen: [.........])"
                    ),
                    answerKey = "1. Kastedilen: Duyulan Geçmiş Zaman (-miş) | 2. Kastedilen: Gelecek Zaman (-ecek) | 3. Kastedilen: Duyulan Geçmiş Zaman (-miş) | 4. Kastedilen: Emir Kipi"
                )
            )
        ),
        GradeLessonMaterial(
            id = "MAT_8_1",
            grade = "8. Sınıf",
            title = "LGS Fiilimsiler & Cümle Ögeleri Strateji Formülü",
            category = "Özet Formül",
            objective = "T.8.3.9 & T.8.3.17. LGS sınavında soru kaçırmamak için kritik tuzaklar, kalıcı isim ayrımları ve öge bulma sırası.",
            summary = "LGS Türkçe testinin belkemiği olan konular için hap bilgiler, akrostiş kodlamalar (MAYIŞMAK, ANASIMEZARDİKECEKMİŞ, KENYALI ASİYE...), öge bulma sırası (Y-Ö-N-T) ve tuzak sorular kılavuzu.",
            instructions = "Bu çalışma yaprağını duvara asarak veya defterinizin başına yapıştırarak her denemeden önce 5 dakika tekrar ediniz.",
            exercises = listOf(
                WorksheetExercise(
                    instruction = "Kritik Kontrol Listesi:",
                    items = listOf(
                        "★ YÖNT KURALI: Cümle ögeleri aranırken mutlaka önce Yüklem, sonra Özne, sonra Nesne, sonra Tümleçler bulunur!",
                        "★ KALICI İSİM TUZAĞI: Dolma, sarma, dondurma, çakmak, ekmek, giriş, çıkış fiilimsi değil, bir nesnenin adı olmuştur.",
                        "★ SIFAT-FİİL vs ÇEKİMLİ FİİL: 'Gelecek hafta görüşürüz.' (Sıfat-fiil) vs 'O buraya yarın gelecek.' (Çekimli fiil).",
                        "★ HİÇBİR ZAMAN TAMLAMALAR VE BİRLEŞİK FİİLLER BÖLÜNMEZ! Bütün halinde tek bir ögedir."
                    ),
                    answerKey = "Bu kurallar LGS sınavında %100 doğruluk oranı sağlar."
                )
            )
        )
    )

    // =========================================================================
    // 3. MEB TÜRKÇE DERS KİTAPLARI REHBERİ (5, 6, 7, 8. Sınıf - 8 Tema Detayı)
    // =========================================================================
    val textbookGuides: List<GradeTextbookGuide> = listOf(
        // 5. SINIF MEB DERS KİTABI
        GradeTextbookGuide(
            grade = "5. Sınıf",
            publisher = "MEB Devlet Kitapları / Anka Kuşu Yayınları",
            themes = listOf(
                TextbookTheme(
                    themeNumber = 1,
                    themeName = "Birey ve Toplum",
                    keyConcepts = listOf("Paylaşma", "Empati", "İletişim", "Saygı"),
                    texts = listOf(
                        ThemeTextItem("Memleket İsterim", "Cahit Sıtkı Tarancı", "Şiir"),
                        ThemeTextItem("Lokumcular", "Fakir Baykurt", "Hikâye Edici Metin"),
                        ThemeTextItem("Gülümsemenin Gücü", "Doğan Cüceloğlu", "Bilgilendirici Metin"),
                        ThemeTextItem("Konuşan Ağaç", "Dinleme Metni", "Dinleme/İzleme Metni"),
                        ThemeTextItem("Büyük İnsanlık", "Nazım Hikmet", "Serbest Okuma")
                    ),
                    grammarFocus = "Gerçek, Mecaz, Terim Anlam; Kök ve Ek Kavramı; Nokta ve Virgül Kullanımı.",
                    listeningSummary = "Konuşan Ağaç metninde doğanın insanla sessiz konuşması ve nezaketin önemi dramatize edilir.",
                    assessmentNotes = "Tema sonu değerlendirmede öğrencilerin sözcükte anlam ve okuduğunu anlama kazanımları ölçülür."
                ),
                TextbookTheme(
                    themeNumber = 2,
                    themeName = "Millî Mücadele ve Atatürk",
                    keyConcepts = listOf("Vatan Sevgisi", "Fedakârlık", "Cumhuriyet", "Kahramanlık"),
                    texts = listOf(
                        ThemeTextItem("Çanakkale Mahşeri", "Mehmet Niyazi", "Hikâye Edici Metin"),
                        ThemeTextItem("Atatürk'ün Çocuk Sevgisi", "Afet İnan", "Bilgilendirici Metin"),
                        ThemeTextItem("Mustafa Kemal'in Kağnısı", "Fazıl Hüsnü Dağlarca", "Şiir"),
                        ThemeTextItem("Kınalı Ali'nin Mektubu", "Dinleme Metni", "Dinleme/İzleme Metni"),
                        ThemeTextItem("İstiklal Marşı ve Mehmet Akif", "M. Ertuğrul Düzdağ", "Serbest Okuma")
                    ),
                    grammarFocus = "Eş ve Zıt Anlamlı Kelimeler, Eş Sesli (Sesteş) Kelimeler, Büyük Harflerin Yazımı.",
                    listeningSummary = "Kınalı Ali'nin cepheye uğurlanırken saçına kına yakılmasının milli ve manevi anlamı.",
                    assessmentNotes = "Milli şuur ve vatan bilinci temalı paragraf yazma çalışması puanlanır."
                ),
                TextbookTheme(
                    themeNumber = 3,
                    themeName = "Doğa ve Evren",
                    keyConcepts = listOf("Çevre Bilinci", "Mevsimler", "Gezegenler", "Canlılar"),
                    texts = listOf(
                        ThemeTextItem("Ormanın Gizli Dili", "Peter Wohlleben", "Bilgilendirici Metin"),
                        ThemeTextItem("Yağmurun Şarkısı", "Cahit Külebi", "Şiir"),
                        ThemeTextItem("Küçük Kar Tanesi", "Andersen", "Hikâye Edici Metin"),
                        ThemeTextItem("Kutup Ayılarının Dünyası", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Ünsüz Yumuşaması ve Benzeşmesi, Soru Eki (-mi) ve 'de/da' Yazımı.",
                    listeningSummary = "Küresel ısınmanın kutup canlıları üzerindeki etkileri ve çevre koruma önlemleri.",
                    assessmentNotes = "Metinle ilgili 5N1K soruları ve neden-sonuç cümleleri tespiti."
                ),
                TextbookTheme(
                    themeNumber = 4,
                    themeName = "Millî Kültürümüz",
                    keyConcepts = listOf("Gelenekler", "Masallar", "El Sanatları", "Misafirperverlik"),
                    texts = listOf(
                        ThemeTextItem("Dede Korkut'tan Tepegöz", "Dede Korkut", "Masal/Destan"),
                        ThemeTextItem("Ebru Sanatının Büyüsü", "Hikmet Barutçugil", "Bilgilendirici Metin"),
                        ThemeTextItem("Halı Dokuyan Kızlar", "Bedri Rahmi Eyüboğlu", "Şiir"),
                        ThemeTextItem("Nasreddin Hoca Fıkraları", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "İsim ve Fiil Kökleri, Yapım Ekleri (-lik, -ci, -li, -siz).",
                    listeningSummary = "Nasreddin Hoca fıkralarındaki ince mizah ve toplumsal hiciv unsurları.",
                    assessmentNotes = "Geleneksel motifler üzerine betimleyici yazı çalışması."
                )
            )
        ),

        // 6. SINIF MEB DERS KİTABI
        GradeTextbookGuide(
            grade = "6. Sınıf",
            publisher = "MEB Yayınları",
            themes = listOf(
                TextbookTheme(
                    themeNumber = 1,
                    themeName = "Duygular ve Paylaşım",
                    keyConcepts = listOf("Sevgi", "Merhamet", "Dostluk", "Vefa"),
                    texts = listOf(
                        ThemeTextItem("Kaşağı", "Ömer Seyfettin", "Hikâye Edici Metin"),
                        ThemeTextItem("Dostluk Üzerine", "Montaigne", "Deneme"),
                        ThemeTextItem("Bir Fotoğrafa Bakarken", "Sunay Akın", "Anlatı"),
                        ThemeTextItem("İyilik Gemisi", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "İsimler (Adlar), İsim Çekim Ekleri (Çoğul, Hal, İyelik Ekleri).",
                    listeningSummary = "İyilik Gemisi dinleme metninde yardımlaşmanın insan ruhundaki dönüştürücü etkisi.",
                    assessmentNotes = "Kaşağı metnindeki vicdan azabı ve dürüstlük çatışmasının değerlendirilmesi."
                ),
                TextbookTheme(
                    themeNumber = 2,
                    themeName = "Millî Mücadele ve Atatürk",
                    keyConcepts = listOf("Bandırma Vapuru", "Kuva-yı Milliye", "Bağımsızlık"),
                    texts = listOf(
                        ThemeTextItem("19 Mayıs 1919 Samsun", "Falih Rıfkı Atay", "Tarihî Anı"),
                        ThemeTextItem("Dumlupınar Şiiri", "Faruk Nafiz Çamlıbel", "Şiir"),
                        ThemeTextItem("Seyit Onbaşı", "Kaan Turan", "Hikâye Edici Metin"),
                        ThemeTextItem("Cumhuriyetin İlanı", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Sıfatlar (Ön Adlar): Niteleme ve Belirtme Sıfatları.",
                    listeningSummary = "29 Ekim 1923 Meclis tutanakları ve halkın coşkusunun canlandırılması.",
                    assessmentNotes = "Sıfat tamlamalarının metin betimlemesindeki gücünün analiz edilmesi."
                ),
                TextbookTheme(
                    themeNumber = 3,
                    themeName = "Bilim ve Teknoloji",
                    keyConcepts = listOf("Buluşlar", "Yapay Zeka", "Uzay Keşfi", "Gelecek"),
                    texts = listOf(
                        ThemeTextItem("İlk Türk Astronot Gezeravcı", "TÜBİTAK Bilim Çocuk", "Haber/Bilgilendirici"),
                        ThemeTextItem("Robotların Dünyası", "Asimov Uyarlaması", "Bilim Kurgu"),
                        ThemeTextItem("Geleceğin Şehirleri", "Mimarlık Dergisi", "Bilgilendirici Metin"),
                        ThemeTextItem("Telefonun İcadı", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "İsim ve Sıfat Tamlamaları, İki Nokta ve Tırnak İşareti.",
                    listeningSummary = "Alexander Graham Bell'in ses iletim deneyleri ve bilimin sabır gerektirdiği.",
                    assessmentNotes = "Geleceğe dair bir icat tasarlayıp özelliklerini yazılı olarak anlatma."
                )
            )
        ),

        // 7. SINIF MEB DERS KİTABI
        GradeTextbookGuide(
            grade = "7. Sınıf",
            publisher = "Özgün / MEB Yayınları",
            themes = listOf(
                TextbookTheme(
                    themeNumber = 1,
                    themeName = "Duygular ve İnsan",
                    keyConcepts = listOf("Özlem", "Umut", "Empati", "İç Huzur"),
                    texts = listOf(
                        ThemeTextItem("Eskici", "Refik Halid Karay", "Hikâye Edici Metin"),
                        ThemeTextItem("Açılan Bir Güller Gibi", "Ahmet Muhip Dıranas", "Şiir"),
                        ThemeTextItem("Yalnızlık Paylaşılmaz", "Özdemir Asaf", "Deneme/Şiir"),
                        ThemeTextItem("Küçük Kemancı", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Fiillerin Anlam Özellikleri (İş, Oluş, Durum) ve Fiil Çekimi (Haber ve Dilek Kipleri).",
                    listeningSummary = "Küçük bir sokak kemancısının müziğiyle insanların yüreğine dokunması.",
                    assessmentNotes = "Eskici hikâyesindeki anadilin gurbetteki birleştirici gücü tartışılır."
                ),
                TextbookTheme(
                    themeNumber = 2,
                    themeName = "Millî Kültür ve Tarih",
                    keyConcepts = listOf("Mimar Sinan", "Divan-ı Hikmet", "Tarih Bilinci"),
                    texts = listOf(
                        ThemeTextItem("Selimiye'nin Zirvesinde", "Mithat Cemal Kuntay", "Biyografi"),
                        ThemeTextItem("Hoca Ahmed Yesevi", "Fuad Köprülü", "İnceleme"),
                        ThemeTextItem("Yunus Emre'den İlahiler", "Yunus Emre", "Tasavvufi Şiir"),
                        ThemeTextItem("Mehteran-ı Hümayun", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Ek Fiil (Ek Eylem) ve Görevleri, Zarflar (Belirteçler).",
                    listeningSummary = "Mehter müziğinin ritmi ve Türk askeri geleneğindeki psikolojik etkisi.",
                    assessmentNotes = "Ek fiilin iki temel işlevinin cümleler üzerinde gösterilmesi."
                ),
                TextbookTheme(
                    themeNumber = 3,
                    themeName = "Doğa ve Canlılar Alemi",
                    keyConcepts = listOf("Ekoloji", "Biyoçeşitlilik", "Denizler", "Koruma"),
                    texts = listOf(
                        ThemeTextItem("Akdeniz'in Mavi Sırrı", "Halikarnas Balıkçısı", "Anlatı"),
                        ThemeTextItem("Göçmen Kuşların Rotası", "Atlas Dergisi", "Bilgilendirici Metin"),
                        ThemeTextItem("Ağaç Diyor ki", "Cahit Sıtkı", "Şiir"),
                        ThemeTextItem("Mercan Resifleri", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Fiillerde Anlam (Zaman) Kayması, Fiillerde Yapı (Basit, Türemiş, Birleşik).",
                    listeningSummary = "Deniz altı ekosistemlerinin korunması ve mercan ağarmasının sebepleri.",
                    assessmentNotes = "Doğa kirliliğine karşı afiş ve slogan hazırlama görevi."
                )
            )
        ),

        // 8. SINIF MEB DERS KİTABI (LGS ODAKLI)
        GradeTextbookGuide(
            grade = "8. Sınıf",
            publisher = "MEB Ders Kitapları",
            themes = listOf(
                TextbookTheme(
                    themeNumber = 1,
                    themeName = "Erdemler ve Hayatın Anlamı",
                    keyConcepts = listOf("Dürüstlük", "Adalet", "Çalışkanlık", "Vicdan"),
                    texts = listOf(
                        ThemeTextItem("Görünmez Adamın Aynası", "Ataol Behramoğlu", "Deneme"),
                        ThemeTextItem("Koca Sinan", "Fazıl Hüsnü Dağlarca", "Şiir"),
                        ThemeTextItem("Diyet", "Ömer Seyfettin", "Klasik Hikâye"),
                        ThemeTextItem("Zamana Direnen Değerler", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Fiilimsiler (İsim-Fiil, Sıfat-Fiil, Zarf-Fiil) ve Kalıcı İsim Ayrımı.",
                    listeningSummary = "Modern çağda erdemlerin korunması ve etik değerlerin insana kazandırdıkları.",
                    assessmentNotes = "Fiilimsi eklerinin LGS tarzı metin analizi sorularıyla denetlenmesi."
                ),
                TextbookTheme(
                    themeNumber = 2,
                    themeName = "Millî Mücadele ve Atatürk",
                    keyConcepts = listOf("Nutuk", "Kurtuluş Savaşı", "Amasya Genelgesi", "Egemenlik"),
                    texts = listOf(
                        ThemeTextItem("Nutuk'tan Seçmeler", "Mustafa Kemal Atatürk", "Söylev"),
                        ThemeTextItem("Kuvayi Milliye Destanı", "Nazım Hikmet", "Epik Şiir"),
                        ThemeTextItem("Ateşten Gömlek", "Halide Edip Adıvar", "Roman Özeti"),
                        ThemeTextItem("Sakarya Meydan Muharebesi", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Cümlenin Ögeleri (Özne, Yüklem, Nesne, Dolaylı Tümleç, Zarf Tümleci) ve Cümlede Vurgu.",
                    listeningSummary = "Sakarya savaşının dönüm noktası olan 'Hattı müdafaa yoktur, sathı müdafaa vardır' emrinin analizi.",
                    assessmentNotes = "LGS'de garanti çıkan cümlenin ögeleri çözümleme testleri."
                ),
                TextbookTheme(
                    themeNumber = 3,
                    themeName = "Bilim, Düşünce ve Gelecek",
                    keyConcepts = listOf("Eleştirel Düşünce", "Bilimsel Yöntem", "Yapay Zeka", "Felsefe"),
                    texts = listOf(
                        ThemeTextItem("Düşünmenin Gücü", "Nurettin Topçu", "Felsefi Deneme"),
                        ThemeTextItem("Kuantum Çağı", "Ali Nesin", "Popüler Bilim"),
                        ThemeTextItem("Evrenin Genişlemesi", "TÜBİTAK", "Bilgilendirici Metin"),
                        ThemeTextItem("Yapay Zeka ve İnsan", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Cümle Türleri (Yüklemine, Anlamına, Öge Dizilişine ve Yapısına Göre Cümleler).",
                    listeningSummary = "Yapay zekanın etik sınırları ve gelecekteki insan mesleklerine etkisi.",
                    assessmentNotes = "Tek yüklemli, fiilimsili birleşik ve sıralı cümlelerin LGS soru kalıplarıyla taranması."
                ),
                TextbookTheme(
                    themeNumber = 4,
                    themeName = "Sanat ve Edebiyat Dünyası",
                    keyConcepts = listOf("Estetik", "Tiyatro", "Roman Tahlili", "Şiir Bilgisi"),
                    texts = listOf(
                        ThemeTextItem("Şiir Nasıl Okunmalı?", "Ahmet Hamdi Tanpınar", "Eleştiri"),
                        ThemeTextItem("Köşebaşı Tiyatrosu", "Ahmet Kutsi Tecer", "Dram"),
                        ThemeTextItem("Bir Ressamın Not Defteri", "Abidin Dino", "Hatıra"),
                        ThemeTextItem("Klasik Müziğin Doğuşu", "Dinleme Metni", "Dinleme/İzleme Metni")
                    ),
                    grammarFocus = "Metin Türleri ve Söz Sanatları (Teşbih, Teşhis, İntak, Tezat, Mübalağa).",
                    listeningSummary = "Beste ve ritmin edebi anlatımla paralelliği.",
                    assessmentNotes = "Söz sanatları ve metin türü eşleştirme LGS denemesi."
                )
            )
        )
    )

    // =========================================================================
    // 4. KONU KAZANIM TESTLERİ (MEB ÖDSGM & EBA Standartlarında Çoktan Seçmeli)
    // =========================================================================
    val kazanimTests: List<KazanimTesti> = listOf(
        // 5. SINIF TESTİ
        KazanimTesti(
            id = "TEST_5_1",
            grade = "5. Sınıf",
            testTitle = "5. Sınıf Türkçe Kazanım Testi 1",
            topicName = "Sözcükte Anlam, Kök-Ek ve Noktalama",
            outcomeCode = "T.5.3.3 & T.5.3.10",
            questions = listOf(
                KazanimTestQuestion(
                    number = 1,
                    passage = "Yaz mevsiminde köydeki bahçemizin çiçekleri neşeyle etrafa kokular saçıyordu.",
                    prompt = "Bu cümledeki 'saçıyordu' sözcüğünün anlam özelliği aşağıdakilerden hangisidir?",
                    optionA = "Gerçek Anlam",
                    optionB = "Mecaz Anlam",
                    optionC = "Terim Anlam",
                    optionD = "Zıt Anlam",
                    correctKey = "B",
                    explanation = "'Saçmak' fiili somut nesneleri dağıtmak anlamına gelirken burada mecazen etrafa koku yaymak anlamında kullanılmıştır."
                ),
                KazanimTestQuestion(
                    number = 2,
                    passage = null,
                    prompt = "Aşağıdaki sözcüklerden hangisinin kökü 'fiil' (eylem) köküdür?",
                    optionA = "Evsiz",
                    optionB = "Tuzluk",
                    optionC = "Yazgı",
                    optionD = "Gözlük",
                    correctKey = "C",
                    explanation = "'Yazgı' kelimesinin kökü 'yaz-' fiilidir (-mak/-mek eki alabilir). Ev, tuz ve göz ise isim köküdür."
                ),
                KazanimTestQuestion(
                    number = 3,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinde ünsüz benzeşmesi (sertleşmesi) vardır?",
                    optionA = "Kitabı çantasına dikkatle koydu.",
                    optionB = "Ağacın dalına küçük bir kuş kondu.",
                    optionC = "Durakta bekleyen yolcular sabırsızlandı.",
                    optionD = "Odasının kapısını aralık bıraktı.",
                    correctKey = "C",
                    explanation = "'Durak-da' -> 'Durak-ta' (k sert ünsüzünden sonra gelen d yumuşak ünsüzü t'ye dönüşmüştür)."
                ),
                KazanimTestQuestion(
                    number = 4,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinin sonuna soru işareti (?) konulmalıdır?",
                    optionA = "Neden böyle davrandığını bir türlü anlayamadım",
                    optionB = "Kimin haklı olduğunu zaman gösterir",
                    optionC = "Bugün kütüphaneye gidecek misiniz",
                    optionD = "Kaç saat geçtiğini fark bile etmemiş",
                    correctKey = "C",
                    explanation = "C şıkkında yanıt bekleyen gerçek bir soru anlamı vardır ('gidecek misiniz?'). Diğerleri dolaylı anlatımlı düz cümlelerdir."
                )
            )
        ),

        // 6. SINIF TESTİ
        KazanimTesti(
            id = "TEST_6_1",
            grade = "6. Sınıf",
            testTitle = "6. Sınıf Türkçe Kazanım Testi 1",
            topicName = "İsimler, Sıfatlar, Zamirler ve Tamlamalar",
            outcomeCode = "T.6.3.8 & T.6.3.9",
            questions = listOf(
                KazanimTestQuestion(
                    number = 1,
                    passage = "Yağmur sonrası sokağın taşları parıldıyor, ıslak kaldırımlar üzerinde yürüyen insanlar telaşla koşuyordu.",
                    prompt = "Bu metinde geçen 'sokağın taşları' tamlamasının türü aşağıdakilerden hangisidir?",
                    optionA = "Belirtili İsim Tamlaması",
                    optionB = "Belirtisiz İsim Tamlaması",
                    optionC = "Zincirleme İsim Tamlaması",
                    optionD = "Sıfat Tamlaması",
                    correctKey = "A",
                    explanation = "'Sokağ-ın' (tamlayan eki -ın almış), 'taşlar-ı' (tamlanan eki -ı almış). İkisi de ek aldığı için Belirtili İsim Tamlamasıdır."
                ),
                KazanimTestQuestion(
                    number = 2,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinde 'belgisiz zamir' kullanılmıştır?",
                    optionA = "Bu kitabı dün akşam bir nefeste okudum.",
                    optionB = "Herkes kütüphanedeki kurallara uymalıdır.",
                    optionC = "Oraya seninle birlikte gitmek isterdim.",
                    optionD = "Hangi soruyu çözmekte zorlandın?",
                    correctKey = "B",
                    explanation = "'Herkes' sözcüğü kim oldukları kesin olarak belli olmayan kişilerin yerini tuttuğu için belgisiz zamirdir."
                ),
                KazanimTestQuestion(
                    number = 3,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinde hem niteleme hem belirtme sıfatı almış bir isim vardır?",
                    optionA = "Eski evler birer birer yıkılıyordu.",
                    optionB = "Şu geniş ovada atlar koşturuyordu.",
                    optionC = "Masanın üstünde iki kalın kitap duruyordu.",
                    optionD = "Yaz günlerinde serin gölgeler aranırdı.",
                    correctKey = "C",
                    explanation = "'Kitap' ismi; 'iki' belirtme (sayı) sıfatı ve 'kalın' niteleme sıfatı almıştır."
                ),
                KazanimTestQuestion(
                    number = 4,
                    passage = null,
                    prompt = "Aşağıdakilerin hangisinde 'için' edatı cümleye 'amaç-sonuç' anlamı katmıştır?",
                    optionA = "Kar yağdığı için yollar kapandı.",
                    optionB = "Yarışmada birinci olmak için gece gündüz çalıştı.",
                    optionC = "Bu fedakârlığı sadece senin için yaptım.",
                    optionD = "Hasta olduğu için okula gelemedi.",
                    correctKey = "B",
                    explanation = "'Birinci olmak amacıyla çalıştı' şeklinde yerine 'amacıyla' getirilebildiği için amaç-sonuç ilgisi kurmuştur."
                )
            )
        ),

        // 7. SINIF TESTİ
        KazanimTesti(
            id = "TEST_7_1",
            grade = "7. Sınıf",
            testTitle = "7. Sınıf Türkçe Kazanım Testi 1",
            topicName = "Fiillerde Anlam, Kip, Kişi ve Ek Fiil",
            outcomeCode = "T.7.3.10 & T.7.3.12",
            questions = listOf(
                KazanimTestQuestion(
                    number = 1,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerde yer alan fiillerden hangisi 'oluş fiili'dir?",
                    optionA = "Bahçedeki sarı yapraklar rüzgârla uçuştu.",
                    optionB = "Kardeşim koltuktaki yastığı yere düşürdü.",
                    optionC = "Güneşin altında unutulan elmalar kısa sürede çürüdü.",
                    optionD = "Sabahtan akşama kadar aralıksız kitap okudu.",
                    correctKey = "C",
                    explanation = "'Çürümek' eylemi öznenin iradesi dışında, zaman içinde kendiliğinden meydana gelen bir değişimi ifade ettiği için oluş fiilidir."
                ),
                KazanimTestQuestion(
                    number = 2,
                    passage = null,
                    prompt = "'Gülümsemelisin' fiilinin kipi ve şahsı aşağıdakilerden hangisidir?",
                    optionA = "Gereklilik Kipi - 2. Tekil Kişi (Sen)",
                    optionB = "İstek Kipi - 1. Çoğul Kişi (Biz)",
                    optionC = "Şart Kipi - 2. Tekil Kişi (Sen)",
                    optionD = "Emir Kipi - 3. Tekil Kişi (O)",
                    correctKey = "A",
                    explanation = "Gülümse-meli (Gereklilik kipi) -sin (2. tekil şahıs eki)."
                ),
                KazanimTestQuestion(
                    number = 3,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinde ek fiil (ek eylem) bir ismi yüklem yapma görevinde kullanılmıştır?",
                    optionA = "Dün akşam bize erkenden uğramıştı.",
                    optionB = "Köyümüz kış aylarında masal gibi güzeldi.",
                    optionC = "Soruları dikkatle çözerse başarılı olur.",
                    optionD = "Bahçedeki ağaçları her sabah sulardı.",
                    correctKey = "B",
                    explanation = "'Güzel' bir isimdir ve ek fiilin görülen geçmiş zamanı (-di) ile çekimlenerek yüklem olmuştur."
                ),
                KazanimTestQuestion(
                    number = 4,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerin hangisinde altı çizili sözcük 'durum zarfı'dır?",
                    optionA = "Misafirler salona sessizce girdiler.",
                    optionB = "Yarın sabah erkenden yola çıkacağız.",
                    optionC = "Çocuklar bahçede neşeyle yukarı koştular.",
                    optionD = "Bu konuyu onunla çok konuştuk.",
                    correctKey = "A",
                    explanation = "Fiile sorulan 'Nasıl girdiler?' sorusuna 'sessizce' yanıt verir, fiilin yapılma tarzını belirttiği için durum zarfıdır."
                )
            )
        ),

        // 8. SINIF (LGS) TESTİ
        KazanimTesti(
            id = "TEST_8_1",
            grade = "8. Sınıf",
            testTitle = "8. Sınıf LGS Deneme ve Kazanım Testi",
            topicName = "Fiilimsiler, Cümlenin Ögeleri ve Cümle Türleri",
            outcomeCode = "T.8.3.9, T.8.3.17 & T.8.3.18",
            questions = listOf(
                KazanimTestQuestion(
                    number = 1,
                    passage = "Zorluklar karşısında yılmadan (I) mücadele eden (II) bilim insanları, karanlığı aydınlatacak (III) buluşlara imza atarak insanlığa ölümsüz bir miras bırakırlar (IV).",
                    prompt = "Bu cümledeki numaralanmış sözcüklerin türleri aşağıdakilerin hangisinde sırasıyla doğru verilmiştir?",
                    optionA = "Zarf-fiil - Sıfat-fiil - Sıfat-fiil - Çekimli Fiil",
                    optionB = "İsim-fiil - Zarf-fiil - Sıfat-fiil - Çekimli Fiil",
                    optionC = "Zarf-fiil - Çekimli Fiil - Sıfat-fiil - İsim-fiil",
                    optionD = "Sıfat-fiil - Sıfat-fiil - Çekimli Fiil - Zarf-fiil",
                    correctKey = "A",
                    explanation = "I. Yılmadan (-madan: Zarf-fiil), II. Eden (-en: Sıfat-fiil), III. Aydınlatacak buluş (-acak: Sıfat-fiil), IV. Bırakırlar (Geniş zaman çekimli fiil)."
                ),
                KazanimTestQuestion(
                    number = 2,
                    passage = null,
                    prompt = "'Gözlerindeki ışıltı, salondaki herkesi derinden etkilemişti.'\nBu cümlenin ögeleri aşağıdakilerin hangisinde sırasıyla doğru verilmiştir?",
                    optionA = "Özne - Zarf Tamlayıcısı - Belirtili Nesne - Yüklem",
                    optionB = "Özne - Belirtili Nesne - Zarf Tamlayıcısı - Yüklem",
                    optionC = "Belirtisiz Nesne - Özne - Yer Tamlayıcısı - Yüklem",
                    optionD = "Özne - Yer Tamlayıcısı - Belirtili Nesne - Yüklem",
                    correctKey = "B",
                    explanation = "Yüklem: etkilemişti. Etkileyen ne? 'Gözlerindeki ışıltı' (Özne). Kimi etkilemişti? 'salondaki herkesi' (Belirtili Nesne). Nasıl etkilemişti? 'derinden' (Zarf Tamlayıcısı)."
                ),
                KazanimTestQuestion(
                    number = 3,
                    passage = null,
                    prompt = "Aşağıdaki cümlelerden hangisi 'yapısına göre tek yüklemli (basit)' bir cümledir?",
                    optionA = "Hava kararınca sokaktaki fenerler yandı.",
                    optionB = "Ders çalışan öğrencilerin dikkati dağılmadı.",
                    optionC = "Kitaplıktaki bütün romanları iki haftada okudu.",
                    optionD = "Kapıyı açtı ve misafirleri güler yüzle karşıladı.",
                    correctKey = "C",
                    explanation = "C şıkkında tek yüklem vardır ve içinde fiilimsi bulunmaz (basit cümle). A ve B fiilimsili birleşik cümle, D ise bağlı cümledir."
                ),
                KazanimTestQuestion(
                    number = 4,
                    passage = "Bir kütüphanedeki raflara A, B, C, D ve E kitapları yan yana dizilmiştir. C kitabı tam ortadadır. A ve B kitapları yan yanadır. D kitabı en sağda değildir.",
                    prompt = "Bu bilgilere göre aşağıdakilerden hangisi kesinlikle doğrudur?",
                    optionA = "C kitabı 3. sıradadır.",
                    optionB = "D kitabı en soldadır.",
                    optionC = "E kitabı en sağdadır.",
                    optionD = "A kitabı 1. sıradadır.",
                    correctKey = "A",
                    explanation = "5 kitap yan yana dizildiğinde (1, 2, 3, 4, 5), 'tam ortada' olan konum kesinlikle 3. sıradır. Dolayısıyla C kitabı kesinlikle 3. sıradadır."
                )
            )
        )
    )

    // =========================================================================
    // HTML / PDF YAZDIRMA MOTORU (A4 BOYUTUNDA MEB BİÇİMLİ DOKÜMANLAR)
    // =========================================================================

    /**
     * Konu Kazanım Testi için MEB ÖDSGM tarzı A4 fotokopiye hazır HTML üretir.
     */
    fun generateKazanimTestHtml(
        test: KazanimTesti,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        val questionsHtml = test.questions.joinToString("\n") { q ->
            """
            <div class="question-block">
                <div class="q-header"><b>Soru ${q.number}</b> <span class="badge">${test.grade}</span></div>
                ${if (!q.passage.isNullOrBlank()) "<div class=\"q-passage\">\"${q.passage}\"</div>" else ""}
                <div class="q-prompt">${q.prompt}</div>
                <div class="options-grid">
                    <div class="opt-item"><span class="opt-key">A)</span> ${q.optionA}</div>
                    <div class="opt-item"><span class="opt-key">B)</span> ${q.optionB}</div>
                    <div class="opt-item"><span class="opt-key">C)</span> ${q.optionC}</div>
                    <div class="opt-item"><span class="opt-key">D)</span> ${q.optionD}</div>
                </div>
            </div>
            """.trimIndent()
        }

        val answerKeyHtml = test.questions.joinToString(" &nbsp;|&nbsp; ") { "<b>${it.number}.</b> ${it.correctKey}" }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${test.testTitle}</title>
            <style>
                @page { size: A4 portrait; margin: 12mm 15mm 12mm 15mm; }
                body {
                    font-family: 'Times New Roman', serif;
                    font-size: 11pt;
                    color: #111;
                    line-height: 1.35;
                    margin: 0;
                    padding: 0;
                }
                .school-header {
                    text-align: center;
                    border-bottom: 2px solid #1a365d;
                    padding-bottom: 6px;
                    margin-bottom: 12px;
                }
                .school-header h1 { font-size: 13pt; margin: 0; text-transform: uppercase; color: #1a365d; }
                .school-header h2 { font-size: 11.5pt; margin: 3px 0; color: #333; }
                .school-header .sub { font-size: 9.5pt; color: #555; }
                .meta-table {
                    width: 100%;
                    border-collapse: collapse;
                    font-size: 9pt;
                    margin-bottom: 14px;
                }
                .meta-table td {
                    border: 1px solid #999;
                    padding: 4px 6px;
                }
                .two-col-layout {
                    column-count: 2;
                    column-gap: 20px;
                    column-rule: 1px dashed #ccc;
                }
                .question-block {
                    break-inside: avoid;
                    margin-bottom: 16px;
                    padding-bottom: 8px;
                    border-bottom: 1px dotted #e0e0e0;
                }
                .q-header {
                    font-size: 9.5pt;
                    color: #1a365d;
                    margin-bottom: 4px;
                }
                .badge {
                    background: #eee;
                    padding: 1px 4px;
                    font-size: 8pt;
                    border-radius: 3px;
                }
                .q-passage {
                    background: #fbfbfb;
                    border-left: 3px solid #718096;
                    padding: 5px 8px;
                    font-style: italic;
                    margin-bottom: 6px;
                    font-size: 10pt;
                }
                .q-prompt {
                    font-weight: bold;
                    margin-bottom: 6px;
                }
                .options-grid {
                    display: flex;
                    flex-direction: column;
                    gap: 3px;
                }
                .opt-item {
                    font-size: 10pt;
                }
                .opt-key {
                    font-weight: bold;
                    color: #1a365d;
                }
                .optik-box {
                    margin-top: 15px;
                    padding: 8px;
                    border: 1px solid #333;
                    background: #f7fafc;
                    font-size: 9pt;
                    break-inside: avoid;
                }
                .footer {
                    margin-top: 20px;
                    border-top: 1px solid #333;
                    padding-top: 6px;
                    font-size: 8.5pt;
                    display: flex;
                    justify-content: space-between;
                }
            </style>
        </head>
        <body>
            <div class="school-header">
                <h1>$schoolName</h1>
                <h2>TÜRKÇE DERSİ KONU KAZANIM KAVRAMA TESTİ</h2>
                <div class="sub">${test.grade} • ${test.topicName} (${test.outcomeCode})</div>
            </div>

            <table class="meta-table">
                <tr>
                    <td style="width: 50%;"><b>Adı Soyadı:</b> ...........................................................</td>
                    <td style="width: 25%;"><b>Sınıf / Şube:</b> ........ / ........</td>
                    <td style="width: 25%;"><b>Numara:</b> .................</td>
                </tr>
            </table>

            <div class="two-col-layout">
                $questionsHtml
            </div>

            <div class="optik-box">
                <div style="font-weight: bold; text-align: center; margin-bottom: 4px;">ÖĞRENCİ CEVAP FORMU</div>
                <div style="text-align: center; font-size: 9pt;">
                    1. ( A ) ( B ) ( C ) ( D ) &nbsp;&nbsp;&nbsp; 2. ( A ) ( B ) ( C ) ( D ) &nbsp;&nbsp;&nbsp; 3. ( A ) ( B ) ( C ) ( D ) &nbsp;&nbsp;&nbsp; 4. ( A ) ( B ) ( C ) ( D )
                </div>
            </div>

            <div class="footer">
                <div>Öğretmen: <b>$teacherName</b></div>
                <div>Cevap Anahtarı: $answerKeyHtml</div>
                <div>Başarılar Dileriz.</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    /**
     * Ders Materyali / Çalışma Kağıdı için A4 çıktı HTML üretir.
     */
    fun generateWorksheetHtml(
        material: GradeLessonMaterial,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        val exercisesHtml = material.exercises.joinToString("\n") { ex ->
            val itemsHtml = ex.items.joinToString("\n") { item ->
                "<li style=\"margin-bottom: 8px; line-height: 1.5;\">$item</li>"
            }
            """
            <div class="exercise-section">
                <div class="ex-title">${ex.instruction}</div>
                <ol style="margin-top: 4px; padding-left: 20px;">
                    $itemsHtml
                </ol>
            </div>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${material.title}</title>
            <style>
                @page { size: A4 portrait; margin: 15mm; }
                body {
                    font-family: 'Times New Roman', serif;
                    font-size: 11pt;
                    color: #111;
                    line-height: 1.4;
                    margin: 0;
                    padding: 0;
                }
                .header {
                    text-align: center;
                    border-bottom: 2px solid #2b6cb0;
                    padding-bottom: 8px;
                    margin-bottom: 12px;
                }
                .header h1 { font-size: 13pt; margin: 0; text-transform: uppercase; }
                .header h2 { font-size: 12pt; margin: 4px 0; color: #2b6cb0; }
                .header .sub { font-size: 9.5pt; color: #4a5568; }
                .info-box {
                    background: #ebf8ff;
                    border-left: 4px solid #3182ce;
                    padding: 8px 12px;
                    margin-bottom: 14px;
                    font-size: 9.5pt;
                }
                .exercise-section {
                    margin-bottom: 18px;
                }
                .ex-title {
                    font-weight: bold;
                    color: #2c5282;
                    border-bottom: 1px solid #cbd5e0;
                    padding-bottom: 3px;
                    margin-bottom: 8px;
                    font-size: 10.5pt;
                }
                .notes-lines {
                    border: 1px dashed #718096;
                    height: 90px;
                    margin-top: 15px;
                    padding: 6px;
                    font-size: 9pt;
                    color: #a0aec0;
                }
                .footer {
                    margin-top: 25px;
                    border-top: 1px solid #4a5568;
                    padding-top: 6px;
                    font-size: 9pt;
                    display: flex;
                    justify-content: space-between;
                }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>${material.title}</h2>
                <div class="sub">${material.grade} • Kategori: ${material.category} • ${material.objective}</div>
            </div>

            <table style="width: 100%; border-collapse: collapse; font-size: 9pt; margin-bottom: 12px;">
                <tr>
                    <td style="border: 1px solid #999; padding: 4px;"><b>Öğrenci Adı Soyadı:</b> .................................................</td>
                    <td style="border: 1px solid #999; padding: 4px;"><b>Sınıf/No:</b> ..... / .....</td>
                    <td style="border: 1px solid #999; padding: 4px;"><b>Tarih:</b> ...../...../202...</td>
                </tr>
            </table>

            <div class="info-box">
                <b>Öğretmen Yönergesi:</b> ${material.instructions}
                <div style="margin-top: 4px; font-style: italic;">${material.summary}</div>
            </div>

            $exercisesHtml

            <div class="notes-lines">
                Öğrenci Not ve Çözüm Alanı / Öz Değerlendirme:
            </div>

            <div class="footer">
                <div>Hazırlayan: <b>$teacherName</b></div>
                <div>Türkçe Dersi Etkinlik ve Pekiştirme Föyü</div>
                <div>Başarılar Dileriz.</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }

    /**
     * Ders Kitabı Temaları Rehberi için A4 çıktı üretir.
     */
    fun generateTextbookThemeGuideHtml(
        guide: GradeTextbookGuide,
        schoolName: String = "T.C. MİLLÎ EĞİTİM BAKANLIĞI",
        teacherName: String = "Türkçe Öğretmeni"
    ): String {
        val themesHtml = guide.themes.joinToString("\n") { theme ->
            val textsHtml = theme.texts.joinToString("") { t ->
                "<tr><td><b>${t.title}</b></td><td>${t.author}</td><td>${t.textType}</td></tr>"
            }
            """
            <div class="theme-card">
                <div class="theme-title">${theme.themeNumber}. TEMA: ${theme.themeName}</div>
                <div class="theme-meta"><b>Anahtar Kavramlar:</b> ${theme.keyConcepts.joinToString(", ")}</div>
                <div class="theme-meta"><b>Dilbilgisi ve İmla Odağı:</b> ${theme.grammarFocus}</div>
                <div class="theme-meta"><b>Dinleme/İzleme Notu:</b> ${theme.listeningSummary}</div>
                
                <table class="theme-table">
                    <thead>
                        <tr><th>Metin Başlığı</th><th>Yazar / Kaynak</th><th>Metin Türü</th></tr>
                    </thead>
                    <tbody>
                        $textsHtml
                    </tbody>
                </table>
                <div style="font-size: 9pt; color: #4a5568; margin-top: 4px;"><b>Değerlendirme:</b> ${theme.assessmentNotes}</div>
            </div>
            """.trimIndent()
        }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${guide.grade} Ders Kitabı Rehberi</title>
            <style>
                @page { size: A4 portrait; margin: 12mm; }
                body { font-family: 'Times New Roman', serif; font-size: 10pt; color: #222; line-height: 1.35; }
                .header { text-align: center; border-bottom: 2px solid #742a2a; padding-bottom: 6px; margin-bottom: 12px; }
                .header h1 { font-size: 13pt; margin: 0; color: #742a2a; }
                .header h2 { font-size: 11pt; margin: 3px 0; }
                .theme-card { margin-bottom: 14px; border: 1px solid #cbd5e0; padding: 8px 10px; border-radius: 4px; break-inside: avoid; }
                .theme-title { font-size: 11pt; font-weight: bold; color: #742a2a; border-bottom: 1px solid #e2e8f0; padding-bottom: 3px; }
                .theme-meta { font-size: 9pt; margin: 3px 0; }
                .theme-table { width: 100%; border-collapse: collapse; font-size: 8.5pt; margin-top: 4px; }
                .theme-table th, .theme-table td { border: 1px solid #cbd5e0; padding: 3px 6px; }
                .theme-table th { background: #f7fafc; text-align: left; }
                .footer { margin-top: 15px; border-top: 1px solid #999; padding-top: 4px; font-size: 8.5pt; display: flex; justify-content: space-between; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>$schoolName</h1>
                <h2>${guide.grade} TÜRKÇE DERS KİTABI ÜNİTE VE TEMA KILAVUZU</h2>
                <div style="font-size: 9pt; color: #555;">Yayın: ${guide.publisher} • Yıllık Planlama ve Zümre Kaynağı</div>
            </div>
            $themesHtml
            <div class="footer">
                <div>Zümre Başkanı / Öğretmen: <b>$teacherName</b></div>
                <div>MEB Müfredatı ve TTKB Kararlarına Uygundur</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
