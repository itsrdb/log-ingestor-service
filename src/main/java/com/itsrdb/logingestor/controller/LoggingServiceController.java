package com.itsrdb.logingestor.controller;

import com.itsrdb.logingestor.dto.LogMessageRequest;
import com.itsrdb.logingestor.model.LogMessageItems;
import com.itsrdb.logingestor.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Slf4j
public class LoggingServiceController {

    private final LoggingService loggingService;

    @PostMapping("/ingest")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CompletableFuture<String> ingestLogs(@RequestBody LogMessageRequest logMessageRequest) {
        log.info("Printing request: [{}]", logMessageRequest);
        return CompletableFuture.supplyAsync(()-> loggingService.storeLogs(logMessageRequest));
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<LogMessageItems> queryLogs(){
        return loggingService.getLogsByQuery();
    }
}
