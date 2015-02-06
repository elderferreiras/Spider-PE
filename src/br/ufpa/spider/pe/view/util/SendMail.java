package br.ufpa.spider.pe.view.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.ufpa.spider.pe.model.set.Email;
import br.ufpa.spider.pe.model.set.dao.EmailDAO;
  
public class SendMail {
    public static boolean sendEmail(String destinatario, String assunto, String conteudo) {

        try {
        	Email email = null;
        	for (Email emailfor : EmailDAO.findAll()) {
        		email = emailfor;
			}
            

            String servidor = email.getServidorEnvio();
            String porta = email.getPorta();
            String usuario = email.getUsuario();
            String senha = email.getSenha();

            Properties props = new Properties();

            if (email.getProtocolo().toLowerCase().equals("smtps")) {
                props.put("mail.transport.protocol", "smtps");
                props.put("mail.smtps.host", servidor);
                props.put("mail.smtps.auth", "true");
            } else {
                props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP  
                props.put("mail.smtp.starttls.enable","true");   
                props.put("mail.smtp.host", servidor); //server SMTP do GMAIL  
                props.put("mail.smtp.auth", "true"); //ativa autenticacao  
                props.put("mail.smtp.user", usuario); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)  
                props.put("mail.debug", "true");  
                props.put("mail.smtp.port", porta); //porta  
                props.put("mail.smtp.socketFactory.port", porta); //mesma porta para o socket  
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
                props.put("mail.smtp.socketFactory.fallback", "false"); 
            }

            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(true);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(assunto);
            message.setContent(conteudo, "text/plain");

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
      
            transport.connect(servidor, Integer.parseInt(porta), usuario, senha);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

            return true;
        } catch (Exception ex) 
        {         
        	ex.printStackTrace();
            return false;
        }
    }
}