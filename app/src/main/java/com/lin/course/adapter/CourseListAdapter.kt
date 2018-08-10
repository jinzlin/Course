package com.lin.course.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.CourseListEntity

/**
 * Author by ljz
 * PS:
 */
class CourseListAdapter(data: MutableList<CourseListEntity>?) : BaseQuickAdapter<CourseListEntity, BaseViewHolder>(R.layout.item_course_list, data) {

    override fun convert(helper: BaseViewHolder, item: CourseListEntity) {
        helper.setText(R.id.tvCourseTitle, item.courseTitle)
                .setText(R.id.tvCourseContent, item.courseContent)
        Glide.with(mContext).load(item.courseImg).into(helper.getView(R.id.ivCourse))
    }
}