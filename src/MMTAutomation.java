import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class MMTAutomation {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\abhay\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        String path = "/usr/bin/google-chrome-stable";
        options.setBinary(path);
        WebDriver driver = new ChromeDriver();
        String Link = "https://www.makemytrip.com/railways/";
        String Open = "input.react-autosuggest__input.react-autosuggest__input--open";
        String Focused = Open + ".react-autosuggest__input--focused";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(Link);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fromCity"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Open))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Focused))).click();

        WebElement FromCity = driver.findElement(By.cssSelector(Focused));
        FromCity.sendKeys("DELHI");
        Thread.sleep(2000);
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        FromCity.sendKeys(Keys.DOWN, Keys.RETURN);
        
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Open))).click();
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Focused))).click();

        WebElement ToCity = driver.findElement(By.cssSelector(Focused));
        ToCity.sendKeys("LUCKNOW");
        Thread.sleep(2000);
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
        ToCity.sendKeys(Keys.DOWN, Keys.RETURN);
        
        Thread.sleep(1000);
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("travelDate"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement date = driver.findElement(By.cssSelector("div[aria-label='Thu Jul 20 2023']"));
        js.executeScript("arguments[0].click();", date);
        
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/div/div[2]/div/div[4]")).click();
        int length = driver.findElements(By.cssSelector("ul.travelForPopup > li")).size();
        System.out.println("Length of the list is: " + length);
        for (int i = 0; i < length; i++) {
            String element = driver.findElements(By.cssSelector("ul.travelForPopup > li")).get(i).getText();
            System.out.println("Element is: " + element);
            if (element.equals("Third AC")) {
                driver.findElements(By.cssSelector("ul.travelForPopup > li")).get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath("// *[@id='root']/div/div[2]/div/div/div/div[2]/p/a")).click();
    }
}
