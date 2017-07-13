package com.ysr.express.call

import com.ysr.express.bean.RequestShipperName

/**单号识别回调接口
 * Created by ysr on 2017/7/11 9:04.
 * 邮箱 ysr200808@163.com
 */
interface SearchDataSource {
    interface LoadQueryCallBack {
        fun onDateLoaded(shipperName: RequestShipperName)
        fun onDataLoadFailed(message: String)
    }
    fun loadQueryData( callBack: LoadQueryCallBack)
}