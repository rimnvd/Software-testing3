package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewRepositoryPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[1]/div[6]/main/react-app/div/div/form/div[5]/button")
    public WebElement createRepositoryBtn;

    @FindBy(xpath = "//*[@id='react-aria-2']")
    public WebElement repositoryName;

    public NewRepositoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public void createRepositoryBtnClick() {
        createRepositoryBtn.click();
    }
    
    public WebElement getBlankNameMsg() {
        return webDriver.findElement(By.xpath("//*[@id=\"react-aria-2-validationMessage\"]"));
    }

    public void waitForBtn() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[6]/main/react-app/div/div/form/div[5]/button")));
    }

    public void inputRepositoryName(String name) {
        repositoryName.sendKeys(name);
        repositoryName.sendKeys(Keys.ENTER);
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.urlToBe(url));
    }

}
