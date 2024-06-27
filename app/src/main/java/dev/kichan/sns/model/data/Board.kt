package dev.kichan.sns.model.data

import java.util.Date

data class Board(
    val title: String = "",
    val writer: String = "",
    val content: String = "",
    val timestamp: Date = Date(),
)