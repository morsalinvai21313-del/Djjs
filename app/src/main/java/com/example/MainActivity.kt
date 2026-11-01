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
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedString
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.LanguagePickerDialog
import com.example.ui.screens.CodeComparisonScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.InstructionDictionaryScreen
import com.example.ui.screens.PlaygroundScreen
import com.example.ui.screens.QuizArenaScreen
import com.example.ui.screens.RegisterVisualizerScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.CodeMonoFontFamily
import com.example.ui.theme.IosBlue
import com.example.ui.theme.IosIndigo
import com.example.ui.theme.LocalIosPalette
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.NavTab
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

data class CupertinoTabItem(
    val tab: NavTab,
    val titleKey: String,
    val icon: ImageVector,
    val matches: Set<NavTab> = setOf(tab)
)

class MainActivity : ComponentActivity() {
    private val viewModel: SmaliViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            MyApplicationTheme(
                darkTheme = uiState.isDarkTheme,
                language = uiState.language
            ) {
                CompositionLocalProvider(
                    LocalAppLanguage provides uiState.language,
                    LocalLayoutDirection provides uiState.language.layoutDirection
                ) {
                    BackHandler(
                        enabled = uiState.activeLesson != null || uiState.currentTab != NavTab.DASHBOARD
                    ) {
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
    val palette = LocalIosPalette.current

    val navItems = listOf(
        CupertinoTabItem(
            tab = NavTab.DASHBOARD,
            titleKey = "nav_home",
            icon = Icons.Default.Home
        ),
        CupertinoTabItem(
            tab = NavTab.CURRICULUM,
            titleKey = "nav_curriculum",
            icon = Icons.Default.MenuBook
        ),
        CupertinoTabItem(
            tab = NavTab.DICTIONARY,
            titleKey = "nav_dictionary",
            icon = Icons.Default.Terminal
        ),
        CupertinoTabItem(
            tab = NavTab.STUDIO,
            titleKey = "nav_lab",
            icon = Icons.Default.Science,
            matches = setOf(NavTab.STUDIO, NavTab.VISUALIZER, NavTab.PLAYGROUND)
        ),
        CupertinoTabItem(
            tab = NavTab.QUIZ,
            titleKey = "nav_quiz",
            icon = Icons.Default.Psychology
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        containerColor = palette.groupedBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CupertinoTopBar(uiState = uiState, viewModel = viewModel)
        },
        bottomBar = {
            CupertinoBottomDock(
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
                NavTab.DICTIONARY -> InstructionDictionaryScreen(uiState = uiState, viewModel = viewModel)
                NavTab.STUDIO -> CodeComparisonScreen(uiState = uiState, viewModel = viewModel)
                NavTab.VISUALIZER -> RegisterVisualizerScreen(uiState = uiState, viewModel = viewModel)
                NavTab.PLAYGROUND -> PlaygroundScreen(uiState = uiState, viewModel = viewModel)
                NavTab.QUIZ -> QuizArenaScreen(uiState = uiState, viewModel = viewModel)
                NavTab.SETTINGS -> SettingsScreen(uiState = uiState, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CupertinoTopBar(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel
) {
    val palette = LocalIosPalette.current

    Surface(
        color = palette.glassBar,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Brand Emblem + Localized Title
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.selectTab(NavTab.DASHBOARD) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(
                                Brush.linearGradient(listOf(IosBlue, IosIndigo))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "{S}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = CodeMonoFontFamily
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            text = localizedString("app_title"),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = localizedString("app_subtitle_badge"),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = palette.primaryBlue,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Right Controls: Language Pill + Theme Toggle + Settings
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = palette.pillBackground,
                        modifier = Modifier
                            .defaultMinSize(minHeight = 40.dp)
                            .border(0.8.dp, palette.separator, CircleShape)
                            .clip(CircleShape)
                            .clickable { viewModel.showLanguageDialog() }
                            .testTag("top_language_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 11.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = uiState.language.flag, fontSize = 14.sp)
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = uiState.language.nativeName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.textPrimary,
                                maxLines = 1
                            )
                        }
                    }

                    IconButton(
                        onClick = { viewModel.toggleDarkTheme(!uiState.isDarkTheme) },
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("top_theme_toggle")
                    ) {
                        Icon(
                            imageVector = if (uiState.isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = localizedString("settings_theme_title"),
                            tint = palette.textSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = { viewModel.selectTab(NavTab.SETTINGS) },
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("top_settings_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = localizedString("nav_settings"),
                            tint = if (uiState.currentTab == NavTab.SETTINGS) palette.primaryBlue else palette.textSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            IosHairlineDivider()
        }
    }
}

@Composable
fun CupertinoBottomDock(
    currentTab: NavTab,
    navItems: List<CupertinoTabItem>,
    onSelectTab: (NavTab) -> Unit
) {
    val palette = LocalIosPalette.current

    Surface(
        color = palette.glassBar,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            IosHairlineDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEach { item ->
                    val isSelected = item.matches.contains(currentTab)
                    val tint = if (isSelected) palette.primaryBlue else palette.textMuted

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .defaultMinSize(minHeight = 50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onSelectTab(item.tab) }
                            .padding(vertical = 4.dp)
                            .testTag("bottom_nav_${item.tab.name.lowercase()}"),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isSelected) palette.primaryBlue.copy(alpha = 0.14f)
                                    else Color.Transparent
                                )
                                .padding(horizontal = 12.dp, vertical = 3.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = localizedString(item.titleKey),
                                tint = tint,
                                modifier = Modifier.size(21.dp)
                            )
                        }
                        Spacer(Modifier.height(3.dp))
                        Text(
                            text = localizedString(item.titleKey),
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = tint,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
