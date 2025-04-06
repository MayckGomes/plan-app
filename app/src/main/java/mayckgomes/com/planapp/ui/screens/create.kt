package mayckgomes.com.planapp.ui.screens

import android.R
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.ui.elements.CardPlan
import mayckgomes.com.planapp.ui.elements.AlertDialogApp
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.CreateViewmodel


@Composable
fun CreateScreen(navController: NavController){

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val viewmodel: CreateViewmodel = viewModel()

    val isExpanded by viewmodel.isExpanded.collectAsState()

    val month by viewmodel.monthName.collectAsState(0)

    val monthNumber by viewmodel.monthNumber.collectAsState()

    val dayList by viewmodel.dayList.collectAsState()

    val dayNumber by viewmodel.day.collectAsState()

    val daySavedList by viewmodel.daysSaved.collectAsState()

    val isLoading by viewmodel.isLoading.collectAsState()

    val isChoosed by viewmodel.isChoosed.collectAsState()

    val isClick by viewmodel.isClick.collectAsState()

    val isClickBack by viewmodel.isClickBack.collectAsState()

    val isFull by viewmodel.isFull.collectAsState()

    var text by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(30.dp, 40.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically

        ){

            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .border(border = BorderStroke(1.dp,Black), shape = RoundedCornerShape(25.dp))) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            Spacer(Modifier.size(13.dp))

            StyledText("Criar Planejamento", fontSize = 24.sp)

        }


        Spacer(Modifier.size(70.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {

            StyledText("Selecione o Mês:", fontSize = 16.sp, fontWeight = FontWeight.Normal)

            Spacer(Modifier.size(23.dp))

            Box(
                modifier = Modifier
                    .width(144.dp)
                    .height(42.dp)
                    .border(1.dp, color = Black, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {

               Row(
                   horizontalArrangement = Arrangement.SpaceBetween,
                   modifier = Modifier.fillMaxSize(1f)
               ) {

                   StyledText(month.toString())


                   IconButton(
                       onClick = {
                       viewmodel.openMenu()
                   }) {
                       Icon(Icons.Default.ArrowDropDown, contentDescription = "dropDownMenu")
                   }

                   DropdownMenu(
                       containerColor = White,
                       expanded = isExpanded ,
                       onDismissRequest = {
                           viewmodel.closeMenu()
                       }
                   ) {

                       viewmodel.monthList.fastForEachIndexed{ index, item->

                           DropdownMenuItem(
                               text = {StyledText(item)},
                               onClick = {
                                   viewmodel.changeMonth(index)
                                   viewmodel.closeMenu()
                               }
                           )

                       }

                   }

               }

            }

        }

        Spacer(Modifier.size(20.dp))

        OutlinedButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 0.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {

                if (monthNumber != 0){

                    scope.launch{
                        viewmodel.isLoadingTrue()
                        viewmodel.isChoosedTrue()
                        viewmodel.GetDaysOfMonth(monthNumber)
                        viewmodel.backAllDays()
                        viewmodel.isLoadingFalse()
                    }
                    
                } else {
                    Toast.makeText(context,"Não é possivel selecionar essa opção", Toast.LENGTH_LONG).show()
                }

            }
        ) {
            StyledText("Selecionar")
        }

        Spacer(Modifier.size(20.dp))

        Box(modifier = Modifier.fillMaxSize(1f)){

            if (isLoading){

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            } else {

                Column(
                    modifier = Modifier.fillMaxSize(1f)
                ) {

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
                                    if(isChoosed && dayNumber != 0){ viewmodel.backDay() }
                                }
                            ) {
                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "back date")
                            }

                            if(isChoosed == false){

                                Text("Selecione um Mês")
                                viewmodel.isFullTrue()

                            } else {

                                if (dayList.isEmpty()){

                                    Text("Todas as datas foram selecionadas")
                                    viewmodel.isFullTrue()

                                } else {

                                    Text(dayList[dayNumber])
                                    viewmodel.isFullFalse()
                                }
                            }

                            IconButton(
                                onClick = {
                                    if(isChoosed && dayNumber + 1 <= dayList.size - 1){ viewmodel.nextDay() }

                                }
                            ) {
                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "next date")
                            }
                        }

                    }

                    Spacer(Modifier.size(33.dp))

                    Box(){

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 43.dp)) {

                            StyledText("Descrição", fontWeight = FontWeight.Normal)


                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(1f),
                                value = text,
                                onValueChange = {text = it},
                                singleLine = true,
                                placeholder = {StyledText("Digite uma descrição...", color = DarkGray, fontSize = 12.sp)},
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = Black, focusedTextColor = Black),
                            )

                            Spacer(Modifier.size(17.dp))

                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                enabled = !isFull,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    disabledContentColor = DarkGray
                                ),
                                onClick = {
                                    if(text.isEmpty()){

                                        Toast.makeText(context,"Digite uma descrição primeiro!", Toast.LENGTH_LONG).show()

                                    } else {

                                        viewmodel.addDay(day = Day(day = dayList[dayNumber], text = text))
                                        viewmodel.backAllDays()
                                        text = ""

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

                        if (daySavedList.isEmpty()){

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
                                items(daySavedList){ day ->

                                    CardPlan(day.day,day.text)
                                    Button(
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = White),
                                        onClick = {viewmodel.delDay(day)},
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        StyledText("Deletar")
                                    }

                                    Spacer(Modifier.size(17.dp))
                                }
                            }

                        }

                    }

                    Spacer(Modifier.size(20.dp))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            viewmodel.isClickTrue()
                        }
                    ) {
                        StyledText("Salvar")
                    }

                }

            }

       }

        BackHandler {

            viewmodel.isClickBackTrue()

        }

        if (isClickBack){



            AlertDialogApp(
                title = "Atenção",
                text = " Deseja voltar?, o conteúdo não será salvo!",
                onDimiss = {viewmodel.isClickBackFalse()},
                onConfirm = {navController.popBackStack()}
            )

        }

        if (isClick){

            AlertDialogApp(
                title = "Atenção",
                text = " Deseja mesmo salvar?, o conteúdo salvo anteriormente será perdido!",
                onDimiss = {viewmodel.isClickFalse()},
                onConfirm = {
                    viewmodel.Save(context)
                    navController.popBackStack()
                }
            )



        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun CreateScreenPreview(){
    CreateScreen(rememberNavController())
}