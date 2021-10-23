package com.fan.activitytest

import android.app.Activity
import android.app.AlertDialog
import android.app.ListActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.AlignmentSpan
import android.widget.Toast
import com.fan.activitytest.chatui.ChatUIActivity
import com.fan.activitytest.list.ListViewActivity
import com.fan.activitytest.recycler.RecyclerActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
//            val intent = Intent(context, SecondActivity::class.java)
//            intent.putExtra("param1",data1)
//            intent.putExtra("param2",data2)
//            context.startActivity(intent)

            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("param1", "data1")
                putExtra("param2", "data2")
            }
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_second_activity.setOnClickListener {
            val intent = Intent()
            intent.putExtra("back_to_main", "666")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        btn1_second_activity.setOnClickListener {
            actionStart(this, "data1", "data2")
        }

        btn3_second_activity.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            startActivity(intent)
        }

        btn4_second_activity.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        btn5_second_activity.setOnClickListener {
            val intent = Intent(this, ChatUIActivity::class.java)
            startActivity(intent)
        }


        btn2_second_activity.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("this is Dialog")
                setMessage("something important.")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
                }
                setPositiveButton("Cancel") { _, _ ->
                    Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("back_to_main", "666")
        setResult(Activity.RESULT_OK, intent)
        finish()

        super.onBackPressed()
    }
}