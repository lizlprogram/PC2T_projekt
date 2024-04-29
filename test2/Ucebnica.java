package projekt;

public class Ucebnica extends Kniha {
    private int rocnik;

    public Ucebnica(String nazev, String[] autori, int rok, boolean dostupnost, int rocnik) {
        super(nazev, autori, rok, dostupnost);
        this.rocnik = rocnik;
    }


    public int getRocnik() {
        return rocnik;
    }

    public void setRocnik(int rocnik) {
        this.rocnik = rocnik;
    }
}