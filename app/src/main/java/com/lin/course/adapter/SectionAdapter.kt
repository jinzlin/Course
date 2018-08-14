package com.lin.course.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.SectionEntity

/**
 * Author by ljz
 * PS:
 */
class SectionAdapter(data: MutableList<SectionEntity>?) : BaseQuickAdapter<SectionEntity, BaseViewHolder>(R.layout.item_section, data) {
    override fun convert(helper: BaseViewHolder, item: SectionEntity) {
        helper.setText(R.id.tvCourseTitle, item.title)
    }
}