package ru.mirea.bookcatalog.screens.detail

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.activity_book_detail.*
import ru.mirea.bookcatalog.R
import ru.mirea.bookcatalog.database.AppDatabase
import ru.mirea.bookcatalog.database.AppDatabase_Impl
import ru.mirea.bookcatalog.entity.Book
import ru.mirea.bookcatalog.entity.dbModels.LocalBook
import ru.mirea.bookcatalog.utils.GlideApp

class BookDetailActivity : AppCompatActivity() {

    private var isInFavs = false
    private lateinit var book: LocalBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "bookDB")
                .allowMainThreadQueries()
                .build()

        book = if (intent.getSerializableExtra("localData") != null) intent.getSerializableExtra("localData") as LocalBook
        else LocalBook(intent.getSerializableExtra("data") as Book)
        val bookId = book.id

        isInFavs = db.bookDao().searchById(bookId) != null
        if (isInFavs) book_detail_action.setImageResource(R.drawable.ic_favorites_filled)

        book_detail_title.text = book.title
        book_detail_description.text = book.description
        book_detail_publisher.text = book.publisher
        book_detail_authors.text = book.authors

        if (book.thumbnail != null){
            GlideApp.with(this)
                    .load(book.thumbnail)
                    .transform(CenterCrop())
                    .into(book_detail_thumbnail)
        }

        detail_book_back.setOnClickListener { finish() }

        book_detail_action.setOnClickListener {
            if (!isInFavs) {
                db.bookDao().insert(book)
                isInFavs = true
                book_detail_action.setImageResource(R.drawable.ic_favorites_filled)
            }
            else  Toast.makeText(this, "Уже добавлено", Toast.LENGTH_SHORT).show()
        }
    }
}
