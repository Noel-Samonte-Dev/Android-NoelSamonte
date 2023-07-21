package noel.samonte.appdevtestproject.Data.Database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsModel
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsDAO

@Database(
    entities = [ButtonLogsModel::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    version = 2,
    exportSchema = true
)
abstract class ButtonLogsDB : RoomDatabase() {
    abstract fun dao(): ButtonLogsDAO

    companion object {
        @Volatile
        private var INSTANCE: ButtonLogsDB? = null

        fun getDatabase(context: Context): ButtonLogsDB {
            val temp_instance = INSTANCE
            if (temp_instance != null) {
                return temp_instance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ButtonLogsDB::class.java,
                    name = "button_logs_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}