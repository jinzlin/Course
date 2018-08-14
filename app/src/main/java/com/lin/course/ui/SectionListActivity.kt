package com.lin.course.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.SectionAdapter
import com.lin.course.base.BaseActivity
import com.lin.course.bean.model.DbModel
import com.lin.course.db.DBManager
import com.lin.course.db.entity.SectionEntity
import com.lin.course.utils.DataManager
import kotlinx.android.synthetic.main.activity_section_list.*

class SectionListActivity : BaseActivity(), BaseQuickAdapter.OnItemClickListener {

    lateinit var chapterName : String
    lateinit var courseName : String
    lateinit var courseLink : String
    private lateinit var sectionAdapter : SectionAdapter
    private val list = ArrayList<SectionEntity>()
    
    override fun initResource(): Int {
        return R.layout.activity_section_list
    }

    override fun initData() {
        chapterName = intent.getStringExtra("ChapterName")
        courseName = intent.getStringExtra("CourseName")
        courseLink = intent.getStringExtra("CourseLink")

        sectionAdapter = SectionAdapter(list)
        sectionAdapter.onItemClickListener = this
        rvSectionList.adapter = sectionAdapter
        rvSectionList.layoutManager = LinearLayoutManager(this)

        val sectionList = DataManager.getSectionData(this, chapterName, courseName, courseLink)
        if (sectionList.size > 0) {
            refresh(sectionList)
        }
    }

    override fun onDbSuccess(tag: Int, t: DbModel) {
        super.onDbSuccess(tag, t)
        refresh(t.sectionList)
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val intent = Intent(this, ContentActivity::class.java)
        val link = (adapter.getItem(position) as SectionEntity).link
        intent.putExtra("link", link)
        startActivity(intent)
    }


    fun refresh(data : ArrayList<SectionEntity>){
        list.clear()
        list.addAll(data)
        sectionAdapter.notifyDataSetChanged()
    }
}
