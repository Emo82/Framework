package emilpractise.pageobjects;

import emilpractise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

  @FindBy(xpath = "//td[position()=2]")
    List<WebElement> productNames;



    public Boolean verifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
    }
}
