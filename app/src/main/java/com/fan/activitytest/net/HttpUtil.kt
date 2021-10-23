package com.fan.activitytest.net

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object HttpUtil {

    fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val responseStr = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 8000
                connection.readTimeout = 8000

                val input = connection.inputStream
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        responseStr.append(it)
                    }
                }
                listener.onSuccess(responseStr.toString())  //请求成功的回调
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onFailure(e)  //请求异常的回调
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }


    suspend fun request(address:String):String{
        return  suspendCoroutine {
            sendHttpRequest(address,object: HttpCallbackListener{
                override fun onSuccess(response: String) {
                    it.resume(response)
                }

                override fun onFailure(e: Exception) {
                    it.resumeWithException(e)
                }
            })

        }
    }

    interface HttpCallbackListener {
        fun onSuccess(response: String)
        fun onFailure(e: Exception)
    }
}