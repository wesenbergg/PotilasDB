package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainAcvtivity";
    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    protected void onResume() {
        super.onResume();

        colRef
                .get()
                .addOnSuccessListener(this, new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        PotilasLista.getInstance().getPotilaat().clear();
                        for(QueryDocumentSnapshot documentSnapShot : queryDocumentSnapshots) {
                            PotilasOlio potilas = documentSnapShot.toObject(PotilasOlio.class);
                            potilas.setId(documentSnapShot.getId());

                            String id = potilas.getId();
                            PotilasLista.getInstance().lisaaPotilas(potilas);

                            Log.i("ID_TAG", id);
                        }
                    }
                });

        ListView potilasLista = (ListView) findViewById(R.id.potilaslista);

        potilasLista.setAdapter(new ArrayAdapter<PotilasOlio>(MainActivity.this, R.layout.listanjasen, PotilasLista.getInstance().getPotilaat()));

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
