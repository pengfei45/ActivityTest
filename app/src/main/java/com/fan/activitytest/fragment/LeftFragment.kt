package com.fan.activitytest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.left_fragment.*

class LeftFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.left_fragment, container, false)
    }

    fun replaceText(str:String){
        left_btn.text = str
    }
}