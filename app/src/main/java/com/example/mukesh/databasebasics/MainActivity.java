package com.example.mukesh.databasebasics;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private TextView dbTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/AndroidDBBasic.db";
        dbTextView = findViewById(R.id.textView);
        try{
            db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
            Toast.makeText(this,"DB opened",Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String dbQuery = "select * from friends";
        Cursor c = null;

        try{
            c = db.rawQuery(dbQuery,null);
            Toast.makeText(this,"DB Query made",Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        int nameColIndex = c.getColumnIndex("name");
        int phoneColIndex = c.getColumnIndex("phone");

        String buffer = "+++++++++++++\n\n";

        while(c.moveToNext()){
            buffer = buffer + c.getString(nameColIndex) + " ===> " + c.getInt(phoneColIndex) +"\n";
         }

         buffer = buffer + "\n\n++++++++++";

        dbTextView.setText(buffer);
    }
}
