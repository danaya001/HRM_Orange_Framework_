package ui_automation.utilities;

import org.openqa.selenium.WebDriver;

public class Driver {
    /* private constructor, no objects can be created outside of this class */
    private Driver(){

    }

    /* private static reference variable to the class */
    private static Driver instance = new Driver();

    /* create a getter method with return type of the class => encapsulation */
    public static Driver getInstance(){
        return instance;
    }

    /**
     * Thread Local is responsible to keep track of the webdrivers that has been created by different threads
     * thread local driver object for webdriver
     */
    ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();

    /* getDriver() is the instance method of our Driver class */
    public WebDriver getDriver(){
        return driver.get();
    }

    /* setDriver() is the instance method of our Driver class */
    public  void setDriver(WebDriver driverParameter){

        driver.set(driverParameter);
    }

    /* removeDriver() is the instance method of our Driver class */
    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }
}