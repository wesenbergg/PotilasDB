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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Syöttämällä tietokenttiin potilaan tiedot ja painamalla luo nappia luot uuden potilaan ja
 * siirryt takaisin MainActivityyn
 */
public class luoPotilas extends AppCompatActivity {

    //Referenssi firebase-collectioniin
    private CollectionReference colRef = FirebaseFirestore.getInstance().collection("potilaat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_potilas);

        //Suorita tämä koodi kun Luo-button on painettu
        Button luoBtn = (Button) findViewById(R.id.luoBtn);
        luoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sex = maleOrFemale();

                //Etsi edit textit, lisää edit text data muuttujiin
                EditText etuNim = (EditText) findViewById(R.id.potilasEtunimi);
                EditText sukuNim = (EditText) findViewById(R.id.potilasSukunimi);
                EditText diagnoos = (EditText) findViewById(R.id.potilasDiagnoosi);
                EditText ika1 = (EditText) findViewById(R.id.potilasIka);

                String etuNimi = etuNim.getText().toString();
                String sukuNimi = sukuNim.getText().toString();
                String diagnoosi = diagnoos.getText().toString();

                int ika = -1;
                if (!ika1.getText().toString().equals("")){
                    ika = Integer.parseInt(ika1.getText().toString());
                }





                if (!etuNimi.equals("") && !sukuNimi.equals("") && !diagnoosi.equals("") && ika > -1) {
                    //Luo potilas-olio, lisää olio firebaseen
                    PotilasOlio potilas = new PotilasOlio(etuNimi,sukuNimi,diagnoosi, sex, ika);
                    colRef.add(potilas);

                    Toast.makeText(luoPotilas.this, "Potilas luotu", Toast.LENGTH_SHORT).show();

                    //Siirry MainActivityyn
                    Intent intentLuo = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentLuo);
                } else {
                    Toast.makeText(luoPotilas.this, "Jotain puuttuu", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Tarkistaa RadioGroupista kumpi nappi on valittu, jos kumpikaan ei ole Sukupuoli on Muu.
     * @return
     */
    public String maleOrFemale() {
        RadioButton male = findViewById(R.id.maleBtn);
        RadioButton female = findViewById(R.id.femaleBtn);
        String palautus = "Muu";
        if(male.isChecked()) {
            return "Mies";
        } else if(female.isChecked()) {
            return "Nainen";
        }
        return palautus;

    }
}
