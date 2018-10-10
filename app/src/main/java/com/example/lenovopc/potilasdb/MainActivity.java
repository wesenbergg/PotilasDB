package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Päänäkymä jossa lista potilaista. Potilaan nimen painaminen vie potilasSingle näkymään.
 Plus napin painaminen vie luoPotilas näkymään.
 *@author Jyry Soinio
 * @author Karoliina Kallio
 * @author Boriss Jerjomkin
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcvtivity";
    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listener Lisää-painikkeelle
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLuo = new Intent(getApplicationContext(), luoPotilas.class);
                startActivity(intentLuo);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Luo lista näkymän
        colRef.get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task ) {

                        //Tyhjentää listan
                        //Lisää listaan kaikki tietokannan documentit
                        //Muuttaa documentit Java-olioiksi ja lisää listalle
                        PotilasLista.getInstance().getPotilaat().clear();
                        for(QueryDocumentSnapshot documentSnapShot : task.getResult()) {
                            PotilasOlio potilas = documentSnapShot.toObject(PotilasOlio.class);
                            potilas.setId(documentSnapShot.getId());

                            String id = potilas.getId();
                            PotilasLista.getInstance().lisaaPotilas(potilas);

                            Log.i("ID_TAG", id);
                        }
                        //Täyttää ListView listan olioilla
                        ListView potilasLista = (ListView) findViewById(R.id.potilaslista);
                        potilasLista.setAdapter(new ArrayAdapter<PotilasOlio>(MainActivity.this,
                                R.layout.listanjasen,
                                PotilasLista.getInstance().getPotilaat()));
                    }
                });

        ListView potilasLista = (ListView) findViewById(R.id.potilaslista);
        //Näkymä yksittäisen olion käyttöliittymään
        potilasLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent newActivity = new Intent(MainActivity.this, potilasSingle.class);
                newActivity.putExtra("Indeksi", i);
                startActivity(newActivity);
            }
        });
    }
}
