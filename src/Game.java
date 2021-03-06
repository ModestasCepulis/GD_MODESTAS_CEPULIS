//For JFrame creating reference I was looking at this video https://www.youtube.com/watch?v=RcvABhflOkI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.security.Key;
import java.util.Random;

/**
 * This class creates the main GUI and the main game program.
 */

public class Game extends JFrame{


    //Instantiates the classes
    static Player thePlayer;
    static Scenes theScenes;

    Container container;

    //Creates Jpanels for the different panels that are needed for the GUI
    JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, aboutTheGameButtonPanel,
            exitGameButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, exitConfirmationPanelYes, exitConfirmationPanelNo,
            changeTextColourPanel, changeBackgroundColourPanel, goBackPanel, ContinueButtonPanel, belowTextPanel;

    //Mainly player setup labels
    JLabel titleNameLabel, hpLabel, hpLabelNumber, itemLabel;
    static JLabel itemLabelName;

    //Creates JButtons that are needed for the GUI
    JButton startButton, optionsButton, aboutTheGameButton, exitGameButton,
            choice1, choice2, choice3, choice4, exitGameButtonYes, exitGameButtonNo, continueButton,
            changeTextColourButton, changeBackgroundColourButton, goBackButton;

    //Main text areas used for the gui
    JTextArea mainTextArea, belowTextArea;

    //==========Cave image variables==========
    ImageIcon caveImage;
    JLabel caveLabel;
    JPanel cavePanel, caveButtonPanel;
    JButton caveContinueButton;

    caveContinueHandler caveContinueHdrl = new caveContinueHandler();

    //==========Market image variables=========
    ImageIcon marketImage;
    JLabel marketLabel;
    JPanel marketPanel, marketButtonPanel;
    JButton marketContinueButton;


    //=========Health items============
    int appleGivenHealth;
    static int appleItemCount=1;

    //=========shop==================

    int firstItemRandomPrice;
    int secondItemRandomPrice;
    int thirdItemRandomPrice;
    int fourthItemRandomPrice;

    marketContinueHandler marketContinueHdrl = new marketContinueHandler();//Action handler for continue button

    StartGameHandler startGameHdlr = new StartGameHandler();//Action handler for game starting button
    OptionsHandler OptionsHdlr = new OptionsHandler(); //Action handler for options button
    AboutGameHandler aboutGameHdlr = new AboutGameHandler(); //Action handler for about button
    ChoiceHandler choiceHdlr = new ChoiceHandler(); //Action handler for choices
    ExitGameHandler exitGameHdlr = new ExitGameHandler(); //Action handler for exit game button
    ExitGameHandlerButtonYes exitGameButtonYesHdlr = new ExitGameHandlerButtonYes(); //Action handler for exit game>yes button
    ExitGameHandlerButtonNo exitGameButtonNoHdlr = new ExitGameHandlerButtonNo(); //Action handler for exit game>no button
    changeTextColourHandler textColourHdrl = new changeTextColourHandler(); //Action handler for text colour changer
    changeBackgroundColourHandler backgroundColourHdrl = new changeBackgroundColourHandler(); //Action handler for background colour changer
    goBackHandler goBackHdlr = new goBackHandler(); //Action handler for going back button in the options menu
    goBackHandlerAboutGame goBackHdrlAboutGame = new goBackHandlerAboutGame(); //Action handler for going back button in the about section
    continueButtonHandler continueButtonHdrl = new continueButtonHandler(); //Action handler for continue button in the images section

    //Main fonts used for the gui texts

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font mainFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font mainTextUsedForNarrative = new Font("Comic Sans MS", Font.PLAIN, 28);
    Font optionsFont = new Font("Arial Black", Font.PLAIN, 30);
    Font exitFont = new Font("Arial", Font.PLAIN, 45);


    int textColourCount = 1, backgroundColourCount = 1; //These are the counters that help to change colours
    int enemyHealth = 20; //sets the enemy health
    int enemyAttack; //makes a variable for enemyattack
    int candleAdditionalDamage, daggerAdditionalDamage; //makes variables for additional attack added to different items

    String position; // the position which the Choice handler use for different choices(different scenes)

    boolean candleEquipped = false, keyAcquired = false, daggerEquipped=false; //sets the boolean values for different items.

    static String playerUsername;

    public static void main(String[] args) {

        creatingAFile();
        readingAFile();
        //Instantiates different classes and launches the actual game
        thePlayer = new Player();
        theScenes = new Scenes();
        new Game();
    }

    public Game() {

        getContentPane().setBackground(Color.DARK_GRAY);//changes the gui background colour to dark gray
        mainGuiDesign();//launches the method which creates the gui


    }

