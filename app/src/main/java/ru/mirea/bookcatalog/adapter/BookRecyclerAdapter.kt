package ru.mirea.bookcatalog.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import ru.mirea.bookcatalog.R
import ru.mirea.bookcatalog.entity.Book
import ru.mirea.bookcatalog.utils.GlideApp

class BookRecyclerAdapter(private val books: ArrayList<Book>, private val context: Context, val listener: (Int) -> Unit) : RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BookViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView = inflater.inflate(R.layout.item_book, viewGroup, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: BookViewHolder, i: Int) {
        viewHolder.title.text = books[i].volumeInfo!!.title

        if (books[i].volumeInfo!!.imageLinks != null){
            GlideApp.with(context)
                    .load(books[i].volumeInfo!!.imageLinks!!.thumbnail)
                    .transform(CenterCrop())
                    .error(R.color.colorAccent)
                    .into(viewHolder.thumbnail)
        }

        viewHolder.itemView.setOnClickListener { listener(i) }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: ImageView = itemView.findViewById(R.id.book_thumbnail)
        var title: TextView = itemView.findViewById(R.id.book_title)
    }

    fun addBooks(books: ArrayList<Book>){
        this.books.addAll(books)
        notifyDataSetChanged()
    }

}
