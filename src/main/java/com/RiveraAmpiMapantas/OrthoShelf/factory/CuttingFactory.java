package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentType;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.CuttingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentSizeRepository;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentTypeRepository;

@Component
public class CuttingFactory implements BorrowableFactory<CuttingInstrument> {

    @Autowired
    private InstrumentTypeRepository typeRepo;
    @Autowired
    private InstrumentSizeRepository sizeRepo;

    @Override
    public CuttingInstrument createFromDto(InstrumentDTO dto) {
        CuttingInstrument cutting = new CuttingInstrument();
        cutting.setName(dto.getName());
        cutting.setPrice(dto.getPrice());
        cutting.setStock(dto.getStock());
        cutting.setDescription(dto.getDescription());
        cutting.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        cutting.setBorrowType(BorrowableInstrument.BorrowType.valueOf(dto.getType()));
        cutting.setCuttingType(CuttingInstrument.CuttingType.valueOf(dto.getSubtype()));
        cutting.setBorrowCount(0);

        InstrumentType type = typeRepo.findByName(dto.getUsetype())
                .orElseThrow(() -> new RuntimeException("Invalid use type: " + dto.getUsetype()));
        InstrumentSize size = sizeRepo.findByName(dto.getSize())
        .orElseThrow(() -> new RuntimeException("Invalid size: " + dto.getSize()));

        cutting.setInstrumentType(type);
        cutting.setInstrumentSize(size);

        return cutting;
    }
}
