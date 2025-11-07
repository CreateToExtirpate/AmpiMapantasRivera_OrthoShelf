package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "wire_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class WireInstrument extends BorrowableInstrument {

    @Enumerated(EnumType.STRING)
    @Column(name = "wire_type")
    private WireType wireType;

    public enum WireType {
        CUTTER, PASSER, TWISTER
    }

    public String getWireType() {
        return wireType.name();
    }

    public void setWireType(WireType wireType) {
        this.wireType = wireType;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSubtype(this.getWireType());

        return dto;
    }
}
