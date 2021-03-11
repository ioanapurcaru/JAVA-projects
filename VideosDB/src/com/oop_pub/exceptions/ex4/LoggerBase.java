package com.oop_pub.exceptions.ex4;

import java.util.EnumSet;

public abstract class LoggerBase {

    private EnumSet<LogLevel> level;
    private LoggerBase next;

    public LoggerBase(EnumSet<LogLevel> e){
       level = e;
    }

    public LoggerBase setNext(LoggerBase next) {
        this.next = next;
        return this.next;
    }

    protected void writeMessage(String mesaj) {
        System.out.println(mesaj);
    }

    public void message(String mesaj, LogLevel l) {
        if (level.contains(l)) {
            writeMessage(mesaj);
        }
        if (this.next != null) {
            next.message(mesaj, l);
        }
    }
}
