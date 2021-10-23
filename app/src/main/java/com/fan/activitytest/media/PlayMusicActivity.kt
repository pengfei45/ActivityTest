package com.fan.activitytest.media

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_pkay_music.*

class PlayMusicActivity : BaseActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pkay_music)

        initMediaPlayer()


        initViews()

    }

    private fun initViews() {
        btnPlay_play_music.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start() //开始播放
            }
        }

        btnPause_play_music.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }

        btnStop_play_music.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }



    }

    private fun initMediaPlayer() {
        val assetsManager = assets
        val openFd = assetsManager.openFd("music.mp3")
        mediaPlayer.setDataSource(openFd.fileDescriptor,openFd.startOffset,openFd.length)
        mediaPlayer.prepare()
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}