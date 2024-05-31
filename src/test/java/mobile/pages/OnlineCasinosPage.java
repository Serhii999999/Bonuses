package mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import mobile.services.MobileActions;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class OnlineCasinosPage {
    private final AndroidDriver driver;
    private final MobileActions actions;
    public OnlineCasinosPage() throws MalformedURLException {
        driver = AndroidInitDriver.setUp();
        actions = new MobileActions();
    }
    public void clickVisitCasinoButton(){
        actions.click(driver.findElement(By.xpath("//button[@class='bonusWelcomeTypeWidget__card__btn']")));
    }
    public void clickPlayNowButton(){
        actions.click(driver.findElement(By.xpath("//button//span[text()='Play Now']")));
    }
    public String getTextFromTopTile(){
        return actions.getTextByXPath("//div[@class='bonusWelcomeTypeWidget__card__top__title']");
    }
}
