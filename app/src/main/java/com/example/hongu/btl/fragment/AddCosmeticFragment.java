package com.example.hongu.btl.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.CosmeticAdapter;
import com.example.hongu.btl.adapter.OnItemClickListener;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Cosmetic;
import com.example.hongu.btl.model.Effect;

import java.util.List;

public class AddCosmeticFragment extends Fragment implements OnItemClickListener {

    private View mView;
    private EditText mTextName;
    private EditText mTextPrice;
    private EditText mTextEffect;
    private TextView mTextType;
    private RadioButton mButtonWater;
    private RadioButton mButtonCapsule;
    private RadioButton mButtonPower;
    private Button mButtonAdd;
    private RecyclerView mRecyclerCosmetic;

    private CosmeticAdapter mAdapter;
    private List<Cosmetic> mCosmetics;
    private DBCosmeticManager mDB;

    public AddCosmeticFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_cosmetic, container, false);
        initView();
        mDB = new DBCosmeticManager(getActivity());
        mCosmetics = mDB.getAllCosmetics();
        initRecycleView();
        registerListener();
        return mView;
    }

    private void registerListener() {
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cosmetic cosmetic = createCosmetic();
                if (cosmetic != null) {
                    mDB.addCosmetic(cosmetic);
                }
                Toast.makeText(getActivity(),
                        getResources().getText(R.string.mes_add_sucess), Toast.LENGTH_LONG).show();
                updateListCosmetic();
                resetView();
            }
        });
    }

    public void initView() {
        mTextName = mView.findViewById(R.id.text_cos_name);
        mTextPrice = mView.findViewById(R.id.text_cos_price);
        mTextEffect = mView.findViewById(R.id.text_cos_effect);
        mTextType = mView.findViewById(R.id.text_cos_type);
        mButtonWater = mView.findViewById(R.id.radioButton_water);
        mButtonCapsule = mView.findViewById(R.id.radioButton_capsule);
        mButtonPower = mView.findViewById(R.id.radioButton_powder);
        mButtonAdd = mView.findViewById(R.id.button_cos_add);
        mRecyclerCosmetic = mView.findViewById(R.id.fragment_recycle_list_cosmetic);

    }

    public void initRecycleView() {
        mAdapter = new CosmeticAdapter(getActivity(), mCosmetics);
        mAdapter.setOnItemClickListener(AddCosmeticFragment.this);
        mRecyclerCosmetic.setHasFixedSize(true);
        mRecyclerCosmetic.setAdapter(mAdapter);
    }

    public Cosmetic createCosmetic() {
        String name = mTextName.getText().toString();
        float price = Float.valueOf(mTextPrice.getText().toString());
        String effect = mTextEffect.getText().toString();
        String type = "";
        if (mButtonWater.isChecked()) {
            type = mButtonWater.getText().toString();
        }
        if (mButtonPower.isChecked()) {
            type = mButtonPower.getText().toString();
        }
        if (mButtonCapsule.isChecked()) {
            type = mButtonCapsule.getText().toString();
        }
        Cosmetic cosmetic = new Cosmetic(name, price, effect, type);
        return cosmetic;
    }

    @Override
    public void onRowClicked(int position) {

    }

    @Override
    public void onViewClicked(View v, int position) {
        if(v.getId() == R.id.image_edit_cos){
            showDialogEdit(mCosmetics.get(position));
        }
        if(v.getId() == R.id.image_del_cos){
            showDialogDelete(mCosmetics.get(position));
        }
    }

    public void updateListCosmetic() {
        mCosmetics.clear();
        mCosmetics.addAll(mDB.getAllCosmetics());
        mAdapter.notifyDataSetChanged();
    }

    public void resetView() {
        mTextName.setText("");
        mTextPrice.setText("");
        mTextEffect.setText("");
        mButtonWater.setChecked(false);
        mButtonPower.setChecked(false);
        mButtonCapsule.setChecked(false);
    }

    public void showDialogEdit(Cosmetic cosmetic) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_cosmetic, null);
        //TextView textName = view.findViewById(R.id.dialog_edit);
        final TextView textId = view.findViewById(R.id.text_id_dialog_cos);
        final EditText textName = view.findViewById(R.id.text_name_dialog_cos);
        final EditText textPrice = view.findViewById(R.id.text_price_dialog_cos);
        final EditText textEffect = view.findViewById(R.id.text_effect_dialog_cos);
        final EditText textType = view.findViewById(R.id.text_type_dialog_cos);

        Button buttonEdit = view.findViewById(R.id.button_update_dialog_cos);
        textId.setText(String.valueOf(cosmetic.getId()));
        textName.setText(cosmetic.getName());
        textPrice.setText(String.valueOf(cosmetic.getPrice()));
        textEffect.setText(cosmetic.getEffect());
        textType.setText(cosmetic.getType());
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cosmetic cos = new Cosmetic();
                cos.setId(Integer.parseInt(String.valueOf(textId.getText())));
                cos.setName(textName.getText().toString());
                cos.setPrice(Float.parseFloat(textPrice.getText().toString()));
                cos.setEffect(textEffect.getText().toString());
                cos.setType(textType.getText().toString());
                int result = mDB.updateCosmetic(cos);
                if (result > 0) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.mes_update_success), Toast.LENGTH_LONG).show();
                    updateListCosmetic();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.mes_update_fail), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
    }

    public void showDialogDelete(final Cosmetic cosmetic) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getResources().getString(R.string.mes_confirm_delete))
                .setPositiveButton(getResources().getString(R.string.mes_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int result = mDB.deleteCosmetic(cosmetic.getId());
                                if (result > 0) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.mes_del),
                                            Toast.LENGTH_LONG).show();
                                    updateListCosmetic();
                                }

                            }
                        }).setNegativeButton(getResources().getString(R.string.mes_no), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
