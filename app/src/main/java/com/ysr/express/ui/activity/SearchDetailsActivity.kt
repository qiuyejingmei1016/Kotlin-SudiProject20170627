package com.ysr.express.ui.activity

import android.view.View
import com.ysr.express.R
import com.ysr.express.bean.RequestEbsDetail
import com.ysr.express.retrofit.API
import com.ysr.express.retrofit.APIService
import com.ysr.express.retrofit.BaseRetrofit
import com.ysr.library.utils.HttpUtils
import com.ysr.library.utils.LogUtils
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_search_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**订单轨迹
 * Created by ysr on 2017/7/14 14:09.
 * 邮箱 ysr200808@163.com
 */
class SearchDetailsActivity : BaseActivity(), View.OnClickListener {


    override fun getLayoutId(): Int {
        return R.layout.activity_search_details
    }

    override fun initPresenter() {

    }

    override fun initView() {
        btn_search.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_search -> {
                loadData()
            }
        }
    }

    fun loadData() {
        val ShipperCode = "YD"
        val LogisticCode = "3999043346251"
        val requestData = "{'OrderCode':'','ShipperCode':'$ShipperCode','LogisticCode':'$LogisticCode'}"
        val RequestData = HttpUtils.urlEncoder(requestData, "UTF-8")
        val dataSign = HttpUtils.encrypt(requestData, API.AppKey, "UTF-8")
        val DataSign = HttpUtils.urlEncoder(dataSign, "UTF-8")
        BaseRetrofit.instance
                .createReq(APIService::class.java)
                .searchDetailsData(RequestData, API.EBusinessID, 1002, 2, DataSign)
                .enqueue(object : Callback<RequestEbsDetail> {
                    override fun onResponse(call: Call<RequestEbsDetail>?, response: Response<RequestEbsDetail>?) {

                    }

                    override fun onFailure(call: Call<RequestEbsDetail>?, t: Throwable?) {
                        LogUtils.loge(t!!.message)
                    }

                })
    }
}
