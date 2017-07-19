package com.ysr.express.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.ysr.express.R
import com.ysr.express.bean.ComDataBean
import com.ysr.express.bean.RequestShipperName
import com.ysr.express.retrofit.API
import com.ysr.library.utils.ComUtils
import java.util.*

/**查询结果Adapter
 * Created by ysr on 2017/7/13 12:34.
 * 邮箱 ysr200808@163.com
 */

class SearchListAdapter(val context: Context, var mData: List<RequestShipperName.ShippersBean>?) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    private var itemClickListener: onItemClickListener? = null
    private var userBean: ComDataBean? = null
    private var beanList: ArrayList<ComDataBean>? = null
    val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.app_res_images_ic_no_exist)
//                        .skipMemoryCache(true)
            .error(R.mipmap.app_res_images_ic_no_exist)
            .diskCacheStrategy(DiskCacheStrategy.NONE)!!

    init {
        getImageUrl()
    }

    fun setData(list: List<RequestShipperName.ShippersBean>) {
        mData = list
    }

    fun update(list: List<RequestShipperName.ShippersBean>) {
        this.mData = list
        this.notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    //解析快递公司图标Json
    fun getImageUrl() {
        val result = ComUtils.getJson(context, "CompanyData.json")
        //先转JsonObject
        val jsonObject = JsonParser().parse(result).asJsonObject
        //再转JsonArray 加上数据头
        val jsonArray = jsonObject.getAsJsonArray("company_data")
        beanList = ArrayList<ComDataBean>()
        //循环遍历
        for (user in jsonArray) {
            //通过反射 ComDataBean.class
            userBean = Gson().fromJson<ComDataBean>(user, object : TypeToken<ComDataBean>() {

            }.type)
            beanList!!.add(userBean!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_info, null))
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        val adjPosition = position
        val viewHolder = holder
        viewHolder.tv_Courier_info.text = mData!![position].ShipperName

        loop@ for (i in beanList!!.indices) {

            for (j in 0..beanList!![i].company!!.size){
                if (mData!![position].ShipperCode == beanList!![i].company!![j].code) {
                    Glide.with(context)
                            .load(API.LogoBaseUrl + beanList!![i].company!![j].logo!!)
                            .apply(options)
                            .into(viewHolder.image_express)
                    //终止此次循环
                    break@loop
                } else {
                    Glide.with(context)
                            .load(R.mipmap.app_res_images_ic_no_exist)
                            .into(viewHolder.image_express)
                }
            }
        }
        if (null != itemClickListener) {
            viewHolder.cv_click.setOnClickListener { v ->
                val tag = v.tag
                if (tag is RequestShipperName.ShippersBean) {
                    itemClickListener!!.onItemTextClick(holder.itemView, adjPosition, tag)
                }
            }
            val one = mData!![adjPosition]
            if (null != one) {
                holder.cv_click.tag = one
            }
        }


    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cv_click: CardView = itemView.findViewById<CardView>(R.id.cv_click)
        var image_express: ImageView = itemView.findViewById<ImageView>(R.id.image_express)
        var tv_Courier_info: TextView = itemView.findViewById<TextView>(R.id.tv_Courier_info)

    }

    /**
     * 点击事件选择回调
     */
    interface onItemClickListener {
        fun onItemTextClick(view: View, position: Int, tag: RequestShipperName.ShippersBean)
    }

}
