package com.example.data.curriculum

data class SpecialDayPoem(
    val title: String,
    val poet: String,
    val verses: String
)

data class SpecialDayItem(
    val id: String,
    val title: String,
    val dateInfo: String,
    val category: String, // Milli Bayram, Anma Günü, Kültür & Edebiyat, Eğitim
    val importance: String,
    val boardText: String, // Okul Panosu Metni
    val speechText: String, // Tören Konuşma Metni
    val poems: List<SpecialDayPoem>,
    val quotes: List<String>,
    val activitySuggestions: List<String>
)

object SpecialDaysRepository {

    val items: List<SpecialDayItem> = listOf(
        // 1. TÜRK DİL BAYRAMI
        SpecialDayItem(
            id = "dil_bayrami",
            title = "Türk Dil Bayramı",
            dateInfo = "26 Eylül",
            category = "Kültür & Edebiyat",
            importance = "Gazi Mustafa Kemal Atatürk'ün öncülüğünde 26 Eylül 1932'de toplanan I. Türk Dili Kurultayı'nın açılış günü esas alınarak her yıl kutlanan, Türkçemizin zenginliğini ve bağımsızlığını vurgulayan en önemli kültür bayramımızdır.",
            boardText = """
                TÜRKÇEMİZ, SES BAYRAĞIMIZDIR!
                
                Dil, bir milletin hafızası, kültürü, geçmişi ve geleceğidir. Bağımsızlığın en sağlam kalesi dildir. 
                26 Eylül 1932 yılında Gazi Mustafa Kemal Atatürk'ün önderliğinde toplanan I. Türk Dili Kurultayı, dilimizin yabancı boyunduruklardan kurtarılması ve özleştirilmesi yolunda atılan tarihi bir adımdır.
                
                Türkçe, dünyanın en köklü ve en zengin dillerinden biridir. Karamanoğlu Mehmet Bey'in 1277'deki fermanından, Yunus Emre'nin duru dizelerine; Dadaloğlu'ndan Yahya Kemal'e, Fazıl Hüsnü Dağlarca'nın "Türkçem, benim ses bayrağım" mısrasına uzanan bu kutlu miras hepimizin ortak hazinesidir.
                
                Türkçemizi yabancı kelimelerin istilasından korumak, doğru, güzel ve etkili kullanmak her Türk gencinin ve öğretmeninin asli vazifesidir.
            """.trimIndent(),
            speechText = """
                Değerli Meslektaşlarım, Sevgili Öğrenciler,
                
                Bugün, kimliğimizin ve bağımsızlığımızın en büyük teminatı olan Türkçemizin bayramını, 26 Eylül Dil Bayramı'nı büyük bir gururla kutluyoruz.
                
                Büyük Önder Mustafa Kemal Atatürk; "Türk dili, Türk milleti için kutsal bir hazinedir. Çünkü Türk milleti geçirdiği nihayetsiz felaketler içinde ahlakının, ananelerinin, hatıralarının, menfaatlerinin, velhasıl bugün kendi milliyetini yapan her şeyin dili sayesinde muhafaza olduğunu görüyor." diyerek dilin millet hayatındaki hayati rolünü en güzel şekilde ifade etmiştir.
                
                Sevgili gençler, dilimiz zenginleştikçe düşünce dünyamız zenginleşir. Okuduğunuz her güzel kitap, kurduğunuz her duru cümle dil bayrağımızın daha gür dalgalanmasını sağlar. Türkçemizi sevelim, koruyalım ve daima yüceltelim. Dil Bayramımız kutlu olsun!
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Türkçem",
                    poet = "Fazıl Hüsnü Dağlarca",
                    verses = """
                        Seslenir seni bana "ova"m, "dağ"ım,
                        Nereye gitsem sesin arkamdan gelir.
                        Türkçem, benim ses bayrağım!
                        
                        Güzeller güzeli, canımdan öte,
                        Seninle söylerim, seninle gülerim.
                        Karanlığı boğan ulu meşale,
                        Seninle parıldar geleceğim!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Türk dili, dillerin en zenginlerindendir; yeter ki bu dil şuurla işlensin. - Mustafa Kemal Atatürk",
                "Türkçem, benim ses bayrağım! - Fazıl Hüsnü Dağlarca",
                "Bugünden sonra divanda, dergâhta, bargâhta, mecliste ve meydanda Türkçe'den başka dil kullanılmaya! - Karamanoğlu Mehmet Bey"
            ),
            activitySuggestions = listOf(
                "Sınıfta 'Sözcüklerin Doğrusu' sergisi ve panosu hazırlama.",
                "Öğrencilerle 'Dilimizin Zenginlikleri' konulu atasözü ve deyim canlandırma tiyatrosu.",
                "Tabelalardaki yabancı sözcüklerin Türkçe karşılıklarını bulma projesi."
            )
        ),

