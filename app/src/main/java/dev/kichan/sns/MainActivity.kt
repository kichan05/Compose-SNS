package dev.kichan.sns

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.Page
import dev.kichan.sns.ui.page.BoardDetailPage
import dev.kichan.sns.ui.page.ChatPage
import dev.kichan.sns.ui.page.HomePage
import dev.kichan.sns.ui.page.LoginPage
import dev.kichan.sns.ui.page.MainPage
import dev.kichan.sns.ui.page.SignUpPage
import dev.kichan.sns.ui.page.WritePage
import dev.kichan.sns.ui.theme.SnsTheme

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp(vm)
        }
    }
}

@Composable
fun MyApp(vm: MainViewModel) {
    SnsTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Page.HOME.name) {
            composable(Page.HOME.name) { HomePage(navController) }
            composable(Page.SIGN_UP.name) { SignUpPage(navController, vm) }
            composable(Page.LOGIN.name) { LoginPage(navController, vm) }
            composable(Page.MAIN.name) { MainPage(navController, vm) }
            composable(Page.WRITE.name) { WritePage(navController, vm) }
            composable(Page.BOARD_DETAIL.name) { BoardDetailPage(navController, vm) }
            composable(Page.CHAT.name + "/{receiverEmail}") {
                val receiverEmail = it.arguments?.getString("receiverEmail") ?: ""
                ChatPage(navController, vm, receiverEmail)
            }
        }
    }
}