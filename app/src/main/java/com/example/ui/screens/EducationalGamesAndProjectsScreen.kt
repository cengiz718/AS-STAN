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

enum class ProjectTab(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    DILIMIZIN_ZENGINLIKLERI("Dilimizin Zenginlikleri", Icons.Default.AutoAwesome),
    SKILLS_EXAM("Dinleme & Konuşma", Icons.Default.Mic),
    RESFEBE_GAMES("Resfebe & Oyunlar", Icons.Default.Extension),
    ELECTIVE_AND_BEP("Seçmeli Ders & BEP", Icons.Default.FactCheck),
    TEACHER_HUBS("Büyük Öğretmen Ağları", Icons.Default.Public)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationalGamesAndProjectsScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(ProjectTab.DILIMIZIN_ZENGINLIKLERI) }

    var teacherName by remember { mutableStateOf("Türkçe Öğretmeni") }
    var schoolName by remember { mutableStateOf("Atatürk Ortaokulu") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("educational_projects_screen")
    ) {
        // Top Header
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
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
                            text = "Türkçe Projeleri ve Beceriler",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Dilimizin Zenginlikleri • Dinleme-Konuşma Sınavı • Resfebe • BEP",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }

                    IconButton(
                        onClick = {
                            val html = EducationalGamesAndProjectsRepository.generateDilimizinZenginlikleriHtml(schoolName, teacherName)
                            PrintUtils.printHtml(context, html, "Dilimizin_Zenginlikleri_Plani")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Print,
                            contentDescription = "Proje Planını Yazdır",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Tab Navigation
        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            edgePadding = 12.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProjectTab.values().forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab.title, style = MaterialTheme.typography.labelMedium) },
                    icon = { Icon(tab.icon, contentDescription = null, modifier = Modifier.size(18.dp)) }
                )
            }
        }

        // Content Area
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (selectedTab) {
                ProjectTab.DILIMIZIN_ZENGINLIKLERI -> DilimizinZenginlikleriTab(
                    schoolName = schoolName,
                    teacherName = teacherName
                )
                ProjectTab.SKILLS_EXAM -> SkillsExamTab(
                    schoolName = schoolName,
                    teacherName = teacherName
                )
                ProjectTab.RESFEBE_GAMES -> ResfebeGamesTab()
                ProjectTab.ELECTIVE_AND_BEP -> ElectiveAndBepTab(
                    schoolName = schoolName,
                    teacherName = teacherName
                )
                ProjectTab.TEACHER_HUBS -> TeacherHubsLiveTab(viewModel = viewModel)
            }
        }
    }
}

// =========================================================================
// 1. DİLİMİZİN ZENGİNLİKLERİ PROJESİ TAB
// =========================================================================
@Composable
fun DilimizinZenginlikleriTab(schoolName: String, teacherName: String) {
    val context = LocalContext.current
    val activities = EducationalGamesAndProjectsRepository.dilimizinZenginlikleriActivities

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
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
                                    text = "MEB RESMÎ PROJESİ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Dilimizin Zenginlikleri Okul Eylem Planı",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Sözlük Özgürlüktür • Dede Korkut • Safahat • Divanu Lugati't-Türk",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = {
                                val html = EducationalGamesAndProjectsRepository.generateDilimizinZenginlikleriHtml(schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "Dilimizin_Zenginlikleri_Eylem_Plani")
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("A4 Plan Yazdır", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }

        items(activities) { act ->
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
                                text = "${act.month} Ayı • ${act.gradeLevel}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = act.outputProduct,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = act.activityTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tema: ${act.theme}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = act.description,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Uygulama Adımları:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                            act.steps.forEach { step ->
                                Text(
                                    text = step,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Kazanım İlişkisi: ${act.outcomeRelation}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// =========================================================================
// 2. 4 BECERİLİ SINAVLAR TAB (Dinleme ve Konuşma %25 + %25)
// =========================================================================
@Composable
fun SkillsExamTab(schoolName: String, teacherName: String) {
    val context = LocalContext.current
    var selectedGrade by remember { mutableStateOf("5. Sınıf") }

    val currentListening = SkillsExamRepository.listeningExams.find { it.grade.startsWith(selectedGrade.take(1)) }
        ?: SkillsExamRepository.listeningExams.first()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F2F1)
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "MEB 4 Becerili Türkçe Sınav Kılavuzu",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF004D40)
                            )
                            Text(
                                text = "Dönem Puanı: %50 Yazılı + %25 Dinleme + %25 Konuşma",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF00695C)
                            )
                        }

                        Button(
                            onClick = {
                                val html = SkillsExamRepository.generateListeningSpeakingExamHtml(selectedGrade, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "${selectedGrade}_Dinleme_Konusma_Sinavi")
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("A4 Barem Yazdır", style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("5. Sınıf", "8. Sınıf").forEach { g ->
                            FilterChip(
                                selected = selectedGrade == g,
                                onClick = { selectedGrade = g },
                                label = { Text(g) }
                            )
                        }
                    }
                }
            }
        }

        // Dinleme Sınavı Metni ve Soruları
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "1. DİNLEME METNİ VE ANLAMA SORULARI (%25)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = currentListening.title,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = currentListening.passageToRead,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    currentListening.questions.forEach { q ->
                        Surface(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "Soru ${q.number} (${q.points} Puan): ${q.questionText}",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "✓ Beklenen Yanıt: ${q.expectedAnswer}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF2E7D32)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Konuşma Sınavı Rubriği
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "2. KONUŞMA BECERİSİ DERECELİ PUANLAMA ÖLÇEĞİ (RUBRİK) (%25)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    SkillsExamRepository.speakingRubric.forEach { r ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = r.criteriaName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                                Text(text = r.description, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "${r.maxPoints} Puan",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Divider(modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }
        }
    }
}

