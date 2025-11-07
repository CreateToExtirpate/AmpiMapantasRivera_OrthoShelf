package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity.Consumable.GauzePad;

@Repository
public interface GauzepadRepository extends JpaRepository<GauzePad, Integer>{

}
