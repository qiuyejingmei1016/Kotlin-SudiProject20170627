package com.ysr.express.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ysr.express.R
import com.ysr.express.bean.RequestEbsDetail
import com.ysr.express.retrofit.API
import com.ysr.library.utils.TimeTextView


/**
 * Created by ysr on 2017/7/17 16:59.
 * 邮箱 ysr200808@163.com
 */
class TraceListAdapter(context: Context, private var traceList: List<RequestEbsDetail.TracesBean>?) : RecyclerView.Adapter<TraceListAdapter.ViewHolder>() {

    fun update(list: List<RequestEbsDetail.TracesBean>) {
        this.traceList = list
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraceListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_trace, null))

    }

    override fun onBindViewHolder(holder: TraceListAdapter.ViewHolder, position: Int) {
        val itemHolder = holder
        if (getItemViewType(position) == API.TYPE_TOP) {
            // 第一行头的竖线不显示
            itemHolder.tvTopLine.visibility = View.INVISIBLE
            // 字体颜色加深
            itemHolder.tvAcceptTime.setTextColor(0xff555555.toInt())
            itemHolder.tvAcceptStation.setTextColor(0xff555555.toInt())
//            itemHolder.tvDot.text = "已签收"

        } else if (getItemViewType(position) == API.TYPE_NORMAL) {
            itemHolder.tvTopLine.visibility = View.VISIBLE
            itemHolder.tvAcceptTime.setTextColor(0xff999999.toInt())
            itemHolder.tvAcceptStation.setTextColor(0xff999999.toInt())
        } else if (getItemViewType(position) == API.TYPE_BOTTOM) {
            itemHolder.tvTopLine.visibility = View.VISIBLE
            itemHolder.tv_bottom.visibility = View.INVISIBLE
            itemHolder.tvAcceptTime.setTextColor(0xff999999.toInt())
            itemHolder.tvAcceptStation.setTextColor(0xff999999.toInt())
        }
        itemHolder.tvAcceptTime.text = traceList!![position].AcceptTime
        itemHolder.tvAcceptStation.text = traceList!![position].AcceptStation
    }

    override fun getItemCount(): Int {
        return traceList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return API.TYPE_TOP
        } else if (position == traceList!!.size - 1) {
            return API.TYPE_BOTTOM
        }
        return API.TYPE_NORMAL
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAcceptTime: TextView = itemView.findViewById<View>(R.id.tvAcceptTime) as TextView
        val tvAcceptStation: TextView = itemView.findViewById<View>(R.id.tvAcceptStation) as TextView
        val tvTopLine: TextView = itemView.findViewById<View>(R.id.tvTopLine) as TextView
        val tvDot: TimeTextView = itemView.findViewById<View>(R.id.tvDot) as TimeTextView
        val tv_bottom = itemView.findViewById<View>(R.id.tv_bottom) as TextView

    }
}
