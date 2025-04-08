package mayckgomes.com.planapp.database

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Day(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    val text: String
)

@Dao
interface DayInterface {

    @Insert
    suspend fun addDays(day: List<Day>)

    @Update
    suspend fun updateDays(day: List<Day>)

    @Query("DELETE FROM day")
    suspend fun clearDb()

    @Query("SELECT * FROM day")
    fun getAll(): Flow<List<Day>>

    @Query("SELECT * FROM day")
    suspend fun getAllList(): List<Day>

}

@Database(entities = [Day::class], version = 1)
abstract class dayDatabase: RoomDatabase(){
    abstract fun daydao() : DayInterface
}

fun GetDB(context: Context): DayInterface {
    val dbConfig = Room.databaseBuilder(
        context,
        dayDatabase::class.java,
        "Day"
    ).build()

    return dbConfig.daydao()
}