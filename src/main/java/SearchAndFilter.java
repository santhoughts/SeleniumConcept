import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class SearchAndFilter {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.id("search-field")).sendKeys("Rice");
        List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));
        // 5 result
        List<WebElement> filteredList = veggies.stream().filter(veggie->veggie.getText().contains("Rice"))
                .collect(Collectors.toList());
        // 1 result
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(veggies.size(),filteredList.size());
        if (veggies.size()== filteredList.size())
        {
            System.out.println("Test is passed");
        }
        else
        {
            System.out.println("Test is failed");
        }

        driver.quit();
    }
}
