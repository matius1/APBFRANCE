package com.mateuszskocz.server.APBFRANCE.emailSender;

/**
 * Created by Mati on 10.07.2017.
 */
public interface EmailSenderInterface {

    void sendEmail(String to, String subject, String content);

}
