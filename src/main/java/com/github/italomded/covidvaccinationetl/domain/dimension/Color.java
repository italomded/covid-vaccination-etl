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
@Table(name = "dim_color")
@Entity
public class Color implements Dimension {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false, length = 45)
    private String representation;

    @OneToMany(mappedBy = "color")
    private Set<Patient> patients = new HashSet<>();
}
