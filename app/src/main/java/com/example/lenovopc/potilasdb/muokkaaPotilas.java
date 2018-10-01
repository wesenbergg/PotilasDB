package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class muokkaaPotilas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muokkaa_potilas);

        Button paivitaBtn = (Button) findViewById(R.id.paivitaBtn);
        paivitaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFrontPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentFrontPage);
            }
        });
    }
}
