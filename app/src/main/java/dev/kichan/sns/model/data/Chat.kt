package dev.kichan.sns.model.data

import java.util.Date

data class Chat(
    val senderEmail: String = "",
    val receiverEmail: String = "",
    val message: String = "",
    val timestamp: Date = Date()
)