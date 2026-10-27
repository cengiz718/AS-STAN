package com.example.data.web

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CloudTutanakItem(
    val id: String,
    val title: String,
    val type: String,          // Zümre Tutanağı, ŞÖK Tutanağı, Veli Toplantısı, BEP Birimi vb.
    val schoolName: String,
    val authorName: String,
    val term: String,
    val date: String,
    val htmlContent: String,
    val updatedAt: String = ""
)

object CloudTutanakRepository {

    private val _syncedDocuments = MutableStateFlow<List<CloudTutanakItem>>(emptyList())
    val syncedDocuments: StateFlow<List<CloudTutanakItem>> = _syncedDocuments.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    init {
        startSyncListener()
    }

    private fun startSyncListener() {
        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("shared_documents")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w("CloudTutanakRepo", "Listen error", error)
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val docs = snapshot.documents.mapNotNull { doc ->
                            try {
                                CloudTutanakItem(
                                    id = doc.getString("id") ?: doc.id,
                                    title = doc.getString("title") ?: "",
                                    type = doc.getString("type") ?: "Tutanak",
                                    schoolName = doc.getString("schoolName") ?: "Emniyet Ortaokulu",
                                    authorName = doc.getString("authorName") ?: "Başöğretmen Zeynep Akdağ",
                                    term = doc.getString("term") ?: "1. Dönem",
                                    date = doc.getString("date") ?: "",
                                    htmlContent = doc.getString("htmlContent") ?: "",
                                    updatedAt = doc.getString("updatedAt") ?: ""
                                )
                            } catch (e: Exception) {
                                null
                            }
                        }
                        _syncedDocuments.value = docs
                    }
                }
        } catch (ignored: Exception) {}
    }

    /**
     * Hazırlanan tutanağı Firestore bulutuna kaydeder.
     * Bu işlem tamamlandığında TÜM ANDROID CİHAZLARINDA otomatik olarak güncellenir!
     */
    fun saveAndBroadcastTutanak(
        id: String,
        title: String,
        type: String,
        schoolName: String = "Emniyet Ortaokulu",
        authorName: String = "Başöğretmen Zeynep Akdağ",
        term: String,
        date: String,
        htmlContent: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        _isSaving.value = true
        try {
            val firestore = FirebaseFirestore.getInstance()
            val timeString = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("tr")).format(Date())

            val data = mapOf(
                "id" to id,
                "title" to title,
                "type" to type,
                "schoolName" to schoolName,
                "authorName" to authorName,
                "term" to term,
                "date" to date,
                "htmlContent" to htmlContent,
                "updatedAt" to timeString,
                "timestamp" to System.currentTimeMillis()
            )

            firestore.collection("shared_documents").document(id)
                .set(data)
                .addOnSuccessListener {
                    _isSaving.value = false
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    _isSaving.value = false
                    onError(e.localizedMessage ?: "Kaydedilemedi")
                }
        } catch (e: Exception) {
            _isSaving.value = false
            onError(e.localizedMessage ?: "Bulut senkronizasyon hatası")
        }
    }
}
