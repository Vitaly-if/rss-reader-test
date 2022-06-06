package com.example.rsstestapplication.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rsstestapplication.models.ItemRssModel


@Entity
data class RssDataBase constructor(
    @PrimaryKey
    val linkSource: String,
    val titleRss: String,
    val linkImg: String,
    val pubDate: String,
    val description: String
)
fun List<RssDataBase>.asDomainModel(): List<ItemRssModel> {
    return map {
        ItemRssModel(
            linkSource = it.linkSource,
            titleRss = it.titleRss,
            linkImg = it.linkImg,
            pubDate = it.pubDate,
            description = it.description)
    }
}