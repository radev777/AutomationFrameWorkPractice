package AutoFramework;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class MainTestSetUp {

    private static final String  CHROME_DRIVER_PARH = "C://WebDriver//bin//chromedriver.exe";
    private static final String  INIT_PARH = "C://WebDriver//inits//init.xlsx";
    private static final String  CHROME = "CHROME";
    private static final String  FIREFOX = "FIREFOX";
    public WebDriver driver;
    private String username;
    private String password;
    private String browserName;
    private String mainURL;
    private String usernameLoggedInInfo;

    public MainTestSetUp(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PARH );
        DOMConfigurator.configure("log4j.xml");
    }


    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getMainURL() {
        return mainURL;
    }

    public String getUsernameLoggedInInfo() {
        return usernameLoggedInInfo;
    }

    public void mainSetUp() throws IOException {
        readDataFromExcelFile();
        setUpWebDriver();
        this.driver.manage().window().maximize();
    }

    private void readDataFromExcelFile() throws IOException {
        String testName = this.getClass().getSimpleName();
        File file = new File(INIT_PARH);
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        //Creating a Sheet object using the sheet Name
        XSSFSheet sheet = wb.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            String cel = row.getCell(0).getStringCellValue();
            if (row.getCell(0).getStringCellValue().equals(testName)) {
                this.username = row.getCell(1).getStringCellValue();
                this.password = row.getCell(2).getStringCellValue();
                this.browserName = row.getCell(3).getStringCellValue();
                this.mainURL = row.getCell(4).getStringCellValue();
                this.usernameLoggedInInfo =  row.getCell(5).getStringCellValue();
            }
        }
        if (username == null || password == null || mainURL == null) {
            throw new InvalidArgumentException("Pass,Username or Url is null");
        }
    }

    private void setUpWebDriver(){
        if (this.browserName.toUpperCase().equals(CHROME)){
            this.driver = new ChromeDriver();
        }else if(this.browserName.toUpperCase().equals(FIREFOX)){
            this.driver = new FirefoxDriver();
        }else{
            this.driver = new ChromeDriver();
        }
    }

    public void mainTestTearDown(){
        this.driver.quit();
    }
}
