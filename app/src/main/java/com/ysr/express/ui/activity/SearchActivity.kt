package com.ysr.express.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ysr.express.R
import com.ysr.express.adapter.SearchListAdapter
import com.ysr.express.bean.RequestShipperName
import com.ysr.express.retrofit.API
import com.ysr.express.retrofit.APIService
import com.ysr.express.retrofit.BaseRetrofit
import com.ysr.express.retrofit.CustemCallBack
import com.ysr.library.utils.HttpUtils
import com.ysr.library.utils.SearchView.SearchViewListener
import com.ysr.news.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Response


/**查询
 * Created by ysr on 2017/6/30 11:24.
 * 邮箱 ysr200808@163.com
 */
class SearchActivity : BaseActivity(), SearchListAdapter.onItemClickListener {


    var adapter: SearchListAdapter? = null
    var list: List<RequestShipperName.ShippersBean>? = null
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
        sv.setSearchViewListener(svListener())
        list = ArrayList<RequestShipperName.ShippersBean>()
        adapter = SearchListAdapter(mContext!!, list)
        adapter!!.setItemClickListener(this)
        as_sv_list.layoutManager = LinearLayoutManager(mContext)
        as_sv_list.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    fun loadData(LogisticCode: String) {
        val requestData = "{LogisticCode:$LogisticCode}"
        val RequestData = HttpUtils.urlEncoder(requestData, "UTF-8")
        val dataSign = HttpUtils.encrypt(requestData, API.AppKey, "UTF-8")
        val DataSign = HttpUtils.urlEncoder(dataSign, "UTF-8")
        BaseRetrofit.instance
                .createReq(APIService::class.java)
                .searchData(RequestData, API.EBusinessID, 2002, 2, DataSign)
                .enqueue(object : CustemCallBack<RequestShipperName>() {
                    override fun onSuccess(response: Response<RequestShipperName>?) {
                        if (response!!.body()!!.Success) {
                            list = response!!.body()!!.Shippers!!
                            adapter!!.update(list!!)
                        }
                    }

                    override fun onFail(message: String) {
                        Toast.makeText(mContext, "请检查网络连接是否通畅", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    //监听搜索
    inner class svListener : SearchViewListener {
        override fun onRefreshAutoComplete(text: String?) {
            loadData(text!!)
        }

        override fun onSearch(text: String?) {
            loadData(text!!)
        }

    }

    override fun onItemTextClick(view: View, position: Int, tag: RequestShipperName.ShippersBean) {
        val intent = Intent(this, SearchDetailsActivity::class.java)
        intent.putExtra("name","韵达")
        intent.putExtra("tag", tag)
        startActivity(intent)
    }

}
