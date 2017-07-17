package com.ysr.express.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysr.express.R;
import com.ysr.express.bean.RequestEbsDetail;
import com.ysr.library.utils.TimeTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ysr on 2017/7/17 16:59.
 * 邮箱 ysr200808@163.com
 */
public class TraceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<RequestEbsDetail.TracesBean> traceList = new ArrayList<>(1);
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL = 0x0001;
    private static final int TYPE_BOTTOM = 0x0002;

    public TraceListAdapter(Context context, List<RequestEbsDetail.TracesBean> traceList) {
        inflater = LayoutInflater.from(context);
        this.traceList = traceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_trace, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        if (getItemViewType(position) == TYPE_TOP) {
            // 第一行头的竖线不显示
            itemHolder.tvTopLine.setVisibility(View.INVISIBLE);
            // 字体颜色加深
            itemHolder.tvAcceptTime.setTextColor(0xff555555);
            itemHolder.tvAcceptStation.setTextColor(0xff555555);
            itemHolder.tvDot.setText("已签收");

        } else if (getItemViewType(position) == TYPE_NORMAL) {
            itemHolder.tvTopLine.setVisibility(View.VISIBLE);
            itemHolder.tvAcceptTime.setTextColor(0xff999999);
            itemHolder.tvAcceptStation.setTextColor(0xff999999);
        } else if (getItemViewType(position) == TYPE_BOTTOM) {
            itemHolder.tvTopLine.setVisibility(View.VISIBLE);
            itemHolder.tv_bottom.setVisibility(View.INVISIBLE);
            itemHolder.tvAcceptTime.setTextColor(0xff999999);
            itemHolder.tvAcceptStation.setTextColor(0xff999999);
        }

        itemHolder.bindHolder(traceList.get(position));
    }

    @Override
    public int getItemCount() {
        return traceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        } else if (position == traceList.size() - 1) {
            return TYPE_BOTTOM;
        }
        return TYPE_NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAcceptTime, tvAcceptStation;
        private TextView tvTopLine, tv_bottom;
        private TimeTextView tvDot;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAcceptTime = (TextView) itemView.findViewById(R.id.tvAcceptTime);
            tvAcceptStation = (TextView) itemView.findViewById(R.id.tvAcceptStation);
            tvTopLine = (TextView) itemView.findViewById(R.id.tvTopLine);
            tvDot = (TimeTextView) itemView.findViewById(R.id.tvDot);
            tv_bottom = (TextView) itemView.findViewById(R.id.tv_bottom);
        }

        public void bindHolder(RequestEbsDetail.TracesBean trace) {
            tvAcceptTime.setText(trace.getAcceptTime());
            tvAcceptStation.setText(trace.getAcceptStation());
        }
    }
}
