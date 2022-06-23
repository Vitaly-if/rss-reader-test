package com.example.rsstestapplication.service

import android.app.Service

import android.content.Intent
import android.os.*
import com.example.rsstestapplication.domain.repository.RssesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RssDiffUpdateService : Service(), KoinComponent {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private val repository: RssesRepository = get()

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        mHandler = Handler()
        mRunnable = Runnable { repositoryUpdate() }
        mHandler.postDelayed(mRunnable, 5000)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    private fun repositoryUpdate() {

        GlobalScope.launch {
            repository.refreshRsses(false)
        }
        mHandler.postDelayed(mRunnable, 5000)
    }
}