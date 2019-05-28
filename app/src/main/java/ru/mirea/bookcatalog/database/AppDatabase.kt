package ru.mirea.bookcatalog.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.mirea.bookcatalog.entity.dbModels.LocalBook

@Database(entities = [LocalBook::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
