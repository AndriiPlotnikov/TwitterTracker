package dev.aplotnikov.tracker;

/**
 * Tracker that gathers statistics
 *
 * @param <INPUT> statistics input
 * @param <OUTPUT> statistics output
 */
public interface Tracker<INPUT, OUTPUT> {

    /**
     * Send Statistics to Tracker.
     *
     * Might be not the best name for method
     *
     * @param input statistics
     */
    void sendStatistics(INPUT input);

    /**
     * get gathered statistics
     *
     * @return statistics result
     */
    OUTPUT getStatistics();
}
