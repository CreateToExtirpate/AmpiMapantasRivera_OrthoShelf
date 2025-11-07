package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.InstrumentType;

@Repository
public interface  InstrumentTypeRepository extends JpaRepository<InstrumentType, Integer>{
    Optional<InstrumentType> findByName(String name);
}
