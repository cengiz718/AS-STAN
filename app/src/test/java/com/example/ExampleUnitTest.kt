package com.example

import com.example.data.curriculum.ExamConfig
import com.example.data.curriculum.ExamRepository
import com.example.data.curriculum.GrammarRepository
import com.example.data.curriculum.SchoolDocumentsRepository
import com.example.data.curriculum.SpecialDaysRepository
import com.example.data.curriculum.VocabularyRepository
import com.example.data.model.GrammarCategory
import com.example.data.web.WebMaterialHelper
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testGrammarTopicsContainRequiredCategories() {
        val topics = GrammarRepository.topics
        assertTrue("Grammar topics should not be empty", topics.isNotEmpty())

        val categories = topics.map { it.category }.toSet()
        assertTrue("Must contain ISIMLER", categories.contains(GrammarCategory.ISIMLER))
        assertTrue("Must contain FIILLER", categories.contains(GrammarCategory.FIILLER))
        assertTrue("Must contain SIFATLAR", categories.contains(GrammarCategory.SIFATLAR))
        assertTrue("Must contain ZARFLAR", categories.contains(GrammarCategory.ZARFLAR))
        assertTrue("Must contain EDATLAR", categories.contains(GrammarCategory.EDATLAR))
        assertTrue("Must contain BAGLACLIR", categories.contains(GrammarCategory.BAGLACLIR))
        assertTrue("Must contain NOKTALAMA", categories.contains(GrammarCategory.NOKTALAMA))
    }

    @Test
    fun testVocabularyRepositoryFunctions() {
        val wordList = VocabularyRepository.wordList
        assertTrue("Word list should contain words", wordList.size >= 10)

        val randomWord = VocabularyRepository.getRandomWord()
        assertNotNull("Random word should not be null", randomWord)
        assertTrue("Word must have a non-empty name", randomWord.word.isNotBlank())
        assertTrue("Word must have a definition", randomWord.definition.isNotBlank())
        assertTrue("Word must have example sentences", randomWord.exampleSentences.isNotEmpty())

        val searchResults = VocabularyRepository.searchWords("özveri")
        assertTrue("Search for 'özveri' should find matching word", searchResults.any { it.word == "özveri" })
    }

    @Test
    fun testSpecialDaysAndHtmlGeneration() {
        val days = SpecialDaysRepository.items
        assertTrue("Special days list should not be empty", days.isNotEmpty())

        val dilBayrami = SpecialDaysRepository.getItemById("dil_bayrami")
        assertNotNull("Dil Bayramı should exist", dilBayrami)

        val htmlPano = SpecialDaysRepository.generatePrintableHtml(dilBayrami!!, "pano")
        assertTrue("Printable board HTML should have content", htmlPano.contains("Türk Dil Bayramı"))
        assertTrue("Printable board HTML should have A4 styles", htmlPano.contains("@page { size: A4;"))
    }

    @Test
    fun testExamGenerationHtml() {
        val questions = ExamRepository.defaultQuestions
        assertTrue("Default exam questions should be populated", questions.isNotEmpty())

        val config = ExamConfig(schoolName = "Örnek Ortaokulu", gradeLevel = "8. Sınıf", bookletGroup = "A")
        val html = ExamRepository.generatePrintableExamHtml(config, questions)

        assertTrue("Generated exam HTML should contain school name", html.contains("ÖRNEK ORTAOKULU"))
        assertTrue("Generated exam HTML should contain booklet group", html.contains("A KİTAPÇIĞI"))
    }

    @Test
    fun testSchoolDocumentsRepository() {
        val docs = SchoolDocumentsRepository.documents
        assertTrue("School documents list should not be empty", docs.isNotEmpty())

        val zumre = SchoolDocumentsRepository.getDocumentById("zumre_sene_basi")
        assertNotNull("Zümre tutanağı should exist", zumre)

        val html = SchoolDocumentsRepository.generatePrintableDocumentHtml(zumre!!, "Atatürk Ortaokulu")
        assertTrue("Document HTML should contain title", html.contains("Sene Başı Türkçe Zümre"))
        assertTrue("Document HTML should contain MEB title", html.contains("T.C. MİLLÎ EĞİTİM BAKANLIĞI"))
    }

    @Test
    fun testWebMaterialAiAnalysis() {
        val channels = WebMaterialHelper.curatedChannels
        assertTrue("Curated channels should not be empty", channels.isNotEmpty())

        val sampleArticle = channels.first()
        val analysis = WebMaterialHelper.analyzeAndGenerateMaterial(sampleArticle.title, sampleArticle.content)

        assertNotNull("Analysis should be generated", analysis)
        assertTrue("Passage title should match", analysis.passageTitle.isNotBlank())
        assertTrue("Generated questions should be populated", analysis.generatedQuestions.isNotEmpty())
    }
}
