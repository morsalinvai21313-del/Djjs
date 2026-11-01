package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedString
import com.example.ui.components.LanguagePickerDialog
import com.example.ui.screens.CodeComparisonScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.InstructionDictionaryScreen
import com.example.ui.screens.PlaygroundScreen
import com.example.ui.screens.QuizArenaScreen
import com.example.ui.screens.RegisterVisualizerScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentRose
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.NavTab
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

data class BottomBarItem(
    val tab: NavTab,
    val titleKey: String,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    private val viewModel: SmaliViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            MyApplicationTheme {
                CompositionLocalProvider(
                    LocalAppLanguage provides uiState.language,
                    LocalLayoutDirection provides uiState.language.layoutDirection
                ) {
                    // Back handler: Close lesson first if open, or return to Dashboard
                    BackHandler(enabled = uiState.activeLesson != null || uiState.currentTab != NavTab.DASHBOARD) {
                        if (uiState.activeLesson != null) {
                            viewModel.closeLesson()
                        } else {
                            viewModel.selectTab(NavTab.DASHBOARD)
                        }
                    }

                    MainAppScaffold(
                        uiState = uiState,
                        viewModel = viewModel
                    )

                    // First-Launch & On-Demand Language Picker Modal
                    if (uiState.showLanguageDialog) {
                        LanguagePickerDialog(
                            selectedLanguage = uiState.language,
                            onSelectLanguage = { viewModel.setLanguage(it) },
                            onDismiss = { viewModel.dismissLanguageDialog() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainAppScaffold(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val navItems = listOf(
        BottomBarItem(NavTab.DASHBOARD, "nav_home", Icons.Default.Dashboard),
        BottomBarItem(NavTab.CURRICULUM, "nav_curriculum", Icons.Default.MenuBook),
        BottomBarItem(NavTab.STUDIO, "nav_compare", Icons.Default.Code),
        BottomBarItem(NavTab.VISUALIZER, "nav_visualizer", Icons.Default.Storage),
        BottomBarItem(NavTab.DICTIONARY, "nav_dictionary", Icons.Default.Terminal),
        BottomBarItem(NavTab.PLAYGROUND, "nav_playground", Icons.Default.AutoAwesome),
        BottomBarItem(NavTab.QUIZ, "nav_quiz", Icons.Default.Psychology),
        BottomBarItem(NavTab.SETTINGS, "nav_settings", Icons.Default.Settings)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.morsalin_bg_xml),
                contentScale = ContentScale.FillBounds
            ),
        containerColor = Color.Transparent,
        topBar = {
            TopAcademyBar(uiState = uiState, viewModel = viewModel)
        },
        bottomBar = {
            AcademyBottomNav(
                currentTab = uiState.currentTab,
                navItems = navItems,
                onSelectTab = { viewModel.selectTab(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentTab) {
                NavTab.DASHBOARD -> DashboardScreen(uiState = uiState, viewModel = viewModel)
                NavTab.CURRICULUM -> CurriculumScreen(uiState = uiState, viewModel = viewModel)
                NavTab.STUDIO -> CodeComparisonScreen(uiState = uiState, viewModel = viewModel)
                NavTab.VISUALIZER -> RegisterVisualizerScreen(uiState = uiState, viewModel = viewModel)
                NavTab.DICTIONARY -> InstructionDictionaryScreen(uiState = uiState, viewModel = viewModel)
                NavTab.PLAYGROUND -> PlaygroundScreen(uiState = uiState, viewModel = viewModel)
                NavTab.QUIZ -> QuizArenaScreen(uiState = uiState, viewModel = viewModel)
                NavTab.SETTINGS -> SettingsScreen(uiState = uiState, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TopAcademyBar(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    Surface(
        color = DevSurface,
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .border(1.dp, DevSurfaceBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Branding & Logo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(AccentCyan.copy(alpha = 0.15f), CircleShape)
                        .border(1.dp, AccentCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "S",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCyan,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = localizedString("app_title"),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "ACADEMY",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentCyan,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Streak & Language Switcher
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak Pill
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = DevSurfaceVariant,
                    modifier = Modifier.border(1.dp, DevSurfaceBorder, RoundedCornerShape(16.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = null,
                            tint = AccentAmber,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(3.dp))
                        Text(
                            text = "${uiState.userProgress.currentStreak}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentAmber
                        )
                    }
                }

                // Language Button
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = DevSurfaceVariant,
                    modifier = Modifier
                        .border(1.dp, AccentCyan.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                        .clickable { viewModel.showLanguageDialog() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = uiState.language.flag, fontSize = 14.sp)
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = uiState.language.nativeName.take(6),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AcademyBottomNav(
    currentTab: NavTab,
    navItems: List<BottomBarItem>,
    onSelectTab: (NavTab) -> Unit
) {
    Surface(
        color = DevSurface,
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .border(1.dp, DevSurfaceBorder)
    ) {
        ScrollableTabRow(
            selectedTabIndex = navItems.indexOfFirst { it.tab == currentTab }.coerceAtLeast(0),
            containerColor = DevSurface,
            contentColor = AccentCyan,
            edgePadding = 8.dp,
            indicator = { tabPositions ->
                val index = navItems.indexOfFirst { it.tab == currentTab }.coerceAtLeast(0)
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[index]),
                    color = AccentCyan,
                    height = 2.5.dp
                )
            }
        ) {
            navItems.forEach { item ->
                val isSelected = item.tab == currentTab
                Tab(
                    selected = isSelected,
                    onClick = { onSelectTab(item.tab) },
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = localizedString(item.titleKey),
                                tint = if (isSelected) AccentCyan else TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.height(3.dp))
                            Text(
                                text = localizedString(item.titleKey),
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) AccentCyan else TextSecondary
                            )
                        }
                    }
                )
            }
        }
    }
}
