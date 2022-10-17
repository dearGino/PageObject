package main.java.common;

import java.time.Duration;

public class InitManager {

 
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();

        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
    }


    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
