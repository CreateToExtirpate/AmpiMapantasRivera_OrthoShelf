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
@Table(name = "retractor_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class RetractorInstrument extends BorrowableInstrument {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InstrumentType instrumentType;
    /*Typing Rules:
    ANGLE: NARROW, BROAD, SKIN, DEEP TISSUE
    SELFRETAINING: FIX ANGLE*/

    @ManyToOne
    @JoinColumn(name = "size_id")
    private InstrumentSize instrumentSize;
    /*Sizing Rules:
    ANGLE: SMALL, MEDIUM, LARGE
    SELFRETAINING: N/A*/

    @Enumerated(EnumType.STRING)
    @Column(name = "retractor_type")
    private RetractorType retractorType;

    public enum RetractorType {
        ANGLE, SELFRETAINING
    }

    public String getInstrumentType() {
        return instrumentType.getName();
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentSize() {
        return instrumentSize.getName();
    }

    public void setInstrumentSize(InstrumentSize instrumentSize) {
        this.instrumentSize = instrumentSize;
    }

    public String getRetractorType() {
        return retractorType.name();
    }

    public void setRetractorType(RetractorType retractorType) {
        this.retractorType = retractorType;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSubtype(this.getRetractorType());
        dto.setUsetype(this.getInstrumentType());
        dto.setSize(this.getInstrumentSize());

        return dto;
    }
}
