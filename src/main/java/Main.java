import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bedrijf bedrijf = Login.getInstance().getBedrijf();
        bedrijf.getFromLocatie("Woonkamer").getOpslag().add(new Product(new ProductKenmerken("Air Jordan 1 mid black red", "Air Jordan", "Zwart rood"), 2, new ProductPrijzen(160.00, 200.00)));
        bedrijf.getFromLocatie("Woonkamer").getOpslag().add(new Product(new ProductKenmerken("Air Force 1 tripple black", "Nike", "Zwart"), 3, new ProductPrijzen(100.00, 125.00)));
        bedrijf.getFromLocatie("Zolder").getOpslag().add(new Product(new ProductKenmerken("Adidas boost 4.0 tripple white", "Adidas", "Wit"), 4, new ProductPrijzen(180.00, 225.00)));
        bedrijf.getFromLocatie("Slaapkamer").getOpslag().add(new Product(new ProductKenmerken("Yeezy Boost Beluga", "Yeezy", "Grijs"), 6, new ProductPrijzen(220.00, 250.00)));

        while(Login.getInstance().isAuthenticated()){
            loop(bedrijf);
        }
    }

    public static Integer printMenu(){
        ArrayList<String> menu = new ArrayList<>(Login.getInstance().getLoggedInUser().printMenu());

        System.out.println("Selecteer een optie?");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("%d) %s \n", i+1, menu.get(i));
        }

        System.out.print("Uw keuze: ");

        return sc.nextInt();
    }

    public static void loop(Bedrijf bedrijf){
        switch (Login.getInstance().getLoggedInUser().printMenu().get(printMenu() - 1)) {
            case "Voeg een product toe" : productToevoegen(bedrijf); break;
            case "Print inventaris van een locatie" : locatieInventaris(bedrijf); break;
            case "Winst-verlies schema per maand" : winstVerliesMaand(bedrijf); break;
            case "Winst-verlies schema per jaar" : winstVerliesJaar(bedrijf); break;
            case "Verkocht product" : verkochtProduct(bedrijf); break;
            case "Kenmerk opzoeken" : kenmerkOpzoeken(bedrijf); break;
            case "Aantal verkochtte producten in de maand" : aantalVerkochtteProducten(bedrijf); break;
        }

    }


    private static void productToevoegen(Bedrijf bedrijf) {
        System.out.print("Typ de naam van het product in: ");
        sc.nextLine();
        String naam = sc.nextLine();
        System.out.print("Typ het merk van het product in: ");
        String merk = sc.nextLine();
        System.out.print("Typ de kleur van het product in: ");
        String kleur = sc.nextLine();
        System.out.print("Voer de aantal in: ");
        Integer aantal = sc.nextInt();
        System.out.print("Wat is de inkoopprijs: ");
        Double inkoopprijs = sc.nextDouble();
        System.out.print("Wat is de verkoopprijs: ");
        Double verkoopprijs = sc.nextDouble();
        String locatie = locatieMenu();
        if(locatie != null) {
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            opslaglocatie.getOpslag().add(new Product(new ProductKenmerken(naam, merk, kleur), aantal, new ProductPrijzen(inkoopprijs, verkoopprijs)));
        }


    }
    private static void locatieInventaris(Bedrijf bedrijf) {
        String locatie = locatieMenu();
        if (locatie != null) {
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            for(Product pro : opslaglocatie.getOpslag()){
                if (pro != null) {
                    System.out.println(pro + opslaglocatie.getLocatie());
                }
            }
        }
    }

    private static void winstVerliesMaand(Bedrijf bedrijf){
        String locatie = locatieMenu();
        if (locatie != null) {
            int maand = getMaand();
            int jaar = getJaar();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totale winst van de maand %d-%d:  %.2f\n",maand, jaar, new OpslaglocatieCalculator(opslaglocatie).berekenWinstMaand(maand, jaar));
        }
    }

    private static void winstVerliesJaar(Bedrijf bedrijf) {
        String locatie = locatieMenu();
        if (locatie != null) {
            int jaar = getJaar();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totale winst van het jaar %d:  %.2f\n", jaar, new OpslaglocatieCalculator(opslaglocatie).berekenWinstJaar(jaar));
        }
    }

    private static void verkochtProduct(Bedrijf bedrijf){
        String locatie = locatieMenu();
        if (locatie != null) {
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            for(Product pro : opslaglocatie.getOpslag()){
                if (pro != null) {
                    System.out.println(pro.toString());
                }
            }
            sc.nextLine();
            System.out.println("Geef productcode op: ");
            String productZoek = sc.nextLine();
            int jaar = getJaar();
            int maand = getMaand();
            new OpslagManager(opslaglocatie).verkoop(productZoek, LocalDate.of(jaar, maand, 1));
            System.out.println("Succesvol verkocht");
        }
    }

    private static void kenmerkOpzoeken(Bedrijf bedrijf){
        System.out.println("Welk kenmerk wil je opzoeken?\n" +
                           "1) Naam\n" +
                           "2) Merk\n" +
                           "3) Kleur\n" +
                           "4) Productcode\n" +
                           "Invoer: ");
        int keuze = sc.nextInt();
        sc.nextLine();
        System.out.println("Waarde: ");
        String waarde = sc.nextLine();
        ArrayList <String> result = new ArrayList<>();
        for (Opslaglocatie oL : bedrijf.getOpslaglocaties()) {
            for (Product product : oL.getOpslag()) {
                switch ((keuze)){
                    case 1:
                        if (product.getKenmerken().getNaam().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        } break;
                    case 2:
                        if (product.getKenmerken().getMerk().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        } break;
                    case 3:
                        if (product.getKenmerken().getKleur().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        } break;
                    case 4:
                        if (product.getProductcode().toString().equals(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        } break;
                }
            }

        }

        for(String pro : result){
            System.out.println(pro);
        }


    }
    public static void aantalVerkochtteProducten (Bedrijf bedrijf){
        String locatie = locatieMenu();
        if (locatie != null) {
            int maand = getMaand();
            int jaar = getJaar();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totaal aantal verkochttte producten van de maand %d-%d:  %d\n",maand, jaar, new OpslaglocatieCalculator(opslaglocatie).berekenAantalVerkochtteProductenMaand(maand, jaar));
        }
    }

    private static String locatieMenu() {
        System.out.print("Kies op welk locatie het product is: \n" +
                         "1) Zolder \n" +
                         "2) Woonkamer \n" +
                         "3) Slaapkamer \n"); 
        int getal = sc.nextInt();
        return getal == 1 ? "zolder" : getal == 2 ? "woonkamer" : getal == 3 ? "slaapkamer" : null;
    }
    private static int getJaar() { System.out.println("Welk jaar is het: "); return sc.nextInt();}
    private static int getMaand() { System.out.println("Welke maand is het (nummer): "); return sc.nextInt();}
}