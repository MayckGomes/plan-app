package mayckgomes.com.planapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mayckgomes.com.planapp.ui.screens.CreateScreen
import mayckgomes.com.planapp.ui.screens.EditScreen
import mayckgomes.com.planapp.ui.screens.HomeScreen
import mayckgomes.com.planapp.ui.screens.ViewScreen
import mayckgomes.com.planapp.ui.screens.Welcome.WelcomeMain
import mayckgomes.com.planapp.ui.theme.PlanAppTheme
import mayckgomes.com.planapp.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanAppTheme {

                val viewmodel = viewModel<UserViewModel>()

                WindowInsetsControllerCompat(window, LocalView.current).isAppearanceLightStatusBars = true

                val isFirstTime = viewmodel.VerifyFirstTime(this)

                Navigation(isFirstTime)

            }
        }
    }
}

@Serializable
object Home

@Serializable
object View

@Serializable
object Create

@Serializable
object Edit

@Serializable
object Register

@Composable
fun Navigation(isFirstTime : Boolean){

    val navControler = rememberNavController()

    NavHost(navControler,
        startDestination =

            if(isFirstTime) {

                Register

            } else {

                Home

            },

        //enterTransition = {},
        builder = {

            composable<Home> {
                HomeScreen(navControler)
            }

            composable<Create>{
                CreateScreen(navControler)
            }

            composable<View> {
                ViewScreen(navControler)
            }

            composable<Edit> {
                EditScreen(navControler)
            }

            composable<Register>{
                WelcomeMain(navControler)
            }
        }

        )

}
