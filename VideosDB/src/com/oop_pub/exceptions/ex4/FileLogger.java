package com.oop_pub.exceptions.ex4;

import java.util.EnumSet;

public class FileLogger extends LoggerBase{
    public FileLogger(EnumSet<LogLevel> e) {
        super(e);
    }

    @Override
    protected void writeMessage(String mesaj) {
        System.out.println("[File]" + mesaj);
    }
}
