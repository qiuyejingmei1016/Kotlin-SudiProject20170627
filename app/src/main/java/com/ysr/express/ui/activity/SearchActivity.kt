package com.ysr.express.ui.activity

import com.ysr.express.R
import com.ysr.express.bean.RequestShipperName
import com.ysr.express.retrofit.API
import com.ysr.express.retrofit.APIService
import com.ysr.express.retrofit.BaseRetrofit
import com.ysr.library.utils.HttpUtils
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**查询
 * Created by ysr on 2017/6/30 11:24.
 * 邮箱 ysr200808@163.com
 */
class SearchActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initPresenter() {

    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.text_null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        as_srl.isEnabled = false
        loadData()
    }

    fun loadData() {
        val requestData = "{LogisticCode:1000745320654}"
        val RequestData = HttpUtils.urlEncoder(requestData, "UTF-8")
        val dataSign = HttpUtils.encrypt(requestData, API.AppKey, "UTF-8")
        val DataSign = HttpUtils.urlEncoder(dataSign, "UTF-8")
        BaseRetrofit.instance
                .createReq(APIService::class.java)
                .searchData(RequestData, API.EBusinessID, 2002, 2, DataSign)
                .enqueue(object : Callback<RequestShipperName> {
                    override fun onResponse(call: Call<RequestShipperName>, response: Response<RequestShipperName>) {

                    }

                    override fun onFailure(call: Call<RequestShipperName>, t: Throwable) {
                        println("失败")
                    }
                })
    }
}
