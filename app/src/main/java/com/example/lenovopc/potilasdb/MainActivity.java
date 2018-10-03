package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainAcvtivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView potilasLista = (ListView) findViewById(R.id.potilaslista);

        potilasLista.setAdapter(new ArrayAdapter<PotilasOlio>(this,
                R.layout.listanjasen,
                PotilasLista.getInstance().getPotilaat()));

        potilasLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(MainActivity.this, potilasSingle.class);
                nextActivity.putExtra("potilasIndex", i);
                startActivity(nextActivity);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intentLuo = new Intent(getApplicationContext(), luoPotilas.class);
                startActivity(intentLuo);
            }
        });
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get resources
        Resources res = getResources();

        //Find components
        final ListView potilaslista = (ListView) findViewById(R.id.potilaslista);
        String[] seed = res.getStringArray(R.array.seed);

        //Set relative layout into parent layout
        potilaslista.setAdapter(new ArrayAdapter<String>(this, R.layout.listanjasen, seed));

        potilaslista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), potilasSingle.class);
                intent.putExtra("ITEM_KEY", position);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intentLuo = new Intent(getApplicationContext(), luoPotilas.class);
                startActivity(intentLuo);
            }
        });
    }
    */


}
