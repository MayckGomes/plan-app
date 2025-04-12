package mayckgomes.com.planapp.ui.screens.Welcome

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.R
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.UserViewModel

@Composable
fun WelcomeScreen2(pager: PagerState) {

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val viewmodel = viewModel<UserViewModel>()

    val userName by viewmodel.userName.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if (userName.isNotEmpty()){
                        scope.launch {

                            viewmodel.changeUserName(userName,context)
                            pager.scrollToPage(2)

                        }
                    } else {
                        Toast.makeText(context, "Escreva seu nome primeiro!", Toast.LENGTH_SHORT).show()
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
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(57.dp)
        ) {

            StyledText("Mas,\nPara começar,", fontSize = 24.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Como podemos te chamar?", fontSize = 24.sp)

            Spacer(Modifier.size(70.dp))

            Image(
                painter = painterResource(R.drawable.undraw_avatar_traveler_ljy2),
                contentDescription = "Girl",
                modifier = Modifier
                    .width(196.dp)
                    .height(198.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.size(20.dp))

            StyledText("Nome", fontSize = 16.sp)

            Spacer(Modifier.size(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(1f),
                value = userName,
                onValueChange = {viewmodel.userNameUpdate(it)},
                singleLine = true,
                placeholder = {StyledText("Digite seu Nome...", color = DarkGray, fontSize = 12.sp)},
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = Black, focusedTextColor = Black),
            )
        }

    }

}