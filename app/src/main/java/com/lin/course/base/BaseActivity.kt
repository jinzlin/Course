package com.lin.course.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.lin.course.bean.model.DbModel
import io.reactivex.disposables.Disposable

/**
 * Author by ljz
 * PS:
 */
abstract class BaseActivity : AppCompatActivity(), RxDbCallback<DbModel>{

    abstract fun initResource() : Int

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initResource())
        initData()
    }

    override fun onDbStart(tag: Int, d: Disposable) {

    }

    override fun onDbSuccess(tag: Int, t: DbModel) {

    }

    override fun onDbComplete(tag: Int) {

    }

    override fun onDbError(tag: Int, e: Throwable) {

    }
}