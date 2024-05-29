package org.example.util.logger;

public class LogLine {
    private String text;

    public LogLine(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}