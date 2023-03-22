package org.example;

import org.example.notifications.SenderNotifications;
import org.example.notifications.GMailSender;
import org.example.notifications.SendAble;
import org.example.notifications.TelegramSender;

import java.util.List;

public class App {

    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String URL = "url";
    public final static String USERNAME_ID = "id";
    public final static String PASSWORD_ID = "id";
    public final static String BUTTON_TAG_NAME = "tag_name";
    public final static String XPATH = "xpath";
    public final static String TEXT = "text";
    public final static int DELAY = 20;
    public final static String BOT_TOKEN = "bot_token";
    private final static long CHAT_ID = 123456789L;
    private final static String MESSAGE = "notification message";
    private final static String FROM = "______@gmail.com";
    private final static String TO = "______@gmail.com";
    private final static String HOST = "smtp.gmail.com";
    private final static String SMTP_PORT = "465";
    private final static String PASSWORD_MAIL = "account password";

    public static void main(String[] args) {

        // list of notification methods
        List<SendAble> list = List.of(
                new TelegramSender(BOT_TOKEN, CHAT_ID),
                new GMailSender(FROM, TO, HOST, SMTP_PORT, PASSWORD_MAIL)
        );

        ScanSelenium scanSelenium = new ScanSelenium(
                USERNAME, PASSWORD, URL, USERNAME_ID, PASSWORD_ID, BUTTON_TAG_NAME,
                XPATH, TEXT, DELAY, new SenderNotifications(list, MESSAGE)
        );

        scanSelenium.authorization();

        while (true) {
            scanSelenium.checkProcessing();
        }
    }
}

