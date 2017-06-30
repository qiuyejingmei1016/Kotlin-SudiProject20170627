package com.ysr.express.retrofit

/**接口地址
 * Created by ysr on 2017/6/30 17:21.
 * 邮箱 ysr200808@163.com
 */
object API {
    /**
     * 电商ID
     */
    val EBusinessID = "464846"
    /**
     * 电商加密私钥，快递鸟提供，注意保管，不要泄漏
     */
    val AppKey = "dwadad-526b-45da-b091-f728eb72b908"
    /**
     * 单号识别请求url
     */
    val ExpressReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx"
    /**
     * 快递公司客户电话html
     */
    val ExpressPhoneURL = "http://wap.guoguo-app.com/cpCompany.htm?type=10"
    /**
     * 快递公司图标
     */
    val LogoBaseUrl = "https://www.kuaidi100.com/images/all/"
}