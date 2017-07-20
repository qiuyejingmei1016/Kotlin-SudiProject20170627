package com.ysr.express.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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
    private val LOADUSERS = 2003
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
        val ShipperCode = tag!!.ShipperCode
        val LogisticCode = intent.getStringExtra("code")
        val imgUrl = intent.getStringExtra("imgUrl")
        tv_Courier_info.text = tag!!.ShipperName!!
        tv_Courier_num.text = LogisticCode!!
        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.app_res_images_ic_logo_default)
                .error(R.mipmap.app_res_images_ic_logo_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE)!!
        Glide.with(mContext)
                .load(imgUrl)
                .apply(options)
                .into(image_express)
        adapter = TraceListAdapter(this, traceList)
        rvTrace.layoutManager = LinearLayoutManager(this)
        rvTrace.adapter = adapter

        loadData(ShipperCode!!, LogisticCode)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    fun loadData(ShipperCode: String, LogisticCode: String) {
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
                            val state = response.body()!!.State
                            if ("3".equals(state)) {
                                tv_Courier_state.text = "已签收"
                            } else if ("0".equals(state)) {
                                tv_Courier_state.setTextColor(Color.BLUE)
                                tv_Courier_state.text = "无轨迹"
                            } else if ("2".equals(state)) {
                                tv_Courier_state.setTextColor(Color.YELLOW)
                                tv_Courier_state.text = "派送中"
                            } else {
                                tv_Courier_state.setTextColor(Color.RED)
                                tv_Courier_state.text = "问题件"
                            }
                            adapter!!.update(response.body()!!.Traces!!)
                        } else {
                            tv_Courier_state.setTextColor(Color.RED)
                            tv_Courier_state.text = "问题件"
                        }
                    }

                    override fun onFail(message: String) {
                        tv_Courier_state.setTextColor(Color.RED)
                        tv_Courier_state.text = "问题件"
                    }
                })
    }
//    uiHandler.sendEmptyMessage(LOADUSERS)
//    internal var uiHandler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            when (msg.what) {
//                LOADUSERS -> post(loadrunnable)
//            }
//        }
//    }
//    internal var loadrunnable: Runnable = Runnable {
//        runOnUiThread {
//            tv_Courier_state.text = "问题件"
//            tv_Courier_state.setTextColor(0xff999999.toInt())
//        }
//    }
}
