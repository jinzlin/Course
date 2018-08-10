package com.lin.course.utils

import android.app.Activity
import android.util.Log
import com.lin.course.db.DBManager
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.db.entity.CourseTypeEntity
import org.jsoup.Jsoup

/**
 * Author by ljz
 * PS:
 */
class DateManager {
    companion object {

        fun getData() {
            val url = "https://www.runoob.com/"
            try {
                val document = Jsoup.connect(url).get()
                val elements0 = document.select("[class~=^codelist codelist-desktop cate]")
                val courseTypes = elements0.select("h2").eachText()
                for (i in courseTypes.indices) {
                    DBManager.instance!!.insertCourseType(CourseTypeEntity(courseTypes[i]))
                    val elements1 = elements0[i].select("a")
                    val courseLists = elements1.eachText()
                    for (j in courseLists.indices) {
                        val courseTitle = elements1[j].select("h4").text()
                        val courseContent = elements1[j].select("strong").text()
                        val courseLink = elements1[j].attr("href")
                        val courseImg = elements1[j].select("img").attr("src")
                        DBManager.instance!!.insertCourseList(CourseListEntity(courseTitle, courseContent, courseTypes[i], courseLink, courseImg))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getCourseData(url : String) {
            try {
                val document = Jsoup.connect(url).get()
                val elements0 = document.select("#leftcolumn")

                val i0 = elements0.select("a")
                val i1 = elements0.select("h2")

                val size = i0.size + i1.size
                val list = elements0.eachText()
                for (i in list.indices) {
                    Log.e("getCourseData-------", "--" + list[i] + "---" + list.size+ "---" + i)
                }
            } catch (e: Exception){

            }
        }
    }
}