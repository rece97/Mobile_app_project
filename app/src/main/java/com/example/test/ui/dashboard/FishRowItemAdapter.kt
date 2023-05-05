package com.example.test.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R

class FishRowItemAdapter(private val fish: ArrayList<Fish>): RecyclerView.Adapter<FishRowItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.fish_name)
        val price = itemView.findViewById<TextView>(R.id.fish_price)
        val icon = itemView.findViewById<ImageView>(R.id.fish_icon)
        init {
            itemView.setOnClickListener{
                val selectedItem = adapterPosition
                //var viewmodel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
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

        val context = holder.itemView.context

        Glide.with(context)
            .load(currentItem.icon_url)
            .placeholder(R.drawable.fish)
            .circleCrop()
            .into(holder.icon)
    }

    override fun getItemCount(): Int {
        return fish.size
    }
}

