package com.ysr.express.retrofit

/**接口地址
 * Created by ysr on 2017/6/30 17:21.
 * 邮箱 ysr200808@163.com
 */
object API {
    /**
     * 电商ID
     */
    val EBusinessID = "1293600"
    /**
     * 电商加密私钥，快递鸟提供，注意保管，不要泄漏
     */
    val AppKey = "53a2e42e-4b0f-47b3-8d5b-36ff13acc733"
    /**
     * 单号识别请求url
     */
    val ExpressReqURL = "http://api.kdniao.cc"
    /**
     * 快递公司客户电话html
     */
    val ExpressPhoneURL = "http://wap.guoguo-app.com/cpCompany.htm?type=10"
    /**
     * 快递公司图标
     */
    val LogoBaseUrl = "https://www.kuaidi100.com/images/all/"
    val TYPE_TOP = 0x0000
    val TYPE_NORMAL = 0x0001
    val TYPE_BOTTOM = 0x0002
}