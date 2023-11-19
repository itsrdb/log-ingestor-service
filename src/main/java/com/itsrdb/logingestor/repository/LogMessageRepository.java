package com.itsrdb.logingestor.repository;

import com.itsrdb.logingestor.model.LogMessageItems;
import com.itsrdb.logingestor.model.QLogMessageItems;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class LogMessageRepository {
    private EntityManager entityManager;

    public List<LogMessageItems> findLogByFilter(String lastName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QLogMessageItems qLogMessageItems = QLogMessageItems.logMessageItems;
        return queryFactory.selectFrom(qLogMessageItems)
                .where(qLogMessageItems.level.eq("info"))
                .fetch();
    }
}
