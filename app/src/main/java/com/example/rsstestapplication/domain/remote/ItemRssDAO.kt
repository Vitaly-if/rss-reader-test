package com.example.rsstestapplication.domain.remote


import androidx.lifecycle.Transformations.map
import com.example.rsstestapplication.domain.database.RssDataBase

data class ItemRssDAO(
    var titleRss: String = "",

    var linkImg: String = "",

    var pubDate: String = "",

    var description: String = "",

    var linkSource: String = ""
)
fun List<ItemRssDAO>.asDatabaseModel(): Array<RssDataBase> {
    return map {
        RssDataBase(
            titleRss = it.titleRss,
            linkImg = it.linkImg,
            pubDate = it.pubDate,
            description = it.description,
            linkSource = it.linkSource)
    }.toTypedArray()
}