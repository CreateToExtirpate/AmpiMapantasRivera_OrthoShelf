package com.RiveraAmpiMapantas.OrthoShelf.factory;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;
import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Borrowable.BorrowableInstrument;

//Ensures that all other factories can create instruments from the dto 
//Use generic return type to handle child classes
public interface BorrowableFactory<T extends BorrowableInstrument> {
    T createFromDto(InstrumentDTO dto);
}
