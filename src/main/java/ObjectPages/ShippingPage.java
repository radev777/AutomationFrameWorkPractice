package ObjectPages;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dimitarrad
 * on 3/1/2021
 */
public class ShippingPage {

    private WebDriver driver;
    private WebDriverWait wait;


    By checkBoxAgree = By.id("uniform-cgv");
    By proceedButton = By.xpath("//button [@name = 'processCarrier' and @class ='button btn btn-default standard-checkout button-medium']");


    public ShippingPage(WebDriver driver){
        this.driver =driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void acceptAndProceed(){
        WebElement web  = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxAgree));
        Actions actions = new Actions(this.driver);
        actions.moveToElement(web).perform();
        actions.click().perform();

         wait.until(ExpectedConditions.visibilityOfElementLocated(proceedButton)).click();
    }
}
