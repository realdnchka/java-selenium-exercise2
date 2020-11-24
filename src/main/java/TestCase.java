import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestCase {
    private WebDriver driver;
    private String baseUrl = "https://online.mkb.ru/";
    private LoginPage loginPage;

    @BeforeTest
    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void login() {
        loginPage = new LoginPage(driver);

        loginPage.checkLoad();
        loginPage.inputUserName("Avtotest");
        loginPage.inputPassword("123456");
        loginPage.submitBtnClick();
        loginPage.checkError();
    }

    @Test(priority = 2)
    public void error15Min() {
        loginPage.loginRepeat("123456");
        loginPage.loginRepeat("123456");
        loginPage.loginRepeat("123456");
        loginPage.checkError15min();
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
