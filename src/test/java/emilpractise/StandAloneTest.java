package emilpractise;

import emilpractise.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class StandAloneTest {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String productName = "ADIDAS ORIGINAL";
        Actions act=new Actions(driver);

        driver.findElement(By.xpath("//label[text()=\"Email\"]/following-sibling::input[@id=\"userEmail\"]"))
                .sendKeys("byunknown82@protonmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sidharta0798!");
        driver.findElement(By.id("login")).click();
        LandingPage landingPage=new LandingPage(driver);

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[contains(@class,'ng-trigger')]"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("(//button/i)[3]")).click();
        List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
        Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();


        act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"bu").perform();
        driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
      String confirmMessage=  driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");
        driver.quit();





    }

}
