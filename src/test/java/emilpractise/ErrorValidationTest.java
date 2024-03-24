package emilpractise;

import TestComponents.BaseTest;
import emilpractise.pageobjects.CartPage;
import emilpractise.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class ErrorValidationTest extends BaseTest {


    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws IOException {

       landingPage.loginApplication("byunknown82@protonmail.com", "0798!");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");




    }
    @Test
    public void productErrorValidation() throws IOException {

        String productName = "ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue = landingPage.loginApplication("byunknown82@protonmail.com", "Test1@@@");
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINA");
        Assert.assertFalse(match);




    }
}


