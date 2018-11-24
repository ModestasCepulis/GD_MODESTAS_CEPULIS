public class Player implements IPlayer  {


    int playerHP, playerMoney, playerAttack;
    String playerItem;

    public void setPlayerHP(int playerHP)
    {this.playerHP = playerHP;}

   // public int getPlayerHP()
    //{return playerHP;}

    public void setPlayerAttack(int playerAttack)
    {this.playerAttack = playerAttack;}

   // public int getPlayerAttack()
   // {return playerAttack;}

    public void setPlayerMoney(int playerMoney)
    {this.playerMoney = playerMoney;}

   // public int getPlayerMoney()
  //  {return playerMoney;}

    public void setPlayerItem(String playerItem)
    {this.playerItem = playerItem;}

  //  public String getPlayerItem()
   // {return playerItem;}

    public Player()
    {
        this.playerHP = 0;
        this.playerMoney = 0;
        this.playerAttack = 0;
        this.playerItem = "fists";
    }

    public Player(int playerHP, int playerMoney, int playerAttack, String playerItem)
    {
        setPlayerHP(playerHP);
        setPlayerAttack(playerAttack);
        setPlayerItem(playerItem);
        setPlayerMoney(playerMoney);
    }


    @Override
    public String getPlayerItem() {
        return playerItem;
    }

    @Override
    public int getPlayerHP() {
        return playerHP;
    }

    @Override
    public int getPlayerMoney() {
        return playerMoney;
    }

    @Override
    public int getPlayerAttack() {
        return playerAttack;
    }
}
