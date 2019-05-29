package dev.aplotnikov.listener;

import dev.aplotnikov.tracker.EvenOddStatisticsTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

@Component
@Slf4j
public class FeedListener extends StatusAdapter {

    private static final String WHITESPACE_REGEX = "\\s+";

    @Autowired
    private EvenOddStatisticsTracker statisticsTracker;

    @Override
    public void onStatus(Status status) {
        log.debug("status: " + status);

        int wordCount = status.getText().split(WHITESPACE_REGEX).length;
        statisticsTracker.sendStatistics(wordCount);
    }

    @Override
    public void onTrackLimitationNotice(int numberOfQueuedStatuses) {
        // It's not really interesting, since we won't do anything with it
        // but in case we make filter too broad and leave app running for long time, this will help explain things.
        // notice: this is number representation of StallWarning

        log.warn("Tweets in queue " + numberOfQueuedStatuses);
    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        // It's not really interesting, since we won't do anything with it
        // but in case we make filter too broad and leave app running for long time, this will help explain things.
        log.warn("WARNING! " + stallWarning);
    }

    @Override
    public void onException(Exception e) {
        log.error("ERROR " + e);
    }
}
