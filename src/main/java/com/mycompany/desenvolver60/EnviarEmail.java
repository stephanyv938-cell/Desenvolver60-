/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desenvolver60;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EnviarEmail {

    public EnviarEmail() {
    }
     private static final String EMAIL_REMETENTE =
            System.getenv("DESENVOLVER60_EMAIL");

    private static final String SENHA_APP =
            System.getenv("DESENVOLVER60_EMAIL_PASSWORD");

    public static void enviarCodigo(
            String destinatario,
            String codigo) throws MessagingException {

        if (EMAIL_REMETENTE == null || SENHA_APP == null) {
            throw new IllegalStateException(
                    "As credenciais de e-mail não foram configuradas."
            );
        }

        Properties propriedades = new Properties();

        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.starttls.enable", "true");
        propriedades.put("mail.smtp.host", "smtp.gmail.com");
        propriedades.put("mail.smtp.port", "587");

        Session sessao = Session.getInstance(
                propriedades,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                EMAIL_REMETENTE,
                                SENHA_APP
                        );
                    }
                }
        );

        Message mensagem = new MimeMessage(sessao);

        mensagem.setFrom(
                new InternetAddress(EMAIL_REMETENTE)
        );

        mensagem.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(destinatario)
        );

        mensagem.setSubject(
                "Código de verificação - Desenvolver 60+"
        );

        mensagem.setText(
                "Seu código de verificação é: " + codigo
                + "\n\nSe você não solicitou este cadastro, "
                + "ignore esta mensagem."
        );

        Transport.send(mensagem);
    }
}
