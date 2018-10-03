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
        lista.add(new PotilasOlio("Stahlberg"," Kaarlo Juho", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Relander"," Lauri Kristian", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Svinhufvud"," Pehr, Evind", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Kallio"," Kyosti", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Ryti"," Risto Heikki", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Mannerheim"," Carl Gustav Emil", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Paasikivi"," Juho Kusti", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Kekkonen"," Urho Kaleva", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Koivisto"," Mauno Henrik", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Ahtisaari"," Martti Oiva Kalevi", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Halonen"," Tarja Kaarina", "Kuvittelee olevansa presidentti."));
        lista.add(new PotilasOlio("Niinistö"," Sauli Väinämö", "Kuvittelee olevansa presidentti."));
    }

    public PotilasOlio haePotilasOlio(int i) {
        return lista.get(i);
    }


}
