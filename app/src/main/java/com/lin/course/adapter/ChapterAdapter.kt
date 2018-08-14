package com.lin.course.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.ChapterEntity

/**
 * Author by ljz
 * PS:
 */
class ChapterAdapter(data: List<ChapterEntity>?) : BaseQuickAdapter<ChapterEntity, BaseViewHolder>(R.layout.item_chapter, data) {

    override fun convert(helper: BaseViewHolder, item: ChapterEntity) {
        helper.setText(R.id.tv_course_name, item.name)
    }

}
