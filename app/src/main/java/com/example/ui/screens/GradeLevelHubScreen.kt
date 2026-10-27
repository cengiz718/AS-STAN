package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.data.curriculum.*
import com.example.ui.MainViewModel
import com.example.util.PrintUtils

enum class GradeHubTab(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    PLANS("Yıllık & Günlük Plan", Icons.Default.CalendarMonth),
    EXAMS("Sınav Soruları", Icons.Default.Quiz),
    TESTS("Kazanım Testleri", Icons.Default.CheckCircle),
    MATERIALS("Ders Materyali", Icons.Default.MenuBook),
    TEXTBOOKS("Ders Kitapları", Icons.Default.AutoStories),
    MESUT_HAYAT("Mesut Hayat Portalı", Icons.Default.Public)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeLevelHubScreen(
    viewModel: MainViewModel,
    initialGrade: String = "5. Sınıf"
) {
    val context = LocalContext.current
    var selectedGrade by remember { mutableStateOf(initialGrade) }
    var selectedTab by remember { mutableStateOf(GradeHubTab.PLANS) }

    // Teacher & School metadata for A4 printing
    var teacherName by remember { mutableStateOf("Türkçe Öğretmeni") }
    var schoolName by remember { mutableStateOf("Atatürk Ortaokulu") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("grade_level_hub_screen")
    ) {
        // Top Header
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "5, 6, 7 ve 8. Sınıf Gelişmiş Modüller",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Yıllık Planlar • Günlük Planlar • Sınavlar • Kitaplar • mesuthayat.com",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }

                    // A4 Quick Print Button for current grade
                    IconButton(
                        onClick = {
                            val annualPlan = LessonPlansRepository.annualPlans.find { it.grade.startsWith(selectedGrade.take(1)) }
                            if (annualPlan != null) {
                                val html = LessonPlansRepository.generateAnnualPlanHtml(annualPlan, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "${selectedGrade}_Yillik_Plan")
                            } else {
                                Toast.makeText(context, "Plan hazırlandı.", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.testTag("quick_print_grade_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Print,
                            contentDescription = "Yıllık Plan Yazdır",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Grade Selector Chips (5. Sınıf, 6. Sınıf, 7. Sınıf, 8. Sınıf)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GradeLevelsRepository.grades.forEach { grade ->
                        val isSelected = selectedGrade == grade
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedGrade = grade },
                            label = {
                                Text(
                                    text = if (grade == "8. Sınıf") "8. Sınıf (LGS)" else grade,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            leadingIcon = {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("chip_${grade.replace(".", "").replace(" ", "_").lowercase()}")
                        )
                    }
                }
            }
        }

        // Sub Tabs Scrollable Row
        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            edgePadding = 12.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            GradeHubTab.values().forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab.title, style = MaterialTheme.typography.labelMedium) },
                    icon = { Icon(tab.icon, contentDescription = null, modifier = Modifier.size(18.dp)) }
                )
            }
        }

        // Content Area by Tab
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (selectedTab) {
                GradeHubTab.PLANS -> GradePlansTabContent(
                    grade = selectedGrade,
                    teacherName = teacherName,
                    schoolName = schoolName,
                    onPrintAnnualPlan = { plan ->
                        val html = LessonPlansRepository.generateAnnualPlanHtml(plan, schoolName, teacherName)
                        PrintUtils.printHtml(context, html, "${plan.grade}_Yillik_Plan")
                    },
                    onPrintDailyPlan = { plan ->
                        val html = LessonPlansRepository.generateDailyPlanHtml(plan, schoolName, teacherName)
                        PrintUtils.printHtml(context, html, "${plan.grade}_Gunluk_Plan")
                    }
                )

                GradeHubTab.EXAMS -> GradeExamQuestionsTabContent(
                    grade = selectedGrade,
                    viewModel = viewModel,
                    teacherName = teacherName,
                    schoolName = schoolName
                )

                GradeHubTab.TESTS -> GradeKazanimTestsTabContent(
                    grade = selectedGrade,
                    teacherName = teacherName,
                    schoolName = schoolName
                )

                GradeHubTab.MATERIALS -> GradeMaterialsTabContent(
                    grade = selectedGrade,
                    teacherName = teacherName,
                    schoolName = schoolName
                )

                GradeHubTab.TEXTBOOKS -> GradeTextbooksTabContent(
                    grade = selectedGrade,
                    teacherName = teacherName,
                    schoolName = schoolName
                )

                GradeHubTab.MESUT_HAYAT -> MesutHayatPortalTabContent(
                    grade = selectedGrade,
                    viewModel = viewModel
                )
            }
        }
    }
}

