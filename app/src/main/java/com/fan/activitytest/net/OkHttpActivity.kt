package com.fan.activitytest.net

import android.os.Bundle
import android.util.Log
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_ok_http.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class OkHttpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        btnSendRequest_okhttp.setOnClickListener {
            //1.
//            sendRequestWithOkHttp()

            //2.
            HttpUtil.sendOkHttpRequest("https://www.baidu.com", object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("OkHttpActivity", "onResponse:   ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.e("OkHttpActivity", "onResponse:   ${response.body?.string()}")
//                    runOnUiThread {
//                        tvResponse_okhttp.text = response.body?.string()
//                    }
                }
            })

//            val str = runBlocking {
//                getBaiduResponse()
//            }

        }
    }


    private suspend fun getBaiduResponse(){
        try {
            val response = HttpUtil.request("https://www.baidu.com")
            Log.e("OkHttpActivity",response)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun sendRequestWithOkHttp() {
        //开启线程发起网络请求
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url("https://www.baidu.com").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                Log.e("OkHttpActivity", "responseData: $responseData")
                if (responseData != null) {
                    runOnUiThread {
                        tvResponse_okhttp.text = responseData
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }
}
