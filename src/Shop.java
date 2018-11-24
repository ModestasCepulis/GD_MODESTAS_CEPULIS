import javax.swing.*;

public class Shop extends Game{


    @Override
    public void buyingItems() {
        super.buyingItems();
    }

    //===============Shop system================

    public static void itemsToBuy1(int itemsToBuy, int firstItemRandomPrice, int firstItemRandomDamage, String[] differentItemCombinations)
    {

        if(itemsToBuy == 1 || itemsToBuy == 2 || itemsToBuy == 3 || itemsToBuy == 4 || itemsToBuy == 5 || itemsToBuy == 6)
        {
            if (itemsToBuy == 1) {
                if (thePlayer.getPlayerMoney()> firstItemRandomPrice) {

                    //this takes away the money required to buy the item.
                    thePlayer.setPlayerMoney(thePlayer.getPlayerMoney() - firstItemRandomPrice);

                    thePlayer.setPlayerItem(differentItemCombinations[0]);
                    //this actually changes the current text to whatever we have it set with .setPlayerItem
                    itemLabelName.setText(thePlayer.getPlayerItem());

                    //this sets the additional attack that the player gets

                    thePlayer.setPlayerAttack((int) (Math.random() * ((5) + 2)) + firstItemRandomDamage);

                    //this adds the damage to the player stats

                    JOptionPane.showMessageDialog(null, "Congratulations, you just bought: " + differentItemCombinations[0]
                            + "\nThe Item cost you: " + firstItemRandomPrice + " gold."
                            + "\nYou get additional: " + firstItemRandomDamage + " Damage."

                            + "\nYour current money status is: " + thePlayer.getPlayerMoney());


                }
                if (thePlayer.getPlayerMoney() < firstItemRandomPrice) {
                    JOptionPane.showMessageDialog(null, "Sorry, but you don't have enough money, you should go back to the arena and earn more!");
                }

            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You did not enter a valid number! Please try again.");
        }



    }

    public static void itemsToBuy2(int itemsToBuy, int secondItemRandomPrice, int secondItemRandomDamage, String[] differentItemCombinations)
    {
        if(itemsToBuy == 1 || itemsToBuy == 2 || itemsToBuy == 3 || itemsToBuy == 4 || itemsToBuy == 5) {
            if (itemsToBuy == 2) {
                if (thePlayer.getPlayerMoney() > secondItemRandomPrice) {

                    //this takes away the money required to buy the item.
                    thePlayer.setPlayerMoney(thePlayer.getPlayerMoney() - secondItemRandomPrice);

                    thePlayer.setPlayerItem(differentItemCombinations[1]);
                    //this actually changes the current text to whatever we have it set with .setPlayerItem
                    itemLabelName.setText(thePlayer.getPlayerItem());

                    //this sets the additional attack that the player gets
                    thePlayer.setPlayerAttack((int) (Math.random() * ((5) + 2)) + secondItemRandomDamage);

                    //this adds the damage to the player stats

                    JOptionPane.showMessageDialog(null, "Congratulations, you just bought: " + differentItemCombinations[1]
                            + "\nThe Item cost you: " + secondItemRandomPrice + " gold."
                            + "\nYou get additional: " + secondItemRandomDamage + " Damage."

                            + "\nYour current money status is: " + thePlayer.getPlayerMoney());


                }
                if (thePlayer.getPlayerMoney() < secondItemRandomPrice) {
                    JOptionPane.showMessageDialog(null, "Sorry, but you don't have enough money, you should go back to the arena and earn more!");
                }

            }
        }

    }

    public static void itemsToBuy3(int itemsToBuy, int thirdItemRandomPrice, int thirdItemRandomDamage, String[] differentItemCombinations)
    {
        if(itemsToBuy == 1 || itemsToBuy == 2 || itemsToBuy == 3 || itemsToBuy == 4 || itemsToBuy == 5) {
            if (itemsToBuy == 3) {
                if (thePlayer.getPlayerMoney() > thirdItemRandomPrice) {

                    //this takes away the money required to buy the item.
                    thePlayer.setPlayerMoney(thePlayer.getPlayerMoney() - thirdItemRandomPrice);

                    thePlayer.setPlayerItem(differentItemCombinations[2]);
                    //this actually changes the current text to whatever we have it set with .setPlayerItem
                    itemLabelName.setText(thePlayer.getPlayerItem());

                    //this sets the additional attack that the player gets
                    thePlayer.setPlayerAttack((int) (Math.random() * ((5) + 2)) + thirdItemRandomDamage);

                    //this adds the damage to the player stats

                    JOptionPane.showMessageDialog(null, "Congratulations, you just bought: " + differentItemCombinations[2]
                            + "\nThe Item cost you: " + thirdItemRandomPrice + " gold."
                            + "\nYou get additional: " + thirdItemRandomDamage + " Damage."

                            + "\nYour current money status is: " + thePlayer.getPlayerMoney());


                }
                if (thePlayer.getPlayerMoney() < thirdItemRandomPrice) {
                    JOptionPane.showMessageDialog(null, "Sorry, but you don't have enough money, you should go back to the arena and earn more!");
                }
            }
        }

    }

    public static void itemsToBuy4(int itemsToBuy, int fourthItemRandomPrice, int fourthItemRandomDamage, String[] differentItemCombinations)
    {
        if(itemsToBuy == 1 || itemsToBuy == 2 || itemsToBuy == 3 || itemsToBuy == 4 || itemsToBuy == 5) {
            if (itemsToBuy == 4) {
                if (thePlayer.getPlayerMoney() > fourthItemRandomPrice) {

                    //this takes away the money required to buy the item.
                    thePlayer.setPlayerMoney(thePlayer.getPlayerMoney() - fourthItemRandomPrice);

                    thePlayer.setPlayerItem(differentItemCombinations[3]);
                    //this actually changes the current text to whatever we have it set with .setPlayerItem
                    itemLabelName.setText(thePlayer.getPlayerItem());


                    //this sets the additional attack that the player gets
                    thePlayer.setPlayerAttack((int) (Math.random() * ((5) + 2)) + fourthItemRandomDamage);

                    //this adds the damage to the player stats

                    JOptionPane.showMessageDialog(null, "Congratulations, you just bought: " + differentItemCombinations[3]
                            + "\nThe Item cost you: " + fourthItemRandomPrice + " gold."
                            + "\nYou get additional: " + fourthItemRandomDamage + " Damage."

                            + "\nYour current money status is: " + thePlayer.getPlayerMoney());


                }
                if (thePlayer.getPlayerMoney() < fourthItemRandomPrice) {
                    JOptionPane.showMessageDialog(null, "Sorry, but you don't have enough money, you should go back to the arena and earn more!");
                }
            }
        }

    }

    public static void exitPhase(int itemsToBuy)
    {
        if(itemsToBuy == 6)
        {
            JOptionPane.showMessageDialog(null,"Thank you for using the market traveler. Bye now.");
        }

    }

    public static void buyingAnApple(int itemsToBuy)
    {
        if(itemsToBuy == 5)
        {
            if(thePlayer.getPlayerMoney() > 15) {
                thePlayer.setPlayerMoney(thePlayer.getPlayerMoney() - 15);
                JOptionPane.showMessageDialog(null, "You just bought an apple! You have total of " + appleItemCount + " apples.");
                appleItemCount++;
            }
            else {
                JOptionPane.showMessageDialog(null, "Sorry, but you don't have enough money, you should go back to the arena and earn more!");
            }
        }
    }
}
