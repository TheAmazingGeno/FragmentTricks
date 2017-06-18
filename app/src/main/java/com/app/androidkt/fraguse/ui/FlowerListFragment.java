package com.app.androidkt.fraguse.ui;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.androidkt.fraguse.R;
import com.app.androidkt.fraguse.util.DetailsTransition;
import com.app.androidkt.fraguse.util.FlowerAdapter;
import com.app.androidkt.fraguse.util.FlowerItemClickListener;
import com.app.androidkt.fraguse.viewmodel.FlowerViewModel;
import com.app.androidkt.fraguse.vo.Flower;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlowerListFragment extends Fragment implements FlowerItemClickListener, LifecycleRegistryOwner {

    public static final String TAG = "FlowerListFragment";
    @BindView(R.id.flower_list)
    RecyclerView flowerListView;

    private List<Flower> flowers;
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private FlowerAdapter flowerAdapter;

    public FlowerListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flowers = new ArrayList<>();
        flowerAdapter = new FlowerAdapter(flowers, getActivity(), this);

        final FlowerViewModel flowerViewModel = ViewModelProviders.of(this).get(FlowerViewModel.class);
        flowerViewModel.init();
        subscribe(flowerViewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void subscribe(FlowerViewModel flowerViewModel) {

        flowerViewModel.geFlower().observe(this, new Observer<List<Flower>>() {
            @Override
            public void onChanged(@Nullable List<Flower> flowers) {
                flowerAdapter.setFlowers(flowers);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower_list, container, false);
        ButterKnife.bind(this, view);
        int numberOfColumns = 2;
        flowerListView.setLayoutManager((new GridLayoutManager(getActivity(), numberOfColumns)));
        flowerListView.setAdapter(flowerAdapter);
        return view;
    }

    @Override
    public void onItemClick(FlowerAdapter.FlowerViewHolder flowerViewHolder, Flower flower) {

        FlowerDetail flowerDetail = FlowerDetail.newInstance(flower);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flowerDetail.setSharedElementEnterTransition(new DetailsTransition());
            flowerDetail.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            flowerDetail.setSharedElementReturnTransition(new DetailsTransition());
        }


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addSharedElement(flowerViewHolder.flowerImage, "kittenImage");
        fragmentTransaction.replace(R.id.main_container, flowerDetail);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
