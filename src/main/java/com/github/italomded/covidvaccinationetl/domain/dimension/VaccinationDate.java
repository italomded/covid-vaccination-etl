package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(of = {"fullDate"})
@NoArgsConstructor
@Table(name = "dim_vaccination_date")
@Entity
public class VaccinationDate implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false)
    private LocalDate fullDate;
    @Column(nullable = false)
    private Integer day;
    @Column(nullable = false)
    private Integer month;
    @Column(nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "vaccinationDate")
    private Set<Patient> patients = new HashSet<>();

    public VaccinationDate(LocalDate fullDate) {
        this.fullDate = fullDate;
        this.day = fullDate.getDayOfMonth();
        this.month = fullDate.getMonthValue();
        this.year = fullDate.getYear();
    }
}
