package com.oop_pub.exceptions.ex4;

import java.util.EnumSet;

public class ConsoleLogger extends LoggerBase {
    public ConsoleLogger(EnumSet<LogLevel> e) {
        super(e);
    }

    @Override
    protected void writeMessage(String mesaj) {
        System.out.println("[Console] " + mesaj);
    }
}
