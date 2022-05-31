package com.example.rsstestapplication.models

import android.os.Parcel
import android.os.Parcelable

class ItemRssModel : Parcelable {

    var titleRss: String = ""

    var linkImg: String = ""

    var pubDate: String = ""

    var description: String = ""

    var linkSource: String = ""

    constructor(parcel: Parcel) {
        titleRss = parcel.readString().toString()
        linkImg = parcel.readString().toString()
        pubDate = parcel.readString().toString()
        description = parcel.readString().toString()
        linkSource = parcel.readString().toString()
    }

    constructor(
        titleRss: String, linkImg: String, pubDate: String, description: String,
        linkSource: String
    ) {
        this.titleRss = titleRss
        this.linkImg = linkImg
        this.description = description
        this.linkSource = linkSource
        this.pubDate = pubDate
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titleRss)
        parcel.writeString(linkImg)
        parcel.writeString(description)
        parcel.writeString(linkSource)
        parcel.writeString(pubDate)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemRssModel> {
        override fun createFromParcel(parcel: Parcel): ItemRssModel {
            return ItemRssModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemRssModel?> {
            return arrayOfNulls(size)
        }
    }
}