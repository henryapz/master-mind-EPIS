package com.example.henry.facebook_prueba01;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

import java.util.*;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_colores extends Fragment {


    public Frag_colores() {
        // Required empty public constructor
    }

    private int red = -3407872;
    private int green = -6697984;
    private int yellow = -17613;
    private int blue =-16737844;
    private int skyblue = -16720385;
    private int pink = -49023;
    private int darkblue = -13615201;
    private int purple = -5609780;

    ArrayList<Integer> guess = new ArrayList<>();
    ArrayList<Integer> guessColors = new ArrayList<>();
    int[] secretColors;

    Button rojo;
    Button verde;
    Button amarillo;
    Button azul;
    Button celeste;
    Button rosado;
    Button cafe;
    Button morado;
    int numBut = 1;
    boolean deft = true;
    ColorStateList seleccionado;
    Button ok;
    int puntaje;
    int hits;
    int pseudoHits;
    int[] esferas = new int[4];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.frag_colores, container, false);

        secretColors = generateSecretColors();

        rojo = (Button) view.findViewById(R.id.but_rojo);
        verde = (Button) view.findViewById(R.id.but_verde);
        amarillo = (Button) view.findViewById(R.id.but_amarillo);
        azul = (Button) view.findViewById(R.id.but_azul);
        celeste = (Button) view.findViewById(R.id.but_celeste);
        rosado = (Button) view.findViewById(R.id.but_rosado);
        cafe = (Button) view.findViewById(R.id.but_cafe);
        morado = (Button) view.findViewById(R.id.but_morado);

        ok = (Button)view.findViewById(R.id.id_ok);

        seleccionado = rojo.getBackgroundTintList();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!deft) {
                    if (numBut <= 28) {
                        try {
                            guessColors.add(guess.get(guess.size() - 1));
                            guess.clear();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "No previous", Toast.LENGTH_LONG).show();
                        }
                        if ((numBut % 4) == 0 && guessColors.size() == 4) {
                            hits = contandoHits(secretColors, guessColors);
                            Toast.makeText(getActivity(), "Aciertos: " + hits, Toast.LENGTH_LONG).show();

                            pseudoHits = contandoPseudoHits(secretColors, guessColors);
                            Toast.makeText(getActivity(), "PsuedoAciertos: " + pseudoHits, Toast.LENGTH_LONG).show();

                            if (hits == 4) {
                                Toast.makeText(getActivity(), "GANASTE!!!", Toast.LENGTH_LONG).show();
                                calcPunt();
                                Toast.makeText(getActivity(), puntaje, Toast.LENGTH_LONG).show();
                                savePuntaje();
                                finish();
                            }

                            String s = "";
                            String s1 = "";
                            for (int i = 0; i < 4; i++) {
                                s += secretColors[i] + "\n";
                                s1 += guessColors.get(i) + "\n";
                            }
                            s += "\n" + s1;
                            //Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                            guessColors.clear();
                            starEsferas();
                            fillHitsAndPsuHit();
                        }


                        numBut++;
                        deft = true;
                        //TextView textView = (TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_hits);
                        //textView.setText("Hits: \n " + numBut);
                    } else {
                        if (hits != 4) {
                            Toast.makeText(getActivity(), "GAME OVER. PERDISTE!!!", Toast.LENGTH_LONG).show();
                            puntaje = 100;
                            savePuntaje();
                            finish();
                        }
                    }
                }
            }
        });

        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, rojo.getBackgroundTintList());
                deft = false;
                guess.add(rojo.getBackgroundTintList().getDefaultColor());
            }
        });

        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, verde.getBackgroundTintList());
                deft = false;
                guess.add(verde.getBackgroundTintList().getDefaultColor());
            }
        });
        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, amarillo.getBackgroundTintList());
                deft = false;
                guess.add(amarillo.getBackgroundTintList().getDefaultColor());
            }
        });
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, azul.getBackgroundTintList());
                deft = false;
                guess.add(azul.getBackgroundTintList().getDefaultColor());
            }
        });
        celeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, celeste.getBackgroundTintList());
                deft = false;
                guess.add(celeste.getBackgroundTintList().getDefaultColor());
            }
        });
        rosado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, rosado.getBackgroundTintList());
                deft = false;
                guess.add(rosado.getBackgroundTintList().getDefaultColor());
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, cafe.getBackgroundTintList());
                deft = false;
                guess.add(cafe.getBackgroundTintList().getDefaultColor());
            }
        });
        morado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(numBut, morado.getBackgroundTintList());
                deft = false;
                guess.add(morado.getBackgroundTintList().getDefaultColor());
            }
        });

        final Button rest = (Button) view.findViewById(R.id.id_rest);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess.clear();
                guessColors.clear();
                secretColors = generateSecretColors();
                starEsferas();
                ColorStateList col = rest.getBackgroundTintList();
                for (int i = 1; i <= 28; i++)
                    changeColor(i,col);

                for (int i = 1; i <= 8; i++){
                    chanHit(i," ");
                    chanPsuHit(i," ");
                }

                numBut = 1;
                seleccionado = rojo.getBackgroundTintList();
                deft = true;
                ok.setEnabled(true);
                rojo.setEnabled(true);
                verde.setEnabled(true);
                amarillo.setEnabled(true);
                azul.setEnabled(true);
                celeste.setEnabled(true);
                rosado.setEnabled(true);
                cafe.setEnabled(true);
                morado.setEnabled(true);
            }
        });


        return view;
    }

    private void fillHitsAndPsuHit() {
        int raw = numBut/4;
        chanHit(raw,String.valueOf(hits));
        chanPsuHit(raw,String.valueOf(pseudoHits));
    }

    private void savePuntaje() {
        SQLiteDatabase bd = getActivity().openOrCreateDatabase("mastermind2",MODE_PRIVATE,null);
        Cursor cursor = bd.rawQuery("SELECT * FROM MasMinDet",null);
        cursor.moveToLast();
        int idDet = 0;
        try{
            idDet = cursor.getInt(cursor.getColumnIndex("id_det"));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Primer Juego :3", Toast.LENGTH_SHORT).show();
        }

        ContentValues cvalues = new ContentValues();
        cvalues.put("id_det",idDet + 1);
        cvalues.put("puntaje",puntaje);
        cvalues.put("id_cab",MenteMaestra.id);
        bd.insert("MasMinDet",null,cvalues);
        puntaje=0;
    }

    public void finish(){
        ok.setEnabled(false);
        rojo.setEnabled(false);
        verde.setEnabled(false);
        amarillo.setEnabled(false);
        azul.setEnabled(false);
        celeste.setEnabled(false);
        rosado.setEnabled(false);
        cafe.setEnabled(false);
        morado.setEnabled(false);
    }
    public void chanHit(int raw, String hit){
        TextView textView;
        switch (raw){
            case 1: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw1); textView.setText(hit);break;
            case 2: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw2); textView.setText(hit);break;
            case 3: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw3); textView.setText(hit);break;
            case 4: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw4); textView.setText(hit);break;
            case 5: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw5); textView.setText(hit);break;
            case 6: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw6); textView.setText(hit);break;
            case 7: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw7); textView.setText(hit);break;
        }
        //TextView textView = (TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_hits);
        //textView.setText("Hits: \n " + numBut);
    }

    public void chanPsuHit(int raw, String psHit){
        TextView textView;
        switch (raw){
            case 1: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw1b); textView.setText(psHit);break;
            case 2: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw2b); textView.setText(psHit);break;
            case 3: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw3b); textView.setText(psHit);break;
            case 4: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw4b); textView.setText(psHit);break;
            case 5: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw5b); textView.setText(psHit);break;
            case 6: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw6b); textView.setText(psHit);break;
            case 7: textView =(TextView)((MenteMaestra)getActivity()).findViewById(R.id.id_raw7b); textView.setText(psHit);break;
        }
    }
    public void changeColor(int numBut, ColorStateList co){
        FragmentActivity view = getActivity();
        Button button = null;
        switch (numBut){
            case 1: button = (Button) view.findViewById(R.id.but_1); break;
            case 2: button = (Button) view.findViewById(R.id.but_2); break;
            case 3: button = (Button) view.findViewById(R.id.but_3); break;
            case 4: button = (Button) view.findViewById(R.id.but_4); break;
            case 5: button = (Button) view.findViewById(R.id.but_5); break;
            case 6: button = (Button) view.findViewById(R.id.but_6); break;
            case 7: button = (Button) view.findViewById(R.id.but_7); break;
            case 8: button = (Button) view.findViewById(R.id.but_8); break;
            case 9: button = (Button) view.findViewById(R.id.but_9); break;
            case 10: button = (Button) view.findViewById(R.id.but_10); break;
            case 11: button = (Button) view.findViewById(R.id.but_11); break;
            case 12: button = (Button) view.findViewById(R.id.but_12); break;
            case 13: button = (Button) view.findViewById(R.id.but_13); break;
            case 14: button = (Button) view.findViewById(R.id.but_14); break;
            case 15: button = (Button) view.findViewById(R.id.but_15); break;
            case 16: button = (Button) view.findViewById(R.id.but_16); break;
            case 17: button = (Button) view.findViewById(R.id.but_17); break;
            case 18: button = (Button) view.findViewById(R.id.but_18); break;
            case 19: button = (Button) view.findViewById(R.id.but_19); break;
            case 20: button = (Button) view.findViewById(R.id.but_20); break;
            case 21: button = (Button) view.findViewById(R.id.but_21); break;
            case 22: button = (Button) view.findViewById(R.id.but_22); break;
            case 23: button = (Button) view.findViewById(R.id.but_23); break;
            case 24: button = (Button) view.findViewById(R.id.but_24); break;
            case 25: button = (Button) view.findViewById(R.id.but_25); break;
            case 26: button = (Button) view.findViewById(R.id.but_26); break;
            case 27: button = (Button) view.findViewById(R.id.but_27); break;
            case 28: button = (Button) view.findViewById(R.id.but_28); break;
            default: break;
        }
        assert button != null;
        button.setBackgroundTintList(co);
    }

    public int[] generateSecretColors(){
        int[] colors = new int[4];
        List<Integer> c = new ArrayList<Integer>();
        c.add(red);
        c.add(green);
        c.add(yellow);
        c.add(blue);
        c.add(skyblue);
        c.add(pink);
        c.add(darkblue);
        c.add(purple);

        for(int i = 0; i < 4; i++){
            int idx = (int)(Math.random() * (8 - i));
            colors[i] = c.remove(idx);
        }
        return colors;
    }

    public int contandoHits (int[] secret, ArrayList<Integer> guess) {
        int count = 0;
        for (int i = 0; i < 4; i++){
            if (secret[i] == guess.get(i)){
                esferas[i] = 1;
                count++;
            }
        }
        return count;
    }

    public int contandoPseudoHits (int[] secret, ArrayList<Integer> guess) {
        int count = 0;
        for (int i = 0; i < 4; i++){
            if (esferas[i] != 1){
                for (int j = 0; j < 4; j++){
                    if (esferas[j] != 1){
                        if (guess.get(i) == secret[j])
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public void starEsferas(){
        for (int i = 0; i < 4; i++){
            esferas[i] = 0;
        }
    }

    public void calcPunt(){
        switch (numBut){
            case 4: puntaje=10000;break;
            case 8: puntaje=8000;break;
            case 12: puntaje=7500;break;
            case 16: puntaje=6000;break;
            case 20: puntaje=4500;break;
            case 24: puntaje=3000;break;
            case 28: puntaje=2000;break;
            case 32: puntaje=1000;break;
        }
    }

}