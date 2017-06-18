package com.app.androidkt.fraguse.util;

import android.view.View;

import com.app.androidkt.fraguse.vo.Flower;

/**
 * Created by brijesh on 14/6/17.
 */

public interface FlowerItemClickListener {
    void onItemClick(FlowerAdapter.FlowerViewHolder flowerViewHolder, Flower flower);
}
