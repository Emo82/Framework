package emilpractise.AbstractComponents;

import emilpractise.pageobjects.CartPage;
import emilpractise.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
WebDriver driver;



    public AbstractComponent(WebDriver driver) {
        this.driver=driver;

        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "(//button/i)[3]")
    WebElement cartHeader;
    @FindBy(xpath = "(//button/i)[2]")
    WebElement ordersHeader;



    public void waitForElementToAppear(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
    }
    public void waitForWebElementToAppear(WebElement ele){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));

    }
    public void waitForElementToDisappear(WebElement ele ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }


    public CartPage goToCartPage() {

        cartHeader.click();
        return new CartPage(driver);
    }
    public OrderPage goToOrders(){
        ordersHeader.click();
        return new OrderPage(driver);
    }





}
