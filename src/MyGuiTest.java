//For JFrame reference I was looking at this video https://www.youtube.com/watch?v=RcvABhflOkI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.security.Key;
import java.util.Random;

public class MyGuiTest extends JFrame{


    static Player thePlayer;

    Container container;

    JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, aboutTheGameButtonPanel,
            exitGameButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, exitConfirmationPanelYes, exitConfirmationPanelNo,
            changeTextColourPanel, changeBackgroundColourPanel, goBackPanel, ContinueButtonPanel, belowTextPanel;

    JLabel titleNameLabel, hpLabel, hpLabelNumber, itemLabel, itemLabelName;

    JButton startButton, optionsButton, aboutTheGameButton, exitGameButton,
            choice1, choice2, choice3, choice4, exitGameButtonYes, exitGameButtonNo, continueButton,
            changeTextColourButton, changeBackgroundColourButton, goBackButton;

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

    marketContinueHandler marketContinueHdrl = new marketContinueHandler();

    StartGameHandler startGameHdlr = new StartGameHandler();
    OptionsHandler OptionsHdlr = new OptionsHandler();
    AboutGameHandler aboutGameHdlr = new AboutGameHandler();
    ChoiceHandler choiceHdlr = new ChoiceHandler();
    ExitGameHandler exitGameHdlr = new ExitGameHandler();
    ExitGameHandlerButtonYes exitGameButtonYesHdlr = new ExitGameHandlerButtonYes();
    ExitGameHandlerButtonNo exitGameButtonNoHdlr = new ExitGameHandlerButtonNo();
    changeTextColourHandler textColourHdrl = new changeTextColourHandler();
    changeBackgroundColourHandler backgroundColourHdrl = new changeBackgroundColourHandler();
    goBackHandler goBackHdlr = new goBackHandler();
    goBackHandlerAboutGame goBackHdrlAboutGame = new goBackHandlerAboutGame();
    continueButtonHandler continueButtonHdrl = new continueButtonHandler();

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font mainFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font mainTextUsedForNarrative = new Font("Comic Sans MS", Font.PLAIN, 28);
    Font optionsFont = new Font("Arial Black", Font.PLAIN, 30);
    Font exitFont = new Font("Arial", Font.PLAIN, 45);

    int playerHP;
    int textColourCount = 1, backgroundColourCount = 1;
    int enemyHealth = 20;
    int playerAttack, enemyAttack;
    int candleAdditionalDamage, daggerAdditionalDamage;

    String position;

    boolean candleEquipped = false, keyAcquired = false, daggerEquipped=false;


    public static void main(String[] args) {

        thePlayer = new Player();
        new MyGuiTest();
    }


