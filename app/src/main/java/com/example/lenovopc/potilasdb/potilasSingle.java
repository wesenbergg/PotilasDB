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

        //Vastaanottaa intentin
        //Tallenna intent välittämät tiedot muuttujaan
        //Hae indeksi key:llä. Jos ei löydy value, niin palauta -1
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final int index = b.getInt("Indeksi", -1);

        //Suorittaa setViewById, jos indeksi löytyy
        if (index > -1) { setViewById(index); }

        //Muokkaa painikkeelle Listener
        FloatingActionButton fabMuokkaa = findViewById(R.id.fabMuokkaa);
        fabMuokkaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent muokkaaActivity = new Intent(getApplicationContext(), muokkaaPotilas.class);
                muokkaaActivity.putExtra("potilasIndex", index);
                startActivity(muokkaaActivity);
            }
        });
    }

    //Metodia kutsutaan jos löytyy indeksi
    //Täyttää aktiviteetin potilas-olion tiedoilla
    private void setViewById(int i){
        String nimet = PotilasLista.getInstance().haePotilasOlio(i).toString();
        String diagnoosi = PotilasLista.getInstance().haePotilasOlio(i).getDiagnoosi();

        TextView nimiTV = (TextView) findViewById(R.id.nimiTV);
        TextView diagnoosiTV = (TextView) findViewById(R.id.diagnoosiTV);

        nimiTV.setText(nimet);
        diagnoosiTV.setText(diagnoosi);
    }
}
