package mayckgomes.com.planapp.ui.screens.Welcome

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.UserViewModel

@Composable
fun WelcomeScreen3(pager: PagerState) {

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val viewmodel = viewModel<UserViewModel>()

    val userName = viewmodel.userName.collectAsState().value

    val domingo = viewmodel.domingo.collectAsState().value

    val segunda = viewmodel.segunda.collectAsState().value

    val terca = viewmodel.terca.collectAsState().value

    val quarta = viewmodel.quarta.collectAsState().value

    val quinta = viewmodel.quinta.collectAsState().value

    val sexta = viewmodel.sexta.collectAsState().value

    val sabado = viewmodel.sabado.collectAsState().value


    Scaffold(
        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {

                        if (domingo || segunda || terca || quarta || quinta || sexta || sabado){
                            viewmodel.setDays(context)
                        } else {
                            Toast.makeText(context, "Selecione pelo menos um dia!", Toast.LENGTH_SHORT).show()
                        }

                        pager.scrollToPage(3)

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
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 57.dp, horizontal = 55.dp)
        ) {

            StyledText("Olá, $userName!", fontSize = 24.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Para finalizarmos,\nMostre quais dias da semana você\nbusca organizar.", fontSize = 16.sp)

            Spacer(Modifier.size(40.dp))

            StyledText("Isso poderá ser editado\nposteriormente, Não se preocupe.", fontSize = 16.sp)

            Spacer(Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = domingo, onCheckedChange = {viewmodel.domingoChange(it)})

                StyledText("Domingo", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = segunda, onCheckedChange = {viewmodel.segundaChange(it)})

                StyledText("Segunda", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = terca, onCheckedChange = {viewmodel.tercaChange(it)})

                StyledText("Terça", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = quarta, onCheckedChange = {viewmodel.quartaChange(it)})

                StyledText("Quarta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = quinta, onCheckedChange = {viewmodel.quintaChange(it)})

                StyledText("Quinta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = sexta, onCheckedChange = {viewmodel.sextaChange(it)})

                StyledText("Sexta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(checked = sabado, onCheckedChange = {viewmodel.sabadoChange(it)})

                StyledText("Sábado", fontSize = 16.sp)

            }


        }

    }
}