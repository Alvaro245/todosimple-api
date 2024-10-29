package com.alvarotrindade.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = Level.TABLE_NAME)

public class Level {
public static final String TABLE_NAME = "level";




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true )
    private long id;



     @Column (name = "anotacoes", length = 225, nullable = false)
     @NotNull
     @NotEmpty
    private String anotacoes;
}
