package src.app;

public class Categories {
  // instance variables
  private final MainCategory main;

  // constructor
  public Categories(String... args) {
    if (args.length == 0) {
      main = MainCategory.OTHER;
    }
    else {
       main = MainCategory.valueOf(args[0]);

       if (args.length > 1) {
         // handle subcategories
       }
    }
  }

  public String toString() {
    return "" + main;
  }


  // enums
  public enum MainCategory {
    EDUCATION, FOOD, HOUSING, INCOME, OTHER, PERSONAL, TRANSPORTATION
  }

  // secondary categories within main categories
  public enum EducationSeconday {
    FEE, SUPPLIES, TEXTBOOKS, TUITION
  }

  public enum FoodSecondary {
    DINING_OUT, GROCERIES
  }
  // dining_out => sit down, fast food, snack, dessert

  public enum HousingSeconday {
    MORTGAGE, RENT, UTILITIES
  }
  // utilities => city (electric, water, waste), gas, phone, internet

  public enum IncomeSecondary {
    FINANCIAL_AID, GIFT, INVESTMENT, PAYCHECK, REIMBURSEMENT
  }
  // financial aid => grants, scholarships, loans
  // reimbursement => tax refunds, store refunds
  // investment => interest earned, dividends

  public enum OtherSecondary {
    CHURCH_DONATION, GIFT, INSURANCE, SAVINGS
  }
  // church_donation => tithing, fast offering, etc
  // gift => birthday, wedding, baby shower, holiday, anniversary, just because (who it was for)
  // insurance => health, auto, renters, life
  // savings => retirement, investments, emergency fund, reserve funds

  public enum PersonalSecondary {
    CLOTHING, ENTERTAINMENT, HAIRCUT, MISC
  }
  // clothing => type
  // entertainment => books, movies, video rental, sporting events, video games, dvds

  public enum TransportationSecondary {
    ACCESSORY, FEE, GAS, MAINTENANCE
  }
  // fee => ticket (type)

}
