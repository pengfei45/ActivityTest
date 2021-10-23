package com.fan.activitytest.list

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fan.activitytest.R

class FruitAdapter(activity: Activity, val resId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resId, data) {

    inner class ViewHolder(val fruitImg: ImageView, val fruitName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resId, parent, false)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        fruit?.let {
            viewHolder.fruitImg.setImageResource(it.img)
            viewHolder.fruitName.text = it.name
        }
        return view
    }
}