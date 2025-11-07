package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="cutting_instruments")
@PrimaryKeyJoinColumn(name="instrument_id")
public class CuttingInstrument extends BorrowableInstrument{
    
    @ManyToOne
    @JoinColumn(name="type_id")
    private InstrumentType instrumentType;
    /*Typing rules:
    BONEHOLDER & BONEFILE: N/A
    SCISSORS: FABRIC, TISSUE
    BONECHISEL: NARROW, BROAD*/

    @ManyToOne
    @JoinColumn(name="size_id")
    private InstrumentSize instrumentSize;
    /*Sizing Rules:
    *CURETTE: SMALL, MEDIUM, BIG
    *BONECHISEL, SCISSORS, BONEHOLDER, & BONEFILE: N/A\
    */

    @Enumerated(EnumType.STRING)
    @Column(name="cutting_type")
    private CuttingType cuttingType;

    public enum CuttingType {
       BLADEHOLDER, SCISSORS, BONECHISEL, BONEFILE, CURETTE
    }
    
    public String getInstrumentSize() {
        return instrumentSize.getName();
    }

    public void setInstrumentSize(InstrumentSize instrumentSize) {
        this.instrumentSize = instrumentSize;
    }

    public String getCuttingType() {
        return cuttingType.name();
    }

    public void setCuttingType(CuttingType cuttingType) {
        this.cuttingType = cuttingType;
    }

    public String getInstrumentType() {
        return instrumentType.getName();
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
    
    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSubtype(this.getCuttingType());
        dto.setUsetype(this.getInstrumentType());
        dto.setSize(this.getInstrumentSize());

        return dto;
    }
}
