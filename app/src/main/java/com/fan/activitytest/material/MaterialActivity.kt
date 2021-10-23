package com.fan.activitytest.material

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import com.fan.activitytest.kotlin.startActivityTest
import com.fan.activitytest.list.Fruit
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_material.*
import kotlin.concurrent.thread

class MaterialActivity : BaseActivity() {

    private val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )

    private val fruitList = ArrayList<Fruit>()
    lateinit var adapter:FruitAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar_material)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
            drawerLayout.closeDrawers()
            true
        }

        fab.setOnClickListener {
            Snackbar.make(it, "点击了按钮", Snackbar.LENGTH_SHORT).setAction("掩耳盗铃") {
                Toast.makeText(this, "你看不到我，看不到我", Toast.LENGTH_SHORT).show()
            }.show()
        }

        initFruit()
        recycler_material.layoutManager = GridLayoutManager(this, 2)
        adapter = FruitAdapter1(this, fruitList)
        recycler_material.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits()
        }
    }

    private fun refreshFruits() {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruit()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruit(){
       fruitList.clear()
       repeat(50){
           val index = (0 until fruits.size).random()
           fruitList.add(fruits[index])
       }
   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "BackUp", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}