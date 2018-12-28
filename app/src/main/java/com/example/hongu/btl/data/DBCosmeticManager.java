package com.example.hongu.btl.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String EFFECT = "effect";
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
                EFFECT + " TEXT," +
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
        values.put(EFFECT, cosmetic.getEffect());
        values.put(TYPE, cosmetic.getType());
        db.insert(TABLE_NAME_COSMETIC, null, values);
        db.close();
    }

    public List<Cosmetic> getAllCosmetics() {
        List<Cosmetic> listCos = new ArrayList<Cosmetic>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_COSMETIC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Cosmetic cosmetic = new Cosmetic();
                cosmetic.setId(cursor.getInt(0));
                cosmetic.setName(cursor.getString(1));
                cosmetic.setPrice(cursor.getFloat(2));
                cosmetic.setEffect(cursor.getString(3));
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

    public int updateCosmetic(Cosmetic cosmetic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,cosmetic.getName());
        values.put(PRICE,cosmetic.getPrice());
        values.put(EFFECT,cosmetic.getEffect());
        values.put(TYPE,cosmetic.getType());
        return db.update(TABLE_NAME_COSMETIC,values,ID +"=?",
                new String[] { String.valueOf(cosmetic.getId())});
    }

    public int deleteCosmetic( int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME_COSMETIC, ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        return result;
    }

    public List<Cosmetic> getCosmeticByEffect(String str){
        List<Cosmetic> list = new ArrayList<Cosmetic>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COSMETIC,null, EFFECT +" LIKE ?",
                new String[] { "%"+str+"%" }, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cosmetic cosmetic = new Cosmetic();
            cosmetic.setId(cursor.getInt(0));
            cosmetic.setName(cursor.getString(1));
            cosmetic.setPrice(cursor.getFloat(2));
            cosmetic.setEffect(cursor.getString(3));
            cosmetic.setType(cursor.getString(4));
            list.add(cosmetic);
            cursor.moveToNext();
        }
//        cursor.close();
//        db.close();
        return list;
    }

    public List<Cosmetic> getCosmeticByName(String name){
        List<Cosmetic> list = new ArrayList<Cosmetic>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COSMETIC,null,NAME +" = ?", new String[] { "%"+name+"%" }, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cosmetic cosmetic = new Cosmetic();
            cosmetic.setId(cursor.getInt(0));
            cosmetic.setName(cursor.getString(1));
            cosmetic.setPrice(cursor.getFloat(2));
            cosmetic.setEffect(cursor.getString(3));
            cosmetic.setType(cursor.getString(4));
            list.add(cosmetic);
            cursor.moveToNext();
        }
//        cursor.close();
//        db.close();
        return list;
    }
    public List<String> getName(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COSMETIC,
                null,null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
//        cursor.close();
//        db.close();
        return list;
    }
    public List<String> getType(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COSMETIC,
                null,null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(4));
            cursor.moveToNext();
        }
//        cursor.close();
//        db.close();
        return list;
    }

    public List<Cosmetic> getCosmeticByType(String type){
        List<Cosmetic> list = new ArrayList<Cosmetic>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_COSMETIC,null,TYPE +" = ?",
                new String[] { "%"+type+"%" }, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cosmetic cosmetic = new Cosmetic();
            cosmetic.setId(cursor.getInt(0));
            cosmetic.setName(cursor.getString(1));
            cosmetic.setPrice(cursor.getFloat(2));
            cosmetic.setEffect(cursor.getString(3));
            cosmetic.setType(cursor.getString(4));
            list.add(cosmetic);
            cursor.moveToNext();
        }
//        cursor.close();
//        db.close();
        return list;
    }
}
