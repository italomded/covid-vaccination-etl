package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "dim_vaccine_dose")
@Entity
public class VaccineDose implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false, length = 45)
    private String dose;

    @OneToMany(mappedBy = "vaccineDose")
    private Set<Patient> patients = new HashSet<>();
}
