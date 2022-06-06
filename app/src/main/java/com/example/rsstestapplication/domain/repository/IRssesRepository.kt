package com.example.rsstestapplication.domain.repository

import com.example.rsstestapplication.models.ItemRssModel

interface IRssesRepository {

    fun loadRsses(callback: LoadRssesCallback)

    interface LoadRssesCallback {

        fun onRssLoaded(loadedRssList: List<ItemRssModel>)

        fun onrssNotAvailable()
    }
}