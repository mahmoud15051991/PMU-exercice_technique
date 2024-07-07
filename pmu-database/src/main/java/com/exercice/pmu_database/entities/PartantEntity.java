package com.exercice.pmu_database.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Partant")
@Data
public class PartantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_partant;

    @Column(name = "numero")
    private int numero;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id_course")
    private CourseEntity course;

}
