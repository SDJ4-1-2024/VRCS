package org.example.service;

import org.example.repository.BookingsRepository;

public class BookingsService {

    private BookingsRepository bookingsRepository;

    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

}
