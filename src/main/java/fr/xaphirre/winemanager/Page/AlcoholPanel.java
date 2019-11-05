package fr.xaphirre.winemanager.Page;

import com.sun.javafx.scene.layout.region.Margins;
import fr.xaphirre.winemanager.alcoholClass.*;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
    private Color background = ColorPage.COLOR2.getColor();
    private Color textColor = ColorPage.DARKPRIMARY.getColor();

    public AlcoholPanel(Alcohol alcohol){
        content.setLayout(new BorderLayout());
        content.setPreferredSize(new Dimension(750,125));
        content.setBackground(background);
        this.initValue(alcohol);
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
            JLabel maturityValue = new JLabel("");
            maturity.setFont(font);
            maturity.setForeground(textColor);
            type.setFont(font);
            type.setForeground(textColor);
            contentRight.add(type);
            contentRight.add(maturity);
        }
        if(typeAlcohol.equals("Beer")){

            contentRight.setLayout(new GridLayout(3,1));
            System.out.println("Beer type "+nameAlcohol);
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
        Font fontName = new Font("Nanum Gothic", Font.ITALIC, 22);

        JLabel nom = new JLabel(nameAlcohol);
        JLabel region = new JLabel("Région : "+regionAlcohol);
        JLabel age = new JLabel("Date de création : "+ageAlcohol);
        JLabel degree = new JLabel("Degrée d'alcool : "+degreeOfAlcohol+"%");
        JLabel capacity = new JLabel("Capacité (ml) : "+capacityAlcohol+"ml");

        nom.setFont(fontName);
        nom.setHorizontalAlignment(JLabel.LEFT);
        nom.setForeground(textColor);

        region.setFont(font);
        region.setForeground(textColor);


        age.setFont(font);
        age.setForeground(textColor);

        degree.setFont(font);
        degree.setForeground(textColor);

        capacity.setFont(font);
        capacity.setForeground(textColor);

        content.add(nom, BorderLayout.NORTH);
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
    private void initValue(Alcohol alcohol) {
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

}
