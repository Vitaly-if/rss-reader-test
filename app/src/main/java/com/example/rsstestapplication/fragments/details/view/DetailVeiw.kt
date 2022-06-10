package com.example.rsstestapplication.fragments.details.view

import com.example.rsstestapplication.models.ItemRssModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailVeiw : MvpView {

    fun showRss(rss: ItemRssModel)

    fun openResource(url: String)
}