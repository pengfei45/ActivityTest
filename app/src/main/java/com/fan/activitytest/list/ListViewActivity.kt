package com.fan.activitytest.list

import android.os.Bundle
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_list_view.*


class ListViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()
    private var startPos:Int = 0
    private var endPos:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        addData()

        val fruitAdapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        listView.adapter = fruitAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList.get(position)
            Toast.makeText(this, fruit.toString(), Toast.LENGTH_SHORT).show()
        }


        /**
         * list滚动监听
         */
        listView.setOnScrollListener(object : OnScrollListener {

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) { //list停止滚动时加载图片
                    loadImage(startPos, endPos) // 异步加载图片   ,只加载可以看到的图片
                }
            }

            override fun onScroll(
                view: AbsListView?, firstVisibleItem: Int,
                visibleItemCount: Int, totalItemCount: Int
            ) {
                //设置当前屏幕显示的起始pos和结束pos
                startPos = firstVisibleItem
                endPos = firstVisibleItem + visibleItemCount
                if (endPos >= totalItemCount) {
                    endPos = totalItemCount - 1
                }
            }
        })
    }

    private fun loadImage(startPos: Int, endPos: Int) {
        TODO("Not yet implemented")
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