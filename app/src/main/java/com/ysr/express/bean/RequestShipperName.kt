package com.ysr.express.bean

import java.io.Serializable

/**单号识别
 * Created by ysr on 2017/7/11 12:00.
 * 邮箱 ysr200808@163.com
 */

class RequestShipperName  : Serializable {

    /**
     * EBusinessID : 1293600
     * Success : true
     * LogisticCode : 1000745320654
     * Shippers : [{"ShipperCode":"YD","ShipperName":"韵达快递"},{"ShipperCode":"EMS","ShipperName":"EMS"},{"ShipperCode":"HTKY","ShipperName":"百世汇通"}]
     */

    var EBusinessID: String? = null
    var Success: Boolean = false
    var LogisticCode: String? = null
    var Shippers: List<ShippersBean>? = null

    class ShippersBean : Serializable {
        /**
         * ShipperCode : YD
         * ShipperName : 韵达快递
         */

        var ShipperCode: String? = null
        var ShipperName: String? = null
    }
}
