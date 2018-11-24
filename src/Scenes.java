import javax.swing.*;

public class Scenes {

    Player thePlayer = new Player();

    String mainTextArea;
    String choice1;
    String choice2;
    String choice3;
    String choice4;

    public Scenes(){

    }

    public void lookAround()
    {
        mainTextArea = "You lift your head and look around the room, there is no much light, but you can see " +
                           " that the ceiling, floor and eveything around you is made out of wet mud and dirt. In the corner" +
                           " of the cave    you can see a small candle burning away. By that candle you can see that one of the wall" +
                            " is more sledged inwards than any other walls inside of this 'room'. What is your next move?";

        choice1 = "Go to the candle";
        choice2 = "Go to the wall";
        choice3 = "Shout for help";
        choice4 = "Try to fall asleep";
    }

    public void goToTheCandle()
    {
        mainTextArea = "You move closer to the candle. You can see that the candle is really old it was made by hand from natural bee wax " +
                       "It seems that the candle is about to burn out... What do you do?";

        choice1 = "Pick up the candle";
        choice2 = "Go to the wall";
        choice3 = "Go back to your previous position";
        choice4 = "Extinguish the fire";

    }

    public void pickUpTheCandle()
    {
        mainTextArea = "You picked up the candle. Now you can move the source of light around you, it will help you to see things more clearly.";

        choice1 = "Go to the wall";
        choice2 = "Go back to the previous position";
        choice3 = "Shout for help";
        choice4 = "Try to fall asleep";
    }

    public void goToTheLight()
    {
        mainTextArea = "As you move closer to the light you realised that the light is actually coming out of an object " +
                       "and the object reminds you of a candle.";

        choice1 = "Pick up the candle";
        choice2 = "Go back to the previous position";
        choice3 = "Shout for help";
        choice4 = "Try to fall asleep";
    }

    public void shoutForHelp()
    {
        mainTextArea = "You scream out top of your lungs... No answer. Is anyone even here? As it seems" +
                "   you can only hear your dry voice echoing around the place...";

        choice1 = "Look around";
        choice2 = "Go to the light";
        choice3 = "------";
        choice4 = "Try to fall asleep";
    }

    public void tryToFallAsleep()
    {
        mainTextArea = "You walk to the nearest corner, lay down and close your eyes... 'Maybe this will help' " +
                " - you think to yourself. As easy it is to fall asleep in a cave, you just can't really do it. ";

        choice1 = "Look around";
        choice2 = "Go to the light";
        choice3 = "Shout for help";
        choice4 = "------";
    }

    public void goToTheWall()
    {
        mainTextArea = "You move towards the wall. While you were looking at it for couple of minutes you realised that " +
                " the 'inward' section of the wall was an actual wooden door. ";

        choice1 = "Knock on the door";
        choice2 = "Go back to the previous position";
        choice3 = "Try to open the door";
        choice4 = "------";
    }

    public void knockOnDoor()
    {
        mainTextArea = "You lift your hand towards the door and you give it few hard knocks... No answer... ";

        choice1 = "------";
        choice2 = "Go back to the previous position";
        choice3 = "Try to open the door";
        choice4 = "------";
    }

    public void openTheDoor()
    {
        mainTextArea = "You extend your arms towards the door, you put your whole weight on it and try to push " +
                "The door makes a loud cranky noise and the door opens...";

        choice1 = "Enter";
        choice2 = "------";
        choice3 = "------";
        choice4 = "------";
    }

    public void extinguishTheFire()
    {
        mainTextArea = "You move the palm over the candle to put it out, you succeed but you lose some health and a light source." +
                " Why did you do that?";

        choice1 = "------";
        choice2 = "Go to the wall";
        choice3 = "Go back to your previous position";
        choice4 = "------";
    }

    public void enterTheDoor()
    {
        mainTextArea = "You enter the room, this room is even smaller than the one you came from," +
                " you can see various objects around you, from table with chairs, fireplace, big metal door, " +
                "and? what? you can't believe your eyes... Who is that?" +
                "\n\nGuard: You shouldn't suppouse to be here! Now you will have to pay.";

        choice1="Attack the guard";
        choice2="------";
        choice3="------";
        choice4="------";
    }

    public void guardKilled()
    {
        mainTextArea="Guards current health: 0 " +
                "\nCongratulations! You've survived and killed the guard! \nWhat is your next move?";

        choice1="Search the guard";
        choice2="Go to the door and open it";
        choice3="------";
        choice4="------";
    }

