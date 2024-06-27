package dev.kichan.sns.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.kichan.sns.MyApp
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.Page
import dev.kichan.sns.ui.component.BoardItem
import dev.kichan.sns.ui.theme.SnsTheme

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    SnsTheme {
        MyApp(MainViewModel())
    }
}

@Composable
fun MainPage(navController: NavController, vm: MainViewModel) {
    LaunchedEffect(Unit) {
        vm.getBoard()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Page.WRITE.name)
            }) {
                Text("글쓰기")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome, ${vm.currentUser!!.userName}", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                vm.logout()
                navController.navigate(Page.LOGIN.name)
            }) {
                Text("로그아웃")
            }


            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(vm.boardList) {
                    BoardItem(it) {
                        vm.selectBoard = it
                        navController.navigate(Page.BOARD_DETAIL.name)
                    }
                }
            }
        }
    }
}