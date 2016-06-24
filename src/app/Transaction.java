package src.app;

public class Transaction
{
  // static variables
  private static int currentID = 0;

  // instance variables
  private int id;

  private double amount;

  private String description;
  private String location;
  private String vendor;

  // enums
  // account type: savings, checking, credit
  // transaction type: withdrawal, deposit ... credit => advance, payment (probably exclude these)

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
