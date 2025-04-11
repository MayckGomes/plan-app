package mayckgomes.com.planapp.ui.screens.Welcome

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeMain(navController: NavController = rememberNavController()) {

    val pages = rememberPagerState(pageCount = {4})

    HorizontalPager(pages, userScrollEnabled = false) {
        when(pages.currentPage){
            0 -> WelcomeScreen1(pages)
            1 -> WelcomeScreen2(pages)
            2 -> WelcomeScreen3(pages)
            3 -> WelcomeScreen4(navController)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun WelcomeMainPreview() {
    WelcomeMain()
}