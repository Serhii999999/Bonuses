package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;
import mobile.services.MobileActions;
import java.net.MalformedURLException;

public class NoDepositPage {
    private final AndroidDriver driver;
    private final MobileActions actions;
    public NoDepositPage() throws MalformedURLException {
        actions = new MobileActions();
        driver = AndroidInitDriver.setUp();
}
    public String getAdditionalTextOfBonusFromRedirectedPage(){
       return driver.findElement(By.xpath("//*[@class='text-white text-left capitalize text-xl leading-8 font-medium md:pb-2']")).getText();
    }
}

