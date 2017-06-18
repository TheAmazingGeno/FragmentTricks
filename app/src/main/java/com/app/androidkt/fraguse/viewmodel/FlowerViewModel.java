package com.app.androidkt.fraguse.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.androidkt.fraguse.repository.remot.FlowerRepository;
import com.app.androidkt.fraguse.vo.Flower;

import java.util.List;

/**
 * Created by brijesh on 13/6/17.
 */

public class FlowerViewModel extends ViewModel {
    LiveData<List<Flower>> user;

    FlowerRepository flowerRepository;
    public FlowerViewModel() {
        flowerRepository=new FlowerRepository();
    }

    public void init(){
        if(user !=null){
            return;
        }
        user =flowerRepository.getFlower();
    }

    public LiveData<List<Flower>> geFlower() {
        return user;
    }
}
