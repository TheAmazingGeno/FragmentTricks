package com.app.androidkt.fraguse.repository.remot;

import com.app.androidkt.fraguse.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brijesh on 13/6/17.
 */

public class FlowerApi {

    public static FlowerService getFlowerService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build().create(FlowerService.class);
    }
}
