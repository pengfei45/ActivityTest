package com.fan.providertest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addData.setOnClickListener {
            // 添加数据
            val uri = Uri.parse("content://com.fan.databasetest.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
            Toast.makeText(this, "添加数据id = $bookId", Toast.LENGTH_SHORT).show()
        }
        queryData.setOnClickListener {
            // 查询数据
            val uri = Uri.parse("content://com.fan.databasetest.provider/book")
            val builder = StringBuilder()
            contentResolver.query(uri, null, null, null, null)?.build {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))

                    builder.append("书名 = $name，作者 = $author，页数 = $pages，价格 = $price \n")
                }
                Toast.makeText(this@MainActivity, builder.toString(), Toast.LENGTH_SHORT).show()
                close()
            }
        }
        updateData.setOnClickListener {
            // 更新数据
            bookId?.let {
                val uri = Uri.parse("content://com.fan.databasetest.provider/book/$it")
                val values = contentValuesOf(
                    "name" to "A Storm of Swords",
                    "pages" to 1216,
                    "price" to 24.05
                )
                val update = contentResolver.update(uri, values, null, null)
                Toast.makeText(this, "更新结果id = $update", Toast.LENGTH_SHORT).show()
            }
        }
        deleteData.setOnClickListener {
            // 删除数据
            bookId?.let {
                val uri = Uri.parse("content://com.fan.databasetest.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }
    }
}