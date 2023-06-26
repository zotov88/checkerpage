package org.example.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

    private static final File fileLog = new File("log.txt");

    private Logger() {
    }

    public static void logMessage(final String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLog, true))) {
            bw.write("\n" + new Date() + ": " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
