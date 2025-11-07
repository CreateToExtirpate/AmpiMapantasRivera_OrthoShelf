package com.RiveraAmpiMapantas.OrthoShelf.factory;

import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.ConsumableInstrument;

@Component
public class ConsumableFactory {
    public ConsumableInstrument createFromDto(InstrumentDTO dto) {
        ConsumableInstrument consumable = new ConsumableInstrument();
        consumable.setName(dto.getName());
        consumable.setPrice(dto.getPrice());
        consumable.setStock(dto.getStock());
        consumable.setDescription(dto.getDescription());
        consumable.setCategory(Instrument.Category.valueOf(dto.getCategory()));
        consumable.setConsumableType(ConsumableInstrument.ConsumableType.valueOf(dto.getType()));

        return consumable;
    }
}
