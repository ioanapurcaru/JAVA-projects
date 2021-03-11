package com.oop_pub.exceptions.ex4;

import java.util.EnumSet;

public class EmailLogger extends LoggerBase {
    public EmailLogger(EnumSet<LogLevel> e) {
        super(e);
    }

    @Override
    protected void writeMessage(String mesaj) {
        System.out.println("[Email]" + mesaj);
    }
}
