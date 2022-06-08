package com.example.rsstestapplication.fragments.main.adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rsstestapplication.R
import com.example.rsstestapplication.fragments.main.presenter.MainFragmentPresenter
import com.example.rsstestapplication.models.ItemRssModel

class RssesAdapter(rsses: List<ItemRssModel>, val presenter: MainFragmentPresenter) :
RecyclerView.Adapter<RssesAdapter.ViewHolder>(){

    var rsses: List<ItemRssModel> = rsses
        set(rsses) {
            field = rsses
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val imageRss: ImageView


        init {
            title = itemView.findViewById(R.id.title)
            imageRss = itemView.findViewById(R.id.imageRss)

        }

        fun bindView(position: Int) {
            val rss: ItemRssModel = rsses[position]
            title.text = rss.titleRss
            Glide.with(itemView)
                .load(rss.linkImg)
               // .placeholder(R.drawable.placeholder)
                .into(imageRss)

            itemView.setOnClickListener {
                presenter.showDetails(rss)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rss, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)

    }

    override fun getItemCount() = rsses.size


}