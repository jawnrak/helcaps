import java.time.LocalDate;
import java.util.ArrayList;

public class OpslagManager {
    private Opslaglocatie opslaglocatie;

    public OpslagManager(Opslaglocatie opslaglocatie) {
        this.opslaglocatie = opslaglocatie;
    }

    public Product getProductBijCode(String productcode, ArrayList<Product> lijst){
        for (Product product : lijst) {
            if (product.getProductcode().toString().equals(productcode)){
                return product;
            }
        }
        return null;
    }

    public void verkoop(String productZoek, LocalDate date){
        Product p = getProductBijCode(productZoek, opslaglocatie.getOpslag());
        if (p == null){
            System.out.println("Product niet gevonden");
            return;
        }
        p.setDate(date);
        p.EentjeVerkocht();

        if (p.getAantal()== 0) {
            opslaglocatie.getOpslag().remove(p);
        }

        for(Product pr : opslaglocatie.getVerkocht()){
            if(pr.getProductcode().toString().equals(productZoek)){
                return;
            }
        }
        opslaglocatie.getVerkocht().add(p);
    }
}
