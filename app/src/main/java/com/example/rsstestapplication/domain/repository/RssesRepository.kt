package com.example.rsstestapplication.domain.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.rsstestapplication.domain.database.RssesDatabase
import com.example.rsstestapplication.domain.database.asDomainModel
import com.example.rsstestapplication.domain.remote.RssFeedFetcher
import com.example.rsstestapplication.domain.remote.asDatabaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.reactivestreams.Subscriber
import java.net.URL

class RssesRepository(val database: RssesDatabase) : IRssesRepository {

    val url = URL("https://rss.nytimes.com/services/xml/rss/nyt/World.xml")
    var sizeDatabase = 0
    val subjectDiffSize = PublishSubject.create<Int>()

    override fun loadRsses(callback: IRssesRepository.LoadRssesCallback) {
        loadRssesFromNetwork(callback)
    }
    private fun loadRssesFromNetwork(callback: IRssesRepository.LoadRssesCallback) {
        val dep = database.rssDao.getRsses()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callback.onRssLoaded(it.asDomainModel())
                sizeDatabase = it.size
                it.forEach {
                    // Log.i(ContentValues.TAG, "c базы данных ${it.titleRss}")
                }
            }

    }
    override fun loadDiffSizeBetweenDatabaseNetwork(callback: IRssesRepository.LoadDiffSizeCallback) {
        loadDiffSize(callback)
    }
    fun loadDiffSize(callback: IRssesRepository.LoadDiffSizeCallback) {
       val dep = subjectDiffSize.subscribe {
           callback.onDiffSizeLoaded(it)
       }

    }

    @SuppressLint("CheckResult")
    suspend fun refreshRsses() {
        withContext(Dispatchers.IO) {

            RssFeedFetcher(callback = { list ->
                //changeRss = list.size - sizeDatabase
                list.forEach {
                    // Log.i(ContentValues.TAG, "Работает ${it.titleRss}")
                }
               subjectDiffSize.onNext(list.size - sizeDatabase)
                GlobalScope.launch {
                    database.rssDao.clearAll()
                    database.rssDao.insertAll(*list.asDatabaseModel())

                }

                            }
            ).execute(url)
        }

    }


}