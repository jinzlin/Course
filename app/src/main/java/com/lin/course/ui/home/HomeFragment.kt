package com.lin.course.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lin.course.R
import com.lin.course.adapter.ChapterAdapter
import com.lin.course.base.BaseFragment
import com.lin.course.bean.model.DbModel
import com.lin.course.db.entity.ChapterEntity
import com.lin.course.ui.ChapterActivity
import com.lin.course.utils.DataManager
import com.lin.course.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.youth.banner.Banner
import com.youth.banner.Transformer


/**
 * Author by ljz
 * PS:
 */
class HomeFragment : BaseFragment(), BaseQuickAdapter.OnItemClickListener {

    private val list = ArrayList<ChapterEntity>()
    val chapterNames = ArrayList<String>()
    lateinit var chapterAdapter : ChapterAdapter


    override fun initResource(): Int {
        return R.layout.fragment_home
    }

    override fun initData(view: View) {
        chapterAdapter = ChapterAdapter(list)
        chapterAdapter.onItemClickListener = this
        view.rv_home.layoutManager = GridLayoutManager(activity, 2)
        view.rv_home.adapter = chapterAdapter

        val headerView = layoutInflater.inflate(R.layout.item_banner, null)
        chapterAdapter.addHeaderView(headerView)
        setBanner(headerView)
        val dateList = DataManager.getChapterData(this)
        if (dateList.size > 0){
            refresh(dateList)
        }
    }

    fun setBanner(headerView: View) {
        val banner = headerView.findViewById(R.id.banner) as Banner
        val imageList = ArrayList<Drawable>()
        imageList.add(resources.getDrawable(R.mipmap.bg_banner1))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner2))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner3))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner4))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner5))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner6))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner7))
        imageList.add(resources.getDrawable(R.mipmap.bg_banner8))
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(imageList)
        banner.setBannerAnimation(Transformer.ScaleInOut)
        banner.start()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//        val intent = Intent(activity, SlideableSpotAdActivity::class.java)
        val intent = Intent(activity, ChapterActivity::class.java)
        val chapterName = (adapter.getItem(position) as ChapterEntity).name

        intent.putExtra("ChapterName", chapterName)
        intent.putStringArrayListExtra("ChapterNames", chapterNames)
        startActivity(intent)
    }

    override fun onDbSuccess(tag: Int, t: DbModel) {
        super.onDbSuccess(tag, t)
        refresh(t.chapterList)
    }

    fun refresh(data : ArrayList<ChapterEntity>){
        list.clear()
        list.addAll(data)
        for (i in list.indices) {
            chapterNames.add(list[i].name)
        }
        chapterAdapter.notifyDataSetChanged()
    }
}
