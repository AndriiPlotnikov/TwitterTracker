package dev.aplotnikov.service;


import dev.aplotnikov.tracker.EvenOddStatisticsTracker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OutputServiceImplTest {

    @Mock
    private EvenOddStatisticsTracker statisticsTracker;

    @InjectMocks
    private OutputServiceImpl outputService;

    @Test
    public void output_noException(){
        Mockito.when(statisticsTracker.getStatistics()).thenReturn("Statistics");

        outputService.outputStatistics();
    }

}
