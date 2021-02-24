package PageFactory;

import ObjectPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dimitarrad
 * on 2/15/2021
 */
public class HomePageFactory {

    private WebDriver driver;
    private String url = "http://automationpractice.com/index.php";

   /* @FindBys({@FindBy(className = "login")})
    List<WebElement> signInBtn;*/

    @FindBy(className = "login")
    WebElement signInBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='contact-link']/a[@title ='Contact us']")
    WebElement contactUsBtn;

    public HomePageFactory(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        if (driver==null){
            throw new NullPointerException("HPF : Driver instance is null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    //login
    public LoginPageFactory openSingInPage(){
        signInBtn.click();
        return new LoginPageFactory(driver);
    }

    public  void closePage(){
        driver.close();
    }
}
