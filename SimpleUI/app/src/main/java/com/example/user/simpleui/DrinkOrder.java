package com.example.user.simpleui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2016/8/1.
 */
public class DrinkOrder implements Parcelable {

    Drink drink;
    int mNumber=0;
    int lNumber=0;
    String ice="Regular";
    String sugar="Regular";
    String note="";

    public DrinkOrder(Drink drink)
    {
        this.drink = drink;
    }

    public int total()
    {
        return drink.lPrice * lNumber + drink.mPrice * mNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(drink, flags);
        dest.writeInt(mNumber);
        dest.writeInt(lNumber);
        dest.writeString(ice);
        dest.writeString(sugar);
        dest.writeString(note);
    }

    protected DrinkOrder(Parcel in) {
        drink = in.readParcelable(Drink.class.getClassLoader());
        mNumber = in.readInt();
        lNumber = in.readInt();
        ice = in.readString();
        sugar = in.readString();
        note = in.readString();
    }

    public static final Creator<DrinkOrder> CREATOR = new Creator<DrinkOrder>() {
        @Override
        public DrinkOrder createFromParcel(Parcel source) {
            return new DrinkOrder(source);
        }

        @Override
        public DrinkOrder[] newArray(int size) {
            return new DrinkOrder[size];
        }
    };



}
