package src.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Workbook {
  // instance variables
  Map<Integer, ArrayList<Transaction>> transactions;

  // constructor
  public Workbook() {
    transactions = new HashMap<>();
  }

  // member functions

  public boolean add(int year, Transaction t) {
    transactions.putIfAbsent(year, new ArrayList<Transaction>());
    transactions.get(year).add(t);
    return true;
  }

  public boolean importData(String file) {




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
