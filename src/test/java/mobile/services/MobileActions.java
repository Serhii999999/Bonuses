package mobile.services;

import io.appium.java_client.android.AndroidDriver;
import mobile.settings.AndroidInitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobileActions {
    private AndroidDriver driver;
    WebDriverWait wait;

    public MobileActions() throws MalformedURLException {
        driver = AndroidInitDriver.setUp();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public  void open(String url){
        driver.navigate().to(url);
    }
    public  void click(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable
                (element));
        if(element.isDisplayed() && element.isEnabled()){
            element.click();
        }
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public void clickWithJS(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String getTextByXPath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(element.isDisplayed() && element.isEnabled())
            return element.getText();
        return "";
    }
    public  void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        while (allWindows.size() == 1) {
            allWindows = driver.getWindowHandles();
        }
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    public void switchToPreviousTab(){
        String originalWindow = driver.getWindowHandles().iterator().next();
        driver.switchTo().window(originalWindow);
    }
    public  boolean checkBonusMainValuesAreEqual(String str1, String str2) {
        String percentPattern = "(\\d+)%";
        String dollarPattern = "\\$(\\d{1,3}(,\\d{3})*(\\.\\d{1,2})?)";
        String spinsPattern = "(\\d+)\\s+(Free\\s+)?[Ss]pins";

        String percent1 = extractValueFromBonusString(str1, percentPattern);
        String percent2 = extractValueFromBonusString(str2, percentPattern);
        String spins1 = extractValueFromBonusString(str1, spinsPattern);
        String spins2 = extractValueFromBonusString(str2, spinsPattern);
        String dollar1 = extractValueFromBonusString(str1, dollarPattern);
        String dollar2 = extractValueFromBonusString(str2, dollarPattern);

        if(percent1!=null && !percent1.isEmpty() && percent2!=null && !percent2.isEmpty() && !percent1.equals(percent2) ){
            return false;
        }
        if(spins1!=null && !spins1.isEmpty() && spins2!=null && !spins2.isEmpty() && !spins1.equals(spins2) ){
            return false;
        }
        if(dollar1!=null && !dollar1.isEmpty() && dollar2!=null && !dollar2.isEmpty() && !dollar1.equals(dollar2) ){
            return false;
        }
        assert percent1 != null;
        if (percent1.equals(percent2)) return true;
        assert dollar1 != null;
        if (dollar1.equals(dollar2)) return true;
        assert spins1 != null;
        return spins1.equals(spins2);
    }

    private  String extractValueFromBonusString(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.find() ? m.group(1) : "";
    }
    public void closeCurrentWindow(){
        driver.close();
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
    public void clickOnFirstBonus(){
        click(driver.findElement(By.xpath("//*[text()='Get Bonus']")));
    }
    public void clickOnSecondBonus(){
        click(driver.findElement(By.xpath("(//*[text()='Get Bonus'])[2]")));
    }
    public String getTextOfBonusOnMainPage(){
        return getTextByXPath("//div[@class='card-title']");
    }
    public String getTextOfBonusFromRedirectedPage(){
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1//span")));
        return element.getText();
    }
    public void printTextIfValuesAreNotEqual(String basicUrl, String redirectUrl, String firstText, String secondText, boolean methodResult){
        if(!methodResult){
            System.out.println("Values are incorrect!");
            System.out.println("Result from original url "+basicUrl + ": "+firstText);
            System.out.println("Result from redirected url "+redirectUrl + ": "+secondText);
        }
    }
}
