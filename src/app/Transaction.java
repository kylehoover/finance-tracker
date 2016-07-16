package src.app;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Transaction implements Comparable<Transaction>, Serializable {
  // instance variables
  private final Account account;
  private final double amount;
  private final ArrayList<String> categories;
  private final Date date;
  private final String description;
  private final String id;
  private final String location;
  private final Type type;
  private final String vendor;

  // constructor
  private Transaction(Builder builder) {
    account = builder.account;
    amount = builder.amount;
    categories = builder.categories;
    date = builder.date;
    description = builder.description;
    id = UUID.randomUUID().toString();
    location = builder.location;
    type = builder.type;
    vendor = builder.vendor;
  }

  // getter methods
  public final Account getAccount() {
    return account;
  }

  public final double getAmount() {
    return amount;
  }

  public final ArrayList<String> getCategories() {
    return categories;
  }

  public final Date getDate() {
    return date;
  }

  public final String getDescription() {
    return description;
  }

  public final String getId() {
    return id;
  }

  public final String getLocation() {
    return location;
  }

  public final Type getType() {
    return type;
  }

  public final String getVendor() {
    return vendor;
  }

  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
    return getClass().getName() + "[amount=" + amount + ",date=" + sdf.format(date) + ",description=" + description + ",location=" + location
           + ",vendor=" + vendor + ",account=" + account + ",type=" + type + ",categories=" + categories.toString() + "]";
  }

  // override compareTo method from Comparable interface
  public int compareTo(Transaction other) {
    if (this == other)
      return 0;

    int result = this.date.compareTo(other.date);

    if (result == 0)
      result = this.vendor.compareTo(other.vendor);

    if (result == 0) {
      if (this.amount < other.amount)
        result = -1;
      else if (this.amount > other.amount)
        result = 1;
      else {
        result = this.id.compareTo(other.id);
      }
    }

    return result;
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

    public Builder(double amount, Date date, String vendor) {
      this.amount = amount;
      this.vendor = vendor;
      this.date = date;
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
