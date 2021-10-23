package com.fan.activitytest.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import com.fan.activitytest.SecondActivity
import kotlinx.android.synthetic.main.activity_media.*
import java.io.File

class MediaActivity : BaseActivity() {

    private val takePhoto = 1
    private val fromAlbum = 2
    lateinit var imgUri: Uri
    lateinit var outputImg: File


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//版本判断
            val channel1 =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel1)
            val channel2 =
                NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }

        tvSendNotice_media.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "normal").setContentTitle("通知标题")
//                .setContentText("今天是2021年8月7日，")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("唧唧复唧唧，木兰当户织。不闻机杼声，唯问女叹息。问女何所思，问女何所忆？女亦无所思，女亦无所忆。")
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image))
                )
                .setSmallIcon(R.drawable.apple_pic)
                .setContentIntent(pi)
//                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.apple_pic))
                .build()
            manager.notify(1, notification)
        }

        tvTakePhoto_media.setOnClickListener {
            //创建File 对象，用于存储拍照后的照片
            outputImg = File(externalCacheDir, "output_image.png")
            if (outputImg.exists()) {
                outputImg.delete()
            }

            outputImg.createNewFile()
            imgUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "com.fan.activitytest.fileProvider", outputImg)
            } else {
                Uri.fromFile(outputImg)
            }

            //启动相机
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
            startActivityForResult(intent, takePhoto)
        }

        tvGetPhoto_media.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)  //打开文件选择器
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"   //指定只显示图片
            startActivityForResult(intent, fromAlbum)
        }

        tvPlayMusic_media.setOnClickListener {
            startActivity(Intent(this,PlayMusicActivity::class.java))
        }

        tvPlayVideo_media.setOnClickListener {
            startActivity(Intent(this,PlayVideoActivity::class.java))
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            takePhoto -> {
                if (resultCode == RESULT_OK) {
                    //将拍摄的图片显示
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imgUri))
                    imgPhoto_media.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
            fromAlbum -> {
                if (resultCode == RESULT_OK && data != null) {
                    data.data?.let { it ->
                        contentResolver.openFileDescriptor(it, "r").use {
                            val bitmap =
                                BitmapFactory.decodeFileDescriptor(it?.fileDescriptor)
                            imgPhoto_media.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }

    }

    //如果需要，旋转图片
    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImg.path)
        return when (exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotateBitmap
    }


}