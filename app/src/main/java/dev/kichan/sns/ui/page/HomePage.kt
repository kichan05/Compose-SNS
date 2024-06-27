package dev.kichan.sns.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.kichan.sns.ui.MainViewModel
import dev.kichan.sns.ui.Page
import dev.kichan.sns.ui.theme.SnsTheme

@Composable
fun HomePage(navComposable: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Column(Modifier.fillMaxWidth()) {
            Button(
                onClick = { navComposable.navigate(Page.LOGIN.name) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "로그인")
            }

            Button(
                onClick = { navComposable.navigate(Page.SIGN_UP.name) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    SnsTheme {
        HomePage(
            navComposable = rememberNavController(),
        )
    }
}

