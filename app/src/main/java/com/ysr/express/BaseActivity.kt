package com.ysr.news

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ysr.news.app.BaseApplication

abstract class BaseActivity : AppCompatActivity() {
    var mContext: Context? = null
    var app: BaseApplication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.getLayoutId())
        mContext = this
        app = BaseApplication.instance
        app!!.addActivity(this)
        //   app?.addActivity(this)


        initPresenter()
        initview()
    }

    //获取布局文件
    abstract fun getLayoutId(): Int

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    abstract fun initPresenter()

    //初始化view
    abstract fun initview()
}
