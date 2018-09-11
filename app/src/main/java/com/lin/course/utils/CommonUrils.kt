package com.lin.course.utils

import android.content.Context


/**
 * Author by ljz
 * PS:
 */
class CommonUrils {
    companion object {

        /**
         * 根据图片的名称获取对应的资源id
         */
        fun getMipmapResourceID(context: Context, resourceName: String): Int {
            val res = context.resources
            return res.getIdentifier(resourceName, "mipmap", context.packageName)
        }
    }
}