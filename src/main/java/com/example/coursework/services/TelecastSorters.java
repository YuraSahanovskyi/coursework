package com.example.coursework.services;

import com.example.coursework.model.Telecast;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TelecastSorters {
    public static final Map<TelecastSortCriteria, Comparator<Telecast>> sorters;

    static {
        sorters = new HashMap<>();
        sorters.put(TelecastSortCriteria.BY_START, Comparator.comparing(Telecast::getStartDateTime));
        sorters.put(TelecastSortCriteria.BY_NAME, Comparator.comparing(Telecast::getName));
        sorters.put(TelecastSortCriteria.BY_CHANEL, Comparator.comparing(Telecast::getChanelName));
    }
}
