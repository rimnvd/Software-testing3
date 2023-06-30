package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExampleRepositoryPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/git-clone-help/div[1]/p/a[1]")
    public WebElement createNewFileBtn;

    @FindBy(xpath = "//*[@id=\"settings-tab\"]/span[1]")
    public WebElement settingsBtn;

    public ExampleRepositoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void createNewFileClk() {
        createNewFileBtn.click();
    }

    public void settingsBtnClick() {
        settingsBtn.click();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

}
