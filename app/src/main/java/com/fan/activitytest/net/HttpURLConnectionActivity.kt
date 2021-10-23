package com.fan.activitytest.net

import android.os.Bundle
import android.util.Log
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_http_urlconnection.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception
import kotlin.concurrent.thread

class HttpURLConnectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_urlconnection)

        btnSendRequest.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }
    }

    private fun sendRequestWithHttpURLConnection() {
//        sendRequestWithHttpUC()
        HttpUtil.sendHttpRequest("https://www.baidu.com",object : HttpUtil.HttpCallbackListener{
            override fun onSuccess(response: String) {
                runOnUiThread { //进行UI 操作，将结果显示
                    tvResponse.text = response
                }
            }

            override fun onFailure(e: Exception) {
                tvResponse.text = e.message
            }
        })
    }


    private fun sendRequestWithHttpUC(){
        //开启线程发起网络请求
        thread {
            var connection:HttpURLConnection? = null
            try {
                val stringBuilder = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 8000
                connection.readTimeout = 8000

                val input = connection.inputStream
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        stringBuilder.append(it)
                    }
                }
                Log.e("HttpURLConnection", "responseData: ${stringBuilder.toString()}")
                runOnUiThread { //进行UI 操作，将结果显示
                    tvResponse.text = stringBuilder.toString()
                }
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }
}