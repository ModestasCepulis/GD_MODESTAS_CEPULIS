import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

//JFrame extends MyGui which means that we can use the public methods that are in JFrame class
public class MyGui extends JFrame {


    public static void main(String[] args) {

        new MyGui();

    }

    public MyGui()
    {






        //---------First Launch Panel------------

        //Creates a text area
        JTextArea textArea = new JTextArea();
        //Sets the text area text to be
        textArea.setText("\n\n\nBegin your Adventure!");
        //Creates a Font object which sets the font parameters
        Font font = new Font("Segoe Script", Font.BOLD, 50);
        //Sets the text area font to be equal to our Font object
        textArea.setFont(font);
        //Block the user from editing the textArea
        textArea.setEditable(false);
        textArea.setForeground(Color.white);
        textArea.setBackground(Color.DARK_GRAY);

        /*
        //Creates a label area (Like a title)
        JLabel label = new JLabel();
        label.setText("Begin your Adventure!");
        */

        //This creates a panel, (More like a holder) for the objects that you
        //want to keep in this specific container and which ones you want to show/add
        JPanel panel = new JPanel();
        panel.add(textArea);
        //Adds specified objects to the panel
        /*panel.add(label);*/



        //---------JFrame------------
        //Values that set the final int value for the frame width and height
        final int FRAME_WIDTH = 900;
        final int FRAME_HEIGHT = 700;
        //Values that set the final value for the frame location (x and y)
        final int FRAME_LOC_X = 350;
        final int FRAME_LOC_Y = 230;
        //Sets the frame to be visible
        setVisible(true);
        //This regulates the size of the frame
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //This sets the window in the spiecified location
        setLocation(FRAME_LOC_X,FRAME_LOC_Y);
        //Registers 'Exit upon closing' as close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //---------JPane------------
        //This is the middle pane of the frame
        Container cPane = getContentPane();
        //This Changes the background color of the pane
        cPane.setBackground(Color.red);
        //Adds the textArea to the pane.
        cPane.add(textArea);

    }
}
