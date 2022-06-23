package com.example.rsstestapplication.service

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log
import android.widget.Toast
import com.example.rsstestapplication.domain.repository.RssesRepository
import com.example.rsstestapplication.fragments.main.presenter.MainFragmentPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.lang.Process
import java.util.*

class RssDiffUpdateService : Service(), KoinComponent {
private lateinit var mHandler: Handler
private lateinit var mRunnable: Runnable
private val repository: RssesRepository = get()


override fun onBind(intent: Intent): IBinder? {
    throw UnsupportedOperationException("Not yet implemented")
}

override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

    mHandler = Handler()
    mRunnable = Runnable { showRandomNumber() }
    mHandler.postDelayed(mRunnable, 5000)

    return START_STICKY
}

override fun onDestroy() {
    super.onDestroy()
    mHandler.removeCallbacks(mRunnable)
}

// Custom method to do a task
private fun showRandomNumber() {
    val rand = Random()
    val number = rand.nextInt(100)

    GlobalScope.launch {
        repository.refreshRsses(false)
    }

   Log.i(ContentValues.TAG,"Random Number : $number")
    mHandler.postDelayed(mRunnable, 5000)
}
}