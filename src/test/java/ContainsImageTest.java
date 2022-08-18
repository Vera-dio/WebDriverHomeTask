import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class ContainsImageTest {
    static WebDriver driver;

    @BeforeAll
    public static void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void checkImageSearch(){
        driver.get("https://www.google.com/");
        WebElement searchField = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        searchField.sendKeys("image");
        searchField.sendKeys(Keys.RETURN);
        WebElement imageTab = driver.findElement(By.xpath("//a[contains(text(),'Зображення')]"));
        imageTab.click();
        WebElement blockOfImages = driver.findElement(By.id("islmp"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(blockOfImages));
        List<WebElement> listOfImages = driver.findElements(By.xpath("//img[@class='rg_i Q4LuWd']"));
        for (int i = 0; i < 4; i++){
            assertTrue(listOfImages.get(i).isDisplayed(), "No image #%d".formatted(i + 1));
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
