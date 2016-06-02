package com.samplejoaTest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.samplejoaTest.Interfaces.SampleJoaInterface;
import com.samplejoaTest.Models.SampleJoaAdapter;
import com.samplejoaTest.Models.SampleJoaModel;
import com.samplejoaTest.R;

import java.util.List;

/**
 * Created by hoyoung on 2016-05-30.
 */
public class FragmentSale extends Fragment implements AdapterView.OnItemClickListener {

    private SampleJoaAdapter mSamplejoaAdapter;
    private GridView mGridView;
    private SampleJoaInterface mSampleJoaInterface;
    private ImageView mImageview;
    private String TAG = FragmentSale.class.getSimpleName();
    private SampleJoaAdapter mAdapter;

    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public interface OnFragmentSaleListner {
        String getBannerUrl(String name);
        void getModels(String name);
    }

    private OnFragmentSaleListner mListner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        mImageview = (ImageView) view.findViewById(R.id.image_view);

        if (mListner != null) {
            Glide.with(this)
                    .load(mListner.getBannerUrl(getName()))
                    .into(mImageview);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListner = (OnFragmentSaleListner) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListner = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mSamplejoaAdapter = new SampleJoaAdapter();
        mGridView.setAdapter(mSamplejoaAdapter);
        mGridView.setOnItemClickListener(this);
//        select();

        if (mListner != null) {
            mListner.getModels(getName());
        }

    }


    public void ModelInit(List<SampleJoaModel> sampleJoaModels) {

        mSamplejoaAdapter.setmData(sampleJoaModels);
        mSamplejoaAdapter.notifyDataSetChanged();
    }


    // 프래그먼트세일뷰 Activity 로 넘겨 준다~~~onItemClick

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }




}
