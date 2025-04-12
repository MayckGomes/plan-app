package mayckgomes.com.planapp.ui.screens.Welcome

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.UserViewModel

@Composable
fun ProfileScreen(navController: NavController) {

    val context = LocalContext.current

    val viewmodel: UserViewModel = viewModel()

    var userName = viewmodel.userName.collectAsState().value

    val domingo = viewmodel.domingo.collectAsState().value

    val segunda = viewmodel.segunda.collectAsState().value

    val terca = viewmodel.terca.collectAsState().value

    val quarta = viewmodel.quarta.collectAsState().value

    val quinta = viewmodel.quinta.collectAsState().value

    val sexta = viewmodel.sexta.collectAsState().value

    val sabado = viewmodel.sabado.collectAsState().value

    LaunchedEffect(Unit) {

        viewmodel.GetUserName(context)
        viewmodel.getDays(context)

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .systemBarsPadding()
            .padding(horizontal = 57.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(Modifier.size(60.dp))

        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Profile",
            modifier = Modifier.size(50.dp)
        )

        Spacer(Modifier.size(30.dp))

        StyledText(if (userName.isNotEmpty()){
            userName
        } else {
            "Você ainda não escolheu um nome!"
        }
            , fontSize = 24.sp)

        Spacer(Modifier.size(60.dp))

        StyledText(
            text = "Novo nome",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(1f),
            value = userName,
            onValueChange = { viewmodel.userNameUpdate(it) },
            singleLine = true,
            placeholder = { StyledText("Digite seu Nome...", color = DarkGray, fontSize = 12.sp) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Black,
                focusedTextColor = Black
            ),
        )

        Spacer(Modifier.size(40.dp))

        StyledText(
            text = "Dias Selecionados",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.align(Alignment.Start)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = domingo,
                    onCheckedChange = {viewmodel.domingoChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Domingo", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = segunda,
                    onCheckedChange = {viewmodel.segundaChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Segunda", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = terca,
                    onCheckedChange = {viewmodel.tercaChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )


                StyledText("Terça", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = quarta,
                    onCheckedChange = {viewmodel.quartaChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Quarta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = quinta,
                    onCheckedChange = {viewmodel.quintaChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Quinta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = sexta,
                    onCheckedChange = {viewmodel.sextaChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Sexta", fontSize = 16.sp)

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = sabado,
                    onCheckedChange = {viewmodel.sabadoChange(it)},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Black,
                        checkmarkColor = White
                    )
                )

                StyledText("Sábado", fontSize = 16.sp)

            }

        }

        Spacer(Modifier.size(25.dp))

        OutlinedButton(
            onClick = {

                if (domingo || segunda || terca || quarta || quinta || sexta || sabado || userName.isNotEmpty()){

                    CoroutineScope(Dispatchers.IO).launch{
                        viewmodel.changeUserName(userName, context)
                        viewmodel.setDays(context)
                    }
                    navController.popBackStack()

                }else if (userName.isEmpty()){

                    Toast.makeText(context, "Digite um nome", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "Selecione pelo menos um dia!", Toast.LENGTH_SHORT).show()
                }


            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            StyledText("Salvar")
        }

    }

}