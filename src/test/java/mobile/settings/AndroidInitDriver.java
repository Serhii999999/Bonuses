package mobile.settings;


import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidInitDriver {
    public static AndroidDriver androidDriver;

    public static AndroidDriver setUp() throws MalformedURLException {
        if(androidDriver==null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "14.0");
            caps.setCapability("deviceName", "Pixel 3a API 34");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("browserName", "Chrome");

            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        }
        return androidDriver;
    }

    @AfterAll
    public static void tearDown() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
    }
}
