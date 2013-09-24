package com.actimind.diagnostic.listeners;

import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.listeners.mail.Message;
import com.actimind.diagnostic.stats.ParamsAware;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Sends mail when stat's status was changed.
 * Uses the following parameters:
 * <list>
 *  <item>*server* - smtp server address
 *  <item>*from* - sender e-mail address
 *  <item>*to* - single receiver e-mail address
 *  <item>*login* - smtp account login
 *  <item>*password* - smtp account password
 *  <item>*port* - smtp server port
 *  <item>*subject.error* - a string to be inserted in subject in case of error
 *  <item>*subject.normal* - a string to be inserted in subject in case of normal state
 * </list>
 *
 * Fails if any parameter is absent or specified incorrectly.
 */
public class Mailer implements StatProcessorListener, ParamsAware {

    private String receiver;
    private String smtpServer;
    private String sender;
    private String uname;
    private String pwd;
    private Map<Boolean, String> subjPrefixes = new HashMap<Boolean, String>();
    private int port;

    public void setParams(Map<String, String> global) throws Exception {

        smtpServer = global.get("server");
        sender = global.get("from");
        receiver = global.get("to");
        uname = global.get("login");
        pwd = global.get("password");
        port = Integer.parseInt(global.get("port"));
        subjPrefixes.put(true, global.get("subject.error"));
        subjPrefixes.put(false, global.get("subject.normal"));
    }

    private void sendMail(Message msg, boolean urgent) {
        try {
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName(smtpServer);
            mail.setSmtpPort(port);
            mail.setFrom(sender);
            mail.addTo(receiver);
            mail.setAuthentication(uname, pwd);
            mail.setSubject(subjPrefixes.get(urgent) + msg.getSubject());
            mail.setMsg(msg.getBody());
            mail.send();
        }
        catch (Exception e) {
            Logger.getLogger(getClass()).error("Cannot send e-mail", e);
        }

    }

    public void onNewStatCollected(Stat stat) {
        //does nothing
    }

    public void onStatStateChanged(Stat stat) {
        if (stat.isNormalState())
            sendMail(normalMessage(stat), false);
        else
            sendMail(errorMessage(stat), true);
    }

    public void onFirstStat(Stat stat) {
        if (!stat.isNormalState())
            onStatStateChanged(stat);
    }

    public void onStatValueChanged(Stat stat) {
        
    }

    private Message normalMessage(Stat stat) {
        Message msg = new Message();
        String subject = stat.getName() + ": now is in normal state";
        if (stat.getStateDescription() != null) subject += " (" + stat.getStateDescription() + ")";
        msg.subject(subject);
        addNameValue(stat, msg);
        return msg;
    }

    private void addNameValue(Stat stat, Message msg) {
        msg.add(stat.getName()).add(": ").add(stat.getStoredValueAsString()).ln().ln();
    }

    private Message errorMessage(Stat stat) {
        Message msg = new Message();
        String subject = stat.getName() + ": validation failed";
        if (stat.getStateDescription() != null) subject += " (" + stat.getStateDescription() + ")";
        msg.subject(subject);
        addNameValue(stat, msg);
        msg.add("Validation failed: ").add(stat.getStateDescription());
        return msg;
    }

}
