package com.ysr.express.base

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**基类Fragment
 * Created by ysr on 2017/6/29 17:09.
 * 邮箱 ysr200808@163.com
 */
abstract class BaseFragment : Fragment() {
    protected var rootView: View? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (null == rootView) {
            rootView = inflater!!.inflate(getLayoutResource(), container, false)
        }
        initPresenter()
        initView()
        return rootView!!
    }

    abstract fun getLayoutResource(): Int

    abstract fun initPresenter()

    //初始化view
    abstract fun initView()

}