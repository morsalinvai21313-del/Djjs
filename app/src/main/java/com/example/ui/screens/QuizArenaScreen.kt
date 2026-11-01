package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuizRepository
import com.example.localization.localizedString
import com.example.model.QuizDifficulty
import com.example.ui.components.CodeBlockView
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentRose
import com.example.ui.theme.DevBackground
import com.example.ui.theme.DevSurface
import com.example.ui.theme.DevSurfaceBorder
import com.example.ui.theme.DevSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.SmaliUiState
import com.example.viewmodel.SmaliViewModel

@Composable
fun QuizArenaScreen(
    uiState: SmaliUiState,
    viewModel: SmaliViewModel,
    modifier: Modifier = Modifier
) {
    val total = QuizRepository.questions.size
    val currentIdx = uiState.activeQuizIndex.coerceIn(0, total - 1)
    val question = QuizRepository.questions[currentIdx]
    val selectedOption = uiState.quizSelectedOption
    val isSubmitted = uiState.isQuizAnswerSubmitted
    val isCorrect = isSubmitted && selectedOption == question.correctIndex

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = DevSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(AccentRose.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Psychology,
                                    contentDescription = null,
                                    tint = AccentRose,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = localizedString("nav_quiz"),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }

                        Surface(
                            color = AccentCyan.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = AccentCyan,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "${uiState.userProgress.totalXp} XP",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AccentCyan
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Question ${currentIdx + 1} of $total",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary
                        )
                        Text(
                            text = "${uiState.correctQuizzesCount} Correct / ${uiState.totalQuizzesAnswered} Solved",
                            fontSize = 12.sp,
                            color = AccentEmerald,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { (currentIdx + 1).toFloat() / total },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = AccentRose,
                        trackColor = DevSurfaceBorder
                    )
                }
            }
        }

        // Question Details Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DevSurfaceBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = DevSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = when (question.difficulty) {
                                QuizDifficulty.EASY -> AccentEmerald.copy(alpha = 0.15f)
                                QuizDifficulty.MEDIUM -> AccentCyan.copy(alpha = 0.15f)
                                QuizDifficulty.HARD -> AccentAmber.copy(alpha = 0.15f)
                                QuizDifficulty.EXPERT -> AccentRose.copy(alpha = 0.15f)
                            },
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = question.difficulty.name,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (question.difficulty) {
                                    QuizDifficulty.EASY -> AccentEmerald
                                    QuizDifficulty.MEDIUM -> AccentCyan
                                    QuizDifficulty.HARD -> AccentAmber
                                    QuizDifficulty.EXPERT -> AccentRose
                                },
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Text(
                            text = question.type.name.replace("_", " "),
                            fontSize = 10.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = question.question,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        lineHeight = 21.sp
                    )

                    if (question.codeSnippet != null) {
                        Spacer(Modifier.height(12.dp))
                        CodeBlockView(code = question.codeSnippet, languageTag = "SMALI", showLineNumbers = false)
                    }
                }
            }
        }

        // Options List
        items(question.options.size) { optionIndex ->
            val optionText = question.options[optionIndex]
            val isSelected = selectedOption == optionIndex
            val isOptionCorrect = optionIndex == question.correctIndex

            val borderColor = when {
                !isSubmitted && isSelected -> AccentCyan
                isSubmitted && isOptionCorrect -> AccentEmerald
                isSubmitted && isSelected && !isOptionCorrect -> AccentRose
                else -> DevSurfaceBorder
            }

            val bgColor = when {
                !isSubmitted && isSelected -> AccentCyan.copy(alpha = 0.1f)
                isSubmitted && isOptionCorrect -> AccentEmerald.copy(alpha = 0.15f)
                isSubmitted && isSelected && !isOptionCorrect -> AccentRose.copy(alpha = 0.15f)
                else -> DevSurface
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                    .clickable(enabled = !isSubmitted) { viewModel.selectQuizOption(optionIndex) },
                colors = CardDefaults.cardColors(containerColor = bgColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(
                                when {
                                    isSubmitted && isOptionCorrect -> AccentEmerald
                                    isSubmitted && isSelected && !isOptionCorrect -> AccentRose
                                    isSelected -> AccentCyan
                                    else -> DevSurfaceBorder
                                },
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSubmitted && isOptionCorrect) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                        } else if (isSubmitted && isSelected && !isOptionCorrect) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        } else {
                            Text(
                                text = ('A' + optionIndex).toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.Black else TextSecondary
                            )
                        }
                    }

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = optionText,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected || (isSubmitted && isOptionCorrect)) FontWeight.Bold else FontWeight.Normal,
                        color = TextPrimary,
                        lineHeight = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Action Button: Submit or Next Question
        item {
            if (!isSubmitted) {
                com.example.ui.components.MorsalinButton(
                    onClick = { viewModel.submitQuizAnswer() },
                    enabled = selectedOption != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Submit Answer", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White)
                }
            } else {
                com.example.ui.components.MorsalinButton(
                    onClick = { viewModel.nextQuizQuestion() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Next Challenge →", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White)
                }
            }
        }

        // Detailed Explanation Card
        if (isSubmitted) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            if (isCorrect) AccentEmerald.copy(alpha = 0.4f) else AccentRose.copy(alpha = 0.4f),
                            RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = DevSurfaceVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                contentDescription = null,
                                tint = if (isCorrect) AccentEmerald else AccentRose,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (isCorrect) "Correct! +25 XP" else "Incorrect (+5 XP for attempt)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) AccentEmerald else AccentRose
                            )
                        }

                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = question.explanation,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp
                        )

                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "💡 Trap Analysis: ${question.whyIncorrect}",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}
