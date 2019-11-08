package fr.xaphirre.winemanager.Page;

import fr.xaphirre.winemanager.ConnectionSQL;
import fr.xaphirre.winemanager.Navigation;
import fr.xaphirre.winemanager.Window;
import fr.xaphirre.winemanager.alcoholClass.Alcohol;
import fr.xaphirre.winemanager.alcoholClass.Beer;
import fr.xaphirre.winemanager.alcoholClass.StrongAlcohol;
import fr.xaphirre.winemanager.alcoholClass.Wine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListAlcoholPage extends JPanel {
  private Color backGround = ColorPage.PRIMARY.getColor();
  private Navigation navigation;
  private String dbPath = "alcohol.db";
  //Variable for get Alcohol display and set Alcohol.
  private static Class alcoholDisplay = Alcohol.class;
  private List<Alcohol> listAlcoholDisplay = new LinkedList<>();
  //Button for edit current alcohol display
  private JRadioButton listAllAlcohol, listWine, listBeer, listStrongAlcohol;
  private JTextField researchAlcohol;
  private String researchString = "";
  //Set JPanell and ScrollPan
  private JPanel contentBody = new JPanel();
  private JPanel scrollContent;

  private JScrollPane scrollPane;

  public ListAlcoholPage(Navigation navigation){
    this.navigation = navigation;
    //Set settings
    this.setLayout(new BorderLayout());
    this.setBackground(backGround);
    //Initialisation de toutes les parties
    this.initHead();
    this.initBody();
    this.initFooter();
    //Listener size pan changead.
    this.addComponentListener(new ResizeListener());

    Window.connection.addSubscriber(this::generateScrollPan);
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
  private void initBody() {
    initToolBar();

    scrollContent = new JPanel(new GridLayout(listAlcoholDisplay.size(), 1));
    scrollPane = new JScrollPane(scrollContent);

    generateScrollPan();

    scrollPane.setPreferredSize(new Dimension(900, 525));
    contentBody.add(scrollPane, BorderLayout.CENTER);
    contentBody.setBackground(backGround);
    this.add(contentBody, BorderLayout.CENTER);
    this.repaint();
  }
  private void generateScrollPan(){

    if (listAlcoholDisplay.size() > 0)
      listAlcoholDisplay.clear();
    //Get correct list
    if (alcoholDisplay == Alcohol.class){
      try {
        listAlcoholDisplay = Window.connection.getAllAlcohol();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (alcoholDisplay == Wine.class){
      try {
        listAlcoholDisplay.addAll(Window.connection.getWines());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if(alcoholDisplay == Beer.class){
      try {
        listAlcoholDisplay.addAll(Window.connection.getBeer());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (alcoholDisplay == StrongAlcohol.class){
      try {
        listAlcoholDisplay.addAll(Window.connection.getStrongAlcohol());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    //Filter list IF reasearchString is field
    if (researchString.length() > 1){
      Stream streamAlcoholDisplay = listAlcoholDisplay.stream().filter(alcohol -> alcohol.getName().contains(researchString) || alcohol.getRegion().contains(researchString));
      listAlcoholDisplay = (List<Alcohol>) streamAlcoholDisplay.collect(Collectors.toList());
    }
    //Set content of scrollContent
    scrollContent.removeAll();
    for(Alcohol alcohol : listAlcoholDisplay){
      scrollContent.add(new AlcoholPanel(alcohol));
    }

    System.out.println();
    scrollContent.revalidate();
    scrollContent.repaint();
    scrollPane.revalidate();
    scrollPane.repaint();

  }
  private void initToolBar(){
    //Style
    Font fontToolBar = new Font("Nanum Gothic", Font.PLAIN, 20);
    Color colorToolBar = ColorPage.COLOR1.getColor();
    //Create radioButton
    listAllAlcohol = new JRadioButton("Tout les Alcool");listAllAlcohol.setForeground(colorToolBar);listAllAlcohol.setFont(fontToolBar);
    listAllAlcohol.setSelected(true);
    listWine = new JRadioButton("Vins");                 listWine.setForeground(colorToolBar);listWine.setFont(fontToolBar);
    listBeer = new JRadioButton("Bières");               listBeer.setForeground(colorToolBar);listBeer.setFont(fontToolBar);
    listStrongAlcohol = new JRadioButton("Alcool fort"); listStrongAlcohol.setForeground(colorToolBar);listStrongAlcohol.setFont(fontToolBar);
    listAllAlcohol.addActionListener(new ListenerResearchAlcohol());
    listWine.addActionListener(new ListenerResearchAlcohol());
    listBeer.addActionListener(new ListenerResearchAlcohol());
    listStrongAlcohol.addActionListener(new ListenerResearchAlcohol());
    //Set group and panel Button
    ButtonGroup groupListAlcohol = new ButtonGroup();
    JPanel panListAlcohol = new JPanel();
    groupListAlcohol.add(listAllAlcohol);
    groupListAlcohol.add(listWine);
    groupListAlcohol.add(listBeer);
    groupListAlcohol.add(listStrongAlcohol);
    panListAlcohol.add(listAllAlcohol);
    panListAlcohol.add(listWine);
    panListAlcohol.add(listBeer);
    panListAlcohol.add(listStrongAlcohol);
    panListAlcohol.setBackground(backGround);
    //Create JTextField
    researchAlcohol = new JTextField();researchAlcohol.setForeground(ColorPage.PRIMARY.getColor());researchAlcohol.setFont(fontToolBar);
    //KeyListener
    researchAlcohol.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) { }
      public void keyPressed(KeyEvent e) { }
      public void keyReleased(KeyEvent e) {
        researchString = researchAlcohol.getText();
        generateScrollPan();
      }
    });

    researchAlcohol.setPreferredSize(new Dimension(200, 30));
    //Create JPanelToolBar
    JPanel panToolBar = new JPanel();
    panToolBar.add(panListAlcohol);
    panToolBar.add(researchAlcohol);
    panToolBar.setBackground(backGround);

    contentBody.add(panToolBar, BorderLayout.NORTH);


  }
  //Listener size panel
  class ResizeListener extends ComponentAdapter {
    public void componentResized(ComponentEvent e) {
      resizeComponent();
    }
  }
  private void resizeComponent(){
    scrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - 175));
  }
  //Set alcohol search when select button
  private class ListenerResearchAlcohol implements ActionListener{
    public void actionPerformed(ActionEvent e) {

      if(listAllAlcohol.isSelected()){
        alcoholDisplay = Alcohol.class;
      }
      if(listWine.isSelected()){
        alcoholDisplay = Wine.class;
      }
      if(listBeer.isSelected()){
        alcoholDisplay = Beer.class;
      }
      if(listStrongAlcohol.isSelected()){
        alcoholDisplay = StrongAlcohol.class;
      }
      generateScrollPan();

    }
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
    //Instanciment button et label pour aller a home
    ImageIcon iconList = new ImageIcon("images/home.png");
    Image scaleList = iconList.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
    JButton buttonList = new JButton((new ImageIcon(scaleList)));
    buttonList.setSize(new Dimension(30,30));
    buttonList.addActionListener(new ButtonHomeListener());
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

  private class ButtonHomeListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      navigation.setPage(0);
    }
  }
  private static class ButtonAddListener implements ActionListener {
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
    }
  }
}
