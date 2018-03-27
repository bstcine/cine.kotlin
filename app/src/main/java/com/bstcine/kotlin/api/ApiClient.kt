package com.bstcine.kotlin.api

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.StringUtils
import com.bstcine.kotlin.AppContext
import com.bstcine.kotlin.BuildConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

/**
 * Created by itwangxiang on 26/03/2018.
 */
object ApiClient {

    fun retrofit(): ApiStores {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor)
            builder.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()

                //封装 Body 参数
                if ("POST" == original.method() && !(original.body() is FormBody || original.body() is MultipartBody)) {
                    val buffer = Buffer()
                    original.body()!!.writeTo(buffer)
                    val dataJson = buffer.readUtf8()

                    val dataMap = Gson().fromJson<Map<String, Any>>(dataJson, object: TypeToken<Map<String, Any>>() {}.type)

                    //统一参数
                    val token = AppContext.instance!!.token
                    val sitecode = "cine.android.phone"
                    val appver = AppUtils.getAppVersionName()

                    val basicParams = HashMap<String, Any>()
                    if (!StringUtils.isEmpty(token)) basicParams.put("token", token)
                    basicParams.put("appver", appver)
                    basicParams.put("sitecode", sitecode)
                    basicParams.put("data", dataMap)

                    val bodyJson = Gson().toJson(basicParams)

                    val requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), bodyJson)
                    requestBuilder.post(requestBody)
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiStores.API_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(ApiStores::class.java)
    }

}