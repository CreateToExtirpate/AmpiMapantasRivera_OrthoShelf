package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.WireInstrument;

@Component
public class WireFactory implements BorrowableFactory<WireInstrument> {

    @Override
    public WireInstrument createFromDto(InstrumentDTO dto) {
        WireInstrument wire = new WireInstrument();
        wire.setName(dto.getName());
        wire.setPrice(dto.getPrice());
        wire.setStock(dto.getStock());
        wire.setDescription(dto.getDescription());
        wire.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        wire.setBorrowType(BorrowableInstrument.BorrowType.valueOf(dto.getType()));
        wire.setWireType(WireInstrument.WireType.valueOf(dto.getSubtype()));
        wire.setBorrowCount(0);

        return wire;
    }
}
