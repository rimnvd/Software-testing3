package utils.driver;

public final class Constants {
    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String WEBDRIVER_FIREFOX_DRIVER = "webdriver.firefox.driver";
    public static final String CHROME_DRIVER = "chromedriver.exe";
    public static final String FIREFOX_FIREFOX = "geckodriver.exe";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
