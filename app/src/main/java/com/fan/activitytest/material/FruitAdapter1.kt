package com.fan.activitytest.material

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fan.activitytest.R
import com.fan.activitytest.kotlin.startActivityTest
import com.fan.activitytest.list.Fruit

class FruitAdapter1(val context: Context, val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter1.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val fruitName = view.findViewById<TextView>(R.id.fruitNames)
        val fruitImage = view.findViewById<ImageView>(R.id.fruitImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item_super, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            (context as MaterialActivity).startActivityTest<FruitMaterialActivity>(context){
                putExtra(FruitMaterialActivity.FRUIT_NAME,fruitList[position].name)
                putExtra(FruitMaterialActivity.FRUIT_IMAGE_ID,fruitList[position].img)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        Glide.with(context).load(fruit.img).into(holder.fruitImage)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount(): Int = fruitList.size
}