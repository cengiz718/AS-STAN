package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainViewModel
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme

enum class AppScreen(val title: String, val icon: ImageVector) {
    DASHBOARD("Genel Bakış", Icons.Default.Dashboard),
    GRADE_MODULES("5-8. Sınıf & Plan", Icons.Default.Class),
    TEACHER_NEWS("Haber & Piyasa", Icons.Default.Newspaper),
    DOCUMENTS("Tutanak & Evrak", Icons.Default.FolderShared),
    EXAM("Sınav Stüdyosu", Icons.Default.Print),
    PROJECTS("Projeler & Oyun", Icons.Default.AutoAwesome),
    WEB_PORTALS("Web & AI", Icons.Default.Public),
    GRAMMAR("Dilbilgisi", Icons.Default.Spellcheck),
    VOCABULARY("Kelime", Icons.Default.Translate),
    SPECIAL_DAYS("Belirli Günler", Icons.Default.CalendarToday),
    CURRICULUM("MEB Plan & Ölçek", Icons.Default.Grading)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: MainViewModel = viewModel()
                var currentScreen by remember { mutableStateOf(AppScreen.DASHBOARD) }
                var selectedGradeTarget by remember { mutableStateOf("5. Sınıf") }

                // Handle back button to return to dashboard when on other screens
                if (currentScreen != AppScreen.DASHBOARD) {
                    BackHandler {
                        currentScreen = AppScreen.DASHBOARD
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeDrawing,
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("main_navigation_bar"),
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp
                        ) {
                            val bottomNavItems = listOf(
                                AppScreen.DASHBOARD,
                                AppScreen.GRADE_MODULES,
                                AppScreen.TEACHER_NEWS,
                                AppScreen.DOCUMENTS,
                                AppScreen.EXAM
                            )

                            bottomNavItems.forEach { screen ->
                                val isSelected = currentScreen == screen
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = { currentScreen = screen },
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.title
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = screen.title,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    },
                                    alwaysShowLabel = true,
                                    modifier = Modifier.testTag("nav_item_${screen.name.lowercase()}")
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
                        when (currentScreen) {
                            AppScreen.DASHBOARD -> DashboardScreen(
                                viewModel = viewModel,
                                onNavigateToGradeModules = { grade ->
                                    if (grade != null) selectedGradeTarget = grade
                                    currentScreen = AppScreen.GRADE_MODULES
                                },
                                onNavigateToProjects = { currentScreen = AppScreen.PROJECTS },
                                onNavigateToGrammar = { currentScreen = AppScreen.GRAMMAR },
                                onNavigateToVocab = { currentScreen = AppScreen.VOCABULARY },
                                onNavigateToExam = { currentScreen = AppScreen.EXAM },
                                onNavigateToDocuments = { currentScreen = AppScreen.DOCUMENTS },
                                onNavigateToWebMaterials = { currentScreen = AppScreen.WEB_PORTALS },
                                onNavigateToSpecialDays = { currentScreen = AppScreen.SPECIAL_DAYS },
                                onNavigateToCurriculum = { currentScreen = AppScreen.CURRICULUM },
                                onNavigateToNews = { currentScreen = AppScreen.TEACHER_NEWS }
                            )
                            AppScreen.GRADE_MODULES -> GradeLevelHubScreen(
                                viewModel = viewModel,
                                initialGrade = selectedGradeTarget
                            )
                            AppScreen.TEACHER_NEWS -> TeacherNewsScreen()
                            AppScreen.PROJECTS -> EducationalGamesAndProjectsScreen(viewModel = viewModel)
                            AppScreen.EXAM -> ExamBuilderScreen(viewModel = viewModel)
                            AppScreen.DOCUMENTS -> SchoolDocumentsScreen()
                            AppScreen.WEB_PORTALS -> WebPortalDirectoryScreen(viewModel = viewModel)
                            AppScreen.GRAMMAR -> GrammarScreen(viewModel = viewModel)
                            AppScreen.VOCABULARY -> VocabularyScreen(viewModel = viewModel)
                            AppScreen.SPECIAL_DAYS -> SpecialDaysScreen()
                            AppScreen.CURRICULUM -> CurriculumAndRubricScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
