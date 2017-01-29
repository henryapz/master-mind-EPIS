package com.example.henry.facebook_prueba01;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.Profile;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registro);
        SQLiteDatabase bd = openOrCreateDatabase("mastermind2", MODE_PRIVATE,null);


        bd.execSQL("CREATE TABLE IF NOT EXISTS MasMinCab\n" +
                "(\n" +
                "id_cab INTEGER NOT NULL,\n" +
                "name TEXT, \n" +
                "lastname TEXT, \n" +
                "CONSTRAINT Key1 PRIMARY KEY (id_cab), \n" +
                "CONSTRAINT id UNIQUE (id_cab) \n" +
                ");"
        );

        bd.execSQL("CREATE TABLE IF NOT EXISTS MasMinDet \n" +
                "( \n"+
                "id_det INTEGER NOT NULL, \n"+
                "puntaje INTEGER, \n"+
                "id_cab INTEGER, \n"+
                "CONSTRAINT Key2 PRIMARY KEY (id_det), \n"+
                "CONSTRAINT id_det UNIQUE (id_det), \n"+
                "CONSTRAINT Relationship1 FOREIGN KEY (id_cab) REFERENCES MasMinCab (id_cab) \n"+
                "); \n"+

                "CREATE INDEX IX_Relationship2 ON MasMinDet (id_cab);"
        );


        Cursor cursor = bd.rawQuery("SELECT * FROM MasMinCab",null);
        Profile profile = Profile.getCurrentProfile();
        cursor.moveToFirst();
        String name = "";
        String lname = "";
        int id = 0;
        boolean extId = false;
        while(cursor.moveToNext()){
            try{
                id = cursor.getInt(cursor.getColumnIndex("id_cab"));
                name = cursor.getString(cursor.getColumnIndex("name"));
                lname = cursor.getString(cursor.getColumnIndex("lastname"));
                if (name == (profile.getName() + " " + profile.getMiddleName()) && lname == profile.getLastName()){
                    extId = true;
                    break;
                }
            }catch (Exception e){}
        }
        if(cursor.isLast()  && name != (profile.getName() + " " + profile.getMiddleName()) && lname != profile.getLastName()){
            ContentValues cvalues = new ContentValues();
            cvalues.put("lastname", profile.getLastName());
            cvalues.put("name", profile.getFirstName() + " " + profile.getMiddleName());
            id = id + 1;
            cvalues.put("id_cab",id);
            bd.insert("MasMinCab",null,cvalues);
            extId = true;
        }
        if(!extId){
            try{
                cursor.moveToPrevious();
                id = cursor.getInt(cursor.getColumnIndex("id_cab"));
            }catch (Exception e){}
        }

        Intent intent = new Intent(this,Juego.class);
        intent.putExtra("idPerfil",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
