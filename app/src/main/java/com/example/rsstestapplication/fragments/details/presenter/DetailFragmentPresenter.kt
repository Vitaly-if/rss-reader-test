package com.example.rsstestapplication.fragments.details.presenter

import com.example.rsstestapplication.fragments.details.view.DetailVeiw
import com.example.rsstestapplication.models.ItemRssModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailFragmentPresenter(private val selectItemRss: ItemRssModel) :
    MvpPresenter<DetailVeiw>() {

    lateinit var rssModel: ItemRssModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRss()
    }

    private fun loadRss() {
        rssModel = selectItemRss
        viewState.showRss(rssModel)
    }

    fun openResource(url: String) {
        viewState.openResource(url)
    }
}