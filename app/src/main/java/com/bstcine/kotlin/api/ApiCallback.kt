package com.bstcine.kotlin.api

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.bstcine.kotlin.model.base.ResModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * Created by itwangxiang on 26/03/2018.
 */
abstract class ApiCallback<M> : Observer<ResModel<M>> {
    abstract fun onSuccess(model: M)
    abstract fun onFailure(msg: String)
    abstract fun onFinish()

    override fun onComplete() {
        LogUtils.d("onComplete")
        onFinish()
    }

    override fun onSubscribe(d: Disposable) {
        LogUtils.d("onSubscribe")
    }

    override fun onNext(baseResponse: ResModel<M>) {
        LogUtils.d("onNext")

        if (StringUtils.equals(baseResponse.code, "1")) {
            if (StringUtils.isEmpty(baseResponse.except_case_desc)) {
                onSuccess(baseResponse.result!!)
            } else {
                onFailure(baseResponse.except_case_desc!!)
            }
        } else {
            onFailure(baseResponse.code!!)
        }
    }

    override fun onError(e: Throwable) {
        LogUtils.d("onError")

        if (e is HttpException) {
            val httpException = e
            val code = httpException.code()
            var msg = httpException.message

            Log.d("wxl", "code=" + code)
            if (code == 504) {
                msg = "网络不给力"
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试"
            }
            onFailure(msg!!)
        } else {
            onFailure(e.toString())
        }
        onFinish()
    }
}