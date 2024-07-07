package com.exercice.pmu_database.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Course")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_course;

    @Column(name = "numero")
    private int numero;

    @Column(name = "date")
    private String date;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<PartantEntity> partants;

}
