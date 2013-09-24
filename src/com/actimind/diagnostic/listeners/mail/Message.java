package com.actimind.diagnostic.listeners.mail;

public class Message {

    private String subject = "";
    private StringBuffer body = new StringBuffer();


    public Message() {
    }

    public Message subject(String s) {
        subject = s;
        return this;
    }

    public Message add(String s) {
        body.append(s);
        return this;
    }

    public Message ln() {
        body.append("\n");
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body.toString();
    }
}
