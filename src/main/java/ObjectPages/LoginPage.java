package ObjectPages;


import AutoFramework.Utilities.Log;
import AutoFramework.Utilities.Screenshots;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    String email ;
    By loginForm = By.id("login_form");
    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By submitBtn = By.id("SubmitLogin");
    By accountInfo = By.xpath("//div[@class ='header_user_info']/a[@class='account']");
    By logOutBnt = By.xpath("//div[@class ='header_user_info']/a[@class='logout']");
    By homelink =  By.linkText("Home");

    By createAccountBtn = By.id("SubmitCreate");
    By emaiCreateField = By.id("email_create");

    public LoginPage(WebDriver dr, String email) {
        driver = dr;
        wait = new WebDriverWait(driver, 10);
        this.email = email;
    }

    // login method
    public void login(String email, String password) throws IOException {

        if (validateEmail(email) && password.length() > 5) {
            Screenshots.takeWebElementScreenShot(wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm)), "LoginForm");
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            Log.info("Login with username: " + email + "- pass: " + password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
            System.out.println("Login passed!!!");
            Log.info("Login passed!!!");
        } else {
            Log.error("wrong email or password input");
            System.out.println("wrong email or password input ");
            throw new IllegalArgumentException("Wrong email or password input");
        }
    }

    public void checkAccountInfoByText(String expectedText) {
        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated
                (accountInfo));
        Assert.assertEquals(expectedText, check.getText());
        System.out.println("Checked passed!!!");
    }

    public void logOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (logOutBnt)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
        System.out.println("Logout passed!!!");
    }

    public void goToHomePage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(homelink)).click();
    }

    private boolean validateEmail(String em) {
        return true;
    }

    public RegistrationPage createAnAccount(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emaiCreateField)).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountBtn)).click();
        return  new RegistrationPage(driver, email);
    }

}
