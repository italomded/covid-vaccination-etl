package com.github.italomded.covidvaccinationetl.domain.dimension;

import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(of = {"corporateName"})
@NoArgsConstructor
@Entity(name = "dim_vaccination_site")
public class VaccinationSite implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false, length = 90)
    private String corporateName;
    @Column(nullable = false, length = 45)
    private String county;
    @Column(nullable = false, length = 45)
    private String state;

    @OneToMany(mappedBy = "vaccinationSite")
    private Set<Patient> patients = new HashSet<>();
}
