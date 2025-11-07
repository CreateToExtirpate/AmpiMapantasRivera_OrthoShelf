package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumable_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class ConsumableInstrument extends Instrument {

    @Enumerated(EnumType.STRING)
    @Column(name = "consumable_type")
    private ConsumableType consumableType;

    public enum ConsumableType {
        ECELECTRODE, SUCTIONTUBING, GAUZEPAD
    }

    public String getConsumableType() {
        return consumableType.name();
    }

    public void setConsumableType(ConsumableType consumableType) {
        this.consumableType = consumableType;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setType(this.getConsumableType());

        return dto;
    }
}
