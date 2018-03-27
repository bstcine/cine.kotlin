package com.bstcine.kotlin

import android.app.Application
import android.content.Context
import android.text.TextUtils

import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils

/**
 * Created by itwangxiang on 26/03/2018.
 */

class AppContext : Application() {

    var isLogin = false
        private set

    val token: String
        get() = SPUtils.getInstance().getString(TOKEN_KEY)

    override fun onCreate() {
        super.onCreate()
        instance = this

        init()
    }

    fun init() {
        Utils.init(this)
        this.isLogin = !TextUtils.isEmpty(token)
    }

    fun login(token: String) {
        SPUtils.getInstance().put(TOKEN_KEY, token)
        this.isLogin = true
    }

    fun logout() {
        this.isLogin = false
        SPUtils.getInstance().remove(TOKEN_KEY)
    }

    companion object {

        private val TOKEN_KEY = "user.token"

        @get:Synchronized
        var instance: AppContext? = null
            private set
    }

}
