import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowHandle {


    public static void main(String[] args) {

        WebDriverManager.chromiumdriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.switchTo().newWindow(WindowType.TAB);
       Set<String> handles = driver.getWindowHandles();
       Iterator<String> it = handles.iterator();
       String parentWindowId = it.next();
       String childWindowId = it.next();
       driver.switchTo().window(childWindowId);
       driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        String text= driver.findElement(By.cssSelector("div[class='brand greenLogo']")).getText();
        driver.switchTo().window(parentWindowId);
        driver.findElement(By.id("search-field")).sendKeys(text);


    }
}
