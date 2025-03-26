package mayckgomes.com.planapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mayckgomes.com.planapp.R
// Set of Material typography styles to start with
val poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_bold,FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.poppins_regular),
            Font(R.font.poppins_bold,FontWeight.Bold),
            Font(R.font.poppins_semibold, FontWeight.SemiBold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)