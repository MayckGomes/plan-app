package mayckgomes.com.planapp.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.ui.elements.AlertDialogApp
import mayckgomes.com.planapp.ui.elements.CardPlan
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.EditViewmodel

@Composable
fun EditScreen(navController: NavController) {

    val context = LocalContext.current

    val viewmodel = viewModel<EditViewmodel>()

    val text by viewmodel.text.collectAsState()

    val dayList by viewmodel.dayList.collectAsState()

    val editDayList by viewmodel.editList.collectAsState()

    val day by viewmodel.day.collectAsState()

    val isLoading by viewmodel.isLoading.collectAsState()

    val isClickSave by viewmodel.isClickSave.collectAsState()

    val isClickBack by viewmodel.isClickBack.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.isLoadingTrue()
        viewmodel.getDays(context)
        viewmodel.isLoadingFalse()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(10.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            IconButton(
                onClick = {viewmodel.isClickBackTrue()},
                modifier = Modifier
                    .border(border = BorderStroke(1.dp,Black), shape = RoundedCornerShape(25.dp))) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            Spacer(Modifier.size(13.dp))

            StyledText("Editar Planejamento", fontSize = 24.sp)

        }

        Spacer(Modifier.size(25.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(39.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(color = Gray)
        ){

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize(1f)
            ){

                IconButton(
                    onClick = {
                        if (editDayList.size >= day - 1){viewmodel.minusDay()}
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "back date")
                }

                if (editDayList.isEmpty()){
                    Text("Todos os dias foram selecionados")
                } else {

                    Text(editDayList[day].day)

                }

                IconButton(
                    onClick = {
                        if(day + 1 <= editDayList.size - 1){ viewmodel.plusDay() }
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "next date")
                }
            }

        }

        Spacer(Modifier.size(33.dp))

        Box{

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp)) {

                StyledText("Descrição", fontWeight = FontWeight.Normal)


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    value = text,
                    onValueChange = {viewmodel.textUpdate(it)},
                    singleLine = true,
                    placeholder = {StyledText("Digite uma descrição...", color = DarkGray, fontSize = 12.sp)},
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = Black, focusedTextColor = Black),
                )

                Spacer(Modifier.size(17.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        disabledContentColor = DarkGray
                    ),
                    onClick = {
                        if(text.isEmpty()){

                            Toast.makeText(context,"Digite uma descrição primeiro!", Toast.LENGTH_LONG).show()

                        } else {

                            viewmodel.delDayEdit(editDayList[day])

                            viewmodel.addDayList(
                                Day(
                                    id = editDayList[day].id,
                                    day = editDayList[day].day,
                                    text = text
                                )
                            )

                            viewmodel.textClear()

                        }
                    }
                ) {
                    StyledText("Salvar")
                }

            }

        }

        Spacer(Modifier.size(52.dp))

        StyledText("Dias Salvos", fontSize = 24.sp)

        Spacer(Modifier.size(18.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray)
        ){

           if (isLoading){
               
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
               
           } else {

               if (dayList.isEmpty()){

                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .fillMaxSize()
                   ) {
                       StyledText("Ainda não há datas Salvas!", color = Black)
                   }

               } else {

                   LazyColumn(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(10.dp)
                   ) {
                       items(dayList){ day ->

                           CardPlan(day.day,day.text)
                           Button(
                               modifier = Modifier.fillMaxWidth(),
                               colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow, contentColor = White),
                               onClick = {

                                   viewmodel.delDayList(day)
                                   viewmodel.addDayEdit(day)

                               },
                               shape = RoundedCornerShape(8.dp)
                           ) {
                               StyledText("Editar")
                           }

                           Spacer(Modifier.size(17.dp))
                       }
                   }

               }

           }

        }

        Spacer(Modifier.size(20.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                viewmodel.isClickSaveTrue()
            }
        ) {
            StyledText("Salvar")
        }

    }

    if (isClickSave){

        AlertDialogApp(
            title = "Atenção!",
            text = "Deseja salvar?, os dados anteriores serão perdidos!",
            onDimiss = {viewmodel.isClickSaveFalse()},
            onConfirm = {
                viewmodel.save(context)
                viewmodel.isClickSaveFalse()
                navController.popBackStack()
            }
        )
    }

    if (isClickBack){

        AlertDialogApp(
            title = "Atenção!",
            text = "Deseja voltar?, os dados não salvos serão perdidos!",
            onDimiss = {viewmodel.isClickBackFalse()},
            onConfirm = {
                navController.popBackStack()
                viewmodel.isClickBackFalse()
            }
        )

    }

    BackHandler {
        viewmodel.isClickBackTrue()
    }

}

@Preview(showSystemUi = true)
@Composable
private fun EditScreenPreview() {
    EditScreen(rememberNavController())
}