package dev.aplotnikov.listener;

import dev.aplotnikov.tracker.EvenOddStatisticsTracker;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.JSONException;
import twitter4j.Status;

@RunWith(MockitoJUnitRunner.class)
public class WordCountFeedListenerTest {

    @Mock
    private EvenOddStatisticsTracker statisticsTracker;


    @InjectMocks
    private WordCountFeedListener wordCountFeedListener;

    @Test
    public void listenerOnStatus_sendStatisticsCalled() {
        Status status = Mockito.mock(Status.class);
        Mockito.when(status.getText()).thenReturn("Mock text");

        wordCountFeedListener.onStatus(status);

        Mockito.verify(statisticsTracker).sendStatistics(Mockito.anyInt());
    }

    @Test
    public void listenerOnError_noException() {
        Exception status = Mockito.mock(Exception.class);

        wordCountFeedListener.onException(status);
    }

    @Test
    @Ignore
    public void listenerOnWarning_noException() throws JSONException {
        // Cannot access JSONObject, ignore test...
//        StallWarning warning = new StallWarning(new JSONObject());

//        wordCountFeedListener.onStallWarning(warning);
    }
}
