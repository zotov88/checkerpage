package org.example.notifications;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class GMailNotificationProxy implements NotificationProxy {

    private final String from;
    private final String to;
    private final String host;
    private final String smtpPort;
    private final String password;
    private final Properties properties = new Properties();

    public GMailNotificationProxy(String from, String to, String host, String smtpPort, String password) {
        this.from = from;
        this.to = to;
        this.host = host;
        this.smtpPort = smtpPort;
        this.password = password;
        setProperties();
    }

    private void setProperties() {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    @Override
    public void send(String message) {
        Session session = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                }
        );
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject("CRP");
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
