package dev.aplotnikov.tracker;

import org.springframework.stereotype.Component;

/**
 * Tracker that determines number of even/odd tweets and prints statistics for them
 */
@Component
public class EvenOddStatisticsTracker implements Tracker<Integer, String> {

    private long oddTweets;
    private long evenTweets;

    // we use synchronized to stop receiving data while we outputting information to avoid weird scenarios
    // since odd/even are bound numbers
    @Override
    public synchronized void sendStatistics(Integer numberOfWords) {
        if (numberOfWords % 2 == 0) {
            evenTweets++;
        } else {
            oddTweets++;
        }
    }

    @Override
    public synchronized String getStatistics() {
        long total = oddTweets + evenTweets;
        int evenTweetsPercentage = toPercentage(evenTweets, total);
        int oddTweetsPercentage = toPercentage(oddTweets, total);

        return getStatisticsString(total, evenTweetsPercentage, oddTweetsPercentage);
    }

    private String getStatisticsString(long total, int evenTweetsPercentage, int oddTweetsPercentage) {
        StringBuilder builder = new StringBuilder();
        builder.append("Total tweets: ");
        builder.append(total);
        builder.append("; ");
//        builder.append("Even tweets: ");
//        builder.append(evenTweetsPercentage);
//        builder.append("%; ");
        builder.append("Odd tweets: ");
        builder.append(oddTweetsPercentage);
        builder.append("%");

        return builder.toString();
    }

    private int toPercentage(long part, long total) {
        return (int) (part * 100.0 / total + 0.5); // 0.5 to round up
    }

}
