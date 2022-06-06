package com.example.rsstestapplication.domain.repository

import android.content.ContentValues
import android.util.Log
import com.example.rsstestapplication.domain.database.RssesDatabase
import com.example.rsstestapplication.domain.database.asDomainModel
import com.example.rsstestapplication.domain.remote.RssFeedFetcher
import com.example.rsstestapplication.domain.remote.asDatabaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class RssesRepository(val database: RssesDatabase): IRssesRepository {
//val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/World.xml")

   override fun loadRsses(callback: IRssesRepository.LoadRssesCallback) {
       loadRssesFromNetwork(callback)
    }

    private fun loadRssesFromNetwork(callback: IRssesRepository.LoadRssesCallback) {
val dep = database.rssDao.getRsses()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe {
        callback.onRssLoaded(it.asDomainModel())
        it.forEach {
            // Log.i(ContentValues.TAG, "c базы данных ${it.titleRss}")
        }
    }

    }

    suspend fun refreshRsses() {
        withContext(Dispatchers.IO) {
            val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/World.xml")
            RssFeedFetcher(callback = { list ->
                list.forEach {

                // Log.i(ContentValues.TAG, "Работает ${it.titleRss}")
                }
                GlobalScope.launch {
                    database.rssDao.clearAll()
                database.rssDao.insertAll(*list.asDatabaseModel())
               }

            }
            ).execute(url)
    }
        //val playlist = Network.devbytes.getPlaylist().await()
            //database.videoDao.insertAll(*playlist.asDatabaseModel())

    }


}