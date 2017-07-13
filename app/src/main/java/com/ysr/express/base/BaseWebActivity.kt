package com.ysr.express.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * 基类WebActivity
 * Created by ysr on 2017/7/13 8:48.
 * 邮箱 ysr200808@163.com
 */
abstract class BaseWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId)
    }

    abstract val getLayoutId: Int
}
