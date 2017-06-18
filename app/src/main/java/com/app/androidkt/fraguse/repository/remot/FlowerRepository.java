package com.app.androidkt.fraguse.repository.remot;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.app.androidkt.fraguse.vo.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brijesh on 13/6/17.
 */

public class FlowerRepository {

    public LiveData<List<Flower>> getFlower() {
        final MutableLiveData<List<Flower>> data = new MutableLiveData<>();
        FlowerApi.getFlowerService().getFlower().enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });

        return data;
    }
}
