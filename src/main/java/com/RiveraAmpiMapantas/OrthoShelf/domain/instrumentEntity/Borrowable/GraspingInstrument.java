package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "grasping_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class GraspingInstrument extends BorrowableInstrument {

    @ManyToOne
    @JoinColumn(name = "size_id")
    private InstrumentSize instrumentSize;
    /* Sizing Rules:
     * CURVECLAMP: SMALL, MEDIUM, LARGE 
     * TISSUEELEVATOR: SMALL, BIG
     * BONEHOLDER: N/A
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "grasping_type")
    private GraspingType graspingType;

    public enum GraspingType {
        CURVECLAMP, BONEHOLDER, TISSUEELEVATOR
    }

    public String getInstrumentSize() {
        return instrumentSize.getName();
    }

    public void setInstrumentSize(InstrumentSize instrumentSize) {
        this.instrumentSize = instrumentSize;
    }

    public String getGraspingType() {
        return graspingType.name();
    }

    public void setGraspingType(GraspingType graspingType) {
        this.graspingType = graspingType;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSubtype(this.getGraspingType());
        dto.setSize(this.getInstrumentSize());

        return dto;
    }
}
