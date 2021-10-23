package com.fan.activitytest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fan.activitytest.bean.Person
import com.fan.activitytest.bean.Teacher
import com.fan.activitytest.database.SQLiteActivity
import com.fan.activitytest.fragment.FragmentTestActivity
import com.fan.activitytest.jetpack.JetpackMainActivity
import com.fan.activitytest.kotlin.startActivityTest
import com.fan.activitytest.material.MaterialActivity
import com.fan.activitytest.newsapp.Main2Activity
import com.fan.activitytest.receiver.ReceiverTestActivity
import com.fan.activitytest.media.MediaActivity
import com.fan.activitytest.net.NetActivity
import com.fan.activitytest.storge.FilePersistenceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        tv_activity_main.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.163.com")
            startActivity(intent)
        }

        tv1_activity_main.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1608)

        }

        tv_fragment_activity_main.setOnClickListener {
            val intent = Intent(this, FragmentTestActivity::class.java)
            startActivity(intent)

        }

        tv_frag_news_activity_main.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)

        }

        tv_receiver_activity_main.setOnClickListener {
            val intent = Intent(this, ReceiverTestActivity::class.java)
            startActivity(intent)

        }

        tv_storage_activity_main.setOnClickListener {
            val intent = Intent(this, FilePersistenceActivity::class.java)
            startActivity(intent)

        }

        tv_database_activity_main.setOnClickListener {
            val intent = Intent(this, SQLiteActivity::class.java)
            startActivity(intent)
        }

        tv_cp_activity_main.setOnClickListener {
            val intent = Intent(this, CPTestActivity::class.java)
            startActivity(intent)

        }

        tv_media_activity_main.setOnClickListener {
            val intent = Intent(this, MediaActivity::class.java)
            startActivity(intent)

        }

        tv_net_activity_main.setOnClickListener {
            startActivityTest<NetActivity>(this) {}
        }

        tv_material_activity_main.setOnClickListener {
            startActivityTest<MaterialActivity>(this) {}
        }
        tv_jetpack_activity_main.setOnClickListener {
            startActivityTest<JetpackMainActivity>(this) {}
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1608 -> if (resultCode == Activity.RESULT_OK) {
                val str = data?.getStringExtra("back_to_main")
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
            }
        }
    }
}