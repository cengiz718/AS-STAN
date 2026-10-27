package com.example.data.curriculum

import com.example.data.model.GrammarCategory
import com.example.data.model.GrammarExample
import com.example.data.model.GrammarRule
import com.example.data.model.GrammarTopic

object GrammarRepository {

    val topics: List<GrammarTopic> = listOf(
        // 1. İSİMLER (ADLAR)
        GrammarTopic(
            id = "isimler",
            category = GrammarCategory.ISIMLER,
            title = "İsimler (Adlar)",
            summary = "Canlı ve cansız varlıkları, duygu ve kavramları karşılayan sözcüklerdir.",
            detailedContent = """
                İsimler, varlıkların veya kavramların dildeki karşılıklarıdır. Cümlede özne, nesne, tümleç ve ek fiil alarak yüklem olabilirler.
                
                1. Varlıklarına Göre İsimler:
                • Özel İsim: Tek olan, benzeri bulunmayan varlıkları karşılar (Ahmet, Türkiye, Ankara, Türk Dili). Büyük harfle başlar.
                • Cins (Tür) İsim: Aynı türden varlıkları karşılar (kitap, ağaç, masa, sevgi).
                • Somut İsim: Beş duyu organımızla algılanabilen varlıklar (rüzgâr, ses, koku, ışık).
                • Soyut İsim: Duyu organlarıyla algılanamayan, zihinsel kavramlar (sevgi, saygı, cesaret, keder).
                
                2. Sayılarına Göre İsimler:
                • Tekil İsim: Çokluk eki (-lar/-ler) almamış tek bir varlık (ev, defter).
                • Çoğul İsim: -lar / -ler ekiyle birden fazla varlığı karşılar (evler, defterler).
                • Topluluk İsmi: Şekilce tekil ancak anlamca birden fazla varlığı kapsayan adlar (ordu, sürü, meclis, deste, orman).
                
                3. Yapılarına Göre İsimler:
                • Basit İsim: Yapım eki almamış kök durumundaki isimler (el, göz, masa).
                • Türemiş İsim: Yapım eki alarak yeni anlam kazanmış isimler (göz-lük, kitap-çı, sev-gi).
                • Birleşik İsim: Birden fazla sözcüğün birleşmesiyle oluşan isimler (kuşburnu, Karadeniz, açıkgöz).
                
                4. İsim Tamlamaları:
                • Belirtili İsim Tamlaması: Tamlayan (-ın/-in) ve tamlanan (-ı/-i) ek alır. (Kapının kolu, okulun bahçesi)
                • Belirtisiz İsim Tamlaması: Sadece tamlanan ek alır. (Türkçe öğretmeni, masa örtüsü)
                • Zincirleme İsim Tamlaması: En az üç ismin birbirine bağlandığı tamlama. (Okul müdürünün odasının anahtarı)
            """.trimIndent(),
            rules = listOf(
                GrammarRule("Tamlayan ve Tamlanan Eki", "Tamlayan ekleri: -ın, -in, -un, -ün (kaynaştırma harfi 'n'). Tamlanan ekleri: -ı, -i, -u, -ü (kaynaştırma harfi 's').", "kapı-n-ın kol-u"),
                GrammarRule("Özel İsimlerin Yazımı", "Özel isimlere gelen çekim ekleri kesme işareti (') ile ayrılır; ancak yapım ekleri ve çoğul eki ayrılmaz.", "Ankara'ya (ayrılır), Türkler, İstanbullu (ayrılmaz)")
            ),
            examples = listOf(
                GrammarExample("Türkçe öğretmenimiz sınıfa girdi.", "Türkçe öğretmenimiz", "Belirtisiz isim tamlamasıdır. 'Türkçe' tamlayan, 'öğretmenimiz' tamlanandır.", true),
                GrammarExample("Sınıfın penceresinin camı kırılmış.", "Sınıfın penceresinin camı", "Üç isimden oluşan zincirleme isim tamlamasıdır.", true),
                GrammarExample("Ormanda yalnız bir kurt sürüsü geziyordu.", "kurt sürüsü", "'Sürü' biçimce tekil, anlamca çokluk bildiren topluluk ismidir.", true)
            ),
            memoryTricks = listOf("Formül: Belirtili = -In + -I (Kapı-nın kol-u) | Belirtisiz = 0 + -I (Okul kapı-sı)")
        ),

        // 2. FİİLLER (EYLEMLER)
        GrammarTopic(
            id = "fiiller",
            category = GrammarCategory.FIILLER,
            title = "Fiiller (Eylemler)",
            summary = "Bir eylemi, durumu, oluşu kişi ve zamana bağlayarak anlatan sözcüklerdir.",
            detailedContent = """
                Fiiller mastar eki (-mak/-mek) alabilen, zaman ve şahıs ekleriyle çekimlenen kelimelerdir.
                
                1. Anlamlarına Göre Fiiller:
                • İş (Kılış) Fiilleri: Öznenin iradesiyle gerçekleşir ve nesne alabilir. Başına 'onu' sözcüğü getirilebilir (oku-, yaz-, sil-, taşı-).
                • Durum Fiilleri: Öznenin içinde bulunduğu durumu belirtir, nesne alamaz (uyu-, gül-, ağla-, otur-, bak-).
                • Oluş Fiilleri: Öznenin iradesi dışında, kendiliğinden zaman içinde gerçekleşen değişimlerdir (sarar-, paslan-, yaşlan-, büyü-).
                
                2. Fiil Kipleri:
                A) Haber (Bildirme) Kipleri (Zaman bildirir):
                • Görülen (Bilinen) Geçmiş Zaman: -dı, -di, -du, -dü (gördü, geldi)
                • Duyulan (Öğrenilen) Geçmiş Zaman: -mış, -miş, -muş, -müş (gelmiş, duymuş)
                • Şimdiki Zaman: -yor / -makta (yazıyor, okumakta)
                • Gelecek Zaman: -acak, -ecek (gelecek, yapacak)
                • Geniş Zaman: -r, -ar, -er, -ır (yazar, gelir; olumsuzu: -maz/-mez)
                
                B) Dilek (Tasarlama) Kipleri (Zaman bildirmez):
                • Gereklilik Kipi: -malı, -meli (çalışmalı)
                • İstek Kipi: -a, -e (geleyim, yapalım)
                • Şart (Koşul) Kipi: -sa, -se (okusa, dinlese)
                • Emir Kipi: Kendi eki yoktur, şahıs ekleriyle çekimlenir (gel, gelsin, gelin).
                
                3. Ek Fiil (Ek Eylem):
                • İsim soylu sözcükleri yüklem yapar. (O çok başarılı bir öğretmendi.)
                • Basit zamanlı fiilleri birleşik zamanlı yapar: Hikaye (-di), Rivayet (-miş), Şart (-se). (gel-miş-ti -> hikaye birleşik zamanı)
            """.trimIndent(),
            rules = listOf(
                GrammarRule("İş/Kılış Testi", "Bir fiilin başına 'onu' getirin. Eğer mantıklı oluyorsa iş (kılış) fiilidir; olmuyorsa durum veya oluştur.", "Onu okudu (İş), Onu uyudu (Durum)"),
                GrammarRule("Fiilde Anlam (Zaman) Kayması", "Bir kip ekinin kendi anlamı dışında başka bir kip yerine kullanılmasıdır. Anlatım bozukluğu DEĞİLDİR, Türkçenin zenginliğidir.", "Yarın Ankara'ya gidiyorum. (Şimdiki zaman, gelecek zaman yerine kullanılmış)")
            ),
            examples = listOf(
                GrammarExample("Sonbaharda bahçedeki yapraklar sarardı.", "sarardı", "Oluş fiilidir, zamanla kendiliğinden gerçekleşen renk değişimini anlatır.", true),
                GrammarExample("Nasreddin Hoca bir gün göle maya çalar.", "çalar", "Geniş zaman kipi (-ar), geçmiş zaman (-mış) anlamında kullanılarak anlam kayması yapılmıştır.", true),
                GrammarExample("Ders bittiğinde ödevlerini hemen bitirmelisin.", "bitirmelisin", "Gereklilik kipi (-meli) ve 2. tekil şahıs eki (-sin) ile çekimlenmiştir.", true)
            ),
            memoryTricks = listOf("İş Fiili: Başına 'ONU' alabilen fiil (onu sevdi, onu yazdı) | Durum: 'ONU' alamaz (onu güldü X)")
        ),

        // 3. SIFATLAR (ÖN ADLAR)
        GrammarTopic(
            id = "sifatlar",
            category = GrammarCategory.SIFATLAR,
            title = "Sıfatlar (Ön Adlar)",
            summary = "İsimlerin önüne gelerek onları niteleyen veya belirten sözcüklerdir.",
            detailedContent = """
                Sıfatlar daima isimlerin önüne gelir ve tek başlarına kullanılamazlar. İsim düştüğünde sıfat adlaşır.
                
                1. Niteleme Sıfatları:
                • Varlıkların renk, biçim, durum gibi kalıcı özelliklerini gösterir.
                • İsme sorulan "NASIL?" sorusunun cevabıdır.
                • Örnek: Kırmızı elma (renk), yuvarlak masa (biçim), çalışkan öğrenci (durum).
                
                2. Belirtme Sıfatları:
                A) İşaret Sıfatları: Varlıkları işaretle gösterir. İsme sorulan "HANGİ?" sorusuna yanıt verir.
                • bu, şu, o, öteki, beriki, diğer... (Bu kitap, şu çocuk, öteki cadde)
                B) Sayı Sıfatları:
                • Asıl Sayı: Üç elma
                • Sıra Sayı: Üçüncü kat (-ncı/-nci)
                • Üleştirme Sayı: Üçer elma (-ar/-er)
                • Kesir Sayı: Yüzde elli başarı, çeyrek ekmek
                C) Belgisiz Sıfatlar: Varlıkları kesin olmayan miktarlarla belirtir.
                • birkaç öğrenci, bazı günler, bütün insanlar, hiçbir konu, birçok insan
                D) Soru Sıfatları: İsimleri soru yoluyla belirtir.
                • Kaç gün? Hangi soru? Nasıl bir ev? Kaçar ceviz?
                
                3. Adlaşmış Sıfat:
                • Niteleme sıfatının önündeki isim düştüğünde, sıfat ismin aldığı ekleri alarak isimleşir.
                • İhtiyar adam bastonuna dayandı -> İhtiyar bastonuna dayandı. (İhtiyar = Adlaşmış sıfat)
            """.trimIndent(),
            rules = listOf(
                GrammarRule("Sıfat Tamlaması", "Sıfat + İsim = Sıfat Tamlaması. Sıfat ile isim arasına asla noktalama işareti girmez.", "Çalışkan öğrenci (Sıfat tamlaması)"),
                GrammarRule("Belgisiz Sıfat vs Belgisiz Zamir", "İsmin önüne gelirse sıfattır; ismin yerini tutup çekim eki alırsa zamirdir.", "Birkaç öğrenci (Sıfat) vs Birkaçı geldi (Zamir)")
            ),
            examples = listOf(
                GrammarExample("Yaşlılar parkta oturmuş sohbet ediyorlardı.", "Yaşlılar", "Adlaşmış sıfattır. 'Yaşlı insanlar' tamlamasındaki isim düşmüştür.", true),
                GrammarExample("Bu zorlu yolda her gün beş kilometre yürüdü.", "Bu / zorlu / her / beş", "'Bu' işaret sıfatı, 'zorlu' niteleme sıfatı, 'her' belgisiz sıfat, 'beş' asıl sayı sıfatıdır.", true),
                GrammarExample("Hangi soruyu çözmekte zorlandın?", "Hangi", "İsme doğrudan yöneltilen soru sıfatıdır.", true)
            ),
            memoryTricks = listOf("Formül: Sıfat her zaman ismin önünde nöbet tutar! İsim düşerse sıfat adlaşır.")
        ),

        // 4. ZARFLAR (BELİRTEÇLER)
        GrammarTopic(
            id = "zarflar",
            category = GrammarCategory.ZARFLAR,
            title = "Zarflar (Belirteçler)",
            summary = "Fiilleri, fiilimsileri, sıfatları ve kendi türünden sözcükleri niteleyen sözcüklerdir.",
            detailedContent = """
                Zarflar eylemin nasıl, ne zaman, ne kadar, nereye yapıldığını açıklar.
                
                1. Durum (Hal) Zarfları:
                • Fiile sorulan "NASIL?" sorusunun cevabıdır. Eylemin yapılış tarzını gösterir.
                • Hızlı koştu, güzel konuştu, gülerek içeri girdi.
                
                2. Zaman Zarfları:
                • Fiile sorulan "NE ZAMAN?" sorusunun cevabıdır.
                • Dün geldi, sabah erkenden yola çıktı, henüz başlamadı, akşamları kitap okur.
                
                3. Miktar (Azlık-Çokluk) Zarfları:
                • Fiil, sıfat veya zarfa sorulan "NE KADAR?" sorusunun cevabıdır.
                • Çok çalıştı, az dinlendi, fazla konuştu.
                • Derecelendirme (Üstünlük) Zarfı: En ve daha sözcükleri (En çalışkan öğrenci, daha güzel bir gün).
                
                4. Yer-Yön Zarfları:
                • Fiile sorulan "NEREYE?" sorusuna yanıt verir.
                • En kritik kural: YER-YÖN ZARFLARI ASLA ÇEKİM EKİ ALMAZ!
                • Sözcükler: içeri, dışarı, aşağı, yukarı, ileri, geri, öte, beri.
                • Aşağı indi (Zarf) vs Aşağıya indi (İsim - ek aldığı için zarflıktan çıkar!)
                
                5. Soru Zarfları:
                • Fiilin anlamını soru yoluyla tamamlayan sözcüklerdir.
                • Nasıl geldi? Ne zaman aradı? Niçin / Neden ağlıyorsun? Ne kadar bekledin?
            """.trimIndent(),
            rules = listOf(
                GrammarRule("Yer-Yön Zarfı Tuzağı", "Yer-yön bildiren sözcükler (içeri, dışarı, aşağı, yukarı...) hâl eki aldıklarında (-a, -e, -da, -dan) isimleşir!", "İçeri girdi (Zarf) - İçeriye girdi (İsim)"),
                GrammarRule("Sıfat vs Zarf Ayrımı", "İsmi niteliyorsa SIFAT, fiili niteliyorsa ZARFTIR.", "Güzel ev (Sıfat) - Güzel konuştu (Zarf)")
            ),
            examples = listOf(
                GrammarExample("Öğretmenimiz konuyu çok tane tane anlattı.", "tane tane", "Fiile sorulan 'Nasıl anlattı?' sorusunun yanıtı olan durum zarfıdır.", true),
                GrammarExample("Çocuklar sevinçle yukarı koştular.", "yukarı", "Çekim eki almadığı ve eylemin yönünü belirttiği için yer-yön zarfıdır.", true),
                GrammarExample("O, sınıfın en başarılı öğrencisiydi.", "en", "Sıfatın derecesini artıran üstünlük (miktar) zarfıdır.", true)
            ),
            memoryTricks = listOf("Kural: İsimden önce gelirse SIFAT, fiilden önce gelirse ZARF!")
        ),

        // 5. EDATLAR (İLGEÇLER)
        GrammarTopic(
            id = "edatlar",
            category = GrammarCategory.EDATLAR,
            title = "Edatlar (İlgeçler)",
            summary = "Tek başlarına anlamı olmayan, cümle içinde sözcükler arasında anlam ilgisi kuran sözcüklerdir.",
            detailedContent = """
                Edatlar tek başlarına bir varlığı ya da kavramı karşılamaz. Cümleden çıkarıldıklarında cümlenin anlamı bozulur veya daralır.
                
                En Sık Kullanılan Edatlar:
                • gibi: Benzetme, eşitlik, hemen ardından anlamı katar. (Aslan gibi kuvvetli; zil çaldığı gibi çıktı.)
                • kadar: Karşılaştırma, miktar, yaklaşıklık ilgisi kurar. (Cennet kadar güzel bir vatan; sabaha kadar çalıştı.)
                • için: Amaç, sebep, uğruna anlamı kurar. (Sınavı kazanmak için çalıştı [amaç]; yağmur yağdığı için ıslandı [sebep].)
                • göre: Karşılaştırma, uygunluk, görüş anlamı. (Bana göre bu doğru; boyuna göre bir palto.)
                • ile (-le/-la): Araç, birliktelik, durum ilgisi. (Kalemle yazdı [araç]; kardeşiyle gitti [birliktelik].)
                • doğru (-e doğru): Yönelme ve zaman ilgisi. (Akşama doğru eve vardı.)
                • karşı (-e karşı): Yönelme, tutum. (Denize karşı oturdu; bana karşı saygılıdır.)
                • dolayı / ötürü (-den dolayı): Neden-sonuç ilgisi.
                • yalnız / ancak: Yalnızca, sadece anlamında kullanıldığında EDATTIR.
            """.trimIndent(),
            rules = listOf(
                GrammarRule("İle: Edat mı Bağlaç mı?", "'İle' yerine 've' getirilebiliyorsa BAĞLAÇ; getirilemiyorsa EDATTIR.", "Kitapla defter aldı (ve olur -> Bağlaç). Okula otobüsle gitti (ve olmaz -> Edat)."),
                GrammarRule("Yalnız / Ancak: Edat mı Bağlaç mı?", "'Yalnız' yerine 'sadece' geliyorsa EDAT; 'fakat/ama' geliyorsa BAĞLAÇTIR.", "Bu soruyu yalnız sen çözersin (Sadece -> Edat). Geldim yalnız seni bulamadım (Ama -> Bağlaç).")
            ),
            examples = listOf(
                GrammarExample("Buz gibi soğuk suyu bir dikişte bitirdi.", "gibi", "Benzetme ilgisi kuran edattır.", true),
                GrammarExample("Konsere gitmek için erkenden bilet aldı.", "için", "Amaç-sonuç ilişkisi kuran edattır.", true),
                GrammarExample("Bu zorlu problemi ancak Ali çözebilir.", "ancak", "'Sadece' anlamında kullanıldığı için edattır.", true)
            ),
            memoryTricks = listOf("Test: İle yerine 'VE' koy -> Oluyorsa Bağlaç, olmuyorsa Edat!")
        ),

        // 6. BAĞLAÇLAR
        GrammarTopic(
            id = "baglaclar",
            category = GrammarCategory.BAGLACLIR,
            title = "Bağlaçlar",
            summary = "Eş görevli sözcükleri, söz öbeklerini veya cümleleri birbirine bağlayan sözcüklerdir.",
            detailedContent = """
                Bağlaçlar tek başlarına anlam taşımaz, cümle içinde bağlama görevi üstlenir. Cümleden çıkarıldıklarında anlam bozulmaz, yalnızca daralabilir.
                
                En Önemli Bağlaçlar:
                • ve, veya, ya da, yahut
                • de / da (Ayrı yazılan bağlaç)
                • ki (Ayrı yazılan bağlaç)
                • ama, fakat, lakin, ancak, yalnız, oysa, ne var ki, gelgelelim
                • çünkü, zira, mademki, halbuki, meğerki
                • Tekrarlı Bağlaçlar: hem... hem..., ne... ne..., ya... ya..., gerek... gerek..., ister... ister...
                
                Kritik İmla Kuralları:
                1. "de / da" Bağlacının Yazımı:
                • Bağlaç olan 'de/da' daima ayrı yazılır. Hiçbir zaman 'te/ta' şeklinde yazılmaz!
                • Test: Cümleden çıkarıldığında anlam bozulmazsa bağlaçtır ve ayrı yazılır.
                
                2. "ki" Bağlacının Yazımı:
                • Cümleleri bağlayan 'ki' ayrı yazılır. (Bilmem ki, anladım ki...)
                • Kalıplaşmış olarak bitişik yazılanlar (SOMBAHÇEMİ kodlaması):
                  Sanki, Oysaki, Mademki, Belki, Halbuki, Çünkü, Meğerki, İllaki.
                
                3. Tekrarlı bağlaçların arasına asla virgül konmaz!
                • Hem ağladı hem güldü. (Virgül konmaz!)
            """.trimIndent(),
            rules = listOf(
                GrammarRule("de/da Çıkarma Testi", "'de' cümleden çıkarıldığında cümle mantıklı kalıyorsa bağlaçtır (ayrı yazılır). Bozuluyorsa bulunma hâl ekidir (bitişik yazılır).", "Sen de bizimle gel (ayrı) / Kitabım evde kaldı (bitişik)."),
                GrammarRule("SOMBAHÇEMİ Kodlaması", "Kalıplaşmış olarak bitişik yazılan 'ki' bağlaçları:", "Sanki, Oysaki, Mademki, Belki, Halbuki, Çünkü, Meğerki, İllaki")
            ),
            examples = listOf(
                GrammarExample("Yarın toplantıya Ahmet de gelecek.", "de", "Bağlaçtır, cümleden çıkarıldığında anlam bozulmaz; ayrı yazılır.", true),
                GrammarExample("Mademki gelmeyecektin, haber verseydin.", "Mademki", "Kalıplaşmış olduğu için 'ki' bitişik yazılmıştır (SOMBAHÇEMİ).", true),
                GrammarExample("Ne kitap okuyor ne ders çalışıyor.", "ne... ne...", "Tekrarlı bağlaçtır; arasına virgül konulmaz.", true)
            ),
            memoryTricks = listOf("Bitişik Ki Formülü: S-O-M-B-A-H-Ç-E-M-İ | de/da Testi: Çıkar, bozulmuyorsa ayrı!")
        ),

        // 7. NOKTALAMA İŞARETLERİ
        GrammarTopic(
            id = "noktalama",
            category = GrammarCategory.NOKTALAMA,
            title = "Noktalama İşaretleri",
            summary = "Duygu ve düşünceleri daha açık ifade etmek, cümlenin yapısını ve duraklama noktalarını belirlemek için kullanılan işaretlerdir.",
            detailedContent = """
                TDK Kılavuzuna Göre En Önemli Noktalama İşaretleri ve Kuralları:
                
                1. Nokta (.):
                • Tamamlanmış cümlenin sonuna konur.
                • Bazı kısaltmaların sonuna konur (Prof., Cad., vb.).
                • Sayılardan sonra sıra bildirmek için (-ıncı): 5. sınıf.
                • Saat ve dakika arasına YALNIZCA TEK NOKTA konur (14.30). [İki nokta yanlıştır!]
                • Tarihlerin yazılışında gün, ay ve yılı ayırmak için (29.10.1923).
                
                2. Virgül (,):
                • Eş görevli kelime ve kelime gruplarının arasına konur. (Pazardan elma, armut, muz aldım.)
                • Sıralı cümleleri birbirinden ayırmak için konur. (Geldi, gördü, yendi.)
                • Uzun cümlelerde yüklemden uzak düşmüş özneyi belirtmek için konur.
                • Hitaplardan sonra konur. (Değerli Öğretmenim,)
                • DİKKAT: Şart ekinden (-se/-sa), zarf-fiil ekinden ve 've, veya, de' bağlaçlarından sonra virgül konmaz!
                
                3. Noktalı Virgül (;):
                • Cümle içinde virgüllerle ayrılmış tür veya takımları birbirinden ayırmak için konur. (Erkek çocuklara Doğan, Tuğrul; kız çocuklara İnci, Çiçek adları verilir.)
                • Ögeleri arasında virgül bulunan sıralı cümleleri ayırmak için konur. (At ölür, meydan kalır; yiğit ölür, şan kalır.)
                
                4. İki Nokta (:):
                • Kendisiyle ilgili açıklama veya örnek verilecek cümlenin sonuna konur.
                • Doğrudan yapılan aktarma konuşmalarından önce konur.
                
                5. Kesme İşareti ('):
                • Özel adlara getirilen iyelik, durum ve bildirme ekleri kesme işaretiyle ayrılır (Atatürk'ün, Türkiye'miz).
                • DİKKAT: Kurum, kuruluş ve kurul adlarına gelen ekler kesmeyle AYRILMAZ! (Türk Dil Kurumuna, Bakanlar Kurulunun).
            """.trimIndent(),
            rules = listOf(
                GrammarRule("Saat ve Dakika Kuralı", "Dijital saatlerdeki iki nokta Türkçede geçersizdir. Saat ile dakika arasına daima TEK NOKTA konur.", "Doğru: 09.30 | Yanlış: 09:30"),
                GrammarRule("Kurum Adları Kesilmez", "Kurum, kuruluş, kurul ve iş yeri adlarına gelen ekler kesme işaretiyle ayrılmaz.", "Milli Eğitim Bakanlığına, Türkiye Büyük Millet Meclisinde")
            ),
            examples = listOf(
                GrammarExample("Otobüsümüz sabah 08.45'te hareket edecek.", "08.45'te", "Saat ve dakika arasına nokta konur, ek saat kuralına göre sertleşir.", true),
                GrammarExample("Türk Dil Kurumundan yeni bir kılavuz yayımlandı.", "Türk Dil Kurumundan", "Kurum adlarına gelen çekim ekleri kesmeyle ayrılmaz.", true),
                GrammarExample("Pazardan elma, armut; marketten deterjan, sabun aldık.", ";", "İki farklı grubu (meyveler ve temizlik ürünleri) ayırmak için noktalı virgül kullanılmıştır.", true)
            ),
            memoryTricks = listOf("Altın Kural: Kurum adları kesilmez! Saat arasına tek nokta konur!")
        ),

        // 8. FİİLİMSİLER (EYLEMSİLER)
        GrammarTopic(
            id = "fiilimsiler",
            category = GrammarCategory.FIILIMSILER,
            title = "Fiilimsiler (Eylemsiler)",
            summary = "Fiil kök veya gövdelerinden türeyen; isim, sıfat ve zarf görevinde kullanılan sözcüklerdir.",
            detailedContent = """
                Fiilimsiler fiil anlamını korur fakat kip ve kişi eki alamazlar. Yan cümlecik kurarlar.
                
                1. İsim-Fiil (Mastar):
                • Ekler: -ma, -ış, -mak (Kodlama: M-A-Y-I-Ş-M-A-K)
                • Örnek: Kitap okumak insanı dinlendirir. Gülüşü çok güzeldi.
                • Kalıcı İsim Tuzağı: Eylemsi eki alıp bir nesnenin kalıcı adı olan sözcükler fiilimsi sayılmaz: dondurma, dolma, çakmak, ekmek, kavurma, danışma.
                
                2. Sıfat-Fiil (Ortaç):
                • Ekler: -an, -ası, -mez, -ar, -dik, -ecek, -miş (Kodlama: ANASI MEZAR DİKECEKMİŞ)
                • Genellikle kendisinden sonraki ismi niteleyerek sıfat tamlaması kurar.
                • Örnek: Koşan çocuk, yıkılası dünya, dönülmez akşam, tanıdık yüzler, gelecek hafta, sararmış yapraklar.
                • Adlaşmış Sıfat-Fiil: Kendisinden sonraki isim düştüğünde oluşur: Gelenler içeri girdi (Gelen insanlar).
                
                3. Zarf-Fiil (Bağ-Fiil / Ulaç):
                • Ekler: -ken, -alı, -asiye, -madan, -ince, -ip, -arak, -dıkça, -e...-e, -r...-mez, -casına, -meksizin
                • Cümlede durum veya zaman zarfı görevindedir.
                • Örnek: Güle güle gitti (durum). Zil çalınca içeri girdi (zaman).
            """.trimIndent(),
            rules = listOf(
                GrammarRule("MAYIŞMAK Kodlaması", "İsim-fiil ekleri: -ma, -ış, -mak.", "Çalışmak, bakış, dinleme"),
                GrammarRule("ANASIMEZARDİKECEKMİŞ Kodlaması", "Sıfat-fiil ekleri: -an, -ası, -mez, -ar, -dik, -ecek, -miş.", "Kırılan cam, tükenmez kalem")
            ),
            examples = listOf(
                GrammarExample("Ders çalışırken müzik dinlemeyi sever.", "çalışırken / dinlemeyi", "'Çalışırken' zarf-fiil, 'dinlemeyi' isim-fiildir.", true),
                GrammarExample("Sararmış yapraklar rüzgârla savruluyordu.", "Sararmış", "'Sararmış' sözcüğü yaprak ismini niteleyen bir sıfat-fiildir.", true),
                GrammarExample("Gelen gideni aratır derler.", "Gelen / gideni", "Adlaşmış sıfat-fiillerdir.", true)
            ),
            memoryTricks = listOf("M-A-Y-I-Ş-M-A-K (İsim-fiil) | A-N-A-S-I M-E-Z-A-R D-İ-K-E-C-E-K-M-İ-Ş (Sıfat-fiil)")
        ),

        // 9. SES OLAYLARI
        GrammarTopic(
            id = "ses_olaylari",
            category = GrammarCategory.SES_OLAYLARI,
            title = "Ses Olayları",
            summary = "Türkçenin ses uyumları ve kelimelerin ek alırken veya birleşirken uğradığı ses değişimleridir.",
            detailedContent = """
                1. Ünsüz Benzeşmesi (Sertleşme):
                • Sert ünsüzle (F, S, T, K, Ç, Ş, H, P: Fıstıkçı Şahap) biten bir kelimeye c, d, g ile başlayan bir ek geldiğinde, bu ekler ç, t, k'ye dönüşür.
                • kitap-cı -> kitapçı, sokak-da -> sokakta, 1923-de -> 1923'te.
                
                2. Ünsüz Yumuşaması (Değişimi):
                • P, Ç, T, K sert ünsüzleriyle biten sözcüklere ünlüyle başlayan bir ek geldiğinde b, c, d, ğ/g'ye dönüşür.
                • kitap-ı -> kitabı, ağaç-a -> ağaca, kanat-ı -> kanadı, sokak-ı -> sokağı.
                
                3. Ünlü Düşmesi:
                • İkinci hecesinde dar ünlü (ı, i, u, ü) bulunan iki heceli sözcüklere ünlüyle başlayan ek geldiğinde ikinci hecedeki dar ünlü düşer.
                • burun-u -> burnu, alın-ı -> alnı, akıl-ı -> aklı, karın-ı -> karnı.
                
                4. Ünlü Daralması:
                • "a" veya "e" geniş ünlüleriyle biten fiillere şimdiki zaman eki (-yor) geldiğinde geniş ünlü ı, i, u, ü'ye daralır.
                • başla-yor -> başlıyor, söyle-yor -> söylüyor, bekle-yor -> bekliyor.
            """.trimIndent(),
            rules = listOf(
                GrammarRule("FISTIKÇI ŞAHAP Formülü", "Sert ünsüzler: f, s, t, k, ç, ş, h, p. Bu harflerden sonra c, d, g gelemez; ç, t, k olur.", "Sınıf-ta, yurt-tan, simit-çi"),
                GrammarRule("Ünlü Daralması Testi", "Fiilin sonuna bakın; mastar hali -a/-e ile bitiyorsa -yor ekiyle daralma gerçekleşir.", "Gözle-mek -> Gözlüyor")
            ),
            examples = listOf(
                GrammarExample("Çocuk caddeden hızla geçen arabaya baktı.", "baktı", "Bak-dı fiili ünsüz sertleşmesiyle 'baktı' olmuştur.", true),
                GrammarExample("Annemin pişirdiği yemeğin kokusu odayı sardı.", "pişirdiği / yemeğin", "'Dık' sıfat-fiili yumuşayarak 'pişirdiği' olmuş, 'yemek' k->ğ yumuşamıştır.", true),
                GrammarExample("Aklına gelen güzel fikri hemen paylaştı.", "Aklına", "Akıl sözcüğüne ünlü gelince 'ı' ünlüsü düşmüştür.", true)
            ),
            memoryTricks = listOf("Sert Ünsüzler: F-I-S-T-I-K-Ç-I Ş-A-H-A-P | Yumuşama: P, Ç, T, K -> B, C, D, Ğ")
        )
    )

    fun getTopicById(id: String): GrammarTopic? = topics.find { it.id == id }
    
    fun getTopicsByCategory(category: GrammarCategory): List<GrammarTopic> =
        topics.filter { it.category == category }
}
