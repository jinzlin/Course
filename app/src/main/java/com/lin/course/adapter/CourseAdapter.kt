package com.lin.course.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.utils.CommonUrils
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author by ljz
 * PS:
 */
class CourseAdapter(data: MutableList<CourseListEntity>?, private val pics: ArrayList<Int>) : BaseQuickAdapter<CourseListEntity, BaseViewHolder>(R.layout.item_course, data) {

    override fun convert(helper: BaseViewHolder, item: CourseListEntity) {
        helper.setText(R.id.tvCourseTitle, item.courseName)
                .setText(R.id.tvCourseContent, item.courseContent)
                .setImageResource(R.id.ivBg, CommonUrils.getMipmapResourceID(mContext, "bg_pic" + pics[helper.layoutPosition]))
        Glide.with(mContext).load(item.courseImg).into(helper.getView(R.id.ivCourse))
    }
}