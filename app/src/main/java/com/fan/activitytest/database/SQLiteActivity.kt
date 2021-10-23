package com.fan.activitytest.database

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_sqlite.*
import java.lang.NullPointerException

class SQLiteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        btn_sqlite_create.setOnClickListener {
            dbHelper.writableDatabase
        }

        btn_sqlite_add.setOnClickListener {
            val wDb = dbHelper.writableDatabase
            wDb.execSQL(
                "insert into Book(name,author,pages,price) values(?,?,?,?)",
                arrayOf("红楼梦", "曹雪芹", "366", "26.8")
            )

            wDb.execSQL(
                "insert into Book(name,author,pages,price) values(?,?,?,?)",
                arrayOf("西游记", "罗贯中", "168", "16.8")
            )


//            val values1 = ContentValues().apply {
//                // 开始组装第一条数据
//                put("name", "红楼梦${(Math.random() * 100).toInt()}")
//                put("author", "张三${(Math.random() * 100).toInt()}")
//                put("pages", 454)
//                put("price", 16.2)
//            }
//            wDb.insert("Book", null, values1) // 插入第一条数据
//            val values2 = ContentValues().apply {
//                // 开始组装第二条数据
//                put("name", "The Lost Symbol")
//                put("author", "Dan Brown")
//                put("pages", 510)
//                put("price", 19.95)
//            }
//            wDb.insert("Book", null, values2) // 插入第二条数据
        }


        btn_sqlite_search.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.rawQuery("select * from Book", null)
//            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        btn_sqlite_update.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("update Book set price = ? where name = ?", arrayOf("26.8", "西游记"))
//            val values = ContentValues()
//            values.put("price", 10.9)
//            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        btn_sqlite_delete.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("delete from Book where pages > ?", arrayOf("200"))
//            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        btn_sqlite_replace_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() //开启事务
            try {
                db.delete("Book", null, null)
                if (true) {
                    throw  NullPointerException()  //手动添加异常使得事务失败
                }
                val values1 = ContentValues().apply {
                    // 开始组装第一条数据
                    put("name", "红楼梦1${(Math.random() * 100).toInt()}")
                    put("author", "张三1${(Math.random() * 100).toInt()}")
                    put("pages", 450)
                    put("price", 16.6)
                }
                db.insert("Book", null, values1) // 插入第一条数据
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }
}