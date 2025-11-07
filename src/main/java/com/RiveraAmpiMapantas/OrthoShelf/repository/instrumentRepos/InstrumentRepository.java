package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Instrument;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
    
}
