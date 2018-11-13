//For JFrame reference I was looking at this video https://www.youtube.com/watch?v=RcvABhflOkI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class MyGuiTest extends JFrame implements KeyListener {

    Container container;

    JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, aboutTheGameButtonPanel,
            exitGameButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, exitConfirmationPanelYes, exitConfirmationPanelNo,
            changeTextColourPanel, changeBackgroundColourPanel;

    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;

    JButton startButton, optionsButton, aboutTheGameButton, exitGameButton,
            choice1, choice2, choice3, choice4, exitGameButtonYes, exitGameButtonNo, continueButton,
            changeTextColourButton, changeBackgroundColourButton;

    JTextArea mainTextArea;

    StartGameHandler startGameHdlr = new StartGameHandler();
    OptionsHandler OptionsHdlr = new OptionsHandler();
    AboutGameHandler aboutGameHdlr = new AboutGameHandler();
    ChoiceHandler choiceHdlr = new ChoiceHandler();
    ExitGameHandler exitGameHdlr = new ExitGameHandler();
    ExitGameHandlerButtonYes exitGameButtonYesHdlr = new ExitGameHandlerButtonYes();
    ExitGameHandlerButtonNo exitGameButtonNoHdlr = new ExitGameHandlerButtonNo();
    changeTextColourHandler textColourHdrl = new changeTextColourHandler();
    changeTextFontHandler textFontHdrl = new changeTextFontHandler();
    changeBackgroundColourHandler backgroundColourHdrl = new changeBackgroundColourHandler();

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font mainFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font mainTextUsedForNarrative = new Font("Comic Sans MS", Font.PLAIN, 28);
    Font optionsFont = new Font("Arial Black", Font.PLAIN, 30);

    int playerHP;
    int textColourCount = 1, backgroundColourCount = 1, fontTypeCount=1;

    String weapon, position;

    JTextField keyText = new JTextField(80);


    public static void main(String[] args) {

        new MyGuiTest();
    }

    public void keyTyped(KeyEvent e) {

    }

    //This is what happens when a button is pressed
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //=======================INVENTORY=====================
        //This method calls the inventory method if they 'I' key is pressed on the keyboard.
        if (keyCode == KeyEvent.VK_I) {
            playerInventory();
        }
        if (keyCode == KeyEvent.VK_M) {
            gameMap();
        }
    }

    public void keyReleased(KeyEvent txt) {

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
        titleNamePanel.setBackground(Color.blue);

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
        playerHP = 15;
        weapon = "Knife";
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);
        townGate();
    }

    //===================INVENTORY SETUP==================

    public void playerInventory() {
        JFrame inventory = new JFrame();
        inventory.setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        inventory.getContentPane().setBackground(Color.DARK_GRAY);
        inventory.setLayout(null);
        container = getContentPane();
        inventory.setVisible(true);

    }

    public void gameMap() {
        JFrame map = new JFrame();
        map.setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        map.getContentPane().setBackground(Color.DARK_GRAY);
        map.setLayout(null);
        container = getContentPane();
        map.setVisible(true);

    }

    //===================BUTTON METHODS===================
    public void startGameButton() {
        //===================START THE GAME BUTTON===================
        startButton = new JButton("Start the game");
        startButton.setBackground(Color.BLUE);
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
        optionsButton.setBackground(Color.BLUE);
        optionsButton.setForeground(Color.white);
        optionsButton.setFont(mainFont);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.addActionListener(OptionsHdlr);
        optionsButton.setFocusPainted(false);
    }

    public void aboutTheGameButton() {
        //===================ABOUT THE GAME BUTTON===================
        aboutTheGameButton = new JButton("About the game");
        aboutTheGameButton.setBackground(Color.BLUE);
        aboutTheGameButton.setForeground(Color.white);
        aboutTheGameButton.setFont(mainFont);
        aboutTheGameButton.setBorder(BorderFactory.createEmptyBorder());
        aboutTheGameButton.addActionListener(aboutGameHdlr);
        aboutTheGameButton.setFocusPainted(false);
    }

    public void exitGameButton() {
        //===================EXIT THE GAME BUTTON===================
        exitGameButton = new JButton("Exit");
        exitGameButton.setBackground(Color.BLUE);
        exitGameButton.setForeground(Color.white);
        exitGameButton.setFont(mainFont);
        exitGameButton.setBorder(BorderFactory.createEmptyBorder());
        exitGameButton.setFocusPainted(false);
        exitGameButton.addActionListener(exitGameHdlr);
    }

    public void exitGameButtonYes() {
        exitGameButtonYes = new JButton("Yes");
        exitGameButtonYes.setBackground(Color.red);
        exitGameButtonYes.setForeground(Color.white);
        exitGameButtonYes.setFont(mainFont);
        exitGameButtonYes.setBorder(BorderFactory.createEmptyBorder());
        exitGameButtonYes.setFocusPainted(false);
        exitGameButtonYes.addActionListener(exitGameButtonYesHdlr);
    }

    public void exitGameButtonNo() {
        exitGameButtonNo = new JButton("No");
        exitGameButtonNo.setBackground(Color.red);
        exitGameButtonNo.setForeground(Color.white);
        exitGameButtonNo.setFont(mainFont);
        exitGameButtonNo.setBorder(BorderFactory.createEmptyBorder());
        exitGameButtonNo.setFocusPainted(false);
        exitGameButtonNo.addActionListener(exitGameButtonNoHdlr);
    }

    public void continueButton() {
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.red);
        continueButton.setForeground(Color.white);
        continueButton.setFont(mainFont);
        continueButton.setBorder(BorderFactory.createEmptyBorder());
        continueButton.setFocusPainted(false);
        //exitGameButtonNo.addActionListener(exitGameButtonNoHdlr);
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
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(mainFont);
        //This automatically wraps the text to the next line
        //Code taken from:
        //https://stackoverflow.com/questions/7861724/is-there-a-word-wrap-property-for-jlabel
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }


    //====================DIFFERENT SCENES==================

    public void FirstScene() {
        disableAllPanels();
        mainTextPanel();
        mainTextArea();

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
        playerPanel.setBackground(Color.blue);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);

        hpLabel = new JLabel("HP: ");
        hpLabel.setFont(mainFont);
        hpLabel.setForeground(Color.WHITE);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(mainFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(mainFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponLabelName = new JLabel();
        weaponLabelName.setFont(mainFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        playerSetup();
    }

    public void afterStartButtonScene() {
        setResizable(false);
        disableAllPanels();
        mainTextPanel();
        mainTextArea();
        mainTextPanel.setBounds(70, 150, 1000, 290);
        mainTextPanel.setBackground(Color.blue);

        mainTextArea.setFont(mainTextUsedForNarrative);
        mainTextArea.setText("It seems that it was a long time ago when you felt like this... " +
                "\n\n\tBut is it a good feeling? You might not know... at least for now. \n\n\n\n\t\tYou open your eyes.");

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(470, 480, 200, 50);
        choiceButtonPanel.setBackground(Color.red);
        choiceButtonPanel.add(continueButton);
        container.add(choiceButtonPanel);


    }

    public void afterAboutGameButtonScene() {
        disableAllPanels();
        mainTextPanel();
        mainTextArea();
    }

    public void afterOptionsButtonScene() {
        disableAllPanels();
        mainTextPanel();
        mainTextArea();


        mainTextPanel.setBounds(100, 100, 520, 450);
        mainTextArea.setFont(optionsFont);
        mainTextArea.setText("\tOptions\n\n\n\tChange text Colour\n\n\n\tChange background color");
        mainTextPanel.setBackground(Color.darkGray);

        changeTextColourPanel = new JPanel();
        changeTextColourPanel.setBounds(700, 240, 150, 50);
        changeTextColourPanel.setFont(mainFont);
        changeTextColourPanel.setBackground(Color.darkGray);
        changeTextColourPanel.add(changeTextColourButton);

        changeBackgroundColourPanel = new JPanel();
        changeBackgroundColourPanel.setBounds(700, 350, 150, 50);
        changeBackgroundColourPanel.setFont(mainFont);
        changeBackgroundColourPanel.setBackground(Color.darkGray);
        changeBackgroundColourPanel.add(changeBackgroundColourButton);

        container.add(changeTextColourPanel);
        container.add(changeBackgroundColourPanel);


    }

    public void afterExitButtonScene() {
        disableAllPanels();
        mainTextPanel();
        mainTextArea();

        mainTextArea.setText("Are you sure you want to exit the game?");

        exitConfirmationPanelYes = new JPanel();
        exitConfirmationPanelYes.setBounds(100, 350, 500, 200);
        exitConfirmationPanelYes.setFont(mainFont);
        exitConfirmationPanelYes.setBackground(Color.green);
        exitConfirmationPanelYes.add(exitGameButtonYes);

        exitConfirmationPanelNo = new JPanel();
        exitConfirmationPanelNo.setBounds(600, 350, 500, 200);
        exitConfirmationPanelNo.setFont(mainFont);
        exitConfirmationPanelNo.setBackground(Color.orange);
        exitConfirmationPanelNo.add(exitGameButtonNo);


        container.add(exitConfirmationPanelYes);
        container.add(exitConfirmationPanelNo);

    }

    public void mainMenuGUI()
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
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        container = getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 1000, 150);
        titleNamePanel.setBackground(Color.blue);

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
    public void townGate() {
        position = "townGate";
        mainTextArea.setText("You are at the gate of the town, you can see 2 guards standing beside the gate, what do you do?");
        choice1.setText("Do nothing");
        choice2.setText("Say Hi");
        choice3.setText("What?");
        choice4.setText("asmrklamsr");
    }

    public void talkGuard() {
        position = "talkGuard";
        mainTextArea.setText("Guard: whatsup");
        choice1.setText("AAAAAAAA");
        choice2.setText("~BBBBBBBBB");
        choice3.setText("CCCCCCCC");
        choice4.setText("DDDDDDD");
    }

    //==================DIFFERENT METHODS===================

    public void textColourCounter() {
        if (textColourCount == 1) {
            mainTextArea.setForeground(Color.white);
        } else if (textColourCount == 2) {
            mainTextArea.setForeground(Color.orange);
        } else if (textColourCount == 3) {
            mainTextArea.setForeground(Color.pink);
        } else if (textColourCount == 4) {
            mainTextArea.setForeground(Color.green);
        } else if (textColourCount == 5) {
            mainTextArea.setForeground(Color.cyan);
        } else {
            textColourCount = 0;
        }
    }

    public void backgroundColourCounter() {
        if (backgroundColourCount == 1)
        {
            mainTextArea.setBackground(Color.darkGray);
            container.setBackground(Color.darkGray);
            mainTextPanel.setBackground(Color.darkGray);
        }
        else if (backgroundColourCount == 2)
        {
            mainTextArea.setBackground(Color.orange);
            container.setBackground(Color.orange);
            mainTextPanel.setBackground(Color.orange);
        }
        else if (backgroundColourCount == 3)
        {
            mainTextArea.setBackground(Color.pink);
            container.setBackground(Color.pink);
            mainTextPanel.setBackground(Color.pink);
        }
        else if (backgroundColourCount == 4)
        {
            mainTextArea.setBackground(Color.green);
            container.setBackground(Color.green);
            mainTextPanel.setBackground(Color.green);
        }
        else if (backgroundColourCount == 5)
        {
            mainTextArea.setBackground(Color.cyan);
            container.setBackground(Color.cyan);
            mainTextPanel.setBackground(Color.cyan);
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
            //FirstScene();

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

            mainMenuGUI();


        }
    }

    public class continueButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {}
    }


    public class changeTextColourHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

            textColourCount++;
            textColourCounter();


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

    public class changeTextFontHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

            fontTypeCount++;
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
                case "townGate":
                    switch(yourChoice)
                    {
                        case "c1": talkGuard(); break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
                    break;
                case "talkGuard":
                    switch (yourChoice)
                    {
                        case "c1": townGate(); break;
                        case "c2": break;
                        case "c3": break;
                        case "c4": break;
                    }
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

