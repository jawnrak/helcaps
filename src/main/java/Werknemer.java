import java.util.ArrayList;
import java.util.Collections;

abstract class Werknemer {
    protected String inlognaam;
    protected String wachtwoord;

    public Werknemer(String inlognaam, String wachtwoord) {
        this.inlognaam = inlognaam;
        this.wachtwoord = wachtwoord;
    }

    public String getInlognaam() { return this.inlognaam; }

    public boolean isPasswordCorrect (String wachtwoord) {
        return wachtwoord.equals (this.wachtwoord);
    }

    public ArrayList<String> printMenu(){
        ArrayList<String> result = new ArrayList<>();

        result.addAll(printBasisOpties());
        result.addAll(printExtraOpties());
        return result;
    }

    public ArrayList<String> printBasisOpties() {
        ArrayList<String> result = new ArrayList<>();

        Collections.addAll(result,
                "Voeg een product toe",
                "Print inventaris van een locatie",
                "Verkocht product",
                "Kenmerk opzoeken"
        );
        return result;
    }

    public abstract ArrayList<String> printExtraOpties();
}



