package AutomationTests;

import AutoFramework.ItemDetails;
import AutoFramework.MainTestSetUp;
import AutoFramework.Utilities.ReadFromXml;
import ObjectPages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;


@RunWith(Parameterized.class)
public class SanityTest extends MainTestSetUp {

    ItemDetails item;

    @Before
    public void setUp() {
        try {
            this.mainSetUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        this.mainTestTearDown();
    }

    public SanityTest(String itemName, String price, String quant, String size, String col){
        item = new ItemDetails(itemName,price,quant,size,col);
    }


    @Parameterized.Parameters
    public static Collection inputs() throws IOException {
        return ReadFromXml.readXml("C:\\WebDriver\\inits\\items.xlsx");
    }

    @Test
    public  void sanityTest() throws IOException, InterruptedException {
        HomePage hp = new HomePage(this.driver, this.getUsername());
        hp.navigateTo(this.getMainURL());
       LoginPage loginPage = hp.openSingInPage();
       // RegistrationPage rp =loginPage.createAnAccount(this.getUsername());
        //rp.register();
        loginPage.login(this.getUsername(),this.getPassword());
        loginPage.goToHomePage();
       ProductDetailsPage product = hp.openItem(item.itemName);
       product.addToCard(item);
       hp.proceedToCheckout();
        AddressPage addressPage = new AddressPage(this.driver);
        hp.proceedToCheckoutSummary();
        String nameFromInput = addressPage.getTextFromNameFiled();
        Assert.assertEquals(this.getUsernameLoggedInInfo(),nameFromInput);
        addressPage.setOrderAddress("Sofia 1000 Mladost 3 34345");
        addressPage.clickProceedButton();
        ShippingPage sp = new ShippingPage(driver);
        sp.acceptAndProceed();
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.checkItemName(item.itemName);
        paymentPage.checkItemPrice(item.price);
        paymentPage.checkTotalPriceAndPay(item);









    }
}
