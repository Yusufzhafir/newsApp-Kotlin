package com.digidig.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digidig.newsapplication.model.News
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private val list = ArrayList<News>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true);

        list.addAll(getListHeroes())
        println(list.toString());
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListHeroes(): ArrayList<News> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<News>()
        for (i in dataName.indices) {
            val hero = News(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(list)
        rvNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(news: News) {
        Toast.makeText(this, "Kamu memilih " + news.name, Toast.LENGTH_SHORT).show()
    }
}
