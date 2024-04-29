package projekt;

public class Roman extends Kniha {
    private String zanr;

    public Roman(String nazev, String[] autori, int rok, boolean dostupnost, String zanr) {
        super(nazev, autori, rok, dostupnost);
        this.zanr = zanr;
    }


    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }
}
