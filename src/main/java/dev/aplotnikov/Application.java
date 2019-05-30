package dev.aplotnikov;

import dev.aplotnikov.configuration.TwitterLocation;
import dev.aplotnikov.configuration.TwitterProperties;
import dev.aplotnikov.listener.WordCountFeedListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import javax.annotation.PostConstruct;

/**
 * Starter of the application
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application {

    @Autowired
    private TwitterStream twitterStream;

    @Autowired
    private WordCountFeedListener feedListener;

    @Autowired
    private TwitterProperties twitterProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void filterFeed() {
        log.debug("Creating filter");
        twitterStream.addListener(feedListener);
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(twitterProperties.getTrack());

        filterQuery.locations(TwitterLocation.getAllLocationsByName(twitterProperties.getLocations()));
        filterQuery.language(twitterProperties.getLanguage());

        log.debug("Parsing feed");

        // filter expects at least one parameter: follow, track, or locations
        twitterStream.filter(filterQuery);
    }
}
