package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "impacting_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class ImpactingInstrument extends BorrowableInstrument {

    @Enumerated(EnumType.STRING)
    @Column(name = "impacting_type")
    private ImpactingType impactingType;

    public enum ImpactingType {
        MALLET
    }

    public String getImpactingType() {
        return impactingType.name();
    }

    public void setImpactingType(ImpactingType impactingType) {
        this.impactingType = impactingType;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSubtype(this.getImpactingType());

        return dto;
    }
}
