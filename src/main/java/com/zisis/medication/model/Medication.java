package com.zisis.medication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String ingredient;

    @Column
    private String manufacturer;

    @Column
    private String code;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column
    private Integer quantity;
}
