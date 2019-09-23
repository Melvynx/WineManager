import java.util.Scanner;

public class WineManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer chosenOption = null;
        int o = 1;
        System.out.println("Bienvenue dans la Cave !" +
                "\n Vous retrouverez tout votre répértoire d'Alcool ici. Il est possible d'y stocker des bières, du Vin et même de l'Alcool fort." +
                "\n \t Commencez maintenant en ajoutant un Alcool ou en parcourant votre List !");
        do {
            do {
                System.out.println("");
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
                    chosenOption = -1;
                }
            } while (chosenOption > 2 || chosenOption < 1);
            //Processus pour ajouter un nouvelle Alcool
            if (chosenOption == 1) {

            }
            //Processus pour afficher un/des alcool(s)
            if (chosenOption == 2) {

            }


        } while(o == -1);

    }

}
