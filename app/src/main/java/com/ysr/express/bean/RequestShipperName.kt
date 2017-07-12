package com.ysr.express.bean

/**
 * Created by ysr on 2017/7/11 12:00.
 * 邮箱 ysr200808@163.com
 */

class RequestShipperName {

    /**
     * EBusinessID : 1293600
     * Success : true
     * LogisticCode : 1000745320654
     * Shippers : [{"ShipperCode":"YD","ShipperName":"韵达快递"},{"ShipperCode":"EMS","ShipperName":"EMS"},{"ShipperCode":"HTKY","ShipperName":"百世汇通"}]
     */

    var eBusinessID: String? = null
    var isSuccess: Boolean = false
    var logisticCode: String? = null
    var shippers: List<ShippersBean>? = null

    class ShippersBean {
        /**
         * ShipperCode : YD
         * ShipperName : 韵达快递
         */

        var shipperCode: String? = null
        var shipperName: String? = null
    }
}
