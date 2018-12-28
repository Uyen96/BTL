package com.example.hongu.btl.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.CosmeticAdapter;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Cosmetic;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = "DetailActivity";
    private TextView mTextTitle;
    private RecyclerView mRecyclerCos;
    private TextView mTextMes;
    private SearchView mSearchView;

    private CosmeticAdapter mAdapter;
    private DBCosmeticManager mDB;
    private List<Cosmetic> mCosmetics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDB = new DBCosmeticManager(this);
        String title = getIntent().getStringExtra("title");
        mCosmetics = mDB.getCosmeticByEffect(title);
        mTextTitle = findViewById(R.id.text_title_detail_activity);
        mRecyclerCos = findViewById(R.id.list_recycle_cos_detail_activity);
        mTextTitle.setText(title.toUpperCase());
        if(mCosmetics.size() > 0){
            mRecyclerCos.setHasFixedSize(true);
            mAdapter = new CosmeticAdapter(this, mCosmetics);
            mRecyclerCos.setAdapter(mAdapter);
        }else {
            mTextMes = findViewById(R.id.text_mes_detail_activity);
            mTextMes.setText(getResources().getString(R.string.mes_null)
                    +" "+ title.toLowerCase());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemSearch = menu.findItem(R.id.search);
        mSearchView = (SearchView) itemSearch.getActionView();
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "onQueryTextChange: "+newText);
        mAdapter.filter(newText.trim());

        return false;
    }
}
