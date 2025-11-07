package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.GraspingInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentSizeRepository;

@Component
public class GraspingFactory implements BorrowableFactory<GraspingInstrument> {

    @Autowired
    private InstrumentSizeRepository sizeRepo;

    @Override
    public GraspingInstrument createFromDto(InstrumentDTO dto) {
        GraspingInstrument grasping = new GraspingInstrument();
        grasping.setName(dto.getName());
        grasping.setPrice(dto.getPrice());
        grasping.setStock(dto.getStock());
        grasping.setDescription(dto.getDescription());
        grasping.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        grasping.setBorrowType(BorrowableInstrument.BorrowType.valueOf(dto.getType()));
        grasping.setGraspingType(GraspingInstrument.GraspingType.valueOf(dto.getSubtype()));
        grasping.setBorrowCount(0);

        InstrumentSize size = sizeRepo.findByName(dto.getSize()).orElseThrow(() -> new RuntimeException("Invalid size: " + dto.getSize()));
        grasping.setInstrumentSize(size);

        return grasping;
    }
}
