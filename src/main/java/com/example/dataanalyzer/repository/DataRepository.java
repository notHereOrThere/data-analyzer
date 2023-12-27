package com.example.dataanalyzer.repository;

import com.example.dataanalyzer.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}