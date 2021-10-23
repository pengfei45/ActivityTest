package com.fan.activitytest.conProvider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_read_contacts.*

class ReadContactsActivity : BaseActivity() {

    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_contacts)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
        listview_read_contacts.adapter = adapter

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 35)
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            35 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun readContacts() {
        // 查询联系人数据
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                // 获取联系人姓名
                val displayName =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                // 获取联系人手机号
                val number =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val times =
                    getInt(getColumnIndex(ContactsContract.CommonDataKinds.Phone.TIMES_CONTACTED))
                contactsList.add("$displayName \n$number ，通话次数： $times 次")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }
}