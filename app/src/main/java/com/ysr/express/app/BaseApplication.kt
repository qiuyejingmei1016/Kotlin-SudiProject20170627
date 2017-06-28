package com.ysr.news.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.support.multidex.MultiDex
import com.ysr.express.BuildConfig
import com.ysr.library.app.EntityInfo
import com.ysr.library.utils.LogUtils.logInit
import com.ysr.news.BaseActivity
import java.util.*


/**
 * Created by ysr on 2017/6/19 12:39.
 * 邮箱 ysr200808@163.com
 */
class BaseApplication : Application() {
    //记录信息
    //   lateinit var entity: EntityInfo
    var entity: EntityInfo? = null
    //记录活动activity
    var mActivities = LinkedList<BaseActivity>()

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        //初始化logger
        logInit(BuildConfig.LOG_DEBUG)
    }

    /**
     * companion object 表示外部类的一个伴生对象
     */
    companion object {
        var baseApplication: BaseApplication? = null


        val instance: BaseApplication?
            get() {
                if (null == baseApplication) {
                    throw RuntimeException("必须先实例化Application")
                }
                return baseApplication
            }

        val appResources: Resources
            get() = baseApplication!!.resources


    }

    //初始化分包
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    //    var info: EntityInfo? = null
//        get() {
//            if (field == null) {
//                info = EntityInfo()
//            }
//            return field
//        }
    fun getInfo(): EntityInfo? {
        if (entity == null) {
            entity = EntityInfo()
        }
        return entity
    }

    fun setInfo(entity: EntityInfo) {
        this.entity = entity
    }

    /**
     * 添加页面
     */
    fun addActivity(activity: BaseActivity) {
        for (i in mActivities.indices) {
            if (mActivities[i] === activity) {
                mActivities.removeAt(i)
                break
            }
        }
        mActivities.add(activity)
    }
}