package com.fan.activitytest.permissionTest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import com.fan.permissionutil.PermissionUtil
import kotlinx.android.synthetic.main.activity_permission_main.*

class PermissionMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_main)

        btnCallPhone_per_main.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                PermissionUtil.request(
                    this, Manifest.permission.CALL_PHONE
                ) { allGranted, deniedList ->
                    if (allGranted) {
                        call()
                    } else {
                        Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show()
                    }
                }
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                call()
            }
        }

    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        when(requestCode){
//            1 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    call()
//                }else{
//                    Toast.makeText(this,"你拒绝了权限",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//    }


    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10000")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}