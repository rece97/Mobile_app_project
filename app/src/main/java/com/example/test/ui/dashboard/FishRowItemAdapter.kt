package com.example.test.ui.dashboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R

class FishRowItemAdapter(private val fish: ArrayList<Fish>): RecyclerView.Adapter<FishRowItemAdapter.ViewHolder>() {
    var onItemClick: ((Fish) -> Unit)? = null
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.fish_name)
        val price = itemView.findViewById<TextView>(R.id.fish_price)
        val icon = itemView.findViewById<ImageView>(R.id.fish_icon)
        val rarity = itemView.findViewById<TextView>(R.id.fish_rarity)
        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(fish[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishRowItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fish_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FishRowItemAdapter.ViewHolder, position: Int) {
        val currentItem = fish[position]
        holder.name.text = currentItem.name.name
        holder.price.text = currentItem.price.toString()
        holder.rarity.text = currentItem.availability.rarity

        val context = holder.itemView.context

        Glide.with(context)
            .load(currentItem.icon_url)
            .placeholder(R.drawable.fish)
            .circleCrop()
            .into(holder.icon)

        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#f5f5f5"))
        }
    }

    override fun getItemCount(): Int {
        return fish.size
    }
}

