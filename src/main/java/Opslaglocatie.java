import java.util.ArrayList;

public class Opslaglocatie {
    private String locatie;
    private ArrayList<Product> opslag = new ArrayList<>();
    private ArrayList<Product> verkocht = new ArrayList<>();

    public ArrayList<Product> getOpslag() {
        return opslag;
    }
    public Opslaglocatie(String locatie) {
        this.locatie = locatie;
    }
    public String getLocatie(){return this.locatie;}
    public ArrayList<Product> getVerkocht() {
        return verkocht;
    }
}
