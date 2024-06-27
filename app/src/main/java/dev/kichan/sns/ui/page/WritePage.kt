package dev.kichan.sns.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.sns.model.data.Board
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.Page
import dev.kichan.sns.ui.theme.SnsTheme
import java.util.Date

@Composable
fun WritePage(navController: NavController, vm: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("게시물 작성", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("내용") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val board = Board(
                title = title,
                writer = vm.currentUser!!.email,
                content = content,
                timestamp = Date()
            )
            vm.addBoardPost(board,
                onSuccess = {
                    navController.navigate(Page.MAIN.name)
                },
                onError = {}
            )
        }) {
            Text("작성")
        }
    }
}

@Preview
@Composable
private fun WritePagePreview() {
    SnsTheme {
        WritePage(navController = rememberNavController(), vm = MainViewModel())
    }
}