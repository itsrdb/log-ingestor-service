package com.itsrdb.logingestor.controller;

import com.itsrdb.logingestor.dto.LogMessageRequest;
import com.itsrdb.logingestor.model.LogMessageItems;
import com.itsrdb.logingestor.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Slf4j
public class LoggingServiceController {
    private final LoggingService loggingService;

    @PostMapping("/ingest")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CompletableFuture<String> ingestLogs(@RequestBody LogMessageRequest logMessageRequest) {
        return CompletableFuture.supplyAsync(()-> loggingService.storeLogs(logMessageRequest));
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<LogMessageItems> queryLogs(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String resourceId,
            @RequestParam(required = false) String traceId,
            @RequestParam(required = false) String spanId,
            @RequestParam(required = false) String commit,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate afterDate,
            @RequestParam(required = false) String parentResourceId) {
        return loggingService.getLogsByQuery(query, level, resourceId, traceId,
                spanId, commit, beforeDate, afterDate, parentResourceId);
    }
}
