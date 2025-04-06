package mayckgomes.com.planapp.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.Gray
import mayckgomes.com.planapp.ui.theme.White
import mayckgomes.com.planapp.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNameDialogApp(
    onClick: () -> Unit
){

    val viewmodel: UserViewModel = viewModel()

    val context = LocalContext.current

    var textinput by rememberSaveable { mutableStateOf("") }

    BasicAlertDialog(

        onDismissRequest = {},
        content = {

            Card(
                colors = CardDefaults.cardColors(containerColor = White),
                modifier = Modifier
                    .height(300.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {

                    StyledText("Digite seu novo nome!", fontSize = 20.sp)

                    Spacer(Modifier.size(20.dp))

                    StyledText(
                        text = "Nome",
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.Start))

                    Spacer(Modifier.size(5.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .width(285.dp),
                        value = textinput,
                        onValueChange = {textinput = it},
                        singleLine = true,
                        placeholder = {StyledText("Digite seu novo nome...", color = Gray, fontSize = 12.sp)},
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = Black, focusedTextColor = Black),
                    )
                    
                    Spacer(Modifier.size(20.dp))

                    OutlinedButton(
                        modifier = Modifier.width(130.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = White, contentColor = Black),
                        onClick = {
                            viewmodel.changeUserName(textinput, context)
                            onClick()
                        }
                    ) {
                        Text("Confirmar")
                    }
                }
            }

        }
    )

}

@Preview
@Composable
fun UpdateNameDialogAppPreview(){
    UpdateNameDialogApp(onClick = {})
}