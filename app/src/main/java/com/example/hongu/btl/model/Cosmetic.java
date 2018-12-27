package com.example.hongu.btl.model;

public class Cosmetic {
    private int mId;
    private String mName;
    private float mPrice;
    private String mFunction;
    private String mType;

    public Cosmetic() {

    }

    public Cosmetic(String name, float price, String function, String type) {
        mName = name;
        mPrice = price;
        mFunction = function;
        mType = type;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public String getFunction() {
        return mFunction;
    }

    public void setFunction(String function) {
        mFunction = function;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}

