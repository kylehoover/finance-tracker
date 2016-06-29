package src.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
  // static variables
  private static int currentID = 0;

  // instance variables
  private final int id;
  private final double amount;
  private final Date date;
  private final String description;
  private final String location;
  private final String vendor;
  private final Account account;
  private final Type type;
  private final ArrayList<String> categories;

  // constructor
  private Transaction(Builder builder) {
    id = currentID++;
    amount = builder.amount;
    date = builder.date;
    description = builder.description;
    location = builder.location;
    vendor = builder.vendor;
    account = builder.account;
    type = builder.type;
    categories = builder.categories;
  }

  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
    return getClass().getName() + "[id=" + id + ",amount=" + amount + ",date=" + sdf.format(date) + ",description=" + description + ",location=" + location
           + ",vendor=" + vendor + ",account=" + account + ",type=" + type + ",categories=" + categories.toString() + "]";
  }


  // eunms
  public enum Account {
    CHECKING, CREDIT, SAVINGS
  }

  public enum Type {
    DEPOSIT, WITHDRAWAL
  }


  // builder class
  public static class Builder {
    // required parameters
    private final double amount;
    private final Date date;
    private final String vendor;

    // optional parameters
    private String description = "";
    private String location = "";
    private Account account = Account.CREDIT;
    private Type type = Type.WITHDRAWAL;
    private ArrayList<String> categories = new ArrayList<>();

    public Builder(String vendor, double amount, String date) {
      this.amount = amount;
      this.vendor = vendor;

      // format date
      SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
      Date d;

      try {
        d = sdf.parse(date);
      } catch (ParseException e) {
        d = null;
      }

      this.date = d;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Builder account(Account account) {
      this.account = account;
      return this;
    }

    public Builder type(Type type) {
      this.type = type;
      return this;
    }

    public Builder categories(String... args) {
      for (String s : args) {
        this.categories.add(s);
      }
      return this;
    }

    public Transaction build() {
      return new Transaction(this);
    }
  }
}
