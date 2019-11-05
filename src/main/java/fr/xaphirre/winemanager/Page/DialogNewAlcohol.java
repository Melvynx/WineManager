package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.alcoholClass.*;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

public class DialogNewAlcohol extends JDialog {
  //To push
  private Wine wine;
  private Beer beer;
  private StrongAlcohol strongAlcohol;
  //Panel
  private JPanel content = new JPanel();
  private JPanel control;
  private JPanel westColor;
  private JPanel formCenter = new JPanel(new GridLayout(9,1));

  private JLabel title = new JLabel();

  private JRadioButton wineRadio, beerRadio, strongAlcoholRadio;
  private JTextField region, nom;
  private JFormattedTextField age, degree, startMaturity, endMaturity, capacity;
  private String typeAlcohol = "alcool";

  private Color background = ColorPage.COLOR1.getColor();

  private JPanel panType, panMaturityWine;
  private Font fontForm = new Font("Nanum Gothic", Font.PLAIN, 20);
  private Color colorForm = ColorPage.DARKPRIMARY.getColor();
  private JLabel nomLabel, ageLabel, regionLabel,typeLabel, capacityLabel, degreeLabel;
  private JComboBox<String> comboTypeWine, comboTypeBeer;


  public DialogNewAlcohol(JFrame parent, String title, boolean modal) {
    super(parent,title,modal);
    //Settings
    this.setSize(500,620);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    //Initialisation des fonctions
    this.initComponent();
    this.initWestPan();
    this.initControlButton();

    this.setBackground(background);
    content.setBackground(background);

    this.setContentPane(content);

    this.setVisible(true);
  }
  public void initWestPan(){
    westColor = new JPanel();
    westColor.setPreferredSize(new Dimension(110,550));
    westColor.setBackground(ColorPage.PRIMARY.getColor());
    content.add(westColor, BorderLayout.EAST);
  }

