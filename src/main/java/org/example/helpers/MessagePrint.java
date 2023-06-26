package org.example.helpers;

public class MessagePrint {

    private MessagePrint() {}

    public static void print(String message, Colors color) {
        System.out.println(color.getCode() + message + Colors.RESET.getCode());
    }
}
