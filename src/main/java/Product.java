import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private String naam;
    private String merk;
    private String kleur;
    private String productcode;
    private Integer aantal; //kan wijzigen hoeveel
    private Integer aantalVerkocht = 0;
    private Integer jaar;
    private Integer maand;
    private Double inkoopprijs;
    private Double verkoopprijs;


    public Product(String naam, String merk, String kleur, String productcode, Integer aantal, Double inkoopprijs, Double verkoopprijs) {
        this.naam = naam;
        this.merk = merk;
        this.kleur = kleur;
        this.productcode = productcode;
        this.aantal = aantal;
        this.jaar = LocalDateTime.now().getYear();
        this.maand = LocalDateTime.now().getMonth().getValue();
        this.inkoopprijs = inkoopprijs;
        this.verkoopprijs = verkoopprijs;
    }


    @Override
    public String toString() {
        return naam + " | " + merk + " | " + kleur + " | " + productcode + "\naantal: " + aantal + "\nlocatie: ";
    }

    public Integer getAantal() {
        return aantal;
    }

    public Integer getAantalVerkocht() {
        return aantalVerkocht;
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

    public String getProductcode() {
        return productcode;
    }

    public Integer getJaar() {
        return jaar;
    }

    public Integer getMaand() {
        return maand;
    }

    public Double getInkoopprijs() {
        return inkoopprijs;
    }

    public Double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setDate(LocalDate date){
        this.jaar = date.getYear();
        this.maand = date.getMonth().getValue();
    }

    public void EentjeVerkocht(){
        aantalVerkocht++;
        aantal--;
    }
}
