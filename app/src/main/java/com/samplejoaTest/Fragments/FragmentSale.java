package com.samplejoaTest.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        mImageview = (ImageView) view.findViewById(R.id.image_view);


        Glide.with(this)
                .load("http://clubcoffee.cafe24.com/home/SampleJoa_Test/banner/sale.jpg")
                .into(mImageview);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mSamplejoaAdapter = new SampleJoaAdapter();
        mGridView.setAdapter(mSamplejoaAdapter);
        mGridView.setOnItemClickListener(this);
        select();

    }


    public void ModelInit(List<SampleJoaModel> sampleJoaModels) {

        mSamplejoaAdapter.setmData(sampleJoaModels);
        mSamplejoaAdapter.notifyDataSetChanged();
    }

    public void select() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://clubcoffee.cafe24.com/home/SampleJoa_Test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSampleJoaInterface = retrofit.create(SampleJoaInterface.class);

        Call<List<SampleJoaModel>> call = mSampleJoaInterface.SelectServer();
        call.enqueue(new Callback<List<SampleJoaModel>>() {
            @Override
            public void onResponse(Call<List<SampleJoaModel>> call, Response<List<SampleJoaModel>> response) {
                if (response.body() != null) {
                    ModelInit(response.body());
                    Log.e(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SampleJoaModel>> call, Throwable t) {

            }
        });
    }
    // 프래그먼트세일뷰 Activity 로 넘겨 준다~~~onItemClick

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }




}
