package src;

import java.util.*;
import src.app.Transaction;

public class Main
{
  public static void main(String[] args)
  {
    Transaction t = new Transaction();
    Transaction s = new Transaction();
    Transaction e = new Transaction();

    System.out.println(t.getID());
    System.out.println(s.getID());
    System.out.println(e.getID());
  }
}
