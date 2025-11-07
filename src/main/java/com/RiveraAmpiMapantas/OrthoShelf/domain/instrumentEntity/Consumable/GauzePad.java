package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "gauze_pads")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class GauzePad extends ConsumableInstrument {

    @ManyToOne
    @JoinColumn(name = "size_id")
    private InstrumentSize size;
    //Sizing Rules: SMALL, BIG, SHORT, LONG

    public String getSize() {
        return size.getName();
    }

    public void setSize(InstrumentSize size) {
        this.size = size;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setSize(this.getSize());

        return dto;
    }
}
