package com.example.restaurantmobile;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    public void dish(View view) {
        Intent intent = new Intent(this, DishActivity.class);
        startActivity(intent);
    }
    public void drink(View view) {
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }
    public void waiter(View view) {
        Intent intent = new Intent(this, WaiterActivity.class);
        startActivity(intent);
    }
    public void orders(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}