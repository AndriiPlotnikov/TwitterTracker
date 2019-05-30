package dev.aplotnikov.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private String language;

    @Value("#{'${twitter.track}'.split(',')}")
    private String[] track;

    @Value("#{'${twitter.locations}'.split(',')}")
    private List<String> locations;

}
