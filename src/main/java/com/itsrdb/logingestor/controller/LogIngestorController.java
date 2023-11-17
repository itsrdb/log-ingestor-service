package com.itsrdb.logingestor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/logs/ingest")
public class LogIngestorController {

    @PostMapping
    public CompletableFuture<String> ingestLogs(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));

    }
}
