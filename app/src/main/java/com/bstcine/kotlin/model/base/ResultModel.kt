package com.bstcine.kotlin.model.base

/**
 * Created by wangxiang on 2017/10/18.
 */

class ResultModel<T> {

    var cur_page: String? = null
    var max_page: String? = null
    var total_rows: String? = null
    var rows: List<T>? = null
    var detail: T? = null
}
