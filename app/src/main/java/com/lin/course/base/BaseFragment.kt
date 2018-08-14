package com.lin.course.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lin.course.bean.model.DbModel
import io.reactivex.disposables.Disposable

/**
 * Author by ljz
 * PS:
 */
abstract class BaseFragment: Fragment(), RxDbCallback<DbModel>{

    abstract fun initResource() : Int

    abstract fun initData(view : View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(initResource(), container, false)
        initData(view)
        return view
    }

    override fun onDbStart(tag : Int, d: Disposable) {

    }

    override fun onDbSuccess(tag : Int, t: DbModel) {

    }

    override fun onDbError(tag : Int, e: Throwable) {

    }

    override fun onDbComplete(tag : Int) {

    }
}