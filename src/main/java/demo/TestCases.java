package demo;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases extends SeleniumWrapper {
    static ChromeDriver driver;
    SeleniumWrapper wrapper;

    public TestCases() {
        super(driver);
        //System.out.println("Constructor: TestCases");
        //WebDriverManager.chromedriver().timeout(30).setup(); // mentor has suggested to comment as part of CR
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wrapper = new SeleniumWrapper(driver);
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // Navigate to this google form.
    public void googleFormAutomation() {
        System.out.println("Start Test case: Google Form Submission");
        try {
            wrapper.navigateTo(
                    "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        } catch (Exception e) {
            System.out.println("Error navigating to Google Form: " + e.getMessage());
        }   

    // Fill in your name in the 1st text box    
        try {
            WebElement name_txtBox = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]"));
            wrapper.click(name_txtBox, driver);
            wrapper.sendKeys(name_txtBox, "Sirisha K");
        } catch (Exception e) {
            System.out.println("Error entering name: " + e.getMessage());
        }   

    // Write down “I want to be the best QA Engineer! 1710572021'' where 1710572021
    // is variable - needs to be the current epoch time.
        try {
            WebElement automation_txtBox = driver.findElement(By.xpath("//div[@class='Pc9Gce Wic03c']//textarea"));
            wrapper.click(automation_txtBox, driver);
            String str = "I want to be the best QA Engineer!";
            long epoch = System.currentTimeMillis() / 1000;
            String message = str + epoch;
            wrapper.sendKeys(automation_txtBox, message);
        } catch (Exception e) {
            System.out.println("Error entering automation message: " + e.getMessage());
        }
    
    // Enter your Automation Testing experience in the next radio button    
        try {
            WebElement experience_txtBox = driver.findElement(By.xpath("(//div[@class='bzfPab wFGF8'])[1]"));
            wrapper.click(experience_txtBox, driver);
        } catch (Exception e) {
            System.out.println("Error selecting radio button: " + e.getMessage());
        }
   
    // Select Java, Selenium and TestNG from the next check-box
        try {
            List<WebElement> learned_txtBox = driver.findElements(By.xpath("//div[@class='eBFwI']//label"));
            int list_Indexes[] = { 0, 1, 3 }; // {java,selenium,testng}
            for (int index : list_Indexes) {
                WebElement select_Index = learned_txtBox.get(index);
                wrapper.click(select_Index, driver);
            }
        } catch (Exception e) {
            System.out.println("Error selecting checkboxes: " + e.getMessage());
        }  

    // Provide how you would like to be addressed in the dropdown
        try {
            WebElement addressedAs_txtBox = driver.findElement(By.xpath("//div[@jsname='LgbsSe']//div/div[1]/span"));
            wrapper.click(addressedAs_txtBox, driver);
            WebElement choose_Options = driver.findElement(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']/div[4]/span"));
            wrapper.click(choose_Options, driver);
        } catch (Exception e) {
            System.out.println("Error selecting dropdown options: " + e.getMessage());
        }   
    /*
     * Provided the current date minus 7 days in the next date field,
     * it should be dynamically calculated and not hardcoded.
     */
        try {
            WebElement date_txtBox = driver.findElement(By.xpath("//input[@type='date']"));
            LocalDate currentDate = LocalDate.now();// Create a date object
            LocalDate before_7days = currentDate.minusDays(7);// minus 7 days
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy");// format changing pattern
            String final_Date_1 = myFormatObj.format(before_7days);
            wrapper.sendKeys(date_txtBox, final_Date_1);
        } catch (Exception e) {
            System.out.println("Error selecting date: " + e.getMessage());
        }
    
    // Provide the current time (Keeping in mind AM/PM) in the next field(AM/PM not
    // visible in my Google Form)   
        try {
            WebElement hour_txtBox = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
            //get the local time in HH:mm
            LocalTime  systemTime = LocalTime.now();
            //extract only hour
            int hh = systemTime.getHour();//24hour format
            int hour = hh % 12;//12hour format
            if (hour == 0) {
                hour = 12; // Adjust for midnight and noon
            }
            //extra only minutes
            int mm = systemTime.getMinute();            
            //convert to string
            String format_HH = Integer.toString(hh);
            String format_mm = Integer.toString(mm);
            
            wrapper.click(hour_txtBox, driver);
            wrapper.sendKeys(hour_txtBox, format_HH);

            WebElement minute_txtBox = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
            wrapper.click(minute_txtBox, driver);
            wrapper.sendKeys(minute_txtBox, format_mm);
        } catch (Exception e) {
            System.out.println("Error setting time: " + e.getMessage());
        }    

    // Submit the Form    
        try {
            WebElement submit_button = driver.findElement(By.xpath("//span[text()='Submit']"));
            wrapper.click(submit_button, driver);

            WebElement response = driver.findElement(By.className("vHW8K"));
            String response_message = response.getText();

            if (response_message.contains("Thanks for your response, Automation Wizard!")) {
                System.out.println("Successfully Submitted the Form : " + response_message);
            } else {
                System.out.println("TestCase Failed");
            }

            System.out.println("End Test case: Submitted Form");
        } catch (Exception e) {
            System.out.println("Error submitting form: " + e.getMessage());
        }
    }
}
