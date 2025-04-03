package mayckgomes.com.planapp.viewmodels

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.database.GetDB
import androidx.core.content.edit

class ShowViewmodel(): ViewModel() {

    private val _userName = MutableStateFlow("Usuario")
    val userName = _userName.asStateFlow()

    fun changeUserName(name: String, context: Context){
        _userName.value = name

        val sp = context.getSharedPreferences("name", Context.MODE_PRIVATE)
        sp.edit() { putString("name", name) }
    }

    fun GetUserName(context: Context) {
        val sp = context.getSharedPreferences("name", Context.MODE_PRIVATE)

        _userName.value = sp.getString("name","Leide").toString()
    }

    private val _list = MutableStateFlow(emptyList<Day>())
    val list = _list.asStateFlow()

    suspend fun GetAllDates(context: Context){

        val db = GetDB(context)

        _list.value += db.getAll()
    }

}