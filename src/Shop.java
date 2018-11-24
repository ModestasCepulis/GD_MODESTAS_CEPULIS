import javax.swing.*;

/**
 * This class manages the shop system.
 */

public class Shop extends Game{

    @Override
    public void buyingItems() {
        super.buyingItems();
    }

    //===============Shop system================
    /**
     *
     * @param itemsToBuy
     * This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     * @param firstItemRandomPrice
     * This is a Math.random method which gets the random price for the item that the user wants to buy.
     * @param firstItemRandomDamage
     * This is Math.Random method which gets the random damage for the item that the user wants to buy.
     * @param differentItemCombinations
     * This is an array that creates different item names combination by joining 2 different random strings together
     */
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

    /**
     *
     * @param itemsToBuy
     * This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     * @param secondItemRandomPrice
     * This is a Math.random method which gets the random price for the item that the user wants to buy.
     * @param secondItemRandomDamage
     * This is Math.Random method which gets the random damage for the item that the user wants to buy.
     * @param differentItemCombinations
     * This is an array that creates different item names combination by joining 2 different random strings together
     */
    //The second items that the player buys
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

    /**
     *
     * @param itemsToBuy
     * This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     * @param thirdItemRandomPrice
     * This is a Math.random method which gets the random price for the item that the user wants to buy.
     * @param thirdItemRandomDamage
     * This is Math.Random method which gets the random damage for the item that the user wants to buy.
     * @param differentItemCombinations
     * This is an array that creates different item names combination by joining 2 different random strings together
     */
    //third item that the player buys
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


    /**
     *
     * @param itemsToBuy
     * This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     * @param fourthItemRandomPrice
     * This is a Math.random method which gets the random price for the item that the user wants to buy.
     * @param fourthItemRandomDamage
     * This is Math.Random method which gets the random damage for the item that the user wants to buy.
     * @param differentItemCombinations
     * This is an array that creates different item names combination by joining 2 different random strings together
     */
    //fourth item that the player buys
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


    /**
     *
     * @param itemsToBuy
     *  This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     *  in this case the user inputs 6 - which leads to the user quitting the shop system
     */
    //the exit phase
    public static void exitPhase(int itemsToBuy)
    {
        if(itemsToBuy == 6)
        {
            JOptionPane.showMessageDialog(null,"Thank you for using the market traveler. Bye now.");
        }

    }


    /**
     *
     * @param itemsToBuy
     *  This checks the user input. (If the user inputs 1,2,3,4,5,6 the system works and does whatever it needs to, if not, the error message pops up.
     *  For this case the user inputs 5 - which leads to the user buying an apple
     */
    //this is when the player buys an apple
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

    /**
     *
     * @param firstItemRandomPrice
     * this sets the selling price of the user item to a random int number generated by Math.Random
     */

    public static void sellingItems(int firstItemRandomPrice) {
        try {

            String sellChoiceAsString = JOptionPane.showInputDialog(null, "Are you sure you want to sell your " + thePlayer.getPlayerItem() + " ?"
                    + "\nFor " + firstItemRandomPrice + " amounts of gold?"
                    + "\n1. Yes."
                    + "\n2. No.");
            int sellChoiceAsInt = Integer.parseInt(sellChoiceAsString);

            if (sellChoiceAsInt == 1) {
                itemLabelName.setText("Item sold");
                JOptionPane.showMessageDialog(null, "You sold your " + thePlayer.getPlayerItem() + " for " + firstItemRandomPrice + " amount of gold.");
                thePlayer.setPlayerMoney(thePlayer.playerMoney + firstItemRandomPrice);

            } else {
                JOptionPane.showMessageDialog(null, "Thanks for using the shop!");
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Something went wrong, please try again.");
        }

        }

    }

