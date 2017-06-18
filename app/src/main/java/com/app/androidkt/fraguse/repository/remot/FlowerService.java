package com.app.androidkt.fraguse.repository.remot;

import com.app.androidkt.fraguse.vo.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by brijesh on 13/6/17.
 */

public interface FlowerService {

    @GET("/feeds/flowers.json")
    Call<List<Flower>> getFlower();
}
