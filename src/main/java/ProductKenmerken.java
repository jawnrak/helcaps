public class ProductKenmerken {
    private String naam;
    private String merk;
    private String kleur;

    public ProductKenmerken(String naam, String merk, String kleur) {
        this.naam = naam;
        this.merk = merk;
        this.kleur = kleur;
    }

    @Override
    public String toString() {
        return naam + " | " + merk + " | " + kleur;
    }

    public String getNaam() {
        return naam;
    }

    public String getMerk() {
        return merk;
    }

    public String getKleur() {
        return kleur;
    }
}
