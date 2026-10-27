package com.example.data.curriculum

import com.example.data.model.VocabularyWord
import kotlin.random.Random

object VocabularyRepository {

    val wordList: List<VocabularyWord> = listOf(
        VocabularyWord(
            id = "w1",
            word = "özveri",
            partOfSpeech = "İsim",
            definition = "Bir amaç uğruna veya bir başkası yararına kendi çıkarlarından, rahatından vazgeçme durumu; fedakârlık.",
            synonyms = listOf("fedakârlık", "feragat", "esirgemezlik"),
            antonyms = listOf("bencillik", "egoizm"),
            exampleSentences = listOf(
                "Öğretmenlerimizin gösterdiği özveri sayesinde geleceğe umutla bakıyoruz.",
                "Bu başarı, gecesini gündüzüne katan ekibin büyük bir özverisiyle elde edildi."
            ),
            difficulty = "Orta",
            origin = "Öz Türkçe"
        ),
        VocabularyWord(
            id = "w2",
            word = "narin",
            partOfSpeech = "Sıfat",
            definition = "İnce yapılı, kırılgan, kolayca zedelenebilen, zarif ve nazik.",
            synonyms = listOf("zarif", "ince", "kırılgan", "nazik"),
            antonyms = listOf("kaba", "sert", "dayanıklı", "iri"),
            exampleSentences = listOf(
                "Vazodaki narin kardelenler baharın ilk müjdecisiydi.",
                "Sanatçının elleri, piyanoyu çalarken son derece narin hareketlerle süzülüyordu."
            ),
            difficulty = "Temel",
            origin = "Farsça"
        ),
        VocabularyWord(
            id = "w3",
            word = "ihtişam",
            partOfSpeech = "İsim",
            definition = "Göz alıcı büyüklük ve güzellik, debdebe, görkem.",
            synonyms = listOf("görkem", "debdebe", "şatafat", "gösteriş"),
            antonyms = listOf("sadelik", "mütevazılık", "gösterişsizlik"),
            exampleSentences = listOf(
                "Topkapı Sarayı'nın ihtişamı, ziyaretçileri yüzyıllar öncesine götürüyor.",
                "Boğaz'ın akşam saatlerindeki ihtişamı tarif edilemez bir duygudur."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w4",
            word = "hasret",
            partOfSpeech = "İsim",
            definition = "Kavuşulmak istenen kimse, şey veya yer için duyulan güçlü özlem duygusu.",
            synonyms = listOf("özlem", "tahassür"),
            antonyms = listOf("kavuşma", "vuslat"),
            exampleSentences = listOf(
                "Memleketine duyduğu hasret, yazdığı şiirlerin ana temasını oluşturuyordu.",
                "Yıllar sonra kavuştuğu kardeşine gözyaşlarıyla sarılarak hasret giderdi."
            ),
            difficulty = "Temel",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w5",
            word = "özgün",
            partOfSpeech = "Sıfat",
            definition = "Yalnız kendine özgü bir nitelik taşıyan, taklit olmayan, orijinal.",
            synonyms = listOf("orijinal", "nev-i şahsına münhasır", "benzersiz"),
            antonyms = listOf("sıradan", "taklit", "kopya", "klişe"),
            exampleSentences = listOf(
                "Yazarın dili son derece özgün olduğu için romanı ilk sayfadan itibaren okuru yakalıyor.",
                "Öğrencilerin projelerinde özgün fikirler üretmesi en büyük önceliğimizdir."
            ),
            difficulty = "Orta",
            origin = "Öz Türkçe"
        ),
        VocabularyWord(
            id = "w6",
            word = "mütevazı",
            partOfSpeech = "Sıfat",
            definition = "Alçak gönüllü, gösterişsiz, kibirden uzak olan kişi veya ortam.",
            synonyms = listOf("alçak gönüllü", "gösterişsiz", "mahviyetkâr"),
            antonyms = listOf("kibirli", "gururlu", "kendini beğenmiş", "şatafatlı"),
            exampleSentences = listOf(
                "Büyük bir bilim insanı olmasına rağmen her zaman mütevazı bir tavır sergiledi.",
                "Kasabanın kenarındaki mütevazı kulübesinde huzur içinde yaşıyordu."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w7",
            word = "erdem",
            partOfSpeech = "İsim",
            definition = "Ahlakın övdüğü iyi olma, alçak gönüllülük, doğruluk gibi ruhsal ve ahlaki üstünlük; fazilet.",
            synonyms = listOf("fazilet", "meziyet"),
            antonyms = listOf("erdemsizlik", "rezillik", "kusur"),
            exampleSentences = listOf(
                "Adalet ve dürüstlük, insanın sahip olabileceği en yüce erdemlerdir.",
                "Atatürk, 'Cumhuriyet, fikren, ilmen ve bedenen kuvvetli ve yüksek karakterli koruyucular ister.' diyerek erdemi vurgulamıştır."
            ),
            difficulty = "İleri",
            origin = "Öz Türkçe"
        ),
        VocabularyWord(
            id = "w8",
            word = "azim",
            partOfSpeech = "İsim",
            definition = "Bir işteki engelleri yenme kararlılığı, sebat.",
            synonyms = listOf("kararlılık", "sebat", "gayret"),
            antonyms = listOf("yılma", "pes etme", "gevşeklik", "kararsızlık"),
            exampleSentences = listOf(
                "Girdiği sınavlarda karşılaştığı zorluklara rağmen azmini hiçbir zaman kaybetmedi.",
                "Büyük hedeflere ulaşmak yetenek kadar yüksek bir azim de gerektirir."
            ),
            difficulty = "Temel",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w9",
            word = "şefkat",
            partOfSpeech = "İsim",
            definition = "Sevecen, koruyucu, acıma ve sevgiyle karışık derin duygu.",
            synonyms = listOf("merhamet", "sevecenlik", "acıma"),
            antonyms = listOf("acımasızlık", "gaddarlık", "katılık"),
            exampleSentences = listOf(
                "Annelerimizin karşılıksız şefkati, dünyadaki en sıcak sığınaktır.",
                "Yaralı kediye şefkatle yaklaşarak onu hemen veterinere götürdü."
            ),
            difficulty = "Temel",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w10",
            word = "cüret",
            partOfSpeech = "İsim",
            definition = "Yüreklilik, cesaret; kimi zaman da saygısızca sergilenen atılganlık.",
            synonyms = listOf("cesaret", "yiğitlik", "atılganlık"),
            antonyms = listOf("korkaklık", "çekingenlik", "ürkeklik"),
            exampleSentences = listOf(
                "Fırtınalı denize açılmak büyük bir cüret istiyordu.",
                "Haksızlığa karşı sesini yükseltme cüretini gösteren az sayıdaki kişiden biriydi."
            ),
            difficulty = "İleri",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w11",
            word = "kıvanç",
            partOfSpeech = "İsim",
            definition = "Övünç, iftihar; gurur verici bir başarıdan duyulan sevinç.",
            synonyms = listOf("iftihar", "övünç", "gurur"),
            antonyms = listOf("utanç", "hicap", "mahcubiyet"),
            exampleSentences = listOf(
                "Öğrencisinin yarışmada birinci olduğunu öğrenen öğretmen büyük bir kıvanç duydu.",
                "Cumhuriyetimizin 100. yılını kutlamak hepimiz için tarifsiz bir kıvanç kaynağıdır."
            ),
            difficulty = "Orta",
            origin = "Öz Türkçe"
        ),
        VocabularyWord(
            id = "w12",
            word = "kasvet",
            partOfSpeech = "İsim",
            definition = "Sıkıntı, iç karartısı, hüzün veren basıklık.",
            synonyms = listOf("keder", "sıkıntı", "iç karartısı", "gam"),
            antonyms = listOf("ferahlık", "neş'e", "huzur", "aydınlık"),
            exampleSentences = listOf(
                "Yağmurlu ve gri gökyüzü tüm şehre derin bir kasvet çöktürmüştü.",
                "Eski terk edilmiş konağın odalarında dayanılmaz bir kasvet havası vardı."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w13",
            word = "berrak",
            partOfSpeech = "Sıfat",
            definition = "Aydınlık, duru, net, tortusuz, temiz ve pırıl pırıl.",
            synonyms = listOf("duru", "arı", "temiz", "net"),
            antonyms = listOf("bulanık", "kirli", "puslu", "karışık"),
            exampleSentences = listOf(
                "Dağdan kaynayan berrak su, yaz sıcağında köylülere can veriyordu.",
                "Yazarın berrak anlatımı, karmaşık felsefi düşünceleri bile anlaşılır kılmıştı."
            ),
            difficulty = "Temel",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w14",
            word = "maharet",
            partOfSpeech = "İsim",
            definition = "Bir işte gösterilen ustalık, beceri, el yatkınlığı.",
            synonyms = listOf("ustalık", "beceri", "hüner", "marifet"),
            antonyms = listOf("acemilik", "beceriksizlik"),
            exampleSentences = listOf(
                "Ebru sanatçısı, fırçasını büyük bir maharetle suyun üzerinde gezdirdi.",
                "Ahşap oymacılığında sergilediği maharet, görenleri hayran bırakıyordu."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w15",
            word = "tasa",
            partOfSpeech = "İsim",
            definition = "Geleceğe ilişkin belirsizlikten doğan kaygı, endişe, üzüntü.",
            synonyms = listOf("kaygı", "endişe", "gam", "keder"),
            antonyms = listOf("rahatlık", "huzur", "güven", "ferahlık"),
            exampleSentences = listOf(
                "Hiçbir tasaya kapılmadan, sadece dersine odaklanmalısın.",
                "Evlatlarının geleceği için duyduğu tasa annenin uykularını kaçırıyordu."
            ),
            difficulty = "Temel",
            origin = "Öz Türkçe"
        ),
        VocabularyWord(
            id = "w16",
            word = "münakaşa",
            partOfSpeech = "İsim",
            definition = "Karşılıklı sert tartışma, çekişme, sözlü atışma.",
            synonyms = listOf("tartışma", "çekişme", "ağız kavgası"),
            antonyms = listOf("uzlaşma", "anlaşma", "barış", "uyum"),
            exampleSentences = listOf(
                "Gereksiz bir münakaşaya girmek yerine sakin kalarak konuyu tatlıya bağladılar.",
                "Edebiyat kulübünde yapılan münakaşalar daima saygı çerçevesinde yürütülürdü."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w17",
            word = "sükûnet",
            partOfSpeech = "İsim",
            definition = "Durgunluk, sessizlik, huzur, dinginlik.",
            synonyms = listOf("dinginlik", "sessizlik", "huzur", "asayiş"),
            antonyms = listOf("kargaşa", "gürültü", "telaş", "kaos"),
            exampleSentences = listOf(
                "Kütüphanenin derin sükûneti içinde saatlerce ders çalıştılar.",
                "Kriz anlarında liderin koruduğu sükûnet, etrafındakilere de cesaret aşılar."
            ),
            difficulty = "İleri",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w18",
            word = "vefa",
            partOfSpeech = "İsim",
            definition = "Dostluk ve sevgide bağlılık, iyilikleri unutmama, sözünde durma.",
            synonyms = listOf("bağlılık", "sadakat", "kadirşinaslık"),
            antonyms = listOf("vefasızlık", "nankörlük", "ihanet"),
            exampleSentences = listOf(
                "Emekli olan yaşlı öğretmenlerini her bayram ziyaret ederek büyük bir vefa örneği gösterdiler.",
                "Vefa sadece bir semt adı değil, insanın yüreğinde taşıdığı en güzel borçtur."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w19",
            word = "muazzam",
            partOfSpeech = "Sıfat",
            definition = "Alışılmışın çok üstünde büyüklüğü, gücü veya etkisi olan; heybetli, olağanüstü.",
            synonyms = listOf("olağanüstü", "harikulade", "heybetli", "kocaman"),
            antonyms = listOf("küçücük", "önemsiz", "sıradan", "basit"),
            exampleSentences = listOf(
                "Kurtuluş Savaşı'nda milletimiz muazzam bir direniş ve kahramanlık sergilemiştir.",
                "Gökyüzünde beliren muazzam dolunay geceyi gündüze çevirmişti."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        ),
        VocabularyWord(
            id = "w20",
            word = "itina",
            partOfSpeech = "İsim",
            definition = "Bir işi en ince ayrıntısına kadar önemseyerek özenle yapma durumu; özen.",
            synonyms = listOf("özen", "dikkat", "ihtimam"),
            antonyms = listOf("özensizlik", "ihmal", "baştan savmalık"),
            exampleSentences = listOf(
                "Yazılı kağıtlarını büyük bir itina ile tek tek okuyup notlandırdı.",
                "Bahçıvan, fidanları büyük bir itina ile budayıp suladı."
            ),
            difficulty = "Orta",
            origin = "Arapça"
        )
    )

    fun getRandomWord(): VocabularyWord {
        return wordList[Random.nextInt(wordList.size)]
    }

    fun getWordById(id: String): VocabularyWord? {
        return wordList.find { it.id == id }
    }

    fun searchWords(query: String): List<VocabularyWord> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return wordList
        return wordList.filter {
            it.word.lowercase().contains(q) ||
            it.definition.lowercase().contains(q) ||
            it.synonyms.any { s -> s.lowercase().contains(q) } ||
            it.antonyms.any { a -> a.lowercase().contains(q) }
        }
    }
}
