package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "dim_vaccine_dose")
public class VaccineDose {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false, length = 45)
    private String dose;

    @OneToMany(mappedBy = "vaccineDose")
    private Set<Patient> patients;
}
