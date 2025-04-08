package mayckgomes.com.planapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.database.GetDB

class EditViewmodel: ViewModel() {

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun textUpdate(text: String){
        _text.value = text
    }

    fun textClear(){
        _text.value = ""
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun isLoadingTrue(){
        _isLoading.value = true

    }

    fun isLoadingFalse(){
        _isLoading.value = false

    }

    private val _isClickSave = MutableStateFlow(false)
    val isClickSave = _isClickSave.asStateFlow()

    fun isClickSaveTrue(){
        _isClickSave.value = true
    }

    fun isClickSaveFalse(){
        _isClickSave.value = false
    }

    private val _isClickBack = MutableStateFlow(false)
    val isClickBack = _isClickBack.asStateFlow()

    fun isClickBackTrue(){
        _isClickBack.value = true
    }

    fun isClickBackFalse(){
        _isClickBack.value = false
    }

    private val _dayList = MutableStateFlow<List<Day>>(emptyList())
    val dayList = _dayList.asStateFlow()


    fun addDayList(day: Day){
        _dayList.value = (_dayList.value + day).sortedBy { it.day }
    }

    fun delDayList(day: Day){
        _dayList.value = (_dayList.value - day).sortedBy { it.day }
    }

    suspend fun getDays(context: Context){

        val db = GetDB(context)

        _dayList.value = db.getAllList()

        Log.d("db", _dayList.value.toString())

    }



    private val _editList = MutableStateFlow<List<Day>>(emptyList())
    val editList = _editList.asStateFlow()


    fun addDayEdit(day: Day){
        _editList.value = (_editList.value + day).sortedBy { it.day }
    }

    fun delDayEdit(day: Day){
        _editList.value = (_editList.value - day).sortedBy { it.day }
    }

    private val _day = MutableStateFlow(0)
    val day = _day.asStateFlow()

    fun plusDay(){
        _day.value++
    }

    fun minusDay(){
        _day.value--

    }


    fun save(context: Context){
        val db = GetDB(context)

        CoroutineScope(Dispatchers.IO).launch {
            db.updateDays(_dayList.value)
        }
    }

}