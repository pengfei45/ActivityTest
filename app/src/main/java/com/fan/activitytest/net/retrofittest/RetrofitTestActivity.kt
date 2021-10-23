package com.fan.activitytest.net.retrofittest

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_retrofit_test.*
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_test)

        tvRequest_activity_retrofit_test.setOnClickListener {
            tvResult_activity_retrofit_test.text = ""

            /** 基础写法
            val retrofit = Retrofit.Builder().baseUrl("https://gank.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            /** //请求轮播数据接口
            retrofit.create(ShowService::class.java).getGankBannerData()
            .enqueue(object : Callback<BannerBean> {
            override fun onResponse(
            call: Call<BannerBean>,
            response: Response<BannerBean>
            ) {
            tvResult_activity_retrofit_test.text = response.body().toString()
            }

            override fun onFailure(call: Call<BannerBean>, t: Throwable) {
            tvResult_activity_retrofit_test.text = t.message
            }
            })  */

            //请求分类数据API
            retrofit.create(ShowService::class.java).getGankCategoryData(
            "Article", "Android", 1, 3
            ).enqueue(object : Callback<CategoryBean> {
            override fun onResponse(
            call: Call<CategoryBean>,
            response: Response<CategoryBean>
            ) {
            tvResult_activity_retrofit_test.text = response.body().toString()
            }

            override fun onFailure(call: Call<CategoryBean>, t: Throwable) {
            tvResult_activity_retrofit_test.text = t.message
            }
            })   */

            //封装后写法
//            ServiceCreator.create(ShowService::class.java).getGankCategoryData(
//                "Article", "Android", 1, 3
//            ).enqueue(object : Callback<CategoryBean> {
//                override fun onResponse(
//                    call: Call<CategoryBean>,
//                    response: Response<CategoryBean>) {
//                    tvResult_activity_retrofit_test.text = response.body().toString()
//                }
//
//                override fun onFailure(call: Call<CategoryBean>, t: Throwable) {
//                    tvResult_activity_retrofit_test.text = t.message
//                }
//            })

            //使用协程，调用 Call 扩展函数 await() 的写法
            lifecycleScope.launch {
                getAppData()
            }
        }
    }


    private suspend fun getAppData() {
        try {
            val bannerData = ServiceCreator.create<ShowService>().getGankBannerData().await()
            Log.e("RetrofitTestActivity", "$bannerData")
            tvResult_activity_retrofit_test.text = bannerData.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            tvResult_activity_retrofit_test.text = e.message
        }
    }

}