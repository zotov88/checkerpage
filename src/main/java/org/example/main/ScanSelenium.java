package org.example.main;

import org.example.constants.NotifyMessage;
import org.example.constants.PageAnalysis;
import org.example.helpers.Logger;
import org.example.helpers.MessagePrint;
import org.example.notifications.SenderNotifications;
import org.example.helpers.Colors;
import org.example.helpers.Sound;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
//        ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
//        this.driver = new ChromeDriver(options);
        this.driver.manage().window().setSize(new Dimension(1, 1));
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
        try {
            if (!url.equals(driver.getCurrentUrl())) {
                if (driver.getCurrentUrl().contains(PageAnalysis.PAGE_AUTHENTICATION)) {
                    authorization();
                } else {
                    driver.get(url);
                    delay();
                }
            }
            WebElement banner = driver.findElement(By.xpath(xpath));
            String receivedText = banner.getText();
            if (receivedText == null || receivedText.isEmpty() || receivedText.contains(text)) {
                MessagePrint.print(NotifyMessage.NO_REVIEW, Colors.YELLOW);
                isSent = false;
            } else {
                if (!isSent) {
                    sender.sendAll();
                    MessagePrint.print(NotifyMessage.MESSAGE_SENT, Colors.GREEN);
                    Logger.logMessage(NotifyMessage.MESSAGE_SENT);
                    new Sound().sentMessage();
                    isSent = true;
                } else {
                    MessagePrint.print(NotifyMessage.REVIEW_AVAILABLE, Colors.CYAN);
                }
            }
        } catch (NoSuchElementException e) {
            Logger.logMessage(NotifyMessage.PAGE_NOT_AVAILABLE);
            MessagePrint.print(NotifyMessage.PAGE_NOT_AVAILABLE, Colors.RED);
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
            Logger.logMessage(NotifyMessage.AUTHORIZATION_SUCCESSFUL);
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
