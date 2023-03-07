package com.digidig.newsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digidig.newsapplication.api.ApiService
import com.digidig.newsapplication.api.NewsApi
import com.digidig.newsapplication.model.Article
import com.digidig.newsapplication.model.News
import com.digidig.newsapplication.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private val listNews = ArrayList<Article>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNews = findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true);

        val newsContent = getNews()
        if (newsContent!=null){
            listNews.addAll(newsContent.articles.subList(0,5))
            println(listNews.toString())
        }

    }

    private fun getNews() : NewsResponse? {
        val serviceGenerator = ApiService.buildService(NewsApi::class.java)
        val call = serviceGenerator.getBreakingNews()
        var newsRes : NewsResponse? = null

        call.enqueue( object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    newsRes = response.body();
                    showRecyclerList(response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return newsRes;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListHeroes(): ArrayList<News> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listHero = ArrayList<News>()
        for (i in dataName.indices) {
            val hero = News(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero

    }

    private fun showRecyclerList( list : List<Article>? = listNews) {
        if (list!=null){
            rvNews.layoutManager = LinearLayoutManager(this)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo).getResourceId(0,-1)
            val listNewsAdapter = ListNewsAdapter(list,dataPhoto)
            rvNews.adapter = listNewsAdapter

            listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Article) {
                    val intentWithArticle = Intent(this@MainActivity,NewsDetailActivity::class.java)
                    intentWithArticle.putExtra(NewsDetailActivity.EXTRA_NEWS,data)
                    startActivity(intentWithArticle)
                }
            })
        }

    }

    private fun showSelectedHero(news: Article) {
        Toast.makeText(this, "Kamu memilih " + news.title, Toast.LENGTH_SHORT).show()
    }
}
