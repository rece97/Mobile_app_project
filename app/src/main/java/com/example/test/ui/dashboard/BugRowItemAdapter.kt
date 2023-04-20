package com.example.test.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R

class BugRowItemAdapter(private val bugs: Array<Bug>): RecyclerView.Adapter<BugRowItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.bug_name)
        val price = itemView.findViewById<TextView>(R.id.bug_price)
        val icon = itemView.findViewById<ImageView>(R.id.bug_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bug_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = bugs[position]
        holder.name.text = currentItem.name.name
        holder.price.text = currentItem.price.toString()

        val context = holder.itemView.context

        Glide.with(context)
            .load(currentItem.icon_url)
            .placeholder(R.drawable.bug)
            .circleCrop()
            .into(holder.icon)
    }

    override fun getItemCount(): Int {
        return bugs.size
    }
}