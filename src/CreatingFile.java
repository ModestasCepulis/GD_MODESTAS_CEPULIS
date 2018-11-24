import javax.swing.*;
import java.util.Formatter;

/**
 * This class creates the .txt file
 * @author https://www.youtube.com/watch?v=Bws9aQuAcdg&t=376s
 */

public class CreatingFile {

    Formatter creator;

     public void openFile()
     {
         try{
            creator = new Formatter("Username.txt");//this creates a new file called username.
         }
         catch (Exception e)
         {
             JOptionPane.showMessageDialog(null,"There was an erorr.");
         }
     }

     public void addRecords()
     {
         creator.format("%s",Game.playerUsername); //this puts the user input string to the file.
     }

     public void closeFile()
     {
         creator.close();
     }
}
