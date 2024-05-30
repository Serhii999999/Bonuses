package mobile.pages;


import io.appium.java_client.android.AndroidDriver;
import mobile.services.MobileActions;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class CasinoBonusesPage {
    private final AndroidDriver driver;
    private final MobileActions actions;
    WebDriverWait wait;
    public CasinoBonusesPage() throws MalformedURLException {
        driver = AndroidInitDriver.setUp();
        actions = new MobileActions();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickOnFirstBonus(){
        actions.click(driver.findElement(By.xpath("//button//*[text()='Get Bonus']")));
    }
    public void clickOnFirstBonusFromTable(){
        actions.click(driver.findElement(By.xpath("//table//*[text()='Get Bonus']")));
    }
    public String getTextFromRestrictedBlock(){
        return actions.getTextByXPath("//div[@class='restrictedBlock__card__content--main']");
    }
    public String getTextFromTopTile(){
       return actions.getTextByXPath("//div[@class='bonusWelcomeTypeWidget__card__top__title']");
    }
    public String getTextFromRedirectedPageTitle(){
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //p[@class='offer_main__bonus']")));
        return element.getText();
    }


}
