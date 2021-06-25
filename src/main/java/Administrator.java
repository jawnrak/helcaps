import java.util.ArrayList;
import java.util.Collections;

public class Administrator extends Werknemer {

    public Administrator(String inlognaam, String wachtwoord) {
        super(inlognaam, wachtwoord);
    }


    @Override
    public ArrayList<String> printExtraOpties() {
        ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result,
                "Winst-verlies schema per maand",
                "Winst-verlies schema per jaar",
                "Aantal verkochtte producten in de maand"
                );
        return result;
    }
}
