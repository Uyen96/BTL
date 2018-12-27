package com.example.hongu.btl.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hongu.btl.R;

public class AddCosmeticFragment extends Fragment {
    private String mTitle;
    private int mPage;

    public AddCosmeticFragment() {
    }

    //    public static AddCosmeticFragment newInstance(int page, String title){
//        AddCosmeticFragment addCosmeticFragment = new AddCosmeticFragment();
//        Bundle args = new Bundle();
//        args.putInt("page", page);
//        args.putString("title", title);
//        addCosmeticFragment.setArguments(args);
//        return addCosmeticFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_add_cosmetic, container, false);
        return view;
    }
}