    public void playerKilled()
    {
        mainTextArea="Players current health: " + thePlayer.getPlayerHP() +
                "\nIt seems that you've messed up, you might've died or your hp is too low " +
                " to continue the fight..." +
                "\n well its not that bad at it seems, it just that you will have to try again...";

        choice1="Go to the start";
        choice2="------";
        choice3="------";
        choice4="------";
    }

    public void searchTheGuard()
    {
        mainTextArea="You lowered your body towards the guard. He's dead. You decide to look through his items." +
                "You managed to find few items: A dagger, an apple and bunch of old keys.";

        choice1="Go to the metal door";
        choice2="Eat the apple";
        choice3="Equip the dagger";
        choice4="------";
    }

    public void goMetalDoorKeyEquipped()
    {
            mainTextArea="You walked to the door, it seems that it is locked. You remembered that you have bunch of old " +
                    "keys that you got from the guard. You spent 15 minutes trying every rusted key and eventually one of them " +
                    "worked. You put the key in, pushed right and the big metal door slowly opened...";

            choice1="Continue";
            choice2="------";
            choice3="------";
            choice4="------";
    }

    public void goMetalDoorNoKey()
    {
        mainTextArea="You tried to open the door. It didn't work, it seems that it is locked. " +
                "You will have to find something to open the door with. Go back.";

        choice1="------";
        choice2="------";
        choice3="Go to previous position";
        choice4="Search the guard";
    }


    public void eatTheApple()
    {
        mainTextArea="You check the guards body and find an apple, maybe its still good, who knows, " +
                " but you eat it anyway. It is not much, but still better than nothing. ";

        choice1="Go to the metal door";
        choice2="------";
        choice3="Equip the dagger";
        choice4="------";
    }

    public void equipDagger()
    {

        mainTextArea="You check the guards body and find a dagger, it doesn't look sharp, but it is better than fighting with." +
                " So you decide to throw away your weapon and equip the new one. ";


        choice1="Go to the metal door";
        choice2="------";
        choice3="------";
        choice4="------";
    }

    public void ContinueMetalDoor()
    {
        mainTextArea="As you push the door open, you can finally start smelling something more than just wet mud and dirt." +
                "\nYou started to hear sounds again, see the sunshine in your eyes, but where are you?" +
                "\nThis place oddly reminds you of some kind of a market...";


        choice1="Continue";
        choice2="------";
        choice3="------";
        choice4="------";

    }

    public void runFromAttack()
    {
        mainTextArea="You have managed to escape the fight...";

        choice1="Go back to the town";
        choice2="------";
        choice3="------";
        choice4="------";
    }

    public void enteringTheMarketScene()
    {
        mainTextArea="Finally, you've managed to escape that dirty place... " +
                "\nAs you look around, you realise that there is 3 paths that you can take." +
                "\nOne of the path is leading to the market" +
                "\nSecond path is leading to the billboard." +
                "\nThird path is leaving to a boats.";

        choice1="Go to the market";
        choice2="Go to the billboard";
        choice3="Go to the boats";
        choice4="------";

    }

    public void marketPlace()
    {
        mainTextArea="You started to walk to the market place, you can see different types of shops that sell different kind of stuff." +
                "\nWhat would you like to do?";

        choice1="Buy items";
        choice2="Sell items";
        choice3="Go back to the town";
        choice4="------";

    }

    public void billBoardDuels()
    {
        mainTextArea="You reached the billboard. Right in front of you, you can see different type of ads. " +
                "\nSome are looking for a chef, new butcher or a cleaner. But one of the ads gets your attention." +
                "\nDUEL ARENA - TEST YOUR SKILLS AGAINST CHALLENGING ENEMIES AND GET SWEET REWARDS!";

        choice1="Enter the duel arena";
        choice2="Go back to the town";
        choice3="------";
        choice4="------";

    }

    public void duelEnemyKilled()
    {
        mainTextArea="Congratulations! You have defeated the enemy!";

        choice1="Go back to the town";
        choice2="------";
        choice3="------";
        choice4="------";
    }

    public void leavingTheTown()
    {
        mainTextArea="So you decided to leave this town already? You can, but you will need a lot of money to do that...";

        choice1="Leave the town (5,000 gold|)";
        choice2="Go back to town";
        choice3="------";
        choice4="------";
    }

}
