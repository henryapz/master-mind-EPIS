package com.example.henry.facebook_prueba01;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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


        instrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Juego.this);
                String inst = "El juego consiste en adivinar la secuencia correcta de 4 colores secretos. " +
                        "Esta secuencia de colores secretos no contiene colores repetidos.\n" +
                        "Tienes 7 intentos para adivinar la secuencia secreta.\n" +
                        "Si adivinas la secuencia secreta en la menor cantidad de intentos, tu puntaje será mayor.\n" +
                        "Los Hits te indican si adivinaste la posición correcta de un color.\n" +
                        "Los PseudoHits te indican si adivinaste un color, pero que está en la posición incorrecta.\n";
                builder.setMessage(inst)
                        .setTitle("INSTRUCCIONES:")
                        .setCancelable(false)
                        .setNeutralButton("Aceptar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
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
