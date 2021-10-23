package com.fan.activitytest.net

import android.os.Bundle
import android.util.Log
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_parse_json.*
import kotlinx.android.synthetic.main.activity_parse_xml.*
import org.json.JSONArray
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory
import kotlin.Exception
import kotlin.reflect.typeOf

class ParseJsonActivity : BaseActivity() {

    private var builder: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_json)

        val jsonStr = getStringFromJson()
        Log.e("ParseJsonActivity", "XML文本: $jsonStr")

        tvJson_activity_json.setOnClickListener {
            builder.clear()
            parseJsonWithJsonObject(jsonStr)
        }

        tvGson_activity_json.setOnClickListener {
            builder.clear()
            parseJsonWithGson(jsonStr)
        }
    }

    private fun parseJsonWithGson(jsonStr: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsonStr, typeOf)
        for (i in appList) {
            builder.append("id : ${i.id} ; name : ${i.name} ; version : ${i.version} \n")
        }
        tvResult_activity_json.text = builder.toString()
    }


    private fun parseJsonWithJsonObject(jsonStr: String) {
        try {
            val jsonArray = JSONArray(jsonStr)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                builder.append("id is $id ; name is $name ; version is $version \n")
            }
            tvResult_activity_json.text = builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getStringFromJson(): String {
        val stringBuilder = StringBuilder()
        try {
            //通过管理器打开文件并读取
            val reader = BufferedReader(InputStreamReader(assets.open("get_data.json")))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}