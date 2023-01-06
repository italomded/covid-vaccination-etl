package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
@Entity(name = "dim_vaccine")
public class Vaccine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = 45, unique = true)
    private String name;
    @Column(nullable = false, length = 45)
    private String category;
    @Column(nullable = false, length = 45)
    private String batch;
    @Column(nullable = false, length = 45)
    private String manufacturer;

    @OneToMany(mappedBy = "vaccine")
    private Set<Patient> patients;
}
