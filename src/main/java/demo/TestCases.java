package demo;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    SeleniumWrapper wrapper;

    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.wrapper = new SeleniumWrapper(driver);
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    //Navigate to this google form.
    public void googleFormLink(){
        System.out.println("Start Test case: Google Form Submission");
        wrapper.navigateTo("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");                  
        
    }
    //Fill in your name in the 1st text box
    public void enterName(){

        WebElement name_txtBox = driver.findElement(By.xpath("(//input[@class=\'whsOnd zHQkBf\'])[1]"));
        wrapper.click(name_txtBox, driver);        
        wrapper.sendKeys(name_txtBox, "Sirisha K");         
    }
    //Write down “I want to be the best QA Engineer! 1710572021'' where 1710572021 is variable - needs to be the current epoch time.    
    public void yAutomation(){

        WebElement automation_txtBox = driver.findElement(By.xpath("//div[@class=\'Pc9Gce Wic03c\']//textarea"));
        wrapper.click(automation_txtBox, driver);
        String str = "I want to be the best QA Engineer!";
        long epoch = System.currentTimeMillis()/1000;        
        String message = str + epoch ;
        wrapper.sendKeys(automation_txtBox, message);        
    }
    //Enter your Automation Testing experience in the next radio button
    public void selectRadioButton(){

        WebElement experience_txtBox = driver.findElement(By.xpath("(//div[@class=\'bzfPab wFGF8\'])[1]"));
        wrapper.click(experience_txtBox, driver);
    }
    //Select Java, Selenium and TestNG from the next check-box
    public void selectCheckBoxes(){
        
        List<WebElement> learned_txtBox = driver.findElements(By.xpath("//div[@class=\'eBFwI\']//label"));
        int list_Indexes[] = {0,1,3}; //{java,selenium,testng}
        for (int index : list_Indexes) {
            WebElement select_Index = learned_txtBox.get(index);
            wrapper.click(select_Index, driver);            
        }     
        
    }
    //Provide how you would like to be addressed in the dropdown
    public void selectDropDown(){

        WebElement addressedAs_txtBox = driver.findElement(By.xpath("//div[@jsname=\'LgbsSe\']//div/div[1]/span"));
        wrapper.click(addressedAs_txtBox, driver);           
        WebElement choose_Options = driver.findElement(By.xpath("//div[@class=\'OA0qNb ncFHed QXL7Te\']/div[4]/span"));
        wrapper.click(choose_Options, driver);            

    }
    /*Provided the current date minus 7 days in the next date field,
     it should be dynamically calculated and not hardcoded.*/
    public void selectDate(){
        
        WebElement date_txtBox = driver.findElement(By.xpath("//input[@type=\'date\']"));

        LocalDate currentDate = LocalDate.now();// Create a date object
        LocalDate before_7days = currentDate.minusDays(7);//minus 7 days
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy");// format changing pattern
        String final_Date_1 = myFormatObj.format(before_7days);
        
        wrapper.sendKeys(date_txtBox, final_Date_1);

    }
    //Provide the current time (Keeping in mind AM/PM) in the next field(AM/PM not visible in my Google Form)
    public void setTime(){        

        WebElement hour_txtBox = driver.findElement(By.xpath("//input[@aria-label=\'Hour\']"));
        wrapper.click(hour_txtBox, driver);        
        wrapper.sendKeys(hour_txtBox, "19");
        
        WebElement minute_txtBox = driver.findElement(By.xpath("//input[@aria-label=\'Minute\']"));
        wrapper.click(minute_txtBox, driver);        
        wrapper.sendKeys(minute_txtBox, "50");        
    }
    //Submit the Form
    public void SubmitForm(){
        
        WebElement submit_button = driver.findElement(By.xpath("//span[text()=\'Submit\']"));
        wrapper.click(submit_button, driver);     
        
        WebElement response = driver.findElement(By.className("vHW8K"));
        String response_message = response.getText();
        
        if(response_message.contains("Thanks for your response, Automation Wizard!")){
            System.out.println("Successfully Submitted the Form : " +response_message);
        }else{
            System.out.println("TestCase Failed");
        }

        System.out.println("End Test case: Submitted Form");

    }

}
