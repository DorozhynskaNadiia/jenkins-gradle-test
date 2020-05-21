import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class InitialTest {
    private WebDriver driver;

    @Before
    public void setup() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("81.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        try {

            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
        }
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchGoogleForAgency04() {
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.clear();
        searchBar.sendKeys("AG04");
        searchBar.submit();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/search"));
        assertThat(driver.getTitle(), containsString("AG04"));
    }
}