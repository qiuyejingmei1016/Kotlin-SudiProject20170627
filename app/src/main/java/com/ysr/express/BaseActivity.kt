package com.ysr.news

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.ysr.express.R
import com.ysr.library.commonwidget.StatusBarCompat
import com.ysr.news.app.BaseApplication

abstract class BaseActivity : AppCompatActivity() {
    var mContext: Context? = null
    var app: BaseApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetcontentView()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(this.getLayoutId())
        mContext = this
        app = BaseApplication.instance
        app!!.addActivity(this)
        //   app?.addActivity(this)
        initPresenter()
        initView()
    }

    //获取布局文件
    abstract fun getLayoutId(): Int

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    abstract fun initPresenter()

    //初始化view
    abstract fun initView()

    /**
     * 设置主题，layout前配置
     */
    fun doBeforeSetcontentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        SetStatusBarColor()
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    fun SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.mainColor))
    }
}
