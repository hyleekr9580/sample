package com.samplejoaTest.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.samplejoaTest.R;

import java.util.List;

/**
 * Created by hoyoung on 2016-05-26.
 */
public class SampleJoaAdapter extends BaseAdapter {

    public List<SampleJoaModel> mData;

    public List<SampleJoaModel> getmData() {
        return mData;
    }

    public void setmData(List<SampleJoaModel> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String mUrl = "http://clubcoffee.cafe24.com/home/SampleJoa_Test/icon/";


        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_samplejoa, parent, false);
            holder.sp_img1 = (ImageView) convertView.findViewById(R.id.sp_img1);
            holder.sp_title = (TextView) convertView.findViewById(R.id.sp_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SampleJoaModel memberModel = (SampleJoaModel) getItem(position);
        String sp_img1 = memberModel.getSp_img1();

        Glide.with(parent.getContext())
                .load(mUrl + sp_img1)
                .error(R.mipmap.ic_launcher)
                .into(holder.sp_img1);

        holder.sp_title.setText(memberModel.getSp_title());

        return convertView;
    }

    static class ViewHolder {
        ImageView sp_img1;
        TextView sp_title;
    }


}
