package dev.aplotnikov.tracker;

import org.springframework.stereotype.Component;

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
        StringBuilder builder = new StringBuilder();

        // TODO currently not synchronous, so possible difference in stats with odd/even tweets
        long total = oddTweets + evenTweets;
        int evenTweets = (int) (this.evenTweets * 100.0 / total + 0.5);
        int oddTweets = (int) (this.oddTweets * 100.0 / total + 0.5);


        builder.append("Total tweets: ");
        builder.append(total);
        builder.append(" even tweets: ");
        builder.append(evenTweets);
        builder.append("%;");
        builder.append(" odd tweets: ");
        builder.append(oddTweets);
        builder.append("%");

        return builder.toString();
    }

}
