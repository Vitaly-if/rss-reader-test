package com.example.rsstestapplication.fragments.details.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rsstestapplication.R
import com.example.rsstestapplication.fragments.details.presenter.DetailFragmentPresenter
import com.example.rsstestapplication.fragments.details.view.DetailVeiw
import com.example.rsstestapplication.models.ItemRssModel
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class DetailFragment : MvpAppCompatFragment(), DetailVeiw {

    @InjectPresenter
    lateinit var presenter: DetailFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): DetailFragmentPresenter {
        val args: DetailFragmentArgs by navArgs()
        return DetailFragmentPresenter(args.selectItemRss)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun showRss(rss: ItemRssModel) {
        val titleRss = view?.findViewById<TextView>(R.id.title_rss)
        val pubImg = view?.findViewById<ImageView>(R.id.imagePubRss)
        val descRss = view?.findViewById<TextView>(R.id.descr_rss)
        val buttonOpenResource = view?.findViewById<Button>(R.id.button_open_resource)
        titleRss?.text = rss.titleRss
        descRss?.text = rss.description
        pubImg?.let {
            Glide.with(this)
                .load(rss.linkImg)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(it)
        }
        buttonOpenResource?.setOnClickListener {
            presenter.openResource(rss.linkSource)
        }
    }

    override fun openResource(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}