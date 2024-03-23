package emilpractise.pageobjects;

import emilpractise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//*[@class='cartSection']/h3")
    List<WebElement> cartProducts;
    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement ckeckoutButton;


    public Boolean verifyProductDisplay(String productName) {
        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

    }

    public CheckoutPage goToCheckout() {
        ckeckoutButton.click();
        return new CheckoutPage(driver);
    }

}
