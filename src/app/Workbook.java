package src.app;

import java.util.ArrayList;
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

  public boolean add(int year, Transaction t) {
    transactions.putIfAbsent(year, new TreeSet<Transaction>());
    transactions.get(year).add(t);
    return true;
  }

  public boolean importData(String filename) {




    return true;
  }

  public boolean exportData(String filename) {
    // filewriter or printwriter
    // bufferedwriter
    // look into using json


    return true;
  }

  public void print() {
    transactions.forEach((y, t) -> {
      System.out.println(y + ": " + t);
    });
  }

}
