package com.fan.activitytest.receiver.practice

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.MainActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val account = accountEdit_login.text.toString()
            val password = passwordEdit_login.text.toString()

            if (isDarkTheme(this)){
                Toast.makeText(this, "开启了夜间模式", Toast.LENGTH_SHORT).show()
            }

            //如果账号是 admin ,密码为 123456，就认为登陆成功
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }
}
