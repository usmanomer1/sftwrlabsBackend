/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.mail.MessagingException
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendEmailWithTemplate(String var1, String var2, String var3) throws MessagingException;

    public String generateWelcomeEmailContent(String var1);

    public String generatePromotionalEmailContent(String var1, String var2, String var3);
}
