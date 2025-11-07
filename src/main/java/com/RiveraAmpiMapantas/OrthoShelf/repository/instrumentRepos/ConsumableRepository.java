package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.ConsumableInstrument;

@Repository
public interface ConsumableRepository extends JpaRepository<ConsumableInstrument, Integer>{
    
}
