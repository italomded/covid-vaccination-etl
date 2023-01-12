package com.github.italomded.covidvaccinationetl.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@NoArgsConstructor
public class History {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(nullable = false)
    private long createdFacts;
    @Column(nullable = false)
    private long rowsRead;
    @Column(nullable = false)
    private LocalDateTime date;

    public History(long createdFacts, long rowsRead, LocalDateTime date) {
        this.createdFacts = createdFacts;
        this.rowsRead = rowsRead;
        this.date = date;
    }
}
