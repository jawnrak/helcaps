import java.util.ArrayList;

public class Bedrijf {
    private ArrayList<Opslaglocatie> opslaglocaties;

    public Bedrijf() {
        this.opslaglocaties = new ArrayList<>();
        opslaglocaties.add(new Opslaglocatie("Zolder"));
        opslaglocaties.add(new Opslaglocatie("Woonkamer"));
        opslaglocaties.add(new Opslaglocatie("Slaapkamer"));
    }

    public ArrayList<Opslaglocatie> getOpslaglocaties() {
        return opslaglocaties;
    }

    public Opslaglocatie getFromLocatie(String naam){
        for (Opslaglocatie opslaglocatie : opslaglocaties) {
            if (opslaglocatie.getLocatie().toLowerCase().equals(naam.toLowerCase())){
                return opslaglocatie;
            }
        }
        return null;
    }

//    public boolean toegangOpslaglocatie(String inlog, String wachtwo) {
//        for (Werknemer toe : Werknemer.accounts) {
//            if (inlog.equals(toe.getInlognaam()) && wachtwo.equals(toe.getWachtwoord())) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean toegangWinstVerlies (String inlog, String wachtwo){
//        for (Werknemer toe : Werknemer.accounts) {
//            if (inlog.equals(toe.getInlognaam()) && wachtwo.equals(toe.getWachtwoord())) {
//                if (inlog.equals("Administrator") || inlog.equals("BoekhouderWerker")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    public ArrayList<Werknemer> getAccounts() {
//        return Werknemer.accounts;
//    }


}
