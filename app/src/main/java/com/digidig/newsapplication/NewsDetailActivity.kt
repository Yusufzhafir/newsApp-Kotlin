package com.digidig.newsapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.digidig.newsapplication.model.Article

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS = "extra_news";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val imgv = findViewById<ImageView>(R.id.newsBanner)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvAuth = findViewById<TextView>(R.id.tvAuthor)
        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvdesc = findViewById<TextView>(R.id.tvDescription)


        val news = intent.getParcelableExtra<Article>(EXTRA_NEWS)

        if (news!=null){
            tvAuth.text = news.author
            tvTitle.text = news.title
            tvDate.text = news.publishedAt
            tvdesc.text = news.content
            Glide.with(this).load(news.urlToImage).into(imgv)
        }

    }
}