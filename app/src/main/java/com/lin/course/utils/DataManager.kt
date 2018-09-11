package com.lin.course.utils

import android.text.TextUtils
import android.util.Log
import com.lin.course.base.RxDbCallback
import com.lin.course.base.RxDbObserver
import com.lin.course.bean.model.DbModel
import com.lin.course.db.DBManager
import com.lin.course.db.entity.ChapterEntity
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.db.entity.SectionEntity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup

/**
 * Author by ljz
 * PS:
 */
class DataManager {
    companion object {

        fun getChapterData(rxDbCallback: RxDbCallback<DbModel>): ArrayList<ChapterEntity> {
            val url = "https://www.runoob.com/"

            val chapterList = ArrayList<ChapterEntity>()
            chapterList.addAll(DBManager.instance!!.seekChapter())
            if (chapterList.size > 0) {
                return chapterList
            }
            rxJavaDb(rxDbCallback, ObservableOnSubscribe {
                try {
                    val dbModel = DbModel()
                    val document = Jsoup.connect(url).get()
                    val elements0 = document.select("[class~=^codelist codelist-desktop cate]")
                    val courseTypes = elements0.select("h2").eachText()
                    for (i in courseTypes.indices) {
                        dbModel.chapterList.add(ChapterEntity(courseTypes[i]))
                        DBManager.instance!!.insertChapter(ChapterEntity(courseTypes[i]))
                        val elements1 = elements0[i].select("a")
                        val courseLists = elements1.eachText()
                        for (j in courseLists.indices) {
                            val courseTitle = elements1[j].select("h4").text()
                            val courseContent = elements1[j].select("strong").text()
                            val courseLink = elements1[j].attr("href")
                            val courseImg = elements1[j].select("img").attr("src")
                            DBManager.instance!!.insertCourse(CourseListEntity(courseTitle, courseContent, courseTypes[i], courseLink, courseImg))
                        }
                    }
                    it.onNext(dbModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
            return chapterList
        }

        fun getSectionData(rxDbCallback: RxDbCallback<DbModel>, chapterName: String, courseName: String, link: String): ArrayList<SectionEntity> {
            val sectionList = ArrayList<SectionEntity>()
            sectionList.addAll(DBManager.instance!!.seekSectionList(courseName))
            if (sectionList.size > 0) {
                return sectionList
            }

            rxJavaDb(rxDbCallback, ObservableOnSubscribe {
                try {
                    val dbModel = DbModel()
                    val document = Jsoup.connect("https:$link").get()
                    val elements0 = document.select("#leftcolumn")
                    val a = elements0.select("a")
                    var title = ""
                    val list = a.eachText()
                    for (i in list.indices) {
                        val href = a[i].attr("href")
                        val sectionEntity = SectionEntity(chapterName, courseName, list[i], href, "")
                        dbModel.sectionList.add(sectionEntity)

                        DBManager.instance!!.insertSection(sectionEntity)
                        if (link.equals(href)) {
                            title = list[i]
                        }
                    }
                    // 去除自动生成换行符"\n"
                    document.outputSettings().prettyPrint(false)
                    val body = document.body().select(".article-body").html()
                    DBManager.instance!!.updataSectionLink(SectionEntity(chapterName, courseName, title, link, body))

                    it.onNext(dbModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })

            return sectionList
        }

        fun getBodyData(rxDbCallback: RxDbCallback<DbModel>, link: String): String? {
            val sectionEntity = DBManager.instance!!.seekSection(link)
            if (!TextUtils.isEmpty(sectionEntity?.body)) {
                return sectionEntity!!.body
            }

            rxJavaDb(rxDbCallback, ObservableOnSubscribe {
                try {
                    val dbModel = DbModel()
                    val document = Jsoup.connect("https://www.runoob.com$link").get()
                    // 去除自动生成换行符"\n"
                    document.outputSettings().prettyPrint(false)
                    val body = document.select(".article-body").html()

                    if (sectionEntity != null) {
                        sectionEntity.link = link
                        sectionEntity.body = body
                        DBManager.instance!!.updataSectionLink(sectionEntity)
                        dbModel.sectionEntity = sectionEntity
                    }
                    it.onNext(dbModel)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
            return null
        }


        private fun <T> rxJavaDb(rxDbCallback: RxDbCallback<T>, observableOnSubscribe: ObservableOnSubscribe<T>) {
            Observable
                    .create(observableOnSubscribe)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(RxDbObserver(rxDbCallback))
        }
    }

}

