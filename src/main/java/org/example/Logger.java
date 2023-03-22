package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

    private static final File fileLog = new File("log.txt");

    private Logger() {
    }

    public static void logAuthorization() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog, true))) {
            bw.write("Authorization: " + new Date() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logSentMessage(long num) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog, true))) {
            bw.write("Sent message on check " + num + " " + new Date() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
