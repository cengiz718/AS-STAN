package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.web.FinanceAndPrayerRepository
import com.example.ui.MainViewModel

@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    onNavigateToGradeModules: (String?) -> Unit,
    onNavigateToProjects: () -> Unit,
    onNavigateToGrammar: () -> Unit,
    onNavigateToVocab: () -> Unit,
    onNavigateToExam: () -> Unit,
    onNavigateToDocuments: () -> Unit,
    onNavigateToWebMaterials: () -> Unit,
    onNavigateToSpecialDays: () -> Unit,
    onNavigateToCurriculum: () -> Unit,
    onNavigateToNews: () -> Unit = {}
) {
    val questions by viewModel.questions.collectAsState()
    val lessonPlans by viewModel.lessonPlans.collectAsState()
    val readingRecords by viewModel.readingRecords.collectAsState()
    val prayerTimes by FinanceAndPrayerRepository.prayerTimes.collectAsState()
    val currencies by FinanceAndPrayerRepository.currencies.collectAsState()
    val randomWord by viewModel.currentRandomWord.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("dashboard_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Hero Visual Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hero_banner_turkce_1793101182464),
                    contentDescription = "Türkçe Öğretmeni Asistanı Kitaplık ve Çalışma Masası",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background.copy(alpha = 0.85f),
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )

                // Welcome Badge & Title
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "BAŞÖĞRETMEN ZEYNEP AKDAĞ",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Emniyet Ortaokulu • Adıyaman",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        // Live Widget Bar: Adıyaman Namaz Vakitleri & Canlı Piyasa Ticker
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onNavigateToNews() },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF004D40) // Adıyaman Zümrüt Yeşili
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Mosque,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Adıyaman Merkez: ${prayerTimes.currentPrayerName}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Surface(
                            color = Color(0xFFFFD54F),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = prayerTimes.remainingTimeText,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B5E20),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.White.copy(alpha = 0.2f))
                    Spacer(modifier = Modifier.height(8.dp))

                    // Mini Ticker: Dolar, Euro, Altın
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        currencies.take(3).forEach { cur ->
                            Column {
                                Text(
                                    text = cur.name.substringBefore(" "),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                                Text(
                                    text = "${String.format(java.util.Locale.US, "%.2f", cur.sellingPrice)} ₺",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Canlı Haber & Piyasa",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFFFD54F),
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        // Daily Wisdom Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "“Türkçem, benim ses bayrağım!”",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Fazıl Hüsnü Dağlarca • Dilimiz kimliğimiz ve bağımsızlığımızdır.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        // Quick Stats Counters
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Soru Bankası",
                    count = "${questions.size}",
                    subtitle = "Açık Uçlu & Test",
                    icon = Icons.Default.Quiz,
                    color = MaterialTheme.colorScheme.primary,
                    onClick = onNavigateToExam
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Ders Planı",
                    count = "${lessonPlans.size}",
                    subtitle = "MEB Planı",
                    icon = Icons.Default.MenuBook,
                    color = MaterialTheme.colorScheme.secondary,
                    onClick = onNavigateToCurriculum
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Kitap Takibi",
                    count = "${readingRecords.size}",
                    subtitle = "Öğrenci Kaydı",
                    icon = Icons.Default.Bookmarks,
                    color = MaterialTheme.colorScheme.tertiary,
                    onClick = onNavigateToCurriculum
                )
            }
        }

        // Quick Grade Level Selector (5, 6, 7, 8. Sınıf)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sınıf Düzeyleri ve Planlar (5-8)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { onNavigateToGradeModules(null) }) {
                        Text("Tümünü Gör", style = MaterialTheme.typography.labelSmall)
                        Icon(Icons.Default.ChevronRight, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("5. Sınıf", "6. Sınıf", "7. Sınıf", "8. Sınıf").forEach { grade ->
                        val isLgs = grade == "8. Sınıf"
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onNavigateToGradeModules(grade) },
                            colors = CardDefaults.cardColors(
                                containerColor = if (isLgs) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 10.dp, horizontal = 4.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = if (isLgs) "8. SINIF" else grade.uppercase(),
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isLgs) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = if (isLgs) "LGS Odaklı" else "MEB Uyumlu",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isLgs) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Word of the Day Highlight
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onNavigateToVocab() },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                ),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
                        )
                    )
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "GÜNÜN KELİMESİ & KELİME DAĞARCIĞI",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        SuggestionChip(
                            onClick = { viewModel.nextRandomWord() },
                            label = { Text("Yeni Kelime", style = MaterialTheme.typography.labelSmall) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Casino,
                                    contentDescription = "Rastgele Kelime",
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = randomWord.word.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = randomWord.definition,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Eş: ${randomWord.synonyms.joinToString(", ")}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        if (randomWord.antonyms.isNotEmpty()) {
                            Surface(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Zıt: ${randomWord.antonyms.joinToString(", ")}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section: All Modules
        item {
            Text(
                text = "Öğretmen Araçları ve Modüller",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // 0.0 Öğretmenler & Türkçe Dersi Canlı Haberleri, Adıyaman Namaz ve Canlı Piyasa
                ModuleActionCard(
                    title = "Öğretmenler & Türkçe Dersi Canlı Haberleri",
                    subtitle = "MEB son dakika duyuruları, ÖMK, ek ders katsayıları, Adıyaman namaz vakitleri, canlı dolar, euro ve altın piyasası",
                    icon = Icons.Default.Newspaper,
                    color = Color(0xFF00695C),
                    tag = "Canlı Haber • Adıyaman Namaz • Piyasa",
                    onClick = onNavigateToNews
                )

                // 0.01 turkcen.net Tutanak Oluşturma Araçları (37+ MEB Tutanak & Dilekçe)
                ModuleActionCard(
                    title = "turkcen.net Tutanak Oluşturma Araçları",
                    subtitle = "Zümre toplantı tutanağı, idare dilekçesi, ŞÖK öğrenci tablosu, veli toplantısı ve kesilebilir davetiye kuponu",
                    icon = Icons.Default.Description,
                    color = Color(0xFFB71C1C),
                    tag = "turkcen.net • Zümre & ŞÖK & Veli • A4",
                    onClick = onNavigateToDocuments
                )

                // 0. 5, 6, 7 ve 8. Sınıf Gelişmiş Modüller (Yıllık/Günlük Planlar, Sınavlar, Kitaplar, Kazanım Testleri)
                ModuleActionCard(
                    title = "5, 6, 7 ve 8. Sınıf Gelişmiş Modüller",
                    subtitle = "MEB ünitelendirilmiş yıllık planları, 40 dk günlük planlar, açık uçlu sınav soruları, ders kitapları (8 tema) ve konu kazanım testleri",
                    icon = Icons.Default.Class,
                    color = Color(0xFF0D47A1),
                    tag = "5-8. Sınıf • Yıllık & Günlük Plan • A4",
                    onClick = { onNavigateToGradeModules(null) }
                )

                // 0.1 Mesut Hayat Portalı ve Canlı Web Taraması
                ModuleActionCard(
                    title = "mesuthayat.com Portalı & Canlı Web Taraması",
                    subtitle = "Mesut Hayat Türkçe arşivi, güncel web sayfalarını tarayıp sürekli taze soru, föy ve plan üretme motoru",
                    icon = Icons.Default.Language,
                    color = Color(0xFF00695C),
                    tag = "mesuthayat.com • Canlı AI Taraması",
                    onClick = { onNavigateToGradeModules(null) }
                )

                // 0.2 Dilimizin Zenginlikleri, 4 Becerili Sınav & Resfebe (turkceciyiz.com & turkceailesi.net)
                ModuleActionCard(
                    title = "Dilimizin Zenginlikleri, 4 Becerili Sınav & Resfebe",
                    subtitle = "MEB resmî eylem planı, %25 dinleme ve %25 konuşma sınavı rubriği, Resfebe zeka oyunları ve BEP formu",
                    icon = Icons.Default.AutoAwesome,
                    color = Color(0xFF6A1B9A),
                    tag = "MEB Projesi • Dinleme/Konuşma • Resfebe",
                    onClick = onNavigateToProjects
                )

                // 1. Profesyonel Sınav Hazırlama
                ModuleActionCard(
                    title = "Profesyonel Sınav Stüdyosu",
                    subtitle = "MEB yeni açık uçlu & test soruları, 2 sütunlu mizanpaj, A/B kitapçığı, not baremi ve A4 çıktısı",
                    icon = Icons.Default.Print,
                    color = MaterialTheme.colorScheme.primary,
                    tag = "A4 Yazdırılabilir • A/B Kitapçığı",
                    onClick = onNavigateToExam
                )

                // 2. Okul Evrakları Modülü
                ModuleActionCard(
                    title = "Türkçe Öğretmeni Okul Evrakları",
                    subtitle = "Zümre tutanakları, yazılı sınav analiz raporu, ŞÖK, kütüphane kulübü ve veli dilekçeleri",
                    icon = Icons.Default.FolderShared,
                    color = MaterialTheme.colorScheme.secondary,
                    tag = "Resmî MEB Evrakları",
                    onClick = onNavigateToDocuments
                )

                // 3. Türkçe Web Portalları & AI
                ModuleActionCard(
                    title = "Türkçe Web Portalları & AI",
                    subtitle = "TDK, MEB, EBA, TÜBİTAK ve edebiyat sitelerinde arama, canlı link çekimi ve soru üretimi",
                    icon = Icons.Default.Public,
                    color = MaterialTheme.colorScheme.tertiary,
                    tag = "Tüm Web Siteleri & AI",
                    onClick = onNavigateToWebMaterials
                )

                // 4. Dilbilgisi Kuralları
                ModuleActionCard(
                    title = "Türkçe Dilbilgisi Kuralları",
                    subtitle = "İsimler, Fiiller, Sıfatlar, Zarflar, Edatlar, Bağlaçlar, Noktalama ve Örnekler",
                    icon = Icons.Default.Spellcheck,
                    color = Color(0xFF1565C0),
                    tag = "Açıklamalı Kurallar",
                    onClick = onNavigateToGrammar
                )

                // 5. Kelime Bilgisi Geliştirme
                ModuleActionCard(
                    title = "Kelime Bilgisi Geliştirici",
                    subtitle = "Rastgele kelimeler, eş & zıt anlamlılar, cümle örnekleri ve interaktif bilgi testi",
                    icon = Icons.Default.Translate,
                    color = Color(0xFFE65100),
                    tag = "Kelime Hazinesi",
                    onClick = onNavigateToVocab
                )

                // 6. Belirli Gün ve Haftalar
                ModuleActionCard(
                    title = "Belirli Gün ve Haftalar (Pano & Tören)",
                    subtitle = "26 Eylül Dil Bayramı, 29 Ekim, 10 Kasım, 12 Mart, 18 Mart; okul panosu ve tören konuşması çıktısı",
                    icon = Icons.Default.CalendarToday,
                    color = Color(0xFF2E7D32),
                    tag = "Pano Çıktısı Alınabilir",
                    onClick = onNavigateToSpecialDays
                )

                // 7. MEB Plan & Ölçek
                ModuleActionCard(
                    title = "MEB Planları, Rubrik & Okuma Takibi",
                    subtitle = "MEB kazanımları, haftalık ders planı, kompozisyon puanlama ölçeği ve 100 Temel Eser takibi",
                    icon = Icons.Default.Grading,
                    color = Color(0xFF6A1B9A),
                    tag = "Ders Planı & Rubrik",
                    onClick = onNavigateToCurriculum
                )
            }
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    count: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = count,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ModuleActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color.copy(alpha = 0.12f), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = color.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = color,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
