package com.example.henry.facebook_prueba01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Juego extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView textView = (TextView) findViewById(R.id.id_title);
        final Button jugar = (Button)findViewById(R.id.but_jugar);
        Button instrucciones = (Button)findViewById(R.id.but_inst);
 

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Juego.this, MenteMaestra.class);
                Bundle bundle = getIntent().getExtras();
                int id = bundle.getInt("idPerfil");
                intent.putExtra("idPerfil",id);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_juego,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int resurceId = item.getItemId();
        if(resurceId == R.id.id_ranking){
            Intent intent = new Intent(Juego.this,Ranking.class);
            Bundle bundle = getIntent().getExtras();
            int id = bundle.getInt("idPerfil");
            intent.putExtra("idPerfil",id);
            startActivity(intent);

        }else if(resurceId == R.id.id_logout){
            Intent intent = new Intent (Juego.this, Login_FB.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
