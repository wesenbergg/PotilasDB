package com.example.lenovopc.potilasdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Voit muokata potilaan tietoja, painamalla päivitä potilaan tiedot muutetaan ja palaat MainActivityyn
 */
public class muokkaaPotilas extends AppCompatActivity {


    //Collection referenssi
    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muokkaa_potilas);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        final int index = b.getInt("potilasIndex", -1);

        RadioButton male = findViewById(R.id.muokkaaMale);
        RadioButton female = findViewById(R.id.muokkaaFemale);
        final EditText muokkaaNimi = findViewById(R.id.muokkaaNimi);
        final EditText muokkaaSukunimi = findViewById(R.id.muokkaaSukunimi);
        final EditText muokkaaDiagnoosi = findViewById(R.id.muokkaaDiagnoosi);
        final EditText muokkaaIka = findViewById(R.id.muokkaaIka);

        final PotilasOlio potilas = PotilasLista.getInstance().haePotilasOlio(index);

        //Lisää tekstikenttiin muokattavat arvot
        muokkaaNimi.setText(potilas.getEtuNimi());
        muokkaaSukunimi.setText(potilas.getSukuNimi());
        muokkaaDiagnoosi.setText(potilas.getDiagnoosi());
        muokkaaIka.setText(String.format("%d",potilas.getIka()));
        if (potilas.getSukupuoli().equals("Mies")) {
            male.setChecked(true);
        } else if (potilas.getSukupuoli().equals("Nainen")) {
            female.setChecked(true);
        }



        //Etsi Button. Suorita koodi alla oleva koodi Button painetaan.
        Button paivitaBtn = (Button) findViewById(R.id.paivitaBtn);
        paivitaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hae potilaan ID
                String potilasId = potilas.getId();
                String sukupuoli = maleOrFemale();

                //muokkaa haetun potilaan tietoja
                colRef.document(potilasId).update("sukupuoli", sukupuoli);
                colRef.document(potilasId).update("etuNimi", muokkaaNimi.getText().toString());
                colRef.document(potilasId).update("sukuNimi", muokkaaSukunimi.getText().toString());
                colRef.document(potilasId).update("diagnoosi", muokkaaDiagnoosi.getText().toString());
                colRef.document(potilasId).update("ika", Integer.parseInt(muokkaaIka.getText().toString()));


                //siirry mainactivityyn
                Intent intentFrontPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentFrontPage);
            }
        });
    }

    /**
     * Tarkistaa RadioGroupista kumpi nappi on valittu, jos kumpikaan ei ole Sukupuoli on Muu.
     * @return
     */
    public String maleOrFemale() {
        RadioButton male = findViewById(R.id.muokkaaMale);
        RadioButton female = findViewById(R.id.muokkaaFemale);
        String palautus = "Muu";
        if(male.isChecked()) {
            return "Mies";
        } else if(female.isChecked()) {
            return "Nainen";
        }
        return palautus;

    }
}