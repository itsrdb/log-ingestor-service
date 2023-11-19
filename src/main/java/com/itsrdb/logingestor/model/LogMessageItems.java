package com.itsrdb.logingestor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "t_log_message_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LogMessageItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    private String message;
    private String resourceId;
    private ZonedDateTime timestamp;
    private String traceId;
    private String spanId;
    private String commit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_log_message_metadata_id", referencedColumnName = "id")
    private Metadata metadata;
}