package dev.aplotnikov;

import dev.aplotnikov.listener.FeedListener;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.*;

import javax.annotation.PostConstruct;

/**
 * Starter of the application
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private TwitterStream twitterStream;

    @Autowired
    private FeedListener feedListener;

    @Value("#{'${track}'.split(',')}")
    private String[] track;

    @Value("${twitter.language}")
    private String language;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void test(){
        System.out.println("Starting");
        twitterStream.addListener(feedListener);
        System.out.println("Sampling");
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(track);

        double[][] newYorkLocation = {{-74.93, 40.23},{-73.43, 41.23}}; //40.730610, -73.935242.
        double[][] chicagoLocation = {{-88.22, 41.38},{-87.22, 42.38}}; //41.881832, -87.623177
        double[][] sanFranciscoLocation = {{-122.75,36.8},{-121.75,37.8}}; //37.773972, -122.431297.
        double[][] locations = ArrayUtils.addAll(newYorkLocation, chicagoLocation);
        locations = ArrayUtils.addAll(locations, sanFranciscoLocation);
        filterQuery.locations(locations);
        filterQuery.language(language);

        // filter expects at least one parameter: follow, track, or locations
        twitterStream.filter(filterQuery);
    }
}
