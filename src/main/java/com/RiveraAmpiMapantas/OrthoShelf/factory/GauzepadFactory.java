package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.ConsumableInstrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.GauzePad;
import com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos.InstrumentSizeRepository;

@Component
public class GauzepadFactory {
    @Autowired
    private InstrumentSizeRepository sizeRepo;

    public GauzePad createFromDto(InstrumentDTO dto) {
        GauzePad gauze = new GauzePad();
        gauze.setName(dto.getName());
        gauze.setPrice(dto.getPrice());
        gauze.setStock(dto.getStock());
        gauze.setDescription(dto.getDescription());
        gauze.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        gauze.setConsumableType(ConsumableInstrument.ConsumableType.valueOf(dto.getType()));

        InstrumentSize size = sizeRepo.findByName(dto.getSize())
            .orElseThrow(() -> new RuntimeException("Invalid size: " + dto.getSize()));
        gauze.setSize(size);

        return gauze;
    }
}
