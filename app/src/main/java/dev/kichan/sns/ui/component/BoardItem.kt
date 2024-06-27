package dev.kichan.sns.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kichan.sns.model.data.Board
import dev.kichan.sns.ui.theme.SnsTheme
import java.util.Date

@Composable
fun BoardItem(board: Board, onClick : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE4E4E4), RoundedCornerShape(12.dp))
            .padding(12.dp)
            .clickable { onClick() }
    ) {
        Text(text = board.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = board.timestamp.toString(), fontSize = 12.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardItemPreview() {
    SnsTheme {
        BoardItem(
            board = Board(
                title = "이것이 안드로이드다",
                writer = "ckstmznf@naver.com",
                content = "반갑습니다~~~",
                timestamp = Date()
            ),
            onClick = {}
        )
    }
}