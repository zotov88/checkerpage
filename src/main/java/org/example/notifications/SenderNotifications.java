package org.example.notifications;

import java.util.List;

public final class SenderNotifications {

    private final List<NotificationProxy> notifiers;
    private final String message;

    public SenderNotifications(List<NotificationProxy> notifiers, String message) {
        this.notifiers = notifiers;
        this.message = message;
    }

    public void sendAll() {
        for (NotificationProxy notifier : notifiers) {
            notifier.send(message);
        }
    }
}
