package ru.mirea.bookcatalog.database

import android.arch.persistence.room.*
import ru.mirea.bookcatalog.entity.dbModels.LocalBook

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAll(): MutableList<LocalBook>

    @Delete
    fun delete(localBook: LocalBook)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg localBook: LocalBook)

    @Query("SELECT * FROM books WHERE id = :id")
    fun searchById(id: String): LocalBook?
}