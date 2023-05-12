package com.example.coursework.services;

import com.example.coursework.model.Telecast;
import com.example.coursework.repository.TelecastRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TelecastServiceImpl implements TelecastService{
    //TODO override service methods

    public TelecastServiceImpl(TelecastRepository telecastRepository) {
        this.telecastRepository = telecastRepository;
        this.defaultSortCriteria = TelecastSortCriteria.BY_START;
    }

    private final TelecastRepository telecastRepository;
    private final TelecastSortCriteria defaultSortCriteria;

    @Override
    public Collection<Telecast> getAllTelecasts() {
        Collection<Telecast> telecasts= (Collection<Telecast>) telecastRepository.findAll();
        telecasts = filterActual(telecasts);
        telecasts = sort(telecasts, defaultSortCriteria);
        return telecasts;
    }
    @Override
    public Collection<Telecast> getAllTelecasts(TelecastSortCriteria telecastSortCriteria) {
        Collection<Telecast> telecasts= (Collection<Telecast>) telecastRepository.findAll();
        telecasts = filterActual(telecasts);
        telecasts = sort(telecasts, telecastSortCriteria);
        return telecasts;
    }
    //TODO search
    @Override
    public Telecast getTelecastById(Long telecastId) {
        return telecastRepository.findById(telecastId).orElseThrow();
    }
    @Override
    public Collection<Telecast> search(String text) {
        return null;
    }
    @Override
    public Collection<Telecast> search(String text, TelecastSortCriteria telecastSortCriteria) {
        return null;
    }
    @Override
    public void add(Telecast telecast) {
        telecastRepository.save(telecast);
    }
    @Override
    public void edit(long id, String name, String chanelName, LocalDateTime startDateTime, String description) {
        Telecast telecast = this.getTelecastById(id);
        telecast.setName(name);
        telecast.setChanelName(chanelName);
        telecast.setStartDateTime(startDateTime);
        telecast.setDescription(description);
        this.add(telecast);
    }
    @Override
    public void delete(Telecast telecast) {
        telecastRepository.delete(telecast);
    }
    @Override
    public boolean isPresent(long telecastId) {
        return telecastRepository.existsById(telecastId);
    }
    private Collection<Telecast> sort(Collection<Telecast> telecasts, TelecastSortCriteria criteria) {
        return telecasts.stream()
                .sorted(TelecastSorters.sorters.get(criteria))
                .collect(Collectors.toCollection(LinkedList::new));
    }
    private Collection<Telecast> filterActual(Collection<Telecast> telecasts) {
        return telecasts.stream()
                .filter(telecast -> telecast.getStartDateTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
