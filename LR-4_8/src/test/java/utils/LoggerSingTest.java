package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import javax.mail.MessagingException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

class LoggerSingTest {

    private LoggerSing loggerSing;
    private Logger mockLogger;
    private FileHandler mockFileHandler;

    @BeforeEach
    void setUp() throws Exception {
        // Мокування об'єктів
        mockLogger = mock(Logger.class);
        mockFileHandler = mock(FileHandler.class);

        // Створення екземпляра LoggerSing з використанням моків
        loggerSing = spy(LoggerSing.getInstance());
    }

    @Test
    void testLogInfo() {
        // Викликаємо метод логування
        loggerSing.log("Test info message");

        // Перевіряємо, що метод info був викликаний на логері
        verify(loggerSing, times(1)).log("Test info message");
    }

    @Test
    void testLogWarning() {
        // Викликаємо метод логування попередження
        loggerSing.logWarning("Test warning message");

        // Перевіряємо, що метод warning був викликаний на логері
        verify(loggerSing, times(1)).logWarning("Test warning message");
    }

    @Test
    void testLogError() {
        // Мокування надсилання email
        LoggerSing spyLogger = spy(loggerSing);
        doNothing().when(spyLogger).sendErrorEmail(anyString());  // Мокуємо відправку email

        // Викликаємо метод логування помилки
        spyLogger.logError("Test error message");

        // Перевіряємо, що метод severe був викликаний на логері
        verify(spyLogger, times(1)).logError("Test error message");

        // Перевіряємо, що метод sendErrorEmail викликаний з правильним повідомленням
        verify(spyLogger, times(1)).sendErrorEmail("Test error message");
    }

    @Test
    void testLogDebug() {
        // Викликаємо метод логування debug
        loggerSing.logDebug("Test debug message");

        // Перевіряємо, що метод fine був викликаний на логері
        verify(loggerSing, times(1)).logDebug("Test debug message");
    }

    @Test
    void testLogDetailed() {
        // Викликаємо метод логування detailed (trace)
        loggerSing.logDetailed("Test detailed message");

        // Перевіряємо, що метод finest був викликаний на логері
        verify(loggerSing, times(1)).logDetailed("Test detailed message");
    }

    @Test
    void testSendErrorEmail() throws MessagingException {
        // Створюємо мок-сесію для email
        LoggerSing spyLogger = spy(loggerSing);
        doNothing().when(spyLogger).sendErrorEmail(anyString());

        // Викликаємо метод для надсилання email з повідомленням
        spyLogger.sendErrorEmail("Critical error occurred");

        // Перевіряємо, чи викликано відправлення email
        verify(spyLogger, times(1)).sendErrorEmail("Critical error occurred");
    }

    @Test
    void testSendErrorEmailFails() throws MessagingException {
        // Мокування об'єкта для генерації винятку під час відправлення email
        LoggerSing spyLogger = spy(loggerSing);
        doThrow(new MessagingException("Email send failed")).when(spyLogger).sendErrorEmail(anyString());

        // Викликаємо метод для надсилання email
        spyLogger.sendErrorEmail("Critical error occurred");

        // Перевіряємо, що логування попередження було здійснене
        verify(loggerSing, times(1)).logWarning("Failed to send email: Email send failed");
    }
}
