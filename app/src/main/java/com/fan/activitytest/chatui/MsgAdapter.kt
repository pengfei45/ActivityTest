package com.fan.activitytest.chatui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fan.activitytest.R
import java.lang.IllegalArgumentException

class MsgAdapter(val msgList: List<MsgBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

    inner class RightViewHolder(view1: View) : RecyclerView.ViewHolder(view1) {
        val rightMsg: TextView = view1.findViewById(R.id.rightMsg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == MsgBean.TYPE_RECEIVER) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }

    override fun getItemCount(): Int = msgList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msgBean = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msgBean.content
            is RightViewHolder -> holder.rightMsg.text = msgBean.content
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return msgList[position].type
    }
}