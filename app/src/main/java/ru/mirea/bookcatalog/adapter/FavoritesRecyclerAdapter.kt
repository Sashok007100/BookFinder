package ru.mirea.bookcatalog.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import ru.mirea.bookcatalog.R
import ru.mirea.bookcatalog.entity.dbModels.LocalBook
import ru.mirea.bookcatalog.utils.GlideApp

class FavoritesRecyclerAdapter(private val books: MutableList<LocalBook>, private val context: Context, val listener: (Int, Int) -> Unit) : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder>() {

    override fun onBindViewHolder(viewHolder: FavoritesViewHolder, i: Int) {
        viewHolder.delete.visibility = View.VISIBLE
        viewHolder.title.text = books[i].title

        if (books[i].thumbnail != null){
            GlideApp.with(context)
                    .load(books[i].thumbnail)
                    .transform(CenterCrop())
                    .error(R.color.colorAccent)
                    .into(viewHolder.thumbnail)
        }
        viewHolder.itemView.setOnClickListener { listener(i, 0) }
        viewHolder.delete.setOnClickListener { listener(i, 1) }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView = inflater.inflate(R.layout.item_book, viewGroup, false)
        return FavoritesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var delete: ImageButton = itemView.findViewById(R.id.book_delete)
        var thumbnail: ImageView = itemView.findViewById(R.id.book_thumbnail)
        var title: TextView = itemView.findViewById(R.id.book_title)
    }

    fun remove(index: Int){
        books.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, books.size)
    }

}
