package com.bstcine.kotlin.api

import com.bstcine.kotlin.model.base.ResModel
import com.bstcine.kotlin.model.user.UserModel
import com.bstcine.kotlin.model.user.V2User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by itwangxiang on 26/03/2018.
 */
interface ApiStores {

    @POST("/api/auth/signin")
    fun login(@Body data: Map<String, String>): Observable<ResModel<V2User>>

    @POST("/api/user/info")
    fun userInfo(): Observable<ResModel<UserModel>>

    companion object {
        //baseUrl
        val API_SERVER_URL = "http://apptest.bstcine.com/"
    }

}