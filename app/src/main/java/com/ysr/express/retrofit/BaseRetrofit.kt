package com.ysr.express.retrofit


import com.ysr.express.util.ConfigUtils
import com.ysr.news.app.BaseApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**基类retrofit
 * Created by ysr on 2017/6/30 17:21.
 * 邮箱 ysr200808@163.com
 */

object BaseRetrofit {
    private const val cacheSize: Long = 10 * 1024 * 1024
    private var REWRITE_CACHE_CONTROL_INTERCEPTOR: Interceptor = Interceptor {
        chain ->
        val response = chain?.proceed(chain.request())
        if (ConfigUtils.isOnle()) {
            val maxAge: Int = 0
            response?.newBuilder()!!.removeHeader("Cache-Control")
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
        } else {
            val maxAge = 60 * 60 * 24 * 7
            response?.newBuilder()!!.removeHeader("Cache-Control")
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxAge)
                    .build()
        }
    }

    private fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (builder.interceptors() != null) {
            builder.interceptors().clear()
        }
        builder.addInterceptor { chain ->
            val request = chain?.request()
            if (!ConfigUtils.isOnle()) {
                chain!!.proceed(request!!
                        .newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build())
            } else {
                chain!!.proceed(
                        request!!.newBuilder().build())
            }
        }.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(Cache(BaseApplication.getContext().cacheDir, cacheSize))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }


    fun retrofit(url: String): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }
}