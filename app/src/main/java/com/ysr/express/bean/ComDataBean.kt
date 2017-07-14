package com.ysr.express.bean

import java.io.Serializable

/**快递图标
 * Created by ysr on 2017/7/13 16:46.
 * 邮箱 ysr200808@163.com
 */
class ComDataBean : Serializable {

    var title: String? = null
    var company: List<CompanyBean>? = null

    class CompanyBean {
        /**
         * name : 顺丰
         * code : SF
         * logo : 56/shunfeng.png
         */

        var name: String? = null
        var code: String? = null
        var logo: String? = null
    }


}