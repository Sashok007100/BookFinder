package ru.mirea.bookcatalog.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.mirea.bookcatalog.R
import ru.mirea.bookcatalog.adapter.BookRecyclerAdapter
import ru.mirea.bookcatalog.screens.detail.BookDetailActivity
import ru.mirea.bookcatalog.screens.favorites.FavoritesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: BookRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.booksData.observe(this, Observer {
            icon_help.visibility = View.GONE
            progressBar.visibility = View.GONE
            if (it != null){
                icon_error.visibility = View.GONE
                adapter = BookRecyclerAdapter(it, this){ position ->
                    val intent = Intent(this, BookDetailActivity::class.java)
                    intent.putExtra("data", it[position])
                    startActivity(intent)
                }
                val layoutManager = LinearLayoutManager(this)
                booksRecycler.layoutManager = layoutManager
                booksRecycler.adapter = adapter

                booksRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val totalItemCount = layoutManager.itemCount
                        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                        if (!viewModel.loading && totalItemCount <= lastVisibleItem + 1) {
                            viewModel.loadNewPage()
                        }
                    }
                })
            } else {
                adapter = BookRecyclerAdapter(ArrayList(), this){ }
                val layoutManager = LinearLayoutManager(this)
                booksRecycler.layoutManager = layoutManager
                booksRecycler.adapter = adapter
                icon_error.visibility = View.VISIBLE
            }
        })

        viewModel.addictionPageBooksData.observe(this, Observer {
            if (it!= null && viewModel.needMode){
                adapter.addBooks(it)
                viewModel.needMode = false
            }
        })

        viewModel.error.observe(this, Observer {
            if (it != null){
                progressBar.visibility = View.GONE
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.error.postValue(null)
            }
        })

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                search.clearFocus()
                when(search_switcher.selectedTab){
                    0 -> viewModel.searchBooks(s)
                    1 -> viewModel.searchBooks("inauthor:$s")
                }
                icon_error.visibility = View.GONE
                icon_help.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

        search_switcher.setOnSwitchListener { position, _ ->
            if (!search.hasFocus() && search.query.toString() != ""){
                icon_error.visibility = View.GONE
                when (position){
                    0 -> viewModel.searchBooks(search.query.toString())
                    1 -> viewModel.searchBooks("inauthor:${search.query}")
                }
                progressBar.visibility = View.VISIBLE
            }
        }

        main_favs.setOnClickListener{startActivity(Intent(this, FavoritesActivity::class.java))}

    }
}
