package ObjectPages;

import AutoFramework.Utilities.ReadFromXml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitarrad
 * on 2/24/2021
 */
public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;
    String email;
    private final String perXml ="C:\\WebDriver\\inits\\personalDetails.xlsx";


    By customer_firstname = By.id("customer_firstname");
    By customer_lastname = By.id("customer_lastname");
    By passwd = By.id("passwd");
    By days = By.id("days");
    By months = By.id("months");
    By years = By.id("years");
    By optin = By.id("optin");
    By firstname = By.id("firstname");
    By lastname = By.id("lastname");
    By company = By.id("company");
    By address1 = By.id("address1");
    By city = By.id("city");
    By id_state = By.id("id_state");
    By postcode = By.id("postcode");
    By id_country = By.id("id_country");
    By phone_mobile = By.id("phone_mobile");
    By alias = By.id("alias");
    By submitAccount = By.id("submitAccount");

    public RegistrationPage (WebDriver driver, String email){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        this.email = email;
    }


    public void register(){

        List<String> details =new ArrayList<>();

        try {
            details = ReadFromXml.readFromXmlFile(perXml,email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_firstname)).sendKeys(details.get(0));
        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_lastname)).sendKeys(details.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwd)).sendKeys(details.get(2));
        /*Select sel = new Select(wait.until(ExpectedConditions.elementToBeClickable(days)));

        sel.selectByIndex(4);

        Select selm = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(months)));
        selm.selectByIndex(5);

        Select sely = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(years)));
        sely.selectByIndex(1999);*/

/*
        WebElement checkb = wait.until(ExpectedConditions.elementToBeClickable(optin));
        if (!checkb.isSelected()){
            checkb.click();
        }
*/

        wait.until(ExpectedConditions.visibilityOfElementLocated(company)).sendKeys(details.get(7));

        wait.until(ExpectedConditions.visibilityOfElementLocated(address1)).sendKeys(details.get(8));

        wait.until(ExpectedConditions.visibilityOfElementLocated(city)).sendKeys(details.get(9));

        Select state = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(id_state)));
        state.selectByIndex(3);


        wait.until(ExpectedConditions.visibilityOfElementLocated(postcode)).sendKeys("12345");

        wait.until(ExpectedConditions.visibilityOfElementLocated(phone_mobile)).sendKeys(details.get(12));

        wait.until(ExpectedConditions.visibilityOfElementLocated(alias)).sendKeys(details.get(13));

        wait.until(ExpectedConditions.visibilityOfElementLocated(submitAccount)).click();
    }

}
