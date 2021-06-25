import java.util.ArrayList;

public class Magazijnmedewerker extends Werknemer {

    public Magazijnmedewerker(String inlognaam, String wachtwoord) {
        super(inlognaam, wachtwoord);
    }


    @Override
    public ArrayList<String> printExtraOpties() {
        return new ArrayList<>();

    }
}
