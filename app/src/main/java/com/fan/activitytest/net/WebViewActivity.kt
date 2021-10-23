package com.fan.activitytest.net

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView_activity.settings.javaScriptEnabled = true
        webView_activity.webViewClient = WebViewClient()
        webView_activity.loadUrl("https://www.baidu.com")
    }
}