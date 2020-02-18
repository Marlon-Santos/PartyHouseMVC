package br.com.gft.mvc.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendEmail {
    private JavaMailSender mailSender;
    private String emailAddress;
    private String message;
    private String subject;

    public SendEmail(String emailAddress, String subject, String message, JavaMailSender mailSender) {
        this.emailAddress = emailAddress;
        this.message = message;
        this.subject = subject;
        this.mailSender = mailSender;
        emailMessage(emailAddress, subject, message, mailSender);
    }

    public String emailMessage(String emailAddress, String subject, String message, JavaMailSender mailSender) {

        SimpleMailMessage emailBody = new SimpleMailMessage();
        emailBody.setTo(emailAddress);
        emailBody.setSubject(subject);
        emailBody.setText(message);
        
        try {
            mailSender.send(emailBody);
            return "email enviado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email";
        }

    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
