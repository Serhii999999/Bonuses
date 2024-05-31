package mobile.pages;


import io.appium.java_client.android.AndroidDriver;
import mobile.services.MobileActions;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class MainPage {
    private final AndroidDriver driver;
    private final MobileActions actions;
    public MainPage() throws MalformedURLException {
        driver = AndroidInitDriver.setUp();
        actions = new MobileActions();
    }

    public void clickOnThirdBonus(){
        actions.click(driver.findElement(By.xpath("(//*[text()='Get Bonus'])[3]")));
    }

    public String getTextOfSecondBonusOnMainPage(){
        return actions.getTextByXPath("(//div[@class='card-title'])[2]");
    }
    public String getTextOfThirdBonusOnMainPage(){
        return actions.getTextByXPath("(//div[@class='card-title'])[3]");
    }
    public void clickShowTermsConditionsButton() throws InterruptedException {
        actions.clickWithJS(driver.findElement(By.xpath("//span[@data-text-before='Show T&C']")));
        Thread.sleep(1500);
    }
    public void clickLinkForTermsAndConditions(){
        actions.click(driver.findElement(By.xpath("//p[@class='card-terms-block shown']")));
    }
    public boolean checkTermsAndConditionsIsVisible(){
        return driver.findElement(By.xpath("//p[@class='card-terms-block shown']")).isDisplayed();
    }
}
