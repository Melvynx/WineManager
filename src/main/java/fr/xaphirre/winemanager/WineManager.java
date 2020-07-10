package fr.xaphirre.winemanager;

import fr.xaphirre.winemanager.alcoholClass.Alcohol;
import fr.xaphirre.winemanager.alcoholClass.Beer;
import fr.xaphirre.winemanager.alcoholClass.StrongAlcohol;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.Wine;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import java.sql.SQLException;
import java.util.*;

public class WineManager {
    public static String dbPath = "alcohol.db";
    static ConnectionSQL connection = null;

    private static Scanner scanner = null;

    public static void main(String[] args) throws SQLException {
        scanner = new Scanner(System.in);
        connection = new ConnectionSQL(dbPath);
        connection.connect();

        Integer chosenOption;
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*\t" +
                "Bienvenue dans la Cave\t"+
                "*-*-*-*-*-*-*-*-*-*-*-*-*\n"+
                "\n Vous retrouverez tout votre répértoire d'Alcool ici. Il est possible d'y stocker des bières, du Vin et même de l'Alcool fort." +
                "\n Commencez maintenant en ajoutant un Alcool ou en parcourant votre List !\n");
        //Application :
        do {
            chosenOption = questionOption();
            //New alcohol
            if (chosenOption == 1) {
                System.out.println("Quelle type d'Alcool voulez-vous crée ?");
                int numberTypeAlcohol = questionAdd();
                if (numberTypeAlcohol == 1) {
                     newWine();
                }
                if (numberTypeAlcohol == 2) {
                    newBeer();
                }
                if (numberTypeAlcohol == 3) {
                    newAlcohol();
                }
            }
            //List alcohol
            if (chosenOption == 2) {

                System.out.println("Voici la list des vins présent acutellement !");
                System.out.println("Quelle Alcool voulez-vous afficher ?");
                int numberSearch = questionSearch();
                switch (numberSearch) {
                    case 1:
                        List<Alcohol> alcohols = connection.getAllAlcohol();
                        for (Alcohol alcohol : alcohols) {
                            System.out.println(alcohol);
                        }
                        break;
                    case 2:
                        List<Wine> alcoholFiltredWine = connection.getWines();
                        for (Wine wine : alcoholFiltredWine) {
                            System.out.println(wine);
                        }
                        break;
                    case 3:
                        List<Beer> alcoholFiltredBeer = connection.getBeer();
                        for (Beer beer : alcoholFiltredBeer) {
                            System.out.println(beer);
                        }
                        break;
                    case 4:
                        List<StrongAlcohol> alcoholFiltredStrongAlcohol = connection.getStrongAlcohol();
                        for (StrongAlcohol strongAlcohol : alcoholFiltredStrongAlcohol) {
                            System.out.println(strongAlcohol);
                        }
                        break;
                }
            }
            //Redo ?
        } while(chosenOption != 3);

