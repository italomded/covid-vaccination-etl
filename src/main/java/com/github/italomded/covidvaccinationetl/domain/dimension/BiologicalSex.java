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
@EqualsAndHashCode(of = {"representation"})
@NoArgsConstructor
@Table(name = "dim_biological_sex")
@Entity
public class BiologicalSex implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false)
    private Character representation;

    @OneToMany(mappedBy = "biologicalSex")
    private Set<Patient> patients = new HashSet<>();
}
