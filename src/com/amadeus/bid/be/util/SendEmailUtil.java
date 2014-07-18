package com.amadeus.bid.be.util;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * this class is used to send a email
 * @author Sumit
 *
 */
public class SendEmailUtil {

    private List<String> to;
    private String from;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String message;
    private String messageType;
    private String host;

    public SendEmailUtil(){
        this.to = new ArrayList<String>();
        this.bcc = new ArrayList<String>();
        this.cc = new ArrayList<String>();
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public List<String> getTo() {
        return to;
    }
    public void setTo(List<String> to) {
        this.to = to;
    }
    public void setTo(String to) {
        if (this.to == null){
            this.to = new ArrayList<String>();
        }

        this.to.add(to);
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public List<String> getCc() {
        return cc;
    }
    public void setCc(List<String> cc) {
        this.cc = cc;
    }
    public void setCc(String cc) {
        if (this.cc == null){
            this.cc = new ArrayList<String>();
        }

        this.cc.add(cc);
    }
    public List<String> getBcc() {
        return bcc;
    }
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }
    public void setBcc(String bcc) {
        if (this.cc == null){
            this.bcc = new ArrayList<String>();
        }

        this.bcc.add(bcc);
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean sendMail(){

        if (this.getTo() != null && this.getFrom() != null && this.getTo().size() > 0){

            Properties props = new Properties();
            Session session = Session.getInstance(props, null);

            InternetAddress[] toIds = new InternetAddress[this.getTo().size()];
            for (int i=0;i<this.getTo().size();i++){
                try{
                    InternetAddress internetAddress = new InternetAddress(this.getTo().get(i));
                    toIds[i] = internetAddress;
                } catch(AddressException e) { /* no action in case of error */ }
            }

            InternetAddress[] ccIds = new InternetAddress[this.getCc().size()];
            for (int i=0;i<this.getCc().size();i++){
                try{
                    InternetAddress internetAddress = new InternetAddress(this.getCc().get(i));
                    ccIds[i] = internetAddress;
                } catch(AddressException e) { /* no action in case of error */ }
            }

            InternetAddress[] bccIds = new InternetAddress[this.getBcc().size()];
            for (int i=0;i<this.getBcc().size();i++){
                try{
                    InternetAddress internetAddress = new InternetAddress(this.getBcc().get(i));
                    bccIds[i] = internetAddress;
                } catch(AddressException e) { /* no action in case of error */ }
            }

            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(this.getFrom(), "ThereUGo Admin"));
                msg.setSubject(this.getSubject());
                msg.setContent(this.getMessage(),this.getMessageType());

                /* adding recipients [START] */
                msg.addRecipients(Message.RecipientType.TO, toIds);
                if (bccIds.length > 0) { msg.addRecipients(Message.RecipientType.BCC, bccIds); }
                if (ccIds.length > 0) { msg.addRecipients(Message.RecipientType.CC, ccIds); }
                /* adding recipients [END] */

                Transport.send(msg);

            } catch (AddressException e) {
                return false;
            } catch (MessagingException e) {
                return false;
            } catch(UnsupportedEncodingException e){
                return false;
            }
        }else{
            return false;
        }

        return true;
    }
}