package dev.aplotnikov.configuration;

import dev.aplotnikov.listener.WordCountFeedListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Configuration file for {@link Twitter} related information
 */
@org.springframework.context.annotation.Configuration
@Slf4j
public class TwitterConfiguration {

    /**
     * Bean for {@link Twitter} creation
     *
     * @param configurationBuilder configuration builder
     * @return Bean for {@link Twitter}
     */
    @Bean
    public Twitter twitter(ConfigurationBuilder configurationBuilder) {
        Configuration configuration = configurationBuilder.build();
        TwitterFactory twitterFactory = new TwitterFactory(configuration);
        return twitterFactory.getInstance();
    }

    /**
     * Bean for {@link TwitterStream}
     *
     * @param configurationBuilder configuration builder
     * @param twitterProperties    properties related to twitter
     * @param feedListener listener to feed
     * @return Bean for {@link TwitterStream}
     */
    @Bean
    public TwitterStream twitterStream(ConfigurationBuilder configurationBuilder, TwitterProperties twitterProperties, WordCountFeedListener feedListener) {
        Configuration configuration = configurationBuilder.build();
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(configuration);
        TwitterStream instance = twitterStreamFactory.getInstance();
        instance.addListener(feedListener);
        startListeningToFeed(instance, twitterProperties);
        return instance;
    }

    private void startListeningToFeed(TwitterStream twitterStream, TwitterProperties twitterProperties) {
        log.debug("Creating filter");
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(twitterProperties.getTrack());

        filterQuery.locations(TwitterLocation.getAllLocationsByName(twitterProperties.getLocations()));
        filterQuery.language(twitterProperties.getLanguage());

        log.debug("Parsing feed");

        // filter expects at least one parameter: follow, track, or locations;
        // problem with filter method is that it won't save filter query and then run it, instead it immediately runs so we have to start it here.
        // TODO figure out better way to start it
        twitterStream.filter(filterQuery);
    }

    /**
     * Create configuration for connection bean
     *
     * @param twitterProperties properties related to twitter
     * @return configuration for connection
     */
    @Bean
    @Scope("prototype")
    public ConfigurationBuilder configurationBuilder(TwitterProperties twitterProperties) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder.setDebugEnabled(true).
                setOAuthAccessToken(twitterProperties.getOAuthAccessToken()).
                setOAuthAccessTokenSecret(twitterProperties.getOAuthAccessTokenSecret()).
                setOAuthConsumerKey(twitterProperties.getOAuthConsumerKey()).
                setOAuthConsumerSecret(twitterProperties.getOAuthConsumerSecret());
        return configurationBuilder;
    }
}
