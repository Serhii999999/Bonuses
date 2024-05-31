package tests;


import mobile.pages.*;
import mobile.services.MobileActions;
import mobile.services.UrlService;
import mobile.settings.AndroidInitDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.net.MalformedURLException;


public class MobileBonusesTests {
    private MobileActions actions;
    private NoDepositPage noDepositPage;
    private CasinoBonusesPage casinoBonusesPage;
    private OnlineCasinosPage onlineCasinosPage;
    private FreeSlotsOnlinePage freeSlotsOnlinePage;
    private MainPage mainPage;

    @BeforeEach
    public void setup() throws MalformedURLException {

        actions = new MobileActions();
        noDepositPage = new NoDepositPage();
        casinoBonusesPage = new CasinoBonusesPage();
        onlineCasinosPage = new OnlineCasinosPage();
        freeSlotsOnlinePage = new FreeSlotsOnlinePage();
        mainPage = new MainPage();
    }
    @Test
    public void checkThreeBonusesAreCorrectOnMainPage() {
        actions.open(UrlService.PREPROD_URL_CASINO);
        String firstBonusText = actions.getTextOfBonusOnMainPage();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(firstBonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_CASINO, actions.getCurrentUrl(),firstBonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
        actions.closeCurrentWindow();

        actions.switchToPreviousTab();
        actions.refreshPage();
        actions.clickOnSecondBonus();
        mainPage.clickOnThirdBonus();
        //not finished
        //second bonus can't be opened, and third is with a picture
    }
    @Test
    public void checkBonusesAreCorrectOnFreeSpinsNoDepositPage() {
        actions.open(UrlService.PREPROD_URL_FREE_SPINS_NO_DEPOSIT);
        String bonusText = actions.getTextOfBonusOnMainPage();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_FREE_SPINS_NO_DEPOSIT, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
        actions.closeCurrentWindow();
        actions.switchToPreviousTab();

        actions.clickOnSecondBonus();
        //not finished - redirect is not working
    }
    @Test
    public void checkBonusIsCorrectOnNoDepositPageFor100Dollars() {
        actions.open(UrlService.PREPROD_URL_FREE_SPINS_NO_DEPOSIT_100_BUCKS);
        String firstBonusText = actions.getTextOfBonusOnMainPage();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        String additionalText = noDepositPage.getAdditionalTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(firstBonusText,firstBonusTextAfterClicking);
        boolean alternativeCheckForFirstCase = actions.checkBonusMainValuesAreEqual(firstBonusText,additionalText);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_FREE_SPINS_NO_DEPOSIT_100_BUCKS, actions.getCurrentUrl(),firstBonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(alternativeCheckForFirstCase|| checkForFirstCase);
        actions.closeCurrentWindow();
        actions.switchToPreviousTab();

        actions.clickOnSecondBonus();
        //not finished - redirect is not working
    }
    @Test
    public void checkBonusIsCorrectOnCasinoBonusesPageForStayCasino() {
        actions.open(UrlService.PREPROD_URL_CASINO_BONUSES_STAY_CASINO);
        String bonusText = casinoBonusesPage.getTextFromRestrictedBlock();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_CASINO_BONUSES_STAY_CASINO, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
    }

    @Test
    public void checkBonusIsCorrectOnCasinoBonusesPageForShazamPromo() {
        actions.open(UrlService.PREPROD_URL_CASINO_BONUSES_SHAZAM_PROMO);
        String bonusText = casinoBonusesPage.getTextFromTopTile();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = casinoBonusesPage.getTextFromRedirectedPageTitle();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_CASINO_BONUSES_SHAZAM_PROMO, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
    }
    @Test
    public void checkBonusIsCorrectOnOnlineCasinoPageForShazamPromo()  {
        actions.open(UrlService.PREPROD_URL_ONLINE_CASINOS_SHAZAM_PROMO);
        String bonusText = casinoBonusesPage.getTextFromTopTile();
        onlineCasinosPage.clickVisitCasinoButton();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = casinoBonusesPage.getTextFromRedirectedPageTitle();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_ONLINE_CASINOS_SHAZAM_PROMO, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
    }
    @Test
    public void checkBonusIsCorrectOnFreeSlotsOnlinePageForStarburst() {
        actions.open(UrlService.PREPROD_URL_FREE_SLOTS_ONLINE_STARBURST);
        String bonusText = casinoBonusesPage.getTextFromTopTile();
        actions.clickOnFirstBonus();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_ONLINE_CASINOS_SHAZAM_PROMO, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
        actions.closeCurrentWindow();
        actions.switchToPreviousTab();
        freeSlotsOnlinePage.clickPlayNowButton();
        actions.switchToNewTab();
        Assert.assertNotEquals(UrlService.PREPROD_URL_FREE_SLOTS_ONLINE_STARBURST,actions.getCurrentUrl());
    }
     @Test
    public void checkBonusIsCorrectOnOnlineCasinosPageForFastPayout() {
        actions.open(UrlService.PREPROD_URL_ONLINE_CASINOS_FAST_PAYOUT);
        String bonusText = actions.getTextOfBonusOnMainPage();
        onlineCasinosPage.clickVisitCasinoButton();
        actions.switchToNewTab();
        String firstBonusTextAfterClicking = actions.getTextOfBonusFromRedirectedPage();
        boolean checkForFirstCase = actions.checkBonusMainValuesAreEqual(bonusText,firstBonusTextAfterClicking);
        actions.printTextIfValuesAreNotEqual(UrlService.PREPROD_URL_ONLINE_CASINOS_FAST_PAYOUT, actions.getCurrentUrl(),bonusText,
                firstBonusTextAfterClicking,checkForFirstCase);
        Assert.assertTrue(checkForFirstCase);
        actions.closeCurrentWindow();
        actions.switchToPreviousTab();
         String bonusTextSecondCase = actions.getTextOfBonusOnMainPage();
         onlineCasinosPage.clickPlayNowButton();
         actions.switchToNewTab();
         String BonusTextAfterClickingSecondCase = actions.getTextOfBonusFromRedirectedPage();
         boolean checkForSecondCase = actions.checkBonusMainValuesAreEqual(bonusTextSecondCase,BonusTextAfterClickingSecondCase);
         Assert.assertTrue(checkForSecondCase);

     }
    @Test
    public void testClickOnLinkOpensAnotherUrl() throws InterruptedException {
        actions.open(UrlService.PROD_URL_CASINO);
        mainPage.clickShowTermsConditionsButton();
        Assert.assertTrue(mainPage.checkTermsAndConditionsIsVisible());
        mainPage.clickShowTermsConditionsButton();
        mainPage.clickShowTermsConditionsButton();
        mainPage.clickLinkForTermsAndConditions();
        actions.switchToNewTab();
        Assert.assertNotEquals(UrlService.PROD_URL_CASINO,actions.getCurrentUrl());
    }
    @AfterEach
    public void tearDown(){
        AndroidInitDriver.tearDown();
    }

}
