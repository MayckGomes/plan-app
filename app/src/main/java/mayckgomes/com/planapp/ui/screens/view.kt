package mayckgomes.com.planapp.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mayckgomes.com.planapp.database.dayDatabase
import mayckgomes.com.planapp.ui.elements.CardPlan
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.viewmodels.ViewViewmodel

@Composable
fun ViewScreen(navController: NavController){

    val viewmodel: ViewViewmodel = viewModel()

    val dateList by viewmodel.list.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.GetAllDates(context)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .border(border = BorderStroke(1.dp,Black), shape = RoundedCornerShape(25.dp))) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            Spacer(Modifier.size(13.dp))

            StyledText("Planejamento Atual", fontSize = 24.sp)

        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(start = 25.dp,end = 25.dp, top = 25.dp)) {

            items(dateList){day ->

                CardPlan(day.day, day.text, bgColor = Gray, dateColor = Black)
                Spacer(Modifier.size(10.dp))
            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun ViewScreenPreview(){
    ViewScreen(rememberNavController())
}