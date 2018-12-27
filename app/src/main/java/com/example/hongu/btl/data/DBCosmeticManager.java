package com.example.hongu.btl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hongu.btl.model.Cosmetic;
import com.example.hongu.btl.model.Effect;

import java.util.ArrayList;
import java.util.List;

public class DBCosmeticManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "cosmetic_manager";
    private static final String TABLE_NAME_COSMETIC = "cosmetic";
    private static final String TABLE_NAME_EFFECT = "effect";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String FUNCTION = "function";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";
    private static final String[] COLUM_EFFECT = {ID, NAME, DESCRIPTION};
    private static final int VERSION = 1;


    public DBCosmeticManager(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME_COSMETIC + " (" +
                ID + " integer primary key, " +
                NAME + " TEXT, " +
                PRICE + " float, " +
                FUNCTION + " TEXT," +
                TYPE + " TEXT)";
        db.execSQL(sqlQuery);
        String sql = " CREATE TABLE " + TABLE_NAME_EFFECT + " (" +
                ID + " integer primary key, " +
                NAME + " TEXT, " +
                DESCRIPTION + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void addCosmetic(Cosmetic cosmetic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, cosmetic.getName());
        values.put(PRICE, cosmetic.getPrice());
        values.put(FUNCTION, cosmetic.getFunction());
        values.put(TYPE, cosmetic.getType());
        db.insert(TABLE_NAME_COSMETIC, null, values);
        db.close();
    }

    public List<Cosmetic> getAllCosmetics() {
        List<Cosmetic> listCos = new ArrayList<Cosmetic>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_EFFECT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Cosmetic cosmetic = new Cosmetic();
                cosmetic.setId(cursor.getInt(0));
                cosmetic.setName(cursor.getString(1));
                cosmetic.setPrice(cursor.getFloat(2));
                cosmetic.setFunction(cursor.getString(3));
                cosmetic.setType(cursor.getString(4));
                listCos.add(cosmetic);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listCos;
    }

    public void addEffect(Effect effect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, effect.getName());
        values.put(DESCRIPTION, effect.getDescription());
        db.insert(TABLE_NAME_EFFECT, null, values);
        db.close();
    }

    public List<Effect> getAllEffect() {
        List<Effect> listEffect = new ArrayList<Effect>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_EFFECT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Effect effect = new Effect();
            effect.setId(cursor.getInt(0));
            effect.setName(cursor.getString(1));
            effect.setDescription(cursor.getString(2));
            listEffect.add(effect);
            cursor.moveToNext();

        }
//        cursor.close();
//        db.close();
        return listEffect;
    }

    public int updateEffect(Effect effect){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME,effect.getName());
            values.put(DESCRIPTION,effect.getDescription());
            return db.update(TABLE_NAME_EFFECT,values,ID +"=?",
                    new String[] { String.valueOf(effect.getId())});
    }

    public int deleteEffect( int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME_EFFECT, ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        return result;
    }
}
