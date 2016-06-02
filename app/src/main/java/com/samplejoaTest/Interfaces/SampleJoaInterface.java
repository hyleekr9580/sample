package com.samplejoaTest.Interfaces;

import com.samplejoaTest.Models.SampleJoaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hoyoung on 2016-05-26.
 */
public interface SampleJoaInterface {

    @GET("samplejoa_select.php")
    Call<List<SampleJoaModel>> SelectServer();

}
