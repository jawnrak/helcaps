import java.util.ArrayList;
import java.util.Collections;

public class Boekhouder extends Werknemer {

    public Boekhouder(String inlognaam, String wachtwoord) {
        super(inlognaam, wachtwoord);
    }

    @Override
    public ArrayList<String> printExtraOpties() {
        ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result,
                "Winst-verlies schema per maand",
                "Winst-verlies schema per jaar"
        );
        return result;
    }
}
