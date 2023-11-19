package com.itsrdb.logingestor.dto;

import com.itsrdb.logingestor.model.Metadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogMessageItemsDto {
    private Long id;
    private String level;
    private String message;
    private String resourceId;
    private ZonedDateTime timestamp;
    private String traceId;
    private String spanId;
    private String commit;
    private Metadata metadata;
}
