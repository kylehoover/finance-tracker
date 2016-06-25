package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import src.app.Transaction;

public class Main
{
  public static void main(String[] args)
  {
    Transaction t = new Transaction.Builder("Smiths", 34.83, "6/25/2016").build();
    Transaction t2 = new Transaction.Builder("Swigs", 2.00, "11/3/2013").description("sugar cookie").location("Provo").build();
    Transaction t3 = new Transaction.Builder("BYU", 980.54, "5/22/2016").account(Transaction.Account.SAVINGS).type(Transaction.Type.DEPOSIT).build();

    System.out.println(t.toString());
    System.out.println(t2.toString());
    System.out.println(t3.toString());

  }
}
