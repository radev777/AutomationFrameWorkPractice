package AutomationTests;

import AutoFramework.MainTestSetUp;
import AutoFramework.Utilities.Log;
import AutoFramework.Utilities.Screenshots;
import ObjectPages.HomePage;
import ObjectPages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginTestDDT extends MainTestSetUp {

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

    @Test
    public void simpleLogin() throws IOException {
        try {
            Log.startTestDetails(this.getClass().getSimpleName());
            Log.info("Open home page");
            HomePage homePage = new HomePage(driver,this.getUsername());
            homePage.navigateTo(this.getMainURL());
            LoginPage loginPage = homePage.openSingInPage();
            //validate email
            BufferedImage image1 = Screenshots.takePageScreenshot(this.driver,"Login");
            BufferedImage imageOut =  ImageIO.read(new File("C:\\WebDriver\\screenshots\\testImage.png"));
            Screenshots.compareImages(image1,imageOut);
            loginPage.login(this.getUsername(), this.getPassword());
            loginPage.checkAccountInfoByText(this.getUsernameLoggedInInfo());
            loginPage.logOut();
            homePage.closePage();
            Log.endTestDetails();
        } catch (RuntimeException exception) {
            Log.error(exception.getMessage());
            throw exception;
        }
    }
}

