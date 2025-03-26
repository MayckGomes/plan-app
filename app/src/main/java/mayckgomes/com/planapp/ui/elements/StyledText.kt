package mayckgomes.com.planapp.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import mayckgomes.com.planapp.ui.theme.Black
import mayckgomes.com.planapp.ui.theme.poppins

@Composable
fun StyledText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Black,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = FontWeight.SemiBold,
){
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = poppins
    )
}