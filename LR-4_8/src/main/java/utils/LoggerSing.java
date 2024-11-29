package utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;

public class LoggerSing {
    private static LoggerSing instance;
    private Logger logger;
    private FileHandler fileHandler;

    private LoggerSing() {
        try {
            // Ініціалізація логера
            logger = Logger.getLogger(LoggerSing.class.getName());
            logger.setUseParentHandlers(false); // Вимкнути логування в консоль за замовчуванням

            // Створення FileHandler для збереження логів у файл
            fileHandler = new FileHandler("application.log", true); // Додати "true" для дописування
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Додавання рівнів логування
            logger.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized LoggerSing getInstance() {
        if (instance == null) {
            instance = new LoggerSing();
        }
        return instance;
    }

    // Логування основних дій
    public void log(String message) {
        logger.info(message);
    }

    // Логування попереджень
    public void logWarning(String message) {
        logger.warning(message);
    }

    // Логування помилок
    public void logError(String message) {
        logger.severe(message);
        sendErrorEmail(message);
    }

    // Логування детальних повідомлень (debug)
    public void logDebug(String message) {
        logger.fine(message);
    }

    // Логування дуже детальних повідомлень (trace)
    public void logDetailed(String message) {
        logger.finest(message);
    }

    // Надсилання критичних помилок на email
    public void sendErrorEmail(String errorMessage) {
        String host = "smtp.gmail.com"; // SMTP сервер
        String port = "587"; // Порт сервера
        String username = "danya150309@gmail.com"; // Ваш email
        String password = "1123581321Da."; // Ваш пароль

        String toEmail = "danylo.krainii.oi.2023@lpnu.ua"; // Email отримувача

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Створення повідомлення
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Critical Error in Application");
            message.setText("A critical error occurred: \n\n" + errorMessage);

            // Надсилання повідомлення
            Transport.send(message);
            logger.info("Error email sent to " + toEmail);
        } catch (MessagingException e) {
            logger.warning("Failed to send email: " + e.getMessage());
        }
    }
}
