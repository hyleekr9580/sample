package com.samplejoaTest.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FragmentSample extends Fragment {
    private GridView mGridView;
    private SampleJoaAdapter mSamplejoaAdapter;
    private SampleJoaInterface mSampleJoaInterface;
    private ImageView mImageview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        mImageview = (ImageView) view.findViewById(R.id.image_view);
        Glide.with(this)
                .load("http://clubcoffee.cafe24.com/home/SampleJoa_Test/banner/sample.jpg")
                .into(mImageview);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mSamplejoaAdapter = new SampleJoaAdapter();
        mGridView.setAdapter(mSamplejoaAdapter);
        select();

    }

    public void init(List<SampleJoaModel> sampleJoaModels) {

        //
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
                    init(response.body());
//                    Log.e(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SampleJoaModel>> call, Throwable t) {

            }
        });
    }
}
