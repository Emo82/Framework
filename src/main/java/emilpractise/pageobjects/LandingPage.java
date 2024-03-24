package emilpractise.pageobjects;

import emilpractise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

//    WebElement useremail = driver.findElement(By.xpath("//label[text()=\"Email\"]/following-sibling::input[@id=\"userEmail\"]"));

    @FindBy(xpath = "//label[text()=\"Email\"]/following-sibling::input[@id=\"userEmail\"]")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(css ="[class*='flyInOut']")
    WebElement incorrectPasswordMsg;


    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(incorrectPasswordMsg);
        return incorrectPasswordMsg.getText();


    }
}

