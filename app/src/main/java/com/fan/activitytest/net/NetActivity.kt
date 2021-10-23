package com.fan.activitytest.net

import android.os.Bundle
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import com.fan.activitytest.kotlin.startActivityTest
import com.fan.activitytest.net.retrofittest.RetrofitTestActivity
import kotlinx.android.synthetic.main.activity_net.*

class NetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)

        tvWebView_activity_net.setOnClickListener {
            startActivityTest<WebViewActivity>(this){}
        }

        tvHttpURLConnection_activity_net.setOnClickListener {
            startActivityTest<HttpURLConnectionActivity>(this){}
        }

        tvOkHttp_activity_net.setOnClickListener {
            startActivityTest<OkHttpActivity>(this){}
        }

        tvXml_activity_net.setOnClickListener {
            startActivityTest<ParseXMLActivity>(this){}
        }

        tvJson_activity_net.setOnClickListener {
            startActivityTest<ParseJsonActivity>(this){}
        }

        tvRetrofitBase_activity_net.setOnClickListener {
            startActivityTest<RetrofitTestActivity>(this){}
        }

    }
}