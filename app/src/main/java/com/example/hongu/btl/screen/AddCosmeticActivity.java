package com.example.hongu.btl.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.CosmeticAdapter;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Cosmetic;

import java.util.List;

public class AddCosmeticActivity extends AppCompatActivity{

    private static final String TAG = "AddCosmeticActivity";
    private EditText mTextName;
    private EditText mTextPrice;
    private TextView mTextType;
    private EditText mTextFunction;
    private RadioButton mRadioButtonWater;
    private RadioButton mRadioButtonConsule;
    private RadioButton mRadioButtonPowder;
    private Button mButtonAdd;
    private ListView mListViewCosmetic;

    private DBCosmeticManager db;
    private CosmeticAdapter mCosmeticAdapter;
    private List<Cosmetic> mCosmetics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cosmetic);
        db = new DBCosmeticManager(this);
        mCosmetics = db.getAllCosmetics();
        setAdapter();
        initView();
        registerListener();
    }

    public void initView(){
        mTextName = findViewById(R.id.text_name);
        mTextPrice = findViewById(R.id.text_price);
        mTextFunction = findViewById(R.id.text_function);
        mTextType = findViewById(R.id.text_type);
        mRadioButtonWater = findViewById(R.id.radioButton_water);
        mRadioButtonConsule = findViewById(R.id.radioButton_capsule);
        mRadioButtonPowder = findViewById(R.id.radioButton_powder);
        mButtonAdd = findViewById(R.id.button_add);
        mListViewCosmetic = findViewById(R.id.list_cosmetic);
    }

    public void registerListener(){
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cosmetic cosmetic = createCosmetic();
                if(cosmetic != null){
                    db.addCosmetic(cosmetic);
                }
                Toast.makeText(AddCosmeticActivity.this,
                        getResources().getText(R.string.mes_add_sucess), Toast.LENGTH_LONG).show();
                mCosmetics.clear();
                mCosmetics.addAll(db.getAllCosmetics());
                setAdapter();
            }
        });
    }

    public Cosmetic createCosmetic(){
        String name = mTextName.getText().toString();
        float price = Float.valueOf(String.valueOf(mTextPrice.getText()));
        String function = mTextFunction.getText().toString();
        String type = "";
        if(mRadioButtonWater.isChecked()){
            type = mRadioButtonWater.getText().toString();
        }
        if(mRadioButtonPowder.isChecked()){
            type = mRadioButtonPowder.getText().toString();
        }
        if(mRadioButtonConsule.isChecked()){
            type = mRadioButtonConsule.getText().toString();
        }
        Cosmetic cosmetic = new Cosmetic(name, price, function, type);
        return cosmetic;
    }

    public void setAdapter(){
        if(mCosmeticAdapter == null){
            mCosmeticAdapter = new CosmeticAdapter(this,
                    R.layout.item_list_cosmetic,  mCosmetics);
            mListViewCosmetic.setAdapter(mCosmeticAdapter);
        }else{
            mCosmeticAdapter.notifyDataSetChanged();
            mListViewCosmetic.setSelection(mCosmeticAdapter.getCount()-1);
        }
        Log.d(TAG, "setAdapter: ");
    }
}
