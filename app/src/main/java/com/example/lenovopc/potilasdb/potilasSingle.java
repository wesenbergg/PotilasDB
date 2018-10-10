package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Näkymä joka aukeaa kun klikkaat potilaan nimeä MainActivityssa. Tässä näkyy potilaan tarkemmat tiedot.
 * Muokkausnapista pääse muokkausnäkymään ja roskisnapilla voi poistaa potilaan.
 */
public class potilasSingle extends AppCompatActivity {

    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potilas_single);

        //Vastaanottaa intentin
        //Tallenna intent välittämät tiedot muuttujaan
        //Hae indeksi key:llä. Jos ei löydy value, niin palauta -1
        final int index = getIndex();

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
        String sukupuoli = PotilasLista.getInstance().haePotilasOlio(i).getSukupuoli();
        int ika = PotilasLista.getInstance().haePotilasOlio(i).getIka();

        TextView nimiTV = (TextView) findViewById(R.id.nimiTV);
        TextView diagnoosiTV = (TextView) findViewById(R.id.diagnoosiTV);
        TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);

        nimiTV.setText(nimet);
        diagnoosiTV.setText(diagnoosi);
        descriptionTV.setText(sukupuoli + ", " + ika);
    }

    //Lisää delete-menu oikeeseen yläkulmaan
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    //Delete painikkeelle metodi
    public void deleteItem(MenuItem item) {
        //Etsii potilaan indexillä
        //Hakee Id tunnuksen
        //Poistaa firestore db:stä
        PotilasOlio potilas = PotilasLista.getInstance().haePotilasOlio(getIndex());
        String poistettavanId = potilas.getId();

        colRef.document(poistettavanId).delete();

        //siirry mainactivityyn
        Intent intentFrontPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentFrontPage);
    }

    private int getIndex(){
        Intent intentti = getIntent();
        Bundle b = intentti.getExtras();
        int index = b.getInt("Indeksi", -1);

        return index;
    }
}
