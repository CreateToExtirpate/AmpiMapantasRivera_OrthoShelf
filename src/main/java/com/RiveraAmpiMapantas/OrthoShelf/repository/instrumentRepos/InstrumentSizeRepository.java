package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentSize;

@Repository
public interface InstrumentSizeRepository extends JpaRepository<InstrumentSize, Integer>{
    Optional<InstrumentSize> findByName(String name);
}
