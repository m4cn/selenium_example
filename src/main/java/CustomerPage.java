import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage extends LoadableComponent<CustomerPage> {

    private static final By WELCOME_HEADER = By.xpath("//h2[text()='Welcome to Housecall Pro']");

    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[contains(text(),'Customer')]/ancestor::div[contains(@class, 'MuiPaper-root')]") WebElement customerSection;
    @FindBy(xpath = "//h2[contains(text(),'Add new customer')]") WebElement newForm;
    @FindBy(xpath = "customer-dialog-first-name") WebElement name;
    @FindBy(xpath = "//button[@type = 'submit']") WebElement submit;
    @FindBy(xpath = "//span[text()='Job']") WebElement newJob;

    public CustomerPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public CustomerPage createNewCustomer(String nameText) {
        wait.until(ExpectedConditions.elementToBeClickable(customerSection));
        name.sendKeys(nameText);
        submit.click();
        return this;
    }

    public JobPage addNewJob(){
        newJob.click();
        return new JobPage(this.driver);
    }

    @Override
    protected void load() {
        wait.until(ExpectedConditions.elementToBeClickable(customerSection));
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.elementToBeClickable(customerSection));
    }
}
