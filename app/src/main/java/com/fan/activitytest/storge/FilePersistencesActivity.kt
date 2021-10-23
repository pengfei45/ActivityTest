package com.fan.activitytest.storge

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_file_persistence.*
import java.io.*

class FilePersistenceActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistence)


        val text = load()
        if (text.isNotBlank()) {
            edit_file.setText(text)
            edit_file.setSelection(text.length)
            Toast.makeText(this, "加载数据成功", Toast.LENGTH_SHORT).show()
        }

        saveSPData.setOnClickListener {
            val inputText = edit_file.text.trim().toString()
            getPreferences(Context.MODE_PRIVATE).edit().apply {
                if (inputText.isNotBlank()) {
                    putString("sp_input", inputText)
                    putBoolean("sp_custom", true)
                } else {
                    putString("sp_input", "input text is null")
                    putBoolean("sp_custom", false)
                }
                apply()
            }
            Toast.makeText(this, "保存输入数据成功", Toast.LENGTH_SHORT).show()
        }

        getSPData.setOnClickListener {
            getPreferences(Context.MODE_PRIVATE).apply {
                val spInput = getString("sp_input", "no data")
                val spCustom = getBoolean("sp_custom", false)
                tv_show_sp_data.text = "上次输入内容： $spInput , 满意度：$spCustom"
            }
            Toast.makeText(this, "载入数据成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = edit_file.text.trim().toString()
        if (inputText.isNotBlank()) {
            saveFile(inputText)
        }
    }

    private fun saveFile(text: String) {
        try {
            val openFileOutput = openFileOutput("file_Note", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(openFileOutput))
            writer.use {
                it.write(text)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("file_Note")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                //forEachLine 函数，也是Kollin提供的内置扩展函数。会将读到的每行内容都回调到 Lamnbda 表达式中，只需要在 Lambda 表达式中完成拼接逻辑即可；
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

}