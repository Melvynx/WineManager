package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.DeleteSQL;
import fr.xaphirre.winemanager.Window;
import fr.xaphirre.winemanager.alcoholClass.*;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlcoholPanel extends JPanel {

    private JPanel content = new JPanel();
    private JPanel contentLeft = new JPanel();
    private JPanel contentRight = new JPanel();

    private String typeAlcohol = "";
    private String nameAlcohol = "";
    private String regionAlcohol = "";
    private Integer ageAlcohol = null;
    private Integer degreeOfAlcohol = null;
    private Integer capacityAlcohol = null;
    private TypeWine typeWine;
    private TypeBeer typeBeer;
    private Integer startMaturityWine = null;
    private Integer endMaturityWine = null;
    private Color background = ColorPage.COLOR1.getColor();
    private Color textColor = ColorPage.DARKPRIMARY.getColor();

    private Alcohol alcohol;

    AlcoholPanel(Alcohol alcohol){
        this.alcohol = alcohol;

        content.setLayout(new BorderLayout());
        content.setPreferredSize(new Dimension(750,125));
        content.setBackground(background);
        this.initValue();
        this.initContent();
        this.add(content);
        Border border = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(0,0,5,0,ColorPage.PRIMARY.getColor()), typeAlcohol, 1, 1, new Font("Nanum Gothic", Font.PLAIN, 18), textColor);

        this.setBorder(border);
        this.setBackground(background);
        this.setSize(this.getWidth(), 180);

    }
    private void initContent(){
        Font font = new Font("Nanum Gothic", Font.PLAIN, 18);

        if(typeAlcohol.equals("Wine")){


            contentRight.setLayout(new GridLayout(4,1));
            initBaseContent();

            JLabel type = new JLabel("C'est du vin " +typeWine.getName());
            JLabel maturity = new JLabel("A consommer entre "+startMaturityWine+" et "+endMaturityWine+".");
            maturity.setFont(font);
            maturity.setForeground(textColor);
            type.setFont(font);
            type.setForeground(textColor);
            contentRight.add(type);
            contentRight.add(maturity);
        }
        if(typeAlcohol.equals("Beer")){

            contentRight.setLayout(new GridLayout(3,1));
            initBaseContent();


            JLabel type = new JLabel("C'est une bière " +typeBeer.getName());
            type.setFont(font);
            type.setForeground(textColor);
            contentRight.add(type);
        }
        if(typeAlcohol.equals("Alcohol")){
            contentRight.setLayout(new GridLayout(2,2));

            initBaseContent();
        }
    }
    private void initBaseContent() {
        Font font = new Font("Nanum Gothic", Font.PLAIN, 18);

        initButtonAndLabel();

        JLabel region = new JLabel("Région : "+regionAlcohol);
        JLabel age = new JLabel("Date de création : "+ageAlcohol);
        JLabel degree = new JLabel("Degrée d'alcool : "+degreeOfAlcohol+"%");
        JLabel capacity = new JLabel("Capacité (ml) : "+capacityAlcohol+"ml");


        region.setFont(font);
        region.setForeground(textColor);


        age.setFont(font);
        age.setForeground(textColor);

        degree.setFont(font);
        degree.setForeground(textColor);

        capacity.setFont(font);
        capacity.setForeground(textColor);

        JPanel contentCenter = new JPanel();
        contentCenter.setLayout(new GridLayout(1,2));
        contentLeft.setLayout(new GridLayout(2,1));
        contentLeft.add(region);
        contentLeft.add(age);

        contentRight.add(degree);
        contentRight.add(capacity);
        if(typeAlcohol.equals("Wine")){
            contentLeft.setLayout(new GridLayout(4,1));
            JPanel panVide = new JPanel(), panVide2 = new JPanel();panVide.setBackground(background);panVide2.setBackground(background);
            contentLeft.add(panVide);
            contentLeft.add(panVide2);
        }
        if(typeAlcohol.equals("Beer")){
            contentLeft.setLayout(new GridLayout(3,1));
            JPanel panVide = new JPanel();panVide.setBackground(background);
            contentLeft.add(panVide);
        }

        contentCenter.add(contentLeft);
        contentCenter.add(contentRight);
        contentLeft.setBackground(background);
        contentRight.setBackground(background);
        content.add(contentCenter, BorderLayout.CENTER);

    }
    private void initValue() {
        //Default value;
        nameAlcohol = alcohol.getName();
        regionAlcohol = alcohol.getRegion();
        ageAlcohol = alcohol.getAge();
        degreeOfAlcohol = alcohol.getDegreeOfAlcohol();
        capacityAlcohol = alcohol.getCapacityML();

        if (alcohol instanceof Wine){
            typeAlcohol = "Wine";
            typeWine = ((Wine) alcohol).getType();
            startMaturityWine = ((Wine) alcohol).getStartMaturity();
            endMaturityWine = ((Wine) alcohol).getEndMaturity();
        }
        if (alcohol instanceof Beer){
            typeAlcohol = "Beer";
            typeBeer = ((Beer) alcohol).getType();
        }
        if (alcohol instanceof StrongAlcohol){
            typeAlcohol = "Alcohol";
        }
    }
    private void initButtonAndLabel() {
        JLabel nom = new JLabel(nameAlcohol);
        Font fontName = new Font("Nanum Gothic", Font.ITALIC, 22);
        JPanel contentNorth = new JPanel(new GridLayout(1,2));

        nom.setFont(fontName);
        nom.setHorizontalAlignment(JLabel.LEFT);
        nom.setForeground(textColor);
        contentNorth.add(nom);

        ImageIcon iconDelete = new ImageIcon("images/delete.png");
        Image scaleDelete = iconDelete.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton deleteButton = new JButton((new ImageIcon(scaleDelete)));
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.addActionListener(new DeleteButtonListener());
        JPanel contentButton = new JPanel(new GridLayout(1,6));
        for (int i = 1; i < 6; i++){
            JPanel panEmpty = new JPanel();
            panEmpty.setBackground(background);
            contentButton.add(panEmpty);
        }
        contentButton.add(deleteButton);
        contentButton.setBackground(background);
        contentNorth.add(contentButton);
        contentNorth.setPreferredSize(new Dimension(750, 35));
        contentNorth.setBackground(background);

        content.add(contentNorth, BorderLayout.NORTH);
    }
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choiceDelete = JOptionPane.showConfirmDialog(null, "<html>Vous-êtes sur le point de supprimer un Alcool.<br/>Etes-vous sûr ?<br/>Nom : "+alcohol.getName()+"<br/>Région : "+alcohol.getRegion()+"</html>", "Suppresion Alcool", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choiceDelete == 0){
                if(typeAlcohol.equals("Wine")){
                    Window.connection.deleteWine(alcohol.getId());
                }
                if(typeAlcohol.equals("Beer")){
                    Window.connection.deleteBeer(alcohol.getId());
                }
                if(typeAlcohol.equals("Alcohol")){
                    Window.connection.deleteStrongAlcohol(alcohol.getId());
                }
                JOptionPane.showMessageDialog(null, "L'alcool : "+alcohol.getName()+" à été supprimer avec succès.", "Delete Succesful", JOptionPane.INFORMATION_MESSAGE);
            }else{
                System.out.println("lol");
            }
        }
    }
}
