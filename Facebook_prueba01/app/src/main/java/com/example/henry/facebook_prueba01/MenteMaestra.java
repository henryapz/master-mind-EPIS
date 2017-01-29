package com.example.henry.facebook_prueba01;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.Profile;

import static com.example.henry.facebook_prueba01.R.drawable.rojo;

public class MenteMaestra extends AppCompatActivity {
    Toolbar toolbar;
    static int id;;
    Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_mente_maestra);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("idPerfil");
        profile = Profile.getCurrentProfile();

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
            Intent intent = new Intent(MenteMaestra.this,Ranking.class);
            Bundle bundle = getIntent().getExtras();
            int id = bundle.getInt("idPerfil");
            intent.putExtra("idPerfil",id);
            startActivity(intent);

        }else if(resurceId == R.id.id_logout){
            Intent intent = new Intent (MenteMaestra.this, Login_FB.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
