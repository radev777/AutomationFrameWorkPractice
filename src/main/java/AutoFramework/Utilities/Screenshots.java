package AutoFramework.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dimitarrad
 * on 2/22/2021
 */
public class Screenshots {

    private static final String FOLDER_PATH = "C:\\WebDriver\\screenshots\\";

    public static BufferedImage takePageScreenshot(WebDriver dr, String screenshotname) throws IOException {
        //Take the screenshot
        File screenshot = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File(FOLDER_PATH + getFileNameWithCurrentDate(screenshotname)));
        } catch (IOException e) {
            Log.error(e.getMessage());
            throw e;
        }
        BufferedImage image = ImageIO.read(screenshot);
        return image;

    }

    public static BufferedImage takeWebElementScreenShot(WebElement el, String elementname) throws IOException {
        File file = el.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(FOLDER_PATH + getFileNameWithCurrentDate(elementname)));
        } catch (IOException e) {
            Log.error(e.getMessage());
            throw e;
        }
        BufferedImage image = ImageIO.read(file);
        return image;
    }

    private static String getFileNameWithCurrentDate(String step) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String fileName = step + "_" + formatter.format(date).toString() + ".png";
        return fileName;
    }

    public static boolean compareImages(BufferedImage image1, BufferedImage image2) {

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(image1, image2);
        if (diff.hasDiff() == true) {
            System.out.println("Images are Not Same");
            return false;

        }
        System.out.println("Images are Same");
        return true;
    }
}
