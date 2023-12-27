package com.example.dataanalyzer.service;

import com.example.dataanalyzer.model.Data;

public interface KafkaDataService {
    void handle(Data data);

}