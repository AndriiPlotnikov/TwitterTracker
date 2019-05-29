package dev.aplotnikov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Configuration file for {@link Twitter} related information
 */
@org.springframework.context.annotation.Configuration
public class TwitterConfiguration {

    @Bean
    public Twitter twitter(ConfigurationBuilder configurationBuilder, TwitterProperties twitterProperties) {
        Configuration configuration = configurationBuilder.build();
        TwitterFactory twitterFactory = new TwitterFactory(configuration);
        return twitterFactory.getInstance();
    }

    @Bean
    public TwitterStream twitterStream(ConfigurationBuilder configurationBuilder, TwitterProperties twitterProperties) {
        Configuration configuration = configurationBuilder.build();
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(configuration);
        return twitterStreamFactory.getInstance();
    }

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
