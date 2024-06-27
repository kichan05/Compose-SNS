package dev.kichan.sns.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kichan.sns.model.data.Chat
import dev.kichan.sns.ui.theme.SnsTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatMessageItem(chat: Chat) {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = chat.message,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color(0xFFB4B4B4), RoundedCornerShape(12.dp))
                .padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview() {
    SnsTheme {
        ChatMessageItem(
            chat = Chat(
                senderEmail = "ckstmznf@naver.com",
                receiverEmail = "ckstmznf111@naver.com",
                message = "안녕",
                timestamp = Date()
            )
        )
    }
}

@Composable
fun MyChatItem(chat: Chat) {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = chat.message,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color(0xff00ff00), RoundedCornerShape(12.dp))
                .padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyChatItemPreview() {
    SnsTheme {
        MyChatItem(
            chat = Chat(
                senderEmail = "ckstmznf@naver.com",
                receiverEmail = "ckstmznf111@naver.com",
                message = "안녕",
                timestamp = Date()
            )
        )
    }
}
