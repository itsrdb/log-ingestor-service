package com.itsrdb.logingestor.service;

import com.itsrdb.logingestor.dto.LogMessageItemsDto;
import com.itsrdb.logingestor.dto.LogMessageRequest;
import com.itsrdb.logingestor.model.LogMessage;
import com.itsrdb.logingestor.model.LogMessageItems;
import com.itsrdb.logingestor.repository.LogMessageRepository;
import com.itsrdb.logingestor.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoggingService {
    private final LogRepository logRespository;
    private final LogMessageRepository logMessageRepository;

    public String storeLogs(LogMessageRequest logMessageRequest) {
        LogMessage logMessage = new LogMessage();

        List<LogMessageItems> logMessageItemsList = logMessageRequest.getLogMessageItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        logMessageItemsList.forEach((c) -> logRespository.save(c));

        return "Saved log messages successfully";
    }

    private LogMessageItems mapToDto(LogMessageItemsDto logMessageItemsDto) {
        LogMessageItems logMessageItems = new LogMessageItems();

        logMessageItems.setLevel(logMessageItemsDto.getLevel());
        logMessageItems.setMessage(logMessageItemsDto.getMessage());
        logMessageItems.setResourceId(logMessageItemsDto.getResourceId());
        logMessageItems.setTimestamp(logMessageItemsDto.getTimestamp());
        logMessageItems.setTraceId(logMessageItemsDto.getTraceId());
        logMessageItems.setSpanId(logMessageItemsDto.getSpanId());
        logMessageItems.setCommit(logMessageItemsDto.getCommit());
        logMessageItems.setMetadata(logMessageItemsDto.getMetadata());

        return logMessageItems;
    }

    public List<LogMessageItems> getLogsByQuery(
            String message,
            String level,
            String resourceId,
            String traceId,
            String spanId,
            String commit,
            LocalDate beforeDate,
            LocalDate afterDate,
            String parentResourceId
    ) {
        ZonedDateTime zonedBeforeDate = null;
        ZonedDateTime zonedAfterDate = null;

        LocalTime defaultTime = LocalTime.of(12, 0);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

        if(beforeDate != null){
            zonedBeforeDate = ZonedDateTime.of(beforeDate, defaultTime, istZone);
        }
        if(afterDate != null){
            zonedAfterDate = ZonedDateTime.of(afterDate, defaultTime, istZone);
        }
        if(level != null && level.isBlank()){
            level = null;
        }

        List<LogMessageItems> logMessageItemsList = logMessageRepository
                .findLogByFilter(level, message, resourceId, traceId, spanId,
                        commit, zonedBeforeDate, zonedAfterDate, parentResourceId);

        return logMessageItemsList;
    }
}
