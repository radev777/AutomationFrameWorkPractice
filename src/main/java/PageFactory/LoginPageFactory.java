package PageFactory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageFactory {

    private WebDriver driver;

    @FindBy(id="login_form")
    WebElement loginForm;

   @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    WebElement submitBtn;

    @FindBy(xpath = "//div[@class ='header_user_info']/a[@class='account']")
    WebElement accountInfo;

    @FindBy(xpath = "//div[@class ='header_user_info']/a[@class='logout']")
    WebElement logOutBnt;


    public LoginPageFactory(WebDriver driver){
        if (driver==null)
        {
            throw new NullPointerException("LPF: Driver instance is null");
        }
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    // login method
    public void login(String email, String password) {
        if (validateEmail(email)&&password.length()>5 && loginForm.isDisplayed()) {
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            submitBtn.click();
            System.out.println("Login passed!!!");
        }else  {
            System.out.println("wrong email or password input ");
        }
    }

    public void checkAccountInfoByText(String expectedText){
        Assert.assertEquals(expectedText,accountInfo.getText());
        System.out.println("Checked passed!!!");
    }

    public void logOut(){
        logOutBnt.click();
        if (loginForm.isDisplayed()) {
            System.out.println("Logout passed!!!");
        }else {
            throw new RuntimeException("Not logout from Login page");
        }
    }

    // validate email
    private boolean validateEmail(String em){
        return true;
    }

}
