package com.lin.course.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author by ljz
 * PS:
 */
class RxDbObserver<T>(private val rxDbCallback: RxDbCallback<T>, private val tag : Int) : Observer<T>{

    constructor(rxDbCallback: RxDbCallback<T>) : this (rxDbCallback, -1)

    override fun onComplete() {
        rxDbCallback.onDbComplete(tag)
    }

    override fun onSubscribe(d: Disposable) {
        rxDbCallback.onDbStart(tag, d)
    }

    override fun onNext(t: T) {
        rxDbCallback.onDbSuccess(tag, t)
    }

    override fun onError(e: Throwable) {
        rxDbCallback.onDbError(tag, e)
    }
}