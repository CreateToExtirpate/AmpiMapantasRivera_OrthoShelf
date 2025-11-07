package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentType;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.RetractorInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentSizeRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentTypeRepository;

@Component
public class RetractorFactory implements BorrowableFactory<RetractorInstrument> {

    @Autowired
    private InstrumentTypeRepository typeRepo;
    @Autowired
    private InstrumentSizeRepository sizeRepo;

    @Override
    public RetractorInstrument createFromDto(InstrumentDTO dto) {
        RetractorInstrument retractor = new RetractorInstrument();
        retractor.setName(dto.getName());
        retractor.setPrice(dto.getPrice());
        retractor.setStock(dto.getStock());
        retractor.setDescription(dto.getDescription());
        retractor.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        retractor.setBorrowType(BorrowableInstrument.BorrowType.valueOf(dto.getType()));
        retractor.setRetractorType(RetractorInstrument.RetractorType.valueOf(dto.getSubtype()));
        retractor.setBorrowCount(0);

        InstrumentType type = typeRepo.findByName(dto.getUsetype())
                .orElseThrow(() -> new RuntimeException("Invalid use type: " + dto.getUsetype()));
        InstrumentSize size = sizeRepo.findByName(dto.getSize())
                .orElseThrow(() -> new RuntimeException("Invalid size: " + dto.getSize()));

        retractor.setInstrumentType(type);
        retractor.setInstrumentSize(size);

        return retractor;
    }

}
