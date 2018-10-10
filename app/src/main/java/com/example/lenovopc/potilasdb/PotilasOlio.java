package com.example.lenovopc.potilasdb;

import com.google.firebase.firestore.Exclude;

/**
 * Potilas oliona
 */
public class PotilasOlio {
    private String id;
    private String etuNimi;
    private String sukuNimi;
    private String Diagnoosi;
    private String sukupuoli;
    private int ika;

    public PotilasOlio(){
        //Tyhjä konstruktori firebase tsydeemille
    }

    //Konstruktori
    public PotilasOlio(String etuNimi,String sukuNimi, String diagnoosi, String Sukupuoli, int ika) {
        this.etuNimi = etuNimi;
        this.sukuNimi = sukuNimi;
        this.Diagnoosi = diagnoosi;
        this.sukupuoli = Sukupuoli;
        this.ika = ika;
    }

    //Setterit alkavat
    public void setId(String id) {
        this.id = id;
    }

    public void setEtuNimi(String etuNimi) {
        this.etuNimi = etuNimi;
    }

    public void setSukuNimi(String sukuNimi) {
        this.sukuNimi = sukuNimi;
    }

    public void setDiagnoosi(String diagnoosi) {
        Diagnoosi = diagnoosi;
    }
    //Setterit loppuvat

    //Getterit alkavat
    @Exclude
    public String getId() {
        return id;
    }

    public String getEtuNimi() {
        return etuNimi;
    }

    public String getSukuNimi() {
        return sukuNimi;
    }

    public String getDiagnoosi() {
        return Diagnoosi;
    }

    public int getIka() {
        return ika;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    @Override
    public String toString() {
        return this.sukuNimi + ", " + this.etuNimi;
    }
    //Getterit loppuvat
}
