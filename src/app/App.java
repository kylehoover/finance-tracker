package src.app;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
  // instance variables
  Scanner in;
  Workbook workbook;

  // constructor
  public App() {
    in = new Scanner(System.in);
    in.useDelimiter("\\n");
    workbook = new Workbook();
  }

  // member functions

  public void run() {
    init();
    menu();
    exit();
  }

  private void init() {
    try {
      workbook.importData("./data/test.ser");
    } catch (IOException e) {
      System.out.println(e);
    } catch (ClassNotFoundException e) {
      System.out.println(e);
    }
  }

  private void exit() {
    try {
      workbook.exportData("./data/test.ser");
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  private void menu() {
    boolean loop = true;

    System.out.print("Finance Tracker\n\n");

    while (loop) {
      System.out.print("Menu\n" +
                       "1) Add Transaction\n" +
                       "2) Display All\n" +
                       "3) Display Summary\n" +
                       "4) Exit\n\n" +
                       "Selection: ");

      int selection = in.nextInt();
      System.out.print("\n\n");

      switch (selection) {
        case 1: addTransaction(); break;
        case 2: displayAll(); break;
        case 3: displaySummary(); break;
        case 4: loop = false;
        default: break;
      }
    }
  }

  private void addTransaction() {
    System.out.print("Vendor: ");
    String vendor = in.next();

    System.out.print("Amount: ");
    Double amount = 0.0;

    try {
      amount = in.nextDouble();
    } catch (InputMismatchException e) {
      System.out.print("\nERROR: Invalid input. Please try again and enter a numebr.\n\n");
      in = new Scanner(System.in);
      return;
    }

    System.out.print("Date (m/d/yyyy): ");
    String date = in.next();

    // format date
    if (date.length() <= 5)
      date += "/" + Calendar.getInstance().get(Calendar.YEAR);

    SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
    Date d;

    try {
      d = sdf.parse(date);
    } catch (ParseException e) {
      System.out.println(e);
      return;
    }

    // get year to add to workbook
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    int year = c.get(Calendar.YEAR);

    Transaction.Builder builder = new Transaction.Builder(amount, d, vendor);

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
    s = in.next();
    if (!s.isEmpty())
      choice = Integer.parseInt(s);
    else
      choice = 0;
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

    Transaction t = builder.build();

    if (workbook.add(year, t) != null)
      System.out.println("\nTransaction successfully added\n");
    else
      System.out.println("\nAn error occurred while trying to add the transaction");
  }

  private void displayAll() {
    System.out.print("Display options: ");

    String input = in.next();

    if (input.isEmpty()) {
      System.out.print("\nERROR: Invalid input. Please enter a year, and optionally a month.\n\n");
      return;
    }

    int indexOfSpace = input.indexOf(' ');
    int year = -1;
    int month = -1;

    if (indexOfSpace == -1) {
      year = Integer.parseInt(input);
    } else {
      year = Integer.parseInt(input.substring(0, indexOfSpace));
      month = Integer.parseInt(input.substring(indexOfSpace + 1));
    }

    System.out.println("\u001b[2J" + "\u001b[H");
    System.out.flush();

    if (month == -1) {
      workbook.printYear(year);
    } else {
      workbook.printMonth(year, month - 1);
    }

    System.out.print("\n\n");
    System.out.print("Continue? ");
    String s = in.next();
    System.out.print("\n\n");
  }

  private void displaySummary() {
    System.out.print("Display options: ");

    String input = in.next();

    if (input.isEmpty()) {
      System.out.print("\nERROR: Invalid input. Please enter a year, and optionally a month.\n\n");
      return;
    }

    int indexOfSpace = input.indexOf(' ');
    int year = -1;
    int month = -1;

    if (indexOfSpace == -1) {
      year = Integer.parseInt(input);
    } else {
      year = Integer.parseInt(input.substring(0, indexOfSpace));
      month = Integer.parseInt(input.substring(indexOfSpace + 1));
    }

    System.out.println("\u001b[2J" + "\u001b[H");
    System.out.flush();

    if (month == -1) {
      workbook.printYear(year);
    } else {
      workbook.printMonth(year, month - 1);
    }

    System.out.print("\n\n");
    System.out.print("Continue? ");
    String s = in.next();
    System.out.print("\n\n");
  }
}
