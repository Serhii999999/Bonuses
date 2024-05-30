package mobile.pages;


import io.appium.java_client.android.AndroidDriver;
import mobile.services.MobileActions;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class FreeSlotsOnlinePage {
    private final AndroidDriver driver;
    private final MobileActions actions;
    public FreeSlotsOnlinePage() throws MalformedURLException {
        driver = AndroidInitDriver.setUp();
        actions = new MobileActions();
    }
    public void clickPlayNowButton(){
        actions.click(driver.findElement(By.xpath("//*[@id='playNowButton']")));
    }
    public String getTextFromTopTile(){
        return actions.getTextByXPath("//div[@class='bonusWelcomeTypeWidget__card__top__title']");
    }
}
