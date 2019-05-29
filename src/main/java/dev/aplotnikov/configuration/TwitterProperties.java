package dev.aplotnikov.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties related to {@link twitter4j.Twitter}
 */
@Component
@ConfigurationProperties(prefix = "twitter")
@Data
public class TwitterProperties {

    private String oAuthAccessToken;
    private String oAuthAccessTokenSecret;
    private String oAuthConsumerKey;
    private String oAuthConsumerSecret;
}
