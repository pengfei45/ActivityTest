package com.fan.activitytest.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fan.activitytest.R
import com.fan.activitytest.list.Fruit
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        addData()
//        recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        val fruitAdapter = FruitAdapter(fruitList)
        recycler.adapter = fruitAdapter

    }

    private fun addData() {
        repeat(2) {
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear", R.drawable.pear_pic))
            fruitList.add(Fruit("Grape", R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry", R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango", R.drawable.mango_pic))
        }
    }
}