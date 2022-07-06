import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends LoadableComponent<DashboardPage> {

    private static final By WELCOME_HEADER = By.xpath("//h2[text()='Welcome to Housecall Pro']");

    private final WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//button[@data-testid = 'add-new-button']") WebElement addNewBtn;
    @FindBy(xpath = "//ul[@role = 'menu']//ul//li[@data-testid = 'add-new-job']") WebElement jobOpt;
    @FindBy(xpath = "//ul[@role = 'menu']//ul//li[@data-testid = 'add-new-customer']") WebElement newCustomerOpt;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public JobPage openNewJobCreationForm() {
        addNewBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(jobOpt));
        jobOpt.click();
        return new JobPage(this.driver);
    }

    @Override
    protected void load() {
        wait.until(ExpectedConditions.presenceOfElementLocated(WELCOME_HEADER));
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.presenceOfElementLocated(WELCOME_HEADER));
    }
}
