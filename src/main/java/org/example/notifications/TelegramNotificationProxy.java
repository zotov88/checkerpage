package org.example.notifications;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public final class TelegramNotificationProxy implements NotificationProxy {

    private final TelegramBot bot;
    private final long chatID;

    public TelegramNotificationProxy(String botToken, long chatID) {
        this.bot = new TelegramBot(botToken);
        this.chatID = chatID;
    }

    @Override
    public void send(String message) {
        bot.execute(new SendMessage(chatID, message));
    }
}
