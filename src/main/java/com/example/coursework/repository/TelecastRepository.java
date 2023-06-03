package com.example.coursework.repository;

import com.example.coursework.model.Telecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelecastRepository extends CrudRepository<Telecast, Long> {
}
