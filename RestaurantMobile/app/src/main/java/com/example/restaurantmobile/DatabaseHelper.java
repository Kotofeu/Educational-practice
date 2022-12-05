package com.example.restaurantmobile;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "RestaurantMobile.db";
    private static final int SCHEMA =  16;
    static final String TABLE_DISH = "Dish";
    static final String TABLE_DRINK = "Drink";
    static final String TABLE_ORDERS = "Orders";
    static final String TABLE_WAITER = "Waiter";
    static final String COLUMN_ID = "_id";

    static final String COLUMN_NAME = "Name";

    static final String COLUMN_COAST = "Coast_price";
    static final String COLUMN_PRICE = "Price";

    static final String COLUMN_WEIGHT = "Weight";
    static final String COLUMN_VOLUME = "Volume";

    static final String COLUMN_DATE = "Date";
    static final String COLUMN_TABLE = "Table_number";
    static final String COLUMN_IDWAITER = "id_waiter";



    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME + SCHEMA;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){
        System.out.println("FIND DB");
        File file = new File(DB_PATH);
        if (!file.exists()) {
            System.out.println("CREATE DB");
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}