package com.lin.course.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.HomeCourseTypeAdapter
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseTypeEntity
import com.lin.course.ui.CourseListActivity
import com.lin.course.utils.DateManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jsoup.Jsoup

class HomeFragment : Fragment(), BaseQuickAdapter.OnItemClickListener {

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val intent = Intent(activity, CourseListActivity::class.java)
        val title = (adapter.getItem(position) as CourseTypeEntity).typeName
        intent.putExtra("title", title)
        startActivity(intent)
    }


    val url = "https://www.runoob.com/"
    val list = ArrayList<CourseTypeEntity>()
    lateinit var homeCourseTypeAdapter : HomeCourseTypeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        for (i in 0..9) {
            list.add(CourseTypeEntity("a$i"))
        }

        homeCourseTypeAdapter = HomeCourseTypeAdapter(list)
        homeCourseTypeAdapter.onItemClickListener = this
        view.rv_home.layoutManager = GridLayoutManager(activity, 2)
        view.rv_home.adapter = homeCourseTypeAdapter


        Thread(Runnable {
            DateManager.getData()
            DateManager.getCourseData("https://www.runoob.com/mongodb/mongodb-tutorial.html")
        }).start()

        list.clear()
        list.addAll(DBManager.instance!!.seekCourseType())
        homeCourseTypeAdapter.notifyDataSetChanged()

        return view
    }

}
