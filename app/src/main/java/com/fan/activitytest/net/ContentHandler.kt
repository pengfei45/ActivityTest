package com.fan.activitytest.net

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class ContentHandler : DefaultHandler() {
    private var nodeName = ""
    private lateinit var id: StringBuilder
    private lateinit var name: StringBuilder
    private lateinit var version: StringBuilder


    //开始XML解析的时候调用
    override fun startDocument() {
        super.startDocument()

        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    //开始解析某个节点的时候调用
    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        //记录当前节点名、
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
        super.endElement(uri, localName, qName)

        if ("app" == localName) {
            Log.e(
                "ContentHandler",
                "id is ${id.toString().trim()} ; name is ${
                    name.toString().trim()
                } ; version is ${version.toString().trim()}"
            )
        }
    }

    //完成整个XML解析的时候调用
    override fun endDocument() {
        super.endDocument()


    }

}