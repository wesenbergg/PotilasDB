package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class luoPotilas extends AppCompatActivity {
    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_potilas);




        Button luoBtn = (Button) findViewById(R.id.luoBtn);
        luoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etuNim = (EditText) findViewById(R.id.potilasEtunimi);
                String etuNimi = etuNim.getText().toString();
                EditText sukuNim = (EditText) findViewById(R.id.potilasSukunimi);
                String sukuNimi = sukuNim.getText().toString();
                EditText diagnoos = (EditText) findViewById(R.id.potilasDiagnoosi);
                String diagnoosi = diagnoos.getText().toString();

                //PotilasLista.getInstance().lisaaPotilas(new PotilasOlio(etuNimi, sukuNimi, diagnoosi));

                PotilasOlio asd = new PotilasOlio(etuNimi,sukuNimi,diagnoosi);
                final String id = asd.getId();
                colRef.add(asd);


                Intent intentLuo = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLuo);
            }
        });
    }
}
