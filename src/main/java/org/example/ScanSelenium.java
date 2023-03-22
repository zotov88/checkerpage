package org.example;

import org.example.notifications.SenderNotifications;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScanSelenium {

    private final FirefoxDriver driver;
    private final String xpath;
    private final String username;
    private final String password;
    private final String url;
    private final String usernameID;
    private final String passwordID;
    private final String logButtonTagName;
    private final int delaySeconds;
    private long countOfCheck = 1;
    private final String text;
    private boolean isSent = false;
    private final SenderNotifications sender;

    public ScanSelenium(final String username, final String password, final String url,
                        final String usernameID, final String passwordID, final String logButtonTagName,
                        final String xpath, final String text, final int delaySeconds, final SenderNotifications sender) {
        this.driver = new FirefoxDriver();
        this.url = url;
        this.usernameID = usernameID;
        this.passwordID = passwordID;
        this.logButtonTagName = logButtonTagName;
        this.xpath = xpath;
        this.username = username;
        this.password = password;
        this.text = text;
        this.delaySeconds = delaySeconds;
        this.sender = sender;
    }

    public void checkProcessing() {
        try {
            System.out.print("Check #" + countOfCheck++ + ": ");
            checkBanner();
        } catch (NoSuchElementException e) {
            authorization();
        }
        driver.navigate().refresh();
        delay();
    }

    private void checkBanner() {
        if (!url.equals(driver.getCurrentUrl())) {
            driver.get(url);
            delay();
        }
        WebElement banner = driver.findElement(By.xpath(xpath));
        if (banner.getText().contains(text)) {
            System.out.println(Colors.YELLOW.getCode() + "No review" + Colors.RESET.getCode());
            isSent = false;
        } else {
            if (!isSent) {
                sender.sendAll();
                System.out.println(Colors.GREEN.getCode() + "Message sent" + Colors.RESET.getCode());
                Logger.logSentMessage(countOfCheck);
                isSent = true;
            } else {
                System.out.println(Colors.RED.getCode() + "Review available. Message was sent" + Colors.RESET.getCode());
            }
        }
    }

    public void authorization() {
        try {
            driver.get(url);
            WebElement usernameWeb = driver.findElement(By.id(usernameID));
            WebElement passwordWeb = driver.findElement(By.id(passwordID));
            WebElement loginButtonWeb = driver.findElement(By.tagName(logButtonTagName));
            usernameWeb.sendKeys(username);
            passwordWeb.sendKeys(password);
            loginButtonWeb.click();
            Logger.logAuthorization();
        } catch (Exception e) {
            e.printStackTrace();
        }
        delay();
    }

    private void delay() {
        try {
            Thread.sleep(delaySeconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
