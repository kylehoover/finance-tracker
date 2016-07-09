package src.app;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
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

  public void importData(String filename) throws ClassNotFoundException, IOException {
    FileInputStream is = new FileInputStream(filename);
    ObjectInputStream in = new ObjectInputStream(is);
    transactions = (Map<Integer, Set<Transaction>>) in.readObject();

    System.out.println(transactions);

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

  public void print() {
    transactions.forEach((y, t) -> {
      System.out.println(y + ": " + t);
    });
  }

}
