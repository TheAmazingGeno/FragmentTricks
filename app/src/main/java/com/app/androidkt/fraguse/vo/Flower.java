package com.app.androidkt.fraguse.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brijesh on 13/6/17.
 */

public class Flower implements Parcelable {
    public String category;
    public float price;
    public String instructions;

    public String photo;
    public String name;
    public String productId;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category);
        dest.writeFloat(this.price);
        dest.writeString(this.instructions);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.productId);
    }

    public Flower() {
    }

    protected Flower(Parcel in) {
        this.category = in.readString();
        this.price = in.readFloat();
        this.instructions = in.readString();
        this.photo = in.readString();
        this.name = in.readString();
        this.productId = in.readString();
    }

    public static final Parcelable.Creator<Flower> CREATOR = new Parcelable.Creator<Flower>() {
        @Override
        public Flower createFromParcel(Parcel source) {
            return new Flower(source);
        }

        @Override
        public Flower[] newArray(int size) {
            return new Flower[size];
        }
    };
}
