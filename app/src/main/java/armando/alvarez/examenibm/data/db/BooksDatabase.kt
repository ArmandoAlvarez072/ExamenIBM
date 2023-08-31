package armando.alvarez.examenibm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import armando.alvarez.examenibm.data.model.SaleInfo
import armando.alvarez.examenibm.data.model.VolumeInfo

@Database(
    entities = [
        VolumeInfo::class, SaleInfo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}