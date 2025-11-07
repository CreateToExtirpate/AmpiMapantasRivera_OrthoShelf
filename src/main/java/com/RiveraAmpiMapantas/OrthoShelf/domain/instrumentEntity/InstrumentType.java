package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/*This class is linked to the instrument_types look-up table
*It is used to identify the use-type of a specific intrument
*It is linked via FK and is used across multiple instrument sub-classes*/
@Entity
@Table(name="instrument_types")
public class InstrumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="type_id")
    private int id;

    @Column(name="type_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
