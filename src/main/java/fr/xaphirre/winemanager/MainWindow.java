package fr.xaphirre.winemanager;

public class MainWindow {
    public static void main(String[] args) {
        //Affichage des menu au bonne endrot sur mac
        try {
            System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "Ted" );
            System.setProperty( "com.apple.macos.useScreenMenuBar", "true" );
            System.setProperty( "apple.laf.useScreenMenuBar", "true" ); // for older versions of Java
        } catch ( SecurityException e ) {/*Nothing*/}



        System.out.println("Démarrage de la page principal.");
        Window startWindow = new Window();
    }
}
