package dev.kichan.sns.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.sns.model.data.Chat
import dev.kichan.sns.model.data.User
import dev.kichan.sns.ui.component.ChatMessageItem
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.component.MyChatItem
import dev.kichan.sns.ui.theme.SnsTheme
import java.util.Date

@Composable
fun ChatPage(navController: NavController, vm: MainViewModel, receiverEmail: String) {
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        vm.getChatMessages(senderEmail = vm.currentUser!!.email, receiverEmail = receiverEmail)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = receiverEmail, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(vm.chatList) {
                if(it.senderEmail == vm.currentUser!!.email) {
                    MyChatItem(chat = it)
                }
                else {
                    ChatMessageItem(it)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                label = { Text("Message") }
            )

            Button(onClick = {
                val chat = Chat(
                    senderEmail = vm.currentUser!!.email,
                    receiverEmail = receiverEmail,
                    message = message,
                    timestamp = Date()
                )
                vm.addChatMessage(
                    chat = chat,
                    onSuccess = {
                        message = ""
                        vm.getChatMessages(senderEmail = vm.currentUser!!.email, receiverEmail = receiverEmail)
                    },
                )
            }) {
                Text("Send")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPagePreview() {
    SnsTheme {
        ChatPage(navController = rememberNavController(), vm = MainViewModel().apply {
            currentUser = User("박희찬", "ckstmznf@naver.com")
            chatList = listOf(
                Chat("ckstmznf@naver.com", "ckstmznf111@naver.com", "안녕하세요", Date()),
                Chat("ckstmznf111@naver.com", "ckstmznf@naver.com", "반갑습니다.", Date())
            )
        }, receiverEmail = "other@user.com")
    }
}