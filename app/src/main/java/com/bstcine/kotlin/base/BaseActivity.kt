package com.bstcine.kotlin.base

import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by itwangxiang on 26/03/2018.
 */
open class BaseActivity : AppCompatActivity() {

    open fun <M> addObserver(observable: Observable<M>, observer: Observer<M>) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}