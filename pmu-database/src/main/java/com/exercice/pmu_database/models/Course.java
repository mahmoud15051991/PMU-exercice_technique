package com.exercice.pmu_database.models;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Course {

    private int numero;
    private String date;
    private String nom;
    private List<Partant> partants;

}
