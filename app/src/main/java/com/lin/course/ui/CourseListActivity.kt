package com.lin.course.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.CourseAdapter
import com.lin.course.base.BaseActivity
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseListEntity
import kotlinx.android.synthetic.main.activity_course_list.*

class CourseListActivity : BaseActivity(), BaseQuickAdapter.OnItemClickListener {

    lateinit var chapterName : String

    override fun initResource(): Int {
        return R.layout.activity_course_list
    }

    override fun initData() {
        chapterName = intent.getStringExtra("ChapterName")

        val list = ArrayList<CourseListEntity>()

        val courseListAdapter = CourseAdapter(list)
        rvCourseList.adapter = courseListAdapter
        rvCourseList.layoutManager = LinearLayoutManager(this)
        courseListAdapter.onItemClickListener = this

        list.addAll(DBManager.instance!!.seekCourseList(chapterName))
        courseListAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val intent = Intent(this, SectionListActivity::class.java)
        val courseName = (adapter.getItem(position) as CourseListEntity).courseName
        val courseLink = (adapter.getItem(position) as CourseListEntity).courseLink
        intent.putExtra("CourseName", courseName)
        intent.putExtra("ChapterName", chapterName)
        intent.putExtra("CourseLink", courseLink)
        startActivity(intent)
    }
}
