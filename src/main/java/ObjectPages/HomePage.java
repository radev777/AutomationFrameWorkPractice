package ObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
   // private String url = "http://automationpractice.com/index.php";

    By signInBtn =By.className("login");
    By contactUsBtn = By.xpath("//div[@id='contact-link']/a[@title ='Contact us']");

    public  HomePage(WebDriver dr){
        this.driver = dr;
        wait = new WebDriverWait(dr, 10);
    }

    public void navigateTo(String url){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public LoginPage openSingInPage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtn)).click();
        return new LoginPage(driver);
    }

    public  void closePage(){
        driver.close();
    }

}
