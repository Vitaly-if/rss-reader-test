package com.example.rsstestapplication.domain.remote

import android.content.ContentValues
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class RssFeedFetcher (val callback: (List<ItemRssDAO>) -> Unit) : AsyncTask<URL, Void, List<ItemRssDAO>>() {


    // val reference = WeakReference(context)
    private var stream: InputStream? = null;
    override fun doInBackground(vararg params: URL?): List<ItemRssDAO>? {
        val connect = params[0]?.openConnection() as HttpURLConnection
        connect.readTimeout = 8000
        connect.connectTimeout = 8000
        connect.requestMethod = "GET"
        connect.connect();

        val responseCode: Int = connect.responseCode;
        var rssItems: List<ItemRssDAO>? = null
        if (responseCode == 200) {
            stream = connect.inputStream;


            try {
                val parser = RssParser()
                rssItems = parser.parse(stream!!)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return rssItems
    }

    override fun onPostExecute(result: List<ItemRssDAO>?) {
        super.onPostExecute(result)
        if (result != null && !result.isEmpty()) {

                //Log.i(ContentValues.TAG, "${it.titleRss} ссылка картинка ${it.linkImg} cсылка ресурс ${it.linkSource}")
                callback.invoke(result)

           // reference.get()?.updateRV(result)
        }

    }

}