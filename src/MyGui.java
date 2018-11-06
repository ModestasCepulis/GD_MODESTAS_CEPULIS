import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JFrame extends MyGui which means that we can use the public methods that are in JFrame class
public class MyGui extends JFrame {

    JPanel launchGamePanel = new JPanel();
    JPanel firstScenePanel = new JPanel();
    
    public static void main(String[] args) {
        new MyGui();
    }

    public MyGui()
    {
        //=================LAUNCH TEXT AREA ======================
        //Creates a button
        JButton launchSceneButton = new JButton("Start");
        JTextArea launchTextarea = new JTextArea();
        //Sets the text area text to be
        launchTextarea.setText("\n\n\n\tBegin your Adventure!");
        //Creates a Font object which sets the font parameters
        Font font  = new Font("Tahoma", Font.BOLD, 50);
        //Sets the text area font to be equal to our Font object
        launchTextarea.setFont(font);
        //Block the user from editing the textArea
        launchTextarea.setEditable(false);
        launchTextarea.setForeground(Color.white);
        launchTextarea.setBackground(Color.DARK_GRAY);
        //=================LAUNCH TEXT AREA END ======================


        //=================FIRST SCENE TEXT AREA ======================
        //Creates a button
        JButton firstChoiceButton = new JButton("Up");
        JButton secondChoiceButton = new JButton("Left");
        JButton thirdChoiceButton = new JButton("Right");
        JTextArea firstTextArea = new JTextArea();
        //Sets the text area text to be
        firstTextArea.setText("\n\n\n\tWhere would you want to go?");
        //Creates a Font object which sets the font parameters
        Font font2  = new Font("Tahoma", Font.BOLD, 50);
        //Sets the text area font to be equal to our Font object
        firstTextArea.setFont(font2);
        //Block the user from editing the textArea
        firstTextArea.setEditable(false);
        firstTextArea.setForeground(Color.white);
        firstTextArea.setBackground(Color.DARK_GRAY);
        //=================LAUNCH TEXT AREA END ======================


        LaunchScene(launchTextarea, launchSceneButton);
        firstLaunchPanel(firstTextArea, firstChoiceButton, secondChoiceButton, thirdChoiceButton);


        //---------JFrame------------
        //Values that set the final int value for the frame width and height
        int FRAME_WIDTH = 1400;
        int FRAME_HEIGHT = 700;
        //Values that set the final value for the frame location (x and y)
        int FRAME_LOC_X = 250;
        int FRAME_LOC_Y = 130;
        //Sets the frame to be visible
        setVisible(true);
        //This regulates the size of the frame
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //This sets the window in the spiecified location
        setLocation(FRAME_LOC_X,FRAME_LOC_Y);
        //Registers 'Exit upon closing' as close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(launchGamePanel);


        //What happens when the button is pressed
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //This sets the current panel(scene) (launch game panel) to false and then adds the
                //next panel(scene)
                launchGamePanel.setVisible(false);
                add(firstScenePanel);
            }
        };
        launchSceneButton.addActionListener(listener);

    }

   public void LaunchScene(JTextArea launchTextarea, JButton launchSceneButton)
   {
       launchGamePanel.setBackground(Color.darkGray);
       launchGamePanel.add(launchTextarea);
       launchGamePanel.add(launchSceneButton);
   }

   public void firstLaunchPanel(JTextArea firstTextArea, JButton firstChoiceButton, JButton secondChoiceButton, JButton thirdChoiceButton)
   {
       firstScenePanel.setBackground(Color.DARK_GRAY);
       firstScenePanel.add(firstTextArea);
       firstScenePanel.add(firstChoiceButton);
       firstScenePanel.add(secondChoiceButton);
       firstScenePanel.add(thirdChoiceButton);

   }
}
