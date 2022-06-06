package com.example.rsstestapplication.fragments.main.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.rsstestapplication.R
import com.example.rsstestapplication.domain.database.getDatabase
import com.example.rsstestapplication.domain.repository.IRssesRepository
import com.example.rsstestapplication.domain.repository.RssesRepository
import com.example.rsstestapplication.fragments.main.adapters.RssesAdapter
import com.example.rsstestapplication.fragments.main.presenter.MainFragmentPresenter
import com.example.rsstestapplication.fragments.main.view.MainView
import com.example.rsstestapplication.models.ItemRssModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MainFragment: MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter
    private lateinit var rssesAdapter: RssesAdapter
    lateinit var repository: RssesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rssesAdapter = RssesAdapter(ArrayList(0), presenter)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val recycler_rss: RecyclerView = view.findViewById(R.id.recycler_rss)
        recycler_rss.apply {
            adapter = rssesAdapter
            layoutManager = linearLayoutManager
        }
        val database = activity?.let { getDatabase(it) }
        repository = database?.let { RssesRepository(it) }!!
    }
    override fun onRssLoaded(rsses: List<ItemRssModel>) {
        GlobalScope.launch {
            repository.refreshRsses()
        }
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
        rssesAdapter.rsses = rsses
        Log.i(ContentValues.TAG, "Загрузка RecyclerView ${rsses[0].titleRss}")
    }

    override fun openDetailsFragment(itemRssModel: ItemRssModel) {
        Log.i(ContentValues.TAG, "Загрузка openDetailsFragment")
    }
}