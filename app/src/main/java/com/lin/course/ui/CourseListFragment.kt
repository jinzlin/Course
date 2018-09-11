package com.lin.course.ui

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.CourseAdapter
import com.lin.course.base.BaseFragment
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseListEntity
import kotlinx.android.synthetic.main.fragment_course_list.view.*
import java.util.*

class CourseListFragment : BaseFragment(), BaseQuickAdapter.OnItemClickListener {

     var chapterName : String = ""
    val picLists = ArrayList<Int>()

    override fun initResource(): Int {
        return R.layout.fragment_course_list
    }

    override fun initData(view: View) {
        chapterName = arguments!!.getString("ChapterName", "")

        val list = ArrayList<CourseListEntity>()
        for (i in 1..46) {
            picLists.add(Random().nextInt(46))
        }
        val courseListAdapter = CourseAdapter(list, picLists)
        view.rvCourseList.adapter = courseListAdapter
//        view.rvCourseList.layoutManager = GridLayoutManager(activity, 2)
        view.rvCourseList.layoutManager = LinearLayoutManager(activity)
        courseListAdapter.onItemClickListener = this

        list.addAll(DBManager.instance!!.seekCourseList(chapterName))
        courseListAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val intent = Intent(activity, SectionListActivity::class.java)
        val courseName = (adapter.getItem(position) as CourseListEntity).courseName
        val courseLink = (adapter.getItem(position) as CourseListEntity).courseLink

        intent.putExtra("Bg", picLists[position])
        intent.putExtra("CourseName", courseName)
        intent.putExtra("ChapterName", chapterName)
        intent.putExtra("CourseLink", courseLink)
        startActivity(intent)
    }
}
