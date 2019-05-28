package ru.mirea.bookcatalog.screens.favorites

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorites.*
import ru.mirea.bookcatalog.R
import ru.mirea.bookcatalog.adapter.FavoritesRecyclerAdapter
import ru.mirea.bookcatalog.database.AppDatabase
import ru.mirea.bookcatalog.screens.detail.BookDetailActivity

class FavoritesActivity : AppCompatActivity() {

    private lateinit var adapter: FavoritesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "bookDB")
                .allowMainThreadQueries()
                .build()

        val books = db.bookDao().getAll()
        books.reverse()

        favorites_recycler.layoutManager = LinearLayoutManager(this)
        adapter = FavoritesRecyclerAdapter(books, this){position, command ->
            when(command){
                1 -> {
                    db.bookDao().delete(books[position])
                    adapter.remove(position)
                }
                0 -> {
                    val intent = (Intent(this, BookDetailActivity::class.java))
                    intent.putExtra("localData", books[position])
                    startActivity(intent)
                }
            }
        }
        favorites_recycler.adapter = adapter

        favorites_back.setOnClickListener { finish() }

    }
}
