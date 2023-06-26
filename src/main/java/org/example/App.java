package org.example;

import org.example.main.ScanSelenium;
import org.example.notifications.SenderNotifications;
import org.example.notifications.NotificationProxy;
import org.example.notifications.TelegramNotificationProxy;

import java.util.List;

import static org.example.constants.NotifyMessage.MESSAGE_TO_SEND;
import static org.example.constants.NotifySetting.BOT_TOKEN;
import static org.example.constants.NotifySetting.CHAT_ID;
import static org.example.constants.PageAnalysis.*;

public class App {

    public static void main(String[] args) {

        // list of notification methods
        List<NotificationProxy> list = List.of
                (
                        new TelegramNotificationProxy(BOT_TOKEN, CHAT_ID)
                );

        ScanSelenium scanSelenium = new ScanSelenium
                (
                        USERNAME, PASSWORD, URL, USERNAME_ID, PASSWORD_ID, BUTTON_TAG_NAME,
                        XPATH, TEXT, DELAY, new SenderNotifications(list, MESSAGE_TO_SEND)
                );

        scanSelenium.authorization();

        while (true) {
            scanSelenium.checkProcessing();
        }
    }
}

