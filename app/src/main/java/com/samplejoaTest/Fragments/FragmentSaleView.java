package com.samplejoaTest.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samplejoaTest.R;

/**
 * Created by hoyoung on 2016-06-01.
 */
public class FragmentSaleView extends Fragment {

    private String mSaleViewId;

    public static FragmentSaleView newInstance(String id) {

        FragmentSaleView fragment = new FragmentSaleView();
        Bundle bundle = new Bundle();
        bundle.putString("saleviewid", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mSaleViewId = getArguments().getString("saleviewid");
        }

        Toast.makeText(getActivity(), ""+ mSaleViewId, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale_view, container, false);


        return view;
    }
}
