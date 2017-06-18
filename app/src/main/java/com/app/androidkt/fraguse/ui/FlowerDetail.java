package com.app.androidkt.fraguse.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.androidkt.fraguse.Constants;
import com.app.androidkt.fraguse.R;
import com.app.androidkt.fraguse.vo.Flower;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FlowerDetail extends Fragment {

    public static final String ARG_FLOWER = "flower";

    private Flower flower;

    @BindView(R.id.flowerDescription)
    TextView flowerDescription;

    @BindView(R.id.flowerDetailImage)
    ImageView flowerImage;

    public FlowerDetail() {
    }

    public static FlowerDetail newInstance(Flower flower) {
        FlowerDetail fragment = new FlowerDetail();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FLOWER, flower);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flower = getArguments().getParcelable(ARG_FLOWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flower_detail, container, false);
        ButterKnife.bind(this,rootView);

        Glide.with(getActivity())
                .load(Constants.BASE_URL+"/photos/"+flower.photo)
                .into(flowerImage);

        flowerDescription.setText(flower.instructions);
        return rootView;
    }

}
