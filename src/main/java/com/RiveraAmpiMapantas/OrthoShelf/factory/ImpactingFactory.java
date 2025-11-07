package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.ImpactingInstrument;

@Component
public class ImpactingFactory implements BorrowableFactory<ImpactingInstrument> {
    @Override
    public ImpactingInstrument createFromDto(InstrumentDTO dto) {
        ImpactingInstrument impacting = new ImpactingInstrument();
        impacting.setName(dto.getName());
        impacting.setPrice(dto.getPrice());
        impacting.setStock(dto.getStock());
        impacting.setDescription(dto.getDescription());
        impacting.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        impacting.setBorrowType(BorrowableInstrument.BorrowType.valueOf(dto.getType()));
        impacting.setImpactingType(ImpactingInstrument.ImpactingType.valueOf(dto.getSubtype()));
        impacting.setBorrowCount(0);

        return impacting;
    }
}
