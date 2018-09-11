package com.lin.course

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.githang.statusbar.StatusBarCompat
import com.lin.course.bean.pojo.TabPojo
import com.lin.course.ui.home.HomeFragment
import com.lin.course.ui.my.MyFragment
import com.lin.course.ui.study.StudyFragment
import kotlinx.android.synthetic.main.activity_main.*
import wer.xds.fds.AdManager
import wer.xds.fds.nm.cm.ErrorCode
import wer.xds.fds.nm.sp.SpotManager
import wer.xds.fds.nm.sp.SpotRequestListener

/**
 * Author by ljz
 * PS:
 */
class MainActivity : AppCompatActivity(), OnTabSelectListener, ViewPager.OnPageChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCommonTabLayout()
    }

    private fun initCommonTabLayout() {

        val mTabs = ArrayList<Fragment>()
        val mTitles = arrayOf(getString(R.string.main_home), getString(R.string.main_study), getString(R.string.main_my))
        val mIconSelectIds = intArrayOf(R.mipmap.ic_home_select_o, R.mipmap.ic_study_select_o,R.mipmap.ic_my_select_o)
        val mIconUnselectIds = intArrayOf(R.mipmap.ic_home_unselect_o, R.mipmap.ic_study_unselect_o,R.mipmap.ic_my_unselect_o)
        val mTabEntities = ArrayList<CustomTabEntity>()

        for (i in mTitles.indices) {
            mTabEntities.add(TabPojo(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        ctMain.setTabData(mTabEntities)
        ctMain.hideMsg(1)
        ctMain.getMsgView(1).backgroundColor = resources.getColor(R.color.colorAccent)
        ctMain.setOnTabSelectListener(this)
        vpMain.addOnPageChangeListener(this)

        mTabs.add(HomeFragment())
        mTabs.add(StudyFragment())
        mTabs.add(MyFragment())

        vpMain.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                return mTabs[p0]
            }

            override fun getCount(): Int {
                return mTabs.size
            }
        }

        vpMain.currentItem = 0
    }


    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {
        ctMain.currentTab = p0
    }

    override fun onTabSelect(position: Int) {
        vpMain.currentItem = position
    }

    override fun onTabReselect(position: Int) {

    }

}


