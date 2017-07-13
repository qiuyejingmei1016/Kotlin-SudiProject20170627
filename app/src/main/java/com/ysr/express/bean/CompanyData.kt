package com.ysr.express.bean

/**快递图标
 * Created by ysr on 2017/7/13 16:46.
 * 邮箱 ysr200808@163.com
 */
class CompanyData {
    var obj: List<ObjectData>? = null

    class ObjectData {
        var title: String? = null
        var company: List<Company>? = null

        class Company {
            var name: String? = null
            var code: String? = null
            var logo: String? = null
        }
    }


}