package org.example.util.logger;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonLog {
    private static SingletonLog instance;
    private static Lock lock = new ReentrantLock();
    private ArrayList<LogLine> logLines;

    private SingletonLog() {
        logLines = new ArrayList<>();
    }

    public static SingletonLog getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SingletonLog();
                }
            }
        }
        return instance;
    }

    public void addLog(String text) {
        LogLine log = new LogLine(text);
        logLines.add(log);
        System.out.println(text);
    }

    public ArrayList<LogLine> getLogLines() {
        return logLines;
    }

    public void log() {
        System.out.println("This is singleton log");
    }
}