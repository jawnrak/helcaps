import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bedrijf bedrijf = Login.getInstance().getBedrijf();
        bedrijf.getFromLocatie("Woonkamer").getOpslag().add(new Product("Air jordan 1 mid black red", "Air Jordan", "Zwart rood", "55232", 2, 160.00));
        bedrijf.getFromLocatie("Woonkamer").getOpslag().add(new Product("Air Force 1 tripple black", "Nike", "Zwart", "98122", 3, 100.00));
        bedrijf.getFromLocatie("Zolder").getOpslag().add(new Product("Adidas boost 4.0 tripple white", "Adidas", "Wit", "44110", 4, 180.00));
        bedrijf.getFromLocatie("Slaapkamer").getOpslag().add(new Product("Yeezy Boost Beluga", "Yeezy", "Grijs", "94222", 6, 220.00));

        while(Login.getInstance().isAuthenticated()){
            loop(bedrijf);
        }
    }

    public static Integer printMenu(){
        ArrayList<String> menu = new ArrayList<>();
        menu.addAll(Login.getInstance().getLoggedInUser().printMenu());
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecteer een optie?");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("%d) %s \n", i+1, menu.get(i));
        }

        System.out.print("Uw keuze: ");

        return sc.nextInt();
    }

    public static void loop(Bedrijf bedrijf){
        switch (Login.getInstance().getLoggedInUser().printMenu().get(printMenu()-1)){
            case "Voeg een product toe" :
                productToevoegen(bedrijf);
                break;
            case "Print inventaris van een locatie" :
                locatieInventaris(bedrijf);
                break;
            case "Winst-verlies schema per maand" :
                winstVerliesMaand(bedrijf);
                break;
            case "Winst-verlies schema per jaar" :
                winstVerliesJaar(bedrijf);
                break;
            case "Verkocht product" :
                verkochtProduct(bedrijf);
                break;
            case "Kenmerk opzoeken" :
                kenmerkOpzoeken(bedrijf);
                break;
            case "Aantal verkochtte producten in de maand" :
                aantalVerkochtteProducten(bedrijf);
                break;
        };

    }


    private static void productToevoegen(Bedrijf bedrijf) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Typ de naam van het product in: ");
        String naam = sc.nextLine();
        System.out.print("Typ het merk van het product in: ");
        String merk = sc.nextLine();
        System.out.print("Typ de kleur van het product in: ");
        String kleur = sc.nextLine();
        System.out.print("Typ de productcode van het product in: ");
        String productcode = sc.nextLine();
        System.out.print("Voer de aantal in: ");
        Integer aantal = sc.nextInt();
        System.out.print("Wat is de inkoopprijs: ");
        Double inkoopprijs = sc.nextDouble();
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
         Integer getal = sc.nextInt();
         String locatie = getal==1? "Zolder": getal==2? "Woonkamer": getal==3? "Slaapkamer": null;
         if(locatie != null) {
             Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
             opslaglocatie.getOpslag().add(new Product(naam, merk, kleur, productcode, aantal, inkoopprijs));
         }


    }
    private static void locatieInventaris(Bedrijf bedrijf) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
        Integer getal = sc.nextInt();
        String locatie = getal == 1 ? "Zolder" : getal == 2 ? "Woonkamer" : getal == 3 ? "Slaapkamer" : null;
        if (locatie != null) {
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            for(Product pro : opslaglocatie.getOpslag()){
                if (pro != null) {
                    System.out.println(pro.toString());
                }
            }
        }
    }

    private static void winstVerliesMaand(Bedrijf bedrijf){
        Scanner sc = new Scanner(System.in);
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
        Integer getal = sc.nextInt();
        String locatie = getal == 1 ? "Zolder" : getal == 2 ? "Woonkamer" : getal == 3 ? "Slaapkamer" : null;
        if (locatie != null) {
            System.out.println("Welk maand is het (nummer): ");
            Integer maand = sc.nextInt();
            System.out.println("Welk jaar is het: ");
            Integer jaar = sc.nextInt();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totale winst van de maand %d-%d:  %.2f\n",maand, jaar, new OpslaglocatieCalculator(opslaglocatie).berekenWinstMaand(maand, jaar));
        }
    }

    private static void winstVerliesJaar(Bedrijf bedrijf) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
        Integer getal = sc.nextInt();
        String locatie = getal == 1 ? "Zolder" : getal == 2 ? "Woonkamer" : getal == 3 ? "Slaapkamer" : null;
        if (locatie != null) {
            System.out.println("Welk jaar is het: ");
            Integer jaar = sc.nextInt();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totale winst van het jaar %d:  %.2f\n", jaar, new OpslaglocatieCalculator(opslaglocatie).berekenWinstJaar(jaar));
        }
    }

    private static void verkochtProduct(Bedrijf bedrijf){
        Scanner sc = new Scanner(System.in);
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
        Integer getal = sc.nextInt();
        String locatie = getal == 1 ? "zolder" : getal == 2 ? "woonkamer" : getal == 3 ? "slaapkamer" : null;
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
            System.out.println("Geef verkoopprijs in format xx.x : ");
            Double verkoopprijs = sc.nextDouble();
            System.out.println("Geef jaar: ");
            Integer jaar = sc.nextInt();
            System.out.println("Geef maand: ");
            Integer maand = sc.nextInt();

            try {
                new OpslagManager(opslaglocatie).verkoop(productZoek, verkoopprijs, LocalDate.of(jaar, maand, 1));
                System.out.println("Succesvol verkocht");
            }catch(Exception e){
                System.out.println("Ongeldige waarde voor prijs");
                e.printStackTrace();
            }
        }
    }

    private static void kenmerkOpzoeken(Bedrijf bedrijf){
        System.out.println("Welk kenmerk wil je opzoeken?\n" +
                "1) Naam\n" +
                "2) Merk\n" +
                "3) Kleur\n" +
                "4) Productcode\n" +
                "Invoer: ");
        Scanner sc = new Scanner(System.in);
        int keuze = sc.nextInt();
        sc.nextLine();
        System.out.println("Waarde: ");
        String waarde = sc.nextLine();
        ArrayList <String> result = new ArrayList<>();
        for (Opslaglocatie oL : bedrijf.getOpslaglocaties()) {
            for (Product product : oL.getOpslag()) {
                switch ((keuze)){
                    case 1:
                        if (product.getNaam().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        }
                    case 2:
                        if (product.getMerk().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        }
                    case 3:
                        if (product.getKleur().contains(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        }
                    case 4:
                        if (product.getProductcode().equals(waarde)){
                            result.add(product.toString() + oL.getLocatie());
                        }
                }
            }

        }

        for(String pro : result){
            System.out.println(pro);
        }


    }
    public static void aantalVerkochtteProducten (Bedrijf bedrijf){
        Scanner sc = new Scanner(System.in);
        System.out.print("Kies op welk locatie het product is: \n" +
                "1) Zolder \n" +
                "2) Woonkamer \n" +
                "3) Slaapkamer \n");
        Integer getal = sc.nextInt();
        String locatie = getal == 1 ? "zolder" : getal == 2 ? "woonkamer" : getal == 3 ? "slaapkamer" : null;
        if (locatie != null) {
            System.out.println("Welk maand is het (nummer): ");
            Integer maand = sc.nextInt();
            System.out.println("Welk jaar is het: ");
            Integer jaar = sc.nextInt();
            Opslaglocatie opslaglocatie = bedrijf.getFromLocatie(locatie);
            System.out.printf("Totaal aantal verkochttte producten van de maand %d-%d:  %d\n",maand, jaar, new OpslaglocatieCalculator(opslaglocatie).berekenAantalVerkochtteProductenMaand(maand, jaar));
        }
    }

}
