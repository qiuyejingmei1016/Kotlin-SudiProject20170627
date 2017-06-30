package com.ysr.express.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ysr.news.app.BaseApplication

/**工具类
 * Created by ysr on 2017/6/30 17:21.
 * 邮箱 ysr200808@163.com
 */
object ConfigUtils{
    fun isOnle(): Boolean {
        val connMgr: ConnectivityManager = BaseApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }

    fun parseString(str: String): String {
        val result = str
                .replace("[", "")
                .replace("]", "")
                .replace(",", "/")
                .replace(" ", "")
        return result
    }
}