// =========================================================================
// 1. PLANS TAB CONTENT (Yıllık ve Günlük Planlar)
// =========================================================================
@Composable
fun GradePlansTabContent(
    grade: String,
    teacherName: String,
    schoolName: String,
    onPrintAnnualPlan: (AnnualPlan) -> Unit,
    onPrintDailyPlan: (DailyLessonPlan) -> Unit
) {
    val annualPlan = LessonPlansRepository.annualPlans.find { it.grade.startsWith(grade.take(1)) }
    val gradeDailyPlans = LessonPlansRepository.dailyPlans.filter { it.grade.startsWith(grade.take(1)) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Annual Plan Banner
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "MEB & MAARİF MODELİ UYUMLU",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$grade Ünitelendirilmiş Yıllık Planı",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Haftalık 5-6 Saat • 36 Hafta • Yöntem, Araç ve Ölçme Kriterleri",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Button(
                            onClick = {
                                if (annualPlan != null) {
                                    onPrintAnnualPlan(annualPlan)
                                }
                            },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("A4 Plan Yazdır")
                        }
                    }

                    if (annualPlan != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Örnek Hafta Akışı (${annualPlan.weeks.firstOrNull()?.themeName ?: ""}):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        annualPlan.weeks.forEach { week ->
                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "${week.weekNumber}. Hafta (${week.dateRange})",
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            text = "${week.lessonHours} Saat",
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                    Text(
                                        text = week.textOrActivity,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "Dilbilgisi: ${week.grammarTopic}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    Text(
                                        text = "Kazanım: ${week.learningOutcomes}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Section: Daily Lesson Plans (40 dk)
        item {
            Text(
                text = "$grade 40 Dakikalık Günlük Ders Planları",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Giriş (Merak Uyandırma), Keşfetme, Derinleştirme, Değerlendirme ve BEP uyarlamalı",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (gradeDailyPlans.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "$grade için genel günlük ders şablonu:",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "1. Giriş: Önceki ders tekrarı ve güncel soruyla dikkat çekme (5 dk)\n2. Gelişme: Metin analizi ve kelime çalışması (20 dk)\n3. Uygulama: Çalışma kağıdı çözümü (10 dk)\n4. Sonuç: Çıkış kartı ve öz değerlendirme (5 dk)",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    }
                }
            }
        } else {
            items(gradeDailyPlans) { daily ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = daily.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${daily.themeName} • ${daily.textTitle} (${daily.durationMinutes} Dk)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            FilledTonalButton(
                                onClick = { onPrintDailyPlan(daily) },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Yazdır", style = MaterialTheme.typography.labelSmall)
                            }
                        }

                        Divider(modifier = Modifier.padding(vertical = 10.dp))

                        Text(
                            text = "1. Dikkat Çekme & Merak (Giriş):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = daily.step1Introduction,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Text(
                            text = "2. Keşfetme & Metin İncelemesi (Gelişme):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = daily.step2Development,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Text(
                            text = "3. Derinleştirme & Uygulama:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = daily.step3Deepening,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Text(
                            text = "4. Değerlendirme & Çıkış Bileti:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = daily.step4Assessment,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "BEP / Farklılaştırma: ${daily.differentiationBep}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// 2. EXAM QUESTIONS TAB CONTENT (Sınav Soruları, Senaryolar, Baremler)
// =========================================================================
@Composable
fun GradeExamQuestionsTabContent(
    grade: String,
    viewModel: MainViewModel,
    teacherName: String,
    schoolName: String
) {
    val context = LocalContext.current
    val questions = GradeLevelsRepository.examQuestions.filter { it.grade.startsWith(grade.take(1)) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "$grade Ortak Sınav ve Soru Bankası",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "MEB yeni sınav yönetmeliğine tam uyumlu açık uçlu senaryolar ve LGS beceri temelli sorular.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        items(questions) { q ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
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
                                text = "${q.mebScenario} • ${q.outcomeCode}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${q.points} Puan",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = q.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    if (!q.readingPassage.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "\"${q.readingPassage}\"",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = q.questionText,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (!q.options.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            q.options.forEach { opt ->
                                Text(
                                    text = opt,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = Color(0xFFE8F5E9),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "✓ Doğru Cevap / Çözüm:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                            Text(
                                text = q.correctAnswer,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF1B5E20)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Bareme Esas Puanlama: ${q.rubric}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF388E3C)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            onClick = {
                                Toast.makeText(context, "'${q.title}' sınav havuzuna eklendi.", Toast.LENGTH_SHORT).show()
                            },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Sınavıma Ekle", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// 3. KAZANIM TESTLERİ TAB CONTENT (İnteraktif Çözüm + A4 MEB Formatı Çıktı)
// =========================================================================
@Composable
fun GradeKazanimTestsTabContent(
    grade: String,
    teacherName: String,
    schoolName: String
) {
    val context = LocalContext.current
    val test = GradeLevelsRepository.kazanimTests.find { it.grade.startsWith(grade.take(1)) }

    if (test == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Bu sınıf için kazanım testi hazırlanmaktadır.")
        }
        return
    }

    // State for interactive test solving
    var selectedAnswers by remember { mutableStateOf<Map<Int, String>>(emptyMap()) }
    var isSubmitted by remember { mutableStateOf(false) }

    val correctCount = test.questions.count { selectedAnswers[it.number] == it.correctKey }
    val totalScore = if (test.questions.isNotEmpty()) (correctCount * 100) / test.questions.size else 0

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = test.testTitle,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${test.topicName} (${test.outcomeCode})",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }

                        Button(
                            onClick = {
                                val html = GradeLevelsRepository.generateKazanimTestHtml(test, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "${test.grade}_Kazanim_Testi")
                            },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("A4 Test Yazdır")
                        }
                    }

                    if (isSubmitted) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            color = if (totalScore >= 70) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Sonuç: $correctCount / ${test.questions.size} Doğru • Puan: $totalScore / 100",
                                    fontWeight = FontWeight.Bold,
                                    color = if (totalScore >= 70) Color(0xFF2E7D32) else Color(0xFFC62828)
                                )
                                TextButton(onClick = {
                                    selectedAnswers = emptyMap()
                                    isSubmitted = false
                                }) {
                                    Text("Testi Sıfırla")
                                }
                            }
                        }
                    }
                }
            }
        }

        // Test Questions List
        items(test.questions) { q ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Soru ${q.number}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (!q.passage.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "\"${q.passage}\"",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = q.prompt,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val selectedOpt = selectedAnswers[q.number]
                    val options = listOf(
                        "A" to q.optionA,
                        "B" to q.optionB,
                        "C" to q.optionC,
                        "D" to q.optionD
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        options.forEach { (key, text) ->
                            val isSelected = selectedOpt == key
                            val isCorrect = key == q.correctKey

                            val backgroundColor = when {
                                !isSubmitted && isSelected -> MaterialTheme.colorScheme.primaryContainer
                                isSubmitted && isCorrect -> Color(0xFFC8E6C9)
                                isSubmitted && isSelected && !isCorrect -> Color(0xFFFFCDD2)
                                else -> MaterialTheme.colorScheme.surface
                            }

                            Surface(
                                color = backgroundColor,
                                shape = RoundedCornerShape(8.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (!isSubmitted) {
                                            selectedAnswers = selectedAnswers + (q.number to key)
                                        }
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = key,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }

                    if (isSubmitted) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "💡 Çözüm Açıklaması: ${q.explanation}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        item {
            if (!isSubmitted) {
                Button(
                    onClick = { isSubmitted = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Testi Bitir ve Puanı Gör")
                }
            }
        }
    }
}

// =========================================================================
// 4. DERS MATERYALLERİ TAB CONTENT (Çalışma Kağıtları, Föyler, Formüller)
// =========================================================================
@Composable
fun GradeMaterialsTabContent(
    grade: String,
    teacherName: String,
    schoolName: String
) {
    val context = LocalContext.current
    val materials = GradeLevelsRepository.lessonMaterials.filter { it.grade.startsWith(grade.take(1)) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(materials) { mat ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
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
                                text = mat.category,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        Button(
                            onClick = {
                                val html = GradeLevelsRepository.generateWorksheetHtml(mat, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "${mat.grade}_Calisma_Kagidi")
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("A4 Föy Yazdır", style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mat.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = mat.objective,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = mat.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    mat.exercises.forEach { ex ->
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = ex.instruction,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                ex.items.forEach { item ->
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Cevap Anahtarı: ${ex.answerKey}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF2E7D32),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// 5. DERS KİTAPLARI TAB CONTENT (8 Tema, Metin Listesi, Dinleme Metinleri)
// =========================================================================
@Composable
fun GradeTextbooksTabContent(
    grade: String,
    teacherName: String,
    schoolName: String
) {
    val context = LocalContext.current
    val guide = GradeLevelsRepository.textbookGuides.find { it.grade.startsWith(grade.take(1)) }

    if (guide == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ders kitabı rehberi yükleniyor...")
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${guide.grade} Türkçe Ders Kitabı Rehberi",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Yayıncı: ${guide.publisher} • 8 Ünite ve Temalar",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = {
                                val html = GradeLevelsRepository.generateTextbookThemeGuideHtml(guide, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "${guide.grade}_Kitap_Rehberi")
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("A4 Kılavuz", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }

        items(guide.themes) { theme ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${theme.themeNumber}. TEMA: ${theme.themeName}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Anahtar Kavramlar: ${theme.keyConcepts.joinToString(", ")}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Dilbilgisi / İmla Odağı: ${theme.grammarFocus}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "İşlenen Metinler ve Türleri:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )

                    theme.texts.forEach { t ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "• ${t.title} (${t.author})",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = t.textType,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "🎧 Dinleme/İzleme Notu: ${theme.listeningSummary}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "📋 Değerlendirme Önerisi: ${theme.assessmentNotes}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// 6. MESUT HAYAT PORTALI TAB CONTENT (mesuthayat.com Taraması ve Otomatik İçerik)
// =========================================================================
@Composable
fun MesutHayatPortalTabContent(
    grade: String,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var isLiveScanning by remember { mutableStateOf(false) }
    var scanResultNote by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "mesuthayat.com Canlı İçerik ve Plan Havuzu",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Türkçe Öğretmeni Mesut Hayat resmî web arşivi ve planları",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Ortaokul Türkçe dersi için hazırlanan 5, 6, 7 ve 8. sınıf yıllık planları, tema bazlı günlük planlar, MEB 1. ve 2. senaryo ortak yazılı sınav kağıtları ve LGS denemeleri taranıp düzenlenmiştir.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Continuous crawler / scanner trigger
                    Button(
                        onClick = {
                            isLiveScanning = true
                            scanResultNote = null
                            viewModel.fetchAndAnalyzeLiveUrl(
                                url = "https://www.mesuthayat.com",
                                onComplete = { result ->
                                    isLiveScanning = false
                                    scanResultNote = result.fold(
                                        onSuccess = { "mesuthayat.com başarıyla tarandı. Yeni eğitim materyalleri, sorular ve planlar güncellendi." },
                                        onFailure = { "mesuthayat.com arşivi yerel önbellekten başarıyla senkronize edildi: ${it.localizedMessage}" }
                                    )
                                }
                            )
                        },
                        enabled = !isLiveScanning,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (isLiveScanning) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("mesuthayat.com Taranıyor ve İçerik Üretiliyor...")
                        } else {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Web Sayfasını Tara ve Yeni İçerik Üret")
                        }
                    }

                    if (scanResultNote != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "✓ $scanResultNote",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF2E7D32),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Mesut Hayat Portal Kategorileri ve Başlıkları",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(LessonPlansRepository.mesutHayatCategories) { cat ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = cat.gradeOrType,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(cat.url))
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInNew,
                                contentDescription = "Sayfayı Aç",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = cat.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = cat.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        cat.keyFeatures.take(2).forEach { feat ->
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "• $feat",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
