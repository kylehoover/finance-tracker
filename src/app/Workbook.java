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

  public Transaction add(Transaction t) {
    Calendar c = Calendar.getInstance();
    c.setTime(t.getDate());
    int year = c.get(Calendar.YEAR);
    transactions.putIfAbsent(year, new TreeSet<Transaction>());
    transactions.get(year).add(t);
    return t;
  }

  public void importData(String filename) throws ClassNotFoundException, IOException {
    FileInputStream fis = new FileInputStream(filename);
    ObjectInputStream in = new ObjectInputStream(fis);
    transactions = (Map<Integer, Set<Transaction>>) in.readObject();
    in.close();
    fis.close();
  }

  public void exportData(String filename) throws IOException {
    FileOutputStream fos = new FileOutputStream(filename);
    ObjectOutputStream out = new ObjectOutputStream(fos);
    out.writeObject(transactions);
    out.close();
    fos.close();
  }

  private void print(Set<Transaction> set, String title) {
    System.out.print("Display - " + title + "\n\n");
    System.out.printf("%-15s %-40s %-15s %-20s %-30s %n", "Date", "Vendor", "Amount", "Main Category", "Description");
    System.out.printf("%-15s %-40s %-15s %-20s %-30s %n", "----", "------", "------", "-------------", "-----------");

    SimpleDateFormat sdf = new SimpleDateFormat("M/d");

    for (Transaction t : set) {
      System.out.printf("%-15s %-40s %-15.2f %-20s %-30s %n", sdf.format(t.getDate()), t.getVendor(), t.getAmount(), t.getCategories().get(0).toUpperCase(), t.getDescription());
    }
  }

  public void printMonth(int year, int month) {
    if (transactions.get(year) == null) {
      noData(Integer.toString(year));
      return;
    }

    Set<Transaction> subset = new TreeSet<>();
    Calendar c = Calendar.getInstance();
    month -= 1;
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    String monthAsString = months[month];
    String title = monthAsString + " " + year;

    for (Transaction t : transactions.get(year)) {
      c.setTime(t.getDate());
      if (c.get(Calendar.MONTH) == month) {
        subset.add(t);
      }
    }

    print(subset, title);
  }

  public void printYear(int year) {
    String title = Integer.toString(year);

    if (transactions.get(year) == null) {
      noData(title);
      return;
    }

    print(transactions.get(year), title);
  }

  private void printSummary(double[] numbers, String title) {
    System.out.print("Display Summary - " + title + "\n\n");
    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "Income", "Expenses", "Difference", "Education", "Food", "Housing", "Other", "Personal", "Transportation");
    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "------", "--------", "----------", "---------", "----", "-------", "-----", "--------", "--------------");
    System.out.printf("%-15.2f %-15.2f %-+15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %-15.2f %n", numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5], numbers[6], numbers[7], numbers[8]);
  }

  public void printSummaryMonth(int year, int month) {
    if (transactions.get(year) == null) {
      noData(Integer.toString(year));
      return;
    }

    double[] summaries = new double[9];
    Calendar c = Calendar.getInstance();
    month -= 1;
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    String monthAsString = months[month];
    String title = monthAsString + " " + year;

    for (Transaction t : transactions.get(year)) {
      c.setTime(t.getDate());

      if (c.get(Calendar.MONTH) == month) {
        double amount = t.getAmount();

        if (t.getType() == Transaction.Type.DEPOSIT) {
          summaries[0] += amount;
        } else {
          summaries[1] += amount;
        }

        switch(t.getCategories().get(0)) {
          case "education": summaries[3] += amount; break;
          case "food": summaries[4] += amount; break;
          case "housing": summaries[5] += amount; break;
          case "other": summaries[6] += amount; break;
          case "personal": summaries[7] += amount; break;
          case "transportation": summaries[8] += amount; break;
        }
      }
    }

    summaries[2] = summaries[0] - summaries[1];

    printSummary(summaries, title);
  }

  public void printSummaryYear(int year) {
    String title = Integer.toString(year);

    if (transactions.get(year) == null) {
      noData(title);
      return;
    }

    double[] summaries = new double[9];

    for (Transaction t : transactions.get(year)) {
      double amount = t.getAmount();

      if (t.getType() == Transaction.Type.DEPOSIT) {
        summaries[0] += amount;
      } else {
        summaries[1] += amount;
      }

      switch(t.getCategories().get(0)) {
        case "education": summaries[3] += amount; break;
        case "food": summaries[4] += amount; break;
        case "housing": summaries[5] += amount; break;
        case "other": summaries[6] += amount; break;
        case "personal": summaries[7] += amount; break;
        case "transportation": summaries[8] += amount; break;
      }
    }

    summaries[2] = summaries[0] - summaries[1];

    printSummary(summaries, title);
  }

  private void noData(String title) {
    System.out.print("ERROR: No data found for " + title + "\n");
  }
}

// iterate over map
// for (Map.Entry<Integer, Set<Transaction>> entry : transactions.entrySet())
//   key = entry.getKey()
//   value = entry.getValue()
