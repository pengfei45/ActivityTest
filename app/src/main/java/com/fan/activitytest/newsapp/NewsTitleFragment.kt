package com.fan.activitytest.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.news_title_frag.*
import java.lang.StringBuilder

class NewsTitleFragment : Fragment() {

    private var isTwoPane = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //通过在Activity中能否找到一个id为newsContentLayout的View，来判断当前是双页模式还是单页模式
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null

        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView.adapter = adapter
    }

    private fun getNews(): List<News> {
        val list = ArrayList<News>()
        for (i in 1..50) {
            list.add(News("This is News Title $i", getRandomLengthString("This is news content $i .")))
        }
        return list
    }

    private fun getRandomLengthString(s: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(n)
        }
        return builder.toString()
    }


    inner class NewsAdapter(val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTwoPane) {//双页模式
                    val fragment = newsContentFrag as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {  //单页模式
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun getItemCount() = newsList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[holder.adapterPosition]
            holder.newsTitle.text = news.title
        }
    }
}