    public void mainGuiDesign()
    {

        setResizable(false); //this sets that the screen size cannot be changed.
        setSize(1200, 800);//setts the size of the GUI
        //This sets the JFrame to be always in the middle regardless of the screen size
        //Code taken from:
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);//sets the layout to none
        container = getContentPane(); //sets the container to get the content pane

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 1000, 150);
        titleNamePanel.setBackground(Color.darkGray);

        titleNameLabel = new JLabel("Begin your adventure");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        //===========CALLS PANEL METHODS============
        startButtonPanel();
        optionsButtonPanel();
        aboutTheGameButtonPanel();
        exitGameButtonPanel();

        //===========CALLS BUTTON METHODS===========
        startGameButton();
        optionsButton();
        aboutTheGameButton();
        exitGameButton();
        exitGameButtonYes();
        exitGameButtonNo();
        continueButton();
        CaveContinueButton();
        MarketContinueButton();
        changeBackgroundColourButton();
        changeTextColourButton();

        //This adds the labels and buttons to he panels
        titleNamePanel.add(titleNameLabel);
        optionsButtonPanel.add(optionsButton);
        startButtonPanel.add(startButton);
        aboutTheGameButtonPanel.add(aboutTheGameButton);
        exitGameButtonPanel.add(exitGameButton);

        //This adds all the panels to the container, etc - the main Pane.
        container.add(titleNamePanel);
        container.add(optionsButtonPanel);
        container.add(startButtonPanel);
        container.add(aboutTheGameButtonPanel);
        container.add(exitGameButtonPanel);

        //Sets the JFrame to visible.
        setVisible(true);
    }

    public static void creatingAFile()
    {
        //this reads the user input for the username and saves it in a file
        //Reference taken from:
        //https://www.youtube.com/watch?v=Bws9aQuAcdg
        playerUsername = JOptionPane.showInputDialog("Please enter your characters username: ");
        CreatingFile creatingFile = new CreatingFile();
        creatingFile.openFile();
        creatingFile.addRecords();
        creatingFile.closeFile();
    }

    public static void readingAFile()
    {
        //this reads the string variable that the user wrote and puts into the market dialogue.
        ReadingFile readingFile = new ReadingFile();
        readingFile.openFile();
        readingFile.readFile();
        readingFile.closeFile();
    }

    public void playerSetup() {    //===================PLAYER SETUP=====================
        itemLabelName.setText(thePlayer.getPlayerItem());
        hpLabelNumber.setText("" + thePlayer.getPlayerHP());
        thePlayer.setPlayerMoney(100);
        firstLaunchScene();
    }

    public void itemsEquipped()    //==================INVENTORY SETUP==================
    {
        candleAdditionalDamage=1;
        daggerAdditionalDamage=2;
        //checks if the items are equipped and adds the additional damage to the characters total damage
        if(candleEquipped)
        {
            thePlayer.setPlayerAttack(thePlayer.playerAttack + candleAdditionalDamage);
        }

        if(daggerEquipped)
        {
            thePlayer.setPlayerAttack(thePlayer.playerAttack + daggerAdditionalDamage);
            thePlayer.setPlayerItem("Dagger");
            //this actually changes the current text to whatever we have it set with .setPlayerItem
            itemLabelName.setText(thePlayer.getPlayerItem());
        }
    }
    //===================BUTTON METHODS===================
    public void startGameButton() {
        //===================START THE GAME BUTTON===================
        startButton = new JButton("Start the game");
        startButton.setBackground(Color.darkGray);
        startButton.setForeground(Color.white);
        startButton.setFont(mainFont);
        //This creates a button with an empty border.
        //Code was taken from:
        //https://alvinalexander.com/source-code/java/java-jbutton-create-jbutton-no-border-borderfactorycreateemptyborder
        startButton.setBorder(BorderFactory.createEmptyBorder());
        //This adds an action listener to the button which allows the button to do somethnig
        startButton.addActionListener(startGameHdlr);
        //Removes the line or circle whenever a button is pressed
        //Code taken from:
        //https://stackoverflow.com/questions/16157120/setfocuspainted-used-for-jbuttons
        startButton.setFocusPainted(false);
    }
    public void optionsButton() {
        //===================OPTIONS BUTTON===================
        optionsButton = new JButton("Options");
        optionsButton.setBackground(Color.darkGray);
        optionsButton.setForeground(Color.white);
        optionsButton.setFont(mainFont);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.addActionListener(OptionsHdlr);
        optionsButton.setFocusPainted(false);
    }
    public void aboutTheGameButton() {
        //===================ABOUT THE GAME BUTTON===================
        aboutTheGameButton = new JButton("About the game");
        aboutTheGameButton.setBackground(Color.darkGray);
        aboutTheGameButton.setForeground(Color.white);
        aboutTheGameButton.setFont(mainFont);
        aboutTheGameButton.setBorder(BorderFactory.createEmptyBorder());
        aboutTheGameButton.addActionListener(aboutGameHdlr);
        aboutTheGameButton.setFocusPainted(false);
    }

    public void exitGameButton() {
        //===================EXIT THE GAME BUTTON===================
        exitGameButton = new JButton("Exit");
        exitGameButton.setBackground(Color.darkGray);
        exitGameButton.setForeground(Color.white);
        exitGameButton.setFont(mainFont);
        exitGameButton.setBorder(BorderFactory.createEmptyBorder());
        exitGameButton.setFocusPainted(false);
        exitGameButton.addActionListener(exitGameHdlr);
    }

    public void exitGameButtonYes() {
        exitGameButtonYes = new JButton("Yes");
        exitGameButtonYes.setBackground(Color.darkGray);
        exitGameButtonYes.setForeground(Color.white);
        exitGameButtonYes.setFont(exitFont);
        exitGameButtonYes.setBorder(BorderFactory.createEmptyBorder());
        exitGameButtonYes.setFocusPainted(false);
        exitGameButtonYes.addActionListener(exitGameButtonYesHdlr);
    }

    public void exitGameButtonNo() {
        exitGameButtonNo = new JButton("No");
        exitGameButtonNo.setBackground(Color.darkGray);
        exitGameButtonNo.setForeground(Color.white);
        exitGameButtonNo.setFont(exitFont);
        exitGameButtonNo.setBorder(BorderFactory.createEmptyBorder());
        exitGameButtonNo.setFocusPainted(false);
        exitGameButtonNo.addActionListener(exitGameButtonNoHdlr);
    }

    public void continueButton() {
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(mainFont);
        continueButton.setBorder(BorderFactory.createEmptyBorder());
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(continueButtonHdrl);
    }

    public void CaveContinueButton()
    {
        caveContinueButton = new JButton("Continue");
        caveContinueButton.setBackground(Color.black);
        caveContinueButton.setForeground(Color.white);
        caveContinueButton.setFont(mainFont);
        caveContinueButton.setBorder(BorderFactory.createEmptyBorder());
        caveContinueButton.setFocusPainted(false);
        caveContinueButton.addActionListener(caveContinueHdrl);
    }

    public void MarketContinueButton()
    {
        marketContinueButton = new JButton("Continue");
        marketContinueButton.setBackground(Color.black);
        marketContinueButton.setForeground(Color.white);
        marketContinueButton.setFont(mainFont);
        marketContinueButton.setBorder(BorderFactory.createEmptyBorder());
        marketContinueButton.setFocusPainted(false);
        marketContinueButton.addActionListener(marketContinueHdrl);
    }

    public void changeTextColourButton() {
        changeTextColourButton = new JButton("   Change    ");
        changeTextColourButton.setBackground(Color.black);
        changeTextColourButton.setForeground(Color.white);
        changeTextColourButton.setFont(optionsFont);
        changeTextColourButton.setBorder(BorderFactory.createEmptyBorder());
        changeTextColourButton.setFocusPainted(false);

        changeTextColourButton.addActionListener(textColourHdrl);
    }

    public void changeBackgroundColourButton() {
        changeBackgroundColourButton = new JButton("   Change    ");
        changeBackgroundColourButton.setBackground(Color.black);
        changeBackgroundColourButton.setForeground(Color.white);
        changeBackgroundColourButton.setFont(optionsFont);
        changeBackgroundColourButton.setBorder(BorderFactory.createEmptyBorder());
        changeBackgroundColourButton.setFocusPainted(false);
        changeBackgroundColourButton.addActionListener(backgroundColourHdrl);
    }


    //========================PANEL METHODS===================
    public void startButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500, 300, 200, 50);
        startButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void optionsButtonPanel() {
        optionsButtonPanel = new JPanel();
        optionsButtonPanel.setBounds(500, 350, 200, 50);
        optionsButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void aboutTheGameButtonPanel() {
        aboutTheGameButtonPanel = new JPanel();
        aboutTheGameButtonPanel.setBounds(500, 400, 200, 50);
        aboutTheGameButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void exitGameButtonPanel() {
        exitGameButtonPanel = new JPanel();
        exitGameButtonPanel.setBounds(500, 450, 200, 50);
        exitGameButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void mainTextPanel() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 1000, 250);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);
    }

    public void mainTextArea() {
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 1000, 250);
        mainTextArea.setBackground(Color.darkGray);
        container.setForeground(Color.white);
        mainTextArea.setFont(mainFont);
        //This automatically wraps the text to the next line
        //Code taken from:
        //https://stackoverflow.com/questions/7861724/is-there-a-word-wrap-property-for-jlabel
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }


    //====================DIFFERENT SCENES==================

    public void firstScene() {
        disableSomePanels(); //disables some of the panels that it needs to disable to show the other panels
        mainTextPanel();
        mainTextArea();

        thePlayer.setPlayerHP(thePlayer.playerHP = 25);
        thePlayer.setPlayerItem("Fists");

        mainTextArea.setForeground(Color.white);
        mainTextPanel.setBackground(Color.darkGray);
        mainTextPanel.setBounds(100,100,1000,200);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 350, 500, 150);
        choiceButtonPanel.setBackground(Color.darkGray);
        //new GridLayout allows to change the layout of the buttons
        //code reference taken from:
        //https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        container.add(choiceButtonPanel);

        //Creates the choice buttons
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.darkGray);
        choice1.setForeground(Color.white);
        choice1.setFont(mainFont);
        choiceButtonPanel.add(choice1);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHdlr);
        choice1.setActionCommand("c1");

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.darkGray);
        choice2.setForeground(Color.white);
        choice2.setFont(mainFont);
        choiceButtonPanel.add(choice2);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHdlr);
        choice2.setActionCommand("c2");

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.darkGray);
        choice3.setForeground(Color.white);
        choice3.setFont(mainFont);
        choiceButtonPanel.add(choice3);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHdlr);
        choice3.setActionCommand("c3");

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.darkGray);
        choice4.setForeground(Color.white);
        choice4.setFont(mainFont);
        choiceButtonPanel.add(choice4);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHdlr);
        choice4.setActionCommand("c4");

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 1000, 50);
        playerPanel.setBackground(Color.darkGray);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);

        hpLabel = new JLabel("Health: ");
        hpLabel.setFont(mainFont);
        hpLabel.setForeground(Color.WHITE);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(mainFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        itemLabel = new JLabel("Item: ");
        itemLabel.setFont(mainFont);
        itemLabel.setForeground(Color.white);
        playerPanel.add(itemLabel);

        itemLabelName = new JLabel();
        itemLabelName.setFont(mainFont);
        itemLabelName.setForeground(Color.white);
        playerPanel.add(itemLabelName);

        playerSetup();
    }

    public void afterStartButtonScene() {
        setResizable(false);
        disableSomePanels();
        mainTextPanel();
        mainTextArea();

        mainTextPanel.setBounds(70, 150, 1000, 290);
        mainTextPanel.setBackground(Color.darkGray);

        mainTextArea.setFont(mainTextUsedForNarrative);
        mainTextArea.setText("It seems that it was a long time ago when you felt like this... " +
                "\n\n\tBut is it a good feeling? You might not know... at least for now. \n\n\n\n\t\tYou open your eyes.");
        mainTextArea.setForeground(Color.white);
        container.add(mainTextPanel);

        ContinueButtonPanel = new JPanel();
        ContinueButtonPanel.setBounds(470, 480, 200, 50);
        ContinueButtonPanel.setBackground(Color.black);
        ContinueButtonPanel.add(continueButton);
        container.add( ContinueButtonPanel);


    }

    public void afterAboutGameButtonScene() {
        disableSomePanels();
        mainTextPanel();
        mainTextArea();

        mainTextArea.setText("What is this game about?");
        mainTextArea.setFont(exitFont);
        mainTextArea.setBackground(Color.darkGray);
        mainTextArea.setForeground(Color.white);
        mainTextPanel.setBounds(100,100,1000,100);
        mainTextPanel.setBackground(Color.darkGray);

        belowTextArea = new JTextArea();
        belowTextArea.setBounds(100, 50, 1000, 150);
        belowTextArea.setBackground(Color.darkGray);
        container.setForeground(Color.white);
        belowTextArea.setFont(mainFont);
        //This automatically wraps the text to the next line
        //Code taken from:
        //https://stackoverflow.com/questions/7861724/is-there-a-word-wrap-property-for-jlabel
        belowTextArea.setLineWrap(true);
        belowTextArea.setText("It is a text based game that challenges you to make the right decisions to survive in a harsh environment." +
                "\nOn your way, you have to defend yourself against different enemies, complete various quests and collect items or weapons.");
        belowTextArea.setFont(mainFont);
        belowTextArea.setBackground(Color.darkGray);
        belowTextArea.setForeground(Color.white);

        belowTextPanel = new JPanel();
        belowTextPanel.setBounds(100, 250, 1000, 250);
        belowTextPanel.setBackground(Color.darkGray);
        belowTextPanel.add(belowTextArea);
        container.add(belowTextPanel);

        goBackButton = new JButton();

        goBackPanel = new JPanel();
        goBackPanel.setBackground(Color.black);
        goBackPanel.setBounds(425,520,300,50);

        goBackButton = new JButton("Back to Menu");
        goBackButton.setBorder(BorderFactory.createEmptyBorder());
        goBackButton.setBackground(Color.black);
        goBackButton.setForeground(Color.white);
        goBackButton.setFont(mainFont);
        goBackButton.setFocusPainted(false);
        goBackButton.addActionListener(goBackHdrlAboutGame);

        goBackPanel.add(goBackButton);
        container.add(goBackPanel);


    }

    public void afterOptionsButtonScene() {
        disableSomePanels();
        mainTextPanel();
        mainTextArea();

        mainTextPanel.setBounds(100, 100, 520, 350);
        mainTextArea.setFont(optionsFont);
        mainTextArea.setText("\tOptions\n\n\n\tChange text Colour\n\n\n\tChange background color");
        mainTextArea.setForeground(Color.white);
        mainTextPanel.setBackground(Color.darkGray);
        mainTextPanel.setForeground(Color.WHITE);

        changeTextColourPanel = new JPanel();
        changeTextColourPanel.setBounds(700, 240, 150, 50);
        changeTextColourPanel.setFont(mainFont);
        changeTextColourPanel.setBackground(Color.black);
        changeTextColourPanel.add(changeTextColourButton);

        changeBackgroundColourPanel = new JPanel();
        changeBackgroundColourPanel.setBounds(700, 350, 150, 50);
        changeBackgroundColourPanel.setFont(mainFont);
        changeBackgroundColourPanel.setBackground(Color.black);
        changeBackgroundColourPanel.add(changeBackgroundColourButton);

        container.add(changeTextColourPanel);
        container.add(changeBackgroundColourPanel);

        goBackButton = new JButton();

        goBackPanel = new JPanel();
        goBackPanel.setBackground(Color.black);
        goBackPanel.setBounds(425,500,300,50);

        goBackButton = new JButton("Back to Menu");
        goBackButton.setBorder(BorderFactory.createEmptyBorder());
        goBackButton.setBackground(Color.black);
        goBackButton.setForeground(Color.white);
        goBackButton.setFont(mainFont);
        goBackButton.setFocusPainted(false);
        goBackButton.addActionListener(goBackHdlr);

        goBackPanel.add(goBackButton);
        container.add(goBackPanel);

    }

    public void afterExitButtonScene() {
        disableSomePanels();
        mainTextPanel();
        mainTextArea();

        mainTextArea.setText("Are you sure you want to exit the game?");
        mainTextArea.setFont(exitFont);
        mainTextArea.setBackground(Color.darkGray);
        mainTextArea.setForeground(Color.white);
        mainTextPanel.setBackground(Color.darkGray);

        exitConfirmationPanelYes = new JPanel();
        exitConfirmationPanelYes.setBounds(300, 370, 150, 80);
        exitConfirmationPanelYes.setFont(titleFont);
        exitConfirmationPanelYes.setBackground(Color.darkGray);
        exitConfirmationPanelYes.add(exitGameButtonYes);

        exitConfirmationPanelNo = new JPanel();
        exitConfirmationPanelNo.setBounds(700, 370, 150, 80);
        exitConfirmationPanelNo.setFont(titleFont);
        exitConfirmationPanelNo.setBackground(Color.darkGray);
        exitConfirmationPanelNo.add(exitGameButtonNo);

        container.add(exitConfirmationPanelYes);
        container.add(exitConfirmationPanelNo);
    }

    public void afterFirstSceneContinue()
    {
        mainTextPanel.setVisible(false);
        ContinueButtonPanel.setVisible(false);

        caveImage = new ImageIcon(getClass().getResource("Images/CavePicture.gif"));
        caveLabel = new JLabel(caveImage);

        cavePanel = new JPanel();
        cavePanel.setBounds(100, 100, 950, 500);
        cavePanel.setBackground(Color.black);
        cavePanel.add(caveLabel);

        container.add(cavePanel);

        caveButtonPanel = new JPanel();
        caveButtonPanel.setBounds(470, 620, 200, 50);
        caveButtonPanel.setBackground(Color.black);
        caveButtonPanel.add(caveContinueButton);
        container.add(caveButtonPanel);

    }

    public void marketScene()
    {
        playerPanel.setVisible(false);
        mainTextPanel.setVisible(false);
        ContinueButtonPanel.setVisible(false);
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);

        marketImage = new ImageIcon(getClass().getResource("Images/marketImage.gif"));
        marketLabel = new JLabel(marketImage);

        marketPanel = new JPanel();
        marketPanel.setBounds(200, 100, 750, 450);
        marketPanel.setBackground(Color.black);
        marketPanel.add(marketLabel);

        container.add(marketPanel);

        marketButtonPanel = new JPanel();
        marketButtonPanel.setBounds(470, 620, 200, 50);
        marketButtonPanel.setBackground(Color.black);
        marketButtonPanel.add(marketContinueButton);
        container.add(marketButtonPanel);
    }


    public void mainMenuGUIChangedColors()
    {
       mainGuiDesign();
    }

    //=================GAME SCENES========================
    public void firstLaunchScene() {
        position = "firstScene";
        theScenes.firstLaunchScene();
        updateScene();

        thePlayer.setPlayerHP(25);//sets the players and guards health back to its original state after the player dies
        thePlayer.setPlayerItem("Fists");
        itemLabelName.setText(thePlayer.getPlayerItem());
        enemyHealth = 20;
        hpLabelNumber.setText("" + thePlayer.playerHP);
    }

    public void lookAround() {
        position = "firstSceneLookAround";

        theScenes.lookAround();
        updateScene();
    }



    private void updateScene(){   //a method that updates all of the scenes by using 'Scenes' class
        mainTextArea.setText(theScenes.mainTextArea);
        choice1.setText(theScenes.choice1);
        choice2.setText(theScenes.choice2);
        choice3.setText(theScenes.choice3);
        choice4.setText(theScenes.choice4);
    }

    public void goToTheCandle()
    {
        position = "goToTheCandleScene"; //sets the position to a name which allows the choice handler to recognise it and act accordingly
        theScenes.goToTheCandle(); // launches the method 'goToTheCandle' which sets the mainTextArea and choice.setText to different text.
        updateScene();
    }

    public void pickUpTheCandle()
    {
        thePlayer.setPlayerItem("Candle"); //this actually changes the current text to whatever we have it set with .setPlayerItem
        itemLabelName.setText(thePlayer.getPlayerItem());
        candleEquipped = true;

        position = "pickUpTheCandle";

        theScenes.pickUpTheCandle();
        updateScene();
    }

    public void goToTheLight()
    {
        position = "goToTheLight";

        theScenes.goToTheLight();
        updateScene();
    }

    public void shoutForHelp()
    {

        position = "shoutForHelp";

        theScenes.shoutForHelp();
        updateScene();
    }

    public void tryToFallAsleep()
    {
        position = "tryToFallAsleep";

        theScenes.tryToFallAsleep();
        updateScene();
    }

    public void goToTheWall()
    {

        position = "goToTheWall";

        theScenes.goToTheWall();
        updateScene();

    }

    public void knockOnDoor()
    {
        position = "knockOnDoor";

        theScenes.knockOnDoor();
        updateScene();
    }

    public void openTheDoor()
    {
        position = "openTheDoor";

        theScenes.openTheDoor();
        updateScene();
    }

    public void extinguishTheFire()
    {
        position = "extinguishFire";

        thePlayer.setPlayerHP(thePlayer.playerHP - 5);
        hpLabelNumber.setText("" + thePlayer.getPlayerHP());

        theScenes.extinguishTheFire();
        updateScene();
    }

    public void enterTheDoor()
    {
        position = "enterTheDoor";

        theScenes.enterTheDoor();
        updateScene();
    }

    public void attackTheGuard()
    {
        //this sets the current scene position to 'attackTheGuard' which will allow the system to track
        //on what scene is the user at.
        position = "attackTheGuard";

        itemsEquipped();

        //An array of different gaurds weapons that he can attack with
        String[] enemyWeapons = new String[5];
        enemyWeapons[0] = "Fists";
        enemyWeapons[1] = "Dagger";
        enemyWeapons[2] = "Back of the sword";
        enemyWeapons[3] = "Kicking attack";
        enemyWeapons[4] = "Scissors";


        //this gets a random number and sets it as player attack power.
        thePlayer.setPlayerAttack((int) (Math.random() * ((5) + 2)));
        //this gets a random number and sets it as enemy attack power.
        enemyAttack = (int) (Math.random() * ((5) + 2));


        //this creates a random number for the array of guards weapons which then chooses the weapon that
        //the guards has/attacks the player with depending on the number.
        String enemyWeapon = enemyWeapons[(int)(Math.random() * ((4) + 1))];

        //this subtracts the enemyhealth depending on the number that we get in 'player attack'
        enemyHealth = enemyHealth - thePlayer.getPlayerAttack();
        thePlayer.setPlayerHP(thePlayer.playerHP - enemyAttack);

        //This if attacks additional 2 attack points to the player attack if the player currently
        //has a candle equipped (which can be eqquiped earlier in the game)

        mainTextArea.setText("You attack the guard with: " + thePlayer.getPlayerItem() +
                " and do: " + thePlayer.getPlayerAttack() + " damage."
                +"\nGuards current health: " + enemyHealth
                +"\n\nThe guard attacks you with: " + enemyWeapon
                +"\nAnd deals: " + enemyAttack + " damage.");

        //this updates the playerhealth.
        hpLabelNumber.setText("" + thePlayer.getPlayerHP());

        choice1.setText("Attack the guard");
        choice2.setText("------");
        choice3.setText("------");
        choice4.setText("------");

        //if the enemy heal is below or equal to 0 it calls guardKilled method
        if(enemyHealth<=0)
        {
            guardKilled();
        }
        //if the player heal is below or equal to 0 it calls playerKilled method
        else if(thePlayer.getPlayerHP()<=3)
        {
            playerKilled();
        }
    }

    public void guardKilled()
    {
        position = "guardKilled";

        theScenes.guardKilled();
        updateScene();
    }

    public void playerKilled()
    {
        position = "playerKilled";

        thePlayer.setPlayerHP(thePlayer.playerHP = 25);

        theScenes.playerKilled();
        updateScene();
    }

    public void duelEnemyKilled(int firstItemRandomPrice)
    {
        position = "duelEnemyKilled";

        thePlayer.setPlayerHP(thePlayer.playerHP = 25);

        int ActualAmount = firstItemRandomPrice + 125;

        theScenes.duelEnemyKilled();
        updateScene();

        JOptionPane.showMessageDialog(null,"You have received " + ActualAmount + " gold!");
        thePlayer.setPlayerMoney(thePlayer.playerMoney + ActualAmount);
    }

    public void searchTheGuard()
    {
        position = "searchTheGuard";

        keyAcquired = true;

        theScenes.searchTheGuard();
        updateScene();
    }

    public void goMetalDoor()
    {
        position = "goMetalDoor";

        if(keyAcquired)
        {
            theScenes.goMetalDoorKeyEquipped();
            updateScene();
        }
        else
        {
            theScenes.goMetalDoorNoKey();
            updateScene();
        }
    }


    public void eatTheApple()
    {
        position = "eatTheApple";

        appleGivenHealth = 35;
        thePlayer.setPlayerHP(thePlayer.playerHP += appleGivenHealth); //adds 35 amount of health to the players total health
        hpLabelNumber.setText("" + thePlayer.getPlayerHP());

        theScenes.eatTheApple();
        updateScene();

    }

    public void equipDagger()
    {

        position = "equipDagger";

        candleEquipped = false;
        daggerEquipped = true;

        itemsEquipped();

        theScenes.equipDagger();
        updateScene();

    }

    public void ContinueMetalDoor()
    {
        position = "enteringTown";

        theScenes.ContinueMetalDoor();
        updateScene();
    }

    public void runFromAttack()
    {
        position = "runFromAttack";

        theScenes.runFromAttack();
        updateScene();
    }

    public void enteringTheMarketScene()
    {

        position = "enteringTheMarket";

        playerPanel.setVisible(true);
        mainTextPanel.setVisible(true);
        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        choice4.setVisible(true);

        theScenes.enteringTheMarketScene();
        updateScene();
    }

    public void marketPlace()
    {
        position = "marketPlace";

        theScenes.marketPlace();
        updateScene();
    }

    public void billBoardDuels()
    {
        position = "billBoardDuels";

        enemyHealth = 100;

        theScenes.billBoardDuels();
        updateScene();
    }

    public void buyingItems()
    {
        int itemsToBuy = 0;

        String itemsToBuyAsString;

        //This is an array of strings that has a word which will be connected with the second array
        String[] itemListFirstWord = new String[7];
        itemListFirstWord[0] = "Wooden ";
        itemListFirstWord[1] = "Rubber " ;
        itemListFirstWord[2] = "Metal ";
        itemListFirstWord[3] = "Spiky ";
        itemListFirstWord[4] = "Sharpened ";
        itemListFirstWord[5] = "Slimy ";
        itemListFirstWord[6] = "Oily ";

        //this is the second word array, which takes the first array string and makes a weapon name out of it
        String[] itemListSecondWord = new String[7];
        itemListSecondWord[0] = "Sword";
        itemListSecondWord[1] = "Scissors" ;
        itemListSecondWord[2] = "Daggers";
        itemListSecondWord[3] = "Rod";
        itemListSecondWord[4] = "Spear";
        itemListSecondWord[5] = "Longsword";
        itemListSecondWord[6] = "Axe";

        String firstWordOfItem = itemListFirstWord[(int) (Math.random() * ((6) + 1))];
        String secondWordOfItem = itemListSecondWord[(int) (Math.random() * ((6) + 1))];

        //its an array of different word combinations which make the name of the weapon.
        String[] differentItemCombinations = new String[4];
        differentItemCombinations[0] = firstWordOfItem + secondWordOfItem;
        differentItemCombinations[1] = itemListFirstWord[(int) (Math.random() * ((6) + 1))] + itemListSecondWord[(int) (Math.random() * ((6) + 1))];
        differentItemCombinations[2] = itemListFirstWord[(int) (Math.random() * ((5) + 2))] + itemListSecondWord[(int) (Math.random() * ((5) + 2))];
        differentItemCombinations[3] = itemListFirstWord[(int) (Math.random() * ((4) + 3))] + itemListSecondWord[(int) (Math.random() * ((4) + 3))];

        //this sets the random int numbers to random prices and random damages
        firstItemRandomPrice = (int) (Math.random() *((2) + 5) + 2);
        int firstItemRandomDamage = (int) (Math.random() *((2) + 5) + 3);

        secondItemRandomPrice = (int) (Math.random() *((2) + 5) + 4);
        int secondItemRandomDamage = (int) (Math.random() *((2) + 5) + 5);

        thirdItemRandomPrice = (int) (Math.random() *((2) + 5) + 6);
        int thirdItemRandomDamage = (int) (Math.random() *((2) + 5) + 7);

        fourthItemRandomPrice = (int) (Math.random() *((2) + 5) + 8);
        int fourthItemRandomDamage = (int) (Math.random() *((2) + 5) + 9);

        //this just shows the output.
        try {
        itemsToBuyAsString = JOptionPane.showInputDialog("Your current money status is: " + thePlayer.getPlayerMoney() + "\n\nPlease choose what kind of items you would like to buy." +
                "\n1. " + differentItemCombinations[0] + " Price(" + firstItemRandomPrice + ")" + " Damage(" + firstItemRandomDamage + ")" +
                "\n2. " + differentItemCombinations[1] + " Price(" + secondItemRandomPrice + ")" + " Damage(" + secondItemRandomDamage + ")" +
                "\n3. " + differentItemCombinations[2] + " Price(" + thirdItemRandomPrice + ")" + " Damage(" + thirdItemRandomDamage + ")" +
                "\n4. " + differentItemCombinations[3] + " Price(" + fourthItemRandomPrice + ")" + " Damage(" + fourthItemRandomDamage + ")" +
                "\n5. Buy an apple Price(15)" +
                "\n6. Exit.");
        itemsToBuy = Integer.parseInt(itemsToBuyAsString);
    }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Something went wrong, please try again...");
        }

        //this calls the methods and puts the required values in
        Shop.itemsToBuy1(itemsToBuy, firstItemRandomPrice, firstItemRandomDamage, differentItemCombinations);
        Shop.itemsToBuy2(itemsToBuy, secondItemRandomPrice, secondItemRandomDamage, differentItemCombinations);
        Shop.itemsToBuy3(itemsToBuy, thirdItemRandomPrice, thirdItemRandomDamage, differentItemCombinations);
        Shop.itemsToBuy4(itemsToBuy, fourthItemRandomPrice, fourthItemRandomDamage, differentItemCombinations);
        Shop.exitPhase(itemsToBuy);
        Shop.buyingAnApple(itemsToBuy);
    }


    //This method allows the player to eat the apple and regain health.

    public void eatAnApple()
    {
        if(appleItemCount>0)
        {
            if(thePlayer.playerHP>=100)
            {
                JOptionPane.showMessageDialog(null,"It seems that you have more than 100 health!" +
                        "\nLower your health in order to eat apples.");
            }
            else {
                appleItemCount -= 1;
                JOptionPane.showMessageDialog(null, "You have just ate an apple, you received some health. " +
                                                                             "\nYou have left " + appleItemCount + " apples.");
                thePlayer.setPlayerHP(thePlayer.playerHP += appleGivenHealth);
                hpLabelNumber.setText("" + thePlayer.getPlayerHP());
            }
        }
        else if(appleItemCount<=0)
        {
            JOptionPane.showMessageDialog(null,"It seems that you don't have any apples, buy them at the market.");
        }
    }

    //Fighting system

    public void enterTheDuels()
    {
        //on what scene is the user at.
        position = "enterTheDuels";

        //An array of different enemy weapons that he can attack with
        String[] enemyWeapons = new String[5];
        enemyWeapons[0] = "Fists";
        enemyWeapons[1] = "Dagger";
        enemyWeapons[2] = "Back of the sword";
        enemyWeapons[3] = "Kicking attack";
        enemyWeapons[4] = "Scissors";

        //this gets a random number and sets it as enemy attack power.
        enemyAttack = (int) (Math.random() * ((15) + 5));
        int playerAttack = (int) (Math.random() * (5) + 2) + thePlayer.getPlayerAttack();

        //this creates a random number for the array of enemy weapons which then chooses the weapon that
        //the enemy has/attacks the player with depending on the number.
        String enemyWeapon = enemyWeapons[(int)(Math.random() * ((4) + 1))];

        //this subtracts the enemy health depending on the number that we get in 'player attack'
        enemyHealth = enemyHealth - playerAttack;
        thePlayer.setPlayerHP(thePlayer.playerHP - enemyAttack);

        mainTextArea.setText("You attack the duelist with: " + thePlayer.getPlayerItem() +
                " and do: " + playerAttack + " damage."
                +"\n duelists current health: " + enemyHealth
                +"\n\nThe duelist attacks you with: " + enemyWeapon
                +"\nAnd deals: " + enemyAttack + " damage.");

        //this updates the player health.
        hpLabelNumber.setText("" + thePlayer.getPlayerHP());

        choice1.setText("Attack the duelist");
        choice2.setText("Run");
        choice3.setText("Eat an apple");
        choice4.setText("------");


        //if the enemy health is below or equal to 0 it calls guardKilled method
        if(enemyHealth<=0)
        {
            duelEnemyKilled(firstItemRandomPrice);
        }
        ////if the player heal is below or equal to 0 it calls playerKilled method
        else if(thePlayer.getPlayerHP()<=1)
        {
            playerKilled();
        }

    }

    public void leavingTheTown()
    {
        position = "leavingTheTown";

        theScenes.leavingTheTown();
        updateScene();
    }

    public void endingScene()
    {
        if(thePlayer.getPlayerMoney() >= 5000) { //if the player has 5000 gold the game finishes
            JOptionPane.showMessageDialog(null, "Congratulations! You have completed the game...");
            System.exit(0);
        }
        else {
            JOptionPane.showMessageDialog(null,"Sorry, but it seems that you don't have the required amount of gold to leave the town...");
        }

    }

    //==================DIFFERENT METHODS===================

    public void textColourChanger() {
        if (textColourCount == 1) {
            container.setForeground(Color.white);
            mainTextArea.setForeground(Color.white);
            mainTextPanel.setForeground(Color.white);
            titleNameLabel.setForeground(Color.WHITE);
        } else if (textColourCount == 2) {
            container.setForeground(Color.orange);
            mainTextArea.setForeground(Color.orange);
            mainTextPanel.setForeground(Color.orange);
            titleNameLabel.setForeground(Color.orange);
            container.setForeground(Color.orange);
        } else if (textColourCount == 3) {
            container.setForeground(Color.pink);
            mainTextArea.setForeground(Color.pink);
            mainTextPanel.setForeground(Color.pink);
            titleNameLabel.setForeground(Color.pink);
        } else if (textColourCount == 4) {
            container.setForeground(Color.green);
            mainTextArea.setForeground(Color.green);
            mainTextPanel.setForeground(Color.green);
            titleNameLabel.setForeground(Color.green);
        } else if (textColourCount == 5) {
            container.setForeground(Color.cyan);
            mainTextArea.setForeground(Color.cyan);
            mainTextPanel.setForeground(Color.cyan);
            titleNameLabel.setForeground(Color.cyan);
        } else {
            textColourCount = 0;
        }
    }

    public void backgroundColourCounter() {
        if (backgroundColourCount == 1)
        {
            container.setBackground(Color.darkGray);
            titleNamePanel.setBackground(Color.darkGray);
        }
        else if (backgroundColourCount == 2)
        {
            container.setBackground(Color.orange);
            titleNamePanel.setBackground(Color.orange);
        }
        else if (backgroundColourCount == 3)
        {
            container.setBackground(Color.pink);
            titleNamePanel.setBackground(Color.pink);
        }
        else if (backgroundColourCount == 4)
        {
            container.setBackground(Color.green);
            titleNamePanel.setBackground(Color.green);
        }
        else if (backgroundColourCount == 5)
        {
            container.setBackground(Color.cyan);
            titleNamePanel.setBackground(Color.cyan);
        }
        else
        {
            backgroundColourCount = 0;
        }
    }

    //==================BUTTON EVENT HANDLERS===============

    public class StartGameHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            afterStartButtonScene();
        }
    }

    public class OptionsHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            afterOptionsButtonScene();
        }
    }

    public class AboutGameHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            afterAboutGameButtonScene();
        }
    }

    public class ExitGameHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            afterExitButtonScene();
        }
    }

    public class ExitGameHandlerButtonYes implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {System.exit(0);}
    }

    public class ExitGameHandlerButtonNo implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            exitConfirmationPanelNo.setVisible(false);
            exitConfirmationPanelYes.setVisible(false);
            mainTextPanel.setVisible(false);
            mainTextArea.setVisible(false);

            mainMenuGUIChangedColors();
        }
    }

    public class continueButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {afterFirstSceneContinue();}
    }


    public class changeTextColourHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            textColourCount++;
            textColourChanger();
        }
    }

    public class changeBackgroundColourHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            backgroundColourCount++;
            backgroundColourCounter();
        }
    }

    public class caveContinueHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

            cavePanel.setVisible(false);
            caveButtonPanel.setVisible(false);
            firstScene();
        }
    }

    public class marketContinueHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            marketPanel.setVisible(false);
            marketButtonPanel.setVisible(false);

            enteringTheMarketScene();
        }
    }

    public class goBackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            mainTextArea.setVisible(false);
            mainTextPanel.setVisible(false);
            changeTextColourPanel.setVisible(false);
            changeBackgroundColourPanel.setVisible(false);
            goBackButton.setVisible(false);
            goBackPanel.setVisible(false);

            mainMenuGUIChangedColors();
        }
    }

    public class goBackHandlerAboutGame implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            mainTextArea.setVisible(false);
            mainTextPanel.setVisible(false);
            belowTextPanel.setVisible(false);
            goBackButton.setVisible(false);
            goBackPanel.setVisible(false);

            mainMenuGUIChangedColors();

        }
    }

    //==========CHOICE HANDLER========================
    //The choice handler is responsible of every choice that the player makes.
    public class ChoiceHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            //This takes the named reference from the buttons
            String yourChoice = event.getActionCommand();
            switch(position)
            {
                case "firstScene":
                    switch(yourChoice)
                    {
                        //first Scene choices
                        case "c1": lookAround(); break;
                        case "c2": goToTheLight();break;
                        case "c3": shoutForHelp();break;
                        case "c4": tryToFallAsleep();break;
                    }
                    break;
                case "firstSceneLookAround":
                    switch (yourChoice)
                    {
                        //firstScene/Look Around
                        case "c1": goToTheCandle(); break;
                        case "c2": goToTheWall(); break;
                        case "c3": shoutForHelp();break;
                        case "c4": tryToFallAsleep();break;
                    }
                    break;
                case "goToTheCandleScene":
                    switch (yourChoice)
                    {
                        case "c1": pickUpTheCandle(); break;
                        case "c2": goToTheWall();break;
                        case "c3": lookAround();break;
                        case "c4": extinguishTheFire(); break;
                    }
                    break;
                case "pickUpTheCandle":
                    switch (yourChoice)
                    {
                        case "c1": goToTheWall();break;
                        case "c2": goToTheCandle();break;
                        case "c3": shoutForHelp();break;
                        case "c4": tryToFallAsleep();break;
                    }
                    break;
                case "goToTheLight":
                    switch(yourChoice)
                    {
                        case "c1": pickUpTheCandle();break;
                        case "c2": firstLaunchScene();break;
                        case "c3": shoutForHelp();break;
                        case "c4": tryToFallAsleep();break;
                    }
                    break;
                case "shoutForHelp":
                    switch(yourChoice)
                    {
                        case "c1": lookAround();break;
                        case "c2": goToTheLight();break;
                        case "c3": break;
                        case "c4": tryToFallAsleep();break;
                    }
                    break;
                case "tryToFallAsleep":
                    switch(yourChoice)
                    {
                        case "c1": lookAround();break;
                        case "c2": goToTheLight();break;
                        case "c3": shoutForHelp();break;
                        case "c4": break;
                    }
                    break;
                case "goToTheWall":
                    switch(yourChoice)
                    {
                        case "c1": knockOnDoor();break;
                        case "c2": lookAround();break;
                        case "c3": openTheDoor();break;
                        case "c4": ;break;
                    }
                    break;
                case "knockOnDoor":
                    switch(yourChoice)
                    {
                        case "c1": break;
                        case "c2": goToTheWall();break;
                        case "c3": openTheDoor();break;
                        case "c4": break;
                    }
                    break;
                case "extinguishFire":
                    switch(yourChoice)
                    {
                        case "c1": break;
                        case "c2": goToTheWall();break;
                        case "c3": goToTheCandle();break;
                        case "c4": ;break;
                    }
                    break;
                case "openTheDoor":
                    switch(yourChoice)
                    {
                        case "c1": enterTheDoor();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": ;break;
                    }
                    break;
                case "enterTheDoor":
                    switch(yourChoice)
                    {
                        case "c1": attackTheGuard();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "attackTheGuard":
                    switch(yourChoice)
                    {
                        case "c1": attackTheGuard();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "runFromTheGuard":
                    switch(yourChoice)
                    {
                        case "c1": attackTheGuard();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "guardKilled":
                    switch(yourChoice)
                    {
                        case "c1": searchTheGuard();break;
                        case "c2": goMetalDoor();break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "playerKilled":
                    switch(yourChoice)
                    {
                        case "c1": firstLaunchScene();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "searchTheGuard":
                    switch(yourChoice)
                    {
                        case "c1": goMetalDoor();break;
                        case "c2": eatTheApple();break;
                        case "c3": equipDagger();break;
                        case "c4": break;
                    }
                    break;
                case "goMetalDoor":
                    switch(yourChoice)
                    {
                        case "c1": ContinueMetalDoor();break;
                        case "c2": break;
                        case "c3": guardKilled();break;
                        case "c4": searchTheGuard();break;
                    }
                    break;
                case "eatTheApple":
                    switch(yourChoice)
                    {
                        case "c1": ContinueMetalDoor();break;
                        case "c2": break;
                        case "c3": equipDagger();break;
                        case "c4": searchTheGuard();break;
                    }
                    break;
                case "equipDagger":
                    switch(yourChoice)
                    {
                        case "c1": ContinueMetalDoor();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "enteringTown":
                    switch(yourChoice)
                    {
                        case "c1": marketScene();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "enteringTheMarket":
                    switch(yourChoice)
                    {
                        case "c1": marketPlace();break;
                        case "c2": billBoardDuels();break;
                        case "c3": leavingTheTown();break;
                        case "c4": break;
                    }
                    break;
                case "marketPlace":
                    switch(yourChoice)
                    {
                        case "c1": buyingItems();break;
                        case "c2": Shop.sellingItems(firstItemRandomPrice);break;
                        case "c3": enteringTheMarketScene();break;
                        case "c4": break;
                    }
                    break;
                case "billBoardDuels":
                    switch(yourChoice)
                    {
                        case "c1": enterTheDuels();break;
                        case "c2": enteringTheMarketScene();break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "enteringTheTownPreviousPosition":
                    switch(yourChoice)
                    {
                        case "c1": enteringTheMarketScene();break;
                        case "c2": billBoardDuels();break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "enterTheDuels":
                    switch(yourChoice)
                    {
                        case "c1": enterTheDuels();break;
                        case "c2": runFromAttack();break;
                        case "c3": eatAnApple();break;
                        case "c4": break;
                    }
                    break;
                case "runFromAttack":
                    switch(yourChoice)
                    {
                        case "c1": enteringTheMarketScene();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "duelEnemyKilled":
                    switch(yourChoice)
                    {
                        case "c1": enteringTheMarketScene();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "leavingTheTown":
                    switch(yourChoice)
                    {
                        case "c1": endingScene();break;
                        case "c2": enteringTheMarketScene();break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
            }
        }
    }

    //================This disables some of the active panels===============

    public void disableSomePanels()
    {
        titleNamePanel.setVisible(false);
        optionsButtonPanel.setVisible(false);
        startButtonPanel.setVisible(false);
        aboutTheGameButtonPanel.setVisible(false);
        exitGameButtonPanel.setVisible(false);
    }

}


