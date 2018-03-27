package com.bstcine.kotlin.model.base

/**
 * Created by wangxiang on 2017/10/18.
 */

class ResModelFormat<T> {

    var code: String? = null
    var code_desc: String? = null
    var except_case: String? = null
    var except_case_desc: String? = null
    var result: ResultModel<T>? = null
}
