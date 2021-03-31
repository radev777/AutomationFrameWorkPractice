package ObjectPages;

import AutoFramework.Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    String email ;
   // private String url = "http://automationpractice.com/index.php";

    By signInBtn =By.className("login");
    By contactUsBtn = By.xpath("//div[@id='contact-link']/a[@title ='Contact us']");
    By proceedToCheckOut = By.xpath("//a[@class ='btn btn-default button button-medium' and @title ='Proceed to checkout']");
    By proceedToCheckOutStandard = By.xpath("//a[@class ='button btn btn-default standard-checkout button-medium' and @title ='Proceed to checkout']");
    By confirmMyorder = By.xpath("//button[@class ='button btn btn-default button-medium' and @type ='submit']");


    public  HomePage(WebDriver dr,String email ){
        this.driver = dr;
        wait = new WebDriverWait(dr, 10);
        this.email = email;
    }

    public void navigateTo(String url){
        Log.info("Open "+ url);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public LoginPage openSingInPage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtn)).click();
        return new LoginPage(driver,email);
    }

    public  void closePage(){
        driver.close();
    }

    public  ProductDetailsPage openItem(String name){
        Actions actions = new Actions(driver);
       WebElement link =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(name)));
       actions.moveToElement(link).perform();
       actions.click(link).perform();
        return new ProductDetailsPage(driver);
    }

    public void proceedToCheckout(){
        WebElement proceedToCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckOut));
        proceedToCheckout.click();
    }

    public void proceedToCheckoutSummary(){
        WebElement proceedToCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckOutStandard));
        proceedToCheckout.click();
    }
}
