import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import java.util.concurrent.TimeUnit;

public class TestCase {
    private static WebDriver driver;
    private static String baseUrl = "https://online.mkb.ru/";
    private static LoginPage loginPage;
    private String password = "123456";
    private String username = "Avtotest";
    @BeforeClass
    public static void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void login() {
        loginPage = new LoginPage(driver);

        loginPage.checkLoad();
        loginPage.inputUserName(username);
        loginPage.inputPassword(password);
        loginPage.submitBtnClick();
        loginPage.waitForLoaderDisappear();
        loginPage.checkError();
    }

    @Test
    public void error15Min() {
        loginPage = new LoginPage(driver);
        for (int i = 0; i < 3; i++) {
            loginPage.inputPassword(password);
            loginPage.submitBtnClick();
            loginPage.waitForLoaderDisappear();
        }
        loginPage.checkError15min();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
