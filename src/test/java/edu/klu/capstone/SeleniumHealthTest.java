
package edu.klu.capstone;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SeleniumHealthTest {

    @Test
    void healthEndpointOpensInChromeBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://localhost:8081/api/health");

            String pageContent = driver.findElement(
                org.openqa.selenium.By.tagName("body")
            ).getText();

            assertTrue(
                pageContent.contains("\"status\":\"UP\"")
                    || pageContent.contains("\"status\": \"UP\""),
                "Health endpoint should return status UP"
            );
        } finally {
            driver.quit();
        }
    }
}
