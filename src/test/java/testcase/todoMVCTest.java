package testcase;

import base.TestBase;
import common.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.todoPage;

import static common.Browser.*;

public class todoMVCTest extends TestBase {
    todoPage TodoPage;
    @BeforeMethod
    void setup() {
        openBrowser("chrome");
        TodoPage = new todoPage();
        TodoPage.open();
    }
    @Test
    void verifyCreatNewTask(){
        TodoPage.verifyAddTask("Task 1\n");
        int itemLeft = TodoPage.getItemLeft();
        Assert.assertEquals(itemLeft , 1);
    }
    @Test
    void  VerifyUserAbleMarkComplete(){
        TodoPage.verifyAddTask("Task 1\n");
        TodoPage.verifyAddTask("Task 2\n");
        TodoPage.verifyAddTask("Task 3\n");
        TodoPage.markCompleted("Task 2");
        TodoPage.clickItem("Completed");
        Assert.assertTrue(TodoPage.isDisplayTask("Task 2"));
    }
    @Test
    void VerifyUserAbleDeleteTodo(){
        TodoPage.verifyAddTask("Task 1\n");
        TodoPage.verifyAddTask("Task 2\n");
        int itemsLeftBefore = TodoPage.getItemLeft();
        TodoPage.verifyDelete("Task 2");
        int itemsLeftAfter = TodoPage.getItemLeft();
        Assert.assertEquals(itemsLeftBefore-itemsLeftAfter , 1);
    }
    @Test
    void  VerifyUserAbleUpdateTodoName(){
        TodoPage.verifyAddTask("Task 1\n");
        TodoPage.verifyAddTask("Task 2\n");
        TodoPage.updateTaskName("Task 1" , "HELLO");
        Assert.assertTrue(TodoPage.isDisplayTask("HELLO"));
    }
    @AfterMethod
    void close(){
        closeBrowser();
    }

}
