package mayckgomes.com.planapp.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    // months ======================================================================================
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
        "Novembro",
        "Dezembro"
        )

    private val _month = MutableStateFlow(0)
    val monthName = _month.map{ monthList[_month.value] }
    val monthNumber = _month.asStateFlow()

    fun changeMonth(month: Int){
        _month.value = month
    }



    // menu ========================================================================================
    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    fun openMenu(){
        _isExpanded.value = true
    }

    fun closeMenu(){
        _isExpanded.value = false
    }



    // days ========================================================================================
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



    // days by month ===============================================================================
    private val _dayList = MutableStateFlow(emptyList<String>())
    val dayList = _dayList.asStateFlow()

    fun GetDaysOfMonth(context: Context, mes: Int){

        val viewmodel = UserViewModel()

        val days = viewmodel.getDays(context)

        Log.d("list", days.toString())

        val ano = LocalDate.now().year

        var listaDias = mutableListOf<String>()

        var data = LocalDate.of(ano,mes,1)

        // verifica se o dia pertence ao mes pedido
        while (data.monthValue == mes){

            Log.d("days", listaDias.toString())
            Log.d("number", data.dayOfMonth.toString())

            when{
                data.dayOfWeek == DayOfWeek.MONDAY && days["segunda"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Segunda"

                        } else {
                            "${data.dayOfMonth}/$mes - Segunda"

                        }
                    )

                    data = data.plusDays(1)
                }


                data.dayOfWeek == DayOfWeek.TUESDAY && days["terca"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Terça"

                        } else {
                            "${data.dayOfMonth}/$mes - Terça"

                        }
                    )

                    data = data.plusDays(1)

                }

                data.dayOfWeek == DayOfWeek.WEDNESDAY && days["quarta"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Quarta"

                        } else {
                            "${data.dayOfMonth}/$mes - Quarta"

                        }
                    )

                    data = data.plusDays(1)

                }

                data.dayOfWeek == DayOfWeek.THURSDAY && days["quinta"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Quinta"

                        } else {
                            "${data.dayOfMonth}/$mes - Quinta"

                        }
                    )

                    data = data.plusDays(1)

                }

                data.dayOfWeek == DayOfWeek.FRIDAY && days["sexta"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Sexta"

                        } else {
                            "${data.dayOfMonth}/$mes - Sexta"

                        }
                    )

                    data = data.plusDays(1)

                }

                data.dayOfWeek == DayOfWeek.SATURDAY && days["sabado"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Sábado"

                        } else {
                            "${data.dayOfMonth}/$mes - Sábado"

                        }
                    )

                    data = data.plusDays(1)

                }

                data.dayOfWeek == DayOfWeek.SUNDAY && days["domingo"] == true -> {

                    listaDias.add(
                        if (data.dayOfMonth < 10) {

                            "0${data.dayOfMonth}/$mes - Domingo"

                        } else {
                            "${data.dayOfMonth}/$mes - Domingo"

                        }
                    )

                    data = data.plusDays(1)

                }
                else -> data = data.plusDays(1)
            }

        }

        Log.d("day", listaDias.toString())
        _dayList.value = listaDias

    }


    // days by user ================================================================================
    private val _daysSaved = MutableStateFlow<List<Day>>(emptyList())
    val daysSaved = _daysSaved.asStateFlow()

    fun addDay(day: Day){
        _daysSaved.value = (_daysSaved.value + day).sortedBy { it.day }
        // adiciona o a data na lista de salvos e retira a data da lista de datas disponiveis
        _dayList.value = (_dayList.value - day.day).sortedBy { it }
    }

    fun delDay(day: Day){
        _daysSaved.value = (_daysSaved.value - day).sortedBy { it.day }
        _dayList.value = (_dayList.value + day.day).sortedBy { it }
    }



    // Database funcs ==============================================================================
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


    // vars ========================================================================================

    private val _isClick = MutableStateFlow(false)
    val isClick = _isClick.asStateFlow()

    fun isClickTrue(){
        _isClick.value = true
    }

    fun isClickFalse(){
        _isClick.value = false
    }



    private val _isClickBack = MutableStateFlow(false)
    val isClickBack = _isClickBack.asStateFlow()

    fun isClickBackTrue(){
        _isClickBack.value = true
    }

    fun isClickBackFalse(){
        _isClickBack.value = false
    }



    private val _isChoosed = MutableStateFlow(false)
    val isChoosed = _isChoosed.asStateFlow()

    fun isChoosedTrue(){
        _isChoosed.value = true
    }



    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun isLoadingTrue(){
        _isLoading.value = true
    }

    fun isLoadingFalse(){
        _isLoading.value = false
    }



    private val _isFull = MutableStateFlow(false)
    val isFull = _isFull.asStateFlow()

    fun isFullTrue(){
        _isFull.value = true
    }

    fun isFullFalse(){
        _isFull.value = false
    }



    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun textUpdate(text: String){
        _text.value = text
    }

    fun textClear(){
        _text.value = ""
    }
}