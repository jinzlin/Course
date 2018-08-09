package com.lin.course.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.lin.course.db.entity.CourseTypeEntity

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

}
