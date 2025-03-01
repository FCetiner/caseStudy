package utils.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.driverClient.DriverClient;

public class JSUtils {


    //This method will take two parameter: WebElement, and WebDriver
    //When you pass the element, JS will click on that element
    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) DriverClient.getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    //to get the page title with JS
    public static String getTitleByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) DriverClient.getDriver());
        String title = jsexecutor.executeScript("return document.title;").toString();
        return title;
    }

  public static void scroolToLeft() {
      JavascriptExecutor js = (JavascriptExecutor) DriverClient.getDriver();
      Long currentScrollPosition = (Long) js.executeScript("return window.scrollX");
      System.out.println("currentScrollPosition = " + currentScrollPosition);
      js.executeScript("window.scrollTo(" + (currentScrollPosition - 700) + ", 0)");
      try {
          Thread.sleep(2000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }




    //Scroll into view with JS. THIS IS VERY USEFULL
    public static void scrollIntoVIewJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) DriverClient.getDriver());
        jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    //    jsexecutor.executeScript("window.scrollBy(0,250)", "");
    }

    public static void changeColor(String color, WebElement element) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) DriverClient.getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Flashing teh background color
    public static void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundcolor");
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0", element);
            changeColor(bgColor, element);
        }
    }
    //this willg enerate an alert when needed
    public static void generateAlert(String message) throws InterruptedException {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) DriverClient.getDriver());
        javascriptExecutor.executeScript("alert('" + message + "')");
        Thread.sleep(3000);
    }

    /*
     * executes the given JavaScript command on given web element
     */
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverClient.getDriver();
        jse.executeScript(command, element);
    }
    /*
     * executes the given JavaScript command on given web element
     */
    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverClient.getDriver();
        jse.executeScript(command);
    }

    public static void scrollIntoView(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) DriverClient.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()",element);
        jse.executeScript("arguments[0].click();", element);
    }

}
