package org.example.notifications;

import java.util.List;

public final class SenderNotifications {

    private final List<SendAble> notifiers;
    private final String message;

    public SenderNotifications(List<SendAble> notifiers, String message) {
        this.notifiers = notifiers;
        this.message = message;
    }

    public void sendAll() {
        for (SendAble notifier : notifiers) {
            notifier.send(message);
        }
    }
}
