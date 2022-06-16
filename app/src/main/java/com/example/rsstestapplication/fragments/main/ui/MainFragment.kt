package com.example.rsstestapplication.fragments.main.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.rsstestapplication.R
import com.example.rsstestapplication.service.RssDiffUpdateService
import com.example.rsstestapplication.fragments.main.adapters.RssesAdapter
import com.example.rsstestapplication.fragments.main.presenter.MainFragmentPresenter
import com.example.rsstestapplication.fragments.main.view.MainView
import com.example.rsstestapplication.models.ItemRssModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MainFragment: MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter
    lateinit var swipeContainer: SwipeRefreshLayout
    lateinit var coutNewRss: FloatingActionButton
    private lateinit var rssesAdapter: RssesAdapter

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
        swipeContainer = view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            presenter.updateRss()
            swipeContainer.isRefreshing = false
        }
        coutNewRss = view.findViewById(R.id.diff_rss_fab)
        val intent = Intent(activity, RssDiffUpdateService::class.java)

        Log.i(ContentValues.TAG, "Загрузка ostartService $activity")
    }
    override fun onRssLoaded(rsses: List<ItemRssModel>) {
        rssesAdapter.rsses = rsses
    }

    override fun openDetailsFragment(itemRssModel: ItemRssModel) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(itemRssModel)
        findNavController().navigate(action)
        Log.i(ContentValues.TAG, "Загрузка openDetailsFragment")
    }

    override fun showCoutNewRss(diffRss: Int) {
        coutNewRss.visibility = View.VISIBLE
        coutNewRss.setOnClickListener{
            presenter.updateRss()
            coutNewRss.visibility = View.GONE
        }
    }
}