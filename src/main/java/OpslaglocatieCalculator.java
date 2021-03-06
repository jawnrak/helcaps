public class OpslaglocatieCalculator {
    private Opslaglocatie opslaglocatie;

    public OpslaglocatieCalculator(Opslaglocatie opslaglocatie) {
        this.opslaglocatie = opslaglocatie;
    }



    public Double berekenWinstMaand(int maand, int jaar){
        double result = 0D;

        for(Product p : opslaglocatie.getVerkocht()){
            if(p.getMaand()==maand && p.getJaar() == jaar){
                result += (p.getPrijzen().getVerkoopprijs() - p.getPrijzen().getInkoopprijs()) * p.getAantalVerkocht();
                //product verkoopt altijd voor zelfde prijs
            }
        }
        return result;
    }

    public Double berekenWinstJaar(int jaar){
        double result = 0D;
        for(Product p : opslaglocatie.getVerkocht()){
            if(p.getJaar()==jaar){
                result += (p.getPrijzen().getVerkoopprijs() - p.getPrijzen().getInkoopprijs()) * p.getAantalVerkocht();
            }
        }
        return result;
    }

    public Integer berekenAantalVerkochtteProductenMaand(int maand, int jaar){
        Integer result = 0;

        for(Product p : opslaglocatie.getVerkocht()){
            if(p.getMaand()==maand && p.getJaar() == jaar){
                result += p.getAantalVerkocht();
            }
        }
        return result;
    }
}
