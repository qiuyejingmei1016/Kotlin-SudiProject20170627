package com.ysr.express.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.ysr.express.R
import com.ysr.express.adapter.TraceListAdapter
import com.ysr.express.bean.RequestEbsDetail
import com.ysr.express.bean.RequestShipperName
import com.ysr.express.retrofit.API
import com.ysr.express.retrofit.APIService
import com.ysr.express.retrofit.BaseRetrofit
import com.ysr.express.retrofit.CustemCallBack
import com.ysr.library.utils.HttpUtils
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_search_details.*
import retrofit2.Response
import java.util.*


/**订单轨迹
 * Created by ysr on 2017/7/14 14:09.
 * 邮箱 ysr200808@163.com
 */
class SearchDetailsActivity : BaseActivity() {
    private var traceList: List<RequestEbsDetail.TracesBean> = ArrayList()
    private var adapter: TraceListAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_search_details
    }

    override fun initPresenter() {

    }

    override fun initView() {
        setSupportActionBar(toolbar_DetailsActivity)
        supportActionBar?.setTitle(R.string.text_null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tag = intent.getSerializableExtra("tag") as? RequestShipperName.ShippersBean
        tv_Courier_info.text=tag!!.ShipperName
        tv_Courier_num.text=tag!!.ShipperCode
        adapter = TraceListAdapter(this, traceList)
        rvTrace.layoutManager = LinearLayoutManager(this)
        rvTrace.adapter = adapter
        loadData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    fun loadData() {
        val ShipperCode = "YD"
        val LogisticCode = "3999043346251"
        val requestData = "{'OrderCode':'','ShipperCode':'$ShipperCode','LogisticCode':'$LogisticCode'}"
        val RequestData = HttpUtils.urlEncoder(requestData, "UTF-8")
        val dataSign = HttpUtils.encrypt(requestData, API.AppKey, "UTF-8")
        val DataSign = HttpUtils.urlEncoder(dataSign, "UTF-8")
        val params = HashMap<String, String>()
        params.put("RequestData", RequestData)
        params.put("EBusinessID", API.EBusinessID)
        params.put("RequestType", "1002")
        params.put("DataSign", DataSign)
        params.put("DataType", "2")
        BaseRetrofit.instance
                .createReq(APIService::class.java)
                .searchDetailsData(params)
                .enqueue(object : CustemCallBack<RequestEbsDetail>() {
                    override fun onSuccess(response: Response<RequestEbsDetail>?) {
                        if (response!!.body()!!.Success) {
                            adapter!!.update(response.body()!!.Traces!!)
                        }
                    }

                    override fun onFail(message: String) {
                    }
                })
    }
}
