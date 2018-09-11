package com.lin.course.base

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.lin.course.bean.model.DbModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_section_list.*

/**
 * Author by ljz
 * PS:
 */
abstract class BaseActivity : AppCompatActivity(), RxDbCallback<DbModel>{

    lateinit var progressDialog : ProgressDialog

    abstract fun initResource() : Int

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initResource())
        initData()
    }

    fun setToolbar(toolbar: Toolbar, canBack: Boolean, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        if (canBack) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onDbStart(tag: Int, d: Disposable) {
        progressDialog = ProgressDialog(this)
        progressDialog.show()
    }

    override fun onDbSuccess(tag: Int, t: DbModel) {

    }

    override fun onDbComplete(tag: Int) {
        progressDialog.dismiss()
    }

    override fun onDbError(tag: Int, e: Throwable) {

    }
}