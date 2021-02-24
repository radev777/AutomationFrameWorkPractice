package AutomationTests.LoginTests;


import AutoFramework.MainTestSetUp;
import ObjectPages.HomePage;
import ObjectPages.LoginPage;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageFactory.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginExample  {

   WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://WebDriver//bin//chromedriver.exe");
        driver = new ChromeDriver();

    }

    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @Test
    public void simpleLogin() {
        String url = "http://automationpractice.com/index.php";
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
        signIn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form")));

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("test123@abv.bg");

        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
        pass.sendKeys("123456");

        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
        submit.click();

        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class ='header_user_info']/a[@class='account']")));
        String text = check.getText();
        Assert.assertEquals("Test Test", text);

        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class ='header_user_info']/a[@class='logout']")));
        logout.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form")));
    }

    @Test
    public void pomLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo("");
        LoginPage loginPage = homePage.openSingInPage();
        //validate email
        try {
            loginPage.login("test123@abv.bg", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginPage.checkAccountInfoByText("Test Test");
        loginPage.logOut();
        homePage.closePage();
    }

    @Test
    public void pomPagefactoryLogin() {
    /*    PageFactory.initElements(driver, HomePageFactory.class);
        PageFactory.initElements(driver, LoginPageFactory.class);*/

        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.navigateTo();
        LoginPageFactory loginPageFactory = homePageFactory.openSingInPage();
        loginPageFactory.login("test123@abv.bg", "123456");
        loginPageFactory.checkAccountInfoByText("Test Test");
        loginPageFactory.logOut();
        homePageFactory.closePage();
    }
}
