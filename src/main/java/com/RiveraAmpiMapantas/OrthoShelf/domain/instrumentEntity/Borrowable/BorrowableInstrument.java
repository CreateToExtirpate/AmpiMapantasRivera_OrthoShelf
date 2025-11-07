package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowable_instruments")
@PrimaryKeyJoinColumn(name = "instrument_id")
public class BorrowableInstrument extends Instrument {

    @Enumerated(EnumType.STRING)
    @Column(name = "borrow_type")
    private BorrowType borrowType;

    public enum BorrowType {
        CUTTING, GRASPING, RETRACTOR, IMPACTING, WIRE
    }

    //Number of Instruments borrowed from stock
    @Column(name = "borrow_count")
    private int borrowCount;

    //Type Getter & Setter
    public String getBorrowType() {
        return borrowType.name();
    }

    public void setBorrowType(BorrowType borrowType) {
        this.borrowType = borrowType;
    }

    //Count Getter & Setter
    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    //Other Methods for Ordering
    public boolean isAvailable() {
        return super.getStock() - getBorrowCount() > 0;
    }

    @Override
    public InstrumentDTO toDto() {
        InstrumentDTO dto = super.toDto();
        dto.setBorrowedStock(this.getBorrowCount());
        dto.setType(this.getBorrowType());

        return dto;
    }
}
