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
class MainFragmentPresenter() : MvpPresenter<MainView>(), KoinComponent {

    private val repository : RssesRepository = get()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRsses()
    }

    fun loadRsses() {
      // val repository = RssesRepository()
        GlobalScope.launch {
          repository.refreshRsses()
            repository.loadRsses(object: IRssesRepository.LoadRssesCallback{
                override fun onRssLoaded(loadedRssList: List<ItemRssModel>) {
                    loadedRssList.forEach {
                        Log.i(ContentValues.TAG, "работает калэк с базы ${it.titleRss}")
                    }
                }
                override fun onrssNotAvailable() {
                    TODO("Not yet implemented")
                }
            })
        }

        val listtestrss = mutableListOf<ItemRssModel>()
        val testItem = ItemRssModel("name","10/01", "","","")
        listtestrss.add(testItem)
        viewState.onRssLoaded(listtestrss)
    }

    fun showDetails(rss: ItemRssModel) {
        viewState.openDetailsFragment(rss)
    }


}