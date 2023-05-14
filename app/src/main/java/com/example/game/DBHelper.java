package com.example.game;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import android.util.SparseIntArray;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {


    // good 100 %

    private Context context;
    private static final String DATABASE_NAME = "GameTown.db";
    private static final String TABLE_NAME = "my_games";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "game_name";
    private static final String COLUMN_TYPE = "game_type";
    private static final String COLUMN_PRICE = "game_price";  // or int

    //***********************************************************************************
    // cons
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }


    //***********************************************************************************
    // on create
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table usersdb(username TEXT primary key, password TEXT ,email TEXT ,phone NUMBER)");
        //Deema

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_PRICE + " INTEGER);";
        MyDB.execSQL(query);
    }

    //***********************************************************************************

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists usersdb");

        //deema
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);
    }

    //***********************************************************************************

    //reem

    public Boolean insertData(String username, String password ,String email , String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        long result = MyDB.insert("usersdb", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersdb where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersdb where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkphone(String phone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersdb where phone = ?", new String[]{phone});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersdb where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    //***********************************************************************************

    //deema
    void addGame(String name, String type, int price){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_PRICE, price);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result==-1){
            // nothing happens. no one is added.
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();

        } }

    //***********************************************************************************
    //for store data
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    } //good

    //***********************************************************************************
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            // nothing happens. no one is deleted.
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }

    }

}

