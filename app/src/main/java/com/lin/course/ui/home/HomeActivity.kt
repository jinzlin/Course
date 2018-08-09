package com.lin.course.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.lin.course.R
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseTypeEntity
import kotlinx.android.synthetic.main.activity_home.*
import org.jsoup.Jsoup

class HomeActivity : AppCompatActivity() {


    val url = "https://www.runoob.com/"
    val url2 = "https://www.runoob.com/"
    val url3 = "https://www.runoob.com/"
    val list = ArrayList<CourseTypeEntity>()
    lateinit var homeCourseTypeAdapter : HomeCourseTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        for (i in 0..9) {
            list.add(CourseTypeEntity("a$i"))
        }

        homeCourseTypeAdapter = HomeCourseTypeAdapter(list)

        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = homeCourseTypeAdapter


        Thread(Runnable {
            getData()
        }).start()

    }

    fun getData(){
        try {
            val document = Jsoup.connect(url).get()
            val titleList = document.select("[class=navto-nav]").eachText()
            Log.e("------", "--" + titleList.size)
            for (i in titleList.indices) {
                Log.e("------", "--" + titleList[i])
                DBManager.instance!!.insertCourseType(CourseTypeEntity(titleList[i]))
            }
            list.clear()
            list.addAll(DBManager.instance!!.seekCourseType())
            homeCourseTypeAdapter.notifyDataSetChanged()
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}
