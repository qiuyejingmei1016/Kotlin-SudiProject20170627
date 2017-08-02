package com.ysr.news.app

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.ysr.express.BuildConfig
import com.ysr.library.app.EntityInfo
import com.ysr.library.utils.LogUtils.logInit
import com.ysr.news.BaseActivity
import java.util.*


/**BaseApplication
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
        C.baseApplication = this
        C.context = applicationContext
        //初始化logger
        logInit(BuildConfig.LOG_DEBUG)
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace() // 打印日志的时候打印线程堆栈
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    object C {
        var baseApplication: BaseApplication? = null
        lateinit var context: Context
    }

    /**
     * companion object 表示外部类的一个伴生对象
     */
    companion object {


        val instance: BaseApplication?
            get() {
                if (null == C.baseApplication) {
                    throw RuntimeException("必须先实例化Application")
                }
                return C.baseApplication
            }

        val appResources: Resources
            get() = C.baseApplication!!.resources

        fun getContext(): Context {
            return C.context
        }
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