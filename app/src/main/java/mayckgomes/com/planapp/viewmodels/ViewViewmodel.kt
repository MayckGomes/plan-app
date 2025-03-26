package mayckgomes.com.planapp.viewmodels

import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import java.time.LocalDate

class ViewViewmodel(): ViewModel() {

    fun GetUserName(): String{
        return "usuario"
    }


    suspend fun GetAllDates(): List<String>{
        return emptyList<String>()
    }
}