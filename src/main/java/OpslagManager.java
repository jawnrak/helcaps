import java.time.LocalDate;
import java.util.ArrayList;

public class OpslagManager {
    private Opslaglocatie opslaglocatie;

    public OpslagManager(Opslaglocatie opslaglocatie) {
        this.opslaglocatie = opslaglocatie;
    }

    public Product getProductBijCode(String productcode, ArrayList<Product> lijst){
        for (Product product : lijst) {
            if (product.getProductcode().equals(productcode)){
                return product;
            }
        }
        return null;
    }

    public void verkoop(String productZoek, Double verkoopprijs, LocalDate date){
        Product p = getProductBijCode(productZoek, opslaglocatie.getOpslag());
        p.setDate(date);
        p.EentjeVerkocht();

        if (p.getAantal()== 0) {
            opslaglocatie.getOpslag().remove(p);
        }

        p.setVerkoopprijs(verkoopprijs);

        for(Product pr : opslaglocatie.getVerkocht()){
            if(pr.getProductcode().equals(productZoek)){
                return;
            }
        }
        opslaglocatie.getVerkocht().add(p);
    }
}
