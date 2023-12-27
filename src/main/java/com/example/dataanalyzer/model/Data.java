package com.example.dataanalyzer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "data")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sensor_id")
    private Long sensorId;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "measurement")
    private double measurement;
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private MeasurementType measurementType;
}