package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class muokkaaPotilas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muokkaa_potilas);
        final EditText muokkaaNimi = findViewById(R.id.muokkaaNimi);
        final EditText muokkaaSukunimi = findViewById(R.id.muokkaaSukunimi);
        final EditText muokkaaDiagnoosi = findViewById(R.id.muokkaaDiagnoosi);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final int index = b.getInt("potilasIndex", -1);

        final PotilasOlio potilas = PotilasLista.getInstance().haePotilasOlio(index);

        muokkaaNimi.setText(potilas.getEtuNimi());
        muokkaaSukunimi.setText(potilas.getSukuNimi());
        muokkaaDiagnoosi.setText(potilas.getDiagnoosi());

        Button paivitaBtn = (Button) findViewById(R.id.paivitaBtn);
        paivitaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                potilas.setEtuNimi(muokkaaNimi.getText().toString());
                potilas.setSukuNimi(muokkaaSukunimi.getText().toString());
                potilas.setDiagnoosi(muokkaaDiagnoosi.getText().toString());


                Intent intentFrontPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentFrontPage);
            }
        });
    }
}
