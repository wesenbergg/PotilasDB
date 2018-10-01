package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class potilasSingle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potilas_single);

        Intent intent1 = getIntent();
        int index = intent1.getIntExtra("ITEM_KEY", -1);

        if (index > -1) { setViewById(index); }

        FloatingActionButton fabMuokkaa = findViewById(R.id.fabMuokkaa);
        fabMuokkaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), muokkaaPotilas.class);
                startActivity(intent);
            }
        });
    }

    private void setViewById(int i){
        Resources res = getResources();

        String[] nimi = res.getStringArray(R.array.seed);

        TextView nimiTV = (TextView) findViewById(R.id.nimiTV);

        nimiTV.setText(nimi[i]);
    }
}
