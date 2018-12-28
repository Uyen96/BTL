package com.example.hongu.btl.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.CosmeticAdapter;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Cosmetic;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    private CosmeticAdapter mAdapter;
    private DBCosmeticManager mdb;
    private List<Cosmetic> mCosmetics;
    private List<String> mSuggests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //initView();
        mdb = new DBCosmeticManager(this);
        mRecycler.setHasFixedSize(true);
        mCosmetics = mdb.getAllCosmetics();
        mAdapter = new CosmeticAdapter(this, mCosmetics);
    }

//    private void startSearch(String text) {
//        mAdapter  = new CosmeticAdapter(this, mdb.getCosmeticByName(text));
//        mSearchBar.setLastSuggestions(mSuggests);
//    }
//
//    private void loadSuggestList() {
//        mSuggests = mdb.getName();
//        mSearchBar.setLastSuggestions(mSuggests);
//    }
//
//    private void initView() {
//        mRecycler = findViewById(R.id.recycle_search);
//        mSearchBar = findViewById(R.id.search_bar);
//    }
}
