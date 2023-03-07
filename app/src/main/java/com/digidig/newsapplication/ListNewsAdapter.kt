package com.digidig.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.digidig.newsapplication.model.Article

class ListNewsAdapter(
    private val listHero: List<Article>,
    private val photo: Int
    )
    : RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

    @Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        return ListViewHolder(view)
    }

    @Override
    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (title, description, urlToImage) = listHero[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = title
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu memilih " + listHero[holder.adapterPosition].title, Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
    }
}