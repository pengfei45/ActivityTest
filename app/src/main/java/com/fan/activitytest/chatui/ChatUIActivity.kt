package com.fan.activitytest.chatui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fan.activitytest.R
import com.fan.activitytest.list.Fruit
import kotlinx.android.synthetic.main.activity_chat_ui.*

class ChatUIActivity : AppCompatActivity() {

    private val msgList = ArrayList<MsgBean>()
    private lateinit var msgAdapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_ui)

        addMsg()
        if (!::msgAdapter.isInitialized) {
           // ::msgAdapter.isOpen
            msgAdapter = MsgAdapter(msgList)
        }
        chatUi_recycler.adapter = msgAdapter
        chaiUi_btn.setOnClickListener {
            val content = chatUi_edit.text.toString()
            if (content.isNotEmpty()) {
                val msgBean = MsgBean(content, MsgBean.TYPE_SEND)
                msgList.add(msgBean)
                msgAdapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新显示
                chatUi_recycler.scrollToPosition(msgList.size - 1) //将 RecyclerView 定位到最后一行
                chatUi_edit.setText("") //清空输入框的内容
            }
        }
    }

    private fun addMsg() {
        msgList.add(MsgBean("Hello guy.", MsgBean.TYPE_RECEIVER))
        msgList.add(MsgBean("Hello. Who is that?", MsgBean.TYPE_SEND))
        msgList.add(MsgBean("This is Tom. Nice talking to you.", MsgBean.TYPE_RECEIVER))
        msgList.add(MsgBean("me too.", MsgBean.TYPE_SEND))
    }
}