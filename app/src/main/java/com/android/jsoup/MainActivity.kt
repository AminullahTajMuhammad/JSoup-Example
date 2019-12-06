package com.android.jsoup

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.jsoup.utils.gone
import com.android.jsoup.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MainActivity : AppCompatActivity() {

    val url = "https://www.aminullah.me"
    val titleList = arrayListOf<String>()
    val dateList =  arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWebsiteData()
    }

    private fun getWebsiteData() {
        val task = MyTask()
        task.execute()
    }

    inner class MyTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            progress_bar.visible()
            val doc: Document = Jsoup.connect(url).get()
            val element = doc.select("article[class=post-preview]")

            for(i in 0 until element.size) {
                val authorName = doc.select("h2[class=post-title]").eq(i)
                val title = authorName.text()

                val publishDate = doc.select("p[class=post-meta]").eq(i)
                val date = publishDate.text()

                titleList.add(title)
                dateList.add(date)
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress_bar.gone()
            recyclerView.apply {
                val mAdapter = Adapter(this@MainActivity, titleList, dateList)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mAdapter
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }
}
