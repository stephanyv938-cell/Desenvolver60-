
package com.mycompany.desenvolver60;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.Properties;

public class EnviarEmail {

    public EnviarEmail() {
    }
    private static final String EMAIL_REMETENTE
            = System.getenv("DESENVOLVER60_EMAIL");

    private static final String SENHA_APP
            = System.getenv("DESENVOLVER60_EMAIL_PASSWORD");

    public static void enviarCodigo(
            String destinatario,
            String codigo) throws EmailException{

        
        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);

        email.setAuthenticator(
                new DefaultAuthenticator(EMAIL_REMETENTE, SENHA_APP)
        );

        email.setSSLOnConnect(false);
        email.setStartTLSEnabled(true);
        email.setStartTLSRequired(true);

        email.setFrom(EMAIL_REMETENTE, "Desenvolver 60+");

        email.setSubject("Código de verificação - Desenvolver 60+");

        email.setMsg(
                "Olá!\n\n" +
                "Seu código de verificação é: " + codigo +
                "\n\nDigite este código no aplicativo para confirmar seu cadastro."
        );
        
        email.addTo(destinatario);
        email.send();
       
        }
    public static void main(String[] args) {
        System.out.println("EMAIL carregado: " + EMAIL_REMETENTE);
        System.out.println("SENHA existe: " + (SENHA_APP != null));
        System.out.println("Tamanho senha: " +
        (SENHA_APP == null ? 0 : SENHA_APP.length()));
    }
    

}
