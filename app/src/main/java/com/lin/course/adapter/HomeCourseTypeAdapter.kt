package com.lin.course.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.CourseTypeEntity

/**
 * Author by ljz
 * PS:
 */
class HomeCourseTypeAdapter(data: List<CourseTypeEntity>?) : BaseQuickAdapter<CourseTypeEntity, BaseViewHolder>(R.layout.item_home_course_type, data) {

    override fun convert(helper: BaseViewHolder, item: CourseTypeEntity) {
        helper.setText(R.id.tv_course_name, item.typeName)
    }

}
