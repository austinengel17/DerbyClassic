package util;

import java.awt.*;
import java.io.InputStream;
import java.util.Properties;

public class MessageUtil {

    private static MessageUtil messageUtil;
    private static Properties messages;

    private MessageUtil() {
        messages = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("messages.properties")){
            messages.load(input);
        } catch (Exception e) {
            System.err.println("Could not load message keys");
            e.printStackTrace();
        }
    }

    public static MessageUtil getMessageUtil(){
        if(messageUtil == null) {
            messageUtil = new MessageUtil();
        }
        return messageUtil;
    }

    public String getMessage(String key) {
        return messages.getProperty(key, "Cannot find message");
    }

    public int getMessageLength(String message, Graphics2D g2) {
        return g2.getFontMetrics().stringWidth(message);
    }

    public int getMessageHeight(Graphics2D g2) {
        return g2.getFontMetrics().getHeight();
    }
}
