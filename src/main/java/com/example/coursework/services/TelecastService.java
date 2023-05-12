package com.example.coursework.services;

import com.example.coursework.model.Telecast;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TelecastService {
    Collection<Telecast> getAllTelecasts();
    Collection<Telecast> getAllTelecasts(TelecastSortCriteria telecastSortCriteria);
    Telecast getTelecastById(Long telecastId);
    Collection<Telecast> search(String text);
    Collection<Telecast> search(String text, TelecastSortCriteria telecastSortCriteria);
    void add(Telecast telecast);
    void edit(long id, String name, String chanelName, LocalDateTime startDateTime, String description);

    void delete(Telecast telecast);
    boolean isPresent(long telecastId);
}
