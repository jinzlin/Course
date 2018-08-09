package com.lin.course

import android.app.Application

import com.lin.course.db.DBManager

/**
 * Author by ljz
 * PS:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DBManager.init(this, "course-db")
    }
}
