package mayckgomes.com.planapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mayckgomes.com.planapp.Create
import mayckgomes.com.planapp.Edit
import mayckgomes.com.planapp.R
import mayckgomes.com.planapp.View
import mayckgomes.com.planapp.ui.elements.CardPlan
import mayckgomes.com.planapp.ui.elements.InputDialogApp
import mayckgomes.com.planapp.ui.elements.StyledText
import mayckgomes.com.planapp.ui.elements.UpdateNameDialogApp
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.ShowViewmodel
import mayckgomes.com.planapp.viewmodels.UserViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController){

    val showViewmodel: ShowViewmodel = viewModel()

    val userViewmodel: UserViewModel = viewModel()

    val userName by userViewmodel.userName.collectAsState()

    val dateList by showViewmodel.list.collectAsState()

    val context = LocalContext.current

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    var isFirstTime by rememberSaveable {
        mutableStateOf(false)
    }

    var isChangeName by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        if (userViewmodel.VerifyFirstTime(context)){

            isFirstTime = true

        }
        userViewmodel.GetUserName(context)
        showViewmodel.GetAllDates(context)
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
                modifier = Modifier
                    .combinedClickable(
                        onClick = {
                            Toast.makeText(context, "Pressione e segure para mudar o nome!", Toast.LENGTH_LONG).show()
                        },
                        onLongClick = {
                            isChangeName = true
                        }
                    )
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
                            onClick = {navController.navigate(View)},
                            modifier = Modifier
                                .padding(0.dp)
                                .size(24.dp)
                                .clip(RoundedCornerShape(8))
                                .background(color = White)
                            ) {
                            Icon(painterResource(R.drawable.eye), contentDescription = "See", tint = Black)
                        }
                        Spacer(Modifier.size(18.dp))

                        IconButton(
                            onClick = {navController.navigate(Edit)},
                            modifier = Modifier
                                .padding(0.dp)
                                .size(24.dp)
                                .clip(RoundedCornerShape(8))
                                .background(color = White)
                            ){
                            Icon(
                                Icons.Default.Edit, contentDescription = "Edit", tint = Black
                            )
                        }

                    }


                    if (isLoading){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            CircularProgressIndicator(color = Black)

                        }

                    } else {

                        if (dateList.isEmpty()){

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                StyledText("Ainda não há datas Salvas!\nToque em Novo e salve agora!", color = Black)
                            }

                        } else {

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 15.dp)
                            ) {

                                items(dateList){ day ->

                                    CardPlan(day.day,day.text)
                                    Spacer(Modifier.size(10.dp))
                                }

                            }

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
                onClick = {
                    navController.navigate(Create)}
            )
        }


        if (isFirstTime){

            InputDialogApp(onClick = {isFirstTime = false})

            userViewmodel.SetFalseFirstTime(context)

        }
        if (isChangeName){

            UpdateNameDialogApp(
                onClick = {
                    isChangeName = false
                    Toast.makeText(context, "Nome trocado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}