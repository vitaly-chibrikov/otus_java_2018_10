package ru.otus.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserIdGeneratorImpl implements UserIdGenerator {

    private static AtomicLong USER_ID = new AtomicLong(0);

    @Override
    public long getUserId() {
        return USER_ID.incrementAndGet();
    }
}