        connection.close();
    }
    /*
    |------------------------------------------------------------------------|
    |------------------------------------------------------------------------|
    |Function                                                                |
    |------------------------------------------------------------------------|
    |------------------------------------------------------------------------|
     */
    private static String answerName(){
        String name;
        System.out.println("Quelle est le nom de votre Alcool ?");
        do {
            name = scanner.nextLine();
            if (name.length() < 3 || name.length() > 30) {
                System.out.println("Minimume 3 caractères, Maximume 30!");
            }
        } while (name.length() < 3 || name.length() > 30);
        return name;
    }
    private static String answerRegion() {
        String region;
        do {
            System.out.println("Quelle est la région de votre Alcool ?");
            region = scanner.nextLine();
        } while (region.length() < 3 || region.length() > 30);
        return region;

    }
    private static int answerAge() {
        int dateGive;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        do {
            System.out.println("Quelle est l'année de création de votre bouteille ? [Format : YYYY]");
            try {
                dateGive = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir des nombres. [1000 - "+yearToday+"]");
                dateGive = 1;
            }
        } while (dateGive > yearToday || dateGive < 1000);
        return dateGive;
    }
    private static int answerDegreeOfAlcohol() {
        int degreeOfAlcohol;
        do {
            System.out.println("Quelle est le degré d'Alcool bouteille ? [Nombre entre 1 et 100.]");
            try {
                degreeOfAlcohol = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir des nombres. [0 - 100]");
                degreeOfAlcohol = -1;
            }
        } while (degreeOfAlcohol > 100 || degreeOfAlcohol < 0);
        return degreeOfAlcohol;
    }
    private static int answerCapacity() {
        int capacity;
        do {
            System.out.println("Combien de ML contient votre bouteille d'Alcool ? [Nombre entre 1 et 1000.]");
            try {
                capacity = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir des nombres. [0 - 10000 ML]");
                capacity = 1;
            }
        } while (capacity > 10000 || capacity < 10);
        return capacity;
    }
    private static TypeWine answerTypeWine() {
        TypeWine type = null;
        Integer numberTypeWine;
        do {
            System.out.println("1 - Blanc");
            System.out.println("2 - Rouge");
            System.out.println("3 - Rosée");
            try {
                numberTypeWine = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir un nombre. [1 / 2 / 3]");
                numberTypeWine = null;
                continue;
            }
            if (numberTypeWine > 3 || numberTypeWine < 1) {
                System.out.println("Votre nombre n'est pas compris entre 1 et 3.");
                numberTypeWine = null;
            }
        } while (numberTypeWine == null);
        switch (numberTypeWine) {
            case 1: type = TypeWine.BLANC;
                break;
            case 2: type = TypeWine.ROUGE;
                break;
            case 3: type = TypeWine.ROSE;
                break;
        }
        return type;
    }
    private static int answerStartMaturity() {
        int dateGive;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        do {
            System.out.println("Quelle est l'année à partir de laquelle votre bouteille est à maturité ? [Format : YYYY]");
            try {
                dateGive = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir des nombres. [1000 - "+yearToday+"]");
                dateGive = 1;
            }
        } while (dateGive > yearToday-10 || dateGive < 300);
        return dateGive;
    }
    private static int answerEndMaturity(int startMaturity) {
        int dateGive;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        do {
            System.out.println("Et la date de fin ? [Format : YYYY]");
            try {
                dateGive = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir des nombres. ["+startMaturity+" - 2XXX]");
                dateGive = 1;
            }
        } while (dateGive < yearToday || dateGive < startMaturity);
        return dateGive;
    }
    private static TypeBeer answerTypeBeer() {
        System.out.println("Quelle est le type de votre bière ?");
        TypeBeer type = null;
        Integer numberTypeBeer;
        do {
            System.out.println("1 - Blanche");
            System.out.println("2 - Blonde");
            System.out.println("3 - Brune");
            System.out.println("4 - Rousse");
            try {
                numberTypeBeer = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir un nombre. [1 / 2 / 3 / 4]");
                numberTypeBeer = null;
                continue;
            }
            if (numberTypeBeer > 4 || numberTypeBeer < 1) {
                System.out.println("Votre nombre n'est pas compris entre 1 et 4.");
                numberTypeBeer = null;
            }
        } while (numberTypeBeer == null);
        switch (numberTypeBeer) {
            case 1: type = TypeBeer.BLANCHE;
                break;
            case 2: type = TypeBeer.BLONDE;
                break;
            case 3: type = TypeBeer.BRUNE;
                break;
            case 4: type = TypeBeer.ROUSSE;
                break;
        }
        return type;
    }
    private static void newWine() {

        String nameNewWine = answerName();
        System.out.println("Le nom de ton Alcool est : " + nameNewWine+".");
        String regionNewWine = answerRegion();
        System.out.println("La région de ton Alcool est : " + regionNewWine+".");
        int ageNewWine = answerAge();
        System.out.println("L'année de ton Alcool est : " + ageNewWine+".");
        int degreeOfAlcoholNewWine = answerDegreeOfAlcohol();
        System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewWine+"% .");
        int capacityNewWine = answerCapacity();
        System.out.println("Votre bouteille contient "+capacityNewWine+" ml.");
        TypeWine typeNewWine = answerTypeWine();
        System.out.println("Votre vins est du "+typeNewWine+".");
        int startMaturity = answerStartMaturity();
        int endMaturity = answerEndMaturity(startMaturity);
        System.out.println("Votre vin est à consommer entre "+startMaturity+" et "+ endMaturity +".");
        Wine newWine = new Wine(nameNewWine, regionNewWine, ageNewWine, degreeOfAlcoholNewWine, capacityNewWine, typeNewWine, startMaturity, endMaturity);
        System.out.println(newWine);

        connection.addWine(newWine);
    }
    private static void newBeer() {
        String nameNewBeer = answerName();
        System.out.println("Le nom de ton Alcool est : " + nameNewBeer+".");
        String regionNewBeer = answerRegion();
        System.out.println("La région de ton Alcool est : " + regionNewBeer+".");
        int ageNewBeer = answerAge();
        System.out.println("L'année de ton Alcool est : " + ageNewBeer+".");
        int degreeOfAlcoholNewBeer = answerDegreeOfAlcohol();
        System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewBeer+"% .");
        int capacityNewBeer = answerCapacity();
        System.out.println("Votre bouteille contient "+capacityNewBeer+" ml.");
        TypeBeer typeNewBeer = answerTypeBeer();
        System.out.println("Votre bière est une "+typeNewBeer+".");
        Beer newBeer = new Beer(nameNewBeer, regionNewBeer, ageNewBeer, degreeOfAlcoholNewBeer, capacityNewBeer, typeNewBeer);
        System.out.println(newBeer);

        connection.addBeer(newBeer);
    }
    private static void newAlcohol() {
        String nameNewAlcohol = answerName();
        System.out.println("Le nom de ton Alcool est : " + nameNewAlcohol+".");
        String regionNewAlcohol = answerRegion();
        System.out.println("La région de ton Alcool est : " + regionNewAlcohol+".");
        int ageNewAlcohol = answerAge();
        System.out.println("L'année de ton Alcool est : " + ageNewAlcohol+".");
        int degreeOfAlcoholNewAlcohol = answerDegreeOfAlcohol();
        System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewAlcohol+"% .");
        int capacityNewAlcohol = answerCapacity();
        System.out.println("Votre bouteille contient "+capacityNewAlcohol+" ml.");
        StrongAlcohol newAlcohol = new StrongAlcohol( nameNewAlcohol, regionNewAlcohol, ageNewAlcohol, degreeOfAlcoholNewAlcohol, capacityNewAlcohol);
        System.out.println(newAlcohol);

        connection.addStrongAlcohol(newAlcohol);
    }
    private  static int questionSearch() {
        Integer numberSearch;
        do {
            System.out.println("1 - Tous les Alcool de ma cave :p !");
            System.out.println("2 - Tous les Vins de ma cave :o !");
            System.out.println("3 - Toutes les Bières de ma cave c: !");
            System.out.println("4 - Tous les Alcool FORT de ma cave :x !");
            try {
                numberSearch = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir un nombre. [1 / 2 / 3]");
                numberSearch = null;
                continue;
            }
            if (numberSearch > 4 || numberSearch < 1) {
                System.out.println("Votre nombre n'est pas compris entre 1 et 4.");
                numberSearch = null;
            }
        } while (numberSearch == null);
        return numberSearch;
    }
    private static int questionAdd() {
        Integer numberTypeAlcohol;
        do {
            System.out.println("1 - Vin");
            System.out.println("2 - Bière");
            System.out.println("3 - Alcool fort");
            try {
                numberTypeAlcohol = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir un nombre. [1 / 2 / 3]");
                numberTypeAlcohol = null;
                continue;
            }
            if (numberTypeAlcohol > 3 || numberTypeAlcohol < 1) {
                System.out.println("Votre nombre n'est pas compris entre 1 et 3.");
                numberTypeAlcohol = null;
            }
        } while (numberTypeAlcohol == null);
        return numberTypeAlcohol;
    }
    private static Integer questionOption() {
        Integer chosenOption;
        do {
            System.out.println("\n\tQue voulez-vous faire ?\n");
            System.out.println("1 : Ajouter un nouvelle Alcoool.");
            System.out.println("2 : Rechercher un Alcool !");
            System.out.println("3 : Quitter");
            try {
                chosenOption = scanner.nextInt();
            } catch (java.util.InputMismatchException a) {
                scanner.next();
                System.out.println("Merci de saisir un nombre. [1 / 2]");
                chosenOption = null;
                continue;
            }
            if (chosenOption > 3 || chosenOption < 1) {
                System.out.println("Il vous est demander le nombre 1, 2 ou 3, merci de ne pas introduire d'autre nombre.");
                chosenOption = null;
            }
        } while (chosenOption == null);
        if (chosenOption == 3) {
            System.out.println("Bonne suite de journée/soirée.");
        }
        return chosenOption;
    }
}
