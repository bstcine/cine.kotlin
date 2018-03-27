package com.bstcine.kotlin.base

import android.support.v4.app.Fragment
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by itwangxiang on 27/03/2018.
 */

open class BaseFragment : Fragment() {

    open fun <M> addObserver(observable: Observable<M>, observer: Observer<M>) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}
