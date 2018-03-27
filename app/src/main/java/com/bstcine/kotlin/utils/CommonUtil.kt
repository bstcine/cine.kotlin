package com.bstcine.kotlin.utils

import com.bstcine.kotlin.AppContext

/**
 * Created by itwangxiang on 27/03/2018.
 */
object CommonUtil {
    fun GetWebUrl(url: String): String {
        var webUrl = url

        if (url.contains("?")) {
            webUrl += "&sitecode=cine.android.phone"
            webUrl += "&token=" + AppContext.instance!!.token
        }else{
            webUrl += "?sitecode=cine.android.phone"
            webUrl += "&token=" + AppContext.instance!!.token
        }

        return webUrl
    }
}