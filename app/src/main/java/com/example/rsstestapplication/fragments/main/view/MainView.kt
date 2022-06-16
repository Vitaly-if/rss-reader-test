package com.example.rsstestapplication.fragments.main.view

import com.example.rsstestapplication.models.ItemRssModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun onRssLoaded(rsses: List<ItemRssModel>)

    @StateStrategyType(value = SkipStrategy::class)
    fun openDetailsFragment(itemRssModel: ItemRssModel)

    fun showCoutNewRss(diffRss: Int)



}