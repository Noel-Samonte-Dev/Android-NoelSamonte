package noel.samonte.appdevtestproject.Data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import noel.samonte.appdevtestproject.Data.Database.ButtonLogsDB
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsModel
import noel.samonte.appdevtestproject.Data.Model.DataRepository

class ButtonLogsViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<ButtonLogsModel>>
    private val repository: DataRepository

    init {
        val dao = ButtonLogsDB.getDatabase(application).dao()
        repository = DataRepository(dao)
        readAllData = repository.readAllData
    }

    fun addEventLog(event: ButtonLogsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEventLog(event)
        }
    }

    data class EventLogsModel (
        val id: String,
        val button_id: String,
        val created_at: String
    )

    private val _event_list = MutableStateFlow<MutableList<EventLogsModel>>(mutableListOf())
    val event_list = _event_list.asStateFlow()

    fun getEventList(events: MutableList<EventLogsModel>) {
        viewModelScope.launch {
            _event_list.update {
                events
            }
        }
    }

    fun getEvents(list: List<ButtonLogsModel>) {
        viewModelScope.launch {
            val event_list = mutableListOf<EventLogsModel>()
            if (list.isNotEmpty()) {
                val list_count = list.size
                for (i in 0 until list_count) {
                    val event = list[i]
                    val model = EventLogsModel(
                        id = event.id.toString(),
                        button_id = event.buttonID,
                        created_at = event.createdAt
                    )
                    event_list.add(model)

                    if (i + 1 == list_count) {
                        getEventList(event_list)
                    }
                }
            }
        }
    }
}