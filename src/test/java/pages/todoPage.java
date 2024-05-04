package pages;


import common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static common.Browser.*;

public class todoPage{
    public void open(){
        Browser.visit("https://todomvc.com/examples/vue/dist/#/");
    }
    public void verifyAddTask(String name){
        Browser.fill(By.xpath("/html/body/section/header/input") , name);
    }
    public int getItemLeft(){
        return Integer.parseInt(Browser.getText(By.xpath("/html/body/section/footer/span/strong")));
    }
    public void clickItem(String nameStatus){
        click(By.xpath(String.format("//a[contains(text(),'%s')]",nameStatus)));
    }
    public void markCompleted(String task){
        click(By.xpath(String.format("//label[contains(text(),'%s')]/../input" , task)));
    }
    public boolean isDisplayTask(String task){
        return isDisplay(By.xpath(String.format("//label[contains(text(),'%s')]", task)));
    }
    public void verifyDelete(String task){
        hover(By.xpath(String.format("//label[contains(text(),'%s')]", task)));
        click(By.xpath(String.format("//label[contains(text(),'%s')]/../button" , task)));
    }
    public void updateTaskName(String oldName , String newName){
        doubleClick(By.xpath(String.format("//label[contains(text(),'%s')]", oldName)));
        WebElement getTask = getElement(By.xpath(String.format("//label[contains(text(),'%s')]", oldName)));
        WebElement editTaskName = getTask.findElement(By.xpath("//input[@id='edit-todo-input']"));
        executeScript("arguments[0].value = ''" , editTaskName);
        editTaskName.sendKeys(newName + Keys.ENTER);
    }
}
