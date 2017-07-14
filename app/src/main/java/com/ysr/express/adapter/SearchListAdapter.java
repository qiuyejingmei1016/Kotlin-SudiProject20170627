package com.ysr.express.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ysr.express.R;
import com.ysr.express.bean.ComDataBean;
import com.ysr.express.bean.RequestShipperName;
import com.ysr.library.utils.ComUtils;
import com.ysr.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysr on 2017/7/13 12:34.
 * 邮箱 ysr200808@163.com
 */

public class SearchListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RequestShipperName.ShippersBean> mData;
    private onItemClickListener itemClickListener;
    private ComDataBean userBean;
    private ArrayList<ComDataBean> beanList;

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
        getImageUrl();
    }

    //解析快递公司图标Json
    private void getImageUrl() {
        String result = ComUtils.getJson(context, "CompanyData.json");
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("company_data");
        Gson gson = new Gson();

        beanList = new ArrayList<>();
        //循环遍历
        for (JsonElement user : jsonArray) {
            //通过反射 ComDataBean.class
            userBean = gson.fromJson(user, new TypeToken<ComDataBean>() {
            }.getType());
            beanList.add(userBean);
        }
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

        for (int i = 0; i < beanList.size(); i++) {
            for (int j = 0; j < beanList.get(i).getCompany().size(); j++) {
                if (mData.get(position).getShipperCode().equals(beanList.get(i).getCompany().get(j).getCode())) {
                    LogUtils.loge(beanList.get(i).getCompany().get(j).getLogo());
                    Glide.with(context)
                            .load("https://www.kuaidi100.com/images/all/" + beanList.get(i).getCompany().get(j).getLogo())
                            .into(viewHolder.image_express);
                }
            }
        }

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
