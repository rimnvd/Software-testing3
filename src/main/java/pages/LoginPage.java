package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "//a[text()='Create an account']")
    public WebElement createAccBtn;

    @FindBy(xpath = "//*[@id='login_field']")
    public WebElement loginInput;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement signInBtn;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public void createAccBtnClick() {
        createAccBtn.click();
    }

    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }
    
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void signInBtnClick() {
        signInBtn.click();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public WebElement getIncorrectMsg() {
        return webDriver.findElement(By.xpath("//div[contains(@class, 'flash-full')]"));
    }
    
    
}
