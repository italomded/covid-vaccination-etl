package com.github.italomded.covidvaccinationetl.domain.fact;

import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = {"patientIdentifier"})
@NoArgsConstructor
@Entity(name = "fact_patient")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, unique = true, length = 90)
    private String patientIdentifier;
    @Column(nullable = false)
    private Integer quantity = 1;

    @JoinColumn(nullable = false) @ManyToOne
    private PatientAddress adress;
    @JoinColumn(nullable = false) @ManyToOne
    private Birthdate birthdate;
    @JoinColumn(nullable = false) @ManyToOne
    private VaccinationSite vaccinationSite;
    @JoinColumn(nullable = false) @ManyToOne
    private VaccinationDate vaccinationDate;
    @JoinColumn(nullable = false) @ManyToOne
    private BiologicalSex biologicalSex;
    @JoinColumn(nullable = false) @ManyToOne
    private Vaccine vaccine;
    @JoinColumn(nullable = false) @ManyToOne
    private Color color;
    @JoinColumn(nullable = false) @ManyToOne
    private VaccineDose vaccineDose;

    public Patient(PatientAddress adress, Birthdate birthdate, VaccinationSite vaccinationSite, VaccinationDate vaccinationDate,
                   BiologicalSex biologicalSex, Vaccine vaccine, Color color, VaccineDose vaccineDose) {
        this.adress = adress;
        this.birthdate = birthdate;
        this.vaccinationSite = vaccinationSite;
        this.vaccinationDate = vaccinationDate;
        this.biologicalSex = biologicalSex;
        this.vaccine = vaccine;
        this.color = color;
        this.vaccineDose = vaccineDose;
    }
}
