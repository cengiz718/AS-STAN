package com.example.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.data.curriculum.MebCurriculumRepository
import com.example.data.model.CompositionEntity
import com.example.data.model.LessonPlanEntity
import com.example.data.model.ReadingRecordEntity
import com.example.ui.MainViewModel

@Composable
fun CurriculumAndRubricScreen(viewModel: MainViewModel) {
    var selectedTab by remember { mutableStateOf(0) } // 0: Ders Planları, 1: MEB Kazanımları, 2: Kompozisyon Ölçeği, 3: Kitap Takibi
    val lessonPlans by viewModel.lessonPlans.collectAsState()
    val compositions by viewModel.compositions.collectAsState()
    val readingRecords by viewModel.readingRecords.collectAsState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "MEB Planları, Ölçek & Okuma",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Ders planı hazırlama, yazma değerlendirme ölçeği ve kitap takibi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    edgePadding = 12.dp,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("Ders Planları (${lessonPlans.size})") }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("MEB Kazanımları") }
                    )
                    Tab(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        text = { Text("Kompozisyon Ölçeği") }
                    )
                    Tab(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        text = { Text("Kitap Okuma Takibi") }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> LessonPlansTab(viewModel = viewModel, lessonPlans = lessonPlans)
                1 -> CurriculumOutcomesTab()
                2 -> CompositionRubricTab(viewModel = viewModel, compositions = compositions)
                3 -> ReadingTrackerTab(viewModel = viewModel, records = readingRecords)
            }
        }
    }
}

