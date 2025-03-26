package mayckgomes.com.planapp.ui.screens

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mayckgomes.com.planapp.R
import mayckgomes.com.planapp.ui.elements.CardPlan
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.ViewViewmodel

@Composable
fun HomeScreen(){

    val viewmodel = ViewViewmodel()
    
    val userName = viewmodel.GetUserName()

    val dateList: MutableList<String> = mutableListOf()

    LaunchedEffect(Unit) {

        dateList.addAll(viewmodel.GetAllDates())

    }

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
                .padding(padding)
                .padding(39.dp, 50.dp, 39.dp, 50.dp)
        ) {

            StyledText("Olá, $userName!",
                fontSize = 24.sp,
            )

            Spacer(Modifier.size(51.dp))

            // card
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Gray)
            ){

                Column(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(32.dp, 18.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StyledText(
                            "Último Planejamento:",
                            fontSize = 14.sp,
                        )

                        Spacer(Modifier.size(36.dp))

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(0.dp)
                                .size(24.dp)
                                .clip(RoundedCornerShape(8))
                                .background(color = White)
                            ) {
                            Icon(painterResource(R.drawable.eye), contentDescription = "See")
                        }
                        Spacer(Modifier.size(18.dp))

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(0.dp)
                                .size(24.dp)
                                .clip(RoundedCornerShape(8))
                                .background(color = White)
                            ){
                            Icon(
                                Icons.Default.Edit, contentDescription = "Edit"
                            )
                        }

                    }


                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(dateList){ key ->


                        }

                    }


                }

            }

            Spacer(Modifier.size(55.dp))

            ExtendedFloatingActionButton(
                modifier = Modifier.align(alignment = Alignment.End),
                containerColor = White,
                contentColor = Black,
                text = {Text("Novo")},
                icon = {Icon(Icons.Default.Add,contentDescription = "New")},
                onClick = {TODO()}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}