package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuizRepository
import com.example.localization.LocalAppLanguage
import com.example.localization.localizedFormat
import com.example.localization.localizedString
import com.example.model.QuizDifficulty
import com.example.ui.components.CodeBlockView
import com.example.ui.components.IosGroupedCard
import com.example.ui.components.IosHairlineDivider
import com.example.ui.components.IosIconSquircle
import com.example.ui.components.MorsalinButton
import com.example.ui.theme.IosCyan
import com.example.ui.theme.IosGreen
import com.example.ui.theme.IosOrange
import com.example.ui.theme.IosPink
import com.example.ui.theme.IosPurple
import com.example.ui.theme.LocalIosPalette
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun QuizArenaScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val palette = LocalIosPalette.current
    val lang = LocalAppLanguage.current
    val total = QuizRepository.questions.size
    val currentIdx = uiState.activeQuizIndex.coerceIn(0, total - 1)
    val question = QuizRepository.questions[currentIdx]
    val selectedOption = uiState.quizSelectedOption
    val isSubmitted = uiState.isQuizAnswerSubmitted
    val isCorrect = isSubmitted && selectedOption == question.correctIndex

    val difficultyTint = when (question.difficulty) {
        QuizDifficulty.EASY -> IosGreen
        QuizDifficulty.MEDIUM -> IosCyan
        QuizDifficulty.HARD -> IosOrange
        QuizDifficulty.EXPERT -> IosPink
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(palette.groupedBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 14.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Cupertino Quiz Header & Score Bar
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IosIconSquircle(
                                    icon = Icons.Default.Psychology,
                                    tint = IosPink
                                )
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = localizedString("nav_quiz"),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = palette.textPrimary
                                    )
                                    Text(
                                        text = localizedString("quiz_subtitle"),
                                        fontSize = 12.sp,
                                        color = palette.textSecondary,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                            Spacer(Modifier.width(8.dp))
                            Surface(
                                color = palette.primaryBlue.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = palette.primaryBlue,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text(
                                        text = localizedFormat("xp_unit", uiState.userProgress.totalXp),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = palette.primaryBlue
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = localizedFormat("question_of_fmt", currentIdx + 1, total),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = palette.textSecondary
                            )
                            Text(
                                text = localizedFormat(
                                    "score_summary_fmt",
                                    uiState.correctQuizzesCount,
                                    uiState.totalQuizzesAnswered
                                ),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = IosGreen
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        LinearProgressIndicator(
                            progress = { (currentIdx + 1).toFloat() / total.coerceAtLeast(1) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = IosPink,
                            trackColor = palette.pillBackground
                        )
                    }
                }
            }

            // Question Card
            item {
                IosGroupedCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = difficultyTint.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = localizedString(question.difficulty.labelKey),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = difficultyTint,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }

                            Surface(
                                color = IosPurple.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = localizedString(question.type.labelKey),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = IosPurple,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        Text(
                            text = question.questionContent.get(lang),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = palette.textPrimary,
                            lineHeight = 23.sp
                        )

                        if (question.codeSnippet != null) {
                            Spacer(Modifier.height(14.dp))
                            CodeBlockView(
                                code = question.codeSnippet,
                                languageTag = "SMALI",
                                showLineNumbers = false
                            )
                        }
                    }
                }
            }

            // Answer Choices
            items(question.optionsContent.size) { optionIndex ->
                val optionText = question.optionsContent[optionIndex].get(lang)
                val isSelected = selectedOption == optionIndex
                val isOptionCorrect = optionIndex == question.correctIndex

                val borderColor = when {
                    !isSubmitted && isSelected -> palette.primaryBlue
                    isSubmitted && isOptionCorrect -> IosGreen
                    isSubmitted && isSelected && !isOptionCorrect -> IosPink
                    else -> palette.separator
                }

                val bgColor = when {
                    !isSubmitted && isSelected -> palette.primaryBlue.copy(alpha = 0.12f)
                    isSubmitted && isOptionCorrect -> IosGreen.copy(alpha = 0.14f)
                    isSubmitted && isSelected && !isOptionCorrect -> IosPink.copy(alpha = 0.14f)
                    else -> palette.card
                }

                val circleBg = when {
                    isSubmitted && isOptionCorrect -> IosGreen
                    isSubmitted && isSelected && !isOptionCorrect -> IosPink
                    isSelected -> palette.primaryBlue
                    else -> palette.pillBackground
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 56.dp)
                        .border(1.dp, borderColor, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(enabled = !isSubmitted) { viewModel.selectQuizOption(optionIndex) }
                        .testTag("quiz_option_$optionIndex"),
                    color = bgColor,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(circleBg, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                isSubmitted && isOptionCorrect -> {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                isSubmitted && isSelected && !isOptionCorrect -> {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                else -> {
                                    Text(
                                        text = ('A' + optionIndex).toString(),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else palette.textSecondary
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.width(12.dp))

                        Text(
                            text = optionText,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected || (isSubmitted && isOptionCorrect)) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Medium
                            },
                            color = palette.textPrimary,
                            lineHeight = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Submit or Next Button
            item {
                if (!isSubmitted) {
                    MorsalinButton(
                        text = localizedString("submit_answer"),
                        onClick = { viewModel.submitQuizAnswer() },
                        enabled = selectedOption != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_submit_button")
                    )
                } else {
                    MorsalinButton(
                        text = localizedString("next_challenge"),
                        onClick = { viewModel.nextQuizQuestion() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_next_button")
                    )
                }
            }

            // Detailed Explanation & Trap Analysis Card
            if (isSubmitted) {
                item {
                    val resultTint = if (isCorrect) IosGreen else IosPink
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, resultTint.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
                        color = palette.cardElevated,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(resultTint.copy(alpha = 0.18f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = resultTint,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = if (isCorrect) {
                                        localizedString("quiz_correct_banner")
                                    } else {
                                        localizedString("quiz_wrong_banner")
                                    },
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = resultTint
                                )
                            }

                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = question.explanationContent.get(lang),
                                fontSize = 14.sp,
                                color = palette.textPrimary,
                                lineHeight = 20.sp
                            )

                            Spacer(Modifier.height(12.dp))
                            IosHairlineDivider()
                            Spacer(Modifier.height(10.dp))

                            Text(
                                text = localizedFormat(
                                    "trap_analysis_prefix",
                                    question.whyIncorrectContent.get(lang)
                                ),
                                fontSize = 13.sp,
                                color = palette.textSecondary,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
