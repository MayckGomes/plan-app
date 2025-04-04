package mayckgomes.com.planapp.viewmodels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel: ViewModel() {

    private val _userName = MutableStateFlow("Usuario")
    val userName = _userName.asStateFlow()

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

}