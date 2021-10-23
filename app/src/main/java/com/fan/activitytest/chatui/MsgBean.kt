package com.fan.activitytest.chatui

class MsgBean(val content: String, val type: Int) {
    companion object {
        const val TYPE_RECEIVER = 0
        const val TYPE_SEND = 1
    }
}