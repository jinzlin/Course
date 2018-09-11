package com.lin.course.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.lin.course.R
import com.lin.course.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course_list.*
import java.util.*


class ChapterActivity : BaseActivity() {

    var chapterNames = ArrayList<String>()
    var chapterName = ""
    private val mFragments = ArrayList<Fragment>()

    override fun initResource(): Int {
        return R.layout.activity_course_list
    }

    override fun initData() {
        setToolbar(toolbar, true, getString(R.string.allSection))

        chapterName = intent.getStringExtra("ChapterName")
        chapterNames = intent.getStringArrayListExtra("ChapterNames")
        var position = 0
        for (i in chapterNames.indices) {
            if (chapterName == chapterNames[i]) {
                position = i
            }
            val courseListFragment = CourseListFragment()
            val bundle = Bundle()
            bundle.putString("ChapterName", chapterNames[i])
            courseListFragment.arguments = bundle
            mFragments.add(courseListFragment)
        }
        val size = chapterNames.size
        val array = chapterNames.toArray(arrayOfNulls<String>(size)) as Array<String>

        vp_chapter.adapter = object :FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(p0: Int): Fragment {
                return mFragments[p0]
            }

            override fun getCount(): Int {
                return chapterNames.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return chapterNames[position]
            }
        }
        st_chapter.setViewPager(vp_chapter, array)
        st_chapter.currentTab = position
        vp_chapter.currentItem = position
    }
}
