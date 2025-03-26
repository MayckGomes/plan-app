package mayckgomes.com.planapp.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.viewmodel.compose.viewModel
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.CreateViewmodel
import java.nio.file.WatchEvent

@Preview(showSystemUi = true)
@Composable
fun CreateScreen(){

    val viewmodel: CreateViewmodel = viewModel()

    val isExpanded by viewmodel.isExpanded.collectAsState()

    val month by viewmodel.month.collectAsState(0)

    val monthNumber by viewmodel.monthNumber.collectAsState()

    val dayList by viewmodel.dayList.collectAsState()

    val dayNumber by viewmodel.dayNumber.collectAsState()

    var text by rememberSaveable {
        mutableStateOf("")
    }

    var loading by rememberSaveable { mutableStateOf(false) }

    var isChoosed by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(30.dp, 40.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            IconButton(
                onClick = {TODO("voltar")},
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
                   modifier = Modifier.fillMaxSize()
               ) {

                   StyledText(month.toString())

                   Spacer(Modifier.size(40.dp))

                   IconButton(
                       onClick = {
                       viewmodel.openMenu()
                   }) {
                       Icon(Icons.Default.ArrowDropDown, contentDescription = "dropDownMenu")
                   }

                   DropdownMenu(
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

                    loading = true
                    isChoosed = true
                    viewmodel.GetDaysOfMonth(monthNumber)
                    
                }

            }
        ) {
            StyledText("Selecionar")
        }

        Spacer(Modifier.size(20.dp))

        Box(modifier = Modifier.fillMaxSize(1f)){

           Column(
               modifier = Modifier.fillMaxSize(1f)
           ) {

               Box(
                   modifier = Modifier
                       .width(351.dp)
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

                       if(!isChoosed){
                           Text("Selecione um Mês")
                       } else {
                          Text(dayList[dayNumber])
                       }

                       IconButton(
                           onClick = {
                               if(isChoosed && dayNumber != dayList.size){ viewmodel.nextDay() }
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
                                .width(285.dp)
                                .height(47.dp),
                            value = text,
                            onValueChange = {text = it},
                            label = {StyledText("Digite uma Descrição...", color= DarkGray, fontSize = 14.sp)},
                            shape = RoundedCornerShape(12.dp)
                        )

                       Spacer(Modifier.size(17.dp))

                       OutlinedButton(
                           modifier = Modifier.fillMaxWidth(),
                           shape = RoundedCornerShape(12.dp),
                           onClick = {

                           }
                       ) {
                           StyledText("Salvar")
                       }

                   }

               }



           }

       }

    }

}