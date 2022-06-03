package com.example.rsstestapplication.domain.remote

import android.content.ContentValues
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class RssParser {
    private val rssItems = ArrayList<ItemRssDAO>()
    private var rssItem : ItemRssDAO ?= null
    private var text: String? = null

    fun parse(inputStream: InputStream):List<ItemRssDAO> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            var foundItem = false
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("item", ignoreCase = true)) {
                        // create a new instance of employee
                        foundItem = true
                        rssItem = ItemRssDAO()
                    } else if (tagname.equals("content", ignoreCase = true)) {
                        rssItem!!.linkImg = parser.getAttributeValue(2)
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("item", ignoreCase = true)) {
                        // add employee object to list
                        rssItem?.let { rssItems.add(it) }
                        foundItem = false
                    } else if ( foundItem && tagname.equals("title", ignoreCase = true)) {
                        rssItem!!.titleRss = text.toString()
                    } else if (foundItem && tagname.equals("link", ignoreCase = true)) {
                        if (text.toString().trim().length != 0) rssItem!!.linkSource = text.toString()
                    } else if (foundItem && tagname.equals("pubDate", ignoreCase = true)) {
                        rssItem!!.pubDate = text.toString()
                    } else if (foundItem && tagname.equals("description", ignoreCase = true)) {
                        rssItem!!.description = text.toString()
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return rssItems
    }
}