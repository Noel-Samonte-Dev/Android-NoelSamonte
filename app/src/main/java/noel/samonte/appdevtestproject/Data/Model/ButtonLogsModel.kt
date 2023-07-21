package noel.samonte.appdevtestproject.Data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "button_logs_table")
class ButtonLogsModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int

    @ColumnInfo(name = "button_id")
    var buttonID: String

    @ColumnInfo(name = "created_at")
    var createdAt: String

    constructor(buttonID: String, createdAt: String, id: Int) {
        this.buttonID = buttonID
        this.createdAt = createdAt
        this.id = id
    }
}