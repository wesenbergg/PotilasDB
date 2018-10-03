package com.example.lenovopc.potilasdb;

public class PotilasOlio {
    private String etuNimi;
    private String sukuNimi;
    private String Diagnoosi;

    public PotilasOlio(String etuNimi,String sukuNimi, String diagnoosi) {
        this.etuNimi = etuNimi;
        this.sukuNimi = sukuNimi;
        this.Diagnoosi = diagnoosi;
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

    public String getEtuNimi() {
        return etuNimi;
    }

    public String getSukuNimi() {
        return sukuNimi;
    }

    public String getDiagnoosi() {
        return Diagnoosi;
    }

    @Override
    public String toString() {
        return this.sukuNimi + ", " + this.etuNimi;
    }
}
