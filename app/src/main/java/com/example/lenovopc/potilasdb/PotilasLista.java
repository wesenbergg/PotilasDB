package com.example.lenovopc.potilasdb;

import java.util.ArrayList;

public class PotilasLista {
    private ArrayList<PotilasOlio> lista = new ArrayList<>();
    private static final PotilasLista ourInstance = new PotilasLista();

    public static PotilasLista getInstance() {
        return ourInstance;
    }

    public ArrayList getPotilaat() {
        return lista;
    }

    public void lisaaPotilas(PotilasOlio potilas) {
        lista.add(potilas);
    }

    private PotilasLista() {
    }

    public PotilasOlio haePotilasOlio(int i) {
        return lista.get(i);
    }

    public void clear() {
        lista.clear();
    }


}
