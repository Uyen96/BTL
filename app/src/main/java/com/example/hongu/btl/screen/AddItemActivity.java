package com.example.hongu.btl.screen;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.EffectAdapter;
import com.example.hongu.btl.adapter.PagerAdapter;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.data.TransactionDatabase;
import com.example.hongu.btl.model.Effect;

import java.util.List;

public class AddItemActivity extends AppCompatActivity implements TransactionDatabase {

    private PagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    private DBCosmeticManager mDB;
    private EffectAdapter mEffectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setupViewPager();
        mDB = new DBCosmeticManager(this);
        FragmentManager manager = getSupportFragmentManager();


    }

    private void setupViewPager() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabs);
        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void addEffect(Effect effect) {
        mDB.addEffect(effect);
    }

    @Override
    public List<Effect> getAllEffect() {
        List<Effect> list;
        list = mDB.getAllEffect();
        mEffectAdapter = new EffectAdapter(this, list);
        return list;
    }

    @Override
    public int updateEffect(Effect effect) {
        return  mDB.updateEffect(effect);
    }
}

