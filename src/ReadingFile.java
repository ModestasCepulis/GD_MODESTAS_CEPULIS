import javax.swing.*;
import java.io.File;
import java.util.Scanner;

/**
 * This class reads the .txt file
 * @author https://www.youtube.com/watch?v=3RNYUKxAgmw&t=303s
 */

public class ReadingFile {

    Scanner reader;
    static String playerUsernameString;

    public void openFile()
    {
        try {
            reader = new Scanner(new File("Username.txt"));//this reads from a txt file called Username.
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Could not find the file");
        }

    }

    public void readFile()
    {
        while(reader.hasNext())
        {
            playerUsernameString = reader.next();//this puts the next line on the txt file to the playerUsernameString attribute

        }
    }

    public void closeFile()
    {
        reader.close();
    }
}
