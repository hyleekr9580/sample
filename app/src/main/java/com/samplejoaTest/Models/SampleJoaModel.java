package com.samplejoaTest.Models;

/**
 * Created by hoyoung on 2016-05-26.
 */
public class SampleJoaModel {

    private String brand_id;
    private String sp_title;
    private String sp_img1;

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getSp_title() {
        return sp_title;
    }

    public void setSp_title(String sp_title) {
        this.sp_title = sp_title;
    }

    public String getSp_img1() {
        return sp_img1;
    }

    public void setSp_img1(String sp_img1) {
        this.sp_img1 = sp_img1;
    }

    @Override
    public String toString() {
        return "SampleJoaModel{" +
                "brand_id='" + brand_id + '\'' +
                ", sp_title='" + sp_title + '\'' +
                ", sp_img1='" + sp_img1 + '\'' +
                '}';
    }
}
