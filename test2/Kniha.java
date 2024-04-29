package projekt;

public class Kniha {
    private String nazev;
    private String[] autori;
    private int rok;
    private boolean dostupnost;

    public Kniha(String nazev, String[] autori, int rok, boolean dostupnost) {
        this.nazev = nazev;
        this.autori = autori;
        this.rok = rok;
        this.dostupnost = dostupnost;
    }

    // Getters and setters for all fields
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String[] getAutori() {
        return autori;
    }

    public void setAutori(String[] autori) {
        this.autori = autori;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }
}