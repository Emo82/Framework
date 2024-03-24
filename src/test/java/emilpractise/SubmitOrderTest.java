package emilpractise;

import TestComponents.BaseTest;
import emilpractise.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {

    String productName = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData",groups={"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("bu");
        ConfirmationPage confirmationPage = checkoutPage.submit();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");


    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("byunknown82@protonmail.com", "Test1@@@");
        OrderPage orderPage = productCatalogue.goToOrders();
       Assert.assertTrue( orderPage.verifyOrderDisplay(productName));
    }



    @DataProvider
    public Object [][] getData() throws IOException {


        List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//testdata//PurchaseOrder.json");
        return new Object [][] {{data.get(0)},{data.get(1)}};

    }
}


