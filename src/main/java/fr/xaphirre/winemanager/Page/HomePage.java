package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.ConnectionSQL;
import fr.xaphirre.winemanager.Navigation;
import fr.xaphirre.winemanager.alcoholClass.*;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {

    private Color backGround = ColorPage.PRIMARY.getColor();
    public static String dbPath = "alcohol.db";
    static ConnectionSQL connection = null;

    public HomePage(Navigation navigation){
        this.setLayout(new BorderLayout());
        this.setBackground(backGround);
        //Initialisation de toutes les parties
        this.initHead();
        this.initBody();
        this.initFooter();
    }
    private void initHead(){
        //Création
        JPanel containTitle = new JPanel(new BorderLayout());
        JLabel wineManagerTitle = new JLabel("WINEMANAGER");
        Border borderTitleWineManager = BorderFactory.createLineBorder(ColorPage.COLOR1.getColor());
        Font fontTitleWineManager = new Font("ROCKETWILDNESS", Font.BOLD, 30);
        Color colorTitleWineManager = ColorPage.COLOR1.getColor();
        //Mise en page
        wineManagerTitle.setForeground(colorTitleWineManager);
        wineManagerTitle.setFont(fontTitleWineManager);
        wineManagerTitle.setBackground(backGround);
        wineManagerTitle.setPreferredSize(new Dimension(280,50));
        //Implémentation
        containTitle.setBackground(ColorPage.PRIMARY.getColor());
        containTitle.add(wineManagerTitle, BorderLayout.WEST);
        this.add(containTitle, BorderLayout.NORTH);
    }
    private void initBody(){
        //TEST
        Wine w = new Wine("LEVIN", "Test2", 1999, 20, 1000, TypeWine.BLANC, 1999, 2020);
        Beer b = new Beer("LABIERE", "Test2", 1999, 20, 1000, TypeBeer.BLANCHE);
        StrongAlcohol s = new StrongAlcohol("L?ALCOOL", "Test2", 1999, 20, 1000);
        AlcoholPanel a = new AlcoholPanel(w);
        AlcoholPanel bc = new AlcoholPanel(b);
        AlcoholPanel cd = new AlcoholPanel(s);
        JPanel containLastAlcohol = new JPanel(new GridLayout(3,1));

        //FIN TEST
        containLastAlcohol.add(a);
        containLastAlcohol.add(bc);
        containLastAlcohol.add(cd);

        //Mise en page
        JPanel containBody = new JPanel(new BorderLayout());
        JLabel titleBody = new JLabel("Vos 3 derniers alcool ajouté : ");
        titleBody.setFont(new Font("Nanum Gothic", Font.ITALIC, 22));
        titleBody.setForeground(ColorPage.DARKPRIMARY.getColor());
        containBody.setBackground(ColorPage.COLOR1.getColor());
        containBody.add(titleBody, BorderLayout.NORTH);
        containBody.add(containLastAlcohol, BorderLayout.CENTER);
        this.add(containBody,BorderLayout.CENTER);
    }
    private void initFooter(){
        //Instanciment button et label pour ajouter
        Font fontLabel = new Font("Nanum Gothic", Font.PLAIN, 20);
        ImageIcon iconAdd = new ImageIcon("images/add.png");
        Image scaleAdd = iconAdd.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton buttonAdd = new JButton((new ImageIcon(scaleAdd)));
        buttonAdd.setSize(new Dimension(30,30));
        buttonAdd.addActionListener(new ButtonAddListener());
        JLabel labelAdd = new JLabel("Ajouter un alcool");
        labelAdd.setForeground(ColorPage.COLOR1.getColor());
        labelAdd.setHorizontalAlignment(JLabel.RIGHT);
        labelAdd.setFont(fontLabel);
        //Instanciment button et label pour afficher la list
        ImageIcon iconList = new ImageIcon("images/list.png");
        Image scaleList = iconList.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton buttonList = new JButton((new ImageIcon(scaleList)));
        buttonList.setSize(new Dimension(30,30));
        JLabel labelList = new JLabel("Afficher des alcools");
        labelList.setForeground(ColorPage.COLOR1.getColor());
        labelList.setFont(fontLabel);
        //Mise en forme
        JPanel contentSouth = new JPanel();
        contentSouth.setLayout(new GridLayout(1,2));
        JPanel contentSouthAdd = new JPanel(new BorderLayout());
        JPanel contentSouthList = new JPanel(new BorderLayout());
        contentSouthList.add(buttonList, BorderLayout.WEST);
        contentSouthList.add(labelList, BorderLayout.CENTER);
        contentSouthAdd.add(labelAdd, BorderLayout.CENTER);
        contentSouthAdd.add(buttonAdd, BorderLayout.EAST);
        contentSouthAdd.setBackground(ColorPage.PRIMARY.getColor());
        contentSouth.add(contentSouthList);
        contentSouth.add(contentSouthAdd);
        contentSouthList.setBackground(ColorPage.PRIMARY.getColor());
        contentSouth.setPreferredSize(new Dimension(this.getWidth(), 40));
        this.add(contentSouth, BorderLayout.SOUTH);
    }

    private class ButtonAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogNewAlcohol newAlcohol = new DialogNewAlcohol(null, "Test", true);
            connection = new ConnectionSQL(dbPath);
            connection.connect();
            switch (newAlcohol.getTypeAlcohol()){
                case "Wine":
                    Wine wine = (Wine)newAlcohol.getAlcohol(0);
                    connection.addWine(wine);
                    break;
                case "Beer":
                    Beer beer = (Beer)newAlcohol.getAlcohol(1);
                    connection.addBeer(beer);
                    break;
                case "Nothing":
                    break;
                default:
                    StrongAlcohol strongAlcohol = (StrongAlcohol)newAlcohol.getAlcohol(2);
                    connection.addStrongAlcohol(strongAlcohol);
                    break;
            }
            connection.close();
        }
    }
}