// -------------------------------------------------------------
// TAB 1: DERS PLANLARI
// -------------------------------------------------------------
@Composable
fun LessonPlansTab(viewModel: MainViewModel, lessonPlans: List<LessonPlanEntity>) {
    var showAddPlanDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("lesson_plans_list"),
            contentPadding = PaddingValues(16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Button(
                    onClick = { showAddPlanDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Yeni Ders Planı Oluştur", fontWeight = FontWeight.Bold)
                }
            }

            items(lessonPlans, key = { it.id }) { plan ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
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
                                    text = "${plan.gradeLevel} • ${plan.durationMinutes} dk",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            IconButton(onClick = { viewModel.deleteLessonPlan(plan) }) {
                                Icon(Icons.Default.DeleteOutline, contentDescription = "Sil", tint = MaterialTheme.colorScheme.error)
                            }
                        }

                        Text(
                            text = plan.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Alan: ${plan.subjectArea}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "🎯 Hedef Kazanımlar:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = plan.learningOutcomes,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "📝 Ders İçi Etkinlik Akışı:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = plan.activities,
                            style = MaterialTheme.typography.bodySmall
                        )

                        if (plan.homework.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "🏠 Ödev / Değerlendirme: ${plan.homework}",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddPlanDialog) {
        var title by remember { mutableStateOf("") }
        var grade by remember { mutableStateOf("8. Sınıf") }
        var area by remember { mutableStateOf("Okuma / Dil Bilgisi") }
        var outcomes by remember { mutableStateOf("T.8.3.9. Fiilimsilerin işlevlerini kavrar.") }
        var duration by remember { mutableStateOf("40") }
        var activities by remember { mutableStateOf("") }
        var homework by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddPlanDialog = false },
            title = { Text("Yeni Ders Planı Ekle") },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Ders Konusu / Başlığı") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = grade,
                            onValueChange = { grade = it },
                            label = { Text("Sınıf Seviyesi") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = outcomes,
                            onValueChange = { outcomes = it },
                            label = { Text("Hedef MEB Kazanımları") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 2
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = activities,
                            onValueChange = { activities = it },
                            label = { Text("Ders Akışı ve Etkinlikler") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = homework,
                            onValueChange = { homework = it },
                            label = { Text("Verilecek Ödev / Görev") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank()) {
                            viewModel.addLessonPlan(
                                LessonPlanEntity(
                                    title = title,
                                    gradeLevel = grade,
                                    subjectArea = area,
                                    learningOutcomes = outcomes,
                                    durationMinutes = duration.toIntOrNull() ?: 40,
                                    activities = activities,
                                    homework = homework
                                )
                            )
                            showAddPlanDialog = false
                        }
                    }
                ) {
                    Text("Planı Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddPlanDialog = false }) { Text("İptal") }
            }
        )
    }
}

// -------------------------------------------------------------
// TAB 2: MEB KAZANIMLARI
// -------------------------------------------------------------
@Composable
fun CurriculumOutcomesTab() {
    var selectedGrade by remember { mutableStateOf("8. Sınıf") }
    var searchQuery by remember { mutableStateOf("") }

    val filteredOutcomes = remember(selectedGrade, searchQuery) {
        MebCurriculumRepository.outcomes.filter {
            it.grade == selectedGrade &&
            (searchQuery.isBlank() || it.description.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery, ignoreCase = true))
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("5. Sınıf", "6. Sınıf", "7. Sınıf", "8. Sınıf").forEach { grade ->
                    FilterChip(
                        selected = selectedGrade == grade,
                        onClick = { selectedGrade = grade },
                        label = { Text(grade) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Kazanım kodu veya metin ara...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }

        items(filteredOutcomes, key = { it.code }) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = item.code,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = item.domain,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// TAB 3: KOMPOZİSYON VE YAZMA DEĞERLENDİRME ÖLÇEĞİ (RUBRİK)
// -------------------------------------------------------------
@Composable
fun CompositionRubricTab(viewModel: MainViewModel, compositions: List<CompositionEntity>) {
    val context = LocalContext.current
    var studentName by remember { mutableStateOf("") }
    var gradeSection by remember { mutableStateOf("8/A") }
    var essayTitle by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("Hikaye") }

    // MEB Rubric Scores
    var titleScore by remember { mutableIntStateOf(10) } // Max 10
    var structureScore by remember { mutableIntStateOf(25) } // Max 25 (Giriş, gelişme, sonuç, paragraf bütünlüğü)
    var contentScore by remember { mutableIntStateOf(25) } // Max 25 (Düşünceyi geliştirme, özgünlük)
    var spellingScore by remember { mutableIntStateOf(20) } // Max 20 (İmla ve noktalama)
    var vocabScore by remember { mutableIntStateOf(20) } // Max 20 (Sözcük zenginliği, akıcılık)
    var teacherNotes by remember { mutableStateOf("") }

    val totalScore = titleScore + structureScore + contentScore + spellingScore + vocabScore

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("composition_rubric_view"),
        contentPadding = PaddingValues(16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "MEB Yazma Becerisi Değerlendirme Ölçeği",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Öğrenci kompozisyonlarını 5 kriter üzerinden objektif puanlayın",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = studentName,
                            onValueChange = { studentName = it },
                            label = { Text("Öğrenci Adı Soyadı") },
                            modifier = Modifier.weight(1.5f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = gradeSection,
                            onValueChange = { gradeSection = it },
                            label = { Text("Şube") },
                            modifier = Modifier.weight(0.7f),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = essayTitle,
                        onValueChange = { essayTitle = it },
                        label = { Text("Yazının / Kompozisyonun Başlığı") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 5 Rubric Criteria Sliders
                    RubricSliderItem("1. Başlığın İçeriğe Uygunluğu", titleScore, 10) { titleScore = it }
                    RubricSliderItem("2. Paragraf Düzeni & Bütünlük (Giriş-Gelişme-Sonuç)", structureScore, 25) { structureScore = it }
                    RubricSliderItem("3. Düşünceyi Geliştirme, Ana Fikir & Özgünlük", contentScore, 25) { contentScore = it }
                    RubricSliderItem("4. İmla Kuralları & Noktalama İşaretleri", spellingScore, 20) { spellingScore = it }
                    RubricSliderItem("5. Sözcük Dağarcığı, Anlatım Zenginliği & Akıcılık", vocabScore, 20) { vocabScore = it }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = teacherNotes,
                        onValueChange = { teacherNotes = it },
                        label = { Text("Öğretmen Değerlendirme Notu / Tavsiyesi") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Total Score Banner & Save Button
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "TOPLAM NOT",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$totalScore / 100",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Button(
                                onClick = {
                                    if (studentName.isNotBlank()) {
                                        viewModel.saveComposition(
                                            CompositionEntity(
                                                studentName = studentName,
                                                gradeSection = gradeSection,
                                                essayTitle = if (essayTitle.isBlank()) "Başlıksız Kompozisyon" else essayTitle,
                                                genre = genre,
                                                titleScore = titleScore,
                                                structureScore = structureScore,
                                                contentScore = contentScore,
                                                spellingGrammarScore = spellingScore,
                                                vocabularyScore = vocabScore,
                                                totalScore = totalScore,
                                                teacherNotes = teacherNotes
                                            )
                                        )
                                        studentName = ""
                                        essayTitle = ""
                                        teacherNotes = ""
                                        Toast.makeText(context, "Değerlendirme kaydedildi.", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Lütfen öğrenci adını giriniz.", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Default.Save, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Kaydet")
                            }
                        }
                    }
                }
            }
        }

        // Saved Evaluations
        if (compositions.isNotEmpty()) {
            item {
                Text(
                    text = "Değerlendirilen Kompozisyonlar (${compositions.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            items(compositions, key = { it.id }) { comp ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = comp.studentName,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "(${comp.gradeSection})",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = "“${comp.essayTitle}”",
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                            if (comp.teacherNotes.isNotBlank()) {
                                Text(
                                    text = "Görüş: ${comp.teacherNotes}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${comp.totalScore}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconButton(onClick = { viewModel.deleteComposition(comp) }) {
                                Icon(Icons.Default.DeleteOutline, contentDescription = "Sil", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RubricSliderItem(
    label: String,
    currentScore: Int,
    maxScore: Int,
    onScoreChanged: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            Text(
                text = "$currentScore / $maxScore",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Slider(
            value = currentScore.toFloat(),
            onValueChange = { onScoreChanged(it.toInt()) },
            valueRange = 0f..maxScore.toFloat(),
            steps = maxScore - 1
        )
    }
}

// -------------------------------------------------------------
// TAB 4: KİTAP OKUMA TAKİBİ
// -------------------------------------------------------------
@Composable
fun ReadingTrackerTab(viewModel: MainViewModel, records: List<ReadingRecordEntity>) {
    var showAddDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Button(
                onClick = { showAddDialog = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.BookmarkAdd, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Yeni Kitap Okuma Kaydı Ekle")
            }
        }

        items(records, key = { it.id }) { record ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = record.studentName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = record.gradeSection,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "📖 ${record.bookTitle} (${record.author})",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Durum: ${record.status} • ${record.pageCount} Sayfa",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        if (record.teacherComment.isNotBlank()) {
                            Text(
                                text = "Öğretmen Notu: ${record.teacherComment}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    if (record.teacherScore > 0) {
                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "${record.teacherScore} Puan",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    IconButton(onClick = { viewModel.deleteReadingRecord(record) }) {
                        Icon(Icons.Default.DeleteOutline, contentDescription = "Sil", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }

        // Recommended 100 Temel Eser Section
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "100 Temel Eser & Gençlik Kitap Önerileri",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(MebCurriculumRepository.recommendedBooks) { book ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = book.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                        Text(text = "${book.author} • ${book.pages} Sayfa", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = book.recommendedGrade,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        var studentName by remember { mutableStateOf("") }
        var gradeSection by remember { mutableStateOf("8/A") }
        var bookTitle by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var pages by remember { mutableStateOf("150") }
        var comment by remember { mutableStateOf("") }
        var score by remember { mutableStateOf("90") }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Yeni Kitap Okuma Kaydı") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Öğrenci Adı Soyadı") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = gradeSection,
                        onValueChange = { gradeSection = it },
                        label = { Text("Sınıf / Şube") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = bookTitle,
                        onValueChange = { bookTitle = it },
                        label = { Text("Kitap Adı") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Yazar") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = pages,
                        onValueChange = { pages = it },
                        label = { Text("Sayfa Sayısı") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = comment,
                        onValueChange = { comment = it },
                        label = { Text("Öğretmen Değerlendirme Yorumu") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (studentName.isNotBlank() && bookTitle.isNotBlank()) {
                            viewModel.addReadingRecord(
                                ReadingRecordEntity(
                                    studentName = studentName,
                                    gradeSection = gradeSection,
                                    bookTitle = bookTitle,
                                    author = author,
                                    pageCount = pages.toIntOrNull() ?: 100,
                                    teacherComment = comment,
                                    teacherScore = score.toIntOrNull() ?: 85
                                )
                            )
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) { Text("İptal") }
            }
        )
    }
}
