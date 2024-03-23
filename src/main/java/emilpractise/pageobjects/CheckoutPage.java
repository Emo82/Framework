package emilpractise.pageobjects;

import emilpractise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(xpath = "(//span[@class='ng-star-inserted'])[2]")
            WebElement selectCountry;
    @FindBy(css = ".action__submit")
            WebElement submit;


public void selectCountry(String countryName){
    Actions act = new Actions(driver);
    act.sendKeys(country,countryName).perform();
    selectCountry.click();

}
public ConfirmationPage submit(){
    submit.click();
    return new ConfirmationPage(driver);
}
}
