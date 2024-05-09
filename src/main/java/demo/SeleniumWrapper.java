package demo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {
    WebDriver driver;
    static WebDriverWait wait;
    static JavascriptExecutor js;
   

    public SeleniumWrapper(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor)driver;      
    }


    public void click(WebElement elementToClick,WebDriver driver) {
        try {            
            if (elementToClick != null && elementToClick.isDisplayed()) {       
            elementToClick.click();
            Thread.sleep(1000);            
            }else {
                System.out.println("Element is not displayed ");             
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking: ");
            e.printStackTrace();          
        } 
    }   

    public void sendKeys(WebElement inputBox, String keysToSend) {
        try {           
            inputBox.sendKeys(keysToSend);
            Thread.sleep(1000);            
            js.executeScript("window.scrollBy(0,400);");
       
        } catch (Exception e) {
            System.out.println("Exception occurred while sending Keys: " + e.getMessage());         
        }
    }

    public void navigateTo(String url) {
        try {            
            if (!(driver.getCurrentUrl().equals(url))) {
                driver.get(url);
                Thread.sleep(3000);
            }            
        } catch (Exception e) {
           System.out.println("Exception occurred while Navigating: " + e.getMessage());
           
        }
    }

}
