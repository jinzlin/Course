package com.lin.course.adapter

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lin.course.R
import com.lin.course.db.entity.ChapterEntity
import com.lin.course.utils.CommonUrils

/**
 * Author by ljz
 * PS:
 */
class ChapterAdapter(data: List<ChapterEntity>?) : BaseQuickAdapter<ChapterEntity, BaseViewHolder>(R.layout.item_chapter, data) {

    override fun convert(helper: BaseViewHolder, item: ChapterEntity) {

        var pic = R.mipmap.ic_main_leaf1
        if (helper.layoutPosition < 10) {
            pic = CommonUrils.getMipmapResourceID(mContext, "ic_main_leaf" + (helper.layoutPosition + 1))
        }
        helper.setText(R.id.tv_course_name, item.name)
                .setImageResource(R.id.iv_course_pic, pic)
    }

}
