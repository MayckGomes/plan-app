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

    private val _list = MutableStateFlow(emptyList<Day>())
    val list = _list.asStateFlow()

    suspend fun GetAllDates(context: Context){

        val db = GetDB(context)

        _list.value += db.getAll()
    }

}