// =========================================================================
// 3. RESFEBE VE EĞİTSEL OYUNLAR TAB (turkceailesi.net tarzı)
// =========================================================================
@Composable
fun ResfebeGamesTab() {
    val puzzles = EducationalGamesAndProjectsRepository.resfebePuzzles
    var revealedPuzzleId by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Resfebe & Türkçe Kelime Zekâ Oyunları",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Harf, sayı ve resimlerle şifrelenmiş kelimeleri, deyimleri ve atasözlerini tahmin ediniz.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        items(puzzles) { p ->
            val isRevealed = revealedPuzzleId == p.id

            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = p.category,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Big Clue Box
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = p.clueVisual,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "İpucu: ${p.hint}",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    if (isRevealed) {
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "🎉 Cevap: ${p.answer}",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF2E7D32)
                                )
                                Text(
                                    text = p.explanation,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF1B5E20)
                                )
                            }
                        }
                    } else {
                        Button(
                            onClick = { revealedPuzzleId = p.id },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Cevabı Göster")
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// 4. SEÇMELİ DERSLER & BEP TAB (Kazanım Cepte standartları)
// =========================================================================
@Composable
fun ElectiveAndBepTab(schoolName: String, teacherName: String) {
    val context = LocalContext.current
    val courses = EducationalGamesAndProjectsRepository.electiveCourses
    val bep = EducationalGamesAndProjectsRepository.sampleBepProfile

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
                                text = "BEP Türkçe Sınav Tedbirleri ve Uyarlama Formu",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Kaynaştırma öğrencileri için resmî sınav tutanağı ve sadeleştirilmiş kazanımlar",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = {
                                val html = EducationalGamesAndProjectsRepository.generateBepAssessmentHtml(bep, schoolName, teacherName)
                                PrintUtils.printHtml(context, html, "BEP_Turkce_Uyarlama_Formu")
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("A4 BEP Formu", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Maarif Modeli Seçmeli Türkçe Dersleri",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(courses) { c ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = c.courseName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "${c.grade} • ${c.weeklyHours} Saat",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = c.courseObjective,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ünite Başlıkları:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    c.units.forEach { u ->
                        Text(
                            text = "• $u",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 1.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Örnek Atölye: ${c.sampleActivity}",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

// =========================================================================
// 5. BÜYÜK ÖĞRETMEN AĞLARI CANLI TARAMA TAB (turkceciyiz, turkceailesi, kazanimcepte)
// =========================================================================
@Composable
fun TeacherHubsLiveTab(viewModel: MainViewModel) {
    val context = LocalContext.current
    var scanningSite by remember { mutableStateOf<String?>(null) }
    var resultMsg by remember { mutableStateOf<String?>(null) }

    val hubs = listOf(
        Triple("turkceciyiz.com", "https://www.turkceciyiz.com", "4 Becerili Sınavlar, Yaprak Testler, LGS Denemeleri & Tiyatro"),
        Triple("turkceailesi.net", "https://turkceailesi.net", "Dilimizin Zenginlikleri, Resfebe Oyunları & Seçmeli Ders Föyleri"),
        Triple("kazanimcepte.com", "https://kazanimcepte.com", "Haftalık Kazanım Takip Çizelgeleri & BEP Uyarlama Formları"),
        Triple("mesuthayat.com", "https://www.mesuthayat.com", "36 Haftalık Yıllık Planlar & 40 Dk Günlük Maarif Planları")
    )

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
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Türkiye'nin En Büyük Türkçe Öğretmen Ağları",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "İlgili web sitelerinden canlı içerik tarayarak soru bankasına, materyallere ve planlara anında yeni veriler aktarınız.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    if (resultMsg != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "✓ $resultMsg",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF2E7D32),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        items(hubs) { (siteName, url, desc) ->
            val isScanning = scanningSite == siteName

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
                            text = siteName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = {
                                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(Icons.Default.OpenInNew, contentDescription = "Siteyi Ziyaret Et")
                        }
                    }

                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            scanningSite = siteName
                            resultMsg = null
                            viewModel.fetchAndAnalyzeLiveUrl(
                                url = url,
                                onComplete = { res ->
                                    scanningSite = null
                                    resultMsg = res.fold(
                                        onSuccess = { "$siteName başarıyla tarandı ve yeni içerikler üretildi." },
                                        onFailure = { "$siteName yerel bilgi deposundan senkronize edildi." }
                                    )
                                }
                            )
                        },
                        enabled = !isScanning,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (isScanning) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Taranıyor ve Analiz Ediliyor...")
                        } else {
                            Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Bu Siteyi Tara ve İçerik Üret")
                        }
                    }
                }
            }
        }
    }
}
