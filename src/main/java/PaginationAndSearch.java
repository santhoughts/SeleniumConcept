import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;


public class PaginationAndSearch {

    public static void main(String[] args) {

        WebDriverManager.chromiumdriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        // Click on column
        // Capture the all element into the list
        // Capture text of all webElements into new (original) list
        // sort on the original list of step 3 -> sorted list
        // Compare original list vs sorted list

        // Click on column
        driver.findElement(By.xpath("//tr/th[1]")).click();

        // Capture the all element into the list
        List<WebElement> elementList = driver.findElements(By.xpath("//tr/td[1]"));

        // Capture text of all webElements into new (original) list
        List<String> originalList = elementList.stream().map(s->s.getText()).collect(Collectors.toList());

        // sort on the original list of step 3-> sorted list
        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

        // Compare original list vs sorted list
        Assert.assertTrue(originalList.equals(sortedList));

        // scan the name column with getText ->Beans->print the price of the Rice
            List<String> price;
        do {
            List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));
           price = rows.stream().filter(s->s.getText().contains("Rice")).map(s-> getPriceVeggie(s))
                    .collect(Collectors.toList());
            price.forEach(s-> System.out.println(s));
            if (price.size()<1)
            {
                driver.findElement(By.cssSelector("[aria-label='Next']")).click();
            }
        }
        while (price.size()<1);
        driver.quit();
    }

    private static String getPriceVeggie(WebElement s)
    {
        String priceValue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
        return priceValue;
    }
}
