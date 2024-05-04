package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import static common.Browser.*;

public class TestBase {
    //WebDriver driver = new ChromeDriver();
    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult testResult) {
        String tcName = testResult.getMethod().getMethodName();
        if (!testResult.isSuccess()) {
            capturScreenShot(tcName);
        }
    }
}
