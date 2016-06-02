package com.samplejoaTest.Activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

public class ViewActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String TAG = ViewActivity.class.getSimpleName();
    private SampleJoaInterface mSampleJoaInterface;
    private SampleJoaAdapter mSamplejoaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mTabLayout = (TabLayout) findViewById(R.id.view_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품3"));
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("화장품6"));
        mTabLayout.setOnTabSelectedListener(this);


        //  ViewPager 와 TabLayout연결
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        select();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // Tab을 선택했을 때 ViewPager 의 페이지를 이동
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
                    mSamplejoaAdapter.mData = response.body();
                    mSamplejoaAdapter.notifyDataSetChanged();
                    Log.e(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SampleJoaModel>> call, Throwable t) {

            }
        });
    }


}
