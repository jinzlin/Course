package com.lin.course.db

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.lin.course.db.entity.SectionEntity
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.db.entity.ChapterEntity
import com.lin.course.db.gen.CourseListEntityDao
import com.lin.course.db.gen.DaoMaster
import com.lin.course.db.gen.DaoSession
import com.lin.course.db.gen.SectionEntityDao

/**
 * Author by ljz
 * PS:
 */
class DBManager private constructor(context: Context, dbName: String) {

    var db: SQLiteDatabase? = null
        private set
    var daoSession: DaoSession? = null
        private set

    init {
        setDatabase(context, dbName)
    }

    /**
     * 设置greenDao
     */
    private fun setDatabase(context: Context, dbName: String) {
        val mHelper = DaoMaster.DevOpenHelper(context, dbName, null)
        db = mHelper.writableDatabase
        daoSession = DaoMaster(db).newSession()
    }

    companion object {

        var instance: DBManager? = null
            private set

        fun init(context: Context, dbName: String) {
            if (instance == null) {
                synchronized(DBManager::class.java) {
                    if (instance == null) {
                        instance = DBManager(context, dbName)
                    }
                }
            }
        }
    }

    fun insertChapter(chapterEntity: ChapterEntity) {
        try {
            Log.e("----db", "-" + chapterEntity.toString())
            daoSession!!.insert(chapterEntity)
        } catch (e: SQLiteConstraintException) {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun seekChapter(): List<ChapterEntity> {
        val list = ArrayList<ChapterEntity>()
        try {
            list.addAll(daoSession!!.chapterEntityDao.queryBuilder().list())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }


    fun insertCourse(courseListEntity: CourseListEntity){
        try {
            Log.e("----db", "-" + courseListEntity.toString())
            daoSession!!.courseListEntityDao.insert(courseListEntity)
        } catch (e1: SQLiteConstraintException) {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun seekCourseList(chapterName: String): List<CourseListEntity> {
        val list = ArrayList<CourseListEntity>()
        try {
            list.addAll(daoSession!!
                    .courseListEntityDao
                    .queryBuilder()
                    .where(CourseListEntityDao.Properties.ChapterName.eq(chapterName))
                    .list())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun insertSection(sectionEntity: SectionEntity) {
        try {
            Log.e("----db", "-" + sectionEntity.toString())
            val data = daoSession!!.sectionEntityDao.queryBuilder()
                    .where(SectionEntityDao.Properties.Title.eq(sectionEntity.title), SectionEntityDao.Properties.Link.eq(sectionEntity.link))
                    .list()
            if (data.size == 0) {
                Log.e("----db", "-新数据")
                daoSession!!.insert(sectionEntity)
            }
        } catch (e1: SQLiteConstraintException) {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun seekSectionList(courseName: String): List<SectionEntity> {
        val list = ArrayList<SectionEntity>()
        try {
            list.addAll(daoSession!!.sectionEntityDao.queryBuilder()
                    .where(SectionEntityDao.Properties.CourseName.eq(courseName))
                    .list())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun seekSection(link: String): SectionEntity? {
        val list = ArrayList<SectionEntity>()
        list.addAll(daoSession!!.sectionEntityDao.queryBuilder()
                .where(SectionEntityDao.Properties.Link.eq(link))
                .list())
        if (list.size > 0) {
            return list[0]
        }
        return null
    }


    fun updataSectionLink(sectionEntity: SectionEntity) {
        try {
            Log.e("----db", "-" + sectionEntity.toString())
            daoSession!!.sectionEntityDao.update(sectionEntity)
        } catch (e1: SQLiteConstraintException) {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