  public void initComponent(){
    DecimalFormat format = new DecimalFormat();
    format.setGroupingUsed(false);

    JPanel contentImage = new JPanel();
    JLabel icon = new JLabel(new ImageIcon("images/vin.png"));
    contentImage.add(icon);
    //content.add(contentImage, BorderLayout.WEST);
    title.setText("Création d'un "+typeAlcohol+".");
    title.setFont(new Font("Nanum Gothic", Font.ITALIC, 25));
    title.setHorizontalAlignment(JLabel.CENTER); title.setVerticalAlignment(JLabel.CENTER);

    //Init type radioButton
    JPanel panTypeAlcohol = new JPanel(new FlowLayout(FlowLayout.LEFT));
    wineRadio = new JRadioButton("Vin"); wineRadio.setFont(fontForm); wineRadio.setForeground(colorForm);
    wineRadio.addActionListener(new StateTypeListener());
    strongAlcoholRadio = new JRadioButton("Alcool Fort"); strongAlcoholRadio.setFont(fontForm); strongAlcoholRadio.setForeground(colorForm);
    strongAlcoholRadio.addActionListener(new StateTypeListener());
    strongAlcoholRadio.setSelected(true);
    beerRadio = new JRadioButton("Bière"); beerRadio.setFont(fontForm); beerRadio.setForeground(colorForm);
    beerRadio.addActionListener(new StateTypeListener());
    ButtonGroup bg = new ButtonGroup();
    bg.add(wineRadio);
    bg.add(beerRadio);
    bg.add(strongAlcoholRadio);
    panTypeAlcohol.add(wineRadio);
    panTypeAlcohol.add(beerRadio);
    panTypeAlcohol.add(strongAlcoholRadio);
    JPanel panTypeContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
    Border borderType = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorPage.COLOR1.getColor()), "Choix de l'alcool", 1, 1, new Font("Nanum Gothic", Font.PLAIN, 18), ColorPage.DARKPRIMARY.getColor());

    panTypeContent.setBorder(borderType);
    panTypeAlcohol.setBorder(borderType);
    panTypeContent.add(panTypeAlcohol, BorderLayout.CENTER);



    //Init name
    JPanel panNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
    nom = new JTextField();
    nom.setPreferredSize(new Dimension(150,30));
    nomLabel = new JLabel("Nom du "+typeAlcohol+" : "); nomLabel.setFont(fontForm); nomLabel.setForeground(colorForm);nomLabel.setHorizontalAlignment(JLabel.LEFT);
    panNom.add(nomLabel);
    panNom.add(nom);

    //Init region
    JPanel panRegion = new JPanel(new FlowLayout(FlowLayout.LEFT));
    region = new JTextField();
    region.setPreferredSize(new Dimension(150,30));
    regionLabel = new JLabel("Région du "+typeAlcohol+" : "); regionLabel.setFont(fontForm); regionLabel.setForeground(colorForm);
    panRegion.add(regionLabel);
    panRegion.add(region);

    //Init age
    JPanel panAge = new JPanel(new FlowLayout(FlowLayout.LEFT));
    MaskFormatter maskDate = null;
    try {
      maskDate = new MaskFormatter("####");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    age = new JFormattedTextField(maskDate);
    age.setPreferredSize(new Dimension(50,30));
    ageLabel = new JLabel("Age du "+typeAlcohol+" : "); ageLabel.setFont(fontForm); ageLabel.setForeground(colorForm);
    panAge.add(ageLabel);
    panAge.add(age);

    //Init degree
    JPanel panDegree = new JPanel(new FlowLayout(FlowLayout.LEFT));
    degree = new JFormattedTextField(format);
    degree.setPreferredSize(new Dimension(50,30));
    degreeLabel = new JLabel("Degree d'alcool : "); degreeLabel.setFont(fontForm); degreeLabel.setForeground(colorForm);
    JLabel degreeLabelPercent = new JLabel(" %");degreeLabelPercent.setFont(fontForm); degreeLabelPercent.setForeground(colorForm);
    panDegree.add(degreeLabel);
    panDegree.add(degree);
    panDegree.add(degreeLabelPercent);

    //Init capacity
    JPanel panCapacity = new JPanel(new FlowLayout(FlowLayout.LEFT));
    capacity = new JFormattedTextField(format);
    capacity.setPreferredSize(new Dimension(50,30));
    capacityLabel = new JLabel("Capacity du "+typeAlcohol+" : "); capacityLabel.setFont(fontForm); capacityLabel.setForeground(colorForm);
    JLabel capacityLabelML = new JLabel(" ml"); capacityLabelML.setFont(fontForm); capacityLabelML.setForeground(colorForm);
    panCapacity.add(capacityLabel);
    panCapacity.add(capacity);
    panCapacity.add(capacityLabelML);

    initSpecialForm();
    formCenter.add(title);title.setBackground(background);
    formCenter.add(panTypeAlcohol);panTypeAlcohol.setBackground(background);
    formCenter.add(panNom);panNom.setBackground(background);
    formCenter.add(panRegion);panRegion.setBackground(background);
    formCenter.add(panAge);panAge.setBackground(background);
    formCenter.add(panDegree);panDegree.setBackground(background);
    formCenter.add(panCapacity);panCapacity.setBackground(background);
    formCenter.add(panType);panType.setBackground(background);
    formCenter.add(panMaturityWine);panMaturityWine.setBackground(background);

    formCenter.setBackground(background);
    formCenter.setPreferredSize(new Dimension(380,550));
    content.add(formCenter, BorderLayout.CENTER);

  }
  public void initSpecialForm(){
    //Wine special
    panType = new JPanel(new FlowLayout(FlowLayout.LEFT));
    String[] listTypeWine = {"Blanc", "Rouge", "Rosé"};
    comboTypeWine = new JComboBox<>(listTypeWine);
    typeLabel = new JLabel("Type de vin ");typeLabel.setFont(fontForm); typeLabel.setForeground(colorForm);
    panType.add(typeLabel);
    panType.add(comboTypeWine);
    panType.setVisible(false);
    panMaturityWine = new JPanel(new FlowLayout(FlowLayout.LEFT));
    MaskFormatter maskDate = null;
    try {
      maskDate = new MaskFormatter("####");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    startMaturity = new JFormattedTextField(maskDate); startMaturity.setPreferredSize(new Dimension(50, 30));
    endMaturity = new JFormattedTextField(maskDate); endMaturity.setPreferredSize(new Dimension(50,30));
    JLabel startMaturityLabel = new JLabel("Maturité du 20 entre");startMaturityLabel.setFont(fontForm); startMaturityLabel.setForeground(colorForm);
    JLabel startMaturityLabelAND = new JLabel("et");startMaturityLabelAND.setFont(fontForm); startMaturityLabelAND.setForeground(colorForm);
    panMaturityWine.add(startMaturityLabel);
    panMaturityWine.add(startMaturity);
    panMaturityWine.add(startMaturityLabelAND);
    panMaturityWine.add(endMaturity);
    panMaturityWine.setVisible(false);


    //Beer special
    String[] listTypeBeer = {"Blanche", "Blonde", "Brune", "Rousse"};
    comboTypeBeer = new JComboBox<>(listTypeBeer);
    JLabel typeBeerLabel = new JLabel("Type de bière ");typeBeerLabel.setFont(fontForm); typeBeerLabel.setForeground(colorForm);



  }
  private boolean testValueForm(){
    if (nom.getText().length() > 50 || nom.getText().length() < 2){
      JOptionPane.showMessageDialog(null,"Erreur, le nom doit contenir entre 2 et 50 caractères.", "Error Nom", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (region.getText().length() > 50 || region.getText().length() < 2){
      JOptionPane.showMessageDialog(null,"Erreur, la région doit contenir entre 2 et 50 caractères.", "Error Region", JOptionPane.ERROR_MESSAGE);
      return false;
    }

    Integer ageInteger = 0;
    if (age.isEditValid()){
      try {
        ageInteger = Integer.parseInt(age.getText());
      } catch (NumberFormatException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur, l'anée de création na pas été correctement remplis.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    int yearToday = Calendar.getInstance().get(Calendar.YEAR);
    if (ageInteger > yearToday || ageInteger < 1000){
      JOptionPane.showMessageDialog(null,"Erreur, l'anée de création du vin ne peut pas être plus grand que "+yearToday+" ni plus petit que 1000.", "Error Age", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    Integer degreeInteger = 0;
    if (degree.isEditValid()) {
      try {
        degreeInteger = Integer.parseInt(degree.getText());
      } catch (NumberFormatException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur, le degrée d'alcool na pas été correctement remplis.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    if (degreeInteger < 1 || degreeInteger > 99){
      JOptionPane.showMessageDialog(null,"Erreur, le dégrée d'alcool doit être compris entre 1 et 99.", "Error Degree", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    int capacityInteger = 0;
    if (capacity.isEditValid()) {
      try {
        capacityInteger = Integer.parseInt(capacity.getText());
      } catch (NumberFormatException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur, la capacity na pas été correctement remplis.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    System.out.println("CAPACITY : "+capacityInteger);
    if (capacityInteger < 10 || capacityInteger > 100000){
      JOptionPane.showMessageDialog(null,"Erreur, la capacité de votre bouteille doit être compris entre 10 et 100'000 ml..", "Error Degree", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (startMaturity.isEditValid() && endMaturity.isEditValid()){
      Integer startMaturityInteger = null;
      Integer endMaturityInteger = null;
      try{
        startMaturityInteger = Integer.parseInt(startMaturity.getText());
        endMaturityInteger = Integer.parseInt(endMaturity.getText());
      } catch (NumberFormatException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,"Erreur, la maturité na pas été correctement remplis.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      if (startMaturityInteger < 1950){
        JOptionPane.showMessageDialog(null,"Erreur, la maturité doit être supérieur à 1950.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      if (startMaturityInteger > endMaturityInteger){
        JOptionPane.showMessageDialog(null,"Erreur, le début de maturité ne peut pas être plus grand que la fin.", "Error Maturity", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    return true;
  }
  public void initControlButton() {
    control = new JPanel();
    control.setBackground(ColorPage.PRIMARY.getColor());
    control.setPreferredSize(new Dimension(500,100));
    JButton okBouton = new JButton("Envoyé");
    JButton cancelButton = new JButton("Annuler");


    okBouton.addActionListener(e -> {
      nom.getText();
      region.getText();
      age.getText();
      degree.getText();
      System.out.println(comboTypeWine.getSelectedItem());
      boolean valueIsValidate = testValueForm();
      if (!valueIsValidate){
        return;
      }

      if (typeAlcohol.equals("Wine")){
        wine = new Wine(nom.getText(), region.getText(), Integer.parseInt(age.getText()), Integer.parseInt(degree.getText()), Integer.parseInt(capacity.getText()), getTypeWine(), Integer.parseInt(startMaturity.getText()), Integer.parseInt(endMaturity.getText()));
        System.out.println("Wine create[\n" + wine.toString()+"]");
      } else if (typeAlcohol.equals("Beer")){
        beer = new Beer(nom.getText(), region.getText(), Integer.parseInt(age.getText()), Integer.parseInt(degree.getText()), Integer.parseInt(capacity.getText()), getTypeBeer());
        System.out.println("Beer create[\n" + beer.toString()+"]");
      } else {
        strongAlcohol = new StrongAlcohol(nom.getText(), region.getText(), Integer.parseInt(age.getText()), Integer.parseInt(degree.getText()), Integer.parseInt(capacity.getText()));
        System.out.println("ALC create[\n" + strongAlcohol.toString()+"]");
      }

      this.setVisible(false);
    });
    cancelButton.addActionListener(e -> {
      typeAlcohol = "Nothing";
      this.setVisible(false);
    });

    control.add(okBouton);
    control.add(cancelButton);
    content.add(control, BorderLayout.SOUTH);
  }
  public class StateTypeListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      String typeAlcoolSelected = "";
      String typeAlcoolSelectedTitle = "";
      if (wineRadio.isSelected()){
        panType.removeAll();
        panType.add(typeLabel);
        panType.add(comboTypeWine);
        panType.setVisible(true);

        panMaturityWine.setVisible(true);
        typeAlcoolSelected = "du vin";
        typeAlcoolSelectedTitle = "d'un vin";
        typeAlcohol = "Wine";
      }
      if (beerRadio.isSelected()){
        panType.removeAll();
        panType.add(typeLabel);
        panType.add(comboTypeBeer);
        panType.setVisible(true);

        panMaturityWine.setVisible(false);
        typeAlcoolSelected = "de la bière";
        typeAlcoolSelectedTitle = "d'une bière";
        typeAlcohol = "Beer";

      }
      if (strongAlcoholRadio.isSelected()){
        panType.setVisible(false);
        panMaturityWine.setVisible(false);
        typeAlcoolSelected = "de l'alcool";
        typeAlcoolSelectedTitle = "d'un alcool";
        typeAlcohol = "Strong Alcohol";

      }
      nomLabel.setText("Nom "+typeAlcoolSelected+" :");
      regionLabel.setText("Région "+typeAlcoolSelected+" :");
      ageLabel.setText("Age "+typeAlcoolSelected+" :");
      capacityLabel.setText("Capacity "+typeAlcoolSelected);
      typeLabel.setText("Type "+typeAlcoolSelected);
      title.setText("Création "+typeAlcoolSelectedTitle+".");
    }
  }
  public String getTypeAlcohol(){
    return typeAlcohol;
  }
  public Alcohol getAlcohol(int i){
    switch (i){
      case 0:
        return wine;
      case 1:
        return beer;
      default:
        return strongAlcohol;
    }
  }
  private TypeWine getTypeWine() {
    String selectWine = (String)comboTypeWine.getSelectedItem();
    TypeWine typeWine = TypeWine.BLANC;
    switch (selectWine){
      case "Blanc":
        typeWine = TypeWine.BLANC;
        break;
      case "Rouge":
        typeWine = TypeWine.ROUGE;
        break;
      case "Rosé":
        typeWine = TypeWine.ROSE;
        break;
    }
    return typeWine;
  }
  private TypeBeer getTypeBeer() {
    String selectBeer = (String)comboTypeBeer.getSelectedItem();
    TypeBeer typeBeer = TypeBeer.BLANCHE;
    switch (selectBeer){
      case "Blanche":
        typeBeer = TypeBeer.BLANCHE;
        break;
      case "Blonde":
        typeBeer = TypeBeer.BLONDE;
        break;
      case "Brune":
        typeBeer = TypeBeer.BRUNE;
        break;
      case "Rousse":
        typeBeer = TypeBeer.ROUSSE;
    }
    return typeBeer;
  }
}
