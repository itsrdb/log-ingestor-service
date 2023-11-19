package com.itsrdb.logingestor.repository;

import com.itsrdb.logingestor.model.LogMessageItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogMessageItems, Long> {

}
