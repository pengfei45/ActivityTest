package com.fan.activitytest.fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_fragment_test.*
import kotlinx.android.synthetic.main.left_fragment.*

class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)

        replaceFragment(RightFragment())

        left_btn.setOnClickListener {
            replaceFragment(OtherFragment())
        }

        btn_frag_text.setOnClickListener {
           val leftFragment = supportFragmentManager.findFragmentById(R.id.frag_left) as LeftFragment
            leftFragment.view?.setBackgroundColor(Color.parseColor("#2c5ecf"))
            leftFragment.replaceText("替换的文本")
        }

    }

    private fun replaceFragment(fragment:Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.right_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
