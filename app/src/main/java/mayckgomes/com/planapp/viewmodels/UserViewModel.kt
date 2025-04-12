package mayckgomes.com.planapp.viewmodels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.SortedMap

open class UserViewModel: ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()


    fun userNameUpdate(text: String){
        _userName.value = text
    }

    fun changeUserName(name: String, context: Context){
        _userName.value = name

        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        sp.edit() { putString("name", name) }
    }

    fun GetUserName(context: Context) {
        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)

        _userName.value = sp.getString("name","").toString()
    }


    fun VerifyFirstTime(context: Context): Boolean{
        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)

        return sp.getBoolean("first",true)
    }

    fun SetFalseFirstTime(context: Context){
        val sp = context.getSharedPreferences("user", Context.MODE_PRIVATE)

        return sp.edit{ putBoolean("first", false) }

    }

    private val _segunda = MutableStateFlow(false)
    val segunda = _segunda.asStateFlow()

    fun segundaChange(type: Boolean){
        _segunda.value = type
    }



    private val _terca = MutableStateFlow(false)
    val terca = _terca.asStateFlow()

    fun tercaChange(type: Boolean){
        _terca.value = type
    }



    private val _quarta = MutableStateFlow(false)
    val quarta = _quarta.asStateFlow()

    fun quartaChange(type: Boolean){
        _quarta.value = type
    }




    private val _quinta = MutableStateFlow(false)
    val quinta = _quinta.asStateFlow()

    fun quintaChange(type: Boolean){
        _quinta.value = type
    }




    private val _sexta = MutableStateFlow(false)
    val sexta = _sexta.asStateFlow()

    fun sextaChange(type: Boolean){
        _sexta.value = type
    }




    private val _sabado = MutableStateFlow(false)
    val sabado = _sabado.asStateFlow()

    fun sabadoChange(type: Boolean){
        _sabado.value = type
    }




    private val _domingo = MutableStateFlow(false)
    val domingo = _domingo.asStateFlow()

    fun domingoChange(type: Boolean){
        _domingo.value = type
    }


    fun setDays(context: Context){

        val db = context.getSharedPreferences("daysOfWeek", Context.MODE_PRIVATE)

        db.edit() {
            putBoolean("domingo",_domingo.value)
            putBoolean("segunda",_segunda.value)
            putBoolean("terca",_terca.value)
            putBoolean("quarta",_quarta.value)
            putBoolean("quinta",_quinta.value)
            putBoolean("sexta",_sexta.value)
            putBoolean("sabado",_sabado.value)
        }

    }

    fun getDays(context: Context): SortedMap<String, Boolean> {

        val db = context.getSharedPreferences("daysOfWeek", Context.MODE_PRIVATE)

        _domingo.value = db.getBoolean("domingo", false)
        _segunda.value = db.getBoolean("segunda", false)
        _terca.value = db.getBoolean("terca", false)
        _quarta.value = db.getBoolean("quarta", false)
        _quinta.value = db.getBoolean("quinta", false)
        _sexta.value = db.getBoolean("sexta", false)
        _sabado.value = db.getBoolean("sabado", false)

        return sortedMapOf(
            "segunda" to db.getBoolean("segunda", false),
            "terca" to db.getBoolean("terca", false),
            "quarta" to db.getBoolean("quarta", false),
            "quinta" to db.getBoolean("quinta", false),
            "sexta" to db.getBoolean("sexta", false),
            "sabado" to db.getBoolean("sabado", false),
            "domingo" to db.getBoolean("domingo", false),
        )

    }

}