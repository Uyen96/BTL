package com.example.hongu.btl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongu.btl.adapter.EffectAdapter;
import com.example.hongu.btl.adapter.OnItemClickListener;
import com.example.hongu.btl.data.DBCosmeticManager;
import com.example.hongu.btl.model.Effect;
import com.example.hongu.btl.screen.AddItemActivity;
import com.example.hongu.btl.screen.DetailActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerEffect;
    private FloatingActionButton mActionButtonAdd;
    private SearchView mSearchView;

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
        mEffectAdapter.setOnItemClickListener(this);
        mRecyclerEffect.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerEffect.setAdapter(mEffectAdapter);
        registerListener();
    }

    public void initView() {
        mRecyclerEffect = findViewById(R.id.recycle_effect);
        mActionButtonAdd = findViewById(R.id.fab);
    }

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

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemSearch = menu.findItem(R.id.search);
        mSearchView = (SearchView) itemSearch.getActionView();
        mSearchView.setOnQueryTextListener(this);
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

    @Override
    public void onRowClicked(int position) {
        startDetailActivity(mEffects.get(position));
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_edit, null);
        //TextView textName = view.findViewById(R.id.dialog_edit);
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
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.mes_update_success), Toast.LENGTH_LONG).show();
                    updateListEffect();
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.mes_update_fail), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
    }

    public void showDialogDelete(final Effect effect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.mes_confirm_delete))
                .setPositiveButton(getResources().getString(R.string.mes_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int result = mDB.deleteEffect(effect.getId());
                                if (result > 0) {
                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.mes_del),
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
        mEffectAdapter.notifyDataSetChanged();
    }

    public void startDetailActivity(Effect effect) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", effect.getName());
        this.startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
