package src.app;

import java.util.Scanner;

public class App {
  // instance variables
  Scanner in;
  Workbook workbook;

  // constructor
  public App() {
    in = new Scanner(System.in);
    workbook = new Workbook();

    in.useDelimiter("\\n");
  }

  // member functions

  public void menu() {
    boolean loop = true;
    while (loop) {
      System.out.print("Finance Tracker\n\n" +
                       "Menu\n" +
                       "1) Add Transaction\n" +
                       "2) Exit\n\n" +
                       "Selection: ");

      int selection = in.nextInt();
      System.out.print("\n\n");

      switch (selection) {
        case 1: add(); break;
        case 2: loop = false;
      }
    }
  }

  private void add() {
    System.out.print("Vendor: ");
    String vendor = in.next();

    System.out.print("Amount: ");
    Double amount = in.nextDouble();

    System.out.print("Date (m/d/yyyy): ");
    String date = in.next();

    Transaction.Builder builder = new Transaction.Builder(vendor, amount, date);

    System.out.print("Location: ");
    String location = in.next();
    if (!location.isEmpty())
      builder.location(location);

    System.out.print("Description: ");
    String description = in.next();
    if (!description.isEmpty())
      builder.description(description);

    System.out.print("Account => (1) Savings (2) Checking (3) Credit (default): ");
    String s = in.next();
    int choice = 0;
    if (!s.isEmpty()) {
      choice = Integer.parseInt(s);
      Transaction.Account account;

      switch (choice) {
        case 1: account = Transaction.Account.SAVINGS; break;
        case 2: account = Transaction.Account.CHECKING; break;
        case 3:
        default: account =Transaction.Account.CREDIT; break;
      }

      builder.account(account);
    }

    if (choice == 1) {
      System.out.print("Type => (1) Deposit (2) Withdrawal (default): ");
      s = in.next();
      if (!s.isEmpty() && Integer.parseInt(s) == 1)
        builder.type(Transaction.Type.DEPOSIT);
    }

    System.out.print("Main Category => (1) Education (2) Food (3) Housing (4) Income (5) Other (6) Personal (7) Transportation: ");
    choice = in.nextInt();
    String main;
    switch (choice) {
      case 1: main = "education"; break;
      case 2: main = "food"; break;
      case 3: main = "housing"; break;
      case 4: main = "income"; break;
      case 5: main = "other"; break;
      case 6: main = "personal"; break;
      case 7: main = "transportation"; break;
      default: main = "other"; break;
    }

    System.out.print("Secondary Category => ");
    String options = "";
    switch (main) {
      case "education": options = "Fee Supplies Textbooks Tuition"; break;
    }
    System.out.print(options + ": ");
    String secondary = in.next();


    System.out.print("Subcategories: ");
    String subcategories = in.next();

    builder.categories(main, secondary, subcategories);

    Transaction tr = builder.build();
    System.out.println(tr.toString());

  }
}
