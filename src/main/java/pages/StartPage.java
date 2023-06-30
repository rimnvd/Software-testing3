package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "//a[normalize-space(text()) = 'Sign in']")
    public WebElement signInBtn;

    @FindBy(xpath = "//a[contains(@class, 'px-0')]")
    public WebElement pricingBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Product']")
    public WebElement productBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Solutions']")
    public WebElement solutionsBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Open Source']")
    public WebElement openSourceBtn;

    @FindBy(xpath = "//a[normalize-space(text()) = 'Sign up']")
    public WebElement signUpBtn;

    @FindBy(xpath = "/html/body/div[1]/div[4]/main/div[1]/div[2]/div/div/div[2]")
    public WebElement window;

    public StartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public void signInBtnClick() {
        signInBtn.click();
    }

    public void pricingBtnClick() {
        pricingBtn.click();
    }

    public void signUpBtnClick() {
        signUpBtn.click();
    }

    public void productBtnHover() {
        new Actions(webDriver).moveToElement(productBtn).perform();
    }

    public void solutionsBtnHover() {
        new Actions(webDriver).moveToElement(solutionsBtn).perform();
    }

    public void openSourceBtnHover() {
        new Actions(webDriver).moveToElement(openSourceBtn).perform();
    }
    
    public void moveToElement(String xpath) {
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath(xpath))).perform();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public WebElement getProductWindow() {
        return webDriver.findElement(By.xpath("//div[contains(@class, 'dropdown-menu-wide')]"));
    }

    public WebElement getSolutionsWindow() {
        return webDriver.findElement(By.xpath("//div[contains(@class, 'm-0')][.//*[@id='solutions-for-heading']]"));
    }

    public WebElement getOpenSourceWindow() {
        return webDriver.findElement(By.xpath("//div[contains(@class, 'm-0')][.//*[@id='open-source-repositories-heading']]"));
    }

    public WebElement getSignUpBtn() {
        return this.signUpBtn;
    }


}
