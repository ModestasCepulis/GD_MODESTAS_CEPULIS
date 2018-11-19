public class Player {


    int playerHP;
    String playerItem;

    public void setPlayerHP(int playerHP)
    {this.playerHP = playerHP;}

    public int getPlayerHP()
    {return playerHP;}

    public void setPlayerItem(String playerItem)
    {this.playerItem = playerItem;}

    public String getPlayerItem()
    {return playerItem;}

    public Player()
    {
        this.playerHP = 0;
        this.playerItem = "No item";
    }

    public Player(int playerHP, String playerItem)
    {
        setPlayerHP(playerHP);
        setPlayerItem(playerItem);
    }







}
