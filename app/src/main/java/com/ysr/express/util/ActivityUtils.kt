package com.ysr.express.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/** 界面跳转管理
 *  Created by ysr on 2017-06-29.
 *  邮箱 ysr200808@163.com
 */
object ActivityUtils {
    fun addFragmentToActivity(frgamenManager: FragmentManager,
                              fragment: Fragment, fragmeId: Int) {

        val transaction: FragmentTransaction = frgamenManager.beginTransaction()
        transaction.add(fragmeId, fragment)
        transaction.commit()

    }

    fun addFragmentToActivity(frgamenManager: FragmentManager,
                              fragment: Fragment, tag: String) {

        val transaction: FragmentTransaction = frgamenManager.beginTransaction()
        transaction.add(fragment, tag)
        transaction.commit()
    }

}