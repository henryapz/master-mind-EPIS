package com.example.henry.facebook_prueba01;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Intent svc=new Intent(this, BackgroundService.class);
        startService(svc);
        if(AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }
        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            String name = profile.getFirstName() + " " + profile.getMiddleName();
            String lastName = profile.getLastName();

            TextView textView = (TextView) findViewById(R.id.id_usuario);

            textView.setText(name + "\n" + lastName);

        }else{
            ProfileTracker profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    stopTracking();
                    Profile.setCurrentProfile(currentProfile);
                }
            };
            profileTracker.startTracking();
        }
        Button button = (Button) findViewById(R.id.id_butPlay);
        final MainActivity m = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(m,Registro.class);
                startActivity(intent);
            }
        });
    }

    public void goLoginScreen() {
        Intent intent = new Intent (MainActivity.this, Login_FB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void goLoginScreen(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}
