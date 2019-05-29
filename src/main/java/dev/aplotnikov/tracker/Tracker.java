package dev.aplotnikov.tracker;

public interface Tracker<INPUT, OUTPUT> {

    void sendStatistics(INPUT input);

    OUTPUT getStatistics();
}
