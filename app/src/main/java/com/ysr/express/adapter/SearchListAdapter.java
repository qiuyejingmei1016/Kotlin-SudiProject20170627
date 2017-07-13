package com.ysr.express.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ysr.express.R;
import com.ysr.express.bean.CompanyData;
import com.ysr.express.bean.RequestShipperName;
import com.ysr.library.config.AppLibConfig;

import java.util.List;

/**
 * Created by ysr on 2017/7/13 12:34.
 * 邮箱 ysr200808@163.com
 */

public class SearchListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RequestShipperName.ShippersBean> mData;
    private onItemClickListener itemClickListener;

    public void setData(List<RequestShipperName.ShippersBean> data) {
        mData = data;
    }

    public void update(List<RequestShipperName.ShippersBean> list) {
        this.mData = list;
        this.notifyDataSetChanged();
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SearchListAdapter(Context context, List<RequestShipperName.ShippersBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_info, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final int adjPosition = position;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_Courier_info.setText(mData.get(position).getShipperName());
        String result = AppLibConfig.jsons;

        CompanyData codeCompany = new Gson().fromJson(result, CompanyData.class);

//        for (int i = 0; i < list.size(); i++) {
//            if (mData.get(position).getShipperCode().equals(list.get(i).getCode())) {
//                Glide.with(context)
//                        .load("https://www.kuaidi100.com/images/all/" + list.get(i).getLogo())
//                        .into(viewHolder.image_express);
//            }
//        }

        if (null != itemClickListener) {
            viewHolder.cv_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if (tag instanceof RequestShipperName.ShippersBean) {
                        itemClickListener.onItemTextClick(holder.itemView, adjPosition, (RequestShipperName.ShippersBean) tag);
                    }

                }
            });
            RequestShipperName.ShippersBean one = mData.get(adjPosition);
            if (null != one) {
                ((ViewHolder) holder).cv_click.setTag(one);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cv_click;
        public ImageView image_express;
        public TextView tv_Courier_info;

        public ViewHolder(View itemView) {
            super(itemView);
            cv_click = itemView.findViewById(R.id.cv_click);
            tv_Courier_info = itemView.findViewById(R.id.tv_Courier_info);
            image_express = itemView.findViewById(R.id.image_express);
        }
    }

    /**
     * 点击事件选择回调
     */
    public interface onItemClickListener {
        void onItemTextClick(View view, int position, RequestShipperName.ShippersBean tag);
    }

}
