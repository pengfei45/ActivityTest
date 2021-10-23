package com.fan.activitytest.media

import android.net.Uri
import android.os.Bundle
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_pkay_music.videoView_play_video
import kotlinx.android.synthetic.main.activity_pkay_video.*

class PlayVideoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pkay_video)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView_play_video.setVideoURI(uri)

        initViews()

    }

    private fun initViews() {
        btnPlay_play_video.setOnClickListener {
            if (!videoView_play_video.isPlaying){
                videoView_play_video.start() //开始播放
            }
        }

        btnPause_play_video.setOnClickListener {
            if (videoView_play_video.isPlaying){
                videoView_play_video.pause()  //暂停播放
            }
        }

        btnStop_play_video.setOnClickListener {
            if (videoView_play_video.isPlaying){
               videoView_play_video.resume()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        videoView_play_video.suspend()
    }
}