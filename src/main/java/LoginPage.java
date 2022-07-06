import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends LoadableComponent<LoginPage> {

    private final WebDriver driver;
    private WebDriverWait wait;
    //HOST is usually in some config class
    private final static String HOST = "https://pro.housecallpro-staging.com/pro/log_in";
    @FindBy(id = "email") private WebElement login;
    @FindBy(id = "password") private WebElement password;
    @FindBy(xpath = "//span[contains(@class, 'Button') and text() = 'Sign in']") private WebElement submit;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public DashboardPage logIn(String email, String pwd) {
        login.sendKeys(email);
        password.sendKeys(pwd);
        login.click();
        return new DashboardPage(this.driver);
    }

    @Override
    protected void load() {
        driver.get(HOST);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
    }
}
