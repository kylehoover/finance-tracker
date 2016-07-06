package src.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Workbook {
  // instance variables
  Map<Integer, ArrayList<Transaction>> transactions;
  Set<Transaction> st;

  // constructor
  public Workbook() {
    transactions = new HashMap<>();
    st = new TreeSet<>();
  }

  // member functions

  public boolean add(int year, Transaction t) {
    transactions.putIfAbsent(year, new ArrayList<Transaction>());
    transactions.get(year).add(t);
    st.add(t);
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
