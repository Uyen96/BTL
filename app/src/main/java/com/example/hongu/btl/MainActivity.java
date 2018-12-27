package com.example.hongu.btl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.hongu.btl.adapter.EffectAdapter;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Effect;
import com.example.hongu.btl.screen.AddItemActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerEffect;
    private ImageView mImageSearch;
    private FloatingActionButton mActionButtonAdd;

    private EffectAdapter mEffectAdapter;
    private DBCosmeticManager mDB;
    private List<Effect> mEffects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDB = new DBCosmeticManager(this);
        mEffects = mDB.getAllEffect();
        mEffectAdapter = new EffectAdapter(this, mEffects);
        mRecyclerEffect.setLayoutManager( new LinearLayoutManager(this));
        mRecyclerEffect.setAdapter(mEffectAdapter);


        Log.d("Main", " abc" + mEffects.size());
        // initRecycle();
        registerListener();
    }

    public void initView() {
        mRecyclerEffect = findViewById(R.id.recycle_effect);
        mImageSearch = findViewById(R.id.image_search);
        mActionButtonAdd = findViewById(R.id.fab);
    }

//    public void initRecycle() {
//       // List<Effect> effects = getData();
//        if(effects == null){
//            Log.d("MainActivity", "initRecycle: null");
//        }
//        else{
//            EffectAdapter adapter = new EffectAdapter(this, effects);
//            mRecyclerEffect.setHasFixedSize(true);
//            mRecyclerEffect.setAdapter(adapter);
//        }
//
//    }

    public void registerListener() {
        mActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
