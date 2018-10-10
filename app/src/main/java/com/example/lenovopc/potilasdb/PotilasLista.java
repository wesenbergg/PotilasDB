package com.example.lenovopc.potilasdb;

import java.util.ArrayList;

/**
 * PotilasLista on Singleton luokka jossa on ArrayList<PotilasOlio>. Tämä lista näytetään MainActivityssa.
 */
public class PotilasLista {
    private ArrayList<PotilasOlio> lista = new ArrayList<>();
    private static final PotilasLista ourInstance = new PotilasLista();

    private PotilasLista() {
        //Tyhjä konstruktori metodi
    }

    //Getterit alkavat
    public static PotilasLista getInstance() {
        return ourInstance;
    }

    public ArrayList getPotilaat() {
        return lista;
    }

    public PotilasOlio haePotilasOlio(int i) { return lista.get(i); }
    //Getterit loppuvat

    //Setterit alkavat
    public void lisaaPotilas(PotilasOlio potilas) {
        lista.add(potilas);
    }
    //Setterit loppuvat

    //Metodi, joka tyhjentää listan
    public void clear() {
        lista.clear();
    }

}
