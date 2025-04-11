package mayckgomes.com.planapp.ui.screens.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.R
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.White

@Composable
fun WelcomeScreen1(pager: PagerState) {

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {

                        pager.scrollToPage(1)

                    }
                },
                containerColor = White,
                contentColor = Black
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StyledText("Próximo")
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "next")
                }
            }
        }
    ) {padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(57.dp)
        ) {

            StyledText("Olá,\nSeja bem-vindo(a)", fontSize = 24.sp)

            Spacer(modifier = Modifier.size(40.dp))

            StyledText("Obrigado por escolher este app\npara organizar sua rotina!",fontSize = 16.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Estamos muito felizes com isso!", fontSize = 16.sp)

            Spacer(Modifier.size(72.dp))

            Image(
                painter = painterResource(R.drawable.undraw_calendar_8r6s),
                contentDescription = "calendar",
                modifier = Modifier
                    .width(222.dp)
                    .height(196.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

        }

    }
}
