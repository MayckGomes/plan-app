package mayckgomes.com.planapp.ui.elements

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import androidx.sqlite.db.SupportSQLiteOpenHelper
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogApp(
    title: String,
    text: String,
    onDimiss: () -> Unit,
    onConfirm: @Composable () -> Unit
){

    var isClick by rememberSaveable {
        mutableStateOf(false)
    }

    BasicAlertDialog(

        onDismissRequest = { onDimiss() },
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
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                ) {

                    Icon(Icons.Default.Warning, contentDescription = null, tint = Black)

                    Spacer(Modifier.size(20.dp))

                    StyledText(title, fontSize = 20.sp)

                    Spacer(Modifier.size(20.dp))

                    StyledText(text, fontWeight = FontWeight.Normal)

                    Spacer(Modifier.size(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(1f)
                    ) {

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White),
                            onClick = {onDimiss()}) {
                            Text("Não")
                        }

                        OutlinedButton(
                            colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Black),
                            onClick = {isClick = true}) {
                            Text("Sim")
                        }
                    }
                }
            }

        }
    )

    if (isClick){

        isClick = false

        onConfirm()
    }

}

@Preview
@Composable
fun dialogPreview(){
    DialogApp(
    title = "Atenção",
    text = " Deseja mesmo salvar?, o conteúdo salvo anteriormente será perdido!",
    onDimiss = {},
    onConfirm = {}
    )
}