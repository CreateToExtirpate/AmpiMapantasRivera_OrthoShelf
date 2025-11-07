package com.RiveraAmpiMapantas.OrthoShelf.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;

@Component
public class BorrowableDispatcher {

    private final Map<String, BorrowableFactory<? extends BorrowableInstrument>> factoryMap;

    @Autowired
    public BorrowableDispatcher (
        CuttingFactory cuttingFactory,
        GraspingFactory graspingFactory,
        RetractorFactory retractorFactory,
        ImpactingFactory impactingFactory,
        WireFactory wireFactory
    ) {
        factoryMap = Map.of(
            "CUTTING", cuttingFactory,
            "GRASPING", graspingFactory,
            "RETRACTOR", retractorFactory,
            "IMPACTING", impactingFactory,
            "WIRE", wireFactory
        );
    }

    public BorrowableInstrument createInstrument(InstrumentDTO dto) {
        BorrowableFactory<? extends BorrowableInstrument> factory = factoryMap.get(dto.getType());
        if (factory == null) {
            throw new RuntimeException("Unsupported Instrument Type: " + dto.getType());
        }
        return factory.createFromDto(dto);
    }
}
