package com.exercice.pmu_datamodel.models;

import lombok.Data;

import java.util.Set;

@Data
public class Course {

    private int numero;
    private String date;
    private String nom;
    private Set<Partant> partants;

}
