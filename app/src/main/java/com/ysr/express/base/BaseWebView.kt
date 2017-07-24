package com.ysr.express.base

import android.app.Activity
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient


/**
 * 基类WebActivity
 * Created by ysr on 2017/7/13 8:48.
 * 邮箱 ysr200808@163.com
 */
class BaseWebView : WebView {
    private var mContext: Context? = null
    constructor(context: Context, activity: Activity) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        mContext=context
        val webSettings = this.settings
        webSettings.javaScriptEnabled = true  //开启javascript
        webSettings.domStorageEnabled = true  //开启DOM
        webSettings.defaultTextEncodingName = "utf-8" //设置编码
        // // web页面处理
        webSettings.allowFileAccess = true// 支持文件流
        // webSettings.setSupportZoom(true);// 支持缩放
        // webSettings.setBuiltInZoomControls(true);// 支持缩放
        webSettings.useWideViewPort = true// 调整到适合webview大小
        webSettings.loadWithOverviewMode = true// 调整到适合webview大小
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        webSettings.blockNetworkImage = true
        //开启缓存机制
        webSettings.setAppCacheEnabled(true)
    }

    private inner class MyWebViewClient : WebViewClient() {


    }
    private inner class MyWebChromeClient  : WebChromeClient() {


    }
}
