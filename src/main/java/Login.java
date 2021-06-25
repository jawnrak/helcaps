import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private ArrayList<Werknemer> werknemers;
    private static Login singleton;
    private Werknemer loggedInUser;
    private Bedrijf bedrijf;

    private Login (){
        werknemers = new ArrayList<>();
        werknemers.add(new Magazijnmedewerker("Magazijnmedewerker", "ToegangInventaris69"));
        werknemers.add(new Administrator("Administrator", "GeheimWachtwoord22"));
        werknemers.add(new Boekhouder("BoekhouderWerker", "WachtwoordBoekhouder44"));
        loggedInUser = null;
        this.bedrijf = new Bedrijf();
    }

    private ArrayList<Werknemer> getWerknemer () {
        return werknemers;
    }

    public static Login getInstance(){

        if (singleton == null){
            singleton = new Login();
        }
        return singleton;
    }


    private boolean userExists (String inlognaam) {

        for (Werknemer werknemer : werknemers) {
            if (werknemer.getInlognaam ().equals (inlognaam)) {
                this.loggedInUser = werknemer;
                return true;
            }
        }

        return false;
    }

    public boolean isAuthenticated () {
        if (loggedInUser != null) {
            return true;
        } else {
            Scanner s = new Scanner(System.in);

            System.out.println("Gebruikersnaam: ");
            String gebruikersnaam = s.nextLine();
            System.out.println("Wachtwoord: ");
            String wachtwoord = s.nextLine();
            if (userExists(gebruikersnaam) && loggedInUser.isPasswordCorrect(wachtwoord)) {
                System.out.println("Inloggen gelukt");
                return true;
            }

            System.out.println("De combinatie van gebruikersnaam en password is niet OK.");
            return false;

        }
    }

    public Werknemer getLoggedInUser() {
        return loggedInUser;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }
}

