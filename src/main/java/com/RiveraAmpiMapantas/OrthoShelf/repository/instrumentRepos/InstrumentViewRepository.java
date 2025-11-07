package com.RiveraAmpiMapantas.OrthoShelf.repository.instrumentRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RiveraAmpiMapantas.OrthoShelf.domain.InstrumentView;

@Repository
public interface InstrumentViewRepository extends JpaRepository<InstrumentView, Integer> {

}
