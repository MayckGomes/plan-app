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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mayckgomes.com.planapp.ui.screens.CreateScreen
import mayckgomes.com.planapp.ui.screens.HomeScreen
import mayckgomes.com.planapp.ui.theme.PlanAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanAppTheme {

                Navigation()

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

@Composable
fun Navigation(){

    val navControler = rememberNavController()

    NavHost(navControler, startDestination = Home,
        //enterTransition = {},
        builder = {

            composable<Home> {
                HomeScreen(navControler)
            }

            composable<Create>{
                CreateScreen(navControler)
            }

        }

        )

}
