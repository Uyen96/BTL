package com.example.hongu.btl.model;

public class Effect {
    private int mId;
    private String mName;
    private String mDescription;

    public Effect() {
    }
    public Effect(int id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
    }

    public Effect(String name, String description) {
        mName = name;
        mDescription = description;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
