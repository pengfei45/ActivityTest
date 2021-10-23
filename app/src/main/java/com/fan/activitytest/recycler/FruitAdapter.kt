package com.fan.activitytest.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fan.activitytest.R
import com.fan.activitytest.list.Fruit

class FruitAdapter(val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context,"position = $position , fruit.name = $fruit.name",Toast.LENGTH_SHORT).show()
        }

        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context,"fruit.name = $fruit.name",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun getItemCount(): Int = fruitList.size


    override fun onBindViewHolder(holder: FruitAdapter.ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.img)
        holder.fruitName.text = fruit.name
    }
}