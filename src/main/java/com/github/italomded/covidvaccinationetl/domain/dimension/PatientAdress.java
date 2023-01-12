package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(of = {"county"})
@NoArgsConstructor
@Table(name = "dim_patient_adress")
@Entity
public class PatientAdress implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(length = 45, unique = true, nullable = false)
    private String county;
    @Column(length = 45, nullable = false)
    private String state;
    @Column(length = 45, nullable = false)
    private String country;

    @OneToMany(mappedBy = "adress")
    private Set<Patient> patients = new HashSet<>();
}
