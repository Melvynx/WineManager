package fr.xaphirre.winemanager;

import fr.xaphirre.winemanager.Page.HomePage;
import fr.xaphirre.winemanager.Page.ShowAlcoholPage;

import javax.swing.*;

public class Window extends JFrame implements Navigation{


    Window(){
        //Default settings
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
        ShowAlcoholPage showAlcoholPage;

        switch (pages) {
            case 0:
                homePage = new HomePage(this);
                this.setContentPane(homePage);
                break;
            case 1:
                showAlcoholPage = new ShowAlcoholPage(this);
                this.setContentPane(showAlcoholPage);
                break;
            default:
                System.out.println("Erreur: ["+pages+"] n'est pas définit.(Définit entre 0 et 1)");
                break;
        }
        this.getContentPane().revalidate();
    }

}
