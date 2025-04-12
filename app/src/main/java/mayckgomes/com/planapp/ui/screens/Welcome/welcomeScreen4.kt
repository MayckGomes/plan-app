package mayckgomes.com.planapp.ui.screens.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.Home
import mayckgomes.com.planapp.R
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.UserViewModel

@Composable
fun WelcomeScreen4(navController: NavController) {

    val scope = rememberCoroutineScope()

    val viewmodel = viewModel<UserViewModel>()

    val context = LocalContext.current

    val userName = viewmodel.userName.collectAsState().value

    Scaffold(
        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(Home)

                    scope.launch {
                        viewmodel.SetFalseFirstTime(context)
                    }
                },
                containerColor = White,
                contentColor = Black
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StyledText("Finalizar")
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "next")
                }
            }

        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(56.dp)
        ) {

            StyledText("Pronto, $userName!", fontSize = 24.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Agora, você pode usar o seu\nPlan App sem problemas!", fontSize = 16.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Toque em Finalizar e começe já a planjar sua rotina! ", fontSize = 16.sp)

            Spacer(Modifier.size(100.dp))

            Image(
                painter = painterResource(R.drawable.undraw_completed_0sqh),
                contentDescription = "complete",
                modifier = Modifier
                    .width(296.dp)
                    .height(189.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }


    }

}