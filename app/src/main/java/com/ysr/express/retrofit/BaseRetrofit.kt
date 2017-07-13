package com.ysr.express.retrofit


import com.ysr.express.BuildConfig
import com.ysr.express.util.ConfigUtils
import com.ysr.library.utils.LogUtils
import com.ysr.news.app.BaseApplication
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**基类retrofit
 * Created by ysr on 2017/6/30 17:21.
 * 邮箱 ysr200808@163.com
 */

class BaseRetrofit private constructor() {
    private val cacheSize: Long = 10 * 1024 * 1024
    private var mRetrofit: Retrofit? = null

    init {
        initRetrofit()
    }

    companion object {
        private var mRetrofitManager: BaseRetrofit? = null

        val instance: BaseRetrofit
            @Synchronized get() {
                if (mRetrofitManager == null) {
                    mRetrofitManager = BaseRetrofit()
                }
                return mRetrofitManager!!
            }
    }

    fun <T> createReq(reqServer: Class<T>): T {
        return mRetrofit!!.create(reqServer)
    }


    //网络拦截器
    inner class REWRITE_CACHE_CONTROL_INTERCEPTOR : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
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
            return response
        }

    }


    //初始化
    private fun initRetrofit() {
        val LoginInterceptor = HttpLoggingInterceptor()
        LoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        if (builder.interceptors() != null) {
            builder.interceptors().clear()
        }
        if (BuildConfig.LOG_DEBUG) {
            builder.addInterceptor(LoggingInterceptor()) //看请求时长开启System
            // builder.addInterceptor(LoginInterceptor)
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
        }
        builder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR())
        builder.cache(Cache(BaseApplication.getContext().cacheDir, cacheSize))
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        val client = builder.build()
        mRetrofit = Retrofit.Builder()
                .baseUrl(API.ExpressReqURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
    }

    //自定义日志拦截器
    inner class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            val request = chain.request()

            val t1 = System.nanoTime()//请求发起的时间
            println(String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()))
            val response = chain.proceed(request)

            val t2 = System.nanoTime()//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            val responseBody = response.peekBody((1024 * 1024).toLong())
            LogUtils.loge(responseBody.string().replace("\n", "").replace(" ", "").trim())
//           String.format("接收响应: [%s] %n返回json:【%s】 %n用时 %.1fms%n%s",
//                    response.request().url(),
//                    responseBody.string().replace("\n","").trim(),
//                    (t2 - t1) / 1e6,
//                    response.headers()).let(::println)
//            ("接收响应:\t${response.request().url()}\n返回json:\n${responseBody.string().replace("\n", "").replace(" ", "")}\n请求时长\t${(t2 - t1) / 1e6}ms\n${response.headers()}").let(::println)
            return response
        }
    }

}