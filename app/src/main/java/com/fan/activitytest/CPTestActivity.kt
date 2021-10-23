package com.fan.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fan.activitytest.conProvider.ReadContactsActivity
import com.fan.activitytest.kotlin.later
import com.fan.activitytest.permissionTest.PermissionMainActivity
import kotlinx.android.synthetic.main.activity_cptest.*

class CPTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cptest)

        tvPermission_cp_test.setOnClickListener {
            val intent = Intent(this, PermissionMainActivity::class.java)
            startActivity(intent)
        }

        tvReadContacts_cp_test.setOnClickListener {
            p.hashCode()
            val intent = Intent(this, ReadContactsActivity::class.java)
            startActivity(intent)
        }

    }

    val p by later {
        Log.d("CPTestActivity","hello world")
        "test later"
    }
}