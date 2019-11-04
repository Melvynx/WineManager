package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.Navigation;
import fr.xaphirre.winemanager.alcoholClass.*;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomePage extends JPanel {

    private Color backGround = ColorPage.MAGENTA.getColor();

    public HomePage(Navigation navigation){

        this.setLayout(new BorderLayout());
        this.setBackground(backGround);
        this.initHead();
        this.initBody();
        this.initFooter();
    }
    private void initHead(){
        JPanel containTitle = new JPanel(new BorderLayout());
        JLabel wineManagerTitle = new JLabel("WINEMANAGER");
        Border borderTitleWineManager = BorderFactory.createLineBorder(ColorPage.LIGHTBLUE.getColor());
        Font fontTitleWineManager = new Font("ROCKETWILDNESS", Font.BOLD, 30);
        Color colorTitleWineManager = ColorPage.LIGHTCYAN.getColor();
        wineManagerTitle.setForeground(colorTitleWineManager);
        wineManagerTitle.setBorder(borderTitleWineManager);
        wineManagerTitle.setFont(fontTitleWineManager);
        wineManagerTitle.setBackground(backGround);
        wineManagerTitle.setPreferredSize(new Dimension(280,50));

        containTitle.setBackground(backGround);
        containTitle.add(wineManagerTitle, BorderLayout.WEST);
        this.add(containTitle, BorderLayout.NORTH);
    }
    private void initBody(){
        Wine w = new Wine("LEVIN", "Test2", 1999, 20, 1000, TypeWine.BLANC, 1999, 2020);
        Beer b = new Beer("LABIERE", "Test2", 1999, 20, 1000, TypeBeer.BLANCHE);
        StrongAlcohol s = new StrongAlcohol("L?ALCOOL", "Test2", 1999, 20, 1000);
        AlcoholPanel a = new AlcoholPanel(w);
        AlcoholPanel bc = new AlcoholPanel(b);
        AlcoholPanel cd = new AlcoholPanel(s);
        JPanel containLastAlcohol = new JPanel(new GridLayout(3,1));
        containLastAlcohol.add(a);
        containLastAlcohol.add(bc);
        containLastAlcohol.add(cd);



        JPanel containBody = new JPanel(new BorderLayout());
        JLabel titleBody = new JLabel("Vos 3 derniers alcool ajouté : ");
        titleBody.setFont(new Font("Nanum Gothic", Font.ITALIC, 22));
        titleBody.setForeground(ColorPage.LIGHTCYAN.getColor());
        containBody.setBackground(backGround);
        containBody.add(titleBody, BorderLayout.NORTH);
        containBody.add(containLastAlcohol, BorderLayout.CENTER);
        this.add(containBody,BorderLayout.CENTER);
    }
    private void initFooter(){
        ImageIcon iconAdd = new ImageIcon("images/add.png");
        Image scaleAdd = iconAdd.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton addAlcoholButton = new JButton((new ImageIcon(scaleAdd)));
        addAlcoholButton.setSize(new Dimension(30,30));

        ImageIcon iconList = new ImageIcon("images/list.png");
        Image scaleList = iconList.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton listAlcoholButton = new JButton((new ImageIcon(scaleList)));
        listAlcoholButton.setSize(new Dimension(30,30));

        JLabel labeAddButton = new JLabel("Ajouter un alcool");
        labeAddButton.setForeground(ColorPage.LIGHTCYAN.getColor());
        labeAddButton.setHorizontalAlignment(JLabel.RIGHT);
        JLabel labeListButton = new JLabel("Afficher des alcools");
        labeListButton.setForeground(ColorPage.LIGHTCYAN.getColor());

        JPanel contentSouth = new JPanel();
        contentSouth.setLayout(new GridLayout(1,2));

        contentSouth.add(listAlcoholButton);
        contentSouth.add(labeListButton);
        contentSouth.add(labeAddButton);
        contentSouth.add(addAlcoholButton);
        contentSouth.setBackground(ColorPage.DARKMAGENTA.getColor());
        this.add(contentSouth, BorderLayout.SOUTH);
    }
}
