import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateNewJobTest {

    private final static String EMAIL = "mwachala@gmail.com";
    //This should not be here
    private final static String PWD = "zaq1@WSX1";

    //This is usually one in some general test class
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private JobPage jobPage;
    private CustomerPage customerPage;

    @Before
    public void setUp(){
        //This is usually done in some parent test class
        driver=new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
    }

    @Test
    public void CreateNewJobTest() {
        //given
        final String CUSTOMER_NAME = "Bob";
        final String FROM = "Wed, Jul 6 2022";
        final String TO = "Wed, Jul 7 2022";
        final String LINE_ITEM = "Test line item";
        final String NOTE_VALUE = "TestNote";
        //when
        loginPage.load();
        dashboardPage = loginPage.logIn(EMAIL, PWD);
        jobPage = dashboardPage.openNewJobCreationForm();
        customerPage = jobPage.openNewCustomerCreationForm()
                .createNewCustomer(CUSTOMER_NAME);
        jobPage.setScheduleDates(FROM, TO)
                .addLineItem(LINE_ITEM)
                .addPrivateNote(NOTE_VALUE)
                .saveJob();
        //then assertions here
    }
}
