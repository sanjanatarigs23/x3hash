
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class testClass {

  @Test
  void testStockAvail() {
    Backend candy = new Backend();
    if (candy.stockAvail("kitkat") != 407) { // tests to see if the stock available matches what was
                                             // passed in by data wrangler
      fail("stockAvail method failed");
    }

    if (candy.stockAvail("hersheys") != 888) {
      fail("stockAvail method failed");
    }

    if (candy.stockAvail("godiva") != 528) {
      fail("stockAvail method failed");
    }
  }

  @Test
  void testSetStock() {
    Backend candy = new Backend();
    candy.setStock("CANDY1234", "kitkat", 300);

    if (candy.stockAvail("kitkat") != 300) { // tests to see if the stock available matches what
                                             // was passed in by the setStock method
      fail("setStock method failed");
    }

    candy.setStock("Candy1234", "hersheys", 450);
    if (candy.stockAvail("hershey") == 450) {
      fail("setStock method failed");
    }

    candy.setStock("CANDY12", "godiva", 643);
    if (candy.stockAvail("godiva") == 643) {
      fail("setStock method failed");
    }
  }

  @Test
  void testPurchaseCandy() {
    Backend candy = new Backend();
    candy.purchaseCandy("kitkat", 10);
    if (candy.stockAvail("kitkat") != 290) { // tests if stock available decreased after customer
                                             // purchased candy
      fail("purchaseCandy method failed");
    }

    candy.purchaseCandy("hersheys", 10);
    if (candy.stockAvail("hershey") != 440) {
      fail("purchaseCandy method failed");
    }

    candy.purchaseCandy("godiva", 10);
    if (candy.stockAvail("godiva") != 633) {
      fail("purchaseCandy method failed");
    }

  }

}