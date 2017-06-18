package com.app.androidkt.fraguse.util;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.androidkt.fraguse.Constants;
import com.app.androidkt.fraguse.R;
import com.app.androidkt.fraguse.vo.Flower;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by brijesh on 13/6/17.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {

    FlowerItemClickListener flowerItemClickListener;
    private List<Flower> flowers;
    private Context context;

    public FlowerAdapter(List<Flower> flowers, Context context, FlowerItemClickListener flowerItemClickListener) {
        this.context = context;
        this.flowers = flowers;
        this.flowerItemClickListener = flowerItemClickListener;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FlowerViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.flower_item, parent, false);
        viewHolder = new FlowerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, int position) {

        FlowerViewHolder flowerViewHolder = (FlowerViewHolder) holder;
        final Flower flower = flowers.get(position);
        flowerViewHolder.flowerName.setText(flower.name);
        flowerViewHolder.flowerPrice.setText(String.valueOf(flower.price));
        if (!TextUtils.isEmpty(flower.photo)) {
            Glide.
                    with(context)
                    .load(Constants.BASE_URL + "/photos/" + flower.photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(flowerViewHolder.flowerImage);
        }

        ViewCompat.setTransitionName(holder.flowerImage,position+"_image");
        flowerViewHolder.flowerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowerItemClickListener.onItemClick(holder,flower);
            }
        });
    }


    public void setFlowers(List<Flower> u) {
        int count = getItemCount();
        flowers.addAll(u);
        notifyItemRangeInserted(count, u.size());
    }

    @Override
    public int getItemCount() {
        return flowers == null ? 0 : flowers.size();
    }


    public static class FlowerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.flowerName)
        public TextView flowerName;
        @BindView(R.id.flowerPrice)
        public TextView flowerPrice;
        @BindView(R.id.flowerImage)
        public ImageView flowerImage;

        public FlowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
