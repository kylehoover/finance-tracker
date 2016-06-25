package src.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction
{
  // static variables
  private static int currentID = 0;

  // instance variables
  private final int id;

  private final double amount;

  private final Date date;
  private final String description;
  private final String location;
  private final String vendor;

  // Category

  // enums
  // account type: savings, checking, credit
  // transaction type: withdrawal, deposit ... credit => advance, payment (probably exclude these)

  private Transaction(Builder builder)
  {
    id = currentID++;
    amount = builder.amount;
    date = builder.date;
    description = builder.description;
    location = builder.location;
    vendor = builder.vendor;
  }

  // getter methods
  public int getID()
  {
    return id;
  }

  public String toString()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
    return getClass().getName() + "[id=" + id + ",amount=" + amount + ",date=" + sdf.format(date) + ",description=" + description + ",location=" + location + ",vendor=" + vendor + "]";
  }

  public static class Builder
  {
    // required parameters
    private final double amount;
    private final Date date;
    private final String vendor;

    // optional parameters
    private String description = "";
    private String location = "";

    public Builder(String vendor, double amount, String date)
    {
      this.amount = amount;
      this.vendor = vendor;

      SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
      Date d;
      try {
        d = sdf.parse(date);
      } catch (ParseException e) {
        d = null;
      }

      this.date = d;
    }

    public Builder description(String description)
    {
      this.description = description;
      return this;
    }

    public Builder location(String location)
    {
      this.location = location;
      return this;
    }

    public Transaction build()
    {
      return new Transaction(this);
    }
  }
}
