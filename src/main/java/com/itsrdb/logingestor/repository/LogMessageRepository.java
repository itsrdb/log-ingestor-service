package com.itsrdb.logingestor.repository;

import com.itsrdb.logingestor.model.LogMessageItems;
import com.itsrdb.logingestor.model.QLogMessageItems;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class LogMessageRepository {
    private EntityManager entityManager;

    public List<LogMessageItems> findLogByFilter(String level,
                                                 String message,
                                                 String resourceId,
                                                 String traceId,
                                                 String spanId,
                                                 String commit,
                                                 ZonedDateTime beforeDate,
                                                 ZonedDateTime afterDate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QLogMessageItems qLogMessageItems = QLogMessageItems.logMessageItems;

        BooleanBuilder predicate = new BooleanBuilder();

        if (level != null) {
            predicate.and(qLogMessageItems.level.eq(level));
        }

        if (message != null) {
            predicate.and(qLogMessageItems.message.contains(message));
        }

        if (resourceId != null) {
            predicate.and(qLogMessageItems.resourceId.contains(resourceId));
        }

        if (traceId != null) {
            predicate.and(qLogMessageItems.traceId.contains(traceId));
        }

        if (spanId != null) {
            predicate.and(qLogMessageItems.spanId.contains(spanId));
        }

        if (commit != null) {
            predicate.and(qLogMessageItems.commit.contains(commit));
        }

        log.info("Printing dates: [{}], [{}]", beforeDate, afterDate);
        if (beforeDate != null) {
            log.info("Adding before data as [{}]", beforeDate);
            predicate.and(qLogMessageItems.timestamp.before(beforeDate));
        }

        if (afterDate != null) {
            log.info("Adding after data as [{}]", afterDate);
            predicate.and(qLogMessageItems.timestamp.after(afterDate));
        }

        log.info("Printing Predicate", predicate);
        log.info("Printing GET attributes", message, level, resourceId, traceId,
                spanId, commit, beforeDate, afterDate);

        return queryFactory
                .selectFrom(qLogMessageItems)
                .where(predicate)
                .fetch();
    }
}
