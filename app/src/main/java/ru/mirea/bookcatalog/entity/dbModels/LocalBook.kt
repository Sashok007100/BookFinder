package ru.mirea.bookcatalog.entity.dbModels

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.mirea.bookcatalog.entity.Book
import java.io.Serializable

@Entity(tableName = "books")
class LocalBook: Serializable{
    @PrimaryKey
    lateinit var id: String

    @ColumnInfo
    var title: String? = null

    @ColumnInfo
    var thumbnail: String? = null

    @ColumnInfo
    var authors: String? = null

    @ColumnInfo
    var publisher: String? = null

    @ColumnInfo
    var description: String? = null

    constructor(book: Book) {
        this.id =  book.selfLink!!.substring(44, book.selfLink!!.length)
        this.title = book.volumeInfo?.title
        this.thumbnail = book.volumeInfo?.imageLinks?.thumbnail
        this.publisher = book.volumeInfo?.publisher
        this.description = book.volumeInfo?.description
        if (book.volumeInfo?.authors == null) this.authors = null
        else {
            this.authors = ""
            for (author in book.volumeInfo!!.authors!!){
                authors += "$author "
            }
        }
    }

    constructor()

}