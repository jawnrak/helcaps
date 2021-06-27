public class ProductPrijzen {
    private Double inkoopprijs;
    private Double verkoopprijs;

    public ProductPrijzen(Double inkoopprijs, Double verkoopprijs) {
        this.inkoopprijs = inkoopprijs;
        this.verkoopprijs = verkoopprijs;
    }

    public Double getInkoopprijs() {
        return inkoopprijs;
    }

    public Double getVerkoopprijs() {
        return verkoopprijs;
    }
}
