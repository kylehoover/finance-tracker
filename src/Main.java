package src;

import src.app.*;

public class Main {
  public static void main(String[] args) {
    // Transaction t = new Transaction.Builder(34.83, "6/25/2016", "Smiths").build();
    // Transaction t2 = new Transaction.Builder(7.23, "6/25/2016", "Cougar Express").build();
    // // Transaction t3 = new Transaction.Builder("BYU", 980.54, "5/22/2016").account(Transaction.Account.SAVINGS).type(Transaction.Type.DEPOSIT).categories("Income", "Paycheck").build();
    // // Workbook w = new Workbook();
    // // w.addTransaction();
    // // w.print();
    // //
    // System.out.println();
    // System.out.println(t.toString());
    // System.out.println(t2.toString());
    // // System.out.println(t3.toString());
    // System.out.println();

    App app = new App();
    app.run();
  }
}
