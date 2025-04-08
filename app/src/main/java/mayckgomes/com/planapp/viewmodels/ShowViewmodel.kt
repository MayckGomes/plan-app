package mayckgomes.com.planapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mayckgomes.com.planapp.database.Day
import mayckgomes.com.planapp.database.GetDB
import kotlinx.coroutines.flow.collectLatest

class ShowViewmodel(): ViewModel() {

    private val _list = MutableStateFlow(emptyList<Day>())
    val list = _list.asStateFlow()

    suspend fun GetAllDates(context: Context){

        val db = GetDB(context)

        db.getAll().collectLatest {
            _list.value = it
        }
    }

}