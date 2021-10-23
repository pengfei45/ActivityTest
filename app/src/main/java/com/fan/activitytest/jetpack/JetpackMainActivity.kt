package com.fan.activitytest.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.fan.activitytest.R
import com.fan.activitytest.jetpack.lifecycles.MyObserver
import com.fan.activitytest.jetpack.room.AppDatabase
import com.fan.activitytest.jetpack.room.UserEntity
import com.fan.activitytest.jetpack.viewmodel.MainViewModel
import com.fan.activitytest.jetpack.viewmodel.MainViewModelFactory
import com.fan.activitytest.jetpack.workmanager.SimpleWorker
import kotlinx.android.synthetic.main.activity_jetpack_main.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class JetpackMainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    private val oneTimeRequest by lazy {
        OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .addTag("simple")
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.MINUTES)
            .build()
//        val periodicRequest =
//            PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_main)
        lifecycle.addObserver(MyObserver(lifecycle))
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)

        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        clearBtn.setOnClickListener {
            viewModel.clear()
        }

        getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this, Observer {
            infoText.text = "${it.firstName} , ${it.lastName}"
        })

        viewModel.counter.observe(this, Observer {
            infoText.text = it.toString()
        })

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = UserEntity("Tom", "Brady", 20)
        val user2 = UserEntity("Tom", "Hanks", 32)

        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 26
                userDao.updateUser(user1)
            }
        }

        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("JetPackMainActivity", "$user")
                }
            }
        }

        doWorkBtn.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeRequest)
        }

        cancelWorkBtn.setOnClickListener {
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")
//            WorkManager.getInstance(this).cancelWorkById(oneTimeRequest.id)
//            WorkManager.getInstance(this).cancelAllWork()
        }

        WorkManager.getInstance(this).getWorkInfosByTagLiveData("simple")
            .observe(this, Observer {
                if (it[0].state == WorkInfo.State.SUCCEEDED) {
                    Log.e("JetpackMainActivity", "do work SUCCEEDED")
                }
            })

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeRequest.id)
            .observe(this, Observer {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        Log.e("JetpackMainActivity", "do work SUCCEEDED")
                    }
                    WorkInfo.State.CANCELLED -> {
                        Log.e("JetpackMainActivity", "do work CANCELLED")
                    }
                    WorkInfo.State.FAILED -> {
                        Log.e("JetpackMainActivity", "do work FAILED")
                    }
                    WorkInfo.State.RUNNING -> {
                        Log.e("JetpackMainActivity", "do work RUNNING")
                    }
                    WorkInfo.State.ENQUEUED -> {
                        Log.e("JetpackMainActivity", "do work ENQUEUED")
                    }
                    WorkInfo.State.BLOCKED -> {
                        Log.e("JetpackMainActivity", "do work BLOCKED")
                    }
                }
            })

    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}