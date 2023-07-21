package noel.samonte.appdevtestproject.Data.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsModel


@Dao
interface ButtonLogsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEventLog(button_logs_model: ButtonLogsModel)

    @Query("SELECT * FROM button_logs_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<ButtonLogsModel>>
}