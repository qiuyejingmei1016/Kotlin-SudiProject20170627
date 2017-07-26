package com.ysr.express.ui.activity

import android.support.constraint.ConstraintLayout
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import com.ysr.express.R
import com.ysr.express.retrofit.API
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_post.*

/**寄快递
 * Created by ysr on 2017/6/30 11:24.
 * 邮箱 ysr200808@163.com
 */
class PostActivity : BaseActivity() {
    private var mIsErrorPage: Boolean = false
    private var mErrorView: View? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_post
    }

    override fun initPresenter() {
    }

    override fun initView() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setSupportActionBar(Post_toolbar)
        supportActionBar?.setTitle(R.string.text_null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val webSettings = wv_post.settings
        webSettings.javaScriptEnabled = true  //开启javascript
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.domStorageEnabled = true  //开启DOM
        webSettings.defaultTextEncodingName = "utf-8" //设置编码
        // // web页面处理
        webSettings.allowFileAccess = true// 支持文件流
        // webSettings.setSupportZoom(true);// 支持缩放
        // webSettings.setBuiltInZoomControls(true);// 支持缩放
        webSettings.useWideViewPort = true// 调整到适合webview大小
        webSettings.loadWithOverviewMode = true// 调整到适合webview大小
        webSettings.loadsImagesAutomatically = true

//        wv_post.webViewClient = MyWebViewClient()
        wv_post.webChromeClient = MyWebChromeClient()
        wv_post.loadUrl(API.ExpressPhoneURL)
    }

    private inner class MyWebViewClient : WebViewClient() {


//        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//            super.onPageStarted(view, url, favicon)
//            mIsErrorPage = true
//        }
//
//        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
//            super.onReceivedError(view, request, error)
//            mIsErrorPage = false
//            showErrorPage()//显示错误页面
//        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
//            view!!.loadUrl("javascript:(function(){" +
//                    "var head =document.getElementsByTagName('header')[0];" +
//                    "head.parentNode.removeChild(head);})()")
            //隐藏标签
            view!!.loadUrl("javascript:function setTop(){document.querySelector('div.header').style.display=\"none\";}setTop();")
//            if (mIsErrorPage) {
//                hideErrorPage()
//            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private inner class MyWebChromeClient : WebChromeClient() {


        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            view!!.loadUrl("javascript:function setTop(){document.querySelector('div.header').style.display=\"none\";}setTop();")

        }

    }

    /****
     * 把系统自身请求失败时的网页隐藏
     */
    fun hideErrorPage() {
        val webParentView = wv_post.parent as ConstraintLayout
        while (webParentView.childCount > 1) {
            webParentView.removeViewAt(0)
        }
        mIsErrorPage = true
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    fun showErrorPage() {
        val webParentView = wv_post.parent as ConstraintLayout
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.activity_url_error, null)
            val button = mErrorView!!.findViewById<View>(R.id.img_tip_logo) as ImageView
            button.setOnClickListener { wv_post.reload() }
            mErrorView!!.setOnClickListener(null)
        }
        while (webParentView.childCount > 1) {
            webParentView.removeViewAt(0)
        }
        val lp = LinearLayout.LayoutParams(-1, -1)
        webParentView.addView(mErrorView, 0, lp)
        mIsErrorPage = false
    }

    override fun onDestroy() {
        if (wv_post != null) {
            wv_post.destroy()
        }
        super.onDestroy()
    }
}
