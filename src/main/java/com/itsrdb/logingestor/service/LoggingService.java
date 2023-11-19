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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoggingService {
    private final LogRepository logRespository;
    private final LogMessageRepository logMessageRepository;

    public String storeLogs(LogMessageRequest logMessageRequest) {
        LogMessage logMessage = new LogMessage();

        log.info("Hey is it working?");
        log.error(logMessageRequest.toString());

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

    public List<LogMessageItems> getLogsByQuery() {
        List<LogMessageItems> logMessageItemsList = logMessageRepository
                .findLogByFilter("abc");
        log.info("Printing search query [{}]", logMessageItemsList);
        return logMessageItemsList;
    }
}