        // 2. 29 EKİM CUMHURİYET BAYRAMI
        SpecialDayItem(
            id = "cumhuriyet_bayrami",
            title = "29 Ekim Cumhuriyet Bayramı",
            dateInfo = "29 Ekim",
            category = "Milli Bayram",
            importance = "Türk milletinin Gazi Mustafa Kemal Atatürk önderliğinde başlattığı İstiklal Harbi'nin taçlandığı, egemenliğin kayıtsız şartsız millete verildiği en büyük milli bayramımızdır.",
            boardText = """
                EGEMENLİK KAYITSIZ ŞARTSIZ MİLLETİNDİR!
                
                29 Ekim 1923; küllerinden doğan bir milletin, kendi kaderini kendi eline aldığı kutlu gündür.
                Cumhuriyet, sadece bir yönetim biçimi değil; aynı zamanda erdemin, çağdaşlaşmanın, bilimin, kadın haklarının ve hürriyetin adıdır.
                
                Gazi Mustafa Kemal Atatürk'ün "Ey yükselen yeni nesil! İstikbal sizsiniz. Cumhuriyeti biz kurduk, onu yükseltecek ve yaşatacak sizlersiniz." sözü, omuzlarımıza yüklenen en şerefli emanettir.
                
                Cumhuriyetimizin kurucusu Gazi Mustafa Kemal Atatürk ve silah arkadaşlarını, aziz şehitlerimizi saygı, minnet ve rahmetle anıyoruz.
            """.trimIndent(),
            speechText = """
                Sayın Müdürüm, Kıymetli Öğretmen Arkadaşlarım ve Sevgili Öğrenciler,
                
                Bugün milletimizin tarih sahnesindeki en muazzam dirilişlerinden biri olan Cumhuriyetimizin kuruluşunun yıl dönümünü kutlamanın coşkusunu yaşıyoruz.
                
                Cumhuriyet, bağımsızlık tutkusuyla yanan bir milletin her türlü imkansızlığa meydan okuyuşudur. Cumhuriyet, Anadolu'nun bozkırında yeşeren umut, köylünün efendileşmesi, çocukların aydınlık geleceğidir.
                
                Sevgili öğrenciler; sizlere düşen görev, aklın ve bilimin rehberliğinde çok çalışmak, Cumhuriyetin temel ilkelerine ve vatanımıza sahip çıkmaktır. 29 Ekim Cumhuriyet Bayramımız kutlu olsun!
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Akdeniz'e Doğru",
                    poet = "Cahit Sıtkı Tarancı",
                    verses = """
                        Gözlerimde parıltısı bakır bir tasın,
                        Kulaklarım komutlarla çınlasın.
                        Atatürk’üm sen başımızdasın,
                        Cumhuriyet güneşi sönmeyecek!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Cumhuriyet, fikren, ilmen, fennen ve bedenen kuvvetli ve yüksek seciyeli muhafızlar ister. - Mustafa Kemal Atatürk",
                "Benim naçiz vücudum elbet bir gün toprak olacaktır; fakat Türkiye Cumhuriyeti ilelebet payidar kalacaktır. - Mustafa Kemal Atatürk"
            ),
            activitySuggestions = listOf(
                "'Cumhuriyet ve Hürriyet' temalı şiir ve kompozisyon yarışması.",
                "Cumhuriyet Oratoryosu hazırlığı ve koro dinletisi.",
                "Tarihi fotoğraflarla Cumhuriyet Sergisi açılışı."
            )
        ),

        // 3. 10 KASIM ATATÜRK'Ü ANMA GÜNÜ
        SpecialDayItem(
            id = "ataturku_anma",
            title = "10 Kasım Atatürk'ü Anma Günü ve Atatürk Haftası",
            dateInfo = "10 - 16 Kasım",
            category = "Anma Günü",
            importance = "Türkiye Cumhuriyeti'nin kurucusu, büyük komutan ve eşsiz devlet adamı Gazi Mustafa Kemal Atatürk'ün ebediyete irtihal ettiği gün ve fikirlerinin yaşatıldığı anma haftasıdır.",
            boardText = """
                BÜYÜK ÖNDER ATATÜRK'Ü SAYGI VE ÖZLEMLE ANIYORUZ
                
                10 Kasım, bir matem günü değil; Atatürk'ün akıl ve bilim mirasını, vatan sevgisini ve barış vizyonunu yeniden idrak etme günüdür.
                
                O, milletine duyduğu sonsuz güvenle bağımsızlık meşalesini yakmış, harf inkılabıyla milletini cehaletten kurtarmış ve eğitime en büyük değeri vermiştir.
                
                "Beni görmek demek, mutlaka yüzümü görmek demek değildir. Benim fikirlerimi, benim duygularımı anlıyorsanız ve hissediyorsanız bu kafidir." diyen Büyük Önder, fikirleriyle kalbimizde sonsuza dek yaşayacaktır.
            """.trimIndent(),
            speechText = """
                Kıymetli Öğretmenlerim ve Sevgili Öğrenciler,
                
                Bugün saat 09.05'te çalan sirenler, sadece bir ayrılığı değil; bir milletin ebedi Başöğretmenine olan tükenmez şükranını haykırmaktadır.
                
                Atatürk; askeri bir deha, büyük bir devrimci ve milletinin sevdalısı bir liderdi. Ancak onun en çok gurur duyduğu unvan Başöğretmenlikti. Eğitimi bir milletin can damarı sayan Atatürk, gençlere güvenmiş ve Cumhuriyeti onlara emanet etmiştir.
                
                Bizler de onun açtığı aydınlık yolda, gösterdiği çağdaş hedeflere durmaksızın yürüyeceğimize ant içeriz. Ruhu şad olsun.
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Mustafa Kemal'i Düşünüyorum",
                    poet = "Ümit Yaşar Oğuzcan",
                    verses = """
                        Mustafa Kemal'i düşünüyorum;
                        Gözleri bir deniz kadar mavi,
                        Saçları altın sarısı başaklar gibi.
                        Mustafa Kemal'i düşünüyorum;
                        Karanlıkları delen bir şimşek,
                        Geleceğe uzanan aydınlık bir el!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Beni görmek demek mutlaka yüzümü görmek değildir. Benim fikirlerimi, benim duygularımı anlıyorsanız ve hissediyorsanız bu kafidir. - Mustafa Kemal Atatürk",
                "Hayatta en hakiki mürşit ilimdir, fendir. - Mustafa Kemal Atatürk"
            ),
            activitySuggestions = listOf(
                "Atatürk'ün sevdiği şarkılar ve şiirler dinletisi.",
                "'Atatürk ve Kitap Sevgisi' temalı okul panosu ve köşe yazısı.",
                "10 Kasım Özel Anma Gazetesi veya bülteni çıkarma."
            )
        ),

        // 4. 24 KASIM ÖĞRETMENLER GÜNÜ
        SpecialDayItem(
            id = "ogretmenler_gunu",
            title = "24 Kasım Öğretmenler Günü",
            dateInfo = "24 Kasım",
            category = "Eğitim",
            importance = "Gazi Mustafa Kemal Atatürk'ün 'Millet Mektepleri Başöğretmenliği' unvanını kabul ettiği 24 Kasım 1928 gününün anısına, 1981 yılından bu yana kutlanan fedakâr öğretmenlerimizin günüdür.",
            boardText = """
                GELECEĞİN MİMARLARI: ÖĞRETMENLERİMİZ
                
                Öğretmenlik, insan yetiştirme sanatıdır. Karanlığı aydınlatan bir meşale gibi, etrafını aydınlatırken kendini tüketen kutsi bir meslektir.
                
                Gazi Mustafa Kemal Atatürk'ün veciz ifadesiyle: "Öğretmenler! Yeni nesil sizin eseriniz olacaktır. Eserin kıymeti sizin maharetiniz ve fedakârlığınız derecesiyle orantılı bulunacaktır."
                
                Şehit öğretmenlerimizi rahmetle, tüm öğretmenlerimizi minnet ve hürmetle selamlıyoruz.
            """.trimIndent(),
            speechText = """
                Saygıdeğer Öğretmen Arkadaşlarım ve Sevgili Öğrenciler,
                
                Bugün, hayatımızın her safhasında bize rehberlik eden, bir harf öğretenin kırk yıl kölesi olunduğu kadim medeniyetimizde baş tacı edilen öğretmenlerimizin şeref günüdür.
                
                Bir milletin istikbali, sınıflarda tebeşir tozu yutan öğretmenlerinin gayretine bağlıdır. Bizler sınıflarımızda sadece ders anlatmıyoruz; sevgi ekiyoruz, dürüstlük aşılıyoruz, vatan sevgisini yoğuruyoruz.
                
                Başta Başöğretmenimiz Mustafa Kemal Atatürk ve şehit öğretmenlerimiz olmak üzere, maarif ordumuzun tüm neferlerinin Öğretmenler Günü'nü yürekten kutlarım.
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Ben Bir Öğretmenim",
                    poet = "Ceyhun Atuf Kansu",
                    verses = """
                        Ben bir öğretmenim Anadolu'da,
                        Çiçekler açtırırım çorak bozkırda.
                        Gözleri parıldayan bir çocuk görsem,
                        Dünyalar benim olur o an sınıfta!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Öğretmenler! Yeni nesil sizin eseriniz olacaktır. - Mustafa Kemal Atatürk",
                "Bana bir harf öğretenin kırk yıl kölesi olurum. - Hz. Ali"
            ),
            activitySuggestions = listOf(
                "Öğretmene vefa mektubu yazma etkinliği.",
                "Emekli öğretmenleri okula davet ederek söyleşi düzenleme.",
                "'Öğretmenim' konulu resim ve şiir sergisi."
            )
        ),

        // 5. 12 MART İSTİKLAL MARŞI'NIN KABULÜ
        SpecialDayItem(
            id = "istiklal_marsi",
            title = "12 Mart İstiklal Marşı'nın Kabulü ve Mehmet Akif Ersoy'u Anma Günü",
            dateInfo = "12 Mart",
            category = "Milli & Edebi",
            importance = "Kurtuluş Savaşı'nın en çetin günlerinde milli mücadele ruhunu şaha kaldıran İstiklal Marşımızın TBMM tarafından milli marş olarak kabul edilişinin ve vatan şairi Mehmet Akif Ersoy'un anıldığı gündür.",
            boardText = """
                KORKMA! SÖNMEZ BU ŞAFAKLARDA YÜZEN AL SANCAK!
                
                12 Mart 1921 tarihinde TBMM kürsüsünde ayakta alkışlarla kabul edilen İstiklal Marşı, bir milletin bağımsızlık andı ve milli mutabakat metnidir.
                
                Milli Şairimiz Mehmet Akif Ersoy, milletine armağan ettiği bu abidevi eseri Safahat adlı kitabına dahi almamış, "O benim değil, kahraman milletimindir." demiştir.
                
                "Allah bu millete bir daha İstiklal Marşı yazdırmasın!" duası, hürriyetimizin ne denli büyük bedellerle kazanıldığının ebedi ifadesidir.
            """.trimIndent(),
            speechText = """
                Saygıdeğer Konuklar, Sevgili Öğrenciler,
                
                Bugün İstiklal Marşımızın kabulünün yıl dönümünde, vatan şairimiz Mehmet Akif Ersoy'u rahmetle anmak için toplanmış bulunuyoruz.
                
                İstiklal Marşı, sadece bir şiir değil; toprağa düşen binlerce şehidin vasiyeti, bayrağımızın göklerdeki hür yankısıdır. Akif, imanı ve ahlakıyla milletinin tercümanı olmuş bir erdem timsalidir.
                
                Milletimizin her ferdi, bu marştaki her mısrayı kalbine kazımalı ve hürriyet aşkını daima diri tutmalıdır. Akif'i ve tüm İstiklal kahramanlarımızı rahmetle yad ediyoruz.
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Çanakkale Şehitlerine'den",
                    poet = "Mehmet Akif Ersoy",
                    verses = """
                        Vurulup tertemiz alnından, uzanmış yatıyor,
                        Bir hilâl uğruna, yâ Rab, ne güneşler batıyor!
                        Ey, bu topraklar için toprağa düşmüş asker!
                        Gökten ecdâd inerek öpse o pâk alnı değer.
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Allah bu millete bir daha İstiklal Marşı yazdırmasın! - Mehmet Akif Ersoy",
                "Doğacaktır sana va'dettiği günler Hakk'ın, kimbilir belki yarın, belki yarından da yakın. - Mehmet Akif Ersoy"
            ),
            activitySuggestions = listOf(
                "Okul çapında İstiklal Marşı'nı Güzel Okuma Yarışması.",
                "Mehmet Akif'in hayatı ve Safahat temalı kitap inceleme panosu.",
                "İstiklal Marşı'nın yazılış sürecini anlatan drama gösterisi."
            )
        ),

        // 6. 18 MART ÇANAKKALE ZAFERİ
        SpecialDayItem(
            id = "canakkale_zaferi",
            title = "18 Mart Şehitleri Anma Günü ve Çanakkale Zaferi",
            dateInfo = "18 Mart",
            category = "Milli & Tarihi",
            importance = "Tarihin seyrini değiştiren, 'Çanakkale Geçilmez!' destanının yazıldığı ve Anafartalar Kahramanı Mustafa Kemal'in tarih sahnesine çıktığı muhteşem zaferimizdir.",
            boardText = """
                ÇANAKKALE GEÇİLMEZ!
                
                18 Mart 1915; Türk milletinin vatan sevgisi, imanı ve sarsılmaz azmiyle dönemin en modern ordularına diz çöktürdüğü kutlu bir zaferdir.
                
                Seyit Onbaşı'nın sırtladığı 276 kiloluk mermide, 57. Alay'ın son neferine kadar şehadete yürüyüşünde ve Mustafa Kemal'in "Ben size taarruzu değil, ölmeyi emrediyorum!" emrinde vatanın mukaddesatı yatar.
                
                Çanakkale, Kurtuluş Savaşımızın önsözü, milli birlik ve kardeşliğimizin ebedi abidesidir.
            """.trimIndent(),
            speechText = """
                Kıymetli Öğretmenlerim ve Sevgili Öğrenciler,
                
                Bugün burada, 18 Mart 1915'te dünya tarihine altın harflerle 'Çanakkale Geçilmez' yazdıran aziz ecdadımızı anmak üzere bir aradayız.
                
                Çanakkale, bir neslin vatan uğruna canını feda ettiği yerdir. Liseli tıbbiyelilerin, kınalı kuzuların, memleketin her köşesinden gelen vatan evlatlarının omuz omuza toprağa düştüğü destandır.
                
                Bizler bugün bu topraklarda hür ve başı dik yaşıyorsak, bunu Çanakkale'de kefensiz yatan yüz binlerce şehidimize borçluyuz. Onların emanetine layık olmak, çok çalışmakla mümkündür. Ruhları şad olsun!
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Dur Yolcu",
                    poet = "Necmettin Halil Onan",
                    verses = """
                        Dur yolcu! Bilmeden gelip bastığın
                        Bu toprak, bir devrin battığı yerdir.
                        Eğil de kulak ver, bu sessiz yığın
                        Bir vatan kalbinin attığı yerdir!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Ben size taarruzu emretmiyorum, ölmeyi emrediyorum! - Mustafa Kemal Atatürk",
                "Çanakkale Zaferi, Türk askerinin ruh kudretini gösteren şayanı hayret ve tebrik bir misaldir. - Mustafa Kemal Atatürk"
            ),
            activitySuggestions = listOf(
                "Çanakkale Şehitleri Anma Oratoryosu.",
                "Öğle yemeğinde temsili hoşaf ve kuru ekmek ikramı ile empati etkinliği.",
                "Çanakkale mektupları ve türküleri dinletisi."
            )
        ),

        // 7. KÜTÜPHANELER HAFTASI
        SpecialDayItem(
            id = "kutuphaneler_haftasi",
            title = "Kütüphaneler Haftası",
            dateInfo = "Mart Son Haftası",
            category = "Kültür & Edebiyat",
            importance = "Kitap okuma alışkanlığını geliştirmek, kütüphanelerin toplumdaki önemini vurgulamak ve öğrencileri kitapların büyülü dünyasıyla buluşturmak amacıyla kutlanır.",
            boardText = """
                KİTAPLAR, DÜNYAYA AÇILAN PENCERELERDİR!
                
                Kütüphaneler, insanlığın ortak aklının ve birikiminin muhafaza edildiği mabetlerdir. Bir kütüphaneye girmek, yüzyıllar öncesinin bilgeleriyle aynı masada oturup sohbet etmektir.
                
                "Kitapsız yaşamak; kör, sağır ve dilsiz yaşamaktır." diyen Seneca, kitabın insan ruhundaki yerini ne kadar güzel özetler.
                
                Kitap okuyan fertler düşünen, sorgulayan, empati yeteneği gelişmiş ve topluma yön veren bireyler olurlar. Her gün en az 30 sayfa kitap okuyarak hayatınıza değer katın.
            """.trimIndent(),
            speechText = """
                Değerli Öğretmenlerim ve Sevgili Kitap Dostları,
                
                Bugün kapılarını araladığımız Kütüphaneler Haftası, zihinlerimizin bayramıdır.
                
                Kitap, insanın en sadık dostudur. Sizi hiç tanımadığınız diyarlara götürür, hiç karşılaşmadığınız insanlarla tanıştırır, kelime hazinenizi zenginleştirir.
                
                Unutmayınız ki medeniyetler kütüphanelerin omuzlarında yükselir. Okulumuzun kütüphanesini dolduralım, raflardaki her kitaba bir dost sıcaklığıyla yaklaşalım. Kütüphaneler Haftamız kutlu olsun!
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "Kitaplarım",
                    poet = "Rıfat Ilgaz",
                    verses = """
                        Açarım sayfalarını birer birer,
                        İçinden mis kokulu baharlar güler.
                        Bana bilgiyi, erdemi veren,
                        En sadık dostumdur benim kitaplar!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Kitapsız büyüyen çocuk, susuz yetişen ağaca benzer. - Çin Atasözü",
                "Okumak, iki elin arasında tutulan bir dünyadır. - Cemil Meriç"
            ),
            activitySuggestions = listOf(
                "Sınıfta 'Sessiz Okuma Saati' (30 dakika toplu kitap okuma).",
                "İlçe / Şehir Halk Kütüphanesine gezi düzenleme.",
                "En çok kitap okuyan 'Kitap Kurdu' öğrencilerine ödül töreni."
            )
        ),

        // 8. 23 NİSAN ULUSAL EGEMENLİK VE ÇOCUK BAYRAMI
        SpecialDayItem(
            id = "cocuk_bayrami",
            title = "23 Nisan Ulusal Egemenlik ve Çocuk Bayramı",
            dateInfo = "23 Nisan",
            category = "Milli Bayram",
            importance = "TBMM'nin açıldığı, milli iradenin tecelli ettiği ve dünyada çocuklara armağan edilen tek evrensel bayramdır.",
            boardText = """
                KÜÇÜK HANIMLAR, KÜÇÜK BEYLER!
                
                23 Nisan 1920'de Ankara'da açılan Türkiye Büyük Millet Meclisi, Türk milletinin esarete asla boyun eğmeyeceğinin tüm dünyaya ilanıdır.
                
                Gazi Mustafa Kemal Atatürk, bu kutlu günü yarının büyükleri olan dünya çocuklarına bayram olarak hediye ederek eşsiz vizyonunu ortaya koymuştur.
                
                "Sizler hepiniz geleceğin bir gülü, yıldızı ve ikbal ışığısınız. Memleketi asıl ışığa boğacak olan sizsiniz." - Mustafa Kemal Atatürk
            """.trimIndent(),
            speechText = """
                Kıymetli Misafirler, Sevgili Çocuklar,
                
                Bugün tüm dünyada eşi benzeri olmayan muhteşem bir bayramı, 23 Nisan Ulusal Egemenlik ve Çocuk Bayramı'nı kutlamanın neşesi içindeyiz.
                
                Bu bayram; barışın, sevginin, kardeşliğin ve millet iradesinin sembolüdür. Sevgili çocuklar, sizler Atatürk'ün en kıymetli hazinesisiniz. Gözlerinizdeki ışıltı vatanımızın aydınlık geleceğidir. Bayramınız kutlu olsun!
            """.trimIndent(),
            poems = listOf(
                SpecialDayPoem(
                    title = "23 Nisan",
                    poet = "İsmail Hakkı Sunat",
                    verses = """
                        Bugün 23 Nisan,
                        Toplandı bütün cihan.
                        Sevinin küçükler,
                        Övünün büyükler,
                        23 Nisan kutlu olsun!
                    """.trimIndent()
                )
            ),
            quotes = listOf(
                "Egemenlik kayıtsız şartsız milletindir. - Mustafa Kemal Atatürk",
                "Çocuklar geleceğimizin güvencesi, yaşama sevincimizdir. - Mustafa Kemal Atatürk"
            ),
            activitySuggestions = listOf(
                "Geleneksel çocuk oyunları şenliği (Mendil kapmaca, seksek, halat çekme).",
                "Öğrencilerin bir günlüğüne öğretmen ve müdür koltuğuna oturması.",
                "Şiir ve koro gösterisi."
            )
        )
    )

    fun getItemById(id: String): SpecialDayItem? = items.find { it.id == id }

    /**
     * Generates an official, beautifully styled printable A4 HTML document
     * for school bulletin boards (Okul Panosu) or ceremony speeches (Tören Dosyası).
     */
    fun generatePrintableHtml(item: SpecialDayItem, docType: String = "pano"): String {
        val typeTitle = if (docType == "pano") "OKUL PANOSU DOKÜMANI" else "TÖREN VE KUTLAMA PROGRAMI"
        val poemHtml = item.poems.joinToString("<br><br>") { poem ->
            """
            <div class="poem-card">
                <div class="poem-title"><b>${poem.title}</b></div>
                <div class="poem-poet"><i>${poem.poet}</i></div>
                <div class="poem-body">${poem.verses.replace("\n", "<br>")}</div>
            </div>
            """.trimIndent()
        }

        val quotesHtml = item.quotes.joinToString("<br>") { "• <i>$it</i>" }

        val activitiesHtml = item.activitySuggestions.joinToString("") { "<li>$it</li>" }

        return """
        <!DOCTYPE html>
        <html lang="tr">
        <head>
            <meta charset="UTF-8">
            <title>${item.title} - $typeTitle</title>
            <style>
                @page { size: A4; margin: 15mm; }
                body {
                    font-family: 'Times New Roman', Times, serif, Arial;
                    color: #1a1a1a;
                    line-height: 1.5;
                    font-size: 13pt;
                    background: #fff;
                    margin: 0;
                    padding: 0;
                }
                .header {
                    text-align: center;
                    border-bottom: 2px solid #8B263E;
                    padding-bottom: 10px;
                    margin-bottom: 20px;
                }
                .school-title {
                    font-size: 12pt;
                    font-weight: bold;
                    letter-spacing: 1px;
                    color: #555;
                }
                .doc-type {
                    font-size: 11pt;
                    color: #8B263E;
                    font-weight: bold;
                    margin-top: 4px;
                }
                .main-title {
                    font-size: 20pt;
                    font-weight: bold;
                    color: #1E3A5F;
                    margin-top: 8px;
                    text-transform: uppercase;
                }
                .date-badge {
                    display: inline-block;
                    background: #f0f4f8;
                    border: 1px solid #1E3A5F;
                    color: #1E3A5F;
                    padding: 3px 12px;
                    border-radius: 4px;
                    font-weight: bold;
                    font-size: 11pt;
                    margin-top: 6px;
                }
                .section {
                    margin-bottom: 20px;
                }
                .section-title {
                    font-size: 13pt;
                    font-weight: bold;
                    color: #8B263E;
                    border-left: 4px solid #8B263E;
                    padding-left: 8px;
                    margin-bottom: 8px;
                    text-transform: uppercase;
                }
                .content-box {
                    background: #fafafa;
                    border: 1px solid #e0e0e0;
                    border-radius: 6px;
                    padding: 14px;
                    text-align: justify;
                }
                .poem-card {
                    background: #fdfbf7;
                    border: 1px dashed #B57D1E;
                    padding: 12px 18px;
                    border-radius: 6px;
                    margin-top: 10px;
                }
                .poem-title {
                    font-size: 12pt;
                    color: #B57D1E;
                }
                .poem-poet {
                    font-size: 10pt;
                    color: #666;
                    margin-bottom: 8px;
                }
                .poem-body {
                    font-style: italic;
                    line-height: 1.6;
                }
                .footer {
                    margin-top: 30px;
                    border-top: 1px solid #ddd;
                    padding-top: 10px;
                    font-size: 9pt;
                    color: #777;
                    display: flex;
                    justify-content: space-between;
                }
                ul { margin-top: 4px; padding-left: 20px; }
                li { margin-bottom: 4px; }
            </style>
        </head>
        <body>
            <div class="header">
                <div class="school-title">T.C. MİLLÎ EĞİTİM BAKANLIĞI</div>
                <div class="doc-type">$typeTitle</div>
                <div class="main-title">${item.title}</div>
                <div class="date-badge">${item.dateInfo} - ${item.category}</div>
            </div>

            <div class="section">
                <div class="section-title">Günün Anlam ve Önemi</div>
                <div class="content-box">
                    ${item.importance}
                </div>
            </div>

            <div class="section">
                <div class="section-title">${if (docType == "pano") "Pano Metni" else "Tören Konuşma Metni"}</div>
                <div class="content-box">
                    ${if (docType == "pano") item.boardText.replace("\n", "<br>") else item.speechText.replace("\n", "<br>")}
                </div>
            </div>

            <div class="section">
                <div class="section-title">Günün Şiiri ve Dizeleri</div>
                $poemHtml
            </div>

            <div class="section">
                <div class="section-title">Özlü Sözler</div>
                <div class="content-box">
                    $quotesHtml
                </div>
            </div>

            <div class="section">
                <div class="section-title">Ders İçi ve Okul Etkinlik Önerileri</div>
                <ul>
                    $activitiesHtml
                </ul>
            </div>

            <div class="footer">
                <div>Türkçe Dersi Zümresi • Türkçe Asistanı Eğitim Portalı</div>
                <div>Hazırlanma Tarihi: 2026-10-27</div>
            </div>
        </body>
        </html>
        """.trimIndent()
    }
}
