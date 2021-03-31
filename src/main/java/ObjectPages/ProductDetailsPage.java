package ObjectPages;

import AutoFramework.ItemDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dimitarrad
 * on 2/24/2021
 */
public class ProductDetailsPage {


    WebDriver driver;
    WebDriverWait wait;

    By quantity_wanted = By.id("quantity_wanted");
    By group_1 = By.id("group_1");
    By add_to_cart = By.xpath("//p[@id='add_to_cart']/button[@name = 'Submit']");

    public  ProductDetailsPage(WebDriver dr){
        driver = dr;
        wait = new WebDriverWait(dr,10);
    }

    public void addToCard(ItemDetails details){


        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity_wanted));
        quantity.clear();
        quantity.sendKeys(details.quant);
   /*   Select sel = new Select( wait.until(ExpectedConditions.visibilityOfElementLocated(group_1)));
       sel.selectByVisibleText(details.size);*/
        String text = String.format("//ul[@id='color_to_pick_list']/li/a[@title='%s']", details.col);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(add_to_cart)).click();
    }



}
