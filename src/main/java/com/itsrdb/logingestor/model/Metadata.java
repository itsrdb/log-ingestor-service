package com.itsrdb.logingestor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_log_message_metadata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parentResourceId;
}
