package com.example.restaurantmobile;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditingActivity extends AppCompatActivity {
    EditText firstBox;
    EditText secondBox;
    EditText thirdBox;
    EditText fourthBox;
    Spinner firthBox;
    Button delButton;
    Button saveButton;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long waiter_id = 0;
    long rowId = 0;
    String table = "Dish";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        firstBox = findViewById(R.id.name);
        secondBox = findViewById(R.id.cost);
        thirdBox = findViewById(R.id.price);
        fourthBox = findViewById(R.id.weight);
        firthBox = findViewById(R.id.spinner);
        firthBox.setVisibility(View.GONE);
        delButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        try{
            rowId = extras.getLong("id");
            table = extras.getString("table");
        }
        catch (Exception ex){}

        if (table.equals("Dish")){
            firstBox.setHint("Введите название");
            fourthBox.setHint("Введите вес");
        }
        else if (table.equals("Drink")) {
            firstBox.setHint("Введите название");
            fourthBox.setHint("Введите объём");
        }
        else if (table.equals("Waiter")){
            firstBox.setHint("Введите имя");
            secondBox.setVisibility(View.GONE);
            thirdBox.setVisibility(View.GONE);
            fourthBox.setVisibility(View.GONE);
        }
        else if (table.equals("Orders")) {
            firstBox.setHint("Введите дату");
            secondBox.setHint("Введите номер стола");
            thirdBox.setVisibility(View.GONE);
            fourthBox.setVisibility(View.GONE);
            firthBox.setVisibility(View.VISIBLE);

            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_WAITER, null);

            String[] from = new String[]{"Name"};
            int[] to = new int[]{android.R.id.text1};
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, userCursor, from, to);
            sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            firthBox = (Spinner) this.findViewById(R.id.spinner);
            firthBox.setAdapter(sca);

            firthBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    waiter_id = id;
                }

                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            firthBox.setSelection(userCursor.getInt(0));
        }
        if (rowId > 0) {
            userCursor = db.rawQuery("select * from " + table + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});
            userCursor.moveToFirst();
            if (table.equals("Dish") || table.equals("Drink")){
                firstBox.setText(userCursor.getString(1));
                secondBox.setText(String.valueOf(userCursor.getFloat(2)));
                thirdBox.setText(String.valueOf(userCursor.getFloat(3)));
                fourthBox.setText(String.valueOf(userCursor.getInt(4)));
            }
            if (table.equals("Waiter")){
                firstBox.setText(userCursor.getString(1));
            }
            if (table.equals("Orders")){
                firstBox.setText(userCursor.getString(1));
                secondBox.setText(String.valueOf(userCursor.getInt(2)));
            }
            userCursor.close();
        } else {
            delButton.setVisibility(View.GONE);
        }
    }
    public void save(View view){
        try{
            ContentValues cv = new ContentValues();
            if (table.equals("Dish") || table.equals("Drink") || table.equals("Waiter")){
                cv.put(DatabaseHelper.COLUMN_NAME, firstBox.getText().toString());
                if (table.equals("Dish") || table.equals("Drink"))
                {
                    cv.put(DatabaseHelper.COLUMN_COAST, Float.parseFloat(secondBox.getText().toString()));
                    cv.put(DatabaseHelper.COLUMN_PRICE, Float.parseFloat(thirdBox.getText().toString()));
                    if (table.equals("Dish")){
                        cv.put(DatabaseHelper.COLUMN_WEIGHT, Integer.parseInt(fourthBox.getText().toString()));
                    }
                    else {
                        cv.put(DatabaseHelper.COLUMN_VOLUME, Integer.parseInt(fourthBox.getText().toString()));
                    }
                }
            }
            if (table.equals("Orders")){
                cv.put(DatabaseHelper.COLUMN_DATE, firstBox.getText().toString());
                cv.put(DatabaseHelper.COLUMN_TABLE, Integer.parseInt(secondBox.getText().toString()));
                cv.put(DatabaseHelper.COLUMN_IDWAITER, Integer.parseInt(String.valueOf(waiter_id)));
            }
            if (rowId > 0) {
                db.update(table, cv, DatabaseHelper.COLUMN_ID + "=" + rowId, null);
            } else {
                db.insert(table, null, cv);
            }
            goHome();
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    e.toString(),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    public void delete(View view){
        db.delete(table, "_id = ?", new String[]{String.valueOf(rowId)});
        goHome();
    }
    private void goHome(){
        db.close();
        Intent intent = new Intent(this, MainActivity.class);

        if (table.equals("Dish")){
            intent = new Intent(this, DishActivity.class);

        }
        else if (table.equals("Drink")) {
            intent = new Intent(this, DrinkActivity.class);
        }
        else if (table.equals("Waiter")){
            intent = new Intent(this, WaiterActivity.class);
        }
        else if (table.equals("Orders")){
            intent = new Intent(this, OrderActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}