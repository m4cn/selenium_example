import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class JobPage extends LoadableComponent<JobPage> {


    private final WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//span[contains(@class, 'Button') and text() = '+ New customer']")
    private WebElement newCustomer;
    @FindBy(xpath = "(//input[@id = 'input-date'])[1]") private WebElement fromDate;
    @FindBy(xpath = "(//input[@id = 'input-date'])[2]") private WebElement toDate;
    @FindBy(xpath = "//span[contains(@class, 'Button') and text() = '+ Services item']")
    private WebElement addServiceLine;
    @FindBy(xpath = "(//input[@id = 'item-name'])[last()]")
    private WebElement serviceItemInput;
    @FindBy(xpath = "//*[contains(text(),'Private notes')]/ancestor::div[contains(@class, 'MuiPaper-root')]")
    private WebElement notesSection;
    @FindBy(xpath = "//span[contains(@class, 'Button')]//p[contains(text(),'Private notes')]")
    private WebElement addPrivateNoteBtn;
    @FindBy(xpath = "//span[contains(text(),'Private notes')]/ancestor::div[contains(@class, 'MuiPaper-root')]//textarea[1]")
    private WebElement noteInput;
    @FindBy(xpath = "//span[contains(@class, 'Button') and text() = 'Save job']")
    private WebElement saveJobBtn;

    public JobPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public CustomerPage openNewCustomerCreationForm() {
        newCustomer.click();
        return new CustomerPage(this.driver);
    }

    public JobPage setScheduleDates(String from, String to) {
        fromDate.sendKeys(from);
        fromDate.sendKeys(Keys.RETURN);
        toDate.sendKeys(to);
        toDate.sendKeys(Keys.RETURN);
        return this;
    }

    public JobPage addLineItem(String lineItemValue) {
        addServiceLine.click();
        serviceItemInput.sendKeys(lineItemValue);
        return this;
    }

    public JobPage addPrivateNote(String noteValue) {
        addPrivateNoteBtn.click();
        noteInput.sendKeys(noteValue);
        return this;
    }

    public DashboardPage saveJob() {
        saveJobBtn.click();
        return new DashboardPage(this.driver);
    }

    @Override
    protected void load() {
        wait.until(ExpectedConditions.elementToBeClickable(newCustomer));
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.elementToBeClickable(newCustomer));
    }
}
