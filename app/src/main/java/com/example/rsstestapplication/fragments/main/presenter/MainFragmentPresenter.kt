package com.example.rsstestapplication.fragments.main.presenter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.rsstestapplication.app.BaseApplication
import com.example.rsstestapplication.di.databaseModule
import com.example.rsstestapplication.domain.database.RssesDatabase
import com.example.rsstestapplication.domain.repository.IRssesRepository
import com.example.rsstestapplication.domain.repository.RssesRepository


import com.example.rsstestapplication.fragments.main.view.MainView
import com.example.rsstestapplication.models.ItemRssModel
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenter.InjectPresenter
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import org.koin.core.component.inject
import org.koin.core.component.get

import org.koin.java.KoinJavaComponent.inject


@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainView>(), KoinComponent {

    private val repository: RssesRepository = get()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRsses()
        loadDiffSizeRssbetweenDatabaseNetwork()
    }

    fun updateRss() {
        loadRsses()
    }

    private fun loadRsses() {

        GlobalScope.launch {
            repository.refreshRsses(true)
            repository.loadRsses(object : IRssesRepository.LoadRssesCallback {
                override fun onRssLoaded(loadedRssList: List<ItemRssModel>) {

                    viewState.onRssLoaded(loadedRssList)
                }

                override fun onrssNotAvailable() {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun loadDiffSizeRssbetweenDatabaseNetwork() {
        repository.loadDiffSizeBetweenDatabaseNetwork(object :
            IRssesRepository.LoadDiffSizeCallback {
            override fun onDiffSizeLoaded(LoadedDiffSize: Int) {
                viewState.showCoutNewRss(LoadedDiffSize)
            }

        })
    }

    fun showDetails(rss: ItemRssModel) {
        viewState.openDetailsFragment(rss)
    }


}