package fr.xaphirre.winemanager;

import fr.xaphirre.winemanager.Page.HomePage;
import fr.xaphirre.winemanager.Page.ListAlcoholPage;

import javax.swing.*;

public class Window extends JFrame implements Navigation{

    public static final ConnectionSQL connection = new ConnectionSQL("alcohol.db");

    Window(){
        //Default settings
        connection.connect();
        this.setSize(900,700);
        this.setTitle("WineManager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setPage(0);
    }

    //Function to change the currently display page
    public void setPage(int pages) {
        this.getContentPane().removeAll();

        HomePage homePage;
        ListAlcoholPage listAlcoholPage;

        switch (pages) {
            case 0:
                homePage = new HomePage(this);
                this.setContentPane(homePage);
                break;
            case 1:
                listAlcoholPage = new ListAlcoholPage(this);
                this.setContentPane(listAlcoholPage);
                break;
            default:
                System.out.println("Erreur: ["+pages+"] n'est pas définit.(Définit entre 0 et 1)");
                break;
        }
        this.getContentPane().revalidate();
    }

}