    public MyGuiTest() {
        //this sets that the screen size cannot be changed.
        setResizable(false);

        setSize(1200, 800);
        //This sets the JFrame to be always in the middle regardless of the screen size
        //Code taken from:
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        container = getContentPane();

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

    //===================PLAYER SETUP=====================

    public void playerSetup() {
        playerHP = thePlayer.getPlayerHP(); //gets the player hp from the 'Player' class.

        thePlayer.getPlayerItem(); //default player item

        itemLabelName.setText(thePlayer.getPlayerItem());
        hpLabelNumber.setText("" + playerHP);
        firstLaunchScene();
    }

    //==================INVENTORY SETUP==================

    public void itemsEquipped()
    {
        candleAdditionalDamage=3;
        daggerAdditionalDamage=6;

        //checks if the candle is equipped.
        if(candleEquipped)
        {
            playerAttack += candleAdditionalDamage;
        }

        if(daggerEquipped)
        {
            playerAttack += daggerAdditionalDamage;
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
        disableAllPanels();
        mainTextPanel();
        mainTextArea();

        thePlayer.setPlayerHP(25);
        thePlayer.setPlayerItem("Fists");

        mainTextArea.setForeground(Color.white);
        mainTextPanel.setBackground(Color.RED);
        mainTextPanel.setBounds(100,100,1000,200);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(350, 350, 500, 150);
        choiceButtonPanel.setBackground(Color.darkGray);
        //new GridLayout allows to change the layout of the buttons
        //code reference taken from:
        //https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        container.add(choiceButtonPanel);

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
        disableAllPanels();
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
        disableAllPanels();
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
        disableAllPanels();
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
        disableAllPanels();
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
        mainTextPanel.setVisible(false);
        ContinueButtonPanel.setVisible(false);
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
        playerPanel.setVisible(false);

        marketImage = new ImageIcon(getClass().getResource("Images/marketImage.gif"));
        marketLabel = new JLabel(marketImage);

        marketPanel = new JPanel();
        marketPanel.setBounds(200, 100, 750, 500);
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
        //this sets that the screen size cannot be changed.
        setResizable(false);
        //This adds the keylistener to the guy.
        //KeyListener taken from:
        //https://www.youtube.com/watch?v=PbmQrkwR9Ko

        setSize(1200, 800);
        //This sets the JFrame to be always in the middle regardless of the screen size
        //Code taken from:
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        container = getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 1000, 150);
        titleNamePanel.setBackground(Color.darkGray);
        titleNamePanel.setForeground(Color.white);

        titleNameLabel = new JLabel("Begin your adventure");
        titleNameLabel.setFont(titleFont);
        titleNameLabel.setForeground(Color.white);

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

    //=================GAME SCENES========================
    public void firstLaunchScene() {
        position = "firstScene";
        mainTextArea.setText("As you opened your eyes, you realised that you are inside of some sort of a room? " +
                             "You can see a light coming out of one of the corners...");
        choice1.setText("Look around");
        choice2.setText("Go to the light");
        choice3.setText("Shout for help");
        choice4.setText("Try to fall asleep");

       //sets the players and guards health back to its original state after the player dies
        playerHP = 25;
        enemyHealth = 20;
        hpLabelNumber.setText("" + playerHP);
    }

    public void lookAround() {
        position = "firstSceneLookAround";

        mainTextArea.setText("You lift your head and look around the room, there is no much light, but you can see" +
                             " that the ceiling, floor and eveything around you is made out of wet mud and dirt. In the corner" +
                             " of the cave    you can see a small candle burning away. By that candle you can see that one of the wall" +
                             " is more sledged inwards than any other walls inside of this 'room'. What is your next move?");
        choice1.setText("Go to the candle");
        choice2.setText("Go to the wall");
        choice3.setText("Shout for help");
        choice4.setText("Try to fall asleep");
    }

    public void goToTheCandle()
    {
        position = "goToTheCandleScene";

        mainTextArea.setText("You move closer to the candle. You can see that the candle is really old it was made by hand from natural bee wax" +
                             " It seems that the candle is about to burn out... What do you do?");
        choice1.setText("Pick up the candle");
        choice2.setText("Go to the wall");
        choice3.setText("Go back to your previous position");
        choice4.setText("Extinguish the fire");
    }

    public void pickUpTheCandle()
    {
        thePlayer.setPlayerItem("Candle");
        //this actually changes the current text to whatever we have it set with .setPlayerItem
        itemLabelName.setText(thePlayer.getPlayerItem());
        thePlayer.setPlayerHP(2);
        candleEquipped = true;
        position = "pickUpTheCandle";

        mainTextArea.setText("You picked up the candle. Now you can move the source of light around you, it will help you to see things more clearly.");
        choice1.setText("Go to the wall");
        choice2.setText("Go back to the previous position");
        choice3.setText("Shout for help");
        choice4.setText("Try to fall asleep");
    }

    public void goToTheLight()
    {
        position = "goToTheLight";

        mainTextArea.setText("As you move closer to the light you realised that the light is actually coming out of an object " +
                             "and the object reminds you of a candle.");

        choice1.setText("Pick up the candle");
        choice2.setText("Go back to the previous position");
        choice3.setText("Shout for help");
        choice4.setText("Try to fall asleep");
    }

    public void shoutForHelp()
    {

        position = "shoutForHelp";

        mainTextArea.setText("You scream out top of your lungs... No answer. Is anyone even here? As it seems" +
                             "   you can only hear your dry voice echoing around the place...");

        choice1.setText("Look around");
        choice2.setText("Go to the light");
        choice3.setText("------");
        choice4.setText("Try to fall asleep");
    }

    public void tryToFallAsleep()
    {
        position = "tryToFallAsleep";

        mainTextArea.setText("You walk to the nearest corner, lay down and close your eyes... 'Maybe this will help' " +
                             " - you think to yourself. As easy it is to fall asleep in a cave, you just can't really do it. ");

        choice1.setText("Look around");
        choice2.setText("Go to the light");
        choice3.setText("Shout for help");
        choice4.setText("------");
    }

    public void goToTheWall()
    {

        position = "goToTheWall";

        mainTextArea.setText("You move towards the wall. While you were looking at it for couple of minutes you realised that " +
                " the 'inward' section of the wall was an actual wooden door. ");

        choice1.setText("Knock on the door");
        choice2.setText("Go back to the previous position");
        choice3.setText("Try to open the door");
        choice4.setText("------");

        playerHP = 25;
        hpLabelNumber.setText("" + playerHP);


    }

    public void knockOnDoor()
    {
        position = "knockOnDoor";

        mainTextArea.setText("You lift your hand towards the door and you give it few hard knocks... No answer... ");

        choice1.setText("------");
        choice2.setText("Go back to the previous position");
        choice3.setText("Try to open the door");
        choice4.setText("------");
    }

    public void openTheDoor()
    {
        position = "openTheDoor";

        mainTextArea.setText("You extend your arms towards the door, you put your whole weight on it and try to push " +
                             "The door makes a loud cranky noise and the door opens...");

        choice1.setText("Enter");
        choice2.setText("------");
        choice3.setText("------");
        choice4.setText("------");
    }

    public void extinguishTheFire()
    {
        position = "extinguishFire";

        mainTextArea.setText("You move the palm over the candle to put it out, you succeed but you lose some health and a light source." +
                             " Why did you do that?");

        playerHP = playerHP - 5;
        hpLabelNumber.setText("" + playerHP);

        choice1.setText("------");
        choice2.setText("Go to the wall");
        choice3.setText("Go back to your previous position");
        choice4.setText("------");
    }

    public void enterTheDoor()
    {
        position = "enterTheDoor";

        mainTextArea.setText("You enter the room, this room is even smaller than the one you came from," +
                             " you can see various objects around you, from table with chairs, fireplace, big wooden door, " +
                             "and? what? you can't believe your eyes... Who is that?" +
                             "\n\nGuard: You shouldn't suppouse to be here! Now you will have to pay.");

        choice1.setText("Attack the guard");
        choice2.setText("Run");
        choice3.setText("------");
        choice4.setText("------");
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
        playerAttack = (int) (Math.random() * ((5) + 2));
        //this gets a random number and sets it as enemy attack power.
        enemyAttack = (int) (Math.random() * ((5) + 2));

        itemsEquipped();

        //this creates a random number for the array of guards weapons which then chooses the weapon that
        //the guards has/attacks the player with depending on the number.
        String enemyWeapon = enemyWeapons[(int)(Math.random() * ((4) + 1))];

        //this subtracts the enemyhealth depending on the number that we get in 'player attack'
        enemyHealth = enemyHealth - playerAttack;
        playerHP = playerHP - enemyAttack;

        //This if attacks additional 2 attack points to the player attack if the player currently
        //has a candle equipped (which can be eqquiped earlier in the game)

        System.out.print("\nPlayer attack damage:" + playerAttack);
        mainTextArea.setText("You attack the guard with: " + thePlayer.getPlayerItem() +
                             " and do: " + playerAttack + " damage."
                             +"\nGuards current health: " + enemyHealth
                             +"\n\nThe guard attacks you with: " + enemyWeapon
                             +"\nAnd deals: " + enemyAttack + " damage.");

        //this updates the playerhealth.
        hpLabelNumber.setText("" + playerHP);

        choice1.setText("Attack the guard");
        choice2.setText("Run");
        choice3.setText("------");
        choice4.setText("------");

        //if the enemy heal is below or equal to 0 it calls guardKilled method
        if(enemyHealth<=0)
        {
            guardKilled();
        }
        //if the player heal is below or equal to 0 it calls playerKilled method
        else if(playerHP<=0)
        {
            playerKilled();
        }
    }

    public void guardKilled()
    {
        position = "guardKilled";

        mainTextArea.setText("Guards current health: 0 " +
                             "\nCongratulations! You've survived and killed the guard! \nWhat is your next move?");

        choice1.setText("Search the guard");
        choice2.setText("Go to the door and open it");
        choice3.setText("------");
        choice4.setText("------");
    }

    public void playerKilled()
    {
        position = "playerKilled";

        thePlayer.setPlayerHP(playerHP);

        mainTextArea.setText("Players current health: " + thePlayer.getPlayerHP() +
                "\nIt seems that you've messed up, well its not that bad at it seems, it just that you will have to try again...");

        choice1.setText("Go to the start");
        choice2.setText("------");
        choice3.setText("------");
        choice4.setText("------");
    }

    public void searchTheGuard()
    {
        position = "searchTheGuard";

        //this checks if the items are equipped, and if they are the system gives the additional buffs to the player.
        itemsEquipped();

        keyAcquired = true;
        candleEquipped = false;
        daggerEquipped = true;

        mainTextArea.setText("You lowered your body towards the guard. He's dead. You decide to look through his items." +
                             "You managed to find few items: A dagger, an apple and bunch of old keys.");

        choice1.setText("Go to the metal door");
        choice2.setText("Eat the apple");
        choice3.setText("Equip the dagger");
        choice4.setText("------");
    }

    public void goMetalDoor()
    {
        position = "goMetalDoor";

        itemsEquipped();

        if(keyAcquired)
        {
            mainTextArea.setText("You walked to the door, it seems that it is locked. You remembered that you have bunch of old " +
                                 "keys that you got from the guard. You spent 15 minutes trying every rusted key and eventually one of them " +
                                 "worked. You put the key in, pushed right and the big metal door slowly opened...");

            choice1.setText("Continue");
            choice2.setText("------");
            choice3.setText("------");
            choice4.setText("------");
        }
        else
        {
            mainTextArea.setText("You tried to open the door. It didn't work, it seems that it is locked. " +
                                 "You will have to find something to open the door with. Go back.");

            choice1.setText("------");
            choice2.setText("------");
            choice3.setText("Go to previous position");
            choice4.setText("Search the guard");
        }
    }


    public void eatTheApple()
    {

    }

    public void equipDagger()
    {

    }

    public void ContinueMetalDoor()
    {
        marketScene();

    }



    //==================DIFFERENT METHODS===================

    public void textColourChanger() {
        if (textColourCount == 1) {
            mainTextArea.setForeground(Color.white);
            mainTextPanel.setForeground(Color.white);
            titleNameLabel.setForeground(Color.WHITE);
        } else if (textColourCount == 2) {
            mainTextArea.setForeground(Color.orange);
            mainTextPanel.setForeground(Color.orange);
            titleNameLabel.setForeground(Color.orange);
            container.setForeground(Color.orange);
        } else if (textColourCount == 3) {
            mainTextArea.setForeground(Color.pink);
            mainTextPanel.setForeground(Color.pink);
            titleNameLabel.setForeground(Color.pink);
        } else if (textColourCount == 4) {
            mainTextArea.setForeground(Color.green);
            mainTextPanel.setForeground(Color.green);
            titleNameLabel.setForeground(Color.green);
        } else if (textColourCount == 5) {
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
            //testNewJFrameWindow();
            afterStartButtonScene();
            //firstScene();

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
                        case "c4": ;break;
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
                        case "c4": ;break;
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
                        case "c4": ;break;
                    }
                    break;
                case "attackTheGuard":
                    switch(yourChoice)
                    {
                        case "c1": attackTheGuard();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": ;break;
                    }
                    break;
                case "runFromTheGuard":
                    switch(yourChoice)
                    {
                        case "c1": attackTheGuard();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": ;break;
                    }
                    break;
                case "guardKilled":
                    switch(yourChoice)
                    {
                        case "c1": searchTheGuard();break;
                        case "c2": goMetalDoor();break;
                        case "c3": break;
                        case "c4": ;break;
                    }
                    break;
                case "playerKilled":
                    switch(yourChoice)
                    {
                        case "c1": firstLaunchScene();break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": ;break;
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

            }

        }
    }


    //================This disables all of the active panels===============

    public void disableAllPanels()
    {
        titleNamePanel.setVisible(false);
        optionsButtonPanel.setVisible(false);
        startButtonPanel.setVisible(false);
        aboutTheGameButtonPanel.setVisible(false);
        exitGameButtonPanel.setVisible(false);
    }

}

//
//getContentPane is like a container that can store things
//setBounts sets the position of the panel.

//This adds the keylistener to the guy.
//KeyListener taken from:
//https://www.youtube.com/watch?v=PbmQrkwR9Ko
        //keyText.addKeyListener(this);
               // add(keyText);

