package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver webDriver;

    @FindBy(xpath = "//a[text()='Issues']")
    public WebElement issuesBtn;

    @FindBy(xpath = "//button[@data-hotkey='s,/']")
    public WebElement searchBtn;

    @FindBy(xpath = "//div[@class='Header-item position-relative mr-0 d-none d-md-flex']")
    public WebElement profileIcon;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div[7]/details/details-menu/a[1]")
    public WebElement yourProfileBtn;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div[7]/details/details-menu/a[2]")
    public WebElement repositoriesBtn;
    
    @FindBy(xpath = "/html/body/div[1]/div[6]/div/div/aside/div/loading-context/div/div[1]/div/ul/li[5]/div/div/a")
    public WebElement exampleRepository;

    @FindBy(xpath = "//a[contains(@data-hydro-click-hmac, 'dcb')]")
    public WebElement newRepositoryBtn;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div[7]/details/details-menu/form/button")
    public WebElement signOutBtn;
    

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void issuesBtnClick() {
        issuesBtn.click();
    }

    public void profileIconClick() {
        profileIcon.click();
    }

    public void yourProfileBtnClick() {
        yourProfileBtn.click();
    }

    public void repositoriesBtnClick() {
        repositoriesBtn.click();
    }

    public void exampleRepositoryClick() {
        exampleRepository.click();
    }

    public void newRepositoryBtnClick() {
        newRepositoryBtn.click();
    }

    public void signOutBtnClick() {
        signOutBtn.click();
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public WebElement getProfileIconMenu() {
        return webDriver.findElement(By.xpath("//details-menu[@style='width: 180px']"));
    }

    public WebElement getProfileIcon() {
        return this.profileIcon;
    }
}
