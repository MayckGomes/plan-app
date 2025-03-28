package mayckgomes.com.planapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mayckgomes.com.planapp.ui.theme.DarkGray
import mayckgomes.com.planapp.ui.theme.White

@Composable
fun CardPlan(
    date: String,
    text: String,
    bgColor: Color = White,
    dateColor: Color = DarkGray
){

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(68.dp)
            .clip(RoundedCornerShape(8))
            .background(color = bgColor)
            .padding(9.dp,4.dp)
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
        ) {

            StyledText(date, color = dateColor)

            Spacer(Modifier.size(6.dp))

            HorizontalDivider()

            Spacer(Modifier.size(6.dp))

            StyledText(text)

        }

    }

}

@Preview
@Composable
fun CardPlanPreview(){
    CardPlan(date = "22/03 - Sabado", text = "teste")
}
