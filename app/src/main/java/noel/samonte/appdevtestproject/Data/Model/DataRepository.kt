package noel.samonte.appdevtestproject.Data.Model

import androidx.lifecycle.LiveData

class DataRepository(private val dao: ButtonLogsDAO) {
    val readAllData: LiveData<List<ButtonLogsModel>> = dao.readAllData()

    suspend fun addEventLog(event: ButtonLogsModel) {
        dao.addEventLog(event)
    }
}