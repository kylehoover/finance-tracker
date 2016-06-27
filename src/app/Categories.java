package src.app;

public class Categories {
  // instance variables
  private final MainCategory main;

  public Categories(String... args) {
    if (args[0] == "housing")
      main = MainCategory.HOUSING;
    else
      main = MainCategory.OTHER;
  }

  public String toString() {
    return "" + main;
  }


  public enum MainCategory {
    EDUCATION, FOOD, HOUSING, INCOME, OTHER, PERSONAL, TRANSPORTATION
  }

}
