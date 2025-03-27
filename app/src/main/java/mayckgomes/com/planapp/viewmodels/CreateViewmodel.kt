package mayckgomes.com.planapp.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import mayckgomes.com.planapp.database.Day
import java.time.DayOfWeek
import java.time.LocalDate

class CreateViewmodel: ViewModel(){

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
    val dayNumber = _day.asStateFlow()


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


    private val _daysSaved = MutableStateFlow<MutableList<Day>>(mutableListOf())
    val daysSaved = _daysSaved.asStateFlow()

    fun addDay(day: String, text: String){
        _daysSaved.value.add(Day(day = day, text = text))
    }

    fun delDay(day: Day){
        _daysSaved.value.removeAt(_daysSaved.value.indexOf(day))
    }

}