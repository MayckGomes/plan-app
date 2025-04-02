package mayckgomes.com.planapp.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun backAllDays(){
        _day.value = 0
    }

    private val _dayList = MutableStateFlow(emptyList<String>())
    val dayList = _dayList.asStateFlow()


    fun GetDaysOfMonth(mes: Int){

        val ano = LocalDate.now().year

        var listaDias = mutableListOf<String>()

        var data = LocalDate.of(ano,mes,1)

        // verifica se o dia pertence ao mes pedido
        while (data.monthValue == mes){

            Log.d("day", data.lengthOfMonth().toString())
            Log.d("day", data.dayOfMonth.toString())

            if (data.dayOfWeek == DayOfWeek.SUNDAY){

                listaDias.add("${
                    if (data.dayOfMonth < 10)"0${data.dayOfMonth}" else data.dayOfMonth
                }/$mes - Domingo")

                data = data.plusDays(1)

            } else if (data.dayOfWeek == DayOfWeek.WEDNESDAY){

                listaDias.add("${
                    if (data.dayOfMonth < 10)"0${data.dayOfMonth}" else data.dayOfMonth
                }/$mes - Quarta")

                data = data.plusDays(1)

            } else {

                data = data.plusDays(1)

            }

        }

        Log.d("day", listaDias.toString())
        _dayList.value = listaDias

    }


    private val _daysSaved = MutableStateFlow<List<Day>>(emptyList())
    val daysSaved = _daysSaved.asStateFlow() // <- como eu faço para sempre retornar a lista sort

    fun addDay(day: Day){
        _daysSaved.value = (_daysSaved.value + day).sortedBy { it.day }
        print(_daysSaved)
    }

    fun delDay(day: Day){
        _daysSaved.value = (_daysSaved.value - day).sortedBy { it.day }
    }

    private val _isClick = MutableStateFlow(true)
    val isClick = _isClick.asStateFlow()

    @SuppressLint("CoroutineCreationDuringComposition")
    fun Save(context: Context) {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            delete(context) // Executa a exclusão primeiro e espera concluir
            val db = GetDB(context)
            db.addDays(_daysSaved.value) // Depois adiciona os novos itens
        }
    }

    suspend fun delete(context: Context) {
        withContext(Dispatchers.IO) { // Garante que roda na thread correta
            val db = GetDB(context)
            db.clearDb()
        }
    }


}