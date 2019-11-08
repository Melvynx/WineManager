package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.Navigation;
import fr.xaphirre.winemanager.Window;
import fr.xaphirre.winemanager.alcoholClass.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JPanel {

    private Color backGround = ColorPage.PRIMARY.getColor();
    private static String dbPath = "alcohol.db";
    JPanel containLastAlcohol, containBody;


    private Navigation navigation;

    public HomePage(Navigation navigation){
        this.navigation = navigation;
        this.setLayout(new BorderLayout());
        this.setBackground(backGround);
        //Initialisation de toutes les parties
        this.initHead();
        this.initBody();
        this.initFooter();
        Window.connection.addSubscriber(this::generateThreeLastAlcohol);
    }
    private void initHead(){
        //Création
        JPanel containTitle = new JPanel(new BorderLayout());
        JLabel wineManagerTitle = new JLabel("WINEMANAGER");
        Font fontTitleWineManager = new Font("ROCKETWILDNESS", Font.BOLD, 30);
        Color colorTitleWineManager = ColorPage.COLOR1.getColor();
        //Mise en page
        wineManagerTitle.setForeground(colorTitleWineManager);
        wineManagerTitle.setFont(fontTitleWineManager);
        wineManagerTitle.setBackground(backGround);
        wineManagerTitle.setPreferredSize(new Dimension(280,50));
        //Implémentation
        containTitle.setBackground(backGround);
        containTitle.add(wineManagerTitle, BorderLayout.WEST);
        this.add(containTitle, BorderLayout.NORTH);
    }
    private void initBody(){
        containBody = new JPanel(new BorderLayout());
        containLastAlcohol = new JPanel(new GridLayout(3,1));
        generateThreeLastAlcohol();
        //Mise en page
        JLabel titleBody = new JLabel("Vos 3 derniers alcool ajouté : ");
        titleBody.setFont(new Font("Nanum Gothic", Font.ITALIC, 22));
        titleBody.setForeground(ColorPage.DARKPRIMARY.getColor());
        containBody.setBackground(ColorPage.COLOR1.getColor());
        containBody.add(titleBody, BorderLayout.NORTH);

        containBody.add(containLastAlcohol, BorderLayout.CENTER);
        this.remove(containBody);
        this.add(containBody,BorderLayout.CENTER);
    }

    private void generateThreeLastAlcohol() {
        //Début real
        containLastAlcohol.removeAll();
        List<Alcohol> alcohols = null;
        try{
            alcohols = Window.connection.getAllAlcohol();
        } catch (SQLException e){
            e.printStackTrace();
        }
        assert alcohols != null;
        if (alcohols.size() > 2){
            this.triBulles(alcohols);
        }
        AlcoholPanel lastPan = new AlcoholPanel(alcohols.get(0));
        AlcoholPanel lastPan2 = new AlcoholPanel(alcohols.get(1));
        AlcoholPanel lastPan3 = new AlcoholPanel(alcohols.get(2));
        //FIN TEST
        containLastAlcohol.add(lastPan);
        containLastAlcohol.add(lastPan2);
        containLastAlcohol.add(lastPan3);

        containLastAlcohol.revalidate();
        containLastAlcohol.repaint();
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
        buttonList.addActionListener(new ButtonListListener());
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
    private class ButtonListListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            navigation.setPage(1);
        }
    }

    private class ButtonAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogAddAlcohol newAlcohol = new DialogAddAlcohol(null, "New Alcohol", true);
            switch (newAlcohol.getTypeAlcohol()){
                case "Wine":
                    Wine wine = (Wine)newAlcohol.getAlcohol(0);
                    Window.connection.addWine(wine);
                    break;
                case "Beer":
                    Beer beer = (Beer)newAlcohol.getAlcohol(1);
                    Window.connection.addBeer(beer);
                    break;
                case "Nothing":
                    break;
                default:
                    StrongAlcohol strongAlcohol = (StrongAlcohol)newAlcohol.getAlcohol(2);
                    Window.connection.addStrongAlcohol(strongAlcohol);
                    break;
            }
            generateThreeLastAlcohol();
        }
    }
    private void triBulles(List<Alcohol> scoreList)
    {
        for (int i=0 ;i<=(scoreList.size()-2);i++)
            for (int j=(scoreList.size()-1);i < j;j--)
                if (scoreList.get(j).getDate().getTime() > scoreList.get(j-1).getDate().getTime())
                {
                    Alcohol alcoholSave = scoreList.get(j-1);
                    scoreList.set(j-1, scoreList.get(j));
                    scoreList.set(j, alcoholSave);
                }
    }
}
