package com.lin.course.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.lin.course.R
import com.lin.course.adapter.CourseListAdapter
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseListEntity
import kotlinx.android.synthetic.main.activity_course_list.*

class CourseListActivity : AppCompatActivity() {

    lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        title = intent.getStringExtra("title")
        Log.e("---------title-----", "---"+title)

        val list = ArrayList<CourseListEntity>()

        val courseListAdapter = CourseListAdapter(list)
        rvCourseList.adapter = courseListAdapter
        rvCourseList.layoutManager = LinearLayoutManager(this)

        list.addAll(DBManager.instance!!.seekCourseList(title))
        Log.e("size", "list" + list.size)
        courseListAdapter.notifyDataSetChanged()
    }
}
