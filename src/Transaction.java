package src;

public class Transaction
{
  // static variables
  private static int currentID = 0;

  // instance variables
  private int id;

  public Transaction()
  {
    id = currentID++;
  }

  // getter methods
  public int getID()
  {
    return id;
  }

}
