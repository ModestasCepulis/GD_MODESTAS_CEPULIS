import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGuiTest extends JFrame {

    JFrame window;

    Container container;

    JPanel titleNamePanel;
    JPanel startButtonPanel;
    JPanel optionsButtonPanel;
    JPanel aboutTheGameButtonPanel;
    JPanel exitGameButtonPanel;
    JPanel mainTextPanel;

    JLabel titleNameLabel;

    JButton startButton;
    JButton optionsButton;
    JButton aboutTheGameButton;
    JButton exitGameButton;

    JTextArea mainTextArea;

    StartGameHandler startGameHdlr = new StartGameHandler();
    OptionsHandler OptionsHdlr = new OptionsHandler();
    AboutGameHandler aboutGameHdlr = new AboutGameHandler();

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 30);

    public static void main(String[] args) {

        new MyGuiTest();
    }

    public MyGuiTest()

    {
        setSize(1200, 800);
        //This sets the JFrame to be always in the middle regardless of the screen size
        //Code taken from:
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        container = getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,1000,150);
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

    //===================BUTTON METHODS===================
    public void startGameButton()
    {
        //===================START THE GAME BUTTON===================
        startButton = new JButton("Start the game");
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.white);
        startButton.setFont(buttonFont);
        //This creates a button with an empty border.
        //Code was taken from:
        //https://alvinalexander.com/source-code/java/java-jbutton-create-jbutton-no-border-borderfactorycreateemptyborder
        startButton.setBorder(BorderFactory.createEmptyBorder());

        //This adds an action listener to the button which allows the button to do somethnig
        startButton.addActionListener(startGameHdlr);
    }

    public  void optionsButton()
    {
        //===================OPTIONS BUTTON===================
        optionsButton = new JButton("Options");
        optionsButton.setBackground(Color.BLUE);
        optionsButton.setForeground(Color.white);
        optionsButton.setFont(buttonFont);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.addActionListener(OptionsHdlr);
    }

    public void aboutTheGameButton()
    {
        //===================ABOUT THE GAME BUTTON===================
        aboutTheGameButton = new JButton("About the game");
        aboutTheGameButton.setBackground(Color.BLUE);
        aboutTheGameButton.setForeground(Color.white);
        aboutTheGameButton.setFont(buttonFont);
        aboutTheGameButton.setBorder(BorderFactory.createEmptyBorder());
        aboutTheGameButton.addActionListener(aboutGameHdlr);
    }

    public void exitGameButton()
    {
        //===================EXIT THE GAME BUTTON===================
        exitGameButton = new JButton("Exit");
        exitGameButton.setBackground(Color.BLUE);
        exitGameButton.setForeground(Color.white);
        exitGameButton.setFont(buttonFont);
        exitGameButton.setBorder(BorderFactory.createEmptyBorder());
    }

    //========================PANEL METHODS===================
    public void startButtonPanel()
    {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500, 300,200,50);
        startButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void optionsButtonPanel()
    {
        optionsButtonPanel = new JPanel();
        optionsButtonPanel.setBounds(500, 350,200,50);
        optionsButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void aboutTheGameButtonPanel()
    {
        aboutTheGameButtonPanel = new JPanel();
        aboutTheGameButtonPanel.setBounds(500, 400,200,50);
        aboutTheGameButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void exitGameButtonPanel()
    {
        exitGameButtonPanel = new JPanel();
        exitGameButtonPanel.setBounds(500, 450,200,50);
        exitGameButtonPanel.setBackground(Color.DARK_GRAY);
    }

    public void afterStartButtonScene()
    {
        disableAllPanels();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,1000,250);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Color.blue);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(buttonFont);
        //This automatically wraps the text to the next line
        //Code taken from:
        //https://stackoverflow.com/questions/7861724/is-there-a-word-wrap-property-for-jlabel
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    public void afterAboutGameButtonScene()
    {
        disableAllPanels();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,1000,250);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Color.blue);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(buttonFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    public void afterOptionsButtonScene()
    {
        disableAllPanels();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,1000,250);
        mainTextPanel.setBackground(Color.blue);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Color.blue);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(buttonFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }



    public void testNewJFrameWindow()
    {
        JFrame window2 = new JFrame();
        window2.setSize(1200, 800);
        //This sets the JFrame to be always in the middle regardless of the screen size
        //Code taken from:
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window2.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        window2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window2.getContentPane().setBackground(Color.DARK_GRAY);
        window2.setLayout(null);
        container = getContentPane();
        window2.setVisible(true);
        setVisible(false);
    }

    //==================BUTTON EVENT HANDLERS===============

    public class StartGameHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            //testNewJFrameWindow();
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

