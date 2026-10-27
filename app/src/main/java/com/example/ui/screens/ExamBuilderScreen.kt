package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import com.example.data.curriculum.ExamConfig
import com.example.data.curriculum.ExamRepository
import com.example.data.curriculum.ExamTemplate
import com.example.data.model.QuestionEntity
import com.example.ui.MainViewModel
import com.example.util.PrintUtils

@Composable
fun ExamBuilderScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val questions by viewModel.questions.collectAsState()
    val examConfig by viewModel.examConfig.collectAsState()
    val selectedIds by viewModel.selectedExamQuestionIds.collectAsState()
    val customPointsMap by viewModel.customPointsMap.collectAsState()

    var activeTab by remember { mutableStateOf(0) } // 0: Sınav Kağıdım (Seçilenler), 1: Soru Havuzu, 2: Hazır Şablonlar, 3: Önizleme & Ayarlar
    var showAddQuestionDialog by remember { mutableStateOf(false) }
    var showConfigDialog by remember { mutableStateOf(false) }

    // Selected questions list
    val selectedQuestions = remember(questions, selectedIds) {
        questions.filter { selectedIds.contains(it.id) }
    }

    // Total points calculation
    val totalPoints = remember(selectedQuestions, customPointsMap) {
        selectedQuestions.sumOf { customPointsMap[it.id] ?: it.points }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Header with School and Exam Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "MEB UYUMLU",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Profesyonel Sınav Stüdyosu",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "${examConfig.schoolName} • ${examConfig.gradeLevel} ${examConfig.semesterExam}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(onClick = { showConfigDialog = true }) {
                        Icon(Icons.Default.Settings, contentDescription = "Sınav Başlık Ayarları")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Score Validator Banner & Quick Print Action
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (totalPoints == 100) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "$totalPoints / 100 Puan",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (totalPoints == 100) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                if (totalPoints == 100) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF2E7D32), modifier = Modifier.size(16.dp))
                                } else {
                                    Text(
                                        text = if (totalPoints < 100) "(${100 - totalPoints} eksik)" else "(${totalPoints - 100} fazla)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.error,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(
                                text = "${selectedQuestions.size} Soru seçili • ${examConfig.bookletGroup} Kitapçığı",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            // 100'e Eşitle Sihirli Butonu
                            if (totalPoints != 100 && selectedQuestions.isNotEmpty()) {
                                TextButton(
                                    onClick = { viewModel.autoDistributePoints100() },
                                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.secondary)
                                ) {
                                    Icon(Icons.Default.Balance, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("100'e Eşitle", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                                }
                            }

                            // Doğrudan A4 Yazdır Butonu
                            Button(
                                onClick = {
                                    val questionsToPrint = if (selectedQuestions.isNotEmpty()) selectedQuestions else questions
                                    val html = ExamRepository.generatePrintableExamHtml(examConfig, questionsToPrint, customPointsMap)
                                    PrintUtils.printHtml(
                                        context = context,
                                        htmlContent = html,
                                        jobName = "Turkce_Sinav_${examConfig.bookletGroup}_Kitapcigi"
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.testTag("print_exam_button")
                            ) {
                                Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("A4 Yazdır", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Quick Chips: Kitapçık A/B & Mizanpaj 2 Sütun/Tek Sütun
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = { viewModel.toggleBookletGroup() },
                        label = { Text("Kitapçık: ${examConfig.bookletGroup}") },
                        leadingIcon = { Icon(Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(14.dp)) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    )

                    AssistChip(
                        onClick = { viewModel.toggleLayoutMode() },
                        label = { Text(if (examConfig.layoutMode == "TWO_COLUMN") "2 Sütunlu MEB Düzeni" else "Tek Sütunlu Düzen") },
                        leadingIcon = { Icon(Icons.Default.ViewWeek, contentDescription = null, modifier = Modifier.size(14.dp)) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Studio Mode Tabs
                TabRow(selectedTabIndex = activeTab, containerColor = MaterialTheme.colorScheme.surface) {
                    Tab(
                        selected = activeTab == 0,
                        onClick = { activeTab = 0 },
                        text = { Text("Sınav Kağıdım (${selectedQuestions.size})") }
                    )
                    Tab(
                        selected = activeTab == 1,
                        onClick = { activeTab = 1 },
                        text = { Text("Soru Havuzu (${questions.size})") }
                    )
                    Tab(
                        selected = activeTab == 2,
                        onClick = { activeTab = 2 },
                        text = { Text("Şablonlar") }
                    )
                }
            }
        },
        floatingActionButton = {
            if (activeTab == 1) {
                FloatingActionButton(
                    onClick = { showAddQuestionDialog = true },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    modifier = Modifier.padding(bottom = 70.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Yeni Soru Ekle")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (activeTab) {
                0 -> SelectedQuestionsTab(
                    questions = selectedQuestions,
                    pointsMap = customPointsMap,
                    bookletGroup = examConfig.bookletGroup,
                    onPointsChanged = { qId, pts -> viewModel.setQuestionPoints(qId, pts) },
                    onRemove = { viewModel.toggleQuestionSelection(it) },
                    onGoToPool = { activeTab = 1 }
                )
                1 -> QuestionPoolTab(
                    allQuestions = questions,
                    selectedIds = selectedIds,
                    onToggleSelect = { viewModel.toggleQuestionSelection(it) },
                    onSelectAll = { viewModel.selectAllQuestions(questions.map { it.id }) },
                    onClear = { viewModel.clearQuestionSelection() },
                    onDelete = { viewModel.deleteQuestion(it) }
                )
                2 -> TemplatesTab(
                    templates = ExamRepository.templates,
                    onApply = { template ->
                        viewModel.applyExamTemplate(template)
                        activeTab = 0
                        Toast.makeText(context, "${template.title} yüklendi.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

    // Exam Config Dialog
    if (showConfigDialog) {
        var district by remember { mutableStateOf(examConfig.districtName) }
        var schoolName by remember { mutableStateOf(examConfig.schoolName) }
        var academicYear by remember { mutableStateOf(examConfig.academicYear) }
        var semesterExam by remember { mutableStateOf(examConfig.semesterExam) }
        var gradeLevel by remember { mutableStateOf(examConfig.gradeLevel) }
        var duration by remember { mutableStateOf(examConfig.durationMinutes.toString()) }
        var signatures by remember { mutableStateOf(examConfig.teacherSignatures) }
        var includeKey by remember { mutableStateOf(examConfig.includeAnswerKey) }
        var includeScoreTable by remember { mutableStateOf(examConfig.includeScoreTable) }

        AlertDialog(
            onDismissRequest = { showConfigDialog = false },
            title = { Text("MEB Sınav Kağıdı Üst Bilgileri") },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = district,
                            onValueChange = { district = it },
                            label = { Text("İl / İlçe Millî Eğitim Müdürlüğü") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = schoolName,
                            onValueChange = { schoolName = it },
                            label = { Text("Okul Adı") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = academicYear,
                            onValueChange = { academicYear = it },
                            label = { Text("Öğretim Yılı (Örn: 2026-2027)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = semesterExam,
                            onValueChange = { semesterExam = it },
                            label = { Text("Sınav Adı (Örn: 1. Dönem 1. Ortak Yazılı)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = gradeLevel,
                            onValueChange = { gradeLevel = it },
                            label = { Text("Sınıf Seviyesi (Örn: 8. Sınıf)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = duration,
                            onValueChange = { duration = it },
                            label = { Text("Sınav Süresi (Dakika)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = signatures,
                            onValueChange = { signatures = it },
                            label = { Text("Zümre Öğretmenleri İsimleri") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = includeScoreTable, onCheckedChange = { includeScoreTable = it })
                            Text("Öğretmen Soru Barem Çizelgesini Ekle", style = MaterialTheme.typography.bodySmall)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = includeKey, onCheckedChange = { includeKey = it })
                            Text("Cevap Anahtarı ve Puanlama Baremini Ekle", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateExamConfig(
                            examConfig.copy(
                                districtName = district,
                                schoolName = schoolName,
                                academicYear = academicYear,
                                semesterExam = semesterExam,
                                gradeLevel = gradeLevel,
                                durationMinutes = duration.toIntOrNull() ?: 40,
                                teacherSignatures = signatures,
                                includeAnswerKey = includeKey,
                                includeScoreTable = includeScoreTable
                            )
                        )
                        showConfigDialog = false
                    }
                ) {
                    Text("Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfigDialog = false }) { Text("İptal") }
            }
        )
    }

    // Add Custom Question Dialog (supports Open Ended & Multiple Choice)
    if (showAddQuestionDialog) {
        var questionType by remember { mutableStateOf("OPEN_ENDED") }
        var topic by remember { mutableStateOf("Okuma & Anlama") }
        var grade by remember { mutableStateOf("8. Sınıf") }
        var readingPassage by remember { mutableStateOf("") }
        var questionText by remember { mutableStateOf("") }
        var optA by remember { mutableStateOf("") }
        var optB by remember { mutableStateOf("") }
        var optC by remember { mutableStateOf("") }
        var optD by remember { mutableStateOf("") }
        var correctAns by remember { mutableStateOf("") }
        var pointsStr by remember { mutableStateOf("15") }
        var explanation by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddQuestionDialog = false },
            title = { Text("Soru Bankasına Yeni Soru Ekle") },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text("Soru Türünü Seçiniz:", style = MaterialTheme.typography.labelMedium)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilterChip(
                                selected = questionType == "OPEN_ENDED",
                                onClick = { questionType = "OPEN_ENDED" },
                                label = { Text("Açık Uçlu (MEB)") }
                            )
                            FilterChip(
                                selected = questionType == "MULTIPLE_CHOICE",
                                onClick = { questionType = "MULTIPLE_CHOICE" },
                                label = { Text("Çoktan Seçmeli (Test)") }
                            )
                            FilterChip(
                                selected = questionType == "ESSAY",
                                onClick = { questionType = "ESSAY" },
                                label = { Text("Kompozisyon") }
                            )
                        }
                    }
                    item {
                        OutlinedTextField(
                            value = topic,
                            onValueChange = { topic = it },
                            label = { Text("Konu / Kazanım Başlığı") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = readingPassage,
                            onValueChange = { readingPassage = it },
                            label = { Text("Ortak Okuma Metni (Varsa)") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 2
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = questionText,
                            onValueChange = { questionText = it },
                            label = { Text("Soru Kökü") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3
                        )
                    }

                    if (questionType == "MULTIPLE_CHOICE") {
                        item {
                            OutlinedTextField(
                                value = optA,
                                onValueChange = { optA = it },
                                label = { Text("A Şıkkı") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            OutlinedTextField(
                                value = optB,
                                onValueChange = { optB = it },
                                label = { Text("B Şıkkı") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            OutlinedTextField(
                                value = optC,
                                onValueChange = { optC = it },
                                label = { Text("C Şıkkı") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            OutlinedTextField(
                                value = optD,
                                onValueChange = { optD = it },
                                label = { Text("D Şıkkı") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            Text("Doğru Şık:", style = MaterialTheme.typography.labelSmall)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf("A", "B", "C", "D").forEach { s ->
                                    FilterChip(
                                        selected = correctAns == s,
                                        onClick = { correctAns = s },
                                        label = { Text(s) }
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            OutlinedTextField(
                                value = correctAns,
                                onValueChange = { correctAns = it },
                                label = { Text("Beklenen Yanıt / Cevap Anahtarı") },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 2
                            )
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = pointsStr,
                            onValueChange = { pointsStr = it },
                            label = { Text("Soru Puanı (Örn: 10, 15, 20)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = explanation,
                            onValueChange = { explanation = it },
                            label = { Text("Puanlama Baremi & Çözüm İpucu") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (questionText.isNotBlank()) {
                            viewModel.addCustomQuestion(
                                QuestionEntity(
                                    gradeLevel = grade,
                                    topic = topic,
                                    questionType = questionType,
                                    readingPassage = readingPassage,
                                    questionText = questionText,
                                    optionA = if (optA.startsWith("A)")) optA else "A) $optA",
                                    optionB = if (optB.startsWith("B)")) optB else "B) $optB",
                                    optionC = if (optC.startsWith("C)")) optC else "C) $optC",
                                    optionD = if (optD.startsWith("D)")) optD else "D) $optD",
                                    correctAnswer = correctAns,
                                    points = pointsStr.toIntOrNull() ?: 10,
                                    explanation = explanation
                                )
                            )
                            showAddQuestionDialog = false
                            Toast.makeText(context, "Soru eklendi ve sınava dahil edildi.", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddQuestionDialog = false }) { Text("İptal") }
            }
        )
    }
}

// -------------------------------------------------------------
// TAB 0: SEÇİLEN SORULAR (SINAV KAĞIDIM)
// -------------------------------------------------------------
@Composable
fun SelectedQuestionsTab(
    questions: List<QuestionEntity>,
    pointsMap: Map<Long, Int>,
    bookletGroup: String,
    onPointsChanged: (Long, Int) -> Unit,
    onRemove: (Long) -> Unit,
    onGoToPool: () -> Unit
) {
    if (questions.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.PostAdd,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Sınavınızda henüz soru bulunmuyor",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Soru havuzundan soru ekleyebilir veya hazır şablonları kullanabilirsiniz.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onGoToPool) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Soru Havuzuna Git")
                }
            }
        }
    } else {
        val displayList = if (bookletGroup == "B") questions.reversed() else questions

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("selected_questions_list"),
            contentPadding = PaddingValues(16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(displayList, key = { _, q -> q.id }) { index, question ->
                val currentPts = pointsMap[question.id] ?: question.points

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = when (question.questionType) {
                                    "OPEN_ENDED" -> MaterialTheme.colorScheme.secondaryContainer
                                    "ESSAY" -> MaterialTheme.colorScheme.tertiaryContainer
                                    else -> MaterialTheme.colorScheme.primaryContainer
                                },
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = when (question.questionType) {
                                        "OPEN_ENDED" -> "Açık Uçlu / MEB"
                                        "ESSAY" -> "Kompozisyon"
                                        "TRUE_FALSE" -> "Doğru / Yanlış"
                                        else -> "Çoktan Seçmeli"
                                    },
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = question.topic,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            // Points Selector Controls (- / +)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { onPointsChanged(question.id, (currentPts - 5).coerceAtLeast(5)) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.Remove, contentDescription = "Puan Azalt", modifier = Modifier.size(16.dp))
                                }
                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "$currentPts P",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { onPointsChanged(question.id, currentPts + 5) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Puan Artır", modifier = Modifier.size(16.dp))
                                }
                            }

                            IconButton(
                                onClick = { onRemove(question.id) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Sınavdan Çıkar", tint = MaterialTheme.colorScheme.error)
                            }
                        }

                        // Reading Passage Box if available
                        if (question.readingPassage.isNotBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = "📖 Bağlı Olduğu Okuma Parçası:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = question.readingPassage,
                                        style = MaterialTheme.typography.bodySmall,
                                        maxLines = 3
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = question.questionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        // Render options or answer line preview
                        if (question.questionType == "MULTIPLE_CHOICE") {
                            Spacer(modifier = Modifier.height(6.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(question.optionA, style = MaterialTheme.typography.bodySmall)
                                Text(question.optionB, style = MaterialTheme.typography.bodySmall)
                                Text(question.optionC, style = MaterialTheme.typography.bodySmall)
                                Text(question.optionD, style = MaterialTheme.typography.bodySmall)
                            }
                        } else {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "✍️ Öğrenci için ${question.answerLines} satırlık çizgili yanıt alanı bırakılacaktır.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (question.correctAnswer.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "✔ Cevap Anahtarı: ${question.correctAnswer}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// TAB 1: SORU HAVUZU
// -------------------------------------------------------------
@Composable
fun QuestionPoolTab(
    allQuestions: List<QuestionEntity>,
    selectedIds: Set<Long>,
    onToggleSelect: (Long) -> Unit,
    onSelectAll: () -> Unit,
    onClear: () -> Unit,
    onDelete: (QuestionEntity) -> Unit
) {
    var selectedTopicFilter by remember { mutableStateOf("Tümü") }
    var selectedTypeFilter by remember { mutableStateOf("Tümü") }

    val distinctTopics = remember(allQuestions) {
        listOf("Tümü") + allQuestions.map { it.topic }.distinct()
    }

    val filteredList = remember(allQuestions, selectedTopicFilter, selectedTypeFilter) {
        allQuestions.filter { q ->
            val matchesTopic = selectedTopicFilter == "Tümü" || q.topic == selectedTopicFilter
            val matchesType = selectedTypeFilter == "Tümü" || when (selectedTypeFilter) {
                "Açık Uçlu" -> q.questionType == "OPEN_ENDED"
                "Test" -> q.questionType == "MULTIPLE_CHOICE"
                "Kompozisyon" -> q.questionType == "ESSAY"
                "D/Y" -> q.questionType == "TRUE_FALSE"
                else -> true
            }
            matchesTopic && matchesType
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("question_pool_list"),
        contentPadding = PaddingValues(16.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Selection helpers & Filters
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onSelectAll) {
                        Icon(Icons.Default.SelectAll, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Tümünü Seç (${allQuestions.size})")
                    }
                    TextButton(onClick = onClear) {
                        Icon(Icons.Default.Deselect, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Seçimi Sıfırla")
                    }
                }

                // Type filter chips
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Tümü", "Açık Uçlu", "Test", "Kompozisyon").forEach { type ->
                        FilterChip(
                            selected = selectedTypeFilter == type,
                            onClick = { selectedTypeFilter = type },
                            label = { Text(type) }
                        )
                    }
                }
            }
        }

        items(filteredList, key = { it.id }) { question ->
            val isSelected = selectedIds.contains(question.id)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { onToggleSelect(question.id) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                    else MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(
                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = { onToggleSelect(question.id) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = question.topic,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${question.points} Puan",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = question.questionText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (question.correctAnswer.isNotBlank()) {
                            Text(
                                text = "Doğru: ${question.correctAnswer.take(60)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    IconButton(onClick = { onDelete(question) }) {
                        Icon(Icons.Default.DeleteOutline, contentDescription = "Sil", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// TAB 2: HAZIR ŞABLONLAR
// -------------------------------------------------------------
@Composable
fun TemplatesTab(
    templates: List<ExamTemplate>,
    onApply: (ExamTemplate) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("exam_templates_list"),
        contentPadding = PaddingValues(16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Hazır MEB Sınav Şablonları",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tek dokunuşla resmî yönetmeliğe uygun 100 puanlık sınav formatı yükleyin",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        items(templates, key = { it.id }) { template ->
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
                                text = template.targetGrade,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "100 TAM PUAN",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = template.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = template.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { onApply(template) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.DownloadDone, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Bu Şablonu Sınava Uygula", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
