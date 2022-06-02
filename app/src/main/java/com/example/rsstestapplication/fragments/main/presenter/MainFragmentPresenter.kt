package com.example.rsstestapplication.fragments.main.presenter

import com.example.rsstestapplication.fragments.main.view.MainView
import com.example.rsstestapplication.models.ItemRssModel
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenter.InjectPresenter

@InjectViewState
class MainFragmentPresenter() : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRsses()
    }

    fun loadRsses() {
        val listtestrss = mutableListOf<ItemRssModel>()
        val testItem = ItemRssModel("name","10/01", "","","")
        listtestrss.add(testItem)
        viewState.onRssLoaded(listtestrss)
    }

    fun showDetails(rss: ItemRssModel) {
        viewState.openDetailsFragment(rss)
    }


}