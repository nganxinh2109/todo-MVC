package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Browser {
    private static WebDriver drive;
    private static int MAX_TIME_OUT = 30;
    public static WebDriverWait wait;
    public static Actions action;

    public static void openBrowser(String name) {
        switch (name.toLowerCase()) {
            case "firefox" -> drive = new FirefoxDriver();
            case "chrome" -> drive = new ChromeDriver();
            case "safari" -> drive = new SafariDriver();
            case "edgs" -> drive = new EdgeDriver();
            default -> drive = new ChromeDriver();
        }
        wait = new WebDriverWait(drive , Duration.ofSeconds(MAX_TIME_OUT));
        action = new Actions(drive);
    }
    public static WebDriver getDriver(){
        return drive;
    }
    public static void capturScreenShot(String tcName){
        TakesScreenshot scrShot = ((TakesScreenshot) drive);
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);

        File destFile = new File(String.format("target/screenshot-%s-%s.png" , tcName, System.currentTimeMillis()));
        try {
            FileUtils.copyFile(scrFile , destFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static void closeBrowser(){
        drive.quit();
    }
    public static void visit(String url){
        drive.get(url);
    }
    public static void click(By locator){
        drive.findElement(locator).click();
    }
    public static void fill(By locator, String withText){
        drive.findElement(locator).clear();
        drive.findElement(locator).sendKeys(withText);
    }
    public static String getText(By locator){
        return drive.findElement(locator).getText();
    }
    public static boolean isDisplay(By locator){
        return drive.findElements(locator).size()>0;
    }
    public static void hover(By locator){
        action.moveToElement(drive.findElement(locator)).perform();
    }
    public static WebElement getElement(By locator){
        return drive.findElement(locator);
    }
    public static void doubleClick(By locator){
        action.doubleClick(drive.findElement(locator)).perform();
    }
    public static void executeScript(String script, Object... arguments) {
        JavascriptExecutor js = (JavascriptExecutor) drive;
        js.executeScript(script, arguments);
    }
}
