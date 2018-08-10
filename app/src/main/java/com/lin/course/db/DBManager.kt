package com.lin.course.db

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.lin.course.db.entity.CourseListEntity
import com.lin.course.db.entity.CourseTypeEntity
import com.lin.course.db.gen.CourseListEntityDao

import com.lin.course.db.gen.DaoMaster
import com.lin.course.db.gen.DaoSession

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

    fun insertCourseType(courseTypeEntity: CourseTypeEntity){
        try {
            Log.e("----db", "-" + courseTypeEntity.toString())
            daoSession!!.insert(courseTypeEntity)
        } catch (e : SQLiteConstraintException) {

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun seekCourseType() : List<CourseTypeEntity>{
        val list = ArrayList<CourseTypeEntity>()
        try {
            list.addAll(daoSession!!.courseTypeEntityDao.queryBuilder().list())
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return list
    }


    fun insertCourseList(courseListEntity: CourseListEntity){
        try {
            Log.e("----db", "-" + courseListEntity.toString())
            daoSession!!.insert(courseListEntity)
        } catch (e1 : SQLiteConstraintException) {

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun seekCourseList(type : String) : List<CourseListEntity>{
        val list = ArrayList<CourseListEntity>()
        try {
            list.addAll(daoSession!!.courseListEntityDao.queryBuilder().where(CourseListEntityDao.Properties.CourseType.eq(type)).list())
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return list
    }
}
