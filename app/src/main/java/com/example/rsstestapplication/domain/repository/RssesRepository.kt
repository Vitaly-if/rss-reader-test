package com.example.rsstestapplication.domain.repository

import android.content.ContentValues
import android.util.Log
import com.example.rsstestapplication.domain.remote.RssFeedFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class RssesRepository() {
//val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/World.xml")
    suspend fun refreshRsses() {
        withContext(Dispatchers.IO) {
            val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/World.xml")
            RssFeedFetcher(callback = { list ->
                list.forEach {
                    Log.i(ContentValues.TAG, "Работает ${it.titleRss}")
                }

            }
            ).execute(url)

        //val playlist = Network.devbytes.getPlaylist().await()
            //database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}