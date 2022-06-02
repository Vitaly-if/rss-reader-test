package com.example.rsstestapplication.fragments.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rsstestapplication.R
import com.example.rsstestapplication.fragments.main.view.MainView
import com.example.rsstestapplication.models.ItemRssModel
import moxy.MvpAppCompatFragment


class MainFragment: MvpAppCompatFragment(), MainView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onRssLoaded(rsses: List<ItemRssModel>) {
        TODO("Not yet implemented")
    }

    override fun openDetailsFragment(itemRssModel: ItemRssModel) {
        TODO("Not yet implemented")
    }
}