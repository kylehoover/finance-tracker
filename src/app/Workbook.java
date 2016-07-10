package src.app;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Workbook {
  // instance variables
  Map<Integer, Set<Transaction>> transactions;

  // constructor
  public Workbook() {
    transactions = new HashMap<>();
  }

  // member functions

  public Transaction add(int year, Transaction t) {
    transactions.putIfAbsent(year, new TreeSet<Transaction>());
    transactions.get(year).add(t);
    return t;
  }

  public void importData(String filename) throws ClassNotFoundException, IOException {
    FileInputStream is = new FileInputStream(filename);
    ObjectInputStream in = new ObjectInputStream(is);
    transactions = (Map<Integer, Set<Transaction>>) in.readObject();
    in.close();
    is.close();
  }

  public void exportData(String filename) throws IOException {
    FileOutputStream os = new FileOutputStream(filename);
    ObjectOutputStream out = new ObjectOutputStream(os);
    out.writeObject(transactions);
    out.close();
    os.close();
  }

  public void print(Set<Transaction> set, String title) {
    System.out.print(title + "\n\n");
    System.out.printf("%-15s %-40s %-15s %-30s %n", "Date", "Vendor", "Amount", "Description");
    System.out.printf("%-15s %-40s %-15s %-30s %n", "----", "------", "------", "-----------");

    SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");

    for (Transaction t : set) {
      System.out.printf("%-15s %-40s %-15.2f %-30s %n", sdf.format(t.getDate()), t.getVendor(), t.getAmount(), t.getDescription());
    }
  }

  public void printSummary(double[] numbers) {
    //System.out.print(title + "\n\n");
    // System.out.printf("%-20s %.2f %n", "Total income:", numbers[0]);
    // System.out.printf("%50s %+.2f %n", "Difference:", numbers[0] - numbers[1]);
    // System.out.printf("%-20s %.2f %n%n%n", "Total expenses:", numbers[1]);
    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "Income", "Expenses", "Difference", "Education", "Food", "Housing", "Income", "Other", "Personal", "Transportation");
    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "------", "--------", "----------", "---------", "----", "-------", "------", "-----", "--------", "--------------");
    System.out.printf("%-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %n", numbers[0], numbers[1], numbers[0] - numbers[1], numbers[2], numbers[3], numbers[4], numbers[5], numbers[6], numbers[7], numbers[8]);

  }

  public void printMonth(int year, int month) {
    Set<Transaction> subset = new TreeSet<>();
    Calendar c = Calendar.getInstance();

    for (Transaction t : transactions.get(year)) {
      c.setTime(t.getDate());
      if (c.get(Calendar.MONTH) == month) {
        subset.add(t);
      }
    }

    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    String monthAsString = months[month];
    String title = monthAsString + " " + year;

    print(subset, title);
  }

  public void printYear(int year) {
    print(transactions.get(year), Integer.toString(year));
  }

}

// iterate over map
// for (Map.Entry<Integer, Set<Transaction>> entry : transactions.entrySet())
//   key = entry.getKey()
//   value = entry.getValue()
