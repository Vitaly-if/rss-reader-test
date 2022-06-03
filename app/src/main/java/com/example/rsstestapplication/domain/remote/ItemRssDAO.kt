package com.example.rsstestapplication.domain.remote

data class ItemRssDAO(
    var titleRss: String = "",

    var linkImg: String = "",

    var pubDate: String = "",

    var description: String = "",

    var linkSource: String = ""
) {
}