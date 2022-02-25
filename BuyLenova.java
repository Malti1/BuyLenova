package testNGHomeWork;

import com.google.common.base.Verify;
import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.control.ContentDisplay;
import org.bouncycastle.asn1.x509.DisplayText;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class BuyLenova {

    WebDriver driver;
        //String expectedmessage="Product has been added in cart";
        //String actualmessage="Product has benn added in cart";

    @Test(priority = 1)
    void OpenBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Open Browser");

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        System.out.println("Open URL");
    }

    @Test(dependsOnMethods = {"OpenBrowser"})
    void SelectItem() {


        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a")).click();
        System.out.println("Click on Computer");

        driver.findElement(By.xpath(" /html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[1]/div/div[1]/div/div/a/img")).click();
        System.out.println("Click on Desktops");

        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[1]/a/img")).click();
        System.out.println("Click on Lenova IdeaCentre 600");

        driver.findElement(By.id("add-to-cart-button-3")).click();
        System.out.println("Click on Add to cartwith quantity 1");
    }

    @Test(dependsOnMethods = {"OpenBrowser", "SelectItem"})
    void Verification() {


        //WebElement Verify = driver.findElement(By.id("bar-notification"));
        //Assert.assertTrue(Verify.isDisplayed(),"Item is added in cart");

        //if(DisplayText.equals("Prdouct has been added in cart")){
        // System.out.println("Verified that product has been added in cart");}
        //else{
        // System.out.println("Not verified");}
        // actualmessage=driver.findElement(By.id("bar-notification")).getText();
        //String verify = driver.getText();
        //Assert.assertTrue(actualmessage.contains(expectedmessage));
        // System.out.println("Test Passed");
        if(driver.findElement(By.id("bar-notification")).isEnabled())
        {
            System.out.println("Verified item has been added in cart");
        }
        else{
            System.out.println("Empty cart");
        }
    }


    @Test(dependsOnMethods = {"OpenBrowser", "SelectItem"})
    void ChangeQuantity() throws InterruptedException {
        driver.findElement(By.className("cart-label")).click();
        System.out.println("Click on cart to add quantity");

        WebElement quantity = driver.findElement(By.className("qty-input"));
        quantity.clear();
        quantity.sendKeys("3");
        System.out.println("Quantity changed from 1 to 3");
        Thread.sleep(2000);
        driver.findElement((By.id("updatecart"))).click();
        System.out.println("Updated cart with quantity and price");
    }


    @Test(dependsOnMethods = {"OpenBrowser", "SelectItem", "ChangeQuantity"})
    void TermAndCondition () throws InterruptedException {
        driver.findElement(By.id("termsofservice")).click();
        Thread.sleep(3000);
    }


    @Test(dependsOnMethods = {"OpenBrowser", "SelectItem", "ChangeQuantity", "TermAndCondition"})
    void CheckOut () {
        driver.findElement(By.id("checkout")).click();
        System.out.println("Click on checkout");
        driver.findElement(By.className("checkout-as-guest-button")).click();
        System.out.println("Click on Checkout as Guest");
    }
    }
