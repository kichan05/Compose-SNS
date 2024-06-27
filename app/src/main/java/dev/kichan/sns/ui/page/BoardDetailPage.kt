package dev.kichan.sns.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.sns.model.data.Board
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.Page
import dev.kichan.sns.ui.theme.SnsTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BoardDetailPage(navController: NavController, vm: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = vm.selectBoard!!.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "작성자: ${vm.selectBoard!!.writer}",
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                navController.navigate(Page.CHAT.name + "/${vm.selectBoard!!.writer}")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = vm.selectBoard!!.content, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(8.dp))

        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        Text(
            text = dateFormat.format(vm.selectBoard!!.timestamp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardDetailPagePreview() {
    SnsTheme {
        BoardDetailPage(
            navController = rememberNavController(),
            vm = MainViewModel().apply {
                selectBoard =
                    Board("제ㅔㅔ목", "ckstmznf@naver.com", "대충 게시물", Date())
            })
    }
}