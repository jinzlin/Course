package com.lin.course.ui.home

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.ChapterAdapter
import com.lin.course.base.BaseFragment
import com.lin.course.bean.model.DbModel
import com.lin.course.db.entity.ChapterEntity
import com.lin.course.ui.CourseListActivity
import com.lin.course.utils.DataManager
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Author by ljz
 * PS:
 */
class HomeFragment : BaseFragment(), BaseQuickAdapter.OnItemClickListener {

    private val list = ArrayList<ChapterEntity>()
    lateinit var chapterAdapter : ChapterAdapter

    override fun initResource(): Int {
        return R.layout.fragment_home
    }

    override fun initData(view: View) {
        chapterAdapter = ChapterAdapter(list)
        chapterAdapter.onItemClickListener = this
        view.rv_home.layoutManager = GridLayoutManager(activity, 2)
        view.rv_home.adapter = chapterAdapter

        val dateList = DataManager.getChapterData(this)
        if (dateList.size > 0){
            refresh(dateList)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val intent = Intent(activity, CourseListActivity::class.java)
        val chapterName = (adapter.getItem(position) as ChapterEntity).name
        intent.putExtra("ChapterName", chapterName)
        Log.e("-------chapterName", "--0")
        Log.e("-------chapterName", "--0" + chapterName)
        startActivity(intent)
    }

    override fun onDbSuccess(tag: Int, t: DbModel) {
        super.onDbSuccess(tag, t)
        refresh(t.chapterList)
    }

    fun refresh(data : ArrayList<ChapterEntity>){
        list.clear()
        list.addAll(data)
        chapterAdapter.notifyDataSetChanged()
    }
}
