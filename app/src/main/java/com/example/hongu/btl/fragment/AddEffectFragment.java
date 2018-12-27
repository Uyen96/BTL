package com.example.hongu.btl.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongu.btl.R;
import com.example.hongu.btl.adapter.EffectAdapter;
import com.example.hongu.btl.adapter.OnItemClickListener;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.data.TransactionDatabase;
import com.example.hongu.btl.model.Effect;

import java.util.List;

public class AddEffectFragment extends Fragment implements OnItemClickListener {

    private View mView;
    private EditText mTextNameEffect;
    private EditText mTextDes;
    private Button mButtonAddEffect;
    private RecyclerView mRecyclerList;

    private TransactionDatabase mCallback;
    private DBCosmeticManager mDB;
    private EffectAdapter mAdapter;
    private List<Effect> mEffects;

    public AddEffectFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_effect, container, false);
        mEffects = mCallback.getAllEffect();
        Log.d("Main", "onCreateView: " + mEffects.size());
        initView();
        mDB = new DBCosmeticManager(getActivity());
        mAdapter = new EffectAdapter(getActivity(), mEffects);
        mAdapter.setOnItemClickListener(AddEffectFragment.this);
        mRecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerList.setHasFixedSize(true);
        mRecyclerList.setAdapter(mAdapter);
        registerListener();
        return mView;
    }

    public void initView() {
        mTextNameEffect = mView.findViewById(R.id.frag_add_func_name);
        mTextDes = mView.findViewById(R.id.frag_add_func_description);
        mButtonAddEffect = mView.findViewById(R.id.frag_button_add_effect);
        mRecyclerList = mView.findViewById(R.id.frag_list_effect);
    }

    public void registerListener() {
        mButtonAddEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Effect effect = createEffect();
                mCallback.addEffect(effect);
                Toast.makeText(getActivity(),
                        getResources().getText(R.string.mes_add_sucess), Toast.LENGTH_LONG).show();
                mTextNameEffect.setText("");
                mTextDes.setText("");
                updateListEffect();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (TransactionDatabase) getActivity();
    }

    public Effect createEffect() {
        String name = mTextNameEffect.getText().toString();
        String description = mTextDes.getText().toString();
        Effect effect = new Effect(name, description);
        return effect;
    }

    @Override
    public void onRowClicked(int position) {
        // showDialogEdit(mEffects.get(position));
    }

    @Override
    public void onViewClicked(View v, int position) {
        if (v.getId() == R.id.list_image_delete) {
            showDialogDelete(mEffects.get(position));
            Log.d("Main", "onViewClicked: ");
        } else {
            if (v.getId() == R.id.list_image_edit) {
                showDialogEdit(mEffects.get(position));
            }
        }

    }

    public void showDialogEdit(Effect effect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_edit, null);
        TextView textName = view.findViewById(R.id.dialog_edit);
        final TextView textId = view.findViewById(R.id.dialog_text_id);
        final EditText textNameEffect = view.findViewById(R.id.dialog_text_name_effect);
        final EditText textDesEffect = view.findViewById(R.id.dialog_text_des_effect);
        Button buttonEdit = view.findViewById(R.id.dialog_button_edit_effect);
        textId.setText(String.valueOf(effect.getId()));
        textNameEffect.setText(effect.getName());
        textDesEffect.setText(effect.getDescription());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Effect eff = new Effect();
                eff.setId(Integer.parseInt(String.valueOf(textId.getText())));
                eff.setName(textNameEffect.getText().toString());
                eff.setDescription(textDesEffect.getText().toString());
                int result = mDB.updateEffect(eff);
                if (result > 0) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.mes_update_success), Toast.LENGTH_LONG).show();
                    updateListEffect();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.mes_update_fail), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
    }

    public void showDialogDelete(final Effect effect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getResources().getString(R.string.mes_confirm_delete))
                .setPositiveButton(getResources().getString(R.string.mes_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int result = mDB.deleteEffect(effect.getId());
                                if (result > 0) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.mes_del),
                                            Toast.LENGTH_LONG).show();
                                    updateListEffect();
                                }

                            }
                        }).setNegativeButton(getResources().getString(R.string.mes_no), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void updateListEffect() {
        mEffects.clear();
        mEffects.addAll(mDB.getAllEffect());
        mAdapter.notifyDataSetChanged();
    }
}


