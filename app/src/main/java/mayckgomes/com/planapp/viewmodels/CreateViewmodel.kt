package mayckgomes.com.planapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.database.GetDB
import java.time.DayOfWeek
import java.time.LocalDate

class CreateViewmodel(): ViewModel(){

    val monthList = listOf(
        "Selecione",
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novenbro",
        "Dezembro"
        )

    private val _month = MutableStateFlow(0)
    val month = _month.map{ monthList[_month.value] }
    val monthNumber = _month.asStateFlow()

    fun changeMonth(month: Int){
        _month.value = month
    }


    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    fun openMenu(){
        _isExpanded.value = true
    }

    fun closeMenu(){
        _isExpanded.value = false
    }

    private val _day = MutableStateFlow(0)
    val day = _day.asStateFlow()

    fun nextDay(){
        _day.value++
    }

    fun backDay(){
        _day.value--
    }

    private val _dayList = MutableStateFlow(emptyList<String>())
    val dayList = _dayList.asStateFlow()


    fun GetDaysOfMonth(mes: Int){

        val ano = LocalDate.now().year

        var listaDias = mutableListOf<String>()

        var data = LocalDate.of(ano,mes,1)

        while (data.dayOfMonth < data.lengthOfMonth()){

            if (data.dayOfWeek == DayOfWeek.SUNDAY){

                listaDias.add("${data.dayOfMonth}/$mes - Domingo")

                data = data.plusDays(1)

            } else if (data.dayOfWeek == DayOfWeek.WEDNESDAY){

                listaDias.add("${data.dayOfMonth}/$mes - Quarta")

                data = data.plusDays(1)

            } else {

                data = data.plusDays(1)

            }

        }

        _dayList.value = listaDias

    }


    private val _daysSaved = MutableStateFlow<List<Day>>(emptyList())
    val daysSaved = _daysSaved.asStateFlow()

    fun addDay(day: Day){
        _daysSaved.value = _daysSaved.value + day
        print(_daysSaved)
    }

    fun delDay(day: Day){
        _daysSaved.value = _daysSaved.value + day
    }

    private val _isClick = MutableStateFlow(true)
    val isClick = _isClick.asStateFlow()

    @Composable
    fun Save(context: Context, list: List<Day>){

        val db = GetDB(context)

        LaunchedEffect(Unit) {

            db.addDays(_daysSaved.value)

        }

    }

    @Composable
    fun Delete(context: Context){

        val db = GetDB(context)

        LaunchedEffect(Unit) {

            db.clearDb()

        }

    }



}