package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkLoad() {
        driver.findElement(By.id("main-page-title"));
    }

    public void inputUserName(String username) {
        driver.findElement(By.id("txtLogin")).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(By.id("txtPassword")).sendKeys(password);
    }

    public void submitBtnClick() {
        driver.findElement(By.name("btnLoginStandard")).click();
    }

    public void checkError() {
        waitForLoaderDisappear();
        String errorText = driver.findElement(By.id("errMessage")).getText();
        Assert.assertEquals(errorText, "Ошибка аутентификации.\n" +
                "Проверьте правильность указания логина и пароля.");
    }

    public void checkError15min() {
        waitForLoaderDisappear();
        String errorMsg = driver.findElement(By.id("errMessage")).getText();
        Assert.assertTrue(errorMsg.contains("В целях безопасности вход в систему ограничен."));
    }

    public void loginRepeat(String password) {
        waitForLoaderDisappear();
        inputPassword(password);
        submitBtnClick();
    }

    private void waitForLoaderDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("block-disabled"))));
    }
}
