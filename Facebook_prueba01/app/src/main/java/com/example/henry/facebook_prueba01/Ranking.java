package com.example.henry.facebook_prueba01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        SQLiteDatabase bd = openOrCreateDatabase("mastermind2",MODE_PRIVATE,null);

        Profile profile = Profile.getCurrentProfile();

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("idPerfil");
        /*
        Cursor cursor = bd.rawQuery("SELECT * FROM MasMinDet",null);
        cursor.moveToLast();
        int idDet = 1;
        try{
            idDet = cursor.getInt(cursor.getColumnIndex("id_det"));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Detalle no registrado", Toast.LENGTH_SHORT).show();
        }

        ContentValues cvalues = new ContentValues();
        cvalues.put("id_det",idDet + 1);
        cvalues.put("puntaje",1000);
        cvalues.put("id_cab",id);
        bd.insert("MasMinDet",null,cvalues);

        cvalues = new ContentValues();
        cvalues.put("id_det",idDet + 2);
        cvalues.put("puntaje",1300);
        cvalues.put("id_cab",id);
        bd.insert("MasMinDet",null,cvalues);
        */
        Cursor cursor2 = bd.rawQuery("SELECT * FROM MasMinDet ORDER BY puntaje DESC",null);
        TextView textView = (TextView) findViewById(R.id.id_rankingL);
        cursor2.moveToFirst();
        String datos ="";
        int c = 1;
        do {
            try {
                datos = datos + c + "- " + cursor2.getInt(cursor2.getColumnIndex("puntaje")) + " " + profile.getFirstName() + " " + profile.getMiddleName() + "\n";
                cursor2.moveToNext();
            }catch (Exception e){
                datos = datos + c + "- none\n";
            }
            c++;
        } while (c < 11);
        textView.setText(datos);

    }
}
