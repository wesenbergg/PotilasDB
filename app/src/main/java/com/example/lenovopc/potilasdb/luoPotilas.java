package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class luoPotilas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_potilas);




        Button luoBtn = (Button) findViewById(R.id.luoBtn);
        luoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //luo uusi potilasolio tässä
                // esim. PotilasLista.getInstance().lisaaPotilas( new PotilasOlio("Esi","Merkki", 2018, "Esim."));
                EditText etuNim = (EditText) findViewById(R.id.potilasEtunimi);
                String etuNimi = etuNim.getText().toString();

                EditText sukuNim = (EditText) findViewById(R.id.potilasSukunimi);
                String sukuNimi = sukuNim.getText().toString();

                EditText diagnoos = (EditText) findViewById(R.id.potilasDiagnoosi);
                String diagnoosi = diagnoos.getText().toString();

                PotilasLista.getInstance().lisaaPotilas(new PotilasOlio(etuNimi, sukuNimi, diagnoosi));

                Intent intentLuo = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLuo);
            }
        });
    }
}
