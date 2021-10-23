package com.fan.activitytest.net

import android.os.Bundle
import android.util.Log
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_parse_xml.*
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory
import kotlin.Exception

class ParseXMLActivity : BaseActivity() {

    var builder: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_xml)

        val xmlStr = getStringFromXml()
        Log.e("ParseXMLActivity", "XML文本: $xmlStr")

        tvPull_activity_xml.setOnClickListener {
            builder.clear()
            try {
                val factory = XmlPullParserFactory.newInstance()
                val xmlPullParser = factory.newPullParser()
                xmlPullParser.setInput(StringReader(xmlStr))
                var eventType = xmlPullParser.eventType

                var id = ""
                var name = ""
                var version = ""

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    val nodeName = xmlPullParser.name
                    when (eventType) {
                        //开始解析某个节点
                        XmlPullParser.START_TAG -> {
                            when (nodeName) {
                                "id" -> id = xmlPullParser.nextText()
                                "name" -> name = xmlPullParser.nextText()
                                "version" -> version = xmlPullParser.nextText()
                            }
                        }
                        //完成解析某个节点
                        XmlPullParser.END_TAG -> {
                            if ("app" == nodeName) {
                                builder.append("id is $id , name is $name , version is $version; \n")
                            }
                        }
                    }
                    eventType = xmlPullParser.next()
                }
                tvResult_activity_xml.text = builder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        tvSAX_activity_xml.setOnClickListener {
            parseXmlWithSax(xmlStr)
        }
    }




    private fun parseXmlWithSax(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            xmlReader.contentHandler = object : DefaultHandler() {
                var nodeName = ""
                lateinit var id: StringBuilder
                lateinit var name: StringBuilder
                lateinit var version: StringBuilder

                //开始XML解析的时候调用
                override fun startDocument() {
                    id = StringBuilder()
                    name = StringBuilder()
                    version = StringBuilder()
                }

                //开始解析某个节点的时候调用
                override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
                    //记录当前节点名
                    if (localName != null) {
                        nodeName = localName
                    }
                    Log.e(
                        "ContentHandler",
                        "uri is $uri ; localName is $localName ; qName is $qName ; attributes is $attributes"
                    )
                }

                //获取节点中内容的时候调用。注意：在获取节点中内容时，该方法可能会被调用多次，回车、换行符也可能会被当作内容解析出来，
                //针对这种情况要在代码中做好控制，如，使用trim()方法。
                override fun characters(ch: CharArray?, start: Int, length: Int) {
                    super.characters(ch, start, length)

                    //根据当前节点名判断将内容添加到哪一个 StringBuilder 对象中
                    when (nodeName) {
                        "id" -> id.append(ch, start, length)
                        "name" -> name.append(ch, start, length)
                        "version" -> version.append(ch, start, length)
                    }
                }

                //完成解析某个节点的时候调用
                override fun endElement(uri: String?, localName: String?, qName: String?) {
                    if ("app" == localName) {
                        Log.e("ContentHandler", "id is ${id.toString().trim()} ; " +
                                "name is ${name.toString().trim()} ;" +
                                " version is ${version.toString().trim()}")

                        tvResult_activity_xml.text = "id is ${id.toString().trim()} \n " +
                                "name is ${name.toString().trim()} \n " +
                                "version is ${version.toString().trim()}"

                        //清空 StringBuilder
                        id.setLength(0)
                        name.setLength(0)
                        version.setLength(0)
                    }
                }

                //完成整个XML解析的时候调用
                override fun endDocument() {
                    super.endDocument()
                }
            }
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getStringFromXml(): String {
        val stringBuilder = StringBuilder()
        try {
            //通过管理器打开文件并读取
            val reader = BufferedReader(InputStreamReader(assets.open("get_data.xml")))
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