package dev.aplotnikov.service;

import dev.aplotnikov.tracker.EvenOddStatisticsTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * default implementation {@link OutputService}
 */
@Service
@Slf4j
public class OutputServiceImpl implements OutputService {

    @Autowired
    private EvenOddStatisticsTracker statisticsTracker;

    @Override
    @Scheduled(fixedRateString = "${fixedRateMs}")
    public void printStatistics() {
        log.info(statisticsTracker.getStatistics());
    }
}
