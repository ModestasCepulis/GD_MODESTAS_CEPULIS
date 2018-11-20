import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class FightingTest extends JFrame {

    public static void main(String[] args) {

        String enemy;
        int playerHealth=100, enemyHealth=100, playerDamage=0, goblinDamage=0;
        int currentPlayerHealth=100, currentEnemyHealth=100;

        int enemyHealth2=100;

        int choice;


        String[] enemies = new String[5];
        enemies[0] = "Goblin";
        enemies[1] = "sheep";
        enemies[2] = "crocodile";
        enemies[3] = "treeman";
        enemies[4] = "hee-man";

        Scanner scanner = new Scanner(System.in);

        //this command gets the Math.random for integer and creates a random number between 0 and 4
        System.out.print("\nWild " + enemies[(int)(Math.random() * ((4) + 1))] + " appears");


        System.out.println("\nPlease write what to do next: 1 - attack, 2 - nothing");
        choice = scanner.nextInt();



        if(choice == 1)
        {
            while(enemyHealth2>0) {

                int attack = (int) (Math.random() * ((5) + 2));
                enemyHealth2 = enemyHealth2 - attack;

                if(enemyHealth2>=-5)
                System.out.print("\nYou attacked the enemy. \nYou have dealt: " + attack +
                        "\n\nCurrent enemy health: " + (enemyHealth2));
                if(enemyHealth2<=0)
                    System.out.print("\n\nCongratulations, you killed the enemy.");
            }
        }
        else
        {
            System.out.print("makslrmasklr");
        }

    }
}
