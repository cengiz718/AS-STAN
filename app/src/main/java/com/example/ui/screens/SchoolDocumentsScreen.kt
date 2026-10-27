package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.SchoolDocumentItem
import com.example.data.curriculum.SchoolDocumentsRepository
import com.example.util.PrintUtils

@Composable
fun SchoolDocumentsScreen() {
    val context = LocalContext.current
    var activeMainTab by remember { mutableStateOf(0) } // 0: Tutanak Oluşturucu (turkcen.net), 1: Hazır Evrak Arşivi
    var selectedCategory by remember { mutableStateOf("Tümü") }
    var selectedDoc by remember { mutableStateOf(SchoolDocumentsRepository.documents.first()) }
    var searchQuery by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("Emniyet Ortaokulu") }
    var academicYear by remember { mutableStateOf("2026-2027 Eğitim Öğretim Yılı") }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = activeMainTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = activeMainTab == 0,
                onClick = { activeMainTab = 0 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Build, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Tutanak Oluşturucu (turkcen.net)", fontWeight = FontWeight.Bold)
                    }
                }
            )
            Tab(
                selected = activeMainTab == 1,
                onClick = { activeMainTab = 1 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FolderShared, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("MEB Evrak Arşivi", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }

        if (activeMainTab == 0) {
            TutanakBuilderScreen()
        } else {
            SchoolDocumentsArchiveView()
        }
    }
}

@Composable
private fun SchoolDocumentsArchiveView() {
    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf("Tümü") }
    var selectedDoc by remember { mutableStateOf(SchoolDocumentsRepository.documents.first()) }
    var searchQuery by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("Emniyet Ortaokulu") }
    var academicYear by remember { mutableStateOf("2026-2027 Eğitim Öğretim Yılı") }

    val categories = remember {
        listOf("Tümü") + SchoolDocumentsRepository.documents.map { it.category }.distinct()
    }

    val filteredDocs = remember(selectedCategory, searchQuery) {
        SchoolDocumentsRepository.documents.filter { doc ->
            val matchesCat = selectedCategory == "Tümü" || doc.category == selectedCategory
            val matchesQuery = searchQuery.isBlank() ||
                doc.title.contains(searchQuery, ignoreCase = true) ||
                doc.description.contains(searchQuery, ignoreCase = true) ||
                doc.documentContent.contains(searchQuery, ignoreCase = true)
            matchesCat && matchesQuery
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "MEB RESMÎ DOSYA",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Türkçe Öğretmeni Okul Evrakları",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "Zümre tutanakları, sınav analiz raporları, ŞÖK, kulüp ve veli evrakları",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Direct Print Action
                    Button(
                        onClick = {
                            val html = SchoolDocumentsRepository.generatePrintableDocumentHtml(
                                doc = selectedDoc,
                                schoolName = schoolName,
                                academicYear = academicYear
                            )
                            PrintUtils.printHtml(
                                context = context,
                                htmlContent = html,
                                jobName = "MEB_Evrak_${selectedDoc.id}"
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("print_school_doc_button")
                    ) {
                        Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("A4 Çıktı", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Search field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Evrak adı, tutanak veya içerik ara...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Category chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("school_documents_view"),
            contentPadding = PaddingValues(16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Horizontal list of documents
            item {
                Text(
                    text = "Evrak Seçimi (${filteredDocs.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 2.dp)
                ) {
                    items(filteredDocs, key = { it.id }) { doc ->
                        val isSelected = selectedDoc.id == doc.id
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedDoc = doc },
                            label = { Text(doc.title) },
                            leadingIcon = {
                                if (isSelected) {
                                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(14.dp))
                                }
                            }
                        )
                    }
                }
            }

            // Selected Document Details Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "${selectedDoc.category} • ${selectedDoc.targetTerm}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            IconButton(onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                                val clip = ClipData.newPlainText("Evrak Metni", selectedDoc.documentContent)
                                clipboard?.setPrimaryClip(clip)
                                Toast.makeText(context, "Evrak metni panoya kopyalandı.", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(Icons.Default.ContentCopy, contentDescription = "Metni Kopyala")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = selectedDoc.title,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = selectedDoc.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(14.dp))

                        // Full Official Document Content
                        Text(
                            text = selectedDoc.documentContent,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Bottom Action: Print Official A4 Document
                        Button(
                            onClick = {
                                val html = SchoolDocumentsRepository.generatePrintableDocumentHtml(
                                    doc = selectedDoc,
                                    schoolName = schoolName,
                                    academicYear = academicYear
                                )
                                PrintUtils.printHtml(
                                    context = context,
                                    htmlContent = html,
                                    jobName = "MEB_Evrak_${selectedDoc.id}"
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Resmî A4 Çıktısı Al / PDF Olarak Kaydet", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
