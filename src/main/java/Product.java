import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private ProductKenmerken kenmerken;
    private static Integer lastProductcode = 10000;
    private Integer productcode;
    private Integer aantal; //kan wijzigen hoeveel
    private Integer aantalVerkocht = 0;
    private ProductPrijzen prijzen;
    private Integer jaar;
    private Integer maand;


    public Product(ProductKenmerken kenmerken, Integer aantal, ProductPrijzen prijzen) {
        this.kenmerken=kenmerken;
        this.productcode = lastProductcode++;
        this.aantal = aantal;
        this.jaar = LocalDateTime.now().getYear();
        this.maand = LocalDateTime.now().getMonth().getValue();
        this.prijzen=prijzen;
    }


    @Override
    public String toString() {
        return kenmerken.toString() + " | " + productcode + "\naantal: " + aantal + "\nlocatie: ";
    }

    public Integer getAantal() {
        return aantal;
    }

    public Integer getAantalVerkocht() {
        return aantalVerkocht;
    }

    public ProductKenmerken getKenmerken() { return kenmerken; }

    public Integer getProductcode() {
        return productcode;
    }

    public Integer getJaar() {
        return jaar;
    }

    public Integer getMaand() {
        return maand;
    }

    public ProductPrijzen getPrijzen() { return prijzen; }

    public void setDate(LocalDate date){
        this.jaar = date.getYear();
        this.maand = date.getMonth().getValue();
    }

    public void EentjeVerkocht(){
        aantalVerkocht++;
        aantal--;
    }
}
