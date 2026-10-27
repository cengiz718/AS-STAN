package com.example.data.model

data class VocabularyWord(
    val id: String,
    val word: String,
    val partOfSpeech: String, // İsim, Sıfat, Fiil, Zarf
    val definition: String,
    val synonyms: List<String>, // Eş anlamlıları
    val antonyms: List<String>, // Zıt anlamlıları
    val exampleSentences: List<String>,
    val difficulty: String = "Orta", // Temel, Orta, İleri
    val origin: String = "Türkçe", // Köken (Türkçe, Arapça, Farsça, Fransızca...)
    val isFavorite: Boolean = false,
    val isLearned: Boolean = false
)

data class GrammarRule(
    val ruleTitle: String,
    val explanation: String,
    val formulaOrTrick: String? = null
)

data class GrammarExample(
    val sentence: String,
    val targetWord: String,
    val analysis: String,
    val isCorrectUsage: Boolean = true
)

enum class GrammarCategory(val displayName: String, val iconName: String) {
    ISIMLER("İsimler (Adlar)", "Badge"),
    FIILLER("Fiiller (Eylemler)", "PlayArrow"),
    SIFATLAR("Sıfatlar (Ön Adlar)", "Palette"),
    ZARFLAR("Zarflar (Belirteçler)", "Speed"),
    EDATLAR("Edatlar (İlgeçler)", "Link"),
    BAGLACLIR("Bağlaçlar", "CallSplit"),
    NOKTALAMA("Noktalama İşaretleri", "EditNote"),
    SES_OLAYLARI("Ses Olayları", "GraphicEq"),
    FIILIMSILER("Fiilimsiler", "Extension")
}

data class GrammarTopic(
    val id: String,
    val category: GrammarCategory,
    val title: String,
    val summary: String,
    val detailedContent: String,
    val rules: List<GrammarRule>,
    val examples: List<GrammarExample>,
    val memoryTricks: List<String> = emptyList() // Akılda kalıcı kodlamalar (M-A-Y-I-Ş-M-A-K vb.)
)
