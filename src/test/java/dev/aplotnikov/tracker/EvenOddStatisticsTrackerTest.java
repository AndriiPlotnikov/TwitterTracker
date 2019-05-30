package dev.aplotnikov.tracker;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EvenOddStatisticsTrackerTest {
    public static final int ODD_NUMBER = 1;
    @InjectMocks
    private EvenOddStatisticsTracker statisticsTracker;

    @Test
    public void statisticsSend_properOutputReceived() {
        statisticsTracker.sendStatistics(ODD_NUMBER);

        String statistics = statisticsTracker.getStatistics();

        assertThat(statistics).contains("Odd tweets: 100%");
    }

}
