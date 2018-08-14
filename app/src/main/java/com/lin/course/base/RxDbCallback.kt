package com.lin.course.base

import io.reactivex.disposables.Disposable

/**
 * Author by ljz
 * PS:
 */
interface RxDbCallback<T> {

    fun onDbStart(tag : Int, d : Disposable)

    fun onDbSuccess(tag : Int, t : T)

    fun onDbComplete(tag : Int)

    fun onDbError(tag : Int, e : Throwable )
}