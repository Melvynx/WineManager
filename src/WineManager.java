import sun.java2d.jules.IdleTileCache;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Calendar;
import java.util.Scanner;

public class WineManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer chosenOption = null;
        boolean work;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("Bienvenue dans la Cave !" +
                "\n Vous retrouverez tout votre répértoire d'Alcool ici. Il est possible d'y stocker des bières, du Vin et même de l'Alcool fort." +
                "\n \t Commencez maintenant en ajoutant un Alcool ou en parcourant votre List !");
        do {
            do {
                System.out.println("");
                chosenOption = null;
                if (chosenOption == null) {
                    System.out.println("1 : Ajouter un nouvelle Alcoool.");
                    System.out.println("2 : Rechercher un Alcool !");
                    try {
                        chosenOption = sc.nextInt();
                    } catch (java.util.InputMismatchException a) {
                       sc.next();
                       System.out.println("Merci de saisir un nombre. [1 / 2]");
                       chosenOption = null;
                       continue;
                    }
                }
                if (chosenOption > 2 || chosenOption < 1) {
                    System.out.println("Il vous est demander le nombre 1 ou 2, merci de ne pas introduire d'autre nombre.");
                    chosenOption = null;
                }
            } while (chosenOption == null);

            String typeAlcohol = "";
            Integer numberTypeAlcohol = null;
            boolean canBeCreate = false;

            //Processus pour ajouter un nouvelle Alcool
            if (chosenOption == 1) {
                System.out.println("Quelle type d'Alcool voulez-vous crée ?");
                do {
                    System.out.println("1 - Vin");
                    System.out.println("2 - Bière");
                    System.out.println("3 - Alcool fort");
                    try {
                        numberTypeAlcohol = sc.nextInt();
                    } catch (java.util.InputMismatchException a) {
                        sc.next();
                        System.out.println("Merci de saisir un nombre. [1 / 2 / 3]");
                        numberTypeAlcohol = null;
                        continue;
                    }
                    if (numberTypeAlcohol > 3 || numberTypeAlcohol < 1) {
                        System.out.println("Votre nombre n'est pas compris entre 1 et 3.");
                        numberTypeAlcohol = null;
                    }
                } while (numberTypeAlcohol == null);
                if (numberTypeAlcohol == 1) {
                    String nameNewWine = answerName();
                    System.out.println("Le nom de ton Alcool est : " + nameNewWine+".");
                    String regionNewWine = answerRegion();
                    System.out.println("Le nom de ton Alcool est : " + regionNewWine+".");
                    int ageNewWine = answerAge();
                    System.out.println("L'année de ton Alcool est : " + ageNewWine+".");
                    int degreeOfAlcoholNewWine = answerDegreeOfAlcohol();
                    System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewWine+"% .");
                    int capacityNewWine = answerCapacity();
                    System.out.println("Votre bouteille contient "+capacityNewWine+" ml.");
                    String typeNewWine = answerTypeWine();
                    System.out.println("Votre vins est du "+typeNewWine+".");
                    int startMaturity = answerStartMaturity();
                    int endMaturity = answerEndMaturity(startMaturity);
                    System.out.println("Votre vin est à consommer entre "+startMaturity+" et "+ endMaturity +".");
                    Wine newWine = new Wine(nameNewWine, regionNewWine, ageNewWine, degreeOfAlcoholNewWine, capacityNewWine, typeNewWine, startMaturity, endMaturity);
                    System.out.println(newWine);
                }
                if (numberTypeAlcohol == 2) {
                    String nameNewBeer = answerName();
                    System.out.println("Le nom de ton Alcool est : " + nameNewBeer+".");
                    String regionNewBeer = answerRegion();
                    System.out.println("Le nom de ton Alcool est : " + regionNewBeer+".");
                    int ageNewBeer = answerAge();
                    System.out.println("L'année de ton Alcool est : " + ageNewBeer+".");
                    int degreeOfAlcoholNewBeer = answerDegreeOfAlcohol();
                    System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewBeer+"% .");
                    int capacityNewBeer = answerCapacity();
                    System.out.println("Votre bouteille contient "+capacityNewBeer+" ml.");
                    String typeNewBeer = answerTypeBeer();
                    System.out.println("Votre bière est une "+typeNewBeer+".");
                    Beer newBeer = new Beer(nameNewBeer, regionNewBeer, ageNewBeer, degreeOfAlcoholNewBeer, capacityNewBeer, typeNewBeer);
                    System.out.println(newBeer);
                }
                if (numberTypeAlcohol == 3) {
                    String nameNewAlcohol = answerName();
                    System.out.println("Le nom de ton Alcool est : " + nameNewAlcohol+".");
                    String regionNewAlcohol = answerRegion();
                    System.out.println("Le nom de ton Alcool est : " + regionNewAlcohol+".");
                    int ageNewAlcohol = answerAge();
                    System.out.println("L'année de ton Alcool est : " + ageNewAlcohol+".");
                    int degreeOfAlcoholNewAlcohol = answerDegreeOfAlcohol();
                    System.out.println("Le degrée de votre Alcool est : " + degreeOfAlcoholNewAlcohol+"% .");
                    int capacityNewAlcohol = answerCapacity();
                    System.out.println("Votre bouteille contient "+capacityNewAlcohol+" ml.");
                    StrongAlcohol newAlcohol = new StrongAlcohol( nameNewAlcohol, regionNewAlcohol, ageNewAlcohol, degreeOfAlcoholNewAlcohol, capacityNewAlcohol);
                    System.out.println(newAlcohol);
                }
            }
            //Processus pour afficher un/des alcool(s)
            if (chosenOption == 2) {
                System.out.println("Voici la list des vins présent acutellement !");
            }
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Voulez vous effectuer d'autre opération ?[Y/N]");
            char charOfChoice = 'y';
            boolean charOfChoiceCorrect = true;
            while (charOfChoiceCorrect) {
                charOfChoice = sc2.nextLine().charAt(0);
                if (charOfChoice == 'y' || charOfChoice == 'Y' || charOfChoice == 'n' || charOfChoice == 'N') {
                    System.out.println("ok");
                    charOfChoiceCorrect = false;
                } else {
                    System.out.println("Votre caractères ne correspond pas au critère. [y/Y(YES) / n/N(NO)]");
                    charOfChoiceCorrect = true;
                }
            }
            if (charOfChoice == 'y' || charOfChoice == 'Y') {
                work = true;
            } else {
                work = false;
            }
        } while(work);

    }
    Scanner scA = new Scanner(System.in);


    public static String answerName(){
        Scanner scA = new Scanner(System.in);
        String name = "";
        do {
            System.out.println("Quelle est le nom de votre Alcool ?");
            name = scA.nextLine();
        } while (name.length() < 3 || name.length() > 30);
        return name;
    }
    public static String answerRegion() {
        Scanner scA = new Scanner(System.in);
        String region = "";
        do {
            System.out.println("Quelle est la région de votre Alcool ?");
            region = scA.nextLine();
        } while (region.length() < 3 || region.length() > 30);
        return region;

    }
    public static int answerAge() {
        int dateGive = 1;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        Scanner scA = new Scanner(System.in);
        do {
            System.out.println("Quelle est l'année de création de votre bouteille ? [Format : YYYY]");
            try {
                dateGive = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
                System.out.println("Merci de saisir des nombres. [1000 - "+yearToday+"]");
                dateGive = 1;
                continue;
            }
        } while (dateGive > yearToday || dateGive < 1000);
        return dateGive;
    }
    public static int answerDegreeOfAlcohol() {
        int degreeOfAlcohol = 1;
        Scanner scA = new Scanner(System.in);
        do {
            System.out.println("Quelle est le degré d'Alcool bouteille ? [Nombre entre 1 et 100.]");
            try {
                degreeOfAlcohol = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
                System.out.println("Merci de saisir des nombres. [0 - 100]");
                degreeOfAlcohol = -1;
                continue;
            }
        } while (degreeOfAlcohol > 100 || degreeOfAlcohol < 0);
        return degreeOfAlcohol;
    }
    public static int answerCapacity() {
        int capacity = 1;
        Scanner scA = new Scanner(System.in);
        do {
            System.out.println("Combien de ML contient votre bouteille d'Alcool ? [Nombre entre 1 et 1000.]");
            try {
                capacity = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
                System.out.println("Merci de saisir des nombres. [0 - 1000 ML]");
                capacity = 1;
                continue;
            }
        } while (capacity > 1000 || capacity < 10);
        return capacity;
    }
    public static String answerTypeWine() {
        String type ="";
        Scanner scA = new Scanner(System.in);
        Integer numberTypeWine = null;
        do {
            System.out.println("1 - Blanc");
            System.out.println("2 - Rouge");
            System.out.println("3 - Rosée");
            try {
                numberTypeWine = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
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
            case 1: type = TypeWine.BLANC.getName();
                break;
            case 2: type = TypeWine.ROUGE.getName();
                break;
            case 3: type = TypeWine.ROSE.getName();
                break;
        }
        return type;
    }
    public static int answerStartMaturity() {
        int dateGive = 1;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        Scanner scA = new Scanner(System.in);
        do {
            System.out.println("Quelle est l'année à partir de laquelle votre bouteille est à maturité ? [Format : YYYY]");
            try {
                dateGive = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
                System.out.println("Merci de saisir des nombres. [1000 - "+yearToday+"]");
                dateGive = 1;
                continue;
            }
        } while (dateGive > yearToday || dateGive < 300);
        return dateGive;
    }
    public static int answerEndMaturity(int startMaturity) {
        int dateGive = 1;
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        Scanner scA = new Scanner(System.in);
        do {
            System.out.println("Et la date de fin ? [Format : YYYY]");
            try {
                dateGive = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
                System.out.println("Merci de saisir des nombres. ["+startMaturity+" - 2XXX]");
                dateGive = 1;
                continue;
            }
        } while (dateGive < yearToday || dateGive < startMaturity);
        return dateGive;
    }
    public static String answerTypeBeer() {
        String type ="";
        Scanner scA = new Scanner(System.in);
        Integer numberTypeBeer= null;
        do {
            System.out.println("1 - Blanche");
            System.out.println("2 - Blonde");
            System.out.println("3 - Brune");
            System.out.println("4 - Rousse");
            try {
                numberTypeBeer = scA.nextInt();
            } catch (java.util.InputMismatchException a) {
                scA.next();
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
            case 1: type = TypeBeer.BLANCHE.getName();
                break;
            case 2: type = TypeBeer.BLONDE.getName();
                break;
            case 3: type = TypeBeer.BRUNE.getName();
                break;
            case 4: type = TypeBeer.ROUSSE.getName();
                break;
        }
        return type;
    }
}
