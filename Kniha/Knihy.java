package Kniha;

public class Knihy {
    protected String titul;
    private String[] autor;
    protected int datumVydani;
    protected String zanr;
    private boolean  vypujcena;

    public Knihy(String titul, String[] autor, int datumVydani, String zanr, boolean vypujcena) {
        this.titul = titul;
        this.autor = autor;
        this.datumVydani = datumVydani;
        this.zanr = zanr;
        this.vypujcena = vypujcena;
    }    

    public String getTitul() {
        return titul;
    }

    public void setVypujcena(boolean vypujcena) {
        this.vypujcena = vypujcena;
    }

    public boolean isVypujcena() {
        return vypujcena;
    }

    public void add(Knihy book) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public String getZanr() {
        return zanr;
    }

    public int getdatumVydani() {
        return datumVydani;
    }

    public String[] getAutor() {
        return autor;
    }

    public void setTitul(String titul) {
    }

    public void setAutor(String[] autor) {
        this.autor = autor;
    }

    public void setdatumVydani(String newdatumVydani) {
        this.datumVydani = datumVydani;
    }

    public void setZanr(String newzanr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setzanr'");
    }

    
}
