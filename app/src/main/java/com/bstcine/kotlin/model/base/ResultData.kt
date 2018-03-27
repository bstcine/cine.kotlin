package com.bstcine.kotlin.model.base

/**
 * Created by itwangxiang on 2017/5/10.
 */

class ResultData<T : Any> {

    var isStatus: Boolean = false

    var data: T? = null

    var msg: String? = null
        get() = if (field == null) "" else field
}
