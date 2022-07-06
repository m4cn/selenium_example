import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

    protected WebDriver driver;

    public void BasePage(){
        //if needed
        //System.setProperty("webdriver.chrome.driver", "path of driver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
}
