package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(password);
    }

    public void submitBtnClick() {
        WebElement submitBtn = driver.findElement(By.name("btnLoginStandard"));
        submitBtn.click();
    }

    public void checkError() {
        String errorText = driver.findElement(By.id("errMessage")).getText();
        Assert.assertEquals(errorText, "Ошибка аутентификации.\n" +
                "Проверьте правильность указания логина и пароля.");
    }

    public void checkError15min() {
        String errorMsg = driver.findElement(By.id("errMessage")).getText();
        Assert.assertTrue(errorMsg.contains("В целях безопасности вход в систему ограничен."));
    }

    public void waitForLoaderDisappear() {
        WebElement loadSpinner = driver.findElement(By.className("block-disabled"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.stalenessOf(loadSpinner));
    }
}
