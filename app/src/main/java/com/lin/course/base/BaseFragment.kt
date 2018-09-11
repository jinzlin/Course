package com.lin.course.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githang.statusbar.StatusBarCompat
import com.lin.course.R
import com.lin.course.bean.model.DbModel
import io.reactivex.disposables.Disposable

/**
 * Author by ljz
 * PS:
 */
abstract class BaseFragment: Fragment(), RxDbCallback<DbModel>{

    lateinit var progressDialog : ProgressDialog

    abstract fun initResource() : Int

    abstract fun initData(view : View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(initResource(), container, false)
        initData(view)
        return view
    }

    override fun onDbStart(tag : Int, d: Disposable) {
        progressDialog = ProgressDialog(activity)
        progressDialog.show()
    }

    override fun onDbSuccess(tag : Int, t: DbModel) {

    }

    override fun onDbError(tag : Int, e: Throwable) {

    }

    override fun onDbComplete(tag : Int) {
        progressDialog.dismiss()
    